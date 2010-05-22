<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>用户注册</title>
		<script src="js/prototype.js" type="text/javascript"></script>
		<script type="text/javascript">
		function goTo(pageIndex){
			document.getElementsByName("paginationSupport.startIndexString")[0].value=pageIndex;
		document.getElementsByName("Userlist")[0].submit();
		}
		
		</script>

	</head>
	<body>
		<center>
		<h3>请输入注册信息</h3>
		<s:form action="/Userlist.action" method="post" validate="true">
		<s:hidden name="paginationSupport.pageSize"></s:hidden>
		<s:hidden name="paginationSupport.totalCount"></s:hidden>
		<s:hidden name="paginationSupport.startIndexString"></s:hidden>
		
		<s:hidden name="userSearch.username"></s:hidden>
		<s:hidden name="userSearch.password"></s:hidden>
		<s:hidden name="userSearch.email"></s:hidden>
		<s:if test="{paginationSupport!=null}">
			<s:iterator  value="paginationSupport.items" status="userList">
			<s:property value="username"/> &nbsp;&nbsp;&nbsp;&nbsp;<s:property value="password"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="email"/><br/>
		</s:iterator>
		</s:if>
		
	     <s:if test="{paginationSupport!=null}">
		  <a href="javascript:goTo(<s:property value="paginationSupport.startIndex"/>)"/>首页</a>
		  <a href="javascript:goTo(<s:property value="paginationSupport.previousIndex"/>)">上一页</a>
		  第<s:property value="paginationSupport.currentPage"/>页 共<s:property value="paginationSupport.pageCount"/>页
		  <a href="javascript:goTo(<s:property value="paginationSupport.nextIndex"/>)">下一页</a>
		  <a href="javascript:goTo(<s:property value="paginationSupport.lastIndex"/>)">末页</a>
		 </s:if>
		</s:form>
		</center>
	</body>
</html>