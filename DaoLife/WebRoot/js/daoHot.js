function daoHot(id,uid,index,name,content,picurl,forwardAmount,dingAmount,replyNum){
	this.id = id;
	this.uid = uid;
	this.index = index;
	this.name = name;
	this.content = content;
	this.picurl = picurl;
	this.forwardAmount = forwardAmount;
	this.dingAmount = dingAmount;
	this.replyNum = replyNum;
}
daoHot.prototype = {
	getHtml : function(){
		return '<div id="daohot_' + this.id + '">' + this.getElement() + '</div>';	
	}
	,getElement : function(){
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
		html.push('<a href="PersonPage.action?userId=' + this.uid + '"><img src="' + this.picurl + '" width="78" height="77" /></a>');
		html.push('</td>');
		html.push('<td width="367" align="left"><a href="PersonPage.action?userId=' + this.uid + '">' + this.name + '</a></td>');
		html.push('<td width="150" rowspan="4">&nbsp;</td>');
		html.push('<td width="149" rowspan="2" align="center">&nbsp;</td>');
		html.push('</tr>');
		html.push('<tr><td align="left">' + this.content + '</td></tr>');
		html.push('<tr><td>&nbsp;</td><td width="149" align="center">&nbsp;</td></tr>');
		html.push('<tr><td height="14">&nbsp;</td><td width="300" align="right"><a href="javascript:doDaoReply(' + this.id + ')">回复（' + this.replyNum + '）</a>&nbsp;&nbsp;<a href="javascript:doForward(' + this.id + ')">转发（' + this.forwardAmount + '）</a>&nbsp;&nbsp;<a href="javascript:doDing(' + this.id + ')">顶他（' + this.dingAmount + '）</a></td></tr>');
		html.push('</table>');
		return html.join('');
	}
	,reload : function(){
		//this.isDingValid = false;
		var myself = this;
		DaolifeAjax.getDlContentById(this.id,function(rs){
			myself.forwardAmount = rs.retwittNum;
			myself.dingAmount = rs.upNum; 
			myself.replyNum = rs.replyNum;
			$('#daohot_' + myself.id).get(0).innerHTML = myself.getElement();
		});
	}
}
function daoHotBox(){
	this.element = [];
	this._element = [];
	this.totalCount = 1;
	this.pageCount = 1;
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
		this._element[article.id] = article;
	}
	,getElementById : function(id){
		if(this._element[id]){
			return this._element[id];
		}
		return null;
	}
	,setpage : function(totalCount,pageCount,currentPage){
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
	}
	,clean : function(){
		this.element = [];
		this._element = [];
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
		$('#daohotbox').empty();
		var html = [];
		for(var i = 0, l = this.element.length; i < l ; ++i){
			html.push(this.element[i].getHtml());
		}
		$('#daohotbox').get(0).innerHTML = html.join('') + this.getpagebar();
		$('#daohotbox').hide().fadeIn(1100);
	}
}
var myBox = function(){}
myBox.articleBox = new daoHotBox();
function doPage(page){
	goTop();
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
			myBox.articleBox.add(new daoHot(rs.items[i].hotdaoId,rs.items[i].userId,((rs.currentPage - 1) * rs.pageSize) + (i + 1),rs.items[i].dlUsers.userNickName,rs.items[i].contentBody,'images/myhome_30.gif',rs.items[i].retwittNum,rs.items[i].upSum,rs.items[i].replyNum));
		}
		if(fn){
			fn();
		}
	}
	DaolifeAjax.getHotDao(myBox.articleBox.currentPage,func);
}
function doDaoReply(id){
	myBox.replyBox[id] = new replyBox(id);
	mask(myBox.replyBox[id].getHtml(),function(){},false);
	var fn = function(obj){
		doReply(obj.data.id,function(){
			myBox.replyBox[id].initPage(function(){
				$('#replymsg_' + id).val('');
				$('#replybutton_' + id).html('<a href="javascript://" id="replybox-button-submit-' + id + '"><img src="images/myhome_46.gif" /></a>&nbsp;&nbsp;<input type="image" href="images/myhome_11.gif" src="images/pinglun_181.gif" id="button-no">');
				$('#replybox-button-submit-' + id).bind('click',{id:id},fn);
				$('#button-no').bind('click',maskHide);
				myBox.articleBox.getElementById(id).reload();
			});
		});
	}
	$('#replybox-button-submit-' + id).bind('click',{id:id},fn);
	myBox.replyBox[id].initPage();
}
myBox.replyBox = [];
function doForward(id){
	var item =myBox.articleBox.getElementById(id);
	if(item){
		var rf = new forward(item.id,item.picurl,item.name,item.content);
		mask(rf.getHtml(),function(){
			DaolifeAjax.addRetwitteDao($('#forward-msg').val(),id,function(rs){
				if(rs){
					maskHide();
					myBox.articleBox.getElementById(id).reload();
				}else{
					maskHide();
					alert('请先登录后在执行此操作！');
				}
			})
		});
	}
}
function doDing(id){
	DaolifeAjax.upDao(id,function(rs){
		if(rs){
			alert(rs);
		}else{
			myBox.articleBox.getElementById(id).reload();
		}
	});
}
function doDaoReply(id){
	myBox.replyBox[id] = new replyBox(id);
	mask(myBox.replyBox[id].getHtml(),function(){},false);
	var fn = function(obj){
		doReply(obj.data.id,function(){
			myBox.replyBox[id].initPage(function(){
				$('#replymsg_' + id).val('');
				$('#replybutton_' + id).html('<a href="javascript://" id="replybox-button-submit-' + id + '"><img src="images/myhome_46.gif" /></a>&nbsp;&nbsp;<input type="image" href="images/myhome_11.gif" src="images/pinglun_181.gif" id="button-no">');
				$('#replybox-button-submit-' + id).bind('click',{id:id},fn);
				$('#button-no').bind('click',maskHide);
				myBox.articleBox.getElementById(id).reload();
			});
		});
	}
	$('#replybox-button-submit-' + id).bind('click',{id:id},fn);
	myBox.replyBox[id].initPage();
}
myBox.replyBox = [];
$(function($){
	doReload(function(){
		myBox.articleBox.load();
	})
	$('#box').get(0).innerHTML = myBox.articleBox.getHtml();
});