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
		<title>${sessionScope.user.userName}的首页</title>
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
		<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/memberInfoCenter.js"></script>
		<script type="text/javascript" src="js/correctPNG.js"></script>
		<link href="styles/body.css" rel="stylesheet" type="text/css" />
		<link href="styles/index.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolifemare.css" rel="stylesheet" type="text/css" />
		<link href="styles/myhome.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolife.css" rel="stylesheet" type="text/css" />
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
		<!--banner区域-->
		<div class="banner">
			<div class="banner1">

				<table width="1002" height="293" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td height="203" align="left" valign="top">
							<table width="947" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="644" height="293" align="left" valign="top">
										<table width="644" height="279" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td width="644" height="48" align="left" valign="bottom">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您还可以输入25个字符
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
																					style="background-image: url(images/myhome_07.gif); border: 0; width: 521px; height: 138px; overflow: hidden"></textarea>
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
		<!--中间小条-->
		<div class="hengxian">
			<div class="hengxian1">
				<img src="images/top_16.gif" />
			</div>
		</div>

		<!--内容区域-->
		<div class="zhuti">
			<table width="650" height="1022" border="0" cellpadding="0"
				cellspacing="0" class="zhuti1">
				<tr>
					<td height="1021" align="left" valign="top">
						<!--左边区域-->
						<div class="zuo">
							<table width="514" height="51" border="0" align="center"
								cellpadding="0" cellspacing="0" class="xia1">
								<tr>
									<td width="118" height="50">
										<a href="myhome.html"><img src="images/myhome_21.gif"
												alt="" width="112" height="24" />
										</a>
									</td>
									<td width="91" align="center" valign="middle">
										<a href="wozaidao.html"><img src="images/myhome_23.gif"
												width="48" height="23" />
										</a><a href="touxiang.html"></a>
									</td>
									<td width="101" align="center" valign="middle">
										<a href="kankan.html"><img src="images/myhome_25.gif"
												width="65" height="23" />
										</a><a href="daolife.html"></a>
									</td>
									<td width="168" align="center" valign="middle">
										&nbsp;
									</td>
								</tr>
							</table>
							<div id = 'articleandreply'>
								<center></center>
							</div>
						</div>
						<!--右边区域-->
						<div class="reight">
							<table width="318" height="58" border="0" align="center"
								cellpadding="0" cellspacing="0" class="xia1">
								<tr>
									<td width="120" class="Box1">
										the5gg.com
									</td>
									<td width="127" align="left" valign="middle">
										&nbsp;
									</td>
									<td align="left" valign="middle">
										<a href="#">退出登陆</a>
									</td>
								</tr>
							</table>
							<table width="82%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="yonghu">
								<tr>
									<td width="32%" align="center">
										<a href="touxiang.html"><img src="images/1_10.gif"
												width="113" height="112" />
										</a>
									</td>
									<td width="68%" align="center" valign="top">
										<table width="193" height="52" border="0" cellpadding="0"
											cellspacing="0" class="xia1">
											<tr>
												<td width="76" height="33" align="center"
													class="STYLE4 STYLE7 STYLE5 STYLE6">
													<a href="woguanzhude.html">6</a>
												</td>
												<td width="76" align="center"
													class="STYLE4 STYLE7 STYLE5 STYLE6">
													<a href="guanzhuwode.html">4</a>
												</td>
												<td width="78" align="center"
													class="STYLE4 STYLE7 STYLE5 STYLE6">
													<a href="wozaidao.html">5</a>
												</td>
											</tr>
											<tr>
												<td align="center">
													<a href="woguanzhude.html">我关注</a>
												</td>
												<td align="center">
													<a href="guanzhuwode.html">关注我</a>
												</td>
												<td align="center">
													<a href="wozaidao.html">叨</a>
												</td>
											</tr>
										</table>
										<table width="200" height="66" border="0" align="center"
											cellpadding="0" cellspacing="4">
											<tr>
												<td width="51" align="right">
													简介：
												</td>
												<td width="143" align="left">
													男 北京市 朝阳起 设计师
												</td>
											</tr>
											<tr>
												<td align="right">
													博客：
												</td>
												<td align="left">
													the5th.blogbus.com
												</td>
											</tr>
											<tr>
												<td align="right">
													关于他：
												</td>
												<td align="left">
													吃个苹果吧青年
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<div class="shezhi">
								<table width="138" border="0" cellspacing="3" cellpadding="3">
									<tr>
										<td width="54">
											<a href="shezhi.html">我的设置</a>
										</td>
										<td width="63">
											<a href="sixin.html">我的私信</a>
										</td>
									</tr>
									<tr>
										<td>
											<a href="zhuanfa.html">我的转发</a>
										</td>
										<td>
											<a href="pinglun.html">我的评论</a>
										</td>
									</tr>
								</table>
							</div>
							<div class="Box">
								最热叨友
							</div>
							<div class="touxiang1">
								<a href="touxiang.html"><img src="images/touxiang.gif"
										width="76" height="77" />
								</a>
								<img src="images/touxiang.gif" width="76" height="77" />
								<img src="images/touxiang.gif" width="76" height="77" />
								<img src="images/touxiang.gif" width="76" height="77" />
								<img src="images/touxiang.gif" width="76" height="77" />
								<img src="images/touxiang.gif" width="76" height="77" />
								<img src="images/touxiang.gif" width="76" height="77" />
								<img src="images/touxiang.gif" width="76" height="77" />
								<img src="images/touxiang.gif" width="76" height="77" />
							</div>
							<div class="moer">
								<table width="77%" height="19" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="27%" align="right">
											<img src="images/moer.gif" width="14" height="13" />
										</td>
										<td width="73%" align="right">
											<a href="daohot.html">查看全部</a>
										</td>
									</tr>
								</table>
							</div>
							<div class="Box">
								最热叨句
							</div>
							<div class="daoju1">
								<a href="touxiang.html">@高建</a>
							</div>
							<div class="daoju2">
								我在真理部上班
							</div>
							<div class="daoju3">
								<a href="daohot1.html">64514人支持</a>
							</div>
							<div class="daoju1">
								<a href="touxiang.html">@高建</a>
							</div>
							<div class="daoju2">
								我在真理部上班
							</div>
							<div class="daoju3">
								<a href="daohot1.html">64514人支持</a>
							</div>
							<div class="daoju1">
								<a href="touxiang.html">@高建</a>
							</div>
							<div class="daoju2">
								我在真理部上班
							</div>
							<div class="daoju3">
								<a href="daohot1.html">64514人支持</a>
							</div>
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
											<a href="daolife.html">查看全部</a>
										</td>
									</tr>
								</table>
							</div>
						</div>




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