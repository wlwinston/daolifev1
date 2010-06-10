<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta name="叨" content="叨微博客" />
		<title>叨--<s:property value="#session['daoLifeUser'].userNickName"/>
		的首页
		</title>
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
		<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/memberInfoCenter.js"></script>
		<script type="text/javascript" src="js/correctPNG.js"></script>
		<link href="styles/body.css" rel="stylesheet" type="text/css" />
		<link href="styles/index.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolifemare.css" rel="stylesheet" type="text/css" />
		<link href="styles/myhome.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolife.css" rel="stylesheet" type="text/css" />
		<link href="styles/chanpin.css" rel="stylesheet" type="text/css" />
	</head>
		<body>
		<jsp:include page="menu.jsp?id=2" flush="true"></jsp:include>
		<div class="banner">
			<div class="banner1">
				<table width="1002" height="293" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="203" align="left" valign="top">
							<table width="947" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="644" height="293" align="left" valign="top">
										<table width="644" height="279" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td width="644" height="48" align="left" valign="bottom">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您还可以输入 <span id='fontlength'>140</span> 个字符
												</td>
											</tr>
											<tr>
												<td height="205" align="center" valign="top">
													<table width="550" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td height="205" align="left" valign="top">
																<form id="form1" name="form1" method="post" action=""  onsubmit="return false">
																	<table width="525" height="205" border="0"
																		cellpadding="0" cellspacing="0">
																		<tr>
																			<td width="525" height="172">
																				<textarea id='articlecontent' name="textarea3" cols="45" rows="5"
																					class="tabledao" id="textarea3"
																					style="background-image: url(images/myhome_07.gif); border: 0; width: 521px; height: 138px; overflow: hidden; resize: none; "></textarea>
																			</td>
																		</tr>
																		<tr>
																			<td align="right">
																				<label>
																					<input type="image" src="images/myhome_11.gif" onclick="doSubmit()"/>
																				</label>
																			</td>
																		</tr>
																	</table>
																</form>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
									<td width="303" align="center" valign="middle">
										<img src="images/T.png" width="223" height="247" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

			</div>

		</div>
		<div class="hengxian">
			<div class="hengxian1">
				<img src="images/top_16.gif" />
			</div>
		</div>
		<div class="zhuti">
			<table width="650" height="1022" border="0" cellpadding="0"
				cellspacing="0" class="zhuti1">
				<tr>
					<td height="1021" align="left" valign="top">
						<div class="zuo">
							<table width="514" height="51" border="0" align="center"
								cellpadding="0" cellspacing="0" class="xia1">
								<tr>
									<td width="112" height="50" align="center" valign="middle">
										<a href="javascript:doStatus(1)" class="status"><img src="images/myhome_21.gif" alt="" width="112" height="24" /></a>
									</td>
									<td width="112" align="center" valign="middle">
										<a href="javascript:doStatus(2)" class="status"><img src="images/myhome_23.gif" width="48" height="23" /></a>
									</td>
									<td width="112" align="center" valign="middle">
										<a href="javascript:doStatus(3)" class="status"><img src="images/myhome_25.gif" width="65" height="23" /></a>
									</td>
								</tr>
							</table>
							<div id = 'articleandreply'>
								<center></center>
							</div>
						</div>
						<div id="infocenter" class="reight">
							<div id="personal">
							</div>
							<s:action name="hotWidget" executeResult="true" />  
							<div class="Box">
								我的叨T
							</div>
							<table width="84%" height="177" border="0" align="center"
								cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<span class="touxiang1"><img src="images/Tx.gif"
												width="223" height="219" />
										</span>
									</td>
								</tr>
							</table>
							<div class="moer">
								<table width="77%" height="19" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="27%" align="right">
											<img src="images/moer.gif" width="14" height="13" />
										</td>
										<td width="73%" align="right">
											<a href="product.action">查看全部</a>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<jsp:include page="foot.jsp" flush="true"></jsp:include> 
	</body>
</html>