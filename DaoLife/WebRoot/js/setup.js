$(function($){
	$('#update').submit(function(){
		$(this).focus();
		return Form.isValid();
	})
	$('#province_select').change(function(){
		DaolifeAjax.getCitys($(this).val(),function(rs){
			var html = [];
			for(var i = 0, l = rs.length; i < l; ++i){
				html.push('<option value="' + rs[i][0] + '">' + rs[i][1] + '</option>');  
			}
			$('#city_select').html(html.join(''));
		});
		$('#province').val($('#province_select').val());
	});
	$('#city_select').change(function(){
		$('#city').val($('#city_select').val());
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
	Form.add('yzm',DaolifeAjax.checkAuthCode,'验证码不正确','请输入验证码');
	$('#province_select').val($('#province').val());
	$('#city_select').val($('#city').val());
	$('#nick').focus();
});
function reflashpic(){
	$('#picyzm').attr('src','servlet/dao.auth?time='+Math.random());
}