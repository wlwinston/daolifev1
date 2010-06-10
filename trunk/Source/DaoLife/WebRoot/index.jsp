<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>叨</title>
		<link href="styles/body.css" rel="stylesheet" type="text/css" />
		<link href="styles/index.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolifemare.css" rel="stylesheet" type="text/css" />
		<link href="styles/myhome.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolife.css" rel="stylesheet" type="text/css" />
		<link href="styles/chanpin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
		<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/movebox.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	<body>
		<jsp:include page="menu.jsp?id=1" flush="true"></jsp:include> 
		<div class="banner">
			<div class="banner1">
				<div id='mbox_content'>
				<s:if test="{productList!=null}">
					<s:iterator  value="productList" status="productList">
					<div class='mbox'>
						<table width="1002" height="300" border="0" cellspacing="0" cellpadding="0">
						  <tr>
						    <td height="104" align="left" valign="top" background="images/banner_03.gif"><table width="1002" border="0" cellspacing="0" cellpadding="0">
						      <tr>
						        <td width="53" height="299" align="center" valign="middle">&nbsp;</td>
						        <td width="897" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
						          <tr>
						            <td width="90%" height="298" align="right" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
						              <tr>
						                <td height="85" colspan="3" align="left" valign="bottom"><table height="21" border="0" cellpadding="0" cellspacing="0">
						                  <tr>
						                    <td width="71">&nbsp;</td>
						                    <td width="500" align="left" valign="bottom"><div class="Box">想把你说的话印在T恤上？快开始叨吧！</div></td>
						                  </tr>
						                </table></td>
						              </tr>
						              <tr>
						                <td width="13%" rowspan="2" align="center" valign="bottom">&nbsp;</td>
						                <td width="63%" height="74" align="left" valign="bottom"><table width="68%" border="0" cellspacing="5" cellpadding="0">
						                  <tr>
						                    <td height="19" align="left"><span class="STYLE4"><s:property value="productName" escape="false"/></span></td>
						                  </tr>
						                  
						                </table></td>
						                <td width="24%" align="center" valign="bottom">&nbsp;</td>
						              </tr>
						              
						              <tr>
						                <td height="42" align="left" valign="bottom"><table width="69%" border="0" cellspacing="5" cellpadding="0">
						                  <tr>
						                    <td height="19" align="left"><span class="STYLE4">@<s:property value="productAuthor" /> <s:date name="productPosttime" format="yyyy.MM.dd"/></span></td>
						                  </tr>
						                </table></td>
						                <td height="42" align="center" valign="bottom">&nbsp;</td>
						              </tr>
						              <tr>
						                <td height="65" colspan="3" align="center" valign="bottom"><table width="45%" border="0" cellspacing="5" cellpadding="0">
						                  <tr>
						                    <td height="32"><a href="<s:property value="productAuthorurl" />"><img src="images/1124_13.gif" width="122" height="32" border="0" /></a></td>
						                    <td><a href="<s:property value="productUrl" />"><img src="images/1124_15.gif" width="122" height="32" border="0" /></a></td>
						                    <td><a href="product.action"><img src="images/1124_17.gif" width="123" height="32" border="0" /></a></td>
						                  </tr>
						                  
						                </table></td>
						              </tr>
						            </table></td>
						            <td width="42%" align="center"><img src="images/T11.png" width="223" height="247" /></td>
						          </tr>
						        </table></td>
						        <td width="52">&nbsp;</td>
						      </tr>
						    </table></td>
						  </tr>
						</table>
					</div>
					</s:iterator>
				</s:if>
				</div>
				<div id='mbox_button_left' class='mbox_button'>
					<div>
						<a href="javascript://"><img src="images/left_arrow.gif" width="41" height="41" border="0" /></a>
					</div>
				</div>
				<div id='mbox_button_right' class='mbox_button'>
					<div>
						<a href="javascript://"><img src="images/right_arrow.gif" width="41" height="41" border="0" /></a>
					</div>
				</div>
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
						<!--左边区域-->
						<div class="zuo">
							<div class="Box">
								MTV和我们一起玩儿了你的态度去哪儿了？
							</div>
							<div class="guanggao">
								<a href="product.action"><center>
										<img src="images/mtv.gif" alt="" />
									</center>
								</a>
							</div>
							<span class="text"><br />
								MTV跟我们一起玩儿了，当然，他们掏钱了，所以最后我们会送你们免费的T恤。<br />
								参与方式是在你的对话框里输入“＃MTV＃＋你为可口可乐设计的广告语”，别忘了提交，我们私下提醒你，越无聊越好。<br />
								活动时间为 2010年1月10日－2010年1月17日<br /> 活动结束，等着你的快递。<br />
								<table width="97%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="86%" align="right">
											<img src="images/moer.gif" width="14" height="13" />
										</td>
										<td width="14%" align="right">
											<a href="product.action">查看详情</a>
										</td>
									</tr>
								</table> </span>

							<div class="Box">
								MTV和我们一起玩儿了你的态度去哪儿了？
							</div>


							<div class="daokuai">
								<div class="bloga">
									<div class="dao">
										<a href="text.html"><img src="images/dao.gif" />
										</a>
									</div>
									<div class="dao1">
										<div>
											<h3>
												<a href="regist.jsp">注册</a>
											</h3>
										</div>
										<div class="dao2">
											注册时请使用你的邮箱，并且在注册时填写你的用户名和密码，如果还有时间，弄弄你的头像也没有问题，对了，我们觉得用真头像很傻。
										</div>
									</div>
								</div>
								<div class="bloga">
									<div class="dao">
										<img src="images/dao.gif" />
									</div>
									<div class="dao1">
										<div>
											<h3>
												<a href="text.html">如何获得免费的T恤</a>
											</h3>
										</div>
										<div class="dao2">
											尽量多的结识叨友，让自己的废话尽可能长时间的保持在首页直到我们去制作他。最后，我们送你一件儿。最先觉得你很酷的三个叨友我们也顺手送一件，如果你不反对的话。
										</div>
									</div>
								</div>
							</div>
							<div class="daokuai">
								<div class="bloga">
									<div class="dao">
										<img src="images/dao.gif" />
									</div>
									<div class="dao1">
										<div>
											<h3>
												<a href="text.html">关于你说的废话</a>
											</h3>
										</div>
										<div class="dao2">
											不要担心自己说的话很无聊，总有人比你无聊。我们的0003期T恤的Slogan竟然是“饿了，吃饭去”。你说什么都无所谓，但是我们希望你不要说一些容易惹麻烦的话，如果你想说，跟我们私下交流，我们有更好的地方。
										</div>
									</div>
								</div>
								<div class="bloga">
									<div class="dao">
										<img src="images/dao.gif" />
									</div>
									<div class="dao1">
										<div>
											<h3>
												<a href="text.html">有关叨团队</a>
											</h3>
										</div>
										<div class="dao2">
											我们放在最后介绍我们自己，因为实在没有什么好说的，我们人还不多，也比较需要一些投资，曾经有人很无聊，问我们要免费的T恤，我们告诉他以后，而不是直接让他去死。我们希望有机会送你们更多的T恤。
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--右边区域-->
						<div class="reight">
							<div id="personal">
							</div>
							<div class="Box">
								最热叨友<span><a href="javascript://">查看全部</a></span>
							</div>
							<div class="daolist">
								<s:if test="{hotUserList!=null}">
								<s:iterator  value="hotUserList" status="hotUserList">
								<div>
									<a href="PersonPage.action?userId=<s:property value="userId"/>">
										<img src="images/touxiang.gif" width="76" height="77" />
										<p><s:property value="userNickName"/><p>
									</a>
								</div>
								
								</s:iterator>
								</s:if>
							</div>
							<div class="Box">
								最热叨句<span><a href="javascript://">查看全部</a></span>
							</div>
							<s:if test="{hotDaoList!=null}">
							<s:iterator  value="hotDaoList" status="hotDaoList">
							<div class="daoju1">
								<a href="PersonPage.action?userId=<s:property value="userId"/>">@<s:property value="userName"/></a>
							</div>
							<div class="daoju2">
								<s:property value="contentBody" escape="false"/>
							</div>
							<div class="daoju3">
								<a href="javascript://"><s:property value="upSum"/>人支持</a>
							</div>
							
							</s:iterator>
							</s:if>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<jsp:include page="foot.jsp" flush="true"></jsp:include> 
	</body>
</html>
