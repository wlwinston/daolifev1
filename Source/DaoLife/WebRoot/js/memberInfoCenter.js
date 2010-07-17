//回复单条数据
function reply(id,toid,name,content,time,picurl){
	this.id = id;
	this.toid = toid;
	this.name = name;
	this.content = content;
	this.time = time;
	this.picurl =picurl;
}
reply.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<table id="reply_' + this.id + '" width="510" border="0" cellspacing="0" cellpadding="2">');
		html.push('<tr>');
		html.push('<td width="45" height="50" align="left" valign="top"><a href="PersonPage.action?userId=' + this.id + '"><img src="' + this.picurl + '" width="38" height="38" /></a></td>');
		html.push('<td width="457" align="left" valign="top">');
		html.push('<table width="432" height="46" border="0" cellpadding="0" cellspacing="0">');
		html.push('<tr>');
		html.push('<td width="432" height="26"><a href="PersonPage.action?userId=' + this.id + '">' + this.name + '</a></td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td height="20" align="left">');
		html.push('<table width="432" border="0" cellspacing="2" cellpadding="2">');
		html.push('<tr>');
		html.push('<td width="300">' + this.content + '</td>');
		html.push('<td width="70">回复</td><td width="42">删除</td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('</td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('</td>');
		html.push('</tr>');
		html.push('</table>');
		return html.join('');
	}
}
//回复集合		
function replyBox(id){
	this.id = id;
	this.element = [];
	this._element = [];
	this.replyid = null;
}
replyBox.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<table id="replybox_' + this.id+ '" width="487" border="0" cellspacing="0" cellpadding="0" style="display:none;margin-bottom:10px;">');
		html.push('<tr><td width="487"><img src="images/myhome_34.gif" width="523" height="6" /></td></tr>');
		html.push('<tr>');
		html.push('<td align="center" valign="top" background="images/myhome_37.gif">');
		html.push('<table width="510" height="15" border="0" cellpadding="0" cellspacing="0">');
		html.push('<tr>');
		html.push('<td width="255">共有评论28条 您现在查看的是1-10条</td><td width="268">前十条 上一页 1 2 3 下一页 后十条</td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('<div id="replylist_' + this.id + '">');
		for(var i = 0, l = this.element.length; i < l; ++i){
			html.push(this.element[i].getHtml());
		}
		html.push('</div>');
		html.push('<table width="510" border="0" cellspacing="0" cellpadding="0">');
		html.push('<tr>');
		html.push('<td height="74" colspan="2" align="center" valign="top">');
		html.push('<form action="" method="post" enctype="multipart/form-data" name="hueifu" id="hueifu">');
		html.push('<table width="493" border="0" cellspacing="4" cellpadding="0">');
		html.push('<tr>');
		html.push('<td height="62" colspan="2" align="center" valign="top">');
		html.push('<label>');
		html.push('<textarea name="textarea" cols="45" rows="5" class="tabledao" style="background-image: url(images/myhome_42.gif); border: 0; width: 495px; height: 53px;" id="replymsg_' + this.id + '"></textarea>');
		html.push('</label>');
		html.push('</td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td width="370" height="22" align="left" valign="top">');
		html.push('</td>');
		html.push('<td width="90" align="center" id="replybutton_' + this.id + '"><a href="javascript:doReply(' + this.id + ')"><img src="images/myhome_46.gif" width="88" height="22" /></a></td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('</form>');
		html.push('</td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('</td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td>');
		html.push('<img src="images/myhome_45.gif" width="523" height="7" />');
		html.push('</td>');
		html.push('</tr>');
		html.push('</table>');
		return html.join('');
	}
	,add : function(reply){
		this.element.push(reply);
		this._element[reply.id] = reply;
	}
	,empty : function(){
		this.element = [];
		this._element = [];
	}
	,showList : function(){
		var html  = [];
		for(var i = 0, l = this.element.length; i < l; ++i){
			html.push(this.element[i].getHtml());
		}
		$('#replylist_' + this.id).get(0).innerHTML = html.join('');
		$('#replylist_' + this.id).hide().fadeIn(1100);
	}
	,getElementById : function(id){
		return this._element[id];
	}
}
function doReply(id){
	var rb = myBox.replyBox[id];
	var replyid = rb.reply ? rb.reply.id : null;
	if($('#replymsg_' + id).val() != '' && $('#replymsg_' + id).val() != ''){
		$('#replybutton_' + id).get(0).innerHTML = '<div style="height:22px;"><img src="images/floading.gif" /></div>';
		DaolifeAjax.addComment(id,$('#replymsg_' + id).val(),replyid,function(rs){
			$('#replybutton_' + id).get(0).innerHTML = '<a href="javascript:doReply(' + id + ')"><img src="images/myhome_46.gif" width="88" height="22" /></a>';
			DaolifeAjax.getDaoReply(1,id,function(data){
				alert(data.items.length);
				if(data){
					rb.empty();
					for(var i = 0, l = data.items.length; i < l; ++i){
						rb.add(new reply(data.items[i].commentId,data.items[i].commentId,data.items[i].dlUsers.userNickName,data.items[i].commentBody,data.items[i].posttime,'images/myhome_39.gif'));
					}
					rb.showList();	
				}
			});
			//for(var i = 0, l = rs.length; ++i){
				//rb.add(new reply());
			//}
		});
	}
}
//做回复
function doArticle(id){
	if($('#forwardbox_' + id).html()){
		$('#forwardbox_' + id).remove();
	}
	if($('#replybox_' + id).html()){
		$('#replybox_' + id).fadeOut(800,function(){
			$(this).remove();
		}); 
	}else{
		myBox.replyBox[id] = new replyBox(id);
		myBox.replyBox[id].add(new reply(1+id,id,'Tony','没妞怎么了','2020年7月17 17:24','images/myhome_39.gif'));
		myBox.replyBox[id].add(new reply(2+id,id,'Sam','有什么大不了的','2020年7月17 17:24','images/myhome_39.gif'));
		myBox.replyBox[id].add(new reply(3+id,id,'Sam','有什么大不了的','2020年7月17 17:24','images/myhome_39.gif'));
		myBox.replyBox[id].add(new reply(4+id,id,'Sam','有什么大不了的','2020年7月17 17:24','images/myhome_39.gif'));
		myBox.replyBox[id].add(new reply(5+id,id,'Sam','有什么大不了的','2020年7月17 17:24','images/myhome_39.gif'));
		$('#article_' + id).get(0).innerHTML += myBox.replyBox[id].getHtml();
		$('#replybox_' +id).fadeIn(1100);
	}
}
//发布的单条数据
function article(id,uid,name,content,picurl,replyAmount,forwardAmount,dingAmount,time,nowuid){
	this.id = id;
	this.uid = uid;
	this.name = name;
	this.content = content;
	this.picurl = picurl;
	this.replyAmount = replyAmount;
	this.forwardAmount = forwardAmount;
	this.dingAmount = dingAmount;
	this.isDingValid = true;
	this.time = time;
	this.nowuid =nowuid;
}
function doMouseover(id){
	$('#article_' + id).css('background','#F5F5F5');
}
function doMouseout(id){
	$('#article_' + id).css('background','#F8F8F8');
}
article.prototype = {
	getHtml : function(){
		return '<div id="article_' + this.id + '" style="width:540px;border-bottom:1px #D8D8D8 dashed;font-size:13px;height:auto;max-height:none;min-height:100px;overflow:hidden" onmouseover="doMouseover(' + this.id + ')" onmouseout="doMouseout(' + this.id + ')">' + this.getElement() + '</div>'
	}
	,getElement : function(){
		var html = [];
		html.push('<div style="width:530px;margin:10px 5px;height:auto;max-height:none;min-height:80px;overflow:hidden;text-align:left;">');
		html.push('<div style="width:80px;height:80px;float:left;padding:0px;border:1px #D8D8D8;"><a href="PersonPage.action?userId=' + this.uid + '"><img src="images/myhome_30.gif" width="80" height="80" /></a></div>');
		html.push('<div style="width:440px;float:right;padding-top:3px;line-height:19px;min-height:60px;">');
		html.push('<a href="PersonPage.action?userId=' + this.uid + '">' + this.name + '</a> ：' + this.content);
		html.push('</div>');
		html.push('<div style="width:440px;float:right;font-size:12px;">' + this.time + '<span style="float:right">');
		html.push('<a href="javascript:doArticle(' + this.id + ')">回复</a>');
		html.push(' | ');
		html.push('<a href="javascript:doForwardBox(' + this.id + ')">转发（' + this.forwardAmount + '）</a>');
		html.push(' | ');
		if(this.uid != this.nowuid){
			html.push('<a href="javascript:doDing(' + this.id + ')">顶它（' + this.dingAmount + '）</a></span></div>');
		}else{
			html.push('<a href="javascript:doDelete(' + this.id + ')">删除</a></span></div>');
		}
		html.push('</div>');
		return html.join('');
	}
	,reload : function(){
		//this.isDingValid = false;
		var myself = this;
		DaolifeAjax.getDlContentById(this.id,function(rs){
			myself.forwardAmount = rs.retwittNum;
			myself.dingAmount = rs.upNum; 
			$('#article_' + myself.id).get(0).innerHTML = myself.getElement();
		});
	}
	,getId : function(){
		return this.id;
	}
	,getName : function(){
		return this.name;
	}
	,getPicurl : function(){
		return this.picurl;
	}
}
function forwardBox(id){
	this.id = id;
}
forwardBox.prototype = {
	getHtml : function(){
		var item =myBox.articleBox.getElementById(this.id);
		var html = [];
		html.push('<table id="forwardbox_' + this.id + '" width="487" border="0" cellspacing="0" cellpadding="0" style="display:none;margin-bottom:10px;">');
		html.push('<tr><td width="487"><img src="images/myhome_34.gif" width="523" height="6" /></td></tr>');
		html.push('<tr>');
		html.push('<td align="center" valign="top" background="images/myhome_37.gif">');
		html.push('<table width="510" height="15" border="0" cellpadding="0" cellspacing="0">');
		html.push('<tr>');
		html.push('<td width="255">&nbsp;</td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('<table width="510" border="0" cellspacing="0" cellpadding="0">');
		html.push('<tr>');
		html.push('<td height="74" colspan="2" align="center" valign="top">');
		html.push('<form action="" method="post" enctype="multipart/form-data" name="hueifu" id="hueifu">');
		html.push('<table width="493" border="0" cellspacing="4" cellpadding="0">');
		html.push('<tr>');
		html.push('<td height="62" colspan="2" align="center" valign="top">');
		html.push('<label>');
		html.push('<textarea name="textarea" cols="45" rows="5" class="tabledao" style="background-image: url(images/myhome_42.gif); border: 0; width: 495px; height: 53px; resize: none; " id="forwardmsg_' + this.id + '">转 @' + item.name + ' : ' + item.content.replace(/<[^>].*?>/g,"") + '</textarea>');
		html.push('</label>');
		html.push('</td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td width="370" height="22" align="left" valign="top">');
		html.push('</td>');
		html.push('<td width="90" align="center" id="forwardbutton_' + this.id + '"><a href="javascript:doForward(' + this.id + ')"><img src="images/myhome_46.gif" width="88" height="22" /></a></td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('</form>');
		html.push('</td>');
		html.push('</tr>');
		html.push('</table>');
		html.push('</td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td>');
		html.push('<img src="images/myhome_45.gif" width="523" height="7" />');
		html.push('</td>');
		html.push('</tr>');
		html.push('</table>');
		return html.join('');
	}
}
//发布的集合
function articleBox(){
	this.element = [];
	this._element = [];
	this.totalCount = 1;
	this.pageCount = 1;
	this.currentPage = 1;
}
articleBox.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<center id="articlebox">');
		for(var l = this.element.length - 1; l>-1 ; --l){
			html.push(this.element[l].getHtml());
		}
		html.push(this.getpagebar());
		html.push('</center>');
		return html.join('');
	}
	,setpage : function(totalCount,pageCount,currentPage){
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
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
	,reload : function(){
		$('#articlebox').empty();
		var html = [];
		for(var i = 0, l = this.element.length; i < l ; ++i){
			html.push(this.element[i].getHtml());
		}
		html.push(this.getpagebar());
		$('#articlebox').get(0).innerHTML = html.join('');
		if(this.element.length){
			$('#article_' + this.element[0].id).hide().fadeIn(1100);
		}
	}
	/*
	,getpagebar : function(){
		var html = [];
		if(this.pageCount > 1){
			html.push('<div style="margin:10px 0;height:37px;color:#666666;border:1px #D8D8D8 solid;text-align:center;position: relative;overflow: hidden;">');
			html.push('<div style="width:440px;padding-top:10px;height:27px;font-size:14px;float:left;" id="pagemore"><a href="javascript:doPage()">更多</a></div>');
			html.push('<div style="width:120px;padding-top:11px;height:26px;float:left;border-left:1px #D8D8D8 solid;"><a href="javascript:goTop()">回到顶部</a></div>');
			html.push('</div>')
		}
		return html.join('');
	}
	*/
	,getpagebar : function(){
		var html = [];
		var l = this.pageCount > 10 ? 10 : this.pageCount;
		html.push('<div class="yem1"><center><ul>');
		if(this.pageCount > 1){
			for(var i = 0; i < l; ++i){
				html.push('<li><a href="javascript:doPage(' + (i+1) + ')">' + (i + 1) + '</a></li>');
			}
		}
		html.push('</ul>');
		if(l == 10){
			html.push('<span style="float:left;font-weight:bold;font-size:#518884;margin-top:3px;margin-left:5px;">...</span>');
		}
		html.push('</center></div>')
		return html.join('');
	}
	,initpage : function(){
		this.totalCount = 1;
		this.pageCount = 1;
		this.currentPage = 1;
	}
	,load : function(){
		$('#articlebox').empty();
		var html = [];
		for(var i = 0, l = this.element.length; i < l ; ++i){
			html.push(this.element[i].getHtml());
		}
		html.push(this.getpagebar());
		$('#articlebox').get(0).innerHTML = html.join('');
		$('#articlebox').hide().fadeIn(1100);
	}
}
var myBox = function(){}
myBox.status = 1;
myBox.articleBox = new articleBox();
myBox.replyBox = [];
/*
function doPage(){
	$('#pagemore').get(0).innerHTML = '<img src="images/floading.gif">';
	++myBox.articleBox.currentPage;
	doReload(function(){
		myBox.articleBox.load();
	});
}
*/
function doPage(page){
	goTop();
	myBox.articleBox.currentPage = page;
	doReload(function(){
		myBox.articleBox.load();
	});
}
function doDelete(id){
	msg.confirm('您确定要删除吗？',function(){
		DaolifeAjax.deleteDao(id,function(rs){
			if(rs){
				doReload(function(){
					myBox.articleBox.load();
				});
			}
		});
	})
}
function doReload(fn){
	myBox.articleBox.clean();
	var func = function(rs){
		myBox.articleBox.setpage(rs.totalCount,rs.pageCount,rs.currentPage);
		for(var i = 0, l = rs.items.length; i < l; ++i){
			myBox.articleBox.add(new article(rs.items[i].contentId,rs.items[i].dlUsers.userId,rs.items[i].dlUsers.userNickName,rs.items[i].contentBody,'images/myhome_30.gif',0,rs.items[i].retwittNum,rs.items[i].upNum,getTime(rs.items[i].posttime),rs.nowUid));
		}
		if(fn){
			fn();
		}
	}
	switch(myBox.status){
		case 1:
			DaolifeAjax.getAllDao(myBox.articleBox.currentPage,func);
			break;
		case 2:
			DaolifeAjax.getMyDao(myBox.articleBox.currentPage,func);
			break;
		case 3:
			DaolifeAjax.getContentsByTime(myBox.articleBox.currentPage,func);
			break;
	}
}
function doSubmit(){
	if($('#articlecontent').val() != null){
		DaolifeAjax.addDao($('#articlecontent').val().substr(0,140),function(rs){
			if(rs != null){
				$('#fontlength').get(0).innerHTML = 140;
				$('#articlecontent').val('');
				doReload(function(){
					if(myBox.articleBox.currentPage == 1){
						myBox.articleBox.reload();
					}else{
						myBox.articleBox.load();
					}
					doPersonal(null);
				});
			}else{
				msg.alert('发送信息失败，请重试！');
			}
		});
	}
}
function doStatus(id){
	myBox.status = id;
	$('.status').each(function(i){
		switch(i + 1){
			case 1:
				var html = '<img height="23" width="77" alt="" src="images/1154a_03.gif">';
				if(id == 1){
					html = '<img src="images/myhome_21.gif" alt="" width="112" height="24" />';
				}
				break;
			case 2:
				var html = '<img src="images/myhome_23.gif" width="48" height="23" />';
				if(id == 2){
					html = '<img height="22" width="112" src="images/1154a_06.gif">';
				}
				break;
			case 3:
				var html = '<img height="23" width="65" src="images/myhome_25.gif">';
				if(id == 3){
					html = '<img height="23" width="112" src="images/kankan.gif">';
				}
				break;
		}
		$(this).get(0).innerHTML = html;
	});
	doReload(function(){
		myBox.articleBox.load();
	});
}
function doForwardBox(id){
	if($('#replybox_' + id).html()){
		$('#replybox_' + id).remove();
	}
	if($('#forwardbox_' + id).html()){
		$('#forwardbox_' + id).fadeOut(800,function(){
			$(this).remove();
		}); 
	}else{
		var fb = new forwardBox(id);
		$('#article_' + id).get(0).innerHTML += fb.getHtml();
		$('#forwardbox_' +id).fadeIn(1100);
	}
}
function doForward(id){
	if($('#forwardmsg_' + id).val()){
		$('#forwardbutton_' + id).get(0).innerHTML = '<div style="height:22px;"><img src="images/floading.gif" /></div>';
		DaolifeAjax.addRetwitteDao($('#forwardmsg_' + id).val(),id,function(rs){
			if(rs){
				//maskHide();
				myBox.articleBox.getElementById(id).reload();
				doReload(function(){
					doForwardBox(id);
					if(myBox.articleBox.currentPage == 1){
						myBox.articleBox.reload();
					}else{
						myBox.articleBox.load();
					}
					doPersonal(null);
				});
			}
		});
	}
	$('#forward-msg').focus();
}
function doDing(id){
	DaolifeAjax.upDao(id,function(rs){
		if(rs){
			msg.alert(rs);
		}else{
			myBox.articleBox.getElementById(id).reload();
		}
	});
}
function closefunction(){
	msg.alert('此功能暂时不开放！');
}
$(function($){
	if(getQueryString('callbackFunc')){
		eval('(' + getQueryString('callbackFunc') + ')');
	}
	doPersonal(null);
	function fontsizealert(){
		var length = 140;
		var tlength = 39;
		var fl = length - parseInt($('#articlecontent').val().length);
		fl = '<span style="font-size:16px;font-weight:bold;">' + fl + '</span>';
		if(fl <= 1){
			$('#articlecontent').val($('#articlecontent').val().substr(0,length));
			$('#fontlength').get(0).innerHTML = '<span style="color:red;font-size:18px;font-weight:bold;">0(不能作为T恤文字)</span>';
			return;
		}
		if(fl < length - tlength){
			fl = '<span style="color:red;font-size:18px;font-weight:bold;">' + fl + '(不能作为T恤文字)</span>';
		}
		$('#fontlength').get(0).innerHTML = fl;
	}
	doReload(function(){
		myBox.articleBox.load();
	});
	$('#articleandreply').get(0).innerHTML = myBox.articleBox.getHtml();	
	$('#articlecontent').keyup(fontsizealert);
	fontsizealert();
});