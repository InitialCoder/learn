package com.ascend.demo.util;


import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;


public class JsoupUtil {
	 
	private static final Whitelist whitelist = Whitelist.basic();
	/** 配置过滤化参数,不对代码进行格式化 */
	private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
	static {
		// 富文本编辑时一些样式是使用style来进行实现的
		// 比如红色字体 style="color:red;"
		// 所以需要给所有标签添加style属性
		whitelist.addTags("img","h1", "h2", "h3", "h4", "h5", "h6","s","section","hr","div","table","tbody","tfoot","thead","tr","th","td")
		
		.addAttributes("img", "align", "alt", "height", "src", "width")
		.addAttributes("table", "summary", "width","cellspacing","cellpadding")
		.addAttributes("td", "abbr", "axis", "colspan", "rowspan", "scope","width","height")
		.addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope","width","height")
		.addAttributes("ol", "start", "type")
		.addAttributes("q", "cite")
		.addAttributes("a", "target")
		
		.addAttributes(":all", "style")
		.addAttributes(":all", "data-ke-src")
		.addAttributes(":all", "data-id")
		.addAttributes(":all", "id")
		.addAttributes(":all", "class")
		.addAttributes(":all", "title")
		.addAttributes(":all", "align")
		
		.removeEnforcedAttribute("a", "rel")
		.removeProtocols("a", "href", "ftp", "http", "https", "mailto")
		.removeProtocols("blockquote", "cite", "http", "https")
        .removeProtocols("cite", "cite", "http", "https");
	
	}
 
	public static String clean(String content) {
	    if(StringUtils.isNotBlank(content)){
	        content = content.trim();
        }
        return Jsoup.clean(content, "", whitelist, outputSettings);
	}
	//测试
	/*public static void main(String[] args) throws IOException {
		String test ="F1000芜湖晋智房地产开发有限公司<img alt=\"\" src=\"/kindeditor/read?path=image/20161102/20161102084135_677.png\" /><img alt=\"\" src=\"/kindeditor/read?path=image/20161102/20161102084146_395.png\" />在从bpm传递外部费用报销单时，没有银行账号，但走bpm流程时已经把账号填写完整了，这样传递到nc的单子就不能使用银企直连支付了，请问该如何处理。"
				+ "<img src=\"null\" onerror=\"alert(1)\" style=\"width:10px;\">aaa</img>";
		
		System.out.println(clean(test));

	}*/
}
