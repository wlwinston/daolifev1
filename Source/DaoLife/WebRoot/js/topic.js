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
		html.push('<div id="replybox_' + this.id + '" style="width:523px;margin: 10px auto;">');
		html.push('<div><img src="images/myhome_34.gif" width="523" height="6" /></div>');
		html.push('<div style="background:url(\'images/myhome_37.gif\');">');
		html.push('<div id="replylist_' + this.id + '" style="padding: 10px 0;background:url(\'images/myhome_37.gif\');"></div>');
		html.push('<div><textarea name="textarea" class="tabledao" style="border: 0;max-width:495px;max-height:53px; width: 495px; height: 53px;resize: none; " id="replymsg_' + this.id + '"></textarea></div>');
		html.push('<div style="margin: 7px auto 0;text-align:right;width:495px;"><span style="float:left;line-height:22px;font-weight:bold;">回复</span><span id="replybutton_' + this.id + '"><a href="javascript://" id="replybox-button-submit-' + this.id + '"><img src="images/myhome_46.gif" /></a>&nbsp;&nbsp;<input type="image" href="images/myhome_11.gif" src="images/pinglun_181.gif" id="button-no"></span></div>');
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
		html.push('<div style="width:495px;text-align:left;height:20px;text-indent:1em;"><b>共有评论 ' + this.totalCount + ' 条,您正在查看的是 ' + str + ' 条</b><span style="float:right;">');
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
	,initPage : function(fn){
		this.currentPage = 1;
		this.pageAjax(fn);
	}
	,pageAjax : function(fn){
		var self = this;
		fn = fn || function(){}
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
				fn();
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
function doReply(id,fn){
	var rb = myBox.replyBox[id];
	var replyid = rb.reply ? rb.reply.id : null;
	if($('#replymsg_' + id).val() != '' && $('#replymsg_' + id).val() != ''){
		$('#replybutton_' + id).get(0).innerHTML = '<span style="margin-right:42px;line-height:22px;"><img src="images/floading.gif" /></span>';
		DaolifeAjax.addComment(id,$('#replymsg_' + id).val(),replyid,function(rs){
			fn();
		});
	}
}
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
myBox.replyBox = [];
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
			myBox.articleBox.add(new daoHot(rs.items[i].contentId,rs.items[i].userId,((rs.currentPage - 1) * rs.pageSize) + (i + 1),rs.items[i].dlUsers.userNickName,rs.items[i].contentBody,'images/myhome_30.gif',rs.items[i].retwittNum,rs.items[i].upNum,rs.items[i].replyNum));
		}
		if(fn){
			fn();
		}
	}
	DaolifeAjax.getTopicContent(myBox.articleBox.currentPage,getQueryString('topicId'),func);
}
function doForward(id){
	var item =myBox.articleBox.getElementById(id);
	if(item){
		var rf = new forward(item.id,item.picurl,item.name,item.content);
		mask(rf.getHtml(),function(){
			DaolifeAjax.addRetwitteDao($('#forward-msg').val(),id,function(rs){
				if(rs){
					maskHide();
					doReload(function(){
						myBox.articleBox.load();
					})
				}else{
					maskHide();
					alert('请先登录后在执行此操作！');
				}
			})
		});
	}
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
function doDing(id){
	DaolifeAjax.upDao(id,function(rs){
		if(rs){
			alert(rs);
		}else{
			myBox.articleBox.getElementById(id).reload();
		}
	});
}
$(function($){
	doReload(function(){
		myBox.articleBox.load();
	})
	$('#box').get(0).innerHTML = myBox.articleBox.getHtml();
});