// skin
function getObject(elementId) { //获取指定id的object
if (document.getElementById) {
   return document.getElementById(elementId);
} else if (document.all) {
   return document.all[elementId];
} else if (document.layers) {
   return document.layers[elementId];
}
}
function changeStyle(id,cssid,num){//切换样式
var stylesheet=getObject(cssid).href="css/"+cssid+"_0"+id+".css";
//document.cookie="stylesheet="+escape(stylesheet);//写入Cookie
//alert(document.cookie);
//alert(stylesheet);
	if(id == 1||id==4||id==6) $('.gt-skin').attr('class','gt-skin gt-skin-vista');
	else if(id == 2) $('.gt-skin').attr('class','gt-skin gt-skin-pink');
	else if(id == 3||id == 5) $('.gt-skin').attr('class','gt-skin gt-skin-mac');
	
	for(i=1;i<=num;i++){
			if(i==id){
				document.getElementById(cssid+"_0"+i).className="this";
			}else{
				document.getElementById(cssid+"_0"+i).className="";
			}
	}
}
//function initStyle(){ //初始化样式，如果cookie存在样式，则加载cookie样式，否则加载默认样式
//   if(/stylesheet=([^;]+)/.test(document.cookie))//判断是否存在cookie.
//    getObject("skin").href=unescape(RegExp.$1);
//    //alert(/stylesheet=([^;]+)/.test(document.cookie));
//}
//initStyle();


// Flickr
$(document).ready(function(){
	/*$("input[type='text'],input[type='password']").focus(function(){
		$(this).css({"background":"#f5f5f5","border":"1px solid #FF0000"});
	});*/
	$("input[type='text'],input[type='password']").focusout(function(){
		$(this).removeAttr("style");
	});
	
	$(".sbtn-green").hover(function(){
		$(this).css({"background-image":"url(img/btn_04.gif)","color":"#FFF"});
	},function(){
		$(this).removeAttr("style");
	});
	
	$(".sbtn-red").hover(function(){
		$(this).css({"background-image":"url(img/skin_02/btn_04.gif)","color":"#FFF"});
	},function(){
		$(this).removeAttr("style");
	});
	
	$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
		$(this).addClass("btnhover");
	},function(){
		$(this).removeClass("btnhover");
	})
	
	$('.sbtn').hover(function(){
		$(this).addClass("sbtnhover");
	},function(){
		$(this).removeClass("sbtnhover");
	})
	
	
	
	$("#nicemenu span.head_menu").click(function(){ 
								
		$("span.head_menu").removeClass('active');
		
		submenu = $(this).parent().find("div.flick_menu");
		
		if(submenu.css('display')=="block"){
			$(this).removeClass("active"); 	
			submenu.hide(); 		
			//$(this).attr('src','arrow_hover.png');									
		}else{
			$(this).addClass("active"); 	
			submenu.fadeIn(); 		
			//$(this).attr('src','arrow_select.png');	
		}
		
		$("div.flick_menu:visible").not(submenu).hide();
		//$("#nicemenu img.arrow").not(this).attr('src','arrow.png');
						
	})
	/*$("#nicemenu img.arrow").click(function(){ 
								
		$("span.head_menu").removeClass('active');
		
		submenu = $(this).parent().parent().find("div.flick_menu");
		
		if(submenu.css('display')=="block"){
			$(this).parent().removeClass("active"); 	
			submenu.hide(); 		
			//$(this).attr('src','arrow_hover.png');									
		}else{
			$(this).parent().addClass("active"); 	
			submenu.fadeIn(); 		
			//$(this).attr('src','arrow_select.png');	
		}
		
		$("div.flick_menu:visible").not(submenu).hide();
		//$("#nicemenu img.arrow").not(this).attr('src','arrow.png');
						
	})*/
