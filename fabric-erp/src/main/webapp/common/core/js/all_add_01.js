//add by wulei, included in resource_add_01.jsp

var _all_search_target_page_id = 'search_page_id';


//自定义列表下拉菜单
function bindPageDropMenu(){
	//已经挪到下面的页面初始化加载中处理了
}

//按键效果
function addButtonClass(){
	//已经挪到下面的页面初始化加载中处理了
}

//模拟点击面包屑最后一级以刷新页面
//no code 

//添加input事件，只允许输入整数和小数
function addInputCheckNumEvent(obj){
	var jObj = null;
	//check if obj is just id
	if(obj instanceof jQuery){
		jObj = obj;
	}else{
		jObj = $('#'+obj);
	}
	if(jObj==null || jObj.length==0){
		alert('数字输入框初始化条件异常');
		return;
	}
	
	jObj.keydown(function(event) {
        var keyCode = event.which;
        var oldVal = $(this).val();
        if(oldVal.indexOf('.')>0 && keyCode == 190)
        	return false;
        if(oldVal=='' && keyCode == 190)
        	return false;
        if (keyCode == 46 || keyCode == 8 || keyCode == 190 || (keyCode >= 48 && keyCode <= 57) || (keyCode >= 96 && keyCode <= 105) || keyCode == 110) {
            return true;
        }else {
			return false;
		}
    }).focus(function() {
        this.style.imeMode = 'disabled';
    });
}

function bindSearchKeyEvent(objId,searchFunc){
	$('#mainContent #'+objId).keypress(function(event) {
	    var keyCode = event.which;
		if(keyCode==13){
			searchFunc();
		}
	});
}

function _add01_bindButtonClassEvent(jButtons, overClass, activeClass)
{
	if(!jButtons || jButtons.length==0)
		return;
	
	jButtons = $(jButtons);
	
	if(!overClass) overClass = 'over';
	if(!activeClass) activeClass = 'active';
	
	jButtons.each(function(){
		$(this).hover(
			function(){
				if(!$(this).hasClass(activeClass))
					$(this).addClass(overClass);
			}
			,function(){
				$(this).removeClass(overClass);
			}
		);
		$(this).bind('click',function(){
			jButtons.removeClass(activeClass);
			$(this).removeClass(overClass);
			$(this).addClass(activeClass);
		});
	});
}


function pushBaseBreadCrumb(menu){
	//var menu = $('#nav_menu li a[onclick*="'+url+'"]');

	//var bcPop = $(document).data('_base_breadcrumb_data');
	//if(!bcPop)
	//	bcPop = new Array();
	var bcPop = new Array();
	
	if(menu.length>0){
		var limit = 20;
		var iconImg = null;
		while(menu.attr('id')!='nav_menu'){
			if(limit<=0)
				break;
			
			menu = menu.parent();

			if(menu.is('li')){
				if(iconImg==null){
					var icon = menu.attr('icon');
					if(icon && icon.length>0){
						iconImg = $('<img/>');
						iconImg.attr('src',icon);
					}
				}
				var li = $('<li></li>');
				var menuLink = menu.children('a:first');
				if(menuLink && menuLink.length>0){
					var onclick = menuLink.attr('onclick');
					var text = menuLink.text();
					if(onclick && onclick.length>0){
						var liLink = $('<a></a>');
						liLink.text(text);
						liLink.attr('onclick',onclick);
						li.append(liLink);
					}else{
						li.text(text);
					}
				}else{
					li.text(menu.text());
				}

				bcPop.push(li);
			}
			
			if(menu.is('li.nav_arrow') || menu.attr('id')=='nav_menu')
				break;
			
			limit--;
		}
		
		if(iconImg!=null && bcPop.length>0){
			var baseLi = bcPop[bcPop.length-1];
			var baseLink = baseLi.children('a:first');
			if(baseLink && baseLink.length>0)
				baseLink.prepend(iconImg);
			else
				baseLi.prepend(iconImg);
		}
	}
	
	$(document).data('_base_breadcrumb_data', bcPop);
}

