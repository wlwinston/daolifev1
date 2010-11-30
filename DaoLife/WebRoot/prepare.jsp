<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>用户修改</title>
		<script src="js/prototype.js" type="text/javascript"></script>
	</head>
	<body>
		<center>
		<h3>请修改客户信息</h3>
		<s:form action="/UpdatePrepare.action" method="post" validate="true">
			
			<s:submit value="修改客户信息"/>
		</s:form>
		</center>
	</body>
</html>