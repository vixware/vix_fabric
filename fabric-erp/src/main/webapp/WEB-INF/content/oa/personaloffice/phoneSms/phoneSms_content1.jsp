<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="${vix}/common/css/aristo/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" title="no title" charset="utf-8">
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script src="${vix}/common/js/underscore-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/backbone-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tmpl.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-debug.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-tinyPubSub.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');

	bindSearch();
	function getTotalHeight(){
		if($.browser.msie){return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight;}
		else {return self.innerHeight;}
	}
	
	if($('.listlib').length){
		$('.listlib').each(function(){
			$('strong',$('li',$(this)).first()).first().next('ul').css('display','block');
			$('strong',$('li',$(this)).first()).first().addClass('current');
		});
		$('.listlib li').each(function(){
			var $_this = $(this);
			if($('ul',this).length){
				if(!$_this.next('li').length&&!$_this.prev('li').length){
			 		$('strong',$_this).first().css('cursor','default').toggleClass('current');
			 		$('strong',$_this).first().next('ul').css('display','block');
				}
				$('strong',$_this).first().css('cursor','pointer').click(function(){
					$(this).toggleClass('current');
			 		$('ul',$_this).first().slideToggle();
			 	});
			}
		});
		$('.listlib strong,.listlib span').hover(function(){$(this).addClass('al_hover');},function(){$(this).removeClass('al_hover');})
	}
	
	if($('.w_bfb').length>0){
		$('.w_bfb').each(function(){
			$(this).append('<div class="bfb" style="width:'+$('span',this).text()+'"></div>');
		})
	}
	
	//login
	$('.login .btn').hover(
	  function () {$(this).css('margin','2px 0 0 51px');},
	  function () {$(this).css('margin','0 0 0 50px');}
	);
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
	
	$(".untitled b img").click(function () {
		var $_this = $(this).parent().parent(".untitled");
		$_this.css({ "position": "relative"});
		$_this.children('.popup').css({ "display": "block"});
		var bh = $("body").height(); 
		var pt = $_this.offset();
		if(( bh - pt.top) < 165){$_this.children('.popup').css({ "bottom": "0"});}else{$_this.children('.popup').css({ "top": "-7px"});};
	  });
	  $(".untitled span img").bind('mouseover',function () {
		var $_this = $(this).parent().parent(".untitled");
		$_this.css({ "position": "relative"});
		$_this.children('.popup').css({ "display": "block"});
		var bh = $("body").height(); 
		var pt = $_this.offset();
		if(( bh - pt.top) < 165){$_this.children('.popup').css({ "bottom": "0"});}else{$_this.children('.popup').css({ "top": "-7px"});};
	  });
	  $(".untitled").bind('mouseleave',function(){
		$(this).css({ "position": "static"});
		$(this).children('.popup').css({ "display": "none"});
	  });
	  
	  
	$(".close").click(function () {
		$(this).parent().parent().css({ "display": "none"});
		return false;
	});
	
	/**跳转到发送消息页面*/
	function messaging(){
		$.ajax({
			  url:'${vix}/oa/phoneSmsAction!goMessagingList.action',
			  success: function(html){
				  $('#page1').html(html);
			  
			  }
		});
	}
	
	/**进入发送消息直接加载*/
	$(function(){
		messaging()
	});
	
	/**跳转到未发送消息列表页面*/
	function unsentMessages(){
		$.ajax({
			  url:'${vix}/oa/phoneSmsAction!goUnsentMessages.action',
			  success: function(html){
				  $('#page1').html(html);
			  
			  }
		});
	}
	
	/**跳转到已发送消息列表页面*/
	function sendMessages(){
		$.ajax({
			  url:'${vix}/oa/phoneSmsAction!goSendMessages.action',
			  success: function(html){
				  $('#page1').html(html);
			  
			  }
		});
	}
	
</script>
<table id="table" class="list">
	<tbody>
		<div class="addleft">
			<div class="addbtn">
				<p>
					<img src="${vix}/common/img/address_book.png" />
					<s:text name="oa_send_message" />
				</p>
			</div>
			<ul class="listlib">
				<li class="liston"><strong><s:text name="发送消息" /></strong>
					<ul>
						<li><strong class="home" onclick="sendMessages(0)">查看消息</strong></li>
						<li><strong class="home" onclick="messaging(0);">发送消息</strong></li>
					</ul></li>
				<li class="liston"><strong><s:text name="oa_not_sent_message" /></strong>
					<ul>
						<li><strong class="home" onclick="unsentMessages(0);">草稿</strong></li>
					</ul></li>
			</ul>
		</div>
		<div class="addright" id="page1"></div>
	</tbody>
</table>