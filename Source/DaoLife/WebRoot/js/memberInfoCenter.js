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
		this.html.push('<td width="75">转发<a href="javascript:mask(' + this.id + ',\'' + this.picurl + '\',\'' + this.name + '\',\'' + this.content + '\')">（' + this.forwardAmount + '）</a></td>');
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
		this.html.push('<div>');
		if(this.pageCount > 1){
			for(var i = 0, l = this.pageCount; i < l; ++i){
				this.html.push('<a href="javascript:doPage(' + (i+1) + ')" style="margin-left:10px;">' + (i+1) + '</a>');
			}
		}
		this.html.push('</div>');
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
	}
	,clean : function(){
		this.element = [];
	}
	,reload : function(){
		$('#articlebox').empty();
		for(var i = 0, l = this.element.length; i < l ; ++i){
			$('#articlebox').get(0).innerHTML = this.element[i].getHtml() + $('#articlebox').get(0).innerHTML;
			if(i == (l - 1)){
				$('#article_' + this.element[i].id).css({
					display:'none'
				})
				$('#article_' + this.element[i].id).fadeIn(1100);
			}
		}
		var html = [];
		html.push('<div  style="margin-top:20px;">');
		for(var i = 0, l = this.pageCount; i < l; ++i){
			html.push('<a href="javascript:doPage(' + (i+1) + ')" style="margin-left:10px;">' + (i+1) + '</a>');
		}
		html.push('</div>');
		$('#articlebox').get(0).innerHTML += html.join('');
	}
	,getpagebar : function(){
		var html = [];
		html.push('<div  style="margin-top:20px;">');
		if(this.pageCount > 1){
			for(var i = 0, l = this.pageCount; i < l; ++i){
				html.push('<a href="javascript:doPage(' + (i+1) + ')" style="margin-left:10px;">' + (i+1) + '</a>');
			}
		}
		html.push('</div>');
		return html.join('');
	}
	,load : function(){
		$('#articlebox').empty();
		var html = [];
		for(var i = 0, l = this.element.length; i < l ; ++i){
			html.push(this.element[i].getHtml());
			//$('#articlebox').get(0).innerHTML = this.element[i].getHtml() + $('#articlebox').get(0).innerHTML;
		}
		$('#articlebox').get(0).innerHTML = html.join('') + this.getpagebar();
		$('#articlebox').css({
			display : 'none'
		});
		$('#articlebox').fadeIn(1100);
		
	}
}
var myBox = function(){}
//myBox.index = 1;
myBox.articleBox = new articleBox();
//myBox.articleBox.add(new article(1,'高建','我在真理部上班','images/myhome_30.gif','100','100','100'));
function doPage(page){
	myBox.articleBox.currentPage = page;
	doReload(true);
}
function doReload(status){
	myBox.articleBox.clean();
	DaolifeAjax.getAllDao(myBox.articleBox.currentPage,function(rs){
		myBox.articleBox.setpage(rs.totalCount,rs.pageCount,rs.currentPage);
		for(var i = 0, l = rs.items.length; i < l; ++i){
			myBox.articleBox.add(new article(rs.items[i].contentId,rs.items[i].dlUsers.userNickName,rs.items[i].contentBody,'images/myhome_30.gif',0,rs.items[i].retwittNum,rs.items[i].upNum));
		}
		if(status){
			myBox.articleBox.load();
		}else{
			myBox.articleBox.reload();
		}
	});
}
function doSubmit(){
	if($('#articlecontent').val() != null){
		DaolifeAjax.addDao($('#articlecontent').val(),function(rs){
			if(rs != null){
				$('#articlecontent').val('');
				doReload(false);
			}else{
				alert('发送信息失败');
			}
		});
	}
}
function forward(id,picurl,name,content){
	this.id = id;
	this.picurl = picurl;
	this.name = name;
	this.content = content;
}
forward.prototype = {
	getHtml : function(){
		this.html = [];
		this.html.push('<table style="margin: 0 auto;" width="487" border="0" align="center" cellpadding="0" cellspacing="0">');
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
        this.html.push('<td height="74" colspan="2" align="center" valign="top"><form action="" method="post" enctype="multipart/form-data" name="hueifu" id="hueifu2">');
        this.html.push('<table width="493" border="0" cellspacing="4" cellpadding="0">');
        this.html.push('<tr>');
        this.html.push('<td height="62" colspan="2" align="center" valign="top"><label></label>');
        this.html.push('<label>');
        this.html.push('<textarea name="textarea2" cols="45" rows="5" class="tabledao" id="textarea2" style="background-image:url(images/myhome_42.gif);border:0; width:495px; height:53px;overflow : hidden"></textarea>');
        this.html.push('</label></td>');
        this.html.push('</tr>');
        this.html.push('<tr>');
        this.html.push('<td width="370" height="22" align="left" valign="top"><label></label></td>');
        this.html.push('<td width="123"><input type="image" src="images/1134.gif" href="images/myhome_11.gif" /></td>');
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
function mask(id,picurl,name,content){
	var rf = new forward(id,picurl,name,content);
	$('body').append('<div id="mask-overlay"></div><div id="mask-forward">' + rf.getHtml() + '</div>');
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
	$('#mask-overlay').click(function() {
		$('#mask-forward').fadeOut(function(){
			$(this).remove();
		})
		$(this).fadeOut(function(){
			$(this).remove();
		});
	});
}
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
$(function($){
	doReload(true);
	$('#articleandreply').get(0).innerHTML = myBox.articleBox.getHtml();
	//var ab1 = new articleBox();
	//ab1.add(new article(1,'高建','我在真理部上班','images/myhome_30.gif','100','100','100'));
	//ab1.add(new article(2,'周云','我听这么牛逼的音乐还是没有妞喜欢我','images/myhome_30.gif','100','100','100'));
	//var rb1 = new replyBox(1);
	//rb1.add(new reply(1,1,'Tony','没妞怎么了','images/myhome_39.gif'));
	//rb1.add(new reply(2,1,'Sam','有什么大不了的','images/myhome_39.gif'));
	//var rb2 = new replyBox(2);
	//rb2.add(new reply(1,2,'Tony','没妞怎么了','images/myhome_39.gif'));
	//rb2.add(new reply(2,2,'Sam','有什么大不了的','images/myhome_39.gif'));
	//$('#articleandreply').get(0).innerHTML = ab1.getHtml();// +  rb.getHtml() //$('.Welcome center').get(0).innerHTML;
	
});