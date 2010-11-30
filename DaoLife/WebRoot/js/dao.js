function article(param){
	id,uid,name,content,picurl,replyAmount,forwardAmount,dingAmount
	//叨id
	this.id = param.id;
	//叨用户id
	this.uid = param.uid;
	//用户名
	this.name = param.name;
	//昵称
	this.nick = param.nick;
	//内容
	this.content = param.content;
	//头像
	this.picurl = param.picurl || 'images/myhome_30.gif';
	//回复数量
	this.replyAmount = param.replyAmount;
	//转发数量
	this.forwardAmount = param.forwardAmount;
	//顶的数量
	this.dingAmount = param.dingAmount;
	//叨时间
	this.time = param.time;
	//session id
	this.nowid = param.nowid;
	//单条数据信息
	this.ds = DaolifeAjax.getDlContentById;
}
article.prototype = {
	getHtml : function(){
		var html = [];
		html.push('<div id="article_' + this.id + '" class="article" onmouseover="doMouseover(' + this.id + ')" onmouseout="doMouseout(' + this.id + ')">');
		//头像
		html.push('<div class="articleleft">');
		html.push('<div>');
		html.push('<a href="PersonPage.action?userId=' + this.uid + '"><img src="images/myhome_30.gif" width="80" height="80" /></a>');
		html.push('</div>');
		//用户名和内容
		html.push('<div class="articleright">');
		html.push('<a href="PersonPage.action?userId=' + this.uid + '">' + this.name + '</a> ：');
		html.push(this.content);
		html.push('</div>');
		//功能
		html.push('<div class="articlerbottom">' + this.time + '<span style="float:right">');
		html.push('<a href="javascript:doForwardBox(' + this.id + ')">转发（' + this.forwardAmount + '）</a>');
		html.push(' | ');
		if(this.id != this.nowid){
			html.push('<a href="javascript:doDing(' + this.id + ')">顶它（' + this.dingAmount + '）</a></span></div>');
		}else{
			html.push('<a href="javascript:doDelete(' + this.id + ')">删除</a></span></div>');
		}
		html.push('</div>');
		html.push('</div>');
		return html.join('');
	}
	,load : function(){
		var myself = this;
		myself.ds(myself.id,function(rs){
			myself.forwardAmount = rs.retwittNum;
			myself.dingAmount = rs.upNum; 
			$('#article_' + myself.id).html(myself.getHtml());
		});
	}
}
function articleBox(param){
	this.element = [];
	this._element = [];
	this.applyTo = param.applyTo
	this.ds = param.ds;
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
	,show : function(){
		var html = [];
		html.push('<center id="articlebox">');
		html.push(this.getTopBar());
		html.push(this.getBottomBar());
		html.push('</center');
		$('#' + this.applyTo).html(html.join(''));
	}
	,push : function(item){
		this.element.push(item);
		this._element[item.id] = item;
	}
	,unshift : function(item){
		this.element.unshift(item);
		this._element[item.id] = item;
	}
	,getById : function(id){
		return this._element[id];
	}
	,getByIndex : function(index){
		return this.element[index];
	}
	,getBottomBar : function(){
		var html = [];
		if(this.pageCount > 1){
			html.push('<div class="articleboxbottombar">');
			html.push('<div class="articleboxbottombarmore" id="pagemore"><a href="javascript:doPage()">更多</a></div>');
			html.push('<div class="articleboxbottombartop"><a href="javascript:goTop()">回到顶部</a></div>');
			html.push('</div>')
		}
		return html.join('');
	}
	,getTopBar : function(){
		return '';
	}
}