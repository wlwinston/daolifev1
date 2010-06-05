<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
		<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/topic.js"></script>
	</head>

	<body>
		<jsp:include page="menu.jsp" flush="true"></jsp:include>
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
									<a href="topicDetail.action"><img src="images/topic.gif"
											alt="" width="113" height="22" />
									</a>
								</td>
								<td width="13%" align="center">
									&nbsp;
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
		<jsp:include page="foot.jsp" flush="true"></jsp:include>
	</body>
</html>
