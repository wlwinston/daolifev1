var Form = {
	valid : []
	,isValid : function(){
		for(var name in Form.valid){
			if(!Form.valid[name]){
				return false;
			}
		}
		return true;
	}
	,add : function(id, ajax, ftext, ttext){
		ftext = ftext || '';
		ttext = ttext || '';
		Form.valid[id] = false;
		$('#' + id).blur(function(){
			ajax($(this).val(), function(result){
				switch($('#' + id).get(0).type){
					case 'password':
					case 'text':
						Form.text.call(this, id, result, ftext);
						break;
				}
			});
		});
		$('#' + id).focus(function(){
			$('#' + id).css({
				backgroundColor:'#F4FFD4'
				,borderColor:'#A5C760'
			});
			if($('#' + id + '_info')){
				$('#' + id + '_info').css({
					color : '#A5C760' 
					,fontSize : '13px' 
				});
			}
			$('#' + id + '_info').html(ttext);
		});
		if($('#' + id + '_info')){
			$('#' + id + '_info').html(ttext);
		};
	}
	,text : function(id, result, ftext){
		if(!result){
			Form.valid[id] = false;
			$('#' + id).css({
				backgroundColor:'#FFCCCC'
				,borderColor:'#FF0000'
			})
			$('#' + id + '_info').html(ftext);
			$('#' + id + '_info').css({
				color : '#FF0000'
				,fontSize : '13px'
			});
		}else{
			Form.valid[id] = true;
			$('#' + id + '_info').css({
				color : '#A5C760'
				,fontSize : '13px' 
			});
			$('#' + id + '_info').html('正确');
		}
	}
}
$(function($){
	$('#regist').submit(function(){
		return Form.isValid();
	})
	Form.add('username',function(val,fn){
		if(val != ''){
			fn.call(this,true);
		}else{
			fn.call(this,false);
		}
	},'注册的登陆账号已经存在','请输入登陆账号');
	Form.add('password',function(val,fn){
		if((val.length > 5) && (val.length < 17)){
			fn.call(this,true);
		}else{
			fn.call(this,false);
		}
	},'密码格式不正确,输入6-16位密码','请输入登陆密码');
	Form.add('repassword',function(val,fn){
		if($('#password').val() != val || val == ''){
			fn.call(this,false);
		}else{
			fn.call(this,true);
		}
	},'确认密码输入不正确','请再次输入密码');
	Form.add('mail',function(val,fn){
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    				fn.call(this,reg.test(val));
	},'邮箱格式不正确','例:example@daolife.com');
});