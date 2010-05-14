function friendsHot(id,index,name,content,picurl,baseinfo,otherinfo,attention,people){
	this.id = id;
	this.index = index;
	this.name = name;
	this.content =content;
	this.picurl = picurl;
	this.baseinfo =baseinfo;
	this.otherinfo = otherinfo;
	this.attention = attention;
	this.people = people;
}
friendsHot.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<table width="830" border="0" align="center" cellspacing="2" class="guanzhu1">');
		html.push('<tr>');
		html.push('<td width="42" height="95" rowspan="4" align="left" valign="top">');
		html.push('<table width="24" height="24" border="0" cellspacing="0" cellpadding="0">');
		html.push('<tr><td align="center" valign="middle" background="images/daohot_19.gif"><span class="STYLE2">' + this.index + '</span></td></tr>');
		html.push('</table>');
		html.push('</td>');
		html.push('<td width="92" rowspan="4" align="left" valign="top">');
		html.push('<a href="PersonPage.action?userId=' + this.id + '"><img src="' + this.picurl + '" width="78" height="77" /></a>');
		html.push('</td>');
		html.push('<td width="367" align="left"><a href="PersonPage.action?userId=' + this.id + '">' + this.name + '</a></td>');
		html.push('<td width="150" rowspan="4">&nbsp;</td>');
		html.push('<td width="149" rowspan="2" align="center">');
		html.push('<img src="images/daohot_13.gif" width="61" height="24" />');
		html.push('</td>');
		html.push('</tr>');
		html.push('<tr><td align="left">' + this.content + '</td></tr>');
		html.push('<tr>');
		html.push('<td align="left">' + this.baseinfo + '</td>');
		html.push('<td width="149" align="center"><a href="#">取消关注</a></td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td height="14" align="left">' + this.otherinfo + '</td>');
		html.push('<td width="149" align="center">共' + this.people + '人关注</td>');
		html.push('</tr>');
		html.push('</table>');
		return html.join('');
	}
}
function friendsHotBox(){
	this.element = [];
	this.totalCount = 1;
	this.pageCount = 1;
	this.currentPage = 1;
}
friendsHotBox.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<div class="hang" id="friendshotbox">');
		for(var i = 0, l = this.element.length; i < l; ++i){
			html.push(this.element[i].getHtml());
		}
		html.push(this.getpagebar());
		html.push('</div>');
		return html.join('');
	}
	,add : function(article){
		this.element.push(article);
		this._element[article.id] = article;
	}
	,getElementById : function(id){
		if(this._element[id]){
			return this._element[id];
		}
		return null;
	}
	,clean : function(){
		this.element = [];
		this._element = [];
	}
	,setpage : function(totalCount,pageCount,currentPage){
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
	}
	,getpagebar : function(){
		var html = [];
		html.push('<div class="yem1"><center><ul>');
		if(this.pageCount > 1){
			for(var i = 0, l = this.pageCount; i < l; ++i){
				html.push('<li><a href="javascript:doPage(' + (i+1) + ')">' + (i + 1) + '</a></li>');
			}
		}
		html.push('</ul></center></div>')
		return html.join('');
	}
	,load : function(){
		$('#friendshotbox').empty();
		var html = [];
		for(var i = 0, l = this.element.length; i < l ; ++i){
			html.push(this.element[i].getHtml());
		}
		$('#friendshotbox').get(0).innerHTML = html.join('') + this.getpagebar();
		$('#friendshotbox').hide().fadeIn(1100);
	}
}
var myBox = function(){}
myBox.articleBox = new friendsHotBox();
function doPage(page){
	myBox.articleBox.currentPage = page;
	doReload(function(){
		myBox.articleBox.load();
	});
}
function doReload(fn){
	myBox.articleBox.clean();
	var func = function(rs){
		myBox.articleBox.setpage(rs.totalCount,rs.pageCount,rs.currentPage);
		for(var i = 0, l = rs.items.length; i < l; ++i){
			var baseinfo = getUserGender(rs.items[i].userGender) + ' | ' + getUserBirthday(rs.items[i].birthday);
			var otherinfo = getUserAddress(rs.items[i].userAddress);
			myBox.articleBox.add(new friendsHot(rs.items[i].userId,((rs.currentPage - 1) * rs.pageCount) + (i + 1),rs.items[i].userNickName,rs.items[i].userInfo,'images/myhome_30.gif',baseinfo,otherinfo,rs.items[i].followFlag,rs.items[i].fansNum));
		}
		if(fn){
			fn();
		}
	}
	DaolifeAjax.getMyFanFriend(myBox.articleBox.currentPage,func);
}
$(function($){
	doReload(function(){
		myBox.articleBox.load();
	})
	$('#box').get(0).innerHTML = myBox.articleBox.getHtml();
});