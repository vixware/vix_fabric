// + - 隐藏
$(document).ready(function(){
	$(".hidd").toggle(
	  function () {
		$(this).addClass("show");
		$(this).removeClass("hidd");
		$(this).parent().parent().parent().next().addClass("none");
	  },
	  function () {
		$(this).removeClass("show");
		$(this).addClass("hidd");
		$(this).parent().parent().parent().next().removeClass("none");
	  }
	)
});