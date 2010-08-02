//回复单条数据
function reply(id,toid,uid,name,content,time,picurl){
	this.id = id;
	this.toid = toid;
	this.uid = uid;
	this.name = name;
	this.content = content;
	this.time = time;
	this.picurl =picurl;
}
reply.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<div style="text-align:left;width:495px;margin:10px auto;min-height:38px;height:auto;max-height:none;overflow:hidden;">');
		html.push('<div style="width:38px;height:38px;float:left;"><img src="images/myhome_39.gif" /></div>');
		html.push('<div style="width:452px;float:right;height:auto;max-height:none;overflow:hidden;">');
		html.push('<div style="min-height:22px;height:auto;max-height:none;line-height:19px;margin-top:1px;"><a href="PersonPage.action?userId=' + this.uid + '">' + this.name + '</a> ：' + this.content + '</div>');
		html.push('<div style="font-size:12px;text-align:right;"><span style="float:left;color:#999;">' + this.time + '</span><a href="javascript:rereply(' + this.id + ',' + this.toid + ')">回复</a> <!--| <a href="javascript://">删除</a>--></div>')
		html.push('</div>');
		html.push('</div>');
		return html.join('');
	}
}
function rereply(id,toid){
	myBox.replyBox[toid].reply = myBox.replyBox[toid].getElementById(id);
	$('#replymsg_' + toid).val('回复@' + myBox.replyBox[toid].getElementById(id).name + ' ：').focus();
}	
//回复集合		
function replyBox(id){
	this.id = id;
	this.element = [];
	this._element = [];
	this.reply = null;
	this.totalCount = 1;
	this.pageCount = 1;
	this.currentPage = 1;
}
replyBox.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<div id="replybox_' + this.id + '" style="width:523px;display:none;margin-bottom:10px;">');
		html.push('<div><img src="images/myhome_34.gif" width="523" height="6" /></div>');
		html.push('<div style="background:url(\'images/myhome_37.gif\');">');
		html.push('<div id="replylist_' + this.id + '" style="padding: 10px 0;background:url(\'images/myhome_37.gif\');"></div>');
		html.push('<div><textarea name="textarea" class="tabledao" style="border: 0;max-width:495px;max-height:53px; width: 495px; height: 53px;resize: none; " id="replymsg_' + this.id + '"></textarea></div>');
		html.push('<div style="margin: 7px auto 0;text-align:right;width:495px;"><span style="float:left;line-height:22px;font-weight:bold;">回复</span><span id="replybutton_' + this.id + '"><a href="javascript:doReply(' + this.id + ')"><img src="images/myhome_46.gif" /></a></span></div>');
		html.push('</div>');
		html.push('<div><img src="images/myhome_45.gif" width="523" height="7" /></div>');
		html.push('</div>');
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
	,setpage : function(totalCount,pageCount,currentPage,pageSize){
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	,showList : function(){
		var html  = [];
		var count = (((this.currentPage - 1) * this.pageSize) + 1);
		var str = count + '-' + ((count + this.pageSize) > this.totalCount ? this.totalCount : (count + this.pageSize) -1 );
		html.push('<div style="width:495px;text-align:left;height:20px;text-indent:3px;"><b>共有评论 ' + this.totalCount + ' 条,您正在查看的是 ' + str + ' 条</b><span style="float:right;">');
		if(this.currentPage != 1){
			html.push('<a href="javascript:replyPre(' + this.id + ')"> 上一页 </a>');
		}
		if(this.currentPage != Math.ceil(this.totalCount/this.pageSize)){
			html.push('<a href="javascript:replyNext(' + this.id + ')"> 下一页 </a>');
		}
		html.push('</span></div>');
		for(var i = 0, l = this.element.length; i < l; ++i){
			html.push(this.element[i].getHtml());
		}
		$('#replylist_' + this.id).get(0).innerHTML = html.join('');
		$('#replylist_' + this.id).hide().fadeIn(1100);
	}
	,getElementById : function(id){
		return this._element[id];
	}
	,nextPage : function(){
		++this.currentPage;
		this.pageAjax();
	}
	,prePage : function(){
		--this.currentPage;
		this.pageAjax();
	}
	,initPage : function(){
		this.currentPage = 1;
		this.pageAjax();
	}
	,pageAjax : function(){
		var self = this;
		DaolifeAjax.getDaoReply(self.currentPage,self.id,function(data){
			if(data){
				self.empty();
				self.setpage(data.totalCount,data.pageCount,data.currentPage,data.pageSize)
				for(var i = 0, l = data.items.length; i < l; ++i){
					self.add(new reply(data.items[i].commentId,data.items[i].contentId,data.items[i].userId,data.items[i].dlUsers.userNickName,data.items[i].commentBody,getTime(data.items[i].posttime),'images/myhome_39.gif'));
				}
				if(l){
					self.showList();
				}else{
					$('#replylist_' + self.id).get(0).innerHTML = '';
				}
			}
		});
	}
}
function replyNext(id){
	myBox.replyBox[id].nextPage();
}
function replyPre(id){
	myBox.replyBox[id].prePage();
}
function doReply(id){
	var rb = myBox.replyBox[id];
	var replyid = rb.reply ? rb.reply.id : null;
	if($('#replymsg_' + id).val() != '' && $('#replymsg_' + id).val() != ''){
		$('#replybutton_' + id).get(0).innerHTML = '<span style="margin-right:42px;line-height:22px;"><img src="images/floading.gif" /></span>';
		DaolifeAjax.addComment(id,$('#replymsg_' + id).val(),replyid,function(rs){
			myBox.articleBox.getElementById(id).reload(function(){
				doArticle(id)
			});
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
		$('#article_' + id).get(0).innerHTML += myBox.replyBox[id].getHtml();
		$('#replylist_' + id).get(0).innerHTML += '<span style="margin-right:42px;"><img src="images/floading.gif" /></span>';
		$('#replybox_' +id).fadeIn(1100);
		myBox.replyBox[id].initPage();
	}
}
//发布的单条数据
function article(id,uid,name,content,picurl,replyAmount,forwardAmount,dingAmount,time,nowuid,replyNum){
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
	this.replyNum = replyNum;
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
		html.push('<div style="width:523px;margin:10px 5px;height:auto;max-height:none;min-height:80px;overflow:hidden;text-align:left;">');
		html.push('<div style="width:80px;height:80px;float:left;padding:0px;border:1px #D8D8D8;"><a href="PersonPage.action?userId=' + this.uid + '"><img src="images/myhome_30.gif" width="80" height="80" /></a></div>');
		html.push('<div style="width:432px;float:right;padding-top:3px;line-height:19px;min-height:60px;">');
		html.push('<a href="PersonPage.action?userId=' + this.uid + '">' + this.name + '</a> ：' + this.content);
		html.push('</div>');
		html.push('<div style="width:432px;float:right;font-size:12px;"><span style="color:#999;">' + this.time + '</span><span style="float:right">');
		html.push('<a href="javascript:doArticle(' + this.id + ')">回复（' + this.replyNum + '）</a>');
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
	,reload : function(fn){
		//this.isDingValid = false;
		var myself = this;
		fn = fn || function(){}
		DaolifeAjax.getDlContentById(this.id,function(rs){
			myself.forwardAmount = rs.retwittNum;
			myself.dingAmount = rs.upNum; 
			myself.replyNum = rs.replyNum;
			$('#article_' + myself.id).get(0).innerHTML = myself.getElement();
			fn();
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
		html.push('<div id="forwardbox_' + this.id + '" style="width:523px;display:none;margin-bottom:10px;">');
		html.push('<div><img src="images/myhome_34.gif" width="523" height="6" /></div>');
		html.push('<div style="background:url(\'images/myhome_37.gif\');">');
		html.push('<div style="padding: 10px 0;"></div>');
		html.push('<div><textarea name="textarea" class="tabledao" style="border: 0;max-width:495px;max-height:53px; width: 495px; height: 53px;resize: none; " id="forwardmsg_' + this.id + '">转 @' + item.name + ' : ' + item.content.replace(/<[^>].*?>/g,"") + '</textarea></div>');
		html.push('<div style="margin: 7px auto 0;text-align:right;width:495px;"><span style="float:left;line-height:22px;font-weight:bold;">转发</span><span id="forwardbutton_' + this.id + '"><a href="javascript:doForward(' + this.id + ')"><img src="images/myhome_46.gif" /></a></span></div>');
		html.push('</div>');
		html.push('<div><img src="images/myhome_45.gif" width="523" height="7" /></div>');
		html.push('</div>');
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
			myBox.articleBox.add(new article(rs.items[i].contentId,rs.items[i].dlUsers.userId,rs.items[i].dlUsers.userNickName,rs.items[i].contentBody,'images/myhome_30.gif',0,rs.items[i].retwittNum,rs.items[i].upNum,getTime(rs.items[i].posttime),rs.nowUid,rs.items[i].replyNum));
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
		$('#forwardbutton_' + id).get(0).innerHTML = '<span style="margin-right:42px;line-height:22px;"><img src="images/floading.gif" /></span>';
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