<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
   <title>注册/登陆成功</title>
</head>
<body>
<center>
注册/登陆成功,欢迎 ${sessionScope.user.userName}<br>

<table width="500" height="200">
<tr><td>
<a href="regist.jsp">重新注册</a>&nbsp;&nbsp;<a href="login.jsp">登陆</a>
<td></tr>
</table>
</center>
</body>
</html>