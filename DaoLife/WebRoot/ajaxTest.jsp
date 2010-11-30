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
		function getTime(time){
			var time = new Date(time);
	var year = time.getFullYear();
	var month = (time.getMonth() + 1);
	var day = time.getDate();
	var hours = time.getHours();
	var seconds = time.getSeconds();
	var minutes = time.getMinutes();
	var date = new Date();
	var str = '';
	str = year + '年' + month + '月' + day + '日';
	return  str + ' ' + hours + ':' + minutes;
		}
			function showUserInfo()
			{   
				//var id=$F("userId");
				//DaolifeAjax.login('wangleimsh@163.com','wangleimsh',true);
				DaolifeAjax.getAllDao(1,showresult);
			}
			function showresult(result)
			{	
				alert(result.items[0].contentBody+"============"+result.items[0].posttime+"============"+getTime(result.items[0].posttime));
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