//	.mouseover(function(){ $(this).attr('src','arrow_hover.png'); })
//	.mouseout(function(){ 
//		if($(this).parent().parent().find("div.flick_menu").css('display')!="block"){
//			$(this).attr('src','img/arrow.png');
//		}else{
//			$(this).attr('src','img/arrow_select.png');
//		}
//	});

	$("#nicemenu span.head_menu").mouseover(function(){ $(this).addClass('over')})
								 .mouseout(function(){ $(this).removeClass('over') });
	
	$("#nicemenu div.flick_menu").mouseover(function(){ $(this).fadeIn(); })
							   .blur(function(){ 
							   		$(this).hide();
									$("span.head_menu").removeClass('active');
								});		
								
	$(document).click(function(event){ 		
			var target = $(event.target);
			if (target.parents("#nicemenu").length == 0) {				
				$("#nicemenu span.head_menu").removeClass('active');
				$("#nicemenu div.flick_menu").hide();
				//$("#nicemenu img.arrow").attr('src','arrow.png');
			}
	});			   
							   
								   
});

// search_advanced
$(document).ready(function(){
	$("#search_advanced").toggle(
	  function () {
		$("#c_head").addClass("advanced");
		if($("#search_advanced").attr('class') == "zh") $("#search_advanced").text("普通搜索");
		else $("#search_advanced").text("Basic Search");
	  },
	  function () {
		$("#c_head").removeClass("advanced");
		if($("#search_advanced").attr('class') == "zh") $("#search_advanced").text("高级搜索");
		else $("#search_advanced").text("Advanced Search");
	  }
	)
});

// list_check
$(document).ready(function() 
{
$(".list_check").bind('mouseover',function()
{
$(".list_check").addClass('posr');
$(".list_check ul").css('display','block');
}).bind('mouseleave',function()
{
$(".list_check").removeClass('posr');
$(".list_check ul").css('display','none');
});
});

// drop_news
$(document).ready(function() 
{
$(".drop_news li").bind('mouseover',function()
{
$(this).children('ul').css('display','block');
}).bind('mouseleave',function()
{
$(this).children('ul').css('display','none');
});
}); 

// popup
$(document).ready(function(){
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
});

