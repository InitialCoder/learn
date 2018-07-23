<%@page import="com.epudge.help.ext.util.SmbUtil"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.context.i18n.LocaleContextHolder" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.json.simple.*" %>
<%

/**
 * KindEditor JSP
 * 
 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。
 * 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
 * 
 */

// 应用文件分离
 /* smb共享目录文件上传 */
Locale locale = LocaleContextHolder.getLocale();
WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
String saveUrl  = request.getContextPath()+"/kindeditor/read";
String savePath = SmbUtil.basePath+"/";
String relativePath = "";

/* //文件保存目录路径
String savePath = pageContext.getServletContext().getRealPath("/") +"kindeditor/attached/";

//文件保存目录URL
 */
//定义允许上传的文件扩展名
HashMap<String, String> extMap = new HashMap<String, String>();
extMap.put("image", "gif,jpg,jpeg,png,bmp");
extMap.put("flash", "swf,flv");
extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf,ppt,pptx");

//最大文件大小<20M>
long maxSize = 20971520;

response.setContentType("text/html; charset=UTF-8");
if(!ServletFileUpload.isMultipartContent(request)){
	out.println(getError(context.getMessage("pleaseSelectFile", null, locale)));
	return;
}
//先检查目录文件夹是否存在
File saveDirFile1 = new File(savePath);
if (!saveDirFile1.exists()) {
	saveDirFile1.mkdirs();
}
//检查目录
File uploadDir = new File(savePath);
if(!uploadDir.isDirectory()){
	out.println(getError(savePath));
	return;
}
//检查目录写权限
if(!uploadDir.canWrite()){
	out.println(getError(context.getMessage("noWritePermission", null, locale)));
	return;
}

String dirName = request.getParameter("dir");
if (dirName == null) {
	dirName = "image";
}
if(!extMap.containsKey(dirName)){
	out.println(getError(context.getMessage("errordirectory", null, locale)));
	return;
}
//创建文件夹

// 应用图文分离使用,非自定义上传时注释
relativePath += dirName + "/";
savePath += relativePath;

//自定义上传时注释以下两行
/* savePath += dirName + "/";
saveUrl += dirName + "/"; */
File saveDirFile = new File(savePath);
if (!saveDirFile.exists()) {
	saveDirFile.mkdirs();
}
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
String ymd = sdf.format(new Date());

//非自定义上传时注释该行
relativePath += ymd + "/";

savePath += ymd + "/";
//自定义上传时注释该行
/* saveUrl += ymd + "/"; */
File dirFile = new File(savePath);
if (!dirFile.exists()) {
	dirFile.mkdirs();
}

FileItemFactory factory = new DiskFileItemFactory();
ServletFileUpload upload = new ServletFileUpload(factory);
upload.setHeaderEncoding("UTF-8");
List items = upload.parseRequest(request);
Iterator itr = items.iterator();
while (itr.hasNext()) {
	FileItem item = (FileItem) itr.next();
	String fileName = item.getName();
	long fileSize = item.getSize();
	if (!item.isFormField()) {
		//检查文件大小
		if(item.getSize() > maxSize){
			out.println(getError(context.getMessage("fileExceedsSizeLimit", null, locale)));
			return;
		}
		//检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	 	if(extMap.get("image").contains(fileExt)){
			fileExt = com.epudge.help.ext.util.ImageTypeUtil.getImageType(item.getInputStream());//根据文件头标识获取文件类型
			if(fileExt==null || !Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
				out.println(getError(context.getMessage("notsupport", new Object[]{extMap.get(dirName)}, locale)));
				return;
			}
		}else if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
			out.println(getError(context.getMessage("notsupport", new Object[]{extMap.get(dirName)}, locale)));
			return;
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		try{
			/* File uploadedFile = new File(savePath, newFileName);
			//out.println(getError(saveUrl+"?path="+relativePath+newFileName));
			item.write(uploadedFile); */
			/* smb 文件共享目录上传 */
			SmbUtil.smbPut(savePath+"/"+newFileName, item.getInputStream());
		}catch(Exception e){
			out.println(getError(context.getMessage("uploadError", null, locale)));
			return;
		}

		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		//自定义上传时注释该行
		/* obj.put("url", saveUrl + newFileName); */
		//自定义路径对应url
		obj.put("url", saveUrl+"?path="+relativePath+newFileName);
		//modify by lqc 2016-10-29 添加原有文件名传送,用于默认改值 
		obj.put("fileName",fileName);
		out.println(obj.toJSONString());
	}
}
%>
<%!
private String getError(String message) {
	JSONObject obj = new JSONObject();
	obj.put("error", 1);
	obj.put("message", message);
	return obj.toJSONString();
}
%>