// menu
$(document).ready(function(){
	$("#menu_btn").toggle(
	  function () {
	  	var w = $(".menu_hidd").width()-10;
		$("#menu").animate({right: -w+'px'},1000)
		$("#menu_btn").addClass("current")
	  },
	  function () {
	  	var b = $(this).prev()
		$("#menu").animate({right:"20px"},500)
		$("#menu_btn").removeClass("current")
	  }
	)
});