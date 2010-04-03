<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>用户注册</title>
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
    	<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/form.js"></script>
		<script language="JavaScript">
		$(function($){
			Form.add('username',DaolifeAjax.checkUserName);
		});
		</script>
	</head>
	<body>
		<center>
		<h3>请输入注册信息</h3>
		<s:form action="/Regist.action" method="post" validate="true">
			<s:textfield id="username" name="user.userName" label="用户名" /><br />
			<s:textfield id="usernickname" name="user.userNickName" label="昵称" /><br />
			<s:password id="password" name="user.password" label="密码"/><br />
			<s:textfield id="email" name="user.mailadres" label="电邮"/><br />
			<s:textfield id="email" name="user.userUrl" label="个性地址"/><br />
			<s:textfield id="email" name="user.birthday" label="生日"/><br />
			<s:textfield id="email" name="user.userGender" label="性别"/><br />
			<s:textfield id="email" name="user.userInfo" label="个人介绍"/><br />
			<s:textfield id="email" name="user.musicaddr" label="音乐地址"/><br />
			<s:textfield id="email" name="user.userAddress" label="地址"/><br />
			<s:textfield id="email" name="user.userPostcode" label="邮编"/><br />
			<s:textfield id="email" name="user.musicaddr" label="音乐地址"/><br />
			<s:textfield id="email" name="user.musicaddr" label="音乐地址"/><br />
			<s:textfield id="email" name="user.musicaddr" label="音乐地址"/><br />
			
			<s:submit value="注册"/>
		</s:form>
		</center>
	</body>
</html>