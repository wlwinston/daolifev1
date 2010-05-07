<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>叨--最热叨句</title>
		<link href="styles/body.css" rel="stylesheet" type="text/css" />
		<link href="styles/index.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolife.css" rel="stylesheet" type="text/css" />
		<link href="styles/chanpinmoer.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolifemare.css" rel="stylesheet" type="text/css" />
		<link href="styles/chanpin.css" rel="stylesheet" type="text/css" />
		<link href="styles/td.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/daoHot.js"></script>
	</head>

	<body>
		<!--top头部区域-->
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
							<a href="index.html">首页 </a>
						</li>
						<li>
							<a href="myhome.html">我的首页</a>
						</li>
						<li>
							<a href="daolife.html">Dao Life</a>
						</li>
						<li>
							<a href="look.html">随便看看</a>
						</li>
						<li style="background-image: url(images/daohang%20%20hover.gif)">
							<a href="daohot1.html">最叨</a>
						</li>
					</ul>
				</div>
			</div>

		</div>
		<div class="zhuti">
			<table width="650" height="1108" border="0" cellpadding="0"
				cellspacing="0" class="zhuti3">
				<tr>
					<td height="1107" valign="top">
						<center>
						<table width="92%" height="43" border="0" cellpadding="0"
							cellspacing="0" class="xia1 guanzhu ">
							<tr>
								<td width="12%" height="40" align="center">
									<a href="daohot1.html"><img src="images/daohot1_03.gif"
											alt="" width="113" height="22" />
									</a><a href="look.html"></a>
								</td>
								<td width="13%" align="center">
									<a href="daohot.html"><img src="images/daohot1_05.gif"
											width="79" height="22" />
									</a>
								</td>
								<td width="75%">
									&nbsp;
								</td>
							</tr>
							<tr></tr>
						</table>
						<div class="daohot1" id='box'>
						</div>
						</center>
					</td>
				</tr>
			</table>
		</div>

		<!--foot脚部信息-->
		<div class="foot">
			<div class="foot1">
				foot
			</div>
		</div>

	</body>
</html>
