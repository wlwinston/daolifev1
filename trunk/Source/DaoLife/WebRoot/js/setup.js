$(function($){
	$('#regist').submit(function(){
		$(this).focus();
		return Form.isValid();
	})
	$('#province').change(function(){
		var html = [];
		switch($(this).val()){
			case '1':
				html.push('<option>海淀区</option>');
				html.push('<option>朝阳区</option>');
				break;
			case '2':
				html.push('<option>淮海路</option>');
				html.push('<option>南京路</option>');
				break;
		}
		$('#region').html(html.join(''));
	});
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
	Form.add('yzm',DaolifeAjax.checkAuthCode,'验证码不正确','请输入验证码');
});
function reflashpic(){
	$('#picyzm').attr('src','servlet/dao.auth?time='+Math.random());
}