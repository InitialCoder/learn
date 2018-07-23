<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>for xss te  st</title>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/libs/xss/he.js"></script>
<script type="text/javascript" src="${ctx}/libs/xss/htmlparser.js"></script>
<script type="text/javascript" src="${ctx}/libs/xss/epudge.xssfilter.js"></script>
<script type="text/javascript" src="${ctx}/libs/kindeditor/kindeditor-all.js"></script>
</head>
<body>

<div>测试富文本过滤XSS  因为没配置文件服务器，暂时不支持文件上传</div>
<form action="#" onsubmit="return false" >
	<div>
		<input name="name" type="text">
	</div>
	<div>
		<textarea id="editor" rows="5" cols="">
		
		</textarea>
	</div>

</form>
<script type="text/javascript">
	$(function(){
		editor = KindEditor.create('#editor', {
					/* uploadJson : '${ctx }/kindeditor/jsp/upload_json.jsp',
					fileManagerJson : '${ctx }/kindeditor/jsp/file_manager_json.jsp', */
					items : [ 'fullscreen'],
					afterBlur : function() {
						this.sync();
					},
					/* afterFocus : function() {
						$('.ke-edit').css("overflow-y","hidden");
					}, */
					//allowFileManager : false
				});
		
	});
	
</script>
</body>
</html>