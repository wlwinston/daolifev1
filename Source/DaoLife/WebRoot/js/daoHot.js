function daoHot(id,index,name,content,picurl){
	this.id = id;
	this.index = index;
	this.name = name;
	this.content = content;
	this.picurl = picurl;
}
daoHot.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<table width="830" border="0" align="center" cellspacing="2"class="guanzhu1">');
		html.push('<tr>');
		html.push('<td width="42" height="95" rowspan="4" align="left" valign="top">');
		html.push('<table width="24" height="24" border="0" cellspacing="0" cellpadding="0">');
		html.push('<tr>');
		html.push('<td align="center" valign="middle" background="images/daohot_19.gif">');
		html.push('<span class="STYLE2">' + this.index + '</span>');
		html.push('</td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('</td>');
		html.push('<td width="92" rowspan="4" align="left" valign="top">');
		html.push('<a href="touxiang.html"><img src="' + this.picurl + '" width="78" height="77" /></a>');
		html.push('</td>');
		html.push('<td width="367">' + this.name + '</td>');
		html.push('<td width="150" rowspan="4">&nbsp;</td>');
		html.push('<td width="149" rowspan="2" align="center">&nbsp;</td>');
		html.push('</tr>');
		html.push('<tr><td>' + this.content + '</td></tr>');
		html.push('<tr><td>&nbsp;</td><td width="149" align="center">&nbsp;</td></tr>');
		html.push('<tr><td height="14">&nbsp;</td><td width="149" align="center"><a href="javascript://">回复</a>&nbsp;&nbsp;<a href="javascript:doForward(' + this.id + ',\'' + this.picurl + '\',\'' + this.name + '\',\'' + this.content + '\')">转发</a>&nbsp;&nbsp;<a href="javascript://">顶他</a></td></tr>');
		html.push('</table>');
		return html.join('');
	}
}
function daoHotBox(){
	this.element = [];
	this.totalCount = 1;
	this.pageCount = 5;
	this.currentPage = 1;
}
daoHotBox.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<div class="hang" id="daohotbox">');
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
		$('#daohotbox').empty();
		var html = [];
		for(var i = 0, l = this.element.length; i < l ; ++i){
			html.push(this.element[i].getHtml());
		}
		$('#daohotbox').get(0).innerHTML = html.join('') + this.getpagebar();
		$('#daohotbox').hide().fadeIn(1100);
	}
}
$(function($){
	var dh = new daoHot(1,1,'zhouy','拒绝歧视非主流','images/myhome_30.gif');
	var dhb = new daoHotBox();
	for(var i = 1, l = 6; i < l; ++i){
		dhb.add(new daoHot(i,i,'zhouy','拒绝歧视非主流','images/myhome_30.gif'));
	}
	$('#box').get(0).innerHTML = dhb.getHtml();
});