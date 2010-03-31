<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>用户登陆</title>
		
	</head>
	<body>
		<center>
		<h3>请输入用户名和密码来登陆</h3>
		如果还没有注册，单击此处<a href="regist.jsp">注册</a>
		${userId}<s:property value="userId"/>
		<s:fielderror />
		<s:actionerror/>
		<s:form action="ResetPasswordSave.action" method="post" validate="true">
			<input type="hidden" name="userId" value ="<s:property value="userId"/>"/>
			<s:textfield name="newPassword" label="新密码"/><br />
			<s:password name="newPasswordConfirm" label="新密码确认"/><br />
			<s:submit value="登陆"/>
		</s:form>
		 
		</center>
	</body>
</html>