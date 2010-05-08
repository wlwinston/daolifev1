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
		this.html.push('<td width="45" height="50" align="left" valign="top"><img src="' + this.picurl + '" width="38" height="38" /></td>');
		this.html.push('<td width="457" align="left" valign="top">');
		this.html.push('<table width="432" height="46" border="0" cellpadding="0" cellspacing="0">');
		this.html.push('<tr>');
		this.html.push('<td width="432" height="26">' + this.name + '</td>');
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
function article(id,name,content,picurl,replyAmount,forwardAmount,dingAmount){
	this.id = id;
	this.name = name;
	this.content = content;
	this.picurl = picurl;
	this.replyAmount = replyAmount;
	this.forwardAmount = forwardAmount;
	this.dingAmount = dingAmount;
	this.html = [];
}
article.prototype = {
	getHtml : function(){
		this.html = [];
		this.html.push('<table id="article_' + this.id + '" width="540" border="0" cellspacing="4" cellpadding="4">');
		this.html.push('<tr>');
		this.html.push('<td width="47" height="44"><img src="' + this.picurl + '" width="78" height="77" /></td>');
		this.html.push('<td width="465" align="left" valign="top">');
		this.html.push('<table width="432" height="78" border="0" cellpadding="0" cellspacing="0">');
		this.html.push('<tr><td width="432">' + this.name + '</td></tr>');
		this.html.push('<tr><td>' + this.content + '</td></tr>');
		this.html.push('<tr>');
		this.html.push('<td align="right">');
		this.html.push('<table width="250" border="0" cellspacing="2" cellpadding="2">');
		this.html.push('<tr>');
		this.html.push('<td width="78">回复<a href="javascript:doArticle(' + this.id + ')">（' + this.replyAmount + '）</a></td>');
		this.html.push('<td width="75">转发<a href="javascript:doForward(' + this.id + ')">（' + this.forwardAmount + '）</a></td>');
		this.html.push('<td width="77">顶他<a href="#">（' + this.dingAmount + '）</a></td>');
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
//发布的集合
function articleBox(){
	this.element = [];
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
		html.push('<div class="yem1"><center><ul>');
		if(this.pageCount > 1){
			for(var i = 0, l = this.pageCount; i < l; ++i){
				html.push('<li><a href="javascript:doPage(' + (i+1) + ')">' + (i + 1) + '</a></li>');
			}
		}
		html.push('</ul></center></div>')
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
			myBox.articleBox.add(new article(rs.items[i].contentId,rs.items[i].dlUsers.userNickName,rs.items[i].contentBody,'images/myhome_30.gif',0,rs.items[i].retwittNum,rs.items[i].upNum));
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
		DaolifeAjax.addDao($('#articlecontent').val(),function(rs){
			if(rs != null){
				$('#articlecontent').val('');
				doReload(function(){
					myBox.articleBox.reload();
				});
			}else{
				alert('发送信息失败');
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
function doForward(id){
	var item =myBox.articleBox.getElementById(id);
	if(item){
		var rf = new forward(item.id,item.picurl,item.name,item.content);
		mask(rf.getHtml());
	}
}
$(function($){
	doReload(function(){
		myBox.articleBox.load();
	});
	$('#articleandreply').get(0).innerHTML = myBox.articleBox.getHtml();	
});