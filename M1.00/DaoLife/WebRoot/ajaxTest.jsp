<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>AJAX测试</title>
		<script type="text/javascript" src="dwr/engine.js"> </script>
    	<script type="text/javascript" src="dwr/util.js"> </script> 
    	<script type="text/javascript" src="dwr/interface/DaolifeAjax.js"></script>
    	<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
    	<script type="text/javascript" src="js/form.js"></script>
		<script language="JavaScript">
		
			function showUserInfo()
			{   
				//var id=$F("userId");
				DaolifeAjax.getAllDao(2,showresult);
			}
			function showresult(result)
			{	
				if(result != null)
				{
					alert(result.items[0].dlUsers.userName);
					//alert("userName===="+result.username+"\n"+"password===="+result.password+"\n"+"email===="+result.email);
				}
			}	
			function refresh(){
				$("authImg").src="authImg?now="+new Date();
			}
			function checkUserInfo()
			{   
				var id=$F("userId");
				DaolifeAjax.checkUserName(id,aftercheckUserInfo);
			}
			function aftercheckUserInfo(result)
			{   
				if(result){
					alert("true");
				}else{
					alert("false");
				}
			}
			$(function(){
				Form.add('userId',DaolifeAjax.checkUserName);
			});
		</script>
	</head>
	<body onload="showUserInfo()">
		<center>
		<h3>请输入用户ID</h3>
		<input type ="text" name="userId" id="userId"/> 
		</center>
	</body>
</html>