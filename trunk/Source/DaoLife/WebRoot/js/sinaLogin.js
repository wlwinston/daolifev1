$(function($){
	$('#regist').submit(function(){
		$(this).focus();
		//alert($('#sinaLoginFlag').val());
		if($('#sinaLoginFlag').val() == '0')
		{
			return Form.isValid();
		}
		else{
			return true;
		}
	})
	Form.add('nick',function(val,fn){
		if(val != null && val != ''){
			DaolifeAjax.checkUserNickName(val,function(rs){
				if(rs){
					fn.call(this,true);
				}else{
					fn.call(this,false);
				}
			});
		}else{
			fn.call(this,false);
		}
	},'昵称格式不正确或已经存在','请输入昵称');
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
		if(reg.test(val)){
			DaolifeAjax.checkUserEmail(val,function(rs){
				if(rs){
					fn.call(this,true);
				}else{
					fn.call(this,false);
				}
			});
		}else{
			fn.call(this,false);
		}
	},'邮箱格式不正确或邮箱已存在','例:example@daolife.com');
	
	Form.add('yzm',DaolifeAjax.checkAuthCode,'验证码不正确','请输入验证码');
});
function reflashpic(){
	$('#picyzm').attr('src','servlet/dao.auth?time='+Math.random());
}