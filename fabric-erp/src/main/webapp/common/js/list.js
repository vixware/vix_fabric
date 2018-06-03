// JavaScript Document By Silence QQ:82568873

function getTotalHeight(){
if($.browser.msie){return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight;}
else {return self.innerHeight;}
}

//
$(document).ready(function(){
	//login
	$('.login .btn').hover(
	  function () {$(this).css('margin','2px 0 0 51px');},
	  function () {$(this).css('margin','0 0 0 50px');}
	);
	//left height
	//$("#content").css({"height": getTotalHeight()-118});
	//$("#box_left").css({"height": getTotalHeight()-118-30});
	//$("#box_right").css({"height": getTotalHeight()-118-30});
	//$("#left").css({"height": document.body.clientHeight-118});
	//switch_search
	$('#list_switch_search').toggle(
	  function () {
		  $(this).addClass("current");
		  $('#list_search_bar').slideUp('fast');
		},
	  function () {
		  $(this).removeClass("current");
		  $('#list_search_bar').slideDown('fast');
		}
	);
	//left menu
	$('#list_left h2').toggle(
	  function () {
		  $(this).next().slideDown('fast');
		  $(this).addClass("current");
		},
	  function () {
		  $(this).next().slideUp('fast');
		  $(this).removeClass("current");
		}
	);
	//version
	$('.version').hover(
	  function () {$(this).children('ul').slideDown('fast');},
	  function () {$(this).children('ul').slideUp('fast');}
	);
	$('.version li').hover(
	  function () {$(this).addClass("hover");},
	  function () {$(this).removeClass("hover");}
	);
	$('.version li').click(
	  function () {
		$('.version b').text($(this).text()); 
	});
	//table
	$("table tr").mouseover(function(){
		$(this).addClass("over");}).mouseout(function(){
			$(this).removeClass("over");})
	$("table tr:even").addClass("alt");
	//switch
	$("#list_switch").toggle(
	  function () {
		$("#list_content").removeClass("list_bg");
		$("#list_switch").addClass("off")
		$("#list_left").addClass("switch")
		$("#list_right").addClass("switch")
	  },
	  function () {
		$("#list_content").addClass("list_bg")
		$("#list_switch").removeClass("off")
		$("#list_left").removeClass("switch")
		$("#list_right").removeClass("switch")
	  });
	//test_box 
//    $('#test').click(function() { 
//        $.blockUI({ 
//		message: $('.box'),
//		css:{top:'25%',border: 'none',width: '390px',cursor:'auto',backgroundColor: '#000'}
//		});
//    }); 
//	$('.box .close').click(function() {$.unblockUI()}); 
});
