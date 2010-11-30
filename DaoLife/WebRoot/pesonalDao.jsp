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
		<title>叨--<s:property value="personalInfo.userNickName"/>的首页</title>
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
							<div id="personal">
								<dir style="padding-top:30px;text-align:center;"><img src="images/ploading.gif"></dir>
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
		<jsp:include page="foot.jsp" flush="true"></jsp:include> 
	</body>
</html>