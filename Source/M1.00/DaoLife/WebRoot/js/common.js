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
        this.html.push('<tr><td width="300">' + this.content + '</td><td width="70">回复</td><td width="42">删除</td></tr></table></td></tr>');
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
        this.html.push('<td width="370" height="22" align="left" valign="top"><label></label></td>');
        this.html.push('<td width="123"><input id="forward-button" type="image" src="images/1134.gif" href="images/myhome_11.gif" /></td>');
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
		,opacity:0.8
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
$(function($){
	doDlinfo();
})