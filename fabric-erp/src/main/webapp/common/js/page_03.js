// 左右拖动
$(document).ready(function(){
	$(function() {
		$( "#left" ).resizable({
			maxHeight: 680,
			minHeight: 680,
			maxWidth: 400,
			minWidth: 200,
			handles: 'e'
		});
	});
});