function genBreadCrumb(){
	var bcUl = $('#mainContent #breadCrumb ul:first');

	if(bcUl.length>0){
		//已经设置的了暂时不覆盖
		if(bcUl.find('li').length>0)
			return;
		
		bcUl.html('');
	}else{
		bcUl = $('<ul></ul>');
		$('#mainContent #breadCrumb').html(bcUl);
	}

	var bcPop = $(document).data('_base_breadcrumb_data');
	if(bcPop && bcPop.length>0){
		for(b = 0;b<bcPop.length;b++){
			bcUl.prepend(bcPop[b]);
		}
	}
}


function _all_gridSimpleSearch(){
	var simple = $('.grid_search.search_simple:first');
	var containerId = simple.attr(_all_search_target_page_id);
	if(!containerId || containerId=='')
		containerId = 'category';
	
	//假设搜索区域，有text，select,checkbox,radio,select,没有textarea
	simple.find('input[type!="button"],select').each(function(){
		_grid_add_adv_filter_input(containerId, $(this));
	});
	
	_pad_grid_search(containerId);
}

function _all_gridDropdownSearch(link){
	var parent = $('#mainContent #'+link.attr('parent'));
	var value = link.attr('value');
	parent.val(value);
	parent.text(link.text());

	var containerId = parent.attr(_all_search_target_page_id);
	if(!containerId || containerId==''){
		containerId = 'category';
	}
	_grid_add_adv_filter_input(containerId, parent);
	_pad_grid_search(containerId);
}

function _all_gridAdvancedSearch(){
	var simple = $('.grid_search.search_simple:first');
	var advanced = $('.grid_search.search_advanced:first');
	
	var containerId = simple.attr(_all_search_target_page_id);
	if(!containerId || containerId==''){
		containerId = advanced.attr(_all_search_target_page_id);
		if(!containerId || containerId=='')
			containerId = 'category';
		else
			simple.attr(_all_search_target_page_id,containerId);
	}else{
		advanced.attr(_all_search_target_page_id,containerId);
	}
	
	
	simple.find('input[type!="button"],select').each(function(){
		_grid_add_adv_filter_input(containerId, $(this));
	});

	advanced.find('input[type!="button"],select').each(function(){
		_grid_add_adv_filter_input(containerId, $(this));
	});
	
	_pad_grid_search(containerId);
}

function _all_gridSearchClear(targetContainerId){
	var simple=null;
	var advanced=null;
	var targetContainer = null;
	if(targetContainerId && targetContainerId.length>0){
		if(targetContainerId.substring(0,1)=='#')
			targetContainerId = targetContainerId.substring(1);
		targetContainer = $('#mainContent #'+targetContainerId);
	}
	if(targetContainer && targetContainer.length>0){
		simple = $('.grid_search.search_simple['+_all_search_target_page_id+'="'+targetContainerId+'"]');
		advanced = $('.grid_search.search_advanced['+_all_search_target_page_id+'="'+targetContainerId+'"]');
	}else{
		simple = $('.grid_search.search_simple:first');
		advanced = $('.grid_search.search_advanced:first');
	}
	
	if(simple && simple.length>0){
		simple.find('input[type!="button"]').each(function(){
			$(this).val('');
		});

		simple.find('select').each(function(){
			$(this).find('option:first').attr('selected',true);
		});
	}

	if(advanced && advanced.length>0){
		advanced.find('input[type!="button"]').each(function(){
			$(this).val('');
		});

		advanced.find('select').each(function(){
			$(this).find('option:first').attr('selected',true);
		});
	}

}

function _all_add_key_event(){
	document.onkeydown = function (e) {
	    var ev = window.event || e;
	    var code = ev.keyCode || ev.which;
	    
	    _pad_handle_document_key_event(code, ev);
	};
}

