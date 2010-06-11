//回复单条数据
function reply(id,toid,name,content,picurl){
	this.id = id;
	this.toid = toid;
	this.name = name;
	this.content = content;
	this.picurl =picurl;
	this.html = [];
}
reply.prototype = {
	getHtml : function(){
		this.html = [];
		this.html.push('<table id="reply_' + this.id + '" width="510" border="0" cellspacing="0" cellpadding="2">');
		this.html.push('<tr>');
		this.html.push('<td width="45" height="50" align="left" valign="top"><a href="PersonPage.action?userId=' + this.id + '"><img src="' + this.picurl + '" width="38" height="38" /></a></td>');
		this.html.push('<td width="457" align="left" valign="top">');
		this.html.push('<table width="432" height="46" border="0" cellpadding="0" cellspacing="0">');
		this.html.push('<tr>');
		this.html.push('<td width="432" height="26"><a href="PersonPage.action?userId=' + this.id + '">' + this.name + '</a></td>');
		this.html.push('</tr>');
		this.html.push('<tr>');
		this.html.push('<td height="20" align="left">');
		this.html.push('<table width="432" border="0" cellspacing="2" cellpadding="2">');
		this.html.push('<tr>');
		this.html.push('<td width="300">' + this.content + '</td>');
		this.html.push('<td width="70">回复</td><td width="42">删除</td>');
		this.html.push('</tr>');
		this.html.push('</table>');
		this.html.push('</td>');
		this.html.push('</tr>');
		this.html.push('</table>');
		this.html.push('</td>');
		this.html.push('</tr>');
		this.html.push('</table>');
		return this.html.join('');
	}
}
//回复集合		
function replyBox(id){
	this.id = id;
	this.element = [];
	this.html = [];
}
replyBox.prototype = {
	getHtml : function(){
		this.html = [];
		this.html.push('<table id="replybox_' + this.id+ '" width="487" border="0" cellspacing="0" cellpadding="0" style="display:none;">');
		this.html.push('<tr><td width="487"><img src="images/myhome_34.gif" width="523" height="6" /></td></tr>');
		this.html.push('<tr>');
		this.html.push('<td align="center" valign="top" background="images/myhome_37.gif">');
		this.html.push('<table width="510" height="15" border="0" cellpadding="0" cellspacing="0">');
		this.html.push('<tr>');
		this.html.push('<td width="255">共有评论28条 您现在查看的是1-10条</td><td width="268">前十条 上一页 1 2 3 下一页 后十条</td>');
		this.html.push('</tr>');
		this.html.push('</table>');
		for(var i = 0, l = this.element.length; i < l; ++i){
			this.html.push(this.element[i].getHtml());
		}
		this.html.push('<table width="510" border="0" cellspacing="0" cellpadding="0">');
		this.html.push('<tr>');
		this.html.push('<td height="74" colspan="2" align="center" valign="top">');
		this.html.push('<form action="" method="post" enctype="multipart/form-data" name="hueifu" id="hueifu">');
		this.html.push('<table width="493" border="0" cellspacing="4" cellpadding="0">');
		this.html.push('<tr>');
		this.html.push('<td height="79" colspan="2" align="center" valign="top">');
		this.html.push('<label>');
		this.html.push('<textarea name="textarea" cols="45" rows="5" class="tabledao" id="textarea" style="background-image: url(images/myhome_42.gif); border: 0; width: 495px; height: 53px;"></textarea>');
		this.html.push('</label>');
		this.html.push('</td>');
		this.html.push('</tr>');
		this.html.push('<tr>');
		this.html.push('<td width="370" height="22" align="left" valign="top">');
		this.html.push('<label>');
		this.html.push('<input type="checkbox" name="dao1" id="dao1" />同时叨一条');
		this.html.push('</label>');
		this.html.push('</td>');
		this.html.push('<td width="123"><img src="images/myhome_46.gif" width="88" height="22" /></td>');
		this.html.push('</tr>');
		this.html.push('</table>');
		this.html.push('</form>');
		this.html.push('</td>');
		this.html.push('</tr>');
		this.html.push('</table>');
		this.html.push('</td>');
		this.html.push('</tr>');
		this.html.push('<tr>');
		this.html.push('<td>');
		this.html.push('<img src="images/myhome_45.gif" width="523" height="7" />');
		this.html.push('</td>');
		this.html.push('</tr>');
		this.html.push('</table>');
		return this.html.join('');
	}
	,add : function(reply){
		this.element.push(reply);
	}
}
//做回复
function doArticle(id){
	if($('#replybox_' + id).html()){
		$('#replybox_' + id).fadeOut(800,function(){
			$(this).remove();
		}); 
	}else{
		var rb = new replyBox(id);
		rb.add(new reply(1+id,id,'Tony','没妞怎么了','images/myhome_39.gif'));
		rb.add(new reply(2+id,id,'Sam','有什么大不了的','images/myhome_39.gif'));
		$('#article_' + id).after(rb.getHtml());
		$('#replybox_' +id).fadeIn(1100);
	}
}
//发布的单条数据
function article(id,uid,name,content,picurl,replyAmount,forwardAmount,dingAmount){
	this.id = id;
	this.uid = uid;
	this.name = name;
	this.content = content;
	this.picurl = picurl;
	this.replyAmount = replyAmount;
	this.forwardAmount = forwardAmount;
	this.dingAmount = dingAmount;
	this.html = [];
	this.isDingValid = true;
	this.time = '2010-06-11 17:50';
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
		html.push('<div style="width:440px;float:right;font-size:12px;">' + this.time + '<span style="float:right"><a href="javascript:doForwardBox(' + this.id + ')">转发（' + this.forwardAmount + '）</a> | <a href="javascript:doDing(' + this.id + ')">顶它（' + this.dingAmount + '）</a></span></div>')
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
		this.html = [];
		this.html.push('<table id="forwardbox_' + this.id + '" width="487" border="0" cellspacing="0" cellpadding="0" style="display:none;margin-bottom:10px;">');
		this.html.push('<tr><td width="487"><img src="images/myhome_34.gif" width="523" height="6" /></td></tr>');
		this.html.push('<tr>');
		this.html.push('<td align="center" valign="top" background="images/myhome_37.gif">');
		this.html.push('<table width="510" height="15" border="0" cellpadding="0" cellspacing="0">');
		this.html.push('<tr>');
		this.html.push('<td width="255">&nbsp;</td>');
		this.html.push('</tr>');
		this.html.push('</table>');
		this.html.push('<table width="510" border="0" cellspacing="0" cellpadding="0">');
		this.html.push('<tr>');
		this.html.push('<td height="74" colspan="2" align="center" valign="top">');
		this.html.push('<form action="" method="post" enctype="multipart/form-data" name="hueifu" id="hueifu">');
		this.html.push('<table width="493" border="0" cellspacing="4" cellpadding="0">');
		this.html.push('<tr>');
		this.html.push('<td height="62" colspan="2" align="center" valign="top">');
		this.html.push('<label>');
		this.html.push('<textarea name="textarea" cols="45" rows="5" class="tabledao" style="background-image: url(images/myhome_42.gif); border: 0; width: 495px; height: 53px; resize: none; " id="forwardmsg_' + this.id + '">转 @' + item.name + ' : ' + item.content.replace(/<[^>].*?>/g,"") + '</textarea>');
		this.html.push('</label>');
		this.html.push('</td>');
		this.html.push('</tr>');
		this.html.push('<tr>');
		this.html.push('<td width="370" height="22" align="left" valign="top">');
		this.html.push('</td>');
		this.html.push('<td width="90" align="center" id="forwardbutton_' + this.id + '"><a href="javascript:doForward(' + this.id + ')"><img src="images/myhome_46.gif" width="88" height="22" /></a></td>');
		this.html.push('</tr>');
		this.html.push('</table>');
		this.html.push('</form>');
		this.html.push('</td>');
		this.html.push('</tr>');
		this.html.push('</table>');
		this.html.push('</td>');
		this.html.push('</tr>');
		this.html.push('<tr>');
		this.html.push('<td>');
		this.html.push('<img src="images/myhome_45.gif" width="523" height="7" />');
		this.html.push('</td>');
		this.html.push('</tr>');
		this.html.push('</table>');
		return this.html.join('');
	}
}
//发布的集合
function articleBox(){
	this.element = [];
	this._element = [];
	this.html = [];
	this.totalCount = 1;
	this.pageCount = 1;
	this.currentPage = 1;
}
articleBox.prototype = {
	getHtml : function(){
		this.html.push('<center id="articlebox">');
		for(var l = this.element.length - 1; l>-1 ; --l){
			this.html.push(this.element[l].getHtml());
		}
		this.html.push(this.getpagebar());
		this.html.push('</center>');
		return this.html.join('');
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
	,getpagebar : function(){
		var html = [];
		/*
		html.push('<div class="yem1"><center><ul>');
		if(this.pageCount > 1){
			for(var i = 0, l = this.pageCount; i < l; ++i){
				html.push('<li><a href="javascript:doPage(' + (i+1) + ')">' + (i + 1) + '</a></li>');
			}
		}
		html.push('</ul></center></div>')
		*/
		if(this.pageCount > 1){
			html.push('<div style="margin:10px 0;height:37px;color:#666666;border:1px #D8D8D8 solid;text-align:center;position: relative;overflow: hidden;">');
			html.push('<div style="width:440px;padding-top:10px;height:27px;font-size:14px;float:left;" id="pagemore"><a href="javascript:doPage()">更多</a></div>');
			html.push('<div style="width:120px;padding-top:11px;height:26px;float:left;border-left:1px #D8D8D8 solid;"><a href="javascript:goTop()">回到顶部</a></div>');
			html.push('</div>')
		}
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
function doPage(){
	$('#pagemore').get(0).innerHTML = '<img src="images/floading.gif">';
	++myBox.articleBox.currentPage;
	doReload(function(){
		myBox.articleBox.load();
	});
}
function doReload(fn){
	//myBox.articleBox.clean();
	var func = function(rs){
		myBox.articleBox.setpage(rs.totalCount,rs.pageCount,rs.currentPage);
		for(var i = 0, l = rs.items.length; i < l; ++i){
			myBox.articleBox.add(new article(rs.items[i].contentId,rs.items[i].dlUsers.userId,rs.items[i].dlUsers.userNickName,rs.items[i].contentBody,'images/myhome_30.gif',0,rs.items[i].retwittNum,rs.items[i].upNum));
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
	//var item =myBox.articleBox.getElementById(id);
	//if(item){
		//var rf = new forward(item.id,item.picurl,item.name,item.content);
		//mask(rf.getHtml(),function(){
			if($('#forwardmsg_' + id).val()){
				$('#forwardbutton_' + id).get(0).innerHTML = '<img src="images/floading.gif" />';
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
		//});
		$('#forward-msg').focus();
	//}
}
/*
function doForward(id){
	var item =myBox.articleBox.getElementById(id);
	if(item){
		var rf = new forward(item.id,item.picurl,item.name,item.content);
		mask(rf.getHtml(),function(){
			DaolifeAjax.addRetwitteDao($('#forward-msg').val(),id,function(rs){
				if(rs){
					maskHide();
					myBox.articleBox.getElementById(id).reload();
					doReload(function(){
						if(myBox.status !=1){
							myBox.articleBox.reload();
						}
						doPersonal(null);
					});
				}
			})
		});
		$('#forward-msg').focus();
	}
}
*/
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
		if(fl <= 1){
			$('#articlecontent').val($('#articlecontent').val().substr(0,length));
			$('#fontlength').get(0).innerHTML = '<span style="color:red;">0(不能作为T恤文字)</span>';
			return;
		}
		if(fl < length - tlength){
			fl = '<span style="color:red;">' + fl + '(不能作为T恤文字)</span>';
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