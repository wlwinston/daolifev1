function userRegist(){
	Form.remove();
	var html = [];
	var sinaUserName = $('#sinaUserName').get(0).value;
	html.push('<table width="623" border="0" cellspacing="3" cellpadding="0">');
	html.push('<tr>');
	html.push('<th height="2" align="right">');
	html.push('</th>');
	html.push('<td>');
	html.push('<label>');
	html.push('<input type ="radio" checked onclick="userRegist()" name="sinaLoginFlag" value="0">还没有daolife账户，注册新帐户</input>&nbsp;<input type ="radio" onclick="userLogin()" name="sinaLoginFlag" value="1">已有daolife的账户</input>');
	html.push('</label>');
	html.push('</td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<th height="21" align="right">');
	html.push('<span class="allow">*</span>昵称：');
	html.push('</th>');
	html.push('<td>');
	html.push('<label>');
	html.push('<input type="text" name="user.userNickName" id="nick" value="' + sinaUserName + '" />');
	html.push('<span id="nick_info" class="info"></span>');
	html.push('</label>');
	html.push('</td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<th height="21" align="right">');
	html.push('<span class="allow">*</span>登陆密码：');
	html.push('</th>');
	html.push('<td>');
	html.push('<label>');
	html.push('<input id="password" type="password" name="user.password" value="" />');
	html.push('<span id="password_info" class="info"></span>');
	html.push('</label>');
	html.push('</td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<th height="21" align="right">');
	html.push('<span class="allow">*</span>密码确认：');
	html.push('</th>');
	html.push('<td>');
	html.push('<label>');
	html.push('<input id="repassword" type="password" name="repassword" value="" />');
	html.push('<span id="repassword_info" class="info"></span>');
	html.push('</label>');
	html.push('</td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<th height="21" align="right">');
	html.push('<span class="allow">*</span>邮箱：');
	html.push('</th>');
	html.push('<td>');
	html.push('<label>');
	html.push('<input type="text" name="user.mailadres" id="mail" value="" />');
	html.push('<input type="hidden" name="user.userInfo" id="userInfo" value="" />');
	html.push('<input type="hidden" name="user.sinaId" id="sinaId" value="" />');
	html.push('<span id="mail_info" class="info"></span>');
	html.push('</label>');
	html.push('</td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<th>&nbsp;</th>');
	html.push('<td><hr/></td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<th height="21" align="right">');
	html.push('<span class="allow">*</span>验证码：');
	html.push('</th>');
	html.push('<td>');
	html.push('<label>');
	html.push('<input type="text" size="5" style="width:70px;" height="26" id="yzm" />');
	html.push('<img src="servlet/dao.auth" id="picyzm" style="border:0;width:100px;margin:0;float:left botton;" />&nbsp;<a href="javascript:reflashpic()">换一张</a>');
	html.push('<span id="yzm_info" class="info"></span>');
	html.push('</label>');
	html.push('</td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<td colpan=3>&nbsp;</td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<td height="21" align="center">');
	html.push('</td>');
	html.push('<td>');
	html.push('<input type="image" src="images/zuce1.gif" />');
	html.push('</td>');
	html.push('</tr>');
	html.push('</table>');
	$('#regist').get(0).innerHTML = html.join('');
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
}
function userLogin(){
	Form.remove();
	var html = [];
	var sinaUserName = $('#sinaUserName').get(0).value;
	html.push('<table width="623" border="0" cellspacing="3" cellpadding="0">');
	html.push('<tr>');
	html.push('<th height="2" align="right">');
	html.push('</th>');
	html.push('<td>');
	html.push('<label>');
	html.push('<input type ="radio" onclick="userRegist()" name="sinaLoginFlag" value="0">还没有daolife账户，注册新帐户</input>&nbsp;<input type ="radio" onclick="userLogin()" checked name="sinaLoginFlag" value="1">已有daolife的账户</input>');
	html.push('</label>');
	html.push('</td>');
	html.push('</tr>');
	html.push('<th height="21" align="right">');
	html.push('<span class="allow">*</span>帐号：');
	html.push('</th>');
	html.push('<td>');
	html.push('<label>');
	html.push('<input type="text" name="existUser" id="existUser" value="' + sinaUserName + '" />');
	html.push('<span id="existUser_info" class="info"></span>');
	html.push('</label>');
	html.push('</td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<th height="21" align="right">');
	html.push('<span class="allow">*</span>密码：');
	html.push('</th>');
	html.push('<td>');
	html.push('<label>');
	html.push('<input id="existPassword" type="password" name="existPassword" value="" />');
	html.push('<span id="existPassword_info" class="info"></span>');
	html.push('</label>');
	html.push('</td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<th>&nbsp;</th>');
	html.push('<td><hr/></td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<th height="21" align="right">');
	html.push('<span class="allow">*</span>验证码：');
	html.push('</th>');
	html.push('<td>');
	html.push('<label>');
	html.push('<input type="text" size="5" style="width:70px;" height="26" id="yzm" />');
	html.push('<img src="servlet/dao.auth" id="picyzm" style="border:0;width:100px;margin:0;float:left botton;" />&nbsp;<a href="javascript:reflashpic()">换一张</a>');
	html.push('<span id="yzm_info" class="info"></span>');
	html.push('</label>');
	html.push('</td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<td colpan=3>&nbsp;</td>');
	html.push('</tr>');
	html.push('<tr>');
	html.push('<td height="21" align="center">');
	html.push('</td>');
	html.push('<td>');
	html.push('<input type="image" src="images/zuce1.gif" />');
	html.push('</td>');
	html.push('</tr>');
	html.push('</table>');
	$('#regist').get(0).innerHTML = html.join('');
	Form.add('existUser',function(val,fn){
		if(val == ''){
			fn.call(this,false);
		}else{
			fn.call(this,true);
		}
	},'确认密码输入不正确','请再次输入密码');
	Form.add('existPassword',function(val,fn){
		if(val == ''){
			fn.call(this,false);
		}else{
			fn.call(this,true);
		}
	},'密码格式输入不正确','请输入密码');
	Form.add('yzm',DaolifeAjax.checkAuthCode,'验证码不正确','请输入验证码');
}	
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
	userRegist();
});
function reflashpic(){
	$('#picyzm').attr('src','servlet/dao.auth?time='+Math.random());
}