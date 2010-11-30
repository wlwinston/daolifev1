$(function($){
	$('#regist').submit(function(){
		$(this).focus();
		return Form.isValid();
	})
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
	Form.add('yzm',DaolifeAjax.checkAuthCode,'验证码不正确','请输入验证码');
});
function reflashpic(){
	$('#picyzm').attr('src','servlet/dao.auth?time='+Math.random());
}