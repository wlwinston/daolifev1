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
		html.push('<tr><td align="center" valign="middle" background="images/daohot_19.gif"><span class="STYLE2">1</span></td></tr>');
		html.push('</table>');
		html.push('</td>');
		html.push('<td width="92" rowspan="4" align="left" valign="top">');
		html.push('<a href="touxiang.html"><img src="' + this.picurl + '" width="78" height="77" /></a>');
		html.push('</td>');
		html.push('<td width="367">' + this.name + '</td>');
		html.push('<td width="150" rowspan="4">&nbsp;</td>');
		html.push('<td width="149" rowspan="2" align="center">');
		html.push('<img src="images/daohot_13.gif" width="61" height="24" />');
		html.push('</td>');
		html.push('</tr>');
		html.push('<tr><td>' + this.content + '</td></tr>');
		html.push('<tr>');
		html.push('<td>' + this.baseinfo + '</td>');
		html.push('<td width="149" align="center"><a href="#">取消关注</a></td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td height="14">' + this.otherinfo + '</td>');
		html.push('<td width="149" align="center">共' + this.people + '人关注</td>');
		html.push('</tr>');
		html.push('</table>');
		return html.join('');
	}
}
function friendsHotBox(){
	this.element = [];
	this.totalCount = 1;
	this.pageCount = 5;
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
	}
	,setpage : function(totalCount,pageCount,currentPage){
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
	}
	,getpagebar : function(){
		var html = [];
		html.push('<div class="yem1"><ul>');
		if(this.pageCount > 1){
			for(var i = 0, l = this.pageCount; i < l; ++i){
				html.push('<li><a href="javascript://">' + (i + 1) + '</a></li>');
			}
		}
		html.push('</ul></div>')
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
$(function($){
	var fh = new friendsHot(1,1,'zhouy','拒绝歧视非主流','images/myhome_30.gif','男 26岁','北京市 设计师',true,12345);
	var fhb = new friendsHotBox();
	for(var i = 1, l = 6; i < l; ++i){
		fhb.add(new friendsHot(1,1,'zhouy','拒绝歧视非主流','images/myhome_30.gif','男 26岁','北京市 设计师',true,12345));
	}
	$('#box').get(0).innerHTML = fhb.getHtml();
});