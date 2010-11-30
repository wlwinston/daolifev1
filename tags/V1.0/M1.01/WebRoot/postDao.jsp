<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>Dao</title>
		<script type="text/javascript">
		function refresh(){
			document.getElementById("authImg").src="authImg?now="+new Date();
		}
		</script>
	</head>
	<body>
		<center>
		<h3>Dao</h3>
		<s:fielderror />
		<s:actionerror/>
		<s:form action="PostDao.action" method="post" validate="true">
			<s:textfield name="contextBody" label="我想叨"/><br />
			<s:submit value="发叨"/>
		</s:form>
		 
		</center>
	</body>
</html>