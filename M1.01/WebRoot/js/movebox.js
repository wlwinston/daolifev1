$(function($){
	function mbox(){}
	mbox.initialize = function(settings){
		var mb_items = $('.mbox')
		, mb_content = $('#mbox_content')
		, mb_button_left = $('#mbox_button_left')
		, mb_button_right = $('#mbox_button_right')
		, mb_width = 0;
		
		mbox.__index = 1;
		mbox.__length = mb_items.length;
		
		for(var i = 0; i < mbox.__length; ++i){
			var mb_item = $(mb_items.get(i));
			mbox.__mbItemWidth = mb_item.width();
			mb_width += mbox.__mbItemWidth;
		}
		
		mb_content.width(mb_width);
		
		
		mb_button_left.bind('click', mbox.left);
		mb_button_right.bind('click', mbox.right);
		
		
		mbox.timeout = function(){
			mbox.left();
			mbox.mouseout();
//			if(mbox.__index == mbox.__length){
//				var mb_content = $('#mbox_content');
//				mb_content.css({'left' : mbox.__mbItemWidth + 'px'});
//				mbox.__index = 1;
//			}
		}
		mbox.mouseout = function(){
			var mb_button_left = $('#mbox_button_left')
			, mb_button_right = $('#mbox_button_right');
			
			mb_button_left.hide();
			mb_button_right.hide();
			
//			mbox.setTimeout = setTimeout(mbox.timeout,5000);
		}
		
		mbox.mouseover = function(){
//			clearTimeout(mbox.setTimeout);
			mbox.setbutton();
		}
		
//		mb_content.mouseout(mbox.mouseout);
//		mb_content.mouseover(mbox.mouseover);
//		mb_button_left.mouseover(mbox.mouseover);
//		mb_button_right.mouseover(mbox.mouseover);
//		mbox.mouseout();
		mbox.setbutton();
	}
	mbox.setbutton = function(){
		var mb_button_left = $('#mbox_button_left')
		, mb_button_right = $('#mbox_button_right');
		
		mb_button_left.show();
		mb_button_right.show();
		
		if(mbox.__index == mbox.__length){ 
			mb_button_left.hide();
		}else if(mbox.__index == 1){
			mb_button_right.hide();
		}
	}
	mbox.left = function(){
		var self = $('#mbox_button_left')
		, left = mbox.__index * mbox.__mbItemWidth * -1
		, mb_content = $('#mbox_content')
		, mb_button_left = $('#mbox_button_left')
		, mb_button_right = $('#mbox_button_right');
		
		self.show();
		
		mb_content.animate({
			left : left
		},{duration:500});
		
		mbox.__index++;
		
		mbox.setbutton();
	}
	
	mbox.right = function(){
		mbox.__index--;
		
		var self = $('#mbox_button_right')
		, left = (mbox.__index-1) * mbox.__mbItemWidth * -1
		, mb_content = $('#mbox_content')
		, mb_button_left = $('#mbox_button_left')
		, mb_button_right = $('#mbox_button_right');
		
		self.show();
		
		mb_content.animate({
			left : left
		},{duration:500});
		
		mbox.setbutton();
	}
	mbox.initialize();
});