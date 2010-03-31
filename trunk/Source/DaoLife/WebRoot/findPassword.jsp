<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>密码找回</title>
	</head>
	<body>
		<center>
		
		<s:fielderror />
		<s:actionerror/>
		<s:form action="FindPassword.action" method="post" validate="true">
			<s:textfield name="userName" label="用户名"/><br />
			<s:submit value="找回"/>
		</s:form>
		 
		</center>
	</body>
</html>