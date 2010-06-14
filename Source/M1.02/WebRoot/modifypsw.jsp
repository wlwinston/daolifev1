<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.innovation.daolife.model.DlUsers" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	DlUsers user = (DlUsers)request.getAttribute("user");
	
 %>
<html>
	<head>
		<title>用户密码修改</title>
		<script src="js/prototype.js" type="text/javascript"></script>
	</head>
	<body>
		<center>
		<h3>请修改客户密码信息</h3>
		<s:form action="/UpdatePsw.action" method="post" validate="true">
			<s:textfield  name="oldpassword" label="原有密码" /><br/>
			<s:textfield  name="newpassword" label="新密码" /><br />
			<s:textfield  name="confirmpassword" label="确认密码"/><br />
			
			<s:submit value="提交修改"/>
		</s:form>
		</center>
	</body>
</html>