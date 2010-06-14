<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
		<title>叨--用户设置</title>
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
    	<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/setup.js"></script>
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
			<!--<div class="regist">  -->
				<table width="539" border="0" align="center" cellpadding="4" cellspacing="4" class="zhuti3" style="height:463px;font-size:15px;">
					<tr>
						<td width="523" height="197" align="left" valign="top">
							<s:form action="Update.action" method="post" enctype="multipart/form-data" name="regist" id="regist" style="float:left;">
								<table width="623" border="0" cellspacing="3" cellpadding="0">
									<tr>
										<th height="21" align="right">
											<span class="allow">*</span>昵称：
										</th>
										<td>
											<label>
												<input type="text" name="user.userNickName" id="nick" value="" />
												<span id="nick_info" class="info"></span>
											</label>
										</td>
									</tr>
									<tr>
										<th>&nbsp;</th>
										<td><hr/></td>
									</tr>
									<tr>
										<th height="21" align="right">
											省份：
										</th>
										<td>
											<label>
											 <select id="province">
											 	<option value="1">北京</option>
											 	<option value="2">上海</option>
											 </select>
										</td>
									</tr>
									<tr>
										<th height="21" align="right">
											地区：
										</th>
										<td>
											<label>
											 <select id="region">
											 	<option>海淀区</option>
											 	<option>朝阳区</option>
											 </select>
											</label>
										</td>
									</tr>
									<tr>
										<th height="21" align="right">
											性别：
										</th>
										<td>
											<label>
												<input type="radio" value="1" name="updateUser.userGender" />男&nbsp;<input type="radio" value="0" name="updateUser.userGender" />女
											</label>
										</td>
									</tr>
									<tr>
										<th height="21" align="right">
											生日：
										</th>
										<td>
											<label>
												<input type="text" name="updateUser.birthday" value="" />
												<span id="nick_info" class="info"></span>
											</label>
										</td>
									</tr>
									<tr>
										<th height="21" align="right">
											邮编：
										</th>
										<td>
											<label>
												<input type="text" name="updateUser.userPostcode" value="" />
												<span id="nick_info" class="info"></span>
											</label>
										</td>
									</tr>
									<tr>
										<th height="21" align="right">
											个性域名：
										</th>
										<td>
											<label>
												<input type="text" name="updateUser.userUrl" value="" />
												<span id="nick_info" class="info"></span>
											</label>
										</td>
									</tr>
									<tr>
										<th height="21" align="right">
											个人介绍：
										</th>
										<td>
											<label>
												<textarea name="updateUser.userInfo" ></textarea>
												<span id="nick_info" class="info"></span>
											</label>
										</td>
									</tr>
									<tr>
										<th height="21" align="right">
											<span class="allow">*</span>验证码：
										</th>
										<td>
											<label>
												<input type="text" size="5" style="width:70px;" height="26" id="yzm" />
												<img src="servlet/dao.auth" id="picyzm" style="border:0;width:100px;margin:0;float:left botton;" />&nbsp;<a href="javascript:reflashpic()">换一张</a>
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
											<input type="image" src="images/setup.gif" />
										</td>
									</tr>
								</table>
							</s:form>
						</td>
					</tr>
				</table>
			<!--</div>  -->
		</div>
		<jsp:include page="foot.jsp" flush="true"></jsp:include>
	</body>
</html>