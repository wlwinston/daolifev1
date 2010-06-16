<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>叨--产品</title>
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
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	<body>
		<jsp:include page="menu.jsp" flush="true"></jsp:include> 
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
						            <td width="58%" height="298" align="right" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
						              <tr>
						                <td height="85" colspan="3" align="left" valign="bottom"><table width="355" height="21" border="0" cellpadding="0" cellspacing="0">
						                  <tr>
						                    <td width="71">&nbsp;</td>
						                    <td width="284" align="left" valign="bottom"><table width="87%" border="0" cellspacing="0" cellpadding="0">
						                      <tr>
						                        <td align="left"><span class="STYLE1">第<s:property value="productDaonum"/>期</span></td>
						                      </tr>
						                    </table></td>
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
<div class="zhuti">
				  <table width="650" height="1022" border="0" cellpadding="0" cellspacing="0" class="zhuti1">
                    <tr>
                      <td height="1021" valign="top">
                      <div class="zuo">
                        <table width="495" height="45" border="0" align="center" cellpadding="0" cellspacing="0" class="xia1">
                          <tr>
                            <td width="120" class="Box1">第<s:property value="product.productDaonum"/>期</td>
                            <td width="127" align="left" valign="middle"><a href="touxiang.html"><img src="images/chanpin_03.gif" width="123" height="31" /></a></td>
                            <td width="125" align="left" valign="middle"><a href="daolife.html"><img src="images/chanpin_05.gif" width="123" height="31" /></a></td>
                            <td width="123" align="left" valign="middle"><a href="http://www.taobao.com"><img src="images/chanpin_07.gif" width="123" height="31" /></a></td>
                          </tr>
                        </table>
                        <table width="541" height="536" border="0" cellpadding="0" cellspacing="0" class="tabledao">
                          <tr>
                            <td height="63" align="center"><img src="<s:property value="product.productTshirtPic" />" width="426" height="421" /></td>
                          </tr>
                        </table>
                        <table width="573" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="478" height="182" align="center" valign="top" class="ziti"><table width="100%" height="39" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                  <td height="28" align="center">&nbsp;</td>
                              </tr>
                              </table>
                              <table width="535" border="0" align="center" cellpadding="0" cellspacing="0">
                                <tr>
                                  <td width="76" height="44" align="left">&nbsp;</td>
                                  <td width="459" align="left"><table width="420" height="21" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                      <td width="183" align="left">发布时间：<s:date name="product.dlContent.posttime" format="yyyy年M月d日"/></td>
                                      <td width="253" align="left">被转发（<s:property value="product.dlContent.retwittNum"/>）次数 被顶（<s:property value="product.dlContent.upNum"/>）次</td>
                                    </tr>
                                  </table></td>
                                </tr>
                                <tr>
                                  <td height="50" align="left">&nbsp;</td>
                                  <td align="left"><span class="text1">想把你说的话印在Tee上么？那就快来www.daolife.com注册吧。每第一百名有效注册（邮箱真实有效，至少发一条叨，转发别人一次。）的用户将得到daolife送出的免费Tee，邮费我们也付啦。如果你说的话受欢迎，我们将会把你说的话印在Tee上。作为作者的你，将会免费得到这件当期Tee，并且，这件Tee每卖出一件，我们将付给你一元钱。
								还等什么， 快来www.daolife.com注册吧。<br>
								活动时间：2010-06-15 — 2010-07-31<br>
								我们会邮件通知您中奖情况，请保持邮箱通畅。<br>
								友情提醒：世界杯虽好，可别冷淡了可人的她。<br></span></td>
                                </tr>
                                <tr>
                                  <td height="50" align="left">&nbsp;</td>
                                  <td align="left"><a href="<s:property value="product.productUrl"/>"><img src="images/chanpin_07.gif" width="123" height="31" /></a></td>
                                </tr>
                              </table>
                              <p class="text1">&nbsp;</p>
                            <p>&nbsp;</p></td>
                          </tr>
                        </table>
                      </div>
						<div class="reight">
							<div class="Box">
								共有<s:property value="product.productFollowsum"/>位叨友关注这件T恤
							</div>
							<div class="daolist">
								<s:if test="{hotUserList!=null}">
								<s:iterator  value="productfollowUserList" status="productfollowUserList">
								<div>
									<a href="PersonPage.action?userId=<s:property value="userId"/>">
										<img src="images/touxiang.gif" width="76" height="77" />
										<p><s:property value="userNickName"/><p>
									</a>
								</div>
								
								</s:iterator>
								</s:if>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<jsp:include page="foot.jsp" flush="true"></jsp:include>
	</body>
</html>
