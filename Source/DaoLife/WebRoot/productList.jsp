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
		<jsp:include page="menu.jsp" flush="true"></jsp:include> 
		<!--banner区域-->
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
						                    <td><a href="chanpinmoer.htmml"><img src="images/1124_17.gif" width="123" height="32" border="0" /></a></td>
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
		<!--中间小条-->
		<div class="hengxian">
			<div class="hengxian1">
				<img src="images/top_16.gif" />
			</div>
		</div>

		<!--内容区域-->
		<div class="zhuti">
			<table width="650" height="283" border="0" cellpadding="0"
				cellspacing="0" class="zhuti2">
				<tr>
					<td height="282" align="left" valign="top">
						<div class="Box2">
							dao001---------009
						</div>
						<div class="chanpin1">
							<div class="hang">
								<div class="chanpinmingcheng">
									<div>
										<a href="daolifemare.html"><img src="images/chanpin1.gif"
												width="260" height="141" />
										</a>
									</div>
									<div class="chanpinneirong">
										<a href="daolifemare.html">Dao0005</a>
									</div>
									<div class="chanpinneirong">
										我听这么牛逼的音乐 还是没有妞儿喜欢我
									</div>
									<div class="chanpinneirong">
										<a href="daolifemare.html">查看详情</a>
									</div>
								</div>
								<div class="chanpinmingcheng">
									<div>
										<a href="daolifemare.html"><img src="images/chanpin1.gif"
												width="260" height="141" />
										</a>
									</div>
									<div class="chanpinneirong">
										<a href="#">Dao0005</a>
									</div>
									<div class="chanpinneirong">
										我听这么牛逼的音乐 还是没有妞儿喜欢我
									</div>
									<div class="chanpinneirong">
										<a href="#">查看详情</a>
									</div>
								</div>
								<div class="chanpinmingcheng">
									<div>
										<a href="daolifemare.html"><img src="images/chanpin1.gif"
												width="260" height="141" />
										</a>
									</div>
									<div class="chanpinneirong">
										<a href="#">Dao0005</a>
									</div>
									<div class="chanpinneirong">
										我听这么牛逼的音乐 还是没有妞儿喜欢我
									</div>
									<div class="chanpinneirong">
										<a href="#">查看详情</a>
									</div>
								</div>
							</div>
							<div class="hang">
								<div class="chanpinmingcheng">
									<div>
										<a href="daolifemare.html"><img src="images/chanpin1.gif"
												width="260" height="141" />
										</a>
									</div>
									<div class="chanpinneirong">
										<a href="#">Dao0005</a>
									</div>
									<div class="chanpinneirong">
										我听这么牛逼的音乐 还是没有妞儿喜欢我
									</div>
									<div class="chanpinneirong">
										<a href="#">查看详情</a>
									</div>
								</div>
								<div class="chanpinmingcheng">
									<div>
										<a href="daolifemare.html"><img src="images/chanpin1.gif"
												width="260" height="141" />
										</a>
									</div>
									<div class="chanpinneirong">
										<a href="#">Dao0005</a>
									</div>
									<div class="chanpinneirong">
										我听这么牛逼的音乐 还是没有妞儿喜欢我
									</div>
									<div class="chanpinneirong">
										<a href="#">查看详情</a>
									</div>
								</div>
								<div class="chanpinmingcheng">
									<div>
										<a href="daolifemare.html"><img src="images/chanpin1.gif"
												width="260" height="141" />
										</a>
									</div>
									<div class="chanpinneirong">
										<a href="#">Dao0005</a>
									</div>
									<div class="chanpinneirong">
										我听这么牛逼的音乐 还是没有妞儿喜欢我
									</div>
									<div class="chanpinneirong">
										<a href="#">查看详情</a>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="foot">
			<div class="foot1">
				foot
			</div>
		</div>

	</body>
</html>
