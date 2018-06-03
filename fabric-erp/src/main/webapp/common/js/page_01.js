// 左右拖动
$(document).ready(function(){
	$(function() {
		if($( "#left").length){
			$( "#left").resizable({
				maxHeight: 680,
				minHeight: 680,
				maxWidth: 400,
				minWidth: 300,
				handles: 'e'
			});
		}
	});
});

//// draggable
//$(document).ready(function(){						   
//	var w = parseInt($("body").width()- 450)/2; 
//	$(".draggable").css({"left":w})
//	$("#text").click(
//	  function () {
//		$(".draggable").slideDown('fast');
//	});
//	$("#hidd_draggable").click(
//	  function () {
//		$(".draggable").css({ "display": "none"});
//	});
//});
//
//// draggable 可移动
//$(document).ready(function(){
//	$(function() {
//		$( ".draggable" ).draggable({
//				containment: "body",
//				scroll: false,
//				handle: 'h3'
//		})
//	});
//});

// 弹出层
$(document).ready(function(){
	$.fx.speeds._default = 1000;
	$(function() {
		$( "#dialog" ).dialog({
			autoOpen: false,
			modal: true
		});

		$( "#text" ).click(function() {
			$( "#dialog" ).dialog( "open" );
			return false;
		});
	});
});
/* a-z字母排序 */
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});

$(function(){
	if($('.easyui-treegrid').length>0){
		$('.easyui-treegrid').width($('.easyui-treegrid').parent().width());
		var oHead = document.getElementsByTagName('head').item(0);
		var oScript= document.createElement("script");
		oScript.type = "text/javascript";
		oScript.src="js/jquery.easyui.min.js";
		oHead.appendChild( oScript);
	}
});


