// menu list
$(document).ready(function(){
	//一级
	$(".drop>ul>li").bind('mouseover',function() // 顶级菜单项的鼠标移入操作 
	{
	$(this).children('ul').delay(0).slideDown('fast');
	}).bind('mouseleave',function() // 顶级菜单项的鼠标移出操作 
	{
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
	}); 
	//二级
	$('.drop>ul>li>ul li').bind('mouseover',function() // 子菜单的鼠标移入操作 
	{ 
	$(this).children('ul').delay(0).slideDown('fast'); 
	}).bind('mouseleave',function() // 子菜单的鼠标移出操作 
	{ 
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast'); 
	});
	//三级
	$('.drop>ul>li>ul>li>ul li').bind('mouseover',function() // 子菜单的鼠标移入操作 
	{ 
	$(this).children('ul').delay(0).slideDown('fast'); 
	}).bind('mouseleave',function() // 子菜单的鼠标移出操作 
	{ 
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast'); 
	}); 
	//四级
	$('.drop>ul>li>ul>li>ul>li>ul li').bind('mouseover',function() // 子菜单的鼠标移入操作 
	{ 
	$(this).children('ul').delay(0).slideDown('fast'); 
	}).bind('mouseleave',function() // 子菜单的鼠标移出操作 
	{ 
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast'); 
	}); 
	//五级
	$('.drop>ul>li>ul>li>ul>li>ul>li>ul li').bind('mouseover',function() // 子菜单的鼠标移入操作 
	{ 
	$(this).children('ul').delay(0).slideDown('fast'); 
	}).bind('mouseleave',function() // 子菜单的鼠标移出操作 
	{ 
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast'); 
	}); 
	//六级
	$('.drop>ul>li>ul>li>ul>li>ul>li>ul>li>ul li').bind('mouseover',function() // 子菜单的鼠标移入操作 
	{ 
	$(this).children('ul').delay(0).slideDown('fast'); 
	}).bind('mouseleave',function() // 子菜单的鼠标移出操作 
	{ 
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast'); 
	}); 
	
	$(".sub_menu .drop>ul>li").hover(function() // sub_menu菜单项的鼠标移入操作 
	{
		$(this).addClass("sub_menu_hover");
	},function() // sub_menu菜单项的鼠标移出操作 
	{
		$(this).removeClass("sub_menu_hover");
	}); 
	
	
	/*$('.drop ul li').unbind("mouseover");
	$('.drop ul li').unbind("mouseleave");*/
	
	/*$('.drop_nav ul li a').bind('mouseover',function(){
		var $_this = $(this);
		if($_this.parent('li').hasClass('nav_arrow')){
			$_this.parent('li').parent('ul').find('.hover').removeClass('hover');
			$_this.addClass('hover');
		}
		if($_this.parent('li').parent('ul').find('.fly') && !$_this.parent('li').hasClass('btn')){
			$_this.parent('li').parent('ul').find('.fly_hover').removeClass('fly_hover');
			
			if($_this.parent('li').hasClass('fly')){
				$_this.parent('li').addClass('fly_hover');
			}
		}

		$_this.parent('li').parent('ul').find('li ul').hide();
		$_this.parent('li').children('ul').slideDown('fast').stop(true, true);
		return false;
	});
	$('.drop_nav>ul').bind('mouseover',function(){
		$('.drop_nav>ul').not($(this)).find('li ul').hide();
		$('.drop_nav>ul').not($(this)).find('.hover').removeClass('hover');
	});
	$('.drop_nav ul li a').click(function(){
		$('.drop_nav ul li ul').hide();
		$('.drop_nav ul').find('.hover').removeClass('hover');
		$('.drop_nav ul').find('.fly_hover').removeClass('fly_hover');
	});
	$(document).click(function(event){ 		
			var target = $(event.target);
			if (target.parents(".drop_nav").length == 0) {	
				alert(0);			
				$('.drop_nav ul li ul').hide();
				$('.drop_nav ul').find('.hover').removeClass('hover');
			}
			else{
				
			}
	});*/
	/*$('.drop_nav>ul>li>a').bind('click',function(){
		$(this).next('ul').show();
		//$('.drop_nav>ul').not($(this)).find('li ul').hide();
		//$('.drop_nav>ul').not($(this)).find('.hover').removeClass('hover');
	});*/
	
	/*$('.drop_nav>ul#nav_menu>li>a').bind('click',function(){
		var $_this = $(this);
		if($_this.parent('li').hasClass('nav_arrow')){
			$_this.parent('li').parent('ul').find('.hover').removeClass('hover');
			$_this.addClass('hover');
		}
		if($_this.parent('li').parent('ul').find('.fly') && !$_this.parent('li').hasClass('btn')){
			$_this.parent('li').parent('ul').find('.fly_hover').removeClass('fly_hover');
			
			if($_this.parent('li').hasClass('fly')){
				$_this.parent('li').addClass('fly_hover');
			}
		}

		$_this.parent('li').parent('ul').find('li ul').hide();
		$_this.parent('li').children('ul').slideDown('fast').stop(true, true);
		return false;
	});*/
	$('.drop_nav .nav_arrow	>a').bind('click',function(){
		var $_this = $(this);
		//$_this.parent('li').parent('ul').find('.hover').removeClass('hover');
		$_this.addClass('hover');
		$_this.next('ul').show();
		return false;
	});
	$('.drop_nav .nav_arrow>a').bind('mouseover',function(){
		var $_this = $(this);
		if($('.drop_nav>ul>li').has("ul:visible").length >0){
			$_this.parent('li').parent('ul').find('.hover').removeClass('hover');
			$_this.addClass('hover');
			$('ul',$_this.parent('li').parent('ul')).hide();
			$_this.next('ul').show();
		}
		return false;
	});
	/*$('.drop_nav>ul>li>a').bind('mouseover',function(){
		var $_this = $(this);
		if($('.drop_nav>ul>li').has("ul:visible").length >0){
			if($_this.parent('li').hasClass('nav_arrow')){
				$_this.parent('li').parent('ul').find('.hover').removeClass('hover');
				$_this.addClass('hover');
			}
			if($_this.parent('li').parent('ul').find('.fly') && !$_this.parent('li').hasClass('btn')){
				$_this.parent('li').parent('ul').find('.fly_hover').removeClass('fly_hover');
				
				if($_this.parent('li').hasClass('fly')){
					$_this.parent('li').addClass('fly_hover');
				}
			}
	
			$_this.parent('li').parent('ul').find('li ul').hide();
			$_this.parent('li').children('ul').slideDown('fast').stop(true, true);
		}
		return false;
	});*/
	
	$('.drop_nav .nav_arrow ul li a').bind('mouseover',function(){
		var $_this = $(this);
		
		if($_this.parent('li').parent('ul').find('.fly') && !$_this.parent('li').hasClass('btn')){
			$_this.parent('li').parent('ul').find('.fly_hover').removeClass('fly_hover');
			
			if($_this.parent('li').hasClass('fly')){
				$_this.parent('li').addClass('fly_hover');
			}
		}

		$_this.parent('li').parent('ul').find('li ul').hide();
		$_this.parent('li').children('ul').slideDown('fast').stop(true, true);
		return false;
	});
	
	$('.drop_nav .nav_arrow ul a').click(function(){
		$('.drop_nav .fly_hover .fly_hover ul').hide();
		$('.drop_nav .fly_hover ul').hide();
		$('.drop_nav .nav_arrow ul').hide();
		
		$('.drop_nav ul').find('.fly_hover').removeClass('fly_hover');
		$('.drop_nav ul').find('.hover').removeClass('hover');
		return false;
	});
	
	$(document).click(function(event){ 		
		var target = $(event.target);
		if (target.parents(".drop_nav").length == 0) {	
			//alert(0);			
			$('.drop_nav .fly_hover .fly_hover ul').hide();
			$('.drop_nav .fly_hover ul').hide();
			$('.drop_nav .nav_arrow ul').hide();
			
			$('.drop_nav ul').find('.fly_hover').removeClass('fly_hover');
			$('.drop_nav ul').find('.hover').removeClass('hover');
		}
		else{
			
		}
	});
	
	
}); 

