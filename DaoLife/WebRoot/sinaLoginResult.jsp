<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
		<title>叨--用户注册</title>
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
    	<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/sinaLogin.js"></script>
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
				<table width="539" border="0" align="center" cellpadding="4" cellspacing="4" class="zhuti3" style="padding-top:30px;padding-bottom:200px;font-size:15px;">
				    <tr>
				    	<td width="220">
				    		<div style="height:260px;text-align:center;">
				    		<p><b>新浪账户信息</b></p>
				    		<image src="<s:property value="sinaUser.profileImageURL"/>"/>
							<p><b><s:property value="sinaUser.screenName"/></b></p>
							<input id="sinaUserName" type="hidden" value="<s:property value="sinaUser.screenName"/>"/>
				    		</div>
				    	</td>
				    	<td>
				    		<s:form action="SinaLoginNext.action" method="post" enctype="multipart/form-data" name="regist" id="regist">
							</s:form>
				    	</td>
				    </tr>
				</table>
			<!--</div>  -->
		</div>
		<jsp:include page="foot.jsp" flush="true"></jsp:include>
	</body>
</html>