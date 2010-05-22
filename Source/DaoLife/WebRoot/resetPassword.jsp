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
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/resetpassword.js"></script>
		<link type="text/css" rel="stylesheet" href="styles/regist.css" />
		<link href="styles/body.css" rel="stylesheet" type="text/css" />
		<link href="styles/index.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolifemare.css" rel="stylesheet" type="text/css" />
		<link href="styles/myhome.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolife.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<jsp:include page="menu.jsp" flush="true"></jsp:include> 
		<div class="zhuti">
			<div class="regist">
				<table width="539" border="0" align="center" cellpadding="4"
					cellspacing="4">
					<tr>
						<td width="523" height="197" align="left" valign="top">
							<s:form action="/ResetPasswordSave.action" method="post" enctype="multipart/form-data" name="regist" id="regist">
								<table width="623" border="0" cellspacing="3" cellpadding="0">
									<tr>
										<th width="117" height="21" align="right">
											<span class="allow">*</span>登陆密码：
										</th>
										<td>
											<label>
												<input id="password" type="password" name="newPassword" />
												<span id="password_info" class="info"></span>
												<input id='userId' type="hidden" name="userId" value ="<s:property value="userId"/>" />
											</label>
										</td>
									</tr>
									<tr>
										<th height="21" align="right">
											<span class="allow">*</span>密码确认：
										</th>
										<td>
											<label>
												<input id='repassword' type="password" name="newPasswordConfirm" />
												<span id="repassword_info" class="info"></span>
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
										<td>
											<label>
												<input type="text" size="5" style="width:70px;" height="26" id="yzm" />
												<img src="servlet/dao.auth" id="picyzm" style="border:0;width:100px;height:17px;margin:0;float:left botton;" />&nbsp;<a href="javascript:reflashpic()">换一张</a>
												<span id="yzm_info" class="info"></span>
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
											<input type="image" src="images/resetpassword.gif" />
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