<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.innovation.daolife.model.DlUsers" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	DlUsers user = (DlUsers)request.getAttribute("user");
	
 %>
<html>
	<head>
		<title>用户修改</title>
		<script src="js/prototype.js" type="text/javascript"></script>
	</head>
	<body>
		<center>
		<h3>请修改客户信息</h3>
		<s:form action="/Update.action" method="post" validate="true">
			<s:textfield  name="updateUser.userName" label="用户名" /><br/>
			<s:textfield  name="updateUser.userNickName" label="昵称" /><br />
			<s:textfield  name="updateUser.mailadres" label="电邮"/><br />
			<s:textfield  name="updateUser.userUrl" label="个性地址"/><br />
			<s:textfield  name="updateUser.birthday" label="生日"/><br />
			<s:textfield  name="updateUser.userGender" label="性别"/><br />
			<s:textfield  name="updateUser.userInfo" label="个人介绍"/><br />
			<s:textfield  name="updateUser.musicaddr" label="音乐地址"/><br />
			<s:textfield  name="updateUser.userAddress" label="地址"/><br />
			<s:textfield  name="updateUser.userPostcode" label="邮编"/><br />
			
			<s:hidden name="updateUser.lastLogin"></s:hidden>
			<s:hidden name="updateUser.atWeekNum"></s:hidden>
			<s:hidden name="updateUser.atMonthNum"></s:hidden>
			<s:hidden name="updateUser.atSumNum"></s:hidden>
			<s:hidden name="updateUser.fansNum"></s:hidden>
			<s:hidden name="updateUser.followNum"></s:hidden>
			<s:hidden name="updateUser.userHead"></s:hidden>
			<s:hidden name="updateUser.recommendInd"></s:hidden>
			<s:hidden name="updateUser.isclose"></s:hidden>
			<s:hidden name="updateUser.photoNum"></s:hidden>
			<s:hidden name="updateUser.userlock"></s:hidden>
			<s:hidden name="updateUser.authEmail"></s:hidden>
			<s:hidden name="updateUser.signupdate"></s:hidden>
			
			<s:submit value="提交修改"/>
		</s:form>
		</center>
	</body>
</html>