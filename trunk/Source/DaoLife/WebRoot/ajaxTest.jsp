<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>AJAX测试</title>
		<script src="js/prototype.js" type="text/javascript"></script>
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
    	<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
		<script language="JavaScript">
			function showUserInfo()
			{   
				var id=$F("userId");
				DaolifeAjax.getUserById(id,showresult);
			}
			function showresult(result)
			{	
				if(result != null)
				{
					alert("userName===="+result.username+"\n"+"password===="+result.password+"\n"+"email===="+result.email);
				}
			}	
			function refresh(){
				$("authImg").src="authImg?now="+new Date();
			}
		</script>
	</head>
	<body>
		<center>
		<h3>请输入用户ID</h3>
		<input type ="text" name="userId" id="userId"/> &nbsp; &nbsp; &nbsp; &nbsp; <input type ="button" name="testButton" value="获取信息" onclick="showUserInfo()" />
		</center>
	</body>
</html>