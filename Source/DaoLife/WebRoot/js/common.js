function getPageScroll() {
	var xScroll, yScroll;
	if (self.pageYOffset) {
		yScroll = self.pageYOffset;
		xScroll = self.pageXOffset;
	} else if (document.documentElement && document.documentElement.scrollTop) {	 
		yScroll = document.documentElement.scrollTop;
		xScroll = document.documentElement.scrollLeft;
	} else if (document.body) {
		yScroll = document.body.scrollTop;
		xScroll = document.body.scrollLeft;	
	}
	arrayPageScroll = new Array(xScroll,yScroll);
	return arrayPageScroll;
};
function getPageSize() {
	var xScroll, yScroll;
	if (window.innerHeight && window.scrollMaxY) {	
		xScroll = window.innerWidth + window.scrollMaxX;
		yScroll = window.innerHeight + window.scrollMaxY;
	} else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
		xScroll = document.body.scrollWidth;
		yScroll = document.body.scrollHeight;
	} else {
		xScroll = document.body.offsetWidth;
		yScroll = document.body.offsetHeight;
	}
	var windowWidth, windowHeight;
	if (self.innerHeight) {	
		if(document.documentElement.clientWidth){
			windowWidth = document.documentElement.clientWidth; 
		} else {
			windowWidth = self.innerWidth;
		}
		windowHeight = self.innerHeight;
	} else if (document.documentElement && document.documentElement.clientHeight) { 
		windowWidth = document.documentElement.clientWidth;
		windowHeight = document.documentElement.clientHeight;
	} else if (document.body) { 
		windowWidth = document.body.clientWidth;
		windowHeight = document.body.clientHeight;
	}	
	if(yScroll < windowHeight){
		pageHeight = windowHeight;
	} else { 
		pageHeight = yScroll;
	}
	if(xScroll < windowWidth){	
		pageWidth = xScroll;		
	} else {
		pageWidth = windowWidth;
	}
	arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight);
	return arrayPageSize;
};
function forward(id,picurl,name,content){
	this.id = id;
	this.picurl = picurl;
	this.name = name;
	this.content = content;
}
forward.prototype = {
	getHtml : function(){
		this.html = [];
		this.html.push('<table style="margin: 100px auto;" width="487" border="0" align="center" cellpadding="0" cellspacing="0">');
  		this.html.push('<tr><td width="487"><img src="images/myhome_34.gif" width="523" height="6" /></td></tr>');
  		this.html.push('<tr>');
    	this.html.push('<td height="164" align="center" valign="top" background="images/myhome_37.gif"><table width="510" height="15" border="0" cellpadding="0" cellspacing="0">');
      	this.html.push('<tr><td width="32" height="15">&nbsp;</td><td width="279">&nbsp;</td><td width="199">&nbsp;</td></tr>');
    	this.html.push('</table>');
      	this.html.push('<table width="510" border="0" cellspacing="0" cellpadding="2">');
        this.html.push('<tr>');
        this.html.push('<td width="45" height="50" align="center" valign="middle"><img src="images/myhome_39.gif" width="38" height="38" /></td>');
        this.html.push('<td width="457" align="left" valign="top"><table width="432" height="46" border="0" cellpadding="0" cellspacing="0">');
        this.html.push('<tr><td width="432" height="26">' + this.name + '</td></tr>');
        this.html.push('<tr>');
        this.html.push('<td height="20" align="left">');
        this.html.push('<table width="432" border="0" cellspacing="2" cellpadding="2">');
        this.html.push('<tr><td width="300">' + this.content + '</td><td width="70">&nbsp;</td><td width="42">&nbsp;</td></tr></table></td></tr>');
        this.html.push('</table></td>');
        this.html.push('</tr>');
        this.html.push('</table>');
      	this.html.push('<table width="510" border="0" cellspacing="0" cellpadding="0">');
        this.html.push('<tr>');
        this.html.push('<td height="74" colspan="2" align="center" valign="top"><form action="" onsubmit="return false" method="post" enctype="multipart/form-data" name="hueifu" id="hueifu2">');
        this.html.push('<table width="493" border="0" cellspacing="4" cellpadding="0">');
        this.html.push('<tr>');
        this.html.push('<td height="62" colspan="2" align="center" valign="top"><label></label>');
        this.html.push('<label>');
        this.html.push('<textarea id="forward-msg" name="textarea2" cols="45" rows="5" class="tabledao" id="textarea2" style="background-image:url(images/myhome_42.gif);border:0; width:495px; height:53px;overflow : hidden">' + this.content.replace(/<[^>].*?>/g,"") + '</textarea>');
        this.html.push('</label></td>');
        this.html.push('</tr>');
        this.html.push('<tr>');
        this.html.push('<td width="365" height="22" align="left" valign="top"><label></label></td>');
        this.html.push('<td width="220"><input id="forward-button" type="image" src="images/1134.gif" href="images/myhome_11.gif" />&nbsp;&nbsp;<input type="image" href="images/myhome_11.gif" src="images/pinglun_181.gif" id="button-no"></td>');
        this.html.push('</tr>');
        this.html.push('</table>');
        this.html.push('</form></td>');
        this.html.push('</tr>');
      	this.html.push('</table></td>');
  		this.html.push('</tr>');
  		this.html.push('<tr><td><img src="images/myhome_45.gif" width="523" height="7" /></td></tr>');
		this.html.push('</table>');
		return this.html.join('');
	}
}
function mask(html,fn,s){
	if(typeof s == 'undefined'){
		s = true;
	}
	$('body').append('<div id="mask-overlay"></div><div id="mask-forward">' + html + '</div>').css({
		overflow : 'hidden'
	});
	var arrPageSizes = getPageSize();
	var arrPageScroll = getPageScroll();
	$('#mask-forward').css({
		width: arrPageSizes[0]
		,position:'absolute'
		,textAlign : 'center'
		,marginTop : arrPageScroll[1] + (arrPageSizes[3] / 10)
		,top: 0
		,left: 0
	}).hide();
	$('#mask-overlay').css({
		backgroundColor:'#000'
		,opacity:0.5
		,width:arrPageSizes[0]
		,height:arrPageSizes[1]
		,left:0
		,top:0
		,position:'absolute'
	}).fadeIn(function(){
		$('#mask-forward').fadeIn()
	});
	if(s){
		$('#mask-overlay').click(maskHide);
	}
	$('#forward-button').click(fn)
	$('#button-no').click(maskHide);
}
function maskHide(){
	$('body').css({
		overflow : 'auto'
	});
	$('#mask-forward').fadeOut(function(){
		$(this).remove();
	})
	$('#mask-overlay').fadeOut(function(){
		$(this).remove();
	});
}
function getUserGender(sex){
	switch(sex){
		case 0:
			sex = '性别：女';
			break;
		case 1 : 
			sex = '性别：男';
			break;
		default :
			sex = '性别：保密';
			
	}
	return sex;
}
function getUserBirthday(btd){
	var str = '保密';
	if(btd && btd != 'null' && btd != ''){
		var year = (new Date()).getFullYear();
		str = year - (btd.split('-'))[0];
	}
	return '年龄：' + str;
}
function getUserAddress(adr){
	var str = '保密';
	if(adr && adr != 'null' && adr != ''){
		str = adr;
	}
	return '城市：' + str;
}
var Form = {
	valid : []
	,isValid : function(){
		for(var name in Form.valid){
			if(!Form.valid[name]){
				return false;
			}
		}
		return true;
	}
	,add : function(id, ajax, ftext, ttext){
		ftext = ftext || '';
		ttext = ttext || '';
		Form.valid[id] = false;
		$('#' + id).blur(function(){
			ajax($(this).val(), function(result){
				switch($('#' + id).get(0).type){
					case 'password':
					case 'text':
						Form.text.call(this, id, result, ftext);
						break;
				}
			});
		});
		$('#' + id).focus(function(){
			$('#' + id).css({
				backgroundColor:'#F4FFD4'
				,borderColor:'#A5C760'
			});
			if($('#' + id + '_info')){
				$('#' + id + '_info').css({
					color : '#A5C760' 
					,fontSize : '13px' 
				});
			}
			$('#' + id + '_info').html(ttext);
		});
		if($('#' + id + '_info')){
			$('#' + id + '_info').html(ttext);
		};
	}
	,text : function(id, result, ftext){
		if(!result){
			Form.valid[id] = false;
			$('#' + id).css({
				backgroundColor:'#FFCCCC'
				,borderColor:'#FF0000'
			})
			$('#' + id + '_info').html(ftext);
			$('#' + id + '_info').css({
				color : '#FF0000'
				,fontSize : '13px'
			});
		}else{
			Form.valid[id] = true;
			$('#' + id + '_info').css({
				color : '#A5C760'
				,fontSize : '13px' 
			});
			$('#' + id + '_info').html('正确');
		}
	}
}
var msg = {
	alert : function(text,fn){
		fn = fn || function(){};
		var html = [];
		html.push('<table style="margin: 100px auto;" width="316" border="0" cellspacing="0" cellpadding="0">');
        html.push('<tr>');
        html.push('<td><img src="images/pinglun_13.gif" width="316" height="6" /></td>');
        html.push('</tr>');
        html.push('<tr>');
        html.push('<td height="69" align="left" valign="top" background="images/pinglun_15.gif"><table width="314" border="0" cellspacing="0" cellpadding="0">');
        html.push('<tr>');
        html.push('<td width="314" height="40" align="center" valign="middle"><h2 class="STYLE5">' + text + '</h2></td>');
        html.push('</tr>');
        html.push('<tr>');
        html.push('<td height="23" align="center"><input type="image" href="images/myhome_11.gif" src="images/pinglun_18.gif" id="forward-button"></td>');
        html.push('</tr>');
        html.push('</table></td>');
        html.push('</tr>');
        html.push('<tr>');
        html.push('<td><img src="images/pinglun_21.gif" width="316" height="5" /></td>');
        html.push('</tr>');
       	html.push('</table>');
       	
       	mask(html.join(''),function(){
       		maskHide();
       		fn();
		},false);
	}
	,confirm : function(text,fn){
		var html = [];
		html.push('<table style="margin: 100px auto;" width="316" border="0" cellspacing="0" cellpadding="0">');
        html.push('<tr>');
        html.push('<td><img src="images/pinglun_13.gif" width="316" height="6" /></td>');
        html.push('</tr>');
        html.push('<tr>');
        html.push('<td height="69" align="left" valign="top" background="images/pinglun_15.gif"><table width="314" border="0" cellspacing="0" cellpadding="0">');
        html.push('<tr>');
        html.push('<td width="314" height="40" align="center" valign="middle"><h2 class="STYLE5">' + text + '</h2></td>');
        html.push('</tr>');
        html.push('<tr>');
        html.push('<td height="23" align="center">');
        html.push('<input type="image" href="images/myhome_11.gif" src="images/pinglun_18.gif" id="forward-button">');
        html.push('&nbsp;&nbsp;');
        html.push('<input type="image" href="images/myhome_11.gif" src="images/pinglun_181.gif" id="button-no">');
        html.push('</td>');
        html.push('</tr>');
        html.push('</table></td>');
        html.push('</tr>');
        html.push('<tr>');
        html.push('<td><img src="images/pinglun_21.gif" width="316" height="5" /></td>');
        html.push('</tr>');
       	html.push('</table>');
       	
       	mask(html.join(''),function(){
       		maskHide();
       		fn();
		},false);
       	$('#button-no').click(function(){
       		maskHide();
       	});
	}
}
function doDlinfo(){
	DaolifeAjax.getMessageStatistics(function(rs){
		var html = [];
		html.push('<table width="68" border="0" cellspacing="0" cellpadding="0">');
		if(rs){
			if(rs.length){
				for(var i = 0, l = rs.length; i < l; ++i){
					var str = '';
					switch(parseInt(rs[i][1])){
						case 0:
							str = '<a href="atMe.jsp">有' + rs[i][0] + '条叨@您</a>';
							break;
						case 2:
							str = '<a href="attentionMe.jsp">有' + rs[i][0] + '条关注您</a>';
							break;
						default:
							str = '&nbsp;';
					}
					html.push('<tr><td align="center">' + str + '</td></tr>');
				}
			}else{
				html.push('<tr><td align="center">没有新信息</td></tr>');
			}
		}else{
			html.push('<tr><td align="center">没有新信息</td></tr>');
		}
		html.push('</table>');
		$('#dlinfo').get(0).innerHTML = html.join(''); 
		setTimeout('doDlinfo()',10000);
	});
}
function personal(mid,pid,name,am,ma,dao,introduction,blog,about,attention){
	this.mid = mid;
	this.name = name;
	this.pid = pid;
	this.am = am;
	this.ma = ma;
	this.dao = dao;
	this.introduction = introduction;
	this.blog = blog;
	this.about = about;
	this.attention = attention;
}
function myDao(){
	if($('#articleandreply').get(0)){
		doStatus(2);
	}else{
		window.location.href="MyPage.action?callbackFunc=doStatus(2)";
	}
}
personal.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<table width="318" height="58" border="0" align="center" cellpadding="0" cellspacing="0" class="xia1">');
		html.push('<tr><td width="250" class="Box1">' + this.name + '</td>');
		html.push('<td width="0" align="left" valign="middle">&nbsp;</td>');
		html.push('<td align="left" valign="middle">');
		if(this.mid != this.pid){
			if(this.attention){
				html.push('<a href="javascript:doFollow(' + this.pid + ',' + !this.attention + ')">取消关注</a>');
			}else{
				html.push('<a href="javascript:doFollow(' + this.pid + ',' + !this.attention + ')">关注</a>');
			}
		}else{
			html.push('<a href="Logout.action">退出登陆</a>');
		}
		html.push('</td>');
		html.push('</tr></table>');
		html.push('<table width="82%" border="0" align="center" cellpadding="0" cellspacing="0" class="yonghu"><tr>');
		html.push('<td width="32%" align="center">');
		html.push('<a href="touxiang.html"><img src="images/1_10.gif" width="113" height="112" /></a>');
		html.push('</td>');
		html.push('<td width="68%" align="center" valign="top">');
		if(this.mid == this.pid){
			html.push('<table width="193" height="52" border="0" cellpadding="0" cellspacing="0" class="xia1">');
			html.push('<tr>');
			html.push('<td width="76" height="33" align="center" class="STYLE4 STYLE7 STYLE5 STYLE6">');
			html.push('<a href="myAttention.jsp">' + this.ma + '</a>');
			html.push('</td>');
			html.push('<td width="76" align="center" class="STYLE4 STYLE7 STYLE5 STYLE6">');
			html.push('<a href="attentionMe.jsp">' + this.am + '</a>');
			html.push('</td>');
			html.push('<td width="78" align="center" class="STYLE4 STYLE7 STYLE5 STYLE6">');
			html.push('<a href="javascript:myDao()">' + this.dao + '</a>');
			html.push('</td>');
			html.push('</tr>');
			html.push('<tr>');
			html.push('<td align="center">');
			html.push('<a href="myAttention.jsp">我关注</a>');
			html.push('</td>');
			html.push('<td align="center">');
			html.push('<a href="attentionMe.jsp">关注我</a>');
			html.push('</td>');
			html.push('<td align="center">');
			html.push('<a href="javascript:myDao()">叨</a>');
			html.push('</td>');
			html.push('</tr>');
			html.push('</table>');
		}else{
			html.push('<table width="193" height="52" border="0" cellpadding="0" cellspacing="0" class="xia1">');
			html.push('<tr>');
			html.push('<td width="76" height="33" align="center" class="STYLE4 STYLE7 STYLE5 STYLE6">');
			html.push('<a href="pesonalAttention.jsp?userId=' + this.pid + '">' + this.ma + '</a>');
			html.push('</td>');
			html.push('<td width="76" align="center" class="STYLE4 STYLE7 STYLE5 STYLE6">');
			html.push('<a href="attentionPesonal.jsp?userId=' + this.pid + '">' + this.am + '</a>');
			html.push('</td>');
			html.push('<td width="78" align="center" class="STYLE4 STYLE7 STYLE5 STYLE6">');
			html.push('<a href="javascript://">' + this.dao + '</a>');
			html.push('</td>');
			html.push('</tr>');
			html.push('<tr>');
			html.push('<td align="center">');
			html.push('<a href="pesonalAttention.jsp?userId=' + this.pid + '">我关注</a>');
			html.push('</td>');
			html.push('<td align="center">');
			html.push('<a href="attentionPesonal.jsp?userId=' + this.pid + '">关注我</a>');
			html.push('</td>');
			html.push('<td align="center">');
			html.push('<a href="javascript://">叨</a>');
			html.push('</td>');
			html.push('</tr>');
			html.push('</table>');
		}
		html.push('<table width="200" height="66" border="0" align="center" cellpadding="0" cellspacing="4">');
		html.push('<tr>');
		html.push('<td width="51" align="right">简介：</td>');
		html.push('<td width="143" align="left">' + this.introduction + '</td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td align="right">博客：</td>');
		html.push('<td align="left">' + this.blog + '</td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td align="right">关于他：</td>');
		html.push('<td align="left">' + this.about + '</td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('</td>');
		html.push('</tr>');
		html.push('</table>');
		if(this.mid == this.pid){
			html.push('<div class="shezhi">');
			html.push('<table width="138" border="0" cellspacing="3" cellpadding="3">');
			html.push('<tr>');
			html.push('<td width="54"><a href="javascript://">我的设置</a></td>');
			html.push('<td width="63"><a href="javascript://">我的私信</a></td>');
			html.push('</tr>');
			html.push('<tr>');
			html.push('<td>');
			html.push('<a href="atMe.jsp">﹫我的</a>');
			html.push('</td>');
			html.push('<td><a href="javascript://">&nbsp;</a></td>');
			html.push('</tr>');
			html.push('</div>');
		}
		html.push('</table>');
		return html.join('');
	}
}
function logon(){}
logon.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<div class="Box">登陆</div>');
		html.push('<div class="zhuce">');
		html.push('<form id="zhuce" name="zhuce" method="post" action="Login.action">');
		html.push('<table width="266" border="0" cellspacing="5" cellpadding="0">');
		html.push('<tr>');
		html.push('<td height="36" background="images/zhuce.gif">');
		html.push('<label>&nbsp;账号：<input name="userName" type="text" id="zhanghao" size="25" maxlength="45" style="border: 0;" /></label>');
		html.push('</td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td height="36" background="images/zhuce.gif">&nbsp;密码：<input name="password" type="password" id="zhanghao" size="25" maxlength="45" style="border: 0;" />');
		html.push('</td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td height="36" align="center">');
		html.push('<table width="98%" border="0" cellspacing="0" cellpadding="0">');
		html.push('<tr>');
		html.push('<td height="32">');
		html.push('<table width="95%" border="0" cellspacing="0" cellpadding="0">');
		html.push('<tr>');
		html.push('<td width="24%" height="28" align="center">');
		html.push('<label><input type="checkbox" name="checkbox" id="checkbox" /></label>');
		html.push('</td>');
		html.push('<td width="76%">记住登陆状态</td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('</td>');
		html.push('<td height="32" align="center">');
		html.push('<a href="findPassword.jsp">找回密码</a>');
		html.push('</td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td height="32">');
		html.push('<a href="regist.jsp">');
		html.push('<img src="images/zuce1.gif" width="122" height="32" />');
		html.push('</a>');
		html.push('</td>');
		html.push('<td height="32">');
		html.push('<a href="javascript://"><input type="image" src="images/denglu.gif" /></a>');
		html.push('</td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('</td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('</form>');
		html.push('</div>');
		return html.join('');
	}
}
function doPersonal(id){
	id = id || null;
	DaolifeAjax.getUserInfo(id,function(rs){
		var html = '';
		if(rs){
			var i = '';
			switch(rs[2].userGender){
				case 1:
					i += '男';
					break;
				case 2:
					i += '女';
					break;
			}
			if(rs[2].userAddress){
				i += ' ' + rs[2].userAddress;
			}
			if(i == ''){
				i = '无';
			}
			//var i = getUserGender(rs[2].userGender) + ' | ' + getUserBirthday(rs[2].birthday) + '|' + getUserAddress(rs[2].userAddress);
			var pp = new personal(rs[0],rs[1],rs[2].userNickName,rs[2].fansNum,rs[2].followNum,rs[2].contentsSize,i,'无',rs[2].userInfo,rs[2].followFlag);
			html = pp.getHtml();
		}else{
			html = (new logon()).getHtml();
		}
		$('#personal').get(0).innerHTML = html;
	});
}
function getQueryString(name){     
	var reg = new   RegExp("(^|&)" + name + "=([^&]*)(&|$)");     
	var r = window.location.search.substr(1).match(reg);     
	if (r!=null){
		return unescape(r[2]);
	}else{
		return null;
	}     
}    
$(function($){
	doDlinfo();
})