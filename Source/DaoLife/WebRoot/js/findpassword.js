$(function($){
	$('#regist').submit(function(){
		$(this).focus();
		return Form.isValid();
	})
	Form.add('username',function(val,fn){
		if(val != null && val != ''){
			DaolifeAjax.checkUserName(val,function(rs){
				if(rs){
					fn.call(this,false);
				}else{
					fn.call(this,true);
				}
			});
		}else{
			fn.call(this,false);
		}
	},'格式不正确或账号不存在','请输入登陆账号');
	Form.add('yzm',DaolifeAjax.checkAuthCode,'验证码不正确','请输入验证码');
});
function reflashpic(){
	$('#picyzm').attr('src','servlet/dao.auth?time='+Math.random());
}