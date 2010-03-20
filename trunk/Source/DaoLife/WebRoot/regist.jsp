<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>用户注册</title>
		<script src="js/prototype.js" type="text/javascript"></script>
	</head>
	<body>
		<center>
		<h3>请输入注册信息</h3>
		<s:form action="/Regist.action" method="post" validate="true">
			<s:textfield id="username" name="user.username" label="用户名" onblur="validateName()"/><br />
			<s:password id="password" name="user.password" label="密码"/><br />
			<s:textfield id="email" name="user.email" label="电邮"/><br />
			<s:submit value="注册"/>
		</s:form>
		</center>
	</body>
</html>