var Form = {
	add : function(id, ajax){
		$('#' + id).blur(function(){
			ajax($(this).val(), function(result){
				switch($('#' + id).get(0).type){
					case 'text':
						Form.text.call(this, id, result);
						break;
				}
				//eval('Form.' + $('#' + id).get(0).type + '(' + id + ',' + eval('('+result+')') +')');
			});
		});
		$('#' + id).focus(function(){
			if($('.result')) $('.result').remove();
		})
	}
	,text : function(id,result){
		if(!result){
			$('#' + id).after('<span style="color:red;" class="result"> false </span>');
		}else{
			$('#' + id).after('<span style="color:green;" class="result"> true </span>');
		}
	}
}