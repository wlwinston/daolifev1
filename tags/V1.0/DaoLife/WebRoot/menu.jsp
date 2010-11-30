<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String menuId = request.getParameter("id");
  if(menuId == null)
  {
	  menuId = "0";
  }
%>
<div class="top1">
	<div class="top2">
		<div class="logo">
			<a href="index.action"><img src="images/top_07.gif" width="62"
					height="63" /> </a>
		</div>
		<div class="tiao">


			<div class="tiao1">
				<img src="images/top_04.gif" />
			</div>

			<div class="tiao2">
				<img src="images/top_08.gif" />
			</div>
			<div class="tiao3" >
				<div><img src="images/top_09.gif" width="118" height="8" /></div>
				<div style="background:url('images/top_11.gif');height:66px;text-align:left;" id = "dlinfo"></div>
				<div><img src="images/top_13.gif" width="118" height="6" /></div>
			</div>
		</div>
		<!--表单-->
		<div class="select">
			<form action="" method="post" name="frm">

				<table width="227" height="32" border="0" cellpadding="0"
					cellspacing="0" background="images/farm.gif">
					<tr>
						<td height="32" align="left" valign="middle">
							<table width="98%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="86%" height="25">
										<label>
											&nbsp;&nbsp;
											<input name="textfield" type="text" id="textfield"
												style="border: 0px;" value="产品: 电影 妞儿 牛逼" />
										</label>
									</td>
									<td width="14%">
										<input type="image" src="images/selsect.gif" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<!--导航-->
		<div class="daohang">

			<ul>
				<%if(menuId.equals("1")) {%>
				<li style="background-image: url(images/daohang%20%20hover.gif)">
					
					<a href="index.action">首页</a>
				</li>
				<%}else{ %>
				<li>
					
					<a href="index.action">首页</a>
				</li>
				<%} %>
				<%if(menuId.equals("2")) {%>
				<li style="background-image: url(images/daohang%20%20hover.gif)">
					
					<a href="MyPage.action">我的首页</a>
				</li>
				<%}else{ %>
				<li>
					
					<a href="MyPage.action">我的首页</a>
				</li>
				<%} %>
				
				<%if(menuId.equals("3")) {%>
				<li style="background-image: url(images/daohang%20%20hover.gif)">
					
					<a href="product.action">Dao Life</a>
				</li>
				<%}else{ %>
				<li>
					
					<a href="product.action">Dao Life</a>
				</li>
				<%} %>
				
				<%if(menuId.equals("4")) {%>
				<li style="background-image: url(images/daohang%20%20hover.gif)">
					
					<a href="LookAround.action">随便看看</a>
				</li>
				<%}else{ %>
				<li>
					
					<a href="LookAround.action">随便看看</a>
				</li>
				<%} %>
				
				<%if(menuId.equals("5")) {%>
				<li style="background-image: url(images/daohang%20%20hover.gif)">
					
					<a href="DaoHot.action">最叨</a>
				</li>
				<%}else{ %>
				<li>
					
					<a href="DaoHot.action">最叨</a>
				</li>
				<%} %>
				
			</ul>
		</div>
	</div>

</div>