$(function(){	
	_all_add_key_event();
	
	//列表、列表上方状态条最右边问号，弹出窗口的事件绑定
	$('#main_content_delegate').delegate('.untitled','hover',function(event){
		if(event.type=='mouseenter'){
			$(this).css({ "position": "relative"});
			var popUp = $(this).children('.popup');
			popUp.css({ "display": "block"});
			var popUpClose = popUp.find('.close');
			popUpClose.unbind('click').bind('click',function(){
				popUp.css({ "display": "none"});
			});
			var bh = $("body").height(); 
			var pt = $(this).offset();
			if(( bh - pt.top) < 165){$(this).children('.popup').css({ "bottom": "0"});}else{$(this).children('.popup').css({ "top": "-7px"});};
		}else if(event.type='mouseleave'){
			$(this).css({ "position": "static"});
			$(this).children('.popup').css({ "display": "none"});
		}
	});
	

	//列表、列表pager中的自定义列表下拉菜单事件绑定
	$('#main_content_delegate').delegate('.pagelist.drop>ul>li','hover',function(event){
		if(event.type=='mouseenter'){
			$(this).children('ul').delay(400).slideDown('fast');
		}else if(event.type='mouseleave'){
			var ulObj = $(this).children('ul');
			ulObj.stop(true, true);
			ulObj.slideUp('fast');
		}
	});
	//列表中的自定义列表下拉菜单事件绑定
	$('#main_content_delegate').delegate('.drop>ul>li','hover',function(event){
		if(event.type=='mouseenter'){
			var ulObj = $(this).children('ul');
			ulObj.delay(100).slideDown('fast');
			
			$(this).find('a').unbind('click').bind('click',function(){
				ulObj.hide();
			});
		}else if(event.type='mouseleave'){
			var ulObj = $(this).children('ul');
			ulObj.stop(true, true);
			ulObj.slideUp('fast');
		}
	});

	//grid的列表颜色变化事件
	$('#main_content_delegate').delegate('table tr','hover',function(event){
		if(event.type=='mouseenter'){
			$(this).addClass("over");
		}else if(event.type='mouseleave'){
			$(this).removeClass("over");
		}
	});
	
	//grid表头左侧checkbox选项pop事件
	$('#main_content_delegate').delegate('table .list_check','hover',function(event){
		if(event.type=='mouseenter'){
			$(this).addClass('posr');
			$(this).find('ul:first').css('display','block');
		}else if(event.type='mouseleave'){
			$(this).removeClass('posr');
			$(this).find('ul:first').css('display','none');
		}
	});


	//按键的hover效果
	$('#main_content_delegate').delegate('input.btn[type="button"],input.btn[type="submit"]','hover',function(event){
		if(event.type=='mouseenter'){
			$(this).addClass("btnhover");
		}else if(event.type='mouseleave'){
			$(this).removeClass("btnhover");
		}
	});
	//索引显示效果
	$('#main_content_delegate').delegate('#numBtn','click',function(event){
		if(!$(this).attr('init')){
			$(this).attr('init','numBox');
			if($('#mainContent #numBox').length)
				$('#mainContent #numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
		}

		$(this).parent("li").toggleClass("current");
		$('#mainContent #number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
			$('#mainContent #number').css('overflow','visible');
		});
		return false;
	});
	
	//按键后显示高级搜索
	$('#main_content_delegate').delegate('.grid_search.search_simple #lb_search_advanced','click',function(event){
		if($ ("#c_head").is('.advanced')){
			$ ("#c_head").removeClass ("advanced");
			$ (this).text ($ ("#cmn_advance_search").val ());
			$(".grid_search.search_simple input").removeAttr("disabled");
			$("#simpleSearch").show();
			$("#simpleReset").show();
		}else{
			$ ("#c_head").addClass ("advanced");
			$ (this).text ($ ("#cmn_simple_search").val ());
			$(".grid_search.search_simple input").attr({
				disabled : 'true'
			});
			$("#simpleSearch").hide();
			$("#simpleReset").hide();
		}
	});
	
	//简单搜索提交,如果高级搜索打开，则无效
	$('#main_content_delegate').delegate('.grid_search.search_simple input[type="text"],.grid_search.search_simple .search_btton','keypress',function(event){
		if(!$("#c_head").is(".advanced")){
		    var keyCode = event.which;
			if(keyCode==13){
				_all_gridSimpleSearch();
			}
		}
	});
	//普通搜索
	$('#main_content_delegate').delegate('.grid_search.search_simple .btn','click',function(event){
		_all_gridSimpleSearch();
	});
	//高级搜索
	$('#main_content_delegate').delegate('.grid_search.search_advanced .btn','click',function(event){
		if($(this).is('.search')){
			_all_gridAdvancedSearch();
		}else if($(this).is('.reset')){
			_all_gridSearchClear();
		}
	});

	//高级搜索
	$('#main_content_delegate').delegate('.dropdown_search_group .ds_link','click',function(event){
		_all_gridDropdownSearch($(this));
	});
	
	//左侧树区域收缩
	$('#main_content_delegate').delegate('#switch_box','click',function(event){
		$ (this).toggle (function (){
			$ ("#mainContent #switch_box").addClass ("off");
			$ ("#mainContent #left").addClass ("switch");
			$ ("#mainContent #right").addClass ("switch");
			$ ("#mainContent .left_head").addClass ("wid7px");
			$ ("#mainContent .left_content").addClass ("current");
			$ ("#mainContent #left").css ({
				"width" : "7px"
			});
		} , function (){
			$ ("#mainContent #switch_box").removeClass ("off");
			$ ("#mainContent #left").removeClass ("switch");
			$ ("#mainContent #right").removeClass ("switch");
			$ ("#mainContent .left_head").removeClass ("wid7px");
			$ ("#mainContent .left_content").removeClass ("current");
			$ ("#mainContent #left").css ({
				"width" : "252px"
			});
		});
		//防止第一次点击无效
		$(this).trigger('click');
	});
	
	//通过菜单生成面包屑
	$('#nav_menu li a[onclick]').bind('click',function(event){
		pushBaseBreadCrumb($(this));
	});
	$('#main_content_delegate').bind('main_content_load',genBreadCrumb);
	

	
	//详细页内上方菜单条悬浮事件
	$(window).bind('scroll',function(){
		if($('#mainContent #orderTitleFixd').length>0){
		    if ($('#mainContent #orderTitleFixd').parent('dl').offset().top - 43 < $(window).scrollTop()) {
			    if (!$('#mainContent #orderTitleFixd').hasClass('fixed')) {
				    $('#mainContent #orderTitleFixd').addClass('fixed');
				    $('#mainContent #orderTitleFixd').parent('dl').css('padding-top', 30);
			    }
		    } else {
			    $('#mainContent #orderTitleFixd').removeClass('fixed');
			    $('#mainContent #orderTitleFixd').parent('dl').css('padding-top', 0);
		    }
		}
	});
	
	//页面中输入框点击时边框变色效果
	$('#main_content_delegate').delegate('.order_table input[type="text"]','focus',function(event){
	    $(this).css({
		    'border' : '1px solid #f00',
		    'background' : '#f5f5f5'
		});
	});
	$('#main_content_delegate').delegate('.order_table input[type="text"]','blur',function(event){
	    $(this).css({
		    'border' : '1px solid #ccc',
		    'background' : '#fff'
		});
	});
	
	//编辑页面中信息区域伸缩
	$('#main_content_delegate').delegate('.voucher.newvoucher dt b','click',function(event){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	
	//编辑页面中下拉按键效果
	$('#main_content_delegate').delegate('.ms.f_btn','hover',function(event){
		if(event.type=='mouseenter'){
			$(">ul",this).stop().slideDown(100);
		}else if(event.type='mouseleave'){
			$(">ul",this).stop().slideUp(100);
		}
	});
	$('#main_content_delegate').delegate('.ms.f_btn ul li','hover',function(event){
		if(event.type=='mouseenter'){
			$(">a",this).addClass("hover");
			$(">ul",this).stop().slideDown(100);
		}else if(event.type='mouseleave'){
			$(">a",this).removeClass("hover");
			$(">ul",this).stop().slideUp(100);
		}
	});
	

	$('#main_content_delegate').delegate('#mainContent','main_content_load new_content_load',function(event){
		_pad_check_update_page_btns($(this));
	});


	$('#main_content_delegate').delegate('#mainContent','main_content_load',function(event,mainContentUrl){
		if(_pad_page_refresh_main_content && mainContentUrl && mainContentUrl.length>0){
			_pad_page_main_content_refresh_url = mainContentUrl;
		}else{
			_pad_check_page_refresh_target($(this),mainContentUrl);
		}
	});
	
});
