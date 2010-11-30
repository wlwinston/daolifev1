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
		<title><s:property value="personalInfo.userNickName"/>的首页</title>
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
		<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/pesonalDao.js"></script>
		<script type="text/javascript" src="js/correctPNG.js"></script>
		<link href="styles/body.css" rel="stylesheet" type="text/css" />
		<link href="styles/index.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolifemare.css" rel="stylesheet" type="text/css" />
		<link href="styles/myhome.css" rel="stylesheet" type="text/css" />
		<link href="styles/daolife.css" rel="stylesheet" type="text/css" />
		<link href="styles/chanpin.css" rel="stylesheet" type="text/css" />
	</head>
		<body>
		<input type="hidden"  id="getUserId" value ="<s:property value="userId"/>"/>
		<!--top头部区域-->
		<jsp:include page="menu.jsp" flush="true"></jsp:include> 
		<div class="zhuti">
			<table class="zhuti3" width="650" height="1022" border="0" cellpadding="0"
				cellspacing="0" class="zhuti1">
				<tr>
					<td height="1021" align="left" valign="top">
						<!--左边区域-->
						<div class="zuo">
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
										<s:property value="personalInfo.userNickName"/>
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
													<a href="woguanzhude.html"><s:property value="personalInfo.followNum"/></a>
												</td>
												<td width="76" align="center"
													class="STYLE4 STYLE7 STYLE5 STYLE6">
													<a href="guanzhuwode.html"><s:property value="personalInfo.fansNum"/></a>
												</td>
												<td width="78" align="center"
													class="STYLE4 STYLE7 STYLE5 STYLE6">
													<a href="wozaidao.html"><s:property value="personalInfo.contentsSize"/></a>
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
													<s:property value="userInfo" />
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