<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.innovation.daolife.model.DlUsers" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>添加</title>
		<script src="js/prototype.js" type="text/javascript"></script>
	</head>
	<body>
		<center>
		<h3>请修改客户密码信息</h3>
		<s:form action="/Follow.action" method="post" validate="true">
			<s:hidden name="followId" value="1"></s:hidden>
			<s:submit value="关注"/>
		</s:form>
		</center>
	</body>
</html>