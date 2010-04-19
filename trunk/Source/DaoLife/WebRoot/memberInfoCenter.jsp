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
		<link href="styles/taober.css" rel="stylesheet" type="text/css" />
		<link href="styles/daohot.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
		<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		function memberInfoSubmit(){
			DaolifeAjax.addDao($('#textarea3').val(),function(rs){
				if(rs != null){
					alert(rs.dlContent.contentId);
				}else{
					alert('发送信息失败');
				}
			});
		}
		</script>
	</head>
	<body>
		<div class="top">
			<div class="logo">
				<img src="images/logo.gif" width="63" height="70" />
				<div class="logo2">
					<ul>
						<li>
							有5条新评论
						</li>
						<li>
							有8条叨@您
						</li>
						<li>
							有8封站内信
						</li>
						<li>
							有3位新关注
						</li>
					</ul>
				</div>
			</div>
			<div class="div2">

				<div class="select">
					<form action="" method="post" name="frm">
						<table width="227" height="32" border="0" cellpadding="0"
							cellspacing="0" background="images/farm.gif">
							<tr>
								<td>
									<label>
										&nbsp;
										<input name="textfield" type="text" id="textfield"
											style="border: 0; width: 185px; height: 20px;"
											value="产品: 电影 妞儿 牛逼" />
										<input type="image" src="images/selsect.gif" />
									</label>
								</td>
							</tr>
						</table>


					</form>
				</div>





			</div>

			<div class="div3">
				<div class="daohang">
					<ul>
						<li>
							<a href="index.html"><img src="images/home.gif" width="81"
									height="32" /> </a>
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
						<li>
							<a href="daohot.html">最叨</a>
						</li>
					</ul>
				</div>



			</div>
		</div>
		<div class="js1">
			<table width="1002" height="314" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td height="203" align="left" valign="top">
						<table width="991" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="643" height="312" align="left" valign="top">
									<table width="644" height="308" border="0" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="644" height="62" align="left" valign="bottom">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您还可以输入25个字符
											</td>
										</tr>
										<tr>
											<td height="220" align="center" valign="top">
												<table width="550" border="0" cellspacing="0"
													cellpadding="0">

													<tr>
														<td height="205" align="left" valign="top">
															<form id="form1" name="form1" method="post" onsubmit="return false">
																<table width="525" height="205" border="0"
																	cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="525" height="172">
																			<textarea name="textarea3" cols="45" rows="5"
																				class="tabledao" id="textarea3"
																				style="background-image: url(images/myhome_07.gif); border: 0; width: 521px; height: 138px;"></textarea>
																		</td>
																	</tr>
																	<tr>
																		<td align="right">
																			<label>
																				<input type="image" src="images/myhome_11.gif" onclick="memberInfoSubmit()" />
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
								<td width="348" align="center" valign="middle">
									<img src="images/T.gif" width="223" height="247" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div class="tiao">
			<img src="images/tiao.gif" />
		</div>
		<div class="neirong">

			<div class="Welcome">
				<div class="xx">
					<table width="418" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="121" align="center">
								<a href="touxiang.html"></a>
								<a href="daohot1.html"><img src="images/myhome_21.gif"
										width="112" height="24" /> </a>
							</td>
							<td align="left" valign="middle">
								<table width="100" border="0" cellspacing="4" cellpadding="4">
									<tr>
										<td>
											<a href="wd.html"><img src="images/myhome_23.gif"
													width="48" height="23" /> </a>
										</td>
										<td>
											<a href="look.html"><img src="images/myhome_25.gif"
													width="65" height="23" /> </a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<table width="540" border="0" cellspacing="4" cellpadding="4">
					<tr>
						<td width="47" height="44">
							<img src="images/myhome_30.gif" width="78" height="77" />
						</td>
						<td width="465" align="left" valign="top">
							<table width="432" height="78" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="432">
										高建
									</td>
								</tr>
								<tr>
									<td>
										我在真理部上班
									</td>
								</tr>
								<tr>
									<td align="right">
										<table width="250" border="0" cellspacing="2" cellpadding="2">
											<tr>
												<td width="78">
													回复
													<a href="#">（205）</a>
												</td>
												<td width="75">
													转发
													<a href="#">（205）</a>
												</td>
												<td width="77">
													顶他
													<a href="#">（100）</a>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<center>
					<table width="487" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="487">
								<img src="images/myhome_34.gif" width="523" height="6" />
							</td>
						</tr>
						<tr>
							<td height="338" align="center" valign="top"
								background="images/myhome_37.gif">
								<table width="510" height="15" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="255">
											共有评论28条 您现在查看的是1-10条
										</td>
										<td width="268">
											前十条 上一页 1 2 3 下一页 后十条
										</td>
									</tr>
								</table>
								<table width="510" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td width="45" height="50" align="left" valign="top">
											<img src="images/myhome_39.gif" width="38" height="38" />
										</td>
										<td width="457" align="left" valign="top">
											<table width="432" height="46" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="432" height="26">
														th5ch.com
													</td>
												</tr>

												<tr>
													<td height="20" align="left">
														<table width="432" border="0" cellspacing="2"
															cellpadding="2">
															<tr>
																<td width="300">
																	我听这么牛逼的音乐，却还是没有妞儿喜欢我。
																</td>
																<td width="70">
																	回复
																</td>
																<td width="42">
																	删除
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="510" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td width="45" height="50" align="left" valign="top">
											<img src="images/myhome_39.gif" width="38" height="38" />
										</td>
										<td width="457" align="left" valign="top">
											<table width="432" height="46" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="432" height="26">
														th5ch.com
													</td>
												</tr>
												<tr>
													<td height="20" align="left">
														<table width="432" border="0" cellspacing="2"
															cellpadding="2">
															<tr>
																<td width="300">
																	我听这么牛逼的音乐，却还是没有妞儿喜欢我。
																</td>
																<td width="70">
																	回复
																</td>
																<td width="42">
																	删除
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="510" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td width="45" height="50" align="left" valign="top">
											<img src="images/myhome_39.gif" width="38" height="38" />
										</td>
										<td width="457" align="left" valign="top">
											<table width="432" height="46" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="432" height="26">
														th5ch.com
													</td>
												</tr>
												<tr>
													<td height="20" align="left">
														<table width="432" border="0" cellspacing="2"
															cellpadding="2">
															<tr>
																<td width="300">
																	我听这么牛逼的音乐，却还是没有妞儿喜欢我。
																</td>
																<td width="70">
																	回复
																</td>
																<td width="42">
																	删除
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="510" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td width="45" height="50" align="left" valign="top">
											<img src="images/myhome_39.gif" width="38" height="38" />
										</td>
										<td width="457" align="left" valign="top">
											<table width="432" height="46" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="432" height="26">
														th5ch.com
													</td>
												</tr>
												<tr>
													<td height="20" align="left">
														<table width="432" border="0" cellspacing="2"
															cellpadding="2">
															<tr>
																<td width="300">
																	我听这么牛逼的音乐，却还是没有妞儿喜欢我。
																</td>
																<td width="70">
																	回复
																</td>
																<td width="42">
																	删除
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="510" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="74" colspan="2" align="center" valign="top">
											<form action="" method="post" enctype="multipart/form-data"
												name="hueifu" id="hueifu">
												<table width="493" border="0" cellspacing="4"
													cellpadding="0">
													<tr>
														<td height="79" colspan="2" align="center" valign="top">
															<label></label>
															<label>
																<textarea name="textarea" cols="45" rows="5"
																	class="tabledao" id="textarea"
																	style="background-image: url(images/myhome_42.gif); border: 0; width: 495px; height: 53px;"></textarea>
															</label>
														</td>
													</tr>
													<tr>
														<td width="370" height="22" align="left" valign="top">
															<label>
																<input type="checkbox" name="dao1" id="dao1" />
																同时叨一条
															</label>
														</td>
														<td width="123">
															<img src="images/myhome_46.gif" width="88" height="22" />
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
								<img src="images/myhome_45.gif" width="523" height="7" />
							</td>
						</tr>
					</table>
					<table width="540" border="0" cellspacing="4" cellpadding="4">
						<tr>
							<td width="47" height="44">
								<img src="images/myhome_30.gif" width="78" height="77" />
							</td>
							<td width="465" align="left" valign="top">
								<table width="432" height="78" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="432">
											高建
										</td>
									</tr>
									<tr>
										<td>
											我在真理部上班
										</td>
									</tr>
									<tr>
										<td align="right">
											<table width="250" border="0" cellspacing="2" cellpadding="2">
												<tr>
													<td width="78">
														回复
														<a href="#">（205）</a>
													</td>
													<td width="75">
														转发
														<a href="#">（205）</a>
													</td>
													<td width="77">
														顶他
														<a href="#">（100）</a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="540" border="0" cellspacing="4" cellpadding="4">
						<tr>
							<td width="47" height="44">
								<img src="images/myhome_30.gif" width="78" height="77" />
							</td>
							<td width="465" align="left" valign="top">
								<table width="432" height="78" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="432">
											高建
										</td>
									</tr>
									<tr>
										<td>
											我在真理部上班
										</td>
									</tr>
									<tr>
										<td align="right">
											<table width="250" border="0" cellspacing="2" cellpadding="2">
												<tr>
													<td width="78">
														回复
														<a href="#">（205）</a>
													</td>
													<td width="75">
														转发
														<a href="#">（205）</a>
													</td>
													<td width="77">
														顶他
														<a href="#">（100）</a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="487" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="487">
								<img src="images/myhome_34.gif" width="523" height="6" />
							</td>
						</tr>
						<tr>
							<td height="338" align="center" valign="top"
								background="images/myhome_37.gif">
								<table width="510" height="15" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="255">
											共有评论28条 您现在查看的是1-10条
										</td>
										<td width="268">
											前十条 上一页 1 2 3 下一页 后十条
										</td>
									</tr>
								</table>
								<table width="510" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td width="45" height="50" align="left" valign="top">
											<img src="images/myhome_39.gif" width="38" height="38" />
										</td>
										<td width="457" align="left" valign="top">
											<table width="432" height="46" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="432" height="26">
														th5ch.com
													</td>
												</tr>
												<tr>
													<td height="20" align="left">
														<table width="432" border="0" cellspacing="2"
															cellpadding="2">
															<tr>
																<td width="300">
																	我听这么牛逼的音乐，却还是没有妞儿喜欢我。
																</td>
																<td width="70">
																	回复
																</td>
																<td width="42">
																	删除
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="510" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td width="45" height="50" align="left" valign="top">
											<img src="images/myhome_39.gif" width="38" height="38" />
										</td>
										<td width="457" align="left" valign="top">
											<table width="432" height="46" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="432" height="26">
														th5ch.com
													</td>
												</tr>
												<tr>
													<td height="20" align="left">
														<table width="432" border="0" cellspacing="2"
															cellpadding="2">
															<tr>
																<td width="300">
																	我听这么牛逼的音乐，却还是没有妞儿喜欢我。
																</td>
																<td width="70">
																	回复
																</td>
																<td width="42">
																	删除
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="510" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td width="45" height="50" align="left" valign="top">
											<img src="images/myhome_39.gif" width="38" height="38" />
										</td>
										<td width="457" align="left" valign="top">
											<table width="432" height="46" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="432" height="26">
														th5ch.com
													</td>
												</tr>
												<tr>
													<td height="20" align="left">
														<table width="432" border="0" cellspacing="2"
															cellpadding="2">
															<tr>
																<td width="300">
																	我听这么牛逼的音乐，却还是没有妞儿喜欢我。
																</td>
																<td width="70">
																	回复
																</td>
																<td width="42">
																	删除
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="510" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td width="45" height="50" align="left" valign="top">
											<img src="images/myhome_39.gif" width="38" height="38" />
										</td>
										<td width="457" align="left" valign="top">
											<table width="432" height="46" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="432" height="26">
														th5ch.com
													</td>
												</tr>
												<tr>
													<td height="20" align="left">
														<table width="432" border="0" cellspacing="2"
															cellpadding="2">
															<tr>
																<td width="300">
																	我听这么牛逼的音乐，却还是没有妞儿喜欢我。
																</td>
																<td width="70">
																	回复
																</td>
																<td width="42">
																	删除
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="510" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="74" colspan="2" align="center" valign="top">
											<form action="" method="post" enctype="multipart/form-data"
												name="zhuanfa" id="hueifu2">
												<table width="493" border="0" cellspacing="4"
													cellpadding="0">
													<tr>
														<td height="79" colspan="2" align="center" valign="top">
															<label></label>
															<label>
																<textarea name="textarea2" cols="45" rows="5"
																	class="tabledao" id="textarea2"
																	style="background-image: url(images/myhome_42.gif); border: 0; width: 495px; height: 53px;"></textarea>
															</label>
														</td>
													</tr>
													<tr>
														<td width="370" height="22" align="left" valign="top">
															<label>
																<input type="checkbox" name="dao2" id="dao2" />
																同时品论到原帖
															</label>
														</td>
														<td width="123">
															<img src="images/myhome_46.gif" width="88" height="22" />
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
								<img src="images/myhome_45.gif" width="523" height="7" />
							</td>
						</tr>
					</table>
					<table width="527" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="149" align="left" valign="bottom">
								<div class="yem">
									<ul>
										<li>
											<a href="#">1</a>
										</li>
										<li>
											<a href="#">2</a>
										</li>
										<li>
											<a href="#">3</a>
										</li>
										<li>
											<a href="#">4</a>
										</li>
										<li>
											<a href="#">5</a>
										</li>
									</ul>
								</div>
							</td>
						</tr>
					</table>
				</center>
			</div>
			<div class="Box">
				<div class="xx">
					<table width="352" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="151">
								the5gg.com
							</td>
							<td width="151" align="right" valign="bottom">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="51%" class="xxguanzhu">
											&nbsp;
										</td>
										<td width="49%" class="xxguanzhu">
											推出登陆
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div class="touxiang2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="32%" align="center">
								<a href="touxiang.html"><img src="images/1_10.gif"
										width="113" height="112" /> </a>
							</td>
							<td width="68%" align="left" valign="top">
								<div class="guanzhu1">
									<table width="230" height="52" border="0" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="76" height="33" align="center">
												6
											</td>
											<td width="76" align="center">
												4
											</td>
											<td width="78" align="center">
												5
											</td>
										</tr>
										<tr>
											<td align="center">
												他关注
											</td>
											<td align="center">
												关注他
											</td>
											<td align="center">
												叨
											</td>
										</tr>
									</table>
								</div>
								<table width="230" border="0" align="center" cellpadding="0"
									cellspacing="3">
									<tr>
										<td width="226">
											简介：男 北京市 朝阳起 设计师
										</td>
									</tr>
									<tr>
										<td>
											博客：the5th.blogbus.com
										</td>
									</tr>
									<tr>
										<td>
											关于他：吃个苹果吧青年
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div class="touxiang2">
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
				<div>
					<h1>
						热门叨友
					</h1>
				</div>
				<div class="touxiang1">
					<a href="touxiang.html"><img src="images/touxiang.gif"
							width="76" height="77" /> </a>
					<img src="images/touxiang.gif" width="76" height="77" />
					<img src="images/touxiang.gif" width="76" height="77" />
					<img src="images/touxiang.gif" width="76" height="77" />
					<img src="images/touxiang.gif" width="76" height="77" />
					<img src="images/touxiang.gif" width="76" height="77" />
					<img src="images/touxiang.gif" width="76" height="77" />
					<img src="images/touxiang.gif" width="76" height="77" />
					<img src="images/touxiang.gif" width="76" height="77" />
				</div>

				<div class=" moer">
					<img src="images/moer.gif" width="14" height="13" />
					&nbsp;&nbsp;
					<a href="daohot.html">查看全部</a>
				</div>

				<div>
					<h1>
						最热叨句
					</h1>
				</div>
				<!---最热叨句1--->
				<div>
					<div class="daoju1">
						@高建
					</div>
					<div class="daoju2">
						我在真理部上班
					</div>
					<div class="daoju3">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="daohot1.html">&nbsp;&nbsp;&nbsp;&nbsp;64514人支持</a><a
							href="#"></a>
					</div>
					<!------最热叨句2------->
					<div class="daoju1">
						@高建
					</div>
					<div class="daoju2">
						我在真理部上班
					</div>
					<div class="daoju3">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="daohot1.html">&nbsp;64514人支持</a>
					</div>
					<!------最热叨句2------->
					<div class="daoju1">
						@高建
					</div>
					<div class="daoju2">
						我在真理部上班
					</div>
					<div class="daoju3">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="daohot1.html">&nbsp;64514人支持</a>
					</div>

					<div class=" moer">
						<img src="images/moer.gif" width="14" height="13" />
						&nbsp;&nbsp;
						<a href="daohot1.html">查看全部</a>
					</div>
				</div>
				<div>
					<div>
						<h1>
							我的叨T
						</h1>
					</div>
				</div>
				<div class="touxiang1">
					<img src="images/Tx.gif" width="223" height="219" />
				</div>
				<div class=" moer">
					<img src="images/moer.gif" width="14" height="13" />
					&nbsp;&nbsp;
					<a href="daolife.html">查看全部</a>
				</div>
			</div>

			<div></div>
		</div>

		</div>
		<!---foot-->
		<div class="foot">
			aa
		</div>
	</body>
</html>