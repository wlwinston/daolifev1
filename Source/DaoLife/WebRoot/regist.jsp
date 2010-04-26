<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
		<title>用户注册</title>
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
    	<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/regist.js"></script>
		<link type="text/css" rel="stylesheet" href="styles/regist.css" />
	</head>
	<body>
	<!-- 
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
	 -->
		<div class="top">
			<div class="logo">
				<img src="images/logo.gif" width="63" height="70" />
			</div>
		</div>
		<div class="tiao">
			<img src="images/tiao.gif" />
		</div>
		<div class="neirong">

			<div class="regist">
				<table width="539" border="0" align="center" cellpadding="4"
					cellspacing="4">
					<tr>
						<td width="523" height="197" align="left" valign="top">
							<s:form action="/Regist.action" method="post" enctype="multipart/form-data" name="regist" id="regist">
								<table width="623" border="0" cellspacing="3" cellpadding="0">
									<tr>
										<th width="117" height="21" align="right">
											<span class="allow">*</span>登陆账号：
										</th>
										<td>
											<label>
												<input id="username" type="text" name="user.userName" />
												<span id="username_info" class='info'></span>
											</label>
										</td>
									</tr>
									<tr>
										<th height="21" align="right">
											<span class="allow">*</span>登陆密码：
										</th>
										<td>
											<label>
												<input id="password" type="password" name="user.password" />
												<span id="password_info" class="info"></span>
											</label>
										</td>
									</tr>
									<tr>
										<th height="21" align="right">
											<span class="allow">*</span>密码确认：
										</th>
										<td>
											<label>
												<input id='repassword' type="password" name="repassword" />
												<span id="repassword_info" class="info"></span>
											</label>
										</td>
									</tr>
									<tr>
										<th height="21" align="right">
											<span class="allow">*</span>邮箱：
										</th>
										<td>
											<label>
												<input type="text" name="user.mailadres" id="mail" />
												<span id="mail_info" class="info"></span>
											</label>
										</td>
									</tr>
									<tr>
										<th>&nbsp;</th>
										<td><hr/></td>
									</tr>
									<tr>
										<th>&nbsp;</th>
										<td><span class="info">以下为选填项</span></td>
									</tr>
									<tr>
										<th height="21" align="right"><span class="sel">昵称：</span></th>
										<td>
											<label>
												<input type="text" name="user.userNickName" id="mail2" />
											</label>
										</td>
									</tr>
									<tr>
										<th>&nbsp;</th>
										<td><hr/></td>
									</tr>
									<tr>
										<th height="21" align="right">
											<span class="allow">*</span>验证码：
										</th>
										<td align="left" valign="top">
											<label>
												<input name="textfield2" type="text" id="textfield2"
													size="5" style="width:60px;" />
												<img src="images/zc.gif" width="100" height="24" />&nbsp;<a href="#">换一张</a>
											</label>
										</td>
									</tr>
									<tr>
										<td colpan=3>&nbsp;</td>
									</tr>
									<tr>
										<td height="21" align="center">
										</td>
										<td>
											<input type="image" src="images/zuce1.gif" />
										</td>
									</tr>
								</table>
							</s:form>
						</td>
					</tr>
				</table>
				<center>
				</center>
			</div>
			<div></div>
		</div>
		</div>
		<div class="foot">DaoLife</div>
	</body>
</html>