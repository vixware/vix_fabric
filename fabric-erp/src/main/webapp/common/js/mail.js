// table
$(document).ready(function(){
	$("table tr").mouseover(function(){
		$(this).addClass("over");}).mouseout(function(){
			$(this).removeClass("over");})
	$("table tr:even").addClass("alt");
});