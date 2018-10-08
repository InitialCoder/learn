package com.ascend.demo.ext.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.util.DigestUtils;
import org.springframework.web.util.HtmlUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("all")
public class ImageUtil {
	private static final Log logger = LogFactory.getLog(ImageUtil.class);
	public static Pattern IMAGE_BASE64_PREFIX_PATTERN = Pattern.compile("^data:image/\\w+;base64,");
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	public static String imageBase64Prefix(String str){
		Matcher m = IMAGE_BASE64_PREFIX_PATTERN.matcher(str);
		if(m.find()){
			return m.group();
		}else{
			return null;
		}
	}
	/**
	 * 压缩图片
	 * 
	 * @param image
	 * @param dest
	 * @throws IOException
	 */
	public static void write(BufferedImage image, OutputStream dest, float quality) throws IOException {
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(dest);
		JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(image);
		param.setQuality(quality, true);// 压缩质量
		encoder.encode(image, param);
	}

	// 平滑优先SCALE_SMOOTH
	// 速度优先SCALE_FAST
	// 区域均值SCALE_AREA_AVERAGING
	// 像素复制型缩放SCALE_REPLICATE
	// 默认缩放模式SCALE_DEFAULT
	public static void zoom(InputStream inputStream, OutputStream outputStream, double scale) {
		BufferedImage src = null;
		try {
			src = ImageIO.read(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		int width = (int) (src.getWidth() * scale);
		int height = (int) (src.getHeight() * scale);
		Image resizedImage = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.drawImage(resizedImage, 0, 0, null); // 绘制缩小后的图
		g.dispose();
		try {
			ImageIO.write(image, "PNG", outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static BufferedImage watermark(int width, int height, Font font, Color color, String text) {
		try {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D gd = image.createGraphics();
			// 设置透明 start
			image = gd.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
			gd = image.createGraphics();
			gd.setFont(font); // 设置字体
			gd.setColor(color); // 设置颜色
			// gd.drawRect(0, 0, width - 1, height - 1); // 画边框
			gd.translate(width / 2, height / 2);// 平移原点到图形的中心
			gd.rotate(-0.5);// 倾斜度
			gd.drawString(text, 0, 0);
			// gd.drawString(text, 50, height / 2 - font.getSize());
			return image;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void zoom(InputStream inputStream, OutputStream outputStream, int maxWidth, int maxHeight) {
		// 读取图片
		BufferedImage src = null;
		try {
			src = ImageIO.read(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		// 调整大小
		int width = src.getWidth();
		int height = src.getHeight();
		int ratio = width / height;
		// 缩放比例
		if (width / height > maxWidth / maxHeight) {
			height = maxWidth * height / width;
			width = maxWidth;
		} else {
			width = width * maxHeight / height;
			height = maxHeight;
		}
		// // 调整大小
		if (width < maxWidth) {
			height = maxWidth * height / width;
			width = maxWidth;
		}
		if (height < maxHeight) {
			width = width * maxHeight / height;
			height = maxHeight;
		}
		Image resizedImage = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage image = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 补白
		g.setColor(Color.white);
		g.fillRect(0, 0, maxWidth, maxHeight);
		// 图片居中
		g.drawImage(resizedImage, (maxWidth - width) / 2, (maxHeight - height) / 2, null); // 绘制缩小后的图
		// 水印
		g.setColor(Color.white);
		g.setFont(new Font("宋体", Font.PLAIN, 20));
		g.drawString("刘玉石", maxWidth > width ? (maxWidth - width) / 2 : 0, maxHeight > height ? maxHeight - (maxHeight - height) / 2 - 8 : maxHeight - 8);
		g.dispose();
		// 锐化
		float softenFactor = 0.05f;
		float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
		Kernel kernel = new Kernel(3, 3, softenArray);
		ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		image = cOp.filter(image, null);
		// 写入图片
		try {
			write(image, outputStream, 1);
			// ImageIO.write(tag, "JPEG", outputStream);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	private static String dir(String baseDir) {
		File baseFile = new File(baseDir);
		if (!baseFile.exists()) {
			baseFile.mkdir();
		}
		File dir = new File(baseDir, getDirName());
		if (!dir.exists()) {
			dir.mkdir();
		}
		String returndir = dir.getAbsolutePath();
		returndir = returndir.replaceAll("\\\\", "/");
		return returndir;
	}

	/** 
	 *
	 * @Title:  getDirName 
	 * @Description: 获取当天的文件夹格式 如日期为 2018-04-24  则返回 20180424
	 * @return String     
	 * @throws 
	 *
	 */
	public static String getDirName() {
		return simpleDateFormat.format(new Date());
	}
	/** 
	 *
	 * @Title:  getYesTerdayDirName 
	 * @Description: 获取当天的文件夹格式 如日期为 2018-04-24  则返回 20180424
	 * @return String     
	 * @throws 
	 *
	 */
	public static String getYesTerdayDirName() {
		return simpleDateFormat.format(DateUtil.addDay(new Date(), -1));
	}
	/** 
	 *
	 * @Title:  getEveryDayDirName 
	 * @Description: 获取当天的文件夹格式 如参数为 2018-04-24 12:05:00  则返回 20180424
	 * @param dateTime 日期字符串或者日期时间字符串
	 * @return String     
	 * @throws 
	 *
	 */
	public static String getEveryDayDirName(String dateTime) {
		return simpleDateFormat.format(DateUtil.parse(dateTime, DateUtil.DATE));
	}

	private static String getUUIDFileName() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = df.format(new Date()) + "_" + new Random().nextInt(1000);
		return s + ".png";
	}

	public static String transBase64(String html) {
		Document doc = null;
		try {
			doc = Jsoup.parse(html);
		} catch (Exception e) {
			return html;
		}
		Elements eles = doc.getElementsByTag("img");
		Map<String, String> cache = new HashMap<String, String>();
		for (Element ele : eles) {
			try {
				String base64 = ele.attr("src");
				String prefix = imageBase64Prefix(base64);
				if (prefix != null) {
					ele.attr("src", "图片");
				}
			} catch (Exception e) {
				continue;
			}
		}
		return doc.body().html();
	}
	public static String transform(String basePath, String baseUrl, String html) {
		html = HtmlUtils.htmlUnescape(html);
		Document doc = null;
		try {
			doc = Jsoup.parse(html);
		} catch (Exception e) {
			return html;
		}
		Elements eles = doc.getElementsByTag("img");
		Map<String, String> cache = new HashMap<String, String>();
		for (Element ele : eles) {
			try {
				String base64 = ele.attr("src");
				String prefix = imageBase64Prefix(base64);
				if (prefix != null) {
					String key = DigestUtils.md5DigestAsHex(base64.getBytes("UTF-8"));
					if (cache.containsKey(key)) {
						ele.attr("src", cache.get(key));
					} else {
						base64 = base64.substring(prefix.length());
						byte[] bytes = decode(base64);
						String fileName = getUUIDFileName();
						String filepath = dir(basePath) + "/" + fileName;
//						zoom(bytes, new FileOutputStream(filepath), Integer.MAX_VALUE);
						//SMB协议上传文件
						//SmbUtil.smbPut(filepath, bytes);
						String url = baseUrl + getDirName() + "/" + fileName;
						ele.attr("src", url);
						cache.put(key, url);
					}
				}
			} catch (Exception e) {
				logger.error("*****ImageUtil*****--> transform failed!" + e);
				continue;
			}
		}
		return doc.body().html();
	}

	// public static void main(String[] args) throws Exception {
	// String base64 = encode("d:\\1231.jpg");
	// long s = System.currentTimeMillis();
	// byte[] bytes = decode(base64);
	// long s1 = System.currentTimeMillis();
	// zoom(bytes, new FileOutputStream("d:\\800.jpg"), 800);
	// long s2 = System.currentTimeMillis();
	//
	// System.out.println(s1 - s + ",解密时间");
	// System.out.println(s2 - s1 + ",压缩");
	// }

	public static String encode(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public static byte[] decode(String imgStr) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			return bytes;
		} catch (Exception e) {
			return null;
		}
	}
	public static void zoom(byte[] bytes, OutputStream os, int maxWidth) throws Exception {
		// 读取图片
		BufferedImage src = null;
		try {
			src = ImageIO.read(new ByteArrayInputStream(bytes));
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		int width = src.getWidth();
		// int height = src.getHeight();
		if (width > maxWidth) {
			double scale = new BigDecimal(maxWidth).divide(new BigDecimal(width), 2, RoundingMode.HALF_UP).floatValue();
			zoom(new ByteArrayInputStream(bytes), os, scale);
		} else {
				os.write(bytes);
				os.flush();
				os.close();
		}
	}
	/** 
	 *
	 * @Title:  formatePcString 
	 * @Description:	图文分离 
	 * @param str
	 * @return Map<String,String>     
	 * @throws 
	 *
	 */
	public static Map<String, String> formatePcString(String str) {
		Map<String, String> map = new HashMap<>();
		map.put("mes", "");
		map.put("url", "");
		Document doc = null;
		try {
			doc = Jsoup.parse(str);
		} catch (Exception e) {
			return map;
		}
		Elements eles = doc.getElementsByTag("img");
		String src = "";
		for (Element ele : eles) {
			src+=ele.attr("src")+"@";
			ele.remove();
		}
		map.put("url", src.endsWith("@")?src.substring(0, src.lastIndexOf("@")):"");
		map.put("mes", doc.body().html());
		return map;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedImage imgMap = watermark(250, 100, new Font("隶书", Font.ITALIC, 30), Color.LIGHT_GRAY, "碧桂园");
		File imgFile = new File("D://watermark.png");
		try {
			ImageIO.write(imgMap, "PNG", imgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// for (int i = 0; i < 19; i++)
		// zoom(new
		// FileInputStream("D:\\workspace11\\website\\src\\main\\webapp\\images\\"
		// + i + ".jpg"), new
		// FileOutputStream("D:\\workspace11\\website\\src\\main\\webapp\\images\\"
		// + i + "_225.jpg"), 225, 300);
	}
}