// left right
$(document).ready(function(){
	$("#switch_box").toggle(
	  function () {
		$("#switch_box").addClass("off")
		$("#left").addClass("switch")
		$("#right").addClass("switch")
		$(".left_head").addClass("wid7px")
		$(".left_content").addClass("current")
		$("#left").css({ "width": "7px"})
	  },
	  function () {
		$("#switch_box").removeClass("off")
		$("#left").removeClass("switch")
		$("#right").removeClass("switch")
		$(".left_head").removeClass("wid7px")
		$(".left_content").removeClass("current")
		$("#left").css({ "width": "252px"})
	  }
	)
});
$(document).ready(function(){
	$("#switch_left").toggle(
	  function () {
		$("#switch_left").addClass("off")
		$("#m_left").addClass("switch")
		$("#m_content").addClass("switch_left")
		$("#mail_list").css({ "width": ""})
		$("#m_left").css({ "width": "7px"})
		$("#m_left .m_box").css({ "display": "none"})
	  },
	  function () {
		$("#switch_left").removeClass("off")
		$("#m_left").removeClass("switch")
		$("#m_content").removeClass("switch_left")
		$("#m_left").css({ "width": "207px"})
		$("#m_left .m_box").css({ "display": "block"})
	  }
	)
});
$(document).ready(function(){
	$("#switch_right").toggle(
	  function () {
		$("#switch_right").removeClass("off")
		$("#m_right").addClass("switch")
		$("#m_content").addClass("switch_right")
		$("#mail_list").css({ "width": ""})
		$("#m_right").css({ "width": "7px"})
		$("#m_right .m_box").css({ "display": "none"})
	  },
	  function () {
		$("#switch_right").addClass("off")
		$("#m_right").removeClass("switch")
		$("#m_content").removeClass("switch_right")
		$("#m_right").css({ "width": "207px"})
		$("#m_right .m_box").css({ "display": "block"})
	  }
	)
});

