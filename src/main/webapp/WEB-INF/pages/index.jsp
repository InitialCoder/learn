<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>for xss te  st</title>
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


<div>

	<br>
	<br>
	<br>
	<form action="${ctx}/logout" >
		<div>
			<button type="submit"> logout</button>	
		</div>
</form>

<div>welcome:<shiro:principal>
	
</shiro:principal>

</div>
<shiro:hasRole name="ADMIN">
	<a href="${ctx}/home/admin.action">admin.jsp</a>
</shiro:hasRole>
<shiro:hasPermission name="home:user">
	<a href="${ctx}/home/user.action">user.jsp</a>
</shiro:hasPermission>
<shiro:hasPermission name="home:findAll">
	<a href="${ctx }/home/list.action">list  permiss</a>
</shiro:hasPermission>
	<a href="${ctx }/home/list.action">list</a>
</div>
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