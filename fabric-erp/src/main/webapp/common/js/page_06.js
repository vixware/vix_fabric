//
$(document).ready(function(){
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
});


// 弹出层
$(document).ready(function(){
	$.fx.speeds._default = 1000;
	$(function() {
		$( "#dialog" ).dialog({
			autoOpen: false,
			width:700,
			modal: true
		});

		$( "#text" ).click(function() {
			$( "#dialog" ).dialog( "open" );
			return false;
		});
	});
});