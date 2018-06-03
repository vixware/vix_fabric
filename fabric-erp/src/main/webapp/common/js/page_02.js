// 左右拖动
$(document).ready(function(){
	$(function() {
		if($( "#left").length){
			$( "#left" ).resizable({
				maxHeight: 680,
				minHeight: 680,
				maxWidth: 400,
				minWidth: 200,
				handles: 'e'
			});
		}
	});
});
/* a-z字母排序 */
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});