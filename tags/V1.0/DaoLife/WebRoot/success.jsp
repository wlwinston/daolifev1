<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>操作成功</title>
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
$(function(){
	setTimeout(function(){
		window.location.href = 'MyPage.action';
	},10000);
});
</script>
<style type="text/css">
<!--
* {margin:0px; padding:0px;}
body {
	background-image: url(images/404_01.gif);
}
.w {
	height: 341px;
	width: 765px;
	margin-top: 200px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
}
.STYLE1 {
	font-size: 12px;
	color: #8d8d8d;
	font-weight: bold;
}
.w .STYLE1 {
	line-height: 1.8em;
}
.text12 {
	font-size: 12px;
	line-height: 1.8em;
}
-->
</style>
</head>

<body>
<div class="w">
  <table width="763" border="0">
    <tr>
      <td width="339"><img src="images/bingo.gif" width="341" height="317" /></td>
      <td width="414" align="left" valign="top"><table width="100%" height="297" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="68"><span class="STYLE1">Bingo！操作成功啦。<br />
           <font style="color:red;">十秒后会自动跳转到首页...</font></span></td>
        </tr>
        <tr>
          <td><img src="images/tiao.gif" width="327" height="1" /></td>
        </tr>
        <tr>
          <td height="131"><br /></td>
        </tr>
        <tr>
          <td height="94" align="left" valign="bottom"><table width="74%" border="0">
            <tr>
              <td height="19" align="center"><a href="MyPage.action"><img src="images/mypage.gif" width="123" height="34" border="0" /></a></td>
              <td align="center"><a href="http://daolife.taobao.com"><img src="images/1124_09.gif" width="122" height="34" border="0" /></a></td>
            </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>
</body>
</html>
