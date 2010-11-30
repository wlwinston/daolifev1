<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="Box">
	最热叨友<span><a href="javascript://">查看全部</a></span>
</div>
<div class="daolist">
	<s:if test="{hotUserList!=null}">
	<s:iterator  value="hotUserList" status="hotUserList">
	<div>
		<a href="PersonPage.action?userId=<s:property value="userId"/>">
			<img src="images/touxiang.gif" width="76" height="77" />
			<p><s:property value="userNickName"/><p>
		</a>
	</div>
	
	</s:iterator>
	</s:if>
</div>
<div class="Box">
	最热叨句<span><a href="javascript://">查看全部</a></span>
</div>
<s:if test="{hotDaoList!=null}">
<s:iterator  value="hotDaoList" status="hotDaoList">
<div class="daoju1">
	<a href="PersonPage.action?userId=<s:property value="userId"/>">@<s:property value="userName"/></a>
</div>
<div class="daoju2">
	<s:property value="contentBody" escape="false"/>
</div>
<div class="daoju3">
	<a href="myhome.html"><s:property value="upSum"/>人支持</a>
</div>
</s:iterator>
</s:if>