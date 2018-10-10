<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>

<div>登录</div>
<form action="${ctx }/login" method="POST" >
	<div>
		<input name="userAccount" type="text">
	</div>
	<div>
		<input name="password" type="text">
	</div>

	<button type="submit">提交</button>
</form>
</body>
</html>