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
		<link href="styles/body.css" rel="stylesheet" type="text/css" />
		<link href="styles/index.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolifemare.css" rel="stylesheet" type="text/css" />
		<link href="styles/myhome.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolife.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="top1">
			<div class="top2">
				<div class="logo">
					<a href="index.html"><img src="images/top_07.gif" width="62"
							height="63" />
					</a>
				</div>
				<div class="tiao">


					<div class="tiao1">
						<img src="images/top_04.gif" />
					</div>

					<div class="tiao2">
						<img src="images/top_08.gif" />
					</div>
					<div class="tiao3">
						<table width="98%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<img src="images/top_09.gif" width="118" height="8" />
								</td>
							</tr>
							<tr>
								<td height="53" align="center" background="images/top_11.gif">
									<table width="68" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="79" align="center">
												有5条新评论
											</td>
										</tr>
										<tr>
											<td align="center">
												有8条叨@您
											</td>
										</tr>
										<tr>
											<td align="center">
												有8封站内信
											</td>
										</tr>
										<tr>
											<td align="center">
												有3位新关注
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="left" valign="top">
									<img src="images/top_13.gif" width="118" height="6" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!--表单-->
				<div class="select">
					<form action="" method="post" name="frm">

						<table width="227" height="32" border="0" cellpadding="0"
							cellspacing="0" background="images/farm.gif">
							<tr>
								<td height="32" align="left" valign="middle">
									<table width="98%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="86%" height="25">
												<label>
													&nbsp;&nbsp;
													<input name="textfield" type="text" id="textfield"
														style="border: 0px;" value="产品: 电影 妞儿 牛逼" />
												</label>
											</td>
											<td width="14%">
												<input type="image" src="images/selsect.gif" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</form>
				</div>


				<!--导航-->
				<div class="daohang">
					<ul>
						<li>
							<a href="index.jsp">首页 </a>
						</li>
						<li style="background-image: url(images/daohang%20%20hover.gif)">
							<a href="memberInfoCenter.jsp">我的首页</a>
						</li>
						<li>
							<a href="daolife.html">Dao Life</a>
						</li>
						<li>
							<a href="look.html">随便看看</a>
						</li>
						<li>
							<a href="daohot1.html">最叨</a>
						</li>
					</ul>
				</div>
			</div>

		</div>
		<div class="tiao">
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
										<th height="21" align="right">
											<span class="allow">*</span>昵称：
										</th>
										<td>
											<label>
												<input type="text" name="user.userNickName" id="nick" />
												<span id="nick_info" class="info"></span>
											</label>
										</td>
									</tr>
									<!--
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
									-->
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
	</body>
</html>