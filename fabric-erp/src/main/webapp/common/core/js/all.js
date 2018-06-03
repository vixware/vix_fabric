// skin
function getObject (elementId){ // 获取指定id的object
	if (document.getElementById){
		return document.getElementById (elementId);
	}else if (document.all){
		return document.all [ elementId ];
	}else if (document.layers){
		return document.layers [ elementId ];
	}
}
function changeStyle (id , cssid , num){// 切换样式
	var stylesheet = getObject (cssid).href = "core/css/" + cssid + "_0" + id + ".css";
	if (id == 1 || id == 4 || id == 6)
		$ ('.gt-skin').attr ('class' , 'gt-skin gt-skin-vista');
	else if (id == 2)
		$ ('.gt-skin').attr ('class' , 'gt-skin gt-skin-pink');
	else if (id == 3 || id == 5)
		$ ('.gt-skin').attr ('class' , 'gt-skin gt-skin-mac');
	for (i = 1;i <= num;i ++ ){
		if (i == id){
			document.getElementById (cssid + "_0" + i).className = "this";
		}else{
			document.getElementById (cssid + "_0" + i).className = "";
		}
	}
}
$ (document).ready (function (){
	$ ("input[type='text'],input[type='password']").focusout (function (){
		$ (this).removeAttr ("style");
	});
	$ (".sbtn-green").hover (function (){
		$ (this).css ({
		"background-image" : "url(img/btn_04.gif)" ,
		"color" : "#FFF"
		});
	} , function (){
		$ (this).removeAttr ("style");
	});
	$ (".sbtn-red").hover (function (){
		$ (this).css ({
		"background-image" : "url(img/skin_02/btn_04.gif)" ,
		"color" : "#FFF"
		});
	} , function (){
		$ (this).removeAttr ("style");
	});
	$ ('input.btn[type="button"]').hover (function (){
		$ (this).addClass ("btnhover");
	} , function (){
		$ (this).removeClass ("btnhover");
	})
	$ ('.sbtn').hover (function (){
		$ (this).addClass ("sbtnhover");
	} , function (){
		$ (this).removeClass ("sbtnhover");
	})
	$ ("#nicemenu img.arrow").click (function (){
		$ ("span.head_menu").removeClass ('active');
		submenu = $ (this).parent ().parent ().find ("div.flick_menu");
		if (submenu.css ('display') == "block"){
			$ (this).parent ().removeClass ("active");
			submenu.hide ();
			// $(this).attr('src','arrow_hover.png');
		}else{
			$ (this).parent ().addClass ("active");
			submenu.fadeIn ();
			// $(this).attr('src','arrow_select.png');
		}
		$ ("div.flick_menu:visible").not (submenu).hide ();
		// $("#nicemenu img.arrow").not(this).attr('src','arrow.png');
	})
	$ ("#nicemenu span.head_menu").mouseover (function (){
		$ (this).addClass ('over')
	}).mouseout (function (){
		$ (this).removeClass ('over')
	});
	$ ("#nicemenu div.flick_menu").mouseover (function (){
		$ (this).fadeIn ();
	}).blur (function (){
		$ (this).hide ();
		$ ("span.head_menu").removeClass ('active');
	});
	$ (document).click (function (event){
		var target = $ (event.target);
		if (target.parents ("#nicemenu").length == 0){
			$ ("#nicemenu span.head_menu").removeClass ('active');
			$ ("#nicemenu div.flick_menu").hide ();
		}
	});
});
// search_advanced
function bindSearch() {
	$("#lb_search_advanced").toggle(function() {
		$("#c_head").addClass("advanced");
		$("#lb_search_advanced").text($("#cmn_simple_search").val());
		$("#nameS").attr({
			disabled : 'true'
		});
		$("#simpleSearch").hide();
		$("#simpleReset").hide();
	}, function() {
		$("#c_head").removeClass("advanced");
		$("#lb_search_advanced").text($("#cmn_advance_search").val());
		$("#nameS").removeAttr("disabled");
		$("#simpleSerach").show();
		$("#simpleReset").show();
	});
}
// list_check
$ (document).ready (function (){
	$ (".list_check").bind ('mouseover' , function (){
		$ (".list_check").addClass ('posr');
		$ (".list_check ul").css ('display' , 'block');
	}).bind ('mouseleave' , function (){
		$ (".list_check").removeClass ('posr');
		$ (".list_check ul").css ('display' , 'none');
	});
});
// drop_news
$ (document).ready (function (){
	$ (".drop_news li").bind ('mouseover' , function (){
		$ (this).children ('ul').css ('display' , 'block');
	}).bind ('mouseleave' , function (){
		$ (this).children ('ul').css ('display' , 'none');
	});
});
// popup
$ (document).ready (function (){
	$ (".untitled b img").click (function (){
		var $_this = $ (this).parent ().parent (".untitled");
		$_this.css ({
			"position" : "relative"
		});
		$_this.children ('.popup').css ({
			"display" : "block"
		});
		var bh = $ ("body").height ();
		var pt = $_this.offset ();
		if ( (bh - pt.top) < 165){
			$_this.children ('.popup').css ({
				"bottom" : "0"
			});
		}else{
			$_this.children ('.popup').css ({
				"top" : "-7px"
			});
		}
		;
	});
	$ (".untitled span img").bind ('mouseover' , function (){
		var $_this = $ (this).parent ().parent (".untitled");
		$_this.css ({
			"position" : "relative"
		});
		$_this.children ('.popup').css ({
			"display" : "block"
		});
		var bh = $ ("body").height ();
		var pt = $_this.offset ();
		if ( (bh - pt.top) < 165){
			$_this.children ('.popup').css ({
				"bottom" : "0"
			});
		}else{
			$_this.children ('.popup').css ({
				"top" : "-7px"
			});
		}
		;
	});
	$ (".untitled").bind ('mouseleave' , function (){
		$ (this).css ({
			"position" : "static"
		});
		$ (this).children ('.popup').css ({
			"display" : "none"
		});
	});
	$ (".close").click (function (){
		$ (this).parent ().parent ().parent ().css ({
			"display" : "none"
		});
		return false;
	});
});
$ (document).ready (function (){
	// 一级
	$ (".drop>ul>li").bind ('mouseover' , function () // 顶级菜单项的鼠标移入操作
	{
		$ (this).children ('ul').delay (0).slideDown ('fast');
	}).bind ('mouseleave' , function () // 顶级菜单项的鼠标移出操作
	{
		$ (this).children ('ul').slideDown ('fast').stop (true , true);
		$ (this).children ('ul').slideUp ('fast');
	});
	// 二级
	$ ('.drop>ul>li>ul li').bind ('mouseover' , function () // 子菜单的鼠标移入操作
	{
		$ (this).children ('ul').delay (0).slideDown ('fast');
	}).bind ('mouseleave' , function () // 子菜单的鼠标移出操作
	{
		$ (this).children ('ul').slideDown ('fast').stop (true , true);
		$ (this).children ('ul').slideUp ('fast');
	});
	// 三级
	$ ('.drop>ul>li>ul>li>ul li').bind ('mouseover' , function () // 子菜单的鼠标移入操作
	{
		$ (this).children ('ul').delay (0).slideDown ('fast');
	}).bind ('mouseleave' , function () // 子菜单的鼠标移出操作
	{
		$ (this).children ('ul').slideDown ('fast').stop (true , true);
		$ (this).children ('ul').slideUp ('fast');
	});
	// 四级
	$ ('.drop>ul>li>ul>li>ul>li>ul li').bind ('mouseover' , function () // 子菜单的鼠标移入操作
	{
		$ (this).children ('ul').delay (0).slideDown ('fast');
	}).bind ('mouseleave' , function () // 子菜单的鼠标移出操作
	{
		$ (this).children ('ul').slideDown ('fast').stop (true , true);
		$ (this).children ('ul').slideUp ('fast');
	});
	// 五级
	$ ('.drop>ul>li>ul>li>ul>li>ul>li>ul li').bind ('mouseover' , function () // 子菜单的鼠标移入操作
	{
		$ (this).children ('ul').delay (0).slideDown ('fast');
	}).bind ('mouseleave' , function () // 子菜单的鼠标移出操作
	{
		$ (this).children ('ul').slideDown ('fast').stop (true , true);
		$ (this).children ('ul').slideUp ('fast');
	});
	// 六级
	$ ('.drop>ul>li>ul>li>ul>li>ul>li>ul>li>ul li').bind ('mouseover' , function () // 子菜单的鼠标移入操作
	{
		$ (this).children ('ul').delay (0).slideDown ('fast');
	}).bind ('mouseleave' , function () // 子菜单的鼠标移出操作
	{
		$ (this).children ('ul').slideDown ('fast').stop (true , true);
		$ (this).children ('ul').slideUp ('fast');
	});
	$ (".sub_menu .drop>ul>li").hover (function () // sub_menu菜单项的鼠标移入操作
	{
		$ (this).addClass ("sub_menu_hover");
	} , function () // sub_menu菜单项的鼠标移出操作
	{
		$ (this).removeClass ("sub_menu_hover");
	});
	$ ('.drop_nav>ul>li>a').bind ('click' , function (){
		var $_this = $ (this);
		if ($_this.parent ('li').hasClass ('nav_arrow')){
			$_this.parent ('li').parent ('ul').find ('.hover').removeClass ('hover');
			$_this.addClass ('hover');
		}
		if ($_this.parent ('li').parent ('ul').find ('.fly') && ! $_this.parent ('li').hasClass ('btn')){
			$_this.parent ('li').parent ('ul').find ('.fly_hover').removeClass ('fly_hover');
			if ($_this.parent ('li').hasClass ('fly')){
				$_this.parent ('li').addClass ('fly_hover');
			}
		}
		$_this.parent ('li').parent ('ul').find ('li ul').hide ();
		$_this.parent ('li').children ('ul').slideDown ('fast').stop (true , true);
		return false;
	});
//	$ ('.drop_nav>ul>li>a').bind ('mouseover' , function (){
//		var $_this = $ (this);
//		if ($ ('.drop_nav>ul>li').has ("ul:visible").length > 0){
//			if ($_this.parent ('li').hasClass ('nav_arrow')){
//				$_this.parent ('li').parent ('ul').find ('.hover').removeClass ('hover');
//				$_this.addClass ('hover');
//			}
//			if ($_this.parent ('li').parent ('ul').find ('.fly') && ! $_this.parent ('li').hasClass ('btn')){
//				$_this.parent ('li').parent ('ul').find ('.fly_hover').removeClass ('fly_hover');
//				if ($_this.parent ('li').hasClass ('fly')){
//					$_this.parent ('li').addClass ('fly_hover');
//				}
//			}
//			$_this.parent ('li').parent ('ul').find ('li ul').hide ();
//			$_this.parent ('li').children ('ul').slideDown ('fast').stop (true , true);
//		}
//		return false;
//	});
//	$ ('.drop_nav>ul>li ul li a').bind ('mouseover' , function (){
//		var $_this = $ (this);
//		if ($_this.parent ('li').hasClass ('nav_arrow')){
//			$_this.parent ('li').parent ('ul').find ('.hover').removeClass ('hover');
//			$_this.addClass ('hover');
//		}
//		if ($_this.parent ('li').parent ('ul').find ('.fly') && ! $_this.parent ('li').hasClass ('btn')){
//			$_this.parent ('li').parent ('ul').find ('.fly_hover').removeClass ('fly_hover');
//			if ($_this.parent ('li').hasClass ('fly')){
//				$_this.parent ('li').addClass ('fly_hover');
//			}
//		}
//		$_this.parent ('li').parent ('ul').find ('li ul').hide ();
//		$_this.parent ('li').children ('ul').slideDown ('fast').stop (true , true);
//		return false;
//	});
//	$ ('.drop_nav>ul>li ul li a').click (function (){
//		$ ('.drop_nav ul li ul').hide ();
//		$ ('.drop_nav ul').find ('.hover').removeClass ('hover');
//		$ ('.drop_nav ul').find ('.fly_hover').removeClass ('fly_hover');
//	});
//	$ (document).click (function (event){
//		var target = $ (event.target);
//		if (target.parents (".drop_nav").length == 0){
//			$ ('.drop_nav ul li ul').hide ();
//			$ ('.drop_nav ul').find ('.hover').removeClass ('hover');
//		}else{
//		}
//	});
		
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
function bindSwitch (){
	$ ("#switch_box").toggle (function (){
		$ ("#switch_box").addClass ("off")
		$ ("#left").addClass ("switch")
		$ ("#right").addClass ("switch")
		$ (".left_head").addClass ("wid7px")
		$ (".left_content").addClass ("current")
		$ ("#left").css ({
			"width" : "7px"
		})
	} , function (){
		$ ("#switch_box").removeClass ("off")
		$ ("#left").removeClass ("switch")
		$ ("#right").removeClass ("switch")
		$ (".left_head").removeClass ("wid7px")
		$ (".left_content").removeClass ("current")
		$ ("#left").css ({
			"width" : "252px"
		})
	})
}
$ (document).ready (function (){
	$ ("#switch_left").toggle (function (){
		$ ("#switch_left").addClass ("off")
		$ ("#m_left").addClass ("switch")
		$ ("#m_content").addClass ("switch_left")
		$ ("#mail_list").css ({
			"width" : ""
		})
		$ ("#m_left").css ({
			"width" : "7px"
		})
		$ ("#m_left .m_box").css ({
			"display" : "none"
		})
	} , function (){
		$ ("#switch_left").removeClass ("off")
		$ ("#m_left").removeClass ("switch")
		$ ("#m_content").removeClass ("switch_left")
		$ ("#m_left").css ({
			"width" : "207px"
		})
		$ ("#m_left .m_box").css ({
			"display" : "block"
		})
	})
});
$ (document).ready (function (){
	$ ("#switch_right").toggle (function (){
		$ ("#switch_right").removeClass ("off")
		$ ("#m_right").addClass ("switch")
		$ ("#m_content").addClass ("switch_right")
		$ ("#mail_list").css ({
			"width" : ""
		})
		$ ("#m_right").css ({
			"width" : "7px"
		})
		$ ("#m_right .m_box").css ({
			"display" : "none"
		})
	} , function (){
		$ ("#switch_right").addClass ("off")
		$ ("#m_right").removeClass ("switch")
		$ ("#m_content").removeClass ("switch_right")
		$ ("#m_right").css ({
			"width" : "207px"
		})
		$ ("#m_right .m_box").css ({
			"display" : "block"
		})
	})
});
// pos_menu
$ (document).ready (function (){
	$("#show_menu").mouseover (function (){
		$ ("#pos_menu").slideDown ('fast');
	});
	$("#hidd_menu,#pos_menu a").live('click',function (){
		$ ("#pos_menu").slideUp ('fast');
	});
	// $(document).click(function(event){
	// var target = $(event.target);
	// if (target.parents("#pos_menu").length == 0) {
	// $("#pos_menu").slideUp('fast');
	// }
	// });
});
// tab
function tab (num , befor , id , e){
	var el = e.target ? e.target.parentNode : e.srcElement.parentNode;
	var pa = el.parentNode.getElementsByTagName ("li");
	for ( var i = 0;i < pa.length;i ++ ){
		// pa[i].className="";
		$ (pa [ i ]).removeClass ("current");
	}
	// el.className="current";
	$ (el).addClass ("current");
	for (i = 1;i <= num;i ++ ){
		try{
			if (i == befor){
				document.getElementById (id + i).style.display = "block";
			}else{
				document.getElementById (id + i).style.display = "none";
			}
		}catch (e){
		}
	}
}
function menu (id){
	$ (".c_menu li").removeClass ("c_current")
	$ (".c_menu li").eq (id).addClass ("c_current")
	$ (".c_content").css ({
		"display" : "none"
	});
	$ (".c_content").eq (id).css ({
		"display" : "block"
	});
}
function statistics_menu (id){
	$ (".statistics_menu li").removeClass ("statistics_menu_current")
	$ (".statistics_menu li").eq (id).addClass ("statistics_menu_current")
	$ (".statistics").css ({
		"display" : "none"
	});
	$ (".statistics").eq (id).css ({
		"display" : "block"
	});
}
function menu_add (){
	// alert($("#menu_add li").length);
	var num = $ ("#menu_add li").length;
	$ ("#menu_add").append ('<li><a onclick="javascript:menu(\'' + num + '\')"><em>DEMO</em></a></li>');
	$ (".content")
			.append ('<div id="c' + num + '" class="c_content" style="display:none;">' + '<div class="left_box connectedSortable ui-sortable">' + '<div class="box_content">' + '<div class="c_title">' + '<span class="left_bg"></span>' + '<span class="right_bg"></span>' + '<i><a href="#" class="close">[关闭]</a></i>' + '<strong>New demo, ID c' + num + '</strong>' + '</div>' + '<div class="c_box">' + '<div class="box_news">' + '<h3>这是新添加的 demo，ID为 c' + num + '</h3>' + '<p>这是新添加的 demo，ID为 c' + num + '，js: all.js line 316 menu_add()</p>' + '</div>' + '</div>' + '<div class="c_foot">' + '<span class="left_bg"></span>' + '<span class="right_bg"></span>' + '</div>' + '</div></div>' + '<div class="right_box connectedSortable ui-sortable"></div></div>');
	$ (".connectedSortable").sortable ({
	connectWith : ".connectedSortable" ,
	handle : '.c_title' ,
	opacity : 0.4
	})
}
function change (txt){
	$ ("#nav_change").html (txt)
}
$ (document).ready (function (){
	$ (".right_menu li").hover (function (){
		$ (this).addClass ("current_over")
	} , function (){
		$ (this).removeClass ("current_over")
	});
});
// m_menu
function m_menu (id){
	$ (".m_menu li").removeClass ("m_current")
	$ (".m_menu li").eq (id).addClass ("m_current")
	$ ("#m_left .m_txt").css ({
		"display" : "none"
	});
	$ ("#tree_0" + id).css ({
		"display" : "block"
	});
}
// table
$ (document).ready (function (){
	$ ("table tr").mouseover (function (){
		$ (this).addClass ("over");
	}).mouseout (function (){
		$ (this).removeClass ("over");
	})
	$ ("table tr:even").addClass ("alt");
});
// s_select
function show (){
	document.getElementById ("s_select").style.display = "block";
}
function hidd (){
	document.getElementById ("s_select").style.display = "none";
}
$ (function (){
	$ ('#navHideButton').hover (function (){
		$ (this).addClass ('navHideButton');
	} , function (){
		$ (this).removeClass ('navHideButton');
	});
	$ ('#navHideButton').click (function (){
		$ ('.head').slideUp (500 , function (){
			if ($ ('#onresizeBox').length > 0){
				extHeight = 168;
				findDimensions ();
			}
		});
		$ ('#dcMenuSugarCube').addClass ('dcMenuSugarCubeOn');
	});
	$ ('#dcMenuSugarCube').hover (function (){
		$ ('#dcMenuSugarCube').addClass ('dcMenuSugarCubeHover');
	} , function (){
		$ ('#dcMenuSugarCube').removeClass ('dcMenuSugarCubeHover');
	});
	$ ('#dcMenuSugarCube').click (function (){
		$ (this).toggleClass ('dcMenuSugarCubeOn');
		$ ('.head').slideToggle (500 , function (){
			if ($ ('#onresizeBox').length > 0){
				if ($ ('#top_bar').next ('.head').is (':visible')){
					// alert('显示');
					extHeight = 258;
					findDimensions ();
				}else if ($ ('#top_bar').next ('.head').is (':hidden')){
					// alert('隐藏');
					extHeight = 168;
					findDimensions ();
				}
			}
		});
	});

	//原来的代码改为bindIndexSearch方法方便调用
	bindIndexSearch();
	
	/*
	 * $('#nav_menu li.nav_arrow').hover(function(){ //var $_this = $(this);
	 * if(!$(this).hasClass('btn')) $('a',this).first().addClass('hover');
	 * },function(){ $('a',this).first().removeClass('hover'); }); $('#nav_menu
	 * li.fly').hover(function(){ $(this).addClass('fly_hover'); },function(){
	 * $(this).removeClass('fly_hover'); });
	 */
	
	// 自动tab溢出
	//原来的代码改为genTabOverflow方法方便调用
	genTabOverflow();
	
	// 面包屑
	if ($ ('#breadCrumb').length){
		$ ("#breadCrumb").jBreadCrumb ({
			previewWidth : 10
		});
	}
	// 提示
	if ($ ('input.sweet-tooltip').length){
		$ ('input.sweet-tooltip')
				.focus (function (){
					tooltip = $ (this);
					tooltipText = tooltip.attr ('data-text-tooltip');
					tooltipClassName = 'tooltip';
					tooltipClass = '.' + tooltipClassName;
					if (tooltip.hasClass ('showed-tooltip'))
						return false;
					tooltip
							.addClass ('showed-tooltip')
							.after ('<div class="' + tooltipClassName + '"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">' + tooltipText + '</div><div class="tooltip_b"></div></div>');
					tooltipPosTop = tooltip.position ().top - $ (tooltipClass).outerHeight () - 10;
					tooltipPosLeft = tooltip.position ().left;
					tooltipPosLeft = tooltipPosLeft - ( ($ (tooltipClass).outerWidth () / 2) - tooltip.outerWidth () / 2);
					$ (tooltipClass).css ({
					'left' : tooltipPosLeft ,
					'top' : tooltipPosTop
					}).animate ({
					'opacity' : '1' ,
					'marginTop' : '0'
					} , 500);
				}).focusout (function (){
					$ (tooltipClass).animate ({
					'opacity' : '0' ,
					'marginTop' : '-10px'
					} , 500 , function (){
						$ (this).remove ();
						tooltip.removeClass ('showed-tooltip');
					});
				});
	}
	// 时间类型
	if ($ ('.time_aoto_input').length){
		$ ('.time_aoto_input').click (function (){
			var fmt = $ (this).attr ('rel');
			WdatePicker ({
			dateFmt : fmt ,
			skin : 'blue'
			});
		});
	}
	// 消息提示框
	if ($ ('#Notification').length){
		$ ('#Notification').jnotifyInizialize ({
		oneAtTime : false ,
		appendType : 'append'
		});
	}
	if ($ ('.listlib').length){
		$ ('.listlib').each (function (){
			$ ('strong' , $ ('li' , $ (this)).first ()).first ().next ('ul').css ('display' , 'block');
			$ ('strong' , $ ('li' , $ (this)).first ()).first ().addClass ('current');
		});
		$ ('.listlib li').each (function (){
			var $_this = $ (this);
			if ($ ('ul' , this).length){
				if ( ! $_this.next ('li').length && ! $_this.prev ('li').length){
					$ ('strong' , $_this).first ().css ('cursor' , 'default').toggleClass ('current');
					$ ('strong' , $_this).first ().next ('ul').css ('display' , 'block');
				}
				// else{
				$ ('strong' , $_this).first ().css ('cursor' , 'pointer').click (function (){
					$ (this).toggleClass ('current');
					$ ('ul' , $_this).first ().slideToggle ();
				});
				// }
			}
		});
		$ ('.listlib strong,.listlib span').hover (function (){
			$ (this).addClass ('al_hover');
		} , function (){
			$ (this).removeClass ('al_hover');
		})
	}
	if ($ ('.w_bfb').length > 0){
		$ ('.w_bfb').each (function (){
			$ (this).append ('<div class="bfb" style="width:' + $ ('span' , this).text () + '"></div>');
		})
	}
	if($('.fixed_header').length > 0 && $('#c_head').length > 0){
		var fixed_t = $('.content #c_head').offset().top -40;
		$(window).scroll(function(){
			if($(window).scrollTop() > fixed_t){
				$('.fixed_header').show();
			}else{
				$('.fixed_header').hide();
				}
		})
	}
});
function showMessage (msgText){
	$ ('#Notification').jnotifyAddMessage ({
	text : msgText ,
	permanent : false
	});
}
function showErrorMessage (msgText){
	$ ('#Notification').jnotifyAddMessage ({
	text : msgText ,
	permanent : true ,
	type : 'error'
	});
}
function showTime (id , fmt){
	WdatePicker ({
	el : id ,
	dateFmt : fmt ,
	skin : 'blue'
	});
}
// gread 编辑
function editNode (doc){
	var node = $ ('#' + doc).treegrid ('getSelected');
	if (node){
		$ ('#' + doc).treegrid ('beginEdit' , node.id);
	}
}
// gread 保存
function saveNode (doc){
	var node = $ ('#' + doc).treegrid ('getSelected');
	if (node){
		for ( var i = 0;i < $ ('#' + doc).parent ('.datagrid-view').find ('.datagrid-row').length;i ++ ){
			if ($ ('#' + doc).parent ('.datagrid-view').find ('.datagrid-row').eq (i).find ('input').length > 0){
				$ ('#' + doc).treegrid ('endEdit' , $ ('#' + doc).parent ('.datagrid-view').find ('.datagrid-row').eq (i).attr ('node-id'));
			}
		}
	}
}
// gread 取消
function cancelNode (doc){
	var node = $ ('#' + doc).treegrid ('getSelected');
	if (node){
		for ( var i = 0;i < $ ('#' + doc).parent ('.datagrid-view').find ('.datagrid-row').length;i ++ ){
			if ($ ('#' + doc).parent ('.datagrid-view').find ('.datagrid-row').eq (i).find ('input').length > 0){
				$ ('#' + doc).treegrid ('cancelEdit' , $ ('#' + doc).parent ('.datagrid-view').find ('.datagrid-row').eq (i).attr ('node-id'));
			}
		}
	}
}
// 展开 编辑表格
$ (function (){
	$ (".addtable .addtable_t").click (function (){
		$ (this).toggleClass ("addtable_td");
		$ (this).next (".addtable_c").toggle ();
	});
	$ (".newvoucher dt b").click (function (){
		$ (this).toggleClass ("downup");
		$ (this).parent ("dt").next ("dd").toggle ();
	});
})
// 帮助弹窗
$ (document).ready (function (){
	if ($ ("#help").length > 0){
		$.fx.speeds._default = 1000;
		$ (function (){
			$ ("#helpContent").dialog ({
			width : 800 ,
			autoOpen : false ,
			modal : true
			});
			$ ("#help").click (function (){
				$ ("#helpContent").dialog ("open");
				return false;
			});
		});
	}
});
$ (function (){
	if ($ (".np_dd_bfb").length > 0){
		$ (".np_dd_bfb").each (function (){
			$ (this).parent ("td").css ("width" , 100);
			$ ("span" , this).css ("width" , $ (this).attr ("title"));
		});
	}
	if ($ (".np_left dl").length > 0){
		$ (".np_left dl dt b").click (function (){
			$ (this).parent ("dt").next ("dd").toggle ();
			$ (this).parent ("dt").toggleClass ("np_close");
		});
	}
	if ($ (".dashstatusmsg .zpcomment").length > 0){
		$ (".dashstatusmsg .zpcomment").click (function (){
			$ (this).next (".plbox").toggle ();
		});
	}
	if ($ ('.msgtable').length > 0){
		$ ('.msgtable tr').hover (function (){
			$ ('.close_sts' , this).css ('display' , 'inline');
		} , function (){
			$ ('.close_sts' , this).hide ();
		});
	}
	if ($ ('.applist').length > 0){
		$ ('.applist table tr td').hover (function (){
			$ (this).addClass ("over");
		} , function (){
			$ (this).removeClass ("over");
		});
	}
	if ($ ('.hovertd').length > 0){
		$ ('.hovertd td').hover (function (){
			$ (this).addClass ("overtd");
		} , function (){
			$ (this).removeClass ("overtd");
		});
	}
	if ($ ('#por_switch').length > 0){
		$ ('#por_switch').click (function (){
			$ (this).toggleClass ("off");
			$ ('#por_left').toggle ();
			if ($ ('#por_right').css ("margin-left") == "220px"){
				$ ('#por_right').css ("margin-left" , 0);
			}else{
				$ ('#por_right').css ("margin-left" , 220);
			}
		});
	}
	if ($ ('.an').length > 0){
		$ ('.an').click (function (){
			var $_pare = $ (this).parents (".addbox")
			$ (".anbox" , $_pare).toggle ();
			$ (this).toggleClass ("anopen");
		});
	}
	if ($ ("#por_left .datelist li").length > 0){
		$ ("#por_left .datelist li").hover (function (){
			$ (this).addClass ("datelistLiHover");
		} , function (){
			$ (this).removeClass ("datelistLiHover");
		});
	}
})
function sum (obj){
	var $_obj = $ ('.' + obj);
	var obj_l = $ ('.' + obj).length;
	var _sum = 0;
	for ( var i = 0;i < obj_l;i ++ ){
		_sum = _sum + parseFloat ($_obj.eq (i).text ());
	}
	document.write (_sum);
}
function mean (obj){
	var $_obj = $ ('.' + obj);
	var obj_l = $ ('.' + obj).length;
	var _sum = 0;
	for ( var i = 0;i < obj_l;i ++ ){
		_sum = _sum + parseFloat ($_obj.eq (i).text ());
	}
	document.write (_sum / obj_l);
}
function tabBig (num , befor , box , btn , className){
	for ( var i = 1;i <= num;i ++ ){
		$ ("#" + box + i).hide ();
		$ ("#" + btn + i).removeClass (className);
	}
	$ ("#" + box + befor).show ();
	$ ("#" + btn + befor).addClass (className);
	return false;
}
// 分页事件
function pager (tag , url , pageContent){
	var pageNo = Number ($ ("#" + pageContent + "PageNoHidden").val ());
	if (tag == "start"){
		pageNo = 1;
	}
	if (tag == "previous"){
		pageNo = pageNo - 1;
		if (pageNo < 1){
			return;
		}
	}
	if (tag == "next"){
		pageNo = pageNo + 1;
		if (pageNo > Number ($ ("#" + pageContent + "TotalPageHidden").val ())){
			return;
		}
	}
	if (tag == "end"){
		pageNo = Number ($ ("#" + pageContent + "TotalPageHidden").val ());
	}
	if (tag == "text"){
		pageNo = Number ($ ("#" + pageContent + "TageNo_text").val ());
		var totalPage = Number ($ ("#" + pageContent + "TotalPageHidden").val ());
		if (pageNo > totalPage){
			pageNo = totalPage;
		}
	}
	var pageParam = "page=" + pageNo;
	if(url.indexOf('?')!=-1)
		pageParam = '&' + pageParam;
	else
		pageParam = '?' + pageParam;
	$.ajax ({
	url : url + pageParam ,
	cache : false ,
	success : function (html){
		$ ("#" + pageContent).html (html);
		$ ("#" + pageContent + "PageNo").html ($ ("#" + pageContent + "PageNoHidden").val ());
		$ ("#" + pageContent + "PageNo_text").val ($ ("#" + pageContent + "PageNoHidden").val ());
		$ ("." + pageContent + "Info").html ($ ("#" + pageContent + "InfoHidden").val ());
		$ ("." + pageContent + "TotalCount").html ($ ("#" + pageContent + "TotalCountHidden").val ());
	}
	});
}
// 页面变更键盘事件
function pagerClick (obj , evt , url , pageContent){
	evt = (evt) ? evt : ( (window.event) ? window.event : "");
	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
	if (keyCode == 13){
		changePage ("text" , url , pageContent);
		return;
	}
}
function uploadFile (url , fileToUpload , callback){
	if ($ ("#" + fileToUpload).val () == ''){
		asyncbox.success ("请选择上传文件!" , "提示信息");
		return false;
	}
	$.ajaxFileUpload ({
	url : url ,
	secureuri : false ,
	fileElementId : fileToUpload ,
	dataType : 'json' ,
	success : function (data , status){
		asyncbox.success (data , "提示信息");
		if ( typeof callback == "function"){
			callback ();
		}
		return true;
	} ,
	error : function (data , status , e){
		asyncbox.error (e , "提示信息");
		return false;
	}
	});
	return false;
}
function deleteAttachment (btn , id){
	var tbody = btn.parentNode.parentNode.parentNode.parentNode;
	var row = btn.parentNode.parentNode.parentNode;
	tbody.removeChild (row);
	$ ("#attchmentIds").val ($ ("#attchmentIds").val ().replace ("," + id , ""));
	deleteAttachmentOnServer (id);
}
function deleteAttachmentOnServer (attachmentId){
	var url = "crm/accountAction!deleteAttachment.action?id=" + attachmentId;
	$.ajax ({
	url : url ,
	cache : false ,
	success : function (html){
		asyncbox.success (html , "提示信息");
	}
	});
}
function vixAjaxSend (_url , data , callBack){
	// var _div = document.getElementById(div);
	if (_url.indexOf ('?') != - 1){
		_url += '&temp=' + Math.random ();
	}else{
		_url += '?temp=' + Math.random ();
	}
	try{
		$.ajax ({
		url : encodeURI (_url) ,
		type : 'POST' ,
		async : false ,
		cache : false ,
		data : data ,
		dataType : 'json' ,
		success : function (result){
			if ( typeof (callBack) != 'undefined' && callBack.constructor == Function){
				callBack (result);
			}
		} ,
		error : function (result){
			window.alert ('操作失败');
		}
		});
	}catch (e){
		alert (e);
	}
}
// 删除js变量的空格
function trim (str){
	var trimStr = str.replace (/(^\s*)|(\s*$)/g , "");
	return trimStr;
}
/** 字典数据失去焦点事件,字典内容为空则去掉checkbox选中事件 */
function directoryBlur (name , index){
	var value = $ ("#" + name + "Name_" + index).val ();
	if (value == ''){
		$ ("#" + name + "Checkbox_" + index).attr ("checked" , false);
	}else{
		$ ("#" + name + "Checkbox_" + index).attr ("checked" , true);
	}
}
/** 载入快捷菜单 */
function loadMenuContent (url){
	$.ajax ({
	url : url ,
	cache : false ,
	success : function (html){
		$ ("#menuContent").html (html);
	}
	});
}

/** 载入数据列排序图标 */
function loadOrderByImage(urlHead,objectName){
	var orderField =  $("#"+objectName+"OrderField").val();
	var orderBy = $("#"+objectName+"OrderBy").val();
		if(orderField.indexOf(',') > 0){
		var orderFields = orderField.split(",");
		for(var i=0; i<orderFields.length;i++){
			if(orderFields[i].indexOf(' ') > 0){
				var ofs = orderFields[i].split(" ");
				if(ofs[0].indexOf('.') > 0){
					orderField = ofs[0].split(".")[0];
				}else{
					orderField = ofs[0];
				}
				if(ofs[1] == 'asc'){
					$("img[id="+objectName+"Img_"+orderField+"]").attr("src",urlHead+"/common/img/arrow_up.gif");
				}
				if(ofs[1] == 'desc'){
					$("img[id="+objectName+"Img_"+orderField+"]").attr("src",urlHead+"/common/img/arrow_down.gif");
				}
			}else{
				orderField = orderFields[i];
				if(orderField.indexOf('.') > 0){
					orderField = orderField.split(".")[0];
				}
				if(orderBy == 'asc'){
					$("img[id="+objectName+"Img_"+orderField+"]").attr("src",urlHead+"/common/img/arrow_up.gif");
				}
				if(orderBy == 'desc'){
					$("img[id="+objectName+"Img_"+orderField+"]").attr("src",urlHead+"/common/img/arrow_down.gif");
				}
			}
		}
	}else{
		if(orderField.indexOf('.') > 0){
			orderField = orderField.split(".")[0];
		}
		if(orderBy == 'asc'){
			$("img[id="+objectName+"Img_"+orderField+"]").attr("src",urlHead+"/common/img/arrow_up.gif");
		}
		if(orderBy == 'desc'){
			$("img[id="+objectName+"Img_"+orderField+"]").attr("src",urlHead+"/common/img/arrow_down.gif");
		}
	}
}

/** 载入快捷菜单 */
function loadTopDynamicMenuContent (url){
	$.ajax ({
	url : url ,
	cache : false ,
	success : function (html){
		$ (".dynamicMenuContent").each (function (){
			$ (this).remove ();
		});
		$ ("#dynamicMenuContent").append (html);
	}
	});
}
$ (function (){
	if ($ (".userlist").length > 0){
		$ (".userlist li .fileico").click (function (){
			$ (this).parents ('li').toggleClass ('current');
		});
		$ (".userlist li .ca").click (function (){
			if ($ (this).is (":checked")){
				$ ('[type=checkbox]' , $ (this).parents ('li')).attr ("checked" , $ (this).attr ("checked"));
			}else{
				$ ('[type=checkbox]' , $ (this).parents ('li')).attr ("checked" , false);
			}
		});
	}
});




//索引显示效果,由原$(function(){})代码中复制出来
function bindIndexSearch(){
	$('#numBtn').click(function(){
		$('#numBtn').parent("li").toggleClass("current");
		$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
			$('#number').css('overflow','visible');
		});
		return false;
	});
}


//自动tab溢出,由原$(function(){})代码中复制出来
function genTabOverflow(){
	if ($ ('.right_menu ul').length){
		var tabBoxWidth = $ ('.right_menu ul').width ();
		var tabLiAllWidth = ($ ('.right_menu ul li').width () + parseInt ($ ('.right_menu ul li').css ('margin-right')) + parseInt ($ ('.right_menu ul li')
				.css ('margin-left')) + parseInt ($ ('.right_menu ul li').css ('border-left-width')) + parseInt ($ ('.right_menu ul li')
				.css ('border-right-width')) + parseInt ($ ('.right_menu ul li').css ('padding-left')) + parseInt ($ ('.right_menu ul li')
				.css ('padding-right'))) * $ ('.right_menu ul li').length;
		if (tabBoxWidth < tabLiAllWidth){
			$ ('.right_menu').css ({
				padding : '0 24px'
			});
			$ ('.right_menu ul').jcarousel ({
			wrap : null ,
			scroll : 2 ,
			animation : 300 ,
			auto : 0
			});
			$ ('.jcarousel-prev,.jcarousel-next').hover (function (){
				$ (this).addClass ('jcarousel-hover');
			} , function (){
				$ (this).removeClass ('jcarousel-hover');
			});
		}
	}
}