// pos_menu
$(document).ready(function(){
	$("#show_menu").mouseover(
	  function () {
		$("#pos_menu").slideDown('fast');
	});
	$("#hidd_menu,#pos_menu a").click(function () {
		$("#pos_menu").slideUp('fast'); 
	});
//	$(document).click(function(event){ 		
//			var target = $(event.target);
//			if (target.parents("#pos_menu").length == 0) {
//				$("#pos_menu").slideUp('fast'); 
//			}
//	});
});


// tab
function tab(num,befor,id,e){
	var el=e.target?e.target.parentNode:e.srcElement.parentNode;
	var pa=el.parentNode.getElementsByTagName("li");
	for(var i=0;i<pa.length;i++){
		//pa[i].className="";
		$(pa[i]).removeClass("current");
	}
	//el.className="current";
	$(el).addClass("current");
	
	for(i=1;i<=num;i++){
		try{
			if(i==befor){
				document.getElementById(id+i).style.display="block";
			}else{
				document.getElementById(id+i).style.display="none";
			}
		}catch(e){ }
	}
}
function menu (id){
	$(".c_menu li").removeClass("c_current")
	$(".c_menu li").eq(id).addClass("c_current")
	$(".c_content").css({ "display": "none"});
	$(".c_content").eq(id).css({ "display": "block"});
}
function statistics_menu (id){
	$(".statistics_menu li").removeClass("statistics_menu_current")
	$(".statistics_menu li").eq(id).addClass("statistics_menu_current")
	$(".statistics").css({ "display": "none"});
	$(".statistics").eq(id).css({ "display": "block"});
}
function menu_add (){
	//alert($("#menu_add li").length);
	var num = $("#menu_add li").length;
	$("#menu_add").append('<li><a onclick="javascript:menu(\''+num+'\')"><em>DEMO</em></a></li>');
	$(".content").append('<div id="c'+num+'" class="c_content" style="display:none;">'+
							'<div class="left_box connectedSortable ui-sortable">'+
								'<div class="box_content">'+
								'<div class="c_title">'+
									'<span class="left_bg"></span>'+
									'<span class="right_bg"></span>'+
									'<i><a href="#" class="close">[关闭]</a></i>'+
									'<strong>New demo, ID c'+num+'</strong>'+
								'</div>'+
								'<div class="c_box">'+
									'<div class="box_news">'+
										'<h3>这是新添加的 demo，ID为 c'+num+'</h3>'+
										'<p>这是新添加的 demo，ID为 c'+num+'，js: all.js line 316 menu_add()</p>'+
									'</div>'+
								'</div>'+
								'<div class="c_foot">'+
									'<span class="left_bg"></span>'+
									'<span class="right_bg"></span>'+
								'</div>'+
							'</div></div>'+
							'<div class="right_box connectedSortable ui-sortable"></div></div>');
	$( ".connectedSortable" ).sortable({
			connectWith: ".connectedSortable",
			handle: '.c_title',
			opacity: 0.4
		})
}
function change(txt){
	$("#nav_change").html(txt)
}
$(document).ready(function(){
	$(".right_menu li").hover(
		function () {
		$(this).addClass("current_over")
	  },
		function () {
		$(this).removeClass("current_over")
	  }
	);
});

// m_menu
function m_menu (id){
	$(".m_menu li").removeClass("m_current")
	$(".m_menu li").eq(id).addClass("m_current")	
	$("#m_left .m_txt").css({ "display": "none"});	
	$("#tree_0"+id).css({ "display": "block"});
}
// table
$(document).ready(function(){
	/*$("table tr").mouseover(function(){
		$(this).addClass("over");
	}).mouseout(function(){
		$(this).removeClass("over");
	});*/
	$("table tr").live('mouseover',function(){
		$(this).addClass("over");
	}).live('mouseout',function(){
		$(this).removeClass("over");
	});
	
	$("table tr:even").addClass("alt");
});

// s_select
function show(){
	document.getElementById("s_select").style.display="block";
}
function hidd(){
	document.getElementById("s_select").style.display="none";
}
$(document).ready(function(){
	$("#search_btn").click(
	  function () {
		$("#search").addClass("show");
		$("#s_select").css({ "display": "block"});
	});
	$("#s_select a").click(function(){
		$("#s_select").hide();
	})
	$(document).click(function(event){ 		
			var target = $(event.target);
			if (target.parents("#search").length == 0) {
			$("#search").removeClass("show");
			$("#s_select").css({ "display": "none"});
			}
	});
});
$(function(){
	$('#navHideButton').hover(function(){
		$(this).addClass('navHideButton');
	},function(){
		$(this).removeClass('navHideButton');
	});
	$('#navHideButton').click(function(){
		$('.head').slideUp(500,function(){
			if($('#onresizeBox').length>0){
				extHeight = 168;
				findDimensions();
			}
		});
		$('#dcMenuSugarCube').addClass('dcMenuSugarCubeOn');
	});
	$('#dcMenuSugarCube').hover(function(){
		$('#dcMenuSugarCube').addClass('dcMenuSugarCubeHover');
	},function(){
		$('#dcMenuSugarCube').removeClass('dcMenuSugarCubeHover');
	});
	$('#dcMenuSugarCube').click(function(){
		$(this).toggleClass('dcMenuSugarCubeOn');
		$('.head').slideToggle(500,function(){
			if($('#onresizeBox').length>0){
				if($('#top_bar').next('.head').is(':visible')){
					//alert('显示');
					extHeight = 258;
					findDimensions();
				}
				else if($('#top_bar').next('.head').is(':hidden')){
					//alert('隐藏');
					extHeight = 168;
					findDimensions();
				}
			}
		});
	});
	$('#numBtn').click(function(){
		$('#numBtn').parent("li").toggleClass("current");
		$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
			$('#number').css('overflow','visible');
		});
		return false;
	});
	/*$('#nav_menu li.nav_arrow').hover(function(){
		//var $_this = $(this);
		if(!$(this).hasClass('btn')) $('a',this).first().addClass('hover');
	},function(){
		$('a',this).first().removeClass('hover');
	});
	
	$('#nav_menu li.fly').hover(function(){
		$(this).addClass('fly_hover');
	},function(){
		$(this).removeClass('fly_hover');
	});*/
	
	
	
	
	//自动tab溢出
	if($('.right_menu ul').length)
	{
		var tabBoxWidth = $('.right_menu ul').width();
		var tabLiAllWidth = ($('.right_menu ul li').width()+parseInt($('.right_menu ul li').css('margin-right'))+parseInt($('.right_menu ul li').css('margin-left'))+parseInt($('.right_menu ul li').css('border-left-width'))+parseInt($('.right_menu ul li').css('border-right-width'))+parseInt($('.right_menu ul li').css('padding-left'))+parseInt($('.right_menu ul li').css('padding-right')))*$('.right_menu ul li').length;
		
		if(tabBoxWidth<tabLiAllWidth)
		{
			$('.right_menu').css({padding:'0 24px'});
			$('.right_menu ul').jcarousel({
				wrap: null,
				scroll: 2,
				animation:300,
				auto:0,
				itemLoadCallback: null
			});
			
			$('.jcarousel-prev,.jcarousel-next').hover(function(){
				$(this).addClass('jcarousel-hover');
			},function(){
				$(this).removeClass('jcarousel-hover');
			});
		}
	}
	
	//面包屑
	if($('#breadCrumb').length)
	{
		$("#breadCrumb").jBreadCrumb({previewWidth:10});
	}
	
	
	
	//提示
	if($('input.sweet-tooltip').length){
		$('input.sweet-tooltip').focus(function() {
			
			tooltip				= $(this);
			tooltipText 		= tooltip.attr('data-text-tooltip');
			tooltipClassName	= 'tooltip';
			tooltipClass		= '.' + tooltipClassName;
			
			if(tooltip.hasClass('showed-tooltip')) return false;
			
			tooltip.addClass('showed-tooltip')
					 .after('<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">'+tooltipText+'</div><div class="tooltip_b"></div></div>');
	
			
			tooltipPosTop 	= tooltip.position().top - $(tooltipClass).outerHeight() - 10;
			tooltipPosLeft = tooltip.position().left;
			tooltipPosLeft = tooltipPosLeft - (($(tooltipClass).outerWidth()/2) - tooltip.outerWidth()/2);
			
			$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop })
								.animate({'opacity':'1', 'marginTop':'0'}, 500);
			
		}).focusout(function() {
			
			$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
					
				$(this).remove();
				tooltip.removeClass('showed-tooltip');
				
			});
		});
	}
	
	
	
	//时间类型
	if($('.time_aoto_input').length){
		$('.time_aoto_input').click(function(){
			var fmt = $(this).attr('rel');
			WdatePicker({dateFmt:fmt,skin:'blue'});
		});
	}
	
	
	//消息提示框
	
	if($('#Notification').length){
		$('#Notification')
			.jnotifyInizialize({
				oneAtTime: false,
				appendType: 'append'
			});
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
				//else{
					$('strong',$_this).first().css('cursor','pointer').click(function(){
						$(this).toggleClass('current');
						$('ul',$_this).first().slideToggle();
					});
				//}
			}
		});
		$('.listlib strong,.listlib span').hover(function(){$(this).addClass('al_hover');},function(){$(this).removeClass('al_hover');})
	}
	
	if($('.w_bfb').length>0){
		$('.w_bfb').each(function(){
			$(this).append('<div class="bfb" style="width:'+$('span',this).text()+'"></div>');
		})
	}
	
	
});

function showMessage(msgText){
	$('#Notification').jnotifyAddMessage({
		text:msgText,
		permanent: false
	});
}

function showErrorMessage(msgText){
	$('#Notification').jnotifyAddMessage({
		text:msgText,
		permanent: true,
		type: 'error'
	});
}

function showTime(id,fmt){
	WdatePicker({el:id,dateFmt:fmt,skin:'blue'});
}


//gread 编辑
function editNode(doc){
	var node = $('#'+doc).treegrid('getSelected');
	if (node){
		$('#'+doc).treegrid('beginEdit',node.id);
	}
}
//gread 保存
function saveNode(doc){
	var node = $('#'+doc).treegrid('getSelected');
	if (node){
		for(var i=0; i<$('#'+doc).parent('.datagrid-view').find('.datagrid-row').length;i++)
		{
			if($('#'+doc).parent('.datagrid-view').find('.datagrid-row').eq(i).find('input').length > 0){
				$('#'+doc).treegrid('endEdit',$('#'+doc).parent('.datagrid-view').find('.datagrid-row').eq(i).attr('node-id'));
			}
		}
	}
}
//gread 取消
function cancelNode(doc){
	var node = $('#'+doc).treegrid('getSelected');
	
	if (node){
		for(var i=0; i<$('#'+doc).parent('.datagrid-view').find('.datagrid-row').length;i++)
		{
			if($('#'+doc).parent('.datagrid-view').find('.datagrid-row').eq(i).find('input').length > 0){
				$('#'+doc).treegrid('cancelEdit',$('#'+doc).parent('.datagrid-view').find('.datagrid-row').eq(i).attr('node-id'));
			}
		}
	}
}


//展开 编辑表格
$(function(){
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle();
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
})

//帮助弹窗
$(document).ready(function(){
if($("#help").length > 0){
	$.fx.speeds._default = 1000;
	$(function() {
		$( "#helpContent" ).dialog({
			width:800,
			autoOpen: false,
			modal: true
		});

		$("#help").click(function() {
			$( "#helpContent" ).dialog("open");
			return false;
		});
	});
}
});

$(function(){
	if($(".np_dd_bfb").length>0){
		$(".np_dd_bfb").each(function(){
			$(this).parent("td").css("width",100);
			$("span",this).css("width",$(this).attr("title"));
		});
	}
	if($(".np_left dl").length > 0){
		$(".np_left dl dt b").click(function(){
			$(this).parent("dt").next("dd").toggle();
			$(this).parent("dt").toggleClass("np_close");
		});
	}
	if($(".dashstatusmsg .zpcomment").length>0){
		$(".dashstatusmsg .zpcomment").click(function(){
			$(this).next(".plbox").toggle();
		});
	}
	if($('.msgtable').length>0){
		$('.msgtable tr').hover(function(){
			$('.close_sts',this).css('display','inline');
		},function(){
			$('.close_sts',this).hide();
		});
	}
	if($('.applist').length >0){
		$('.applist table tr td').hover(function(){
			$(this).addClass("over");
		},function(){
			$(this).removeClass("over");
		});
	}
	
	if($('.hovertd').length > 0){
		$('.hovertd td').hover(function(){
			$(this).addClass("overtd");
		},function(){
			$(this).removeClass("overtd");
		});
	}
	
	if($('#por_switch').length > 0){
		$('#por_switch').click(function(){
			$(this).toggleClass("off");
			$('#por_left').toggle();
			if($('#por_right').css("margin-left")=="220px"){
				$('#por_right').css("margin-left",10);
				$(this).css("left",-18);
			}else{
				$('#por_right').css("margin-left",220);
				$(this).css("left",-8);
			}
		});
	}
	if($('.an').length > 0){
		$('.an').click(function(){
			var $_pare = $(this).parents(".addbox")
			$(".anbox",$_pare).toggle();
			$(this).toggleClass("anopen");
		});
	}
	if($("#por_left .datelist li").length>0){
		$("#por_left .datelist li").hover(function(){
			$(this).addClass("datelistLiHover");
		},function(){
			$(this).removeClass("datelistLiHover");
		});
	}
	if($(".ms").length>0){
		$(".ms").hover(function(){
			$(">ul",this).stop().slideDown(100);
		},function(){
			$(">ul",this).stop().slideUp(100);
		});
		$(".ms ul li").hover(function(){
			$(">a",this).addClass("hover");
			$(">ul",this).stop().slideDown(100);
		},function(){
			$(">a",this).removeClass("hover");
			$(">ul",this).stop().slideUp(100);
		});
	}
	
	$(".mail_menu").hover(function(){
		$(this).addClass("sub_menu_hover");
		$("ul",this).stop().slideDown(200);
	},function(){
		$(this).removeClass("sub_menu_hover");
		$("ul",this).stop().slideUp(200);
	}); 
	
	$("input[type='radio'],input[type='checkbox']").css("border","0 none;");
	$("#sortlist .item").each(function(index, element) {
		$("h3",element).click(function(){
			$(element).toggleClass("current");
		});
	});
	$("#store-selector").hover(function(){
		$(".content",this).show();
	},function(){
		$(".content",this).hide();
	});

})

function sum(obj){
	var $_obj = $('.'+obj);
	var obj_l = $('.'+obj).length;
	var _sum=0;
	for(var i=0; i<obj_l ; i++){
		_sum = _sum + parseFloat($_obj.eq(i).text());
	}
	document.write(_sum);
}
function mean(obj){
	var $_obj = $('.'+obj);
	var obj_l = $('.'+obj).length;
	var _sum=0;
	for(var i=0; i<obj_l ; i++){
		_sum = _sum + parseFloat($_obj.eq(i).text());
	}
	document.write(_sum/obj_l);
}
function tabBig(num,befor,box,btn,className){
	for(var i=1; i<=num; i++){
		$("#"+box+i).hide();
		$("#"+btn+i).removeClass(className);
	}
	$("#"+box+befor).show();
	$("#"+btn+befor).addClass(className);
	return false;
}

$(function(){
	if($(".userlist").length > 0){
		$(".userlist li .fileico").click(function(){
			$(this).parents('li').toggleClass('current');
		});
		$(".userlist li .ca").click(function(){
			if($(this).is(":checked")){
				$('[type=checkbox]',$(this).parents('li')).attr("checked",$(this).attr("checked"));
			}else{
				$('[type=checkbox]',$(this).parents('li')).attr("checked",false);
			}
		});
		
	}
})