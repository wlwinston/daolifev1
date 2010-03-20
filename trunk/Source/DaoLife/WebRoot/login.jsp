<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>用户登陆</title>
		<script type="text/javascript">
		function refresh(){
			document.getElementById("authImg").src="authImg?now="+new Date();
		}
		</script>
	</head>
	<body>
		<center>
		<h3>请输入用户名和密码来登陆</h3>
		如果还没有注册，单击此处<a href="regist.jsp">注册</a>
		<s:fielderror />
		<s:actionerror/>
		<s:form action="Login.action" method="post" validate="true">
			<s:textfield name="username" label="用户名"/><br />
			<s:password name="password" label="密码"/><br />
			<s:submit value="登陆"/>
		</s:form>
		 
		</center>
	</body>
</html>