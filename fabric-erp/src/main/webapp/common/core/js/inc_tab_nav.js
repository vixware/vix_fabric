
var itn_tab_Page_Idx = 1;
var itn_tabContainerId_default = "tabNameDiv";
var itn_tabPageContainerId_default = "tabPageDiv";

function initNavTabsEvent(){

	$('.right_menu.nav_tabs .tab_nav').hover(
		function(){
			$(this).addClass("over");
		},
		function(){
			$(this).removeClass("over");
		}
	);
	
	$('.right_menu.nav_tabs .tab_nav.r').bind('click',function(){
		if($(this).hasClass('on')){
			var tabScrollDiv = $(this).siblings('.right_menu_tab');
			//tabScrollDiv.scrollLeft(tabScrollDiv.scrollLeft() - 130);
			tabScrollDiv.animate({scrollLeft:tabScrollDiv.scrollLeft() - 130},'fast','linear',function(){
				if(tabScrollDiv.scrollLeft()==0){
					$('.right_menu.nav_tabs .tab_nav.r').removeClass('on');
				}
			});
			$(this).siblings('.tab_nav.l').addClass('on');
		}
	});
	
	$('.right_menu.nav_tabs .tab_nav.l').bind('click',function(){
		if($(this).hasClass('on')){
			var tabScrollDiv = $(this).siblings('.right_menu_tab');
			var currentScrollLeft = tabScrollDiv.scrollLeft();
			//tabScrollDiv.scrollLeft(currentScrollLeft + 130);
			tabScrollDiv.animate({scrollLeft:tabScrollDiv.scrollLeft() + 130},'fast','linear',function(){
				if(currentScrollLeft == tabScrollDiv.scrollLeft()){
					$('.right_menu.nav_tabs .tab_nav.l').removeClass('on');
				}
			});
			$(this).siblings('.tab_nav.r').addClass('on');
		}
	});
}


function _tabShow(title,contentUrl,pageId,params,tabContainerId,tabPageContainerId){
	if(!tabContainerId)tabContainerId = itn_tabContainerId_default;
	if(!tabPageContainerId)tabPageContainerId = itn_tabPageContainerId_default;
	
	var tabContainer = $('#'+tabContainerId);
	var tabList = tabContainer.find('ul:first');
	var tabPageContainer = $('#'+tabPageContainerId);
	
	if(!pageId){
		pageId = 'subPage_'+itn_tab_Page_Idx;
		itn_tab_Page_Idx++;
	}
	if(!title)title=pageId;
	var newTab;
	var newTabContent;
	var tabSwitcher;
	
	if($('#'+pageId).length>0){
		newTab = tabList.find("li[pageId='"+pageId+"']");
		newTabContent = $('#'+pageId);
		tabSwitcher = newTab.find('a:first');
		tabSwitcher.text(title);
	}else{
		newTab = $('<li pageId="'+pageId+'" class="sub"></li>');
		tabSwitcher = $('<a onclick="categoryTab(this)">'+title+'</a>');
		newTab.append(tabSwitcher);
		tabList.append(newTab);

		var tabClose = $('<a class="sub_close"></a>');
		newTab.append(tabClose)
		tabClose.bind('click',function(){
			var tabObj = $(this).parent();
			var tabWidth = tabObj.outerWidth(true);
			var tabListObj = tabObj.parent();
			var pageId = tabObj.attr('pageId');
			if(tabObj.hasClass('current')){
				var prevTab = tabObj.prev();
				prevTab.find('a').first().click();
			}
			tabObj.remove();
			$('#'+pageId).remove();
			
			var allTabsWidth = tabWidth * tabListObj.children().length;
			tabListObj.width(allTabsWidth);
			if($('.right_menu.nav_tabs .tab_nav.r').hasClass('on'))
				$('.right_menu.nav_tabs .tab_nav.r').trigger('click');
			var tabScrollDivObj = tabListObj.parent();
			if(allTabsWidth<=tabScrollDivObj.width())
				$('.right_menu.nav_tabs .tab_nav.l').removeClass('on');
		});
		
		newTabContent = $('<div id="'+pageId+'" class="table new_tab" ></div>');
		tabPageContainer.append(newTabContent);
	}

	if(contentUrl && contentUrl.length>0){
		if(params){
			$.post(contentUrl,
					params,
					function(html){
					newTabContent.html(html);
				}
			);
		}else{
			$.ajax({
				url:contentUrl,
				cache:false,
				success:function(html){
					newTabContent.html(html);
				}
			});
		}
	}
	
	categoryTab(tabSwitcher);
	
	var allTabsWidth = newTab.outerWidth(true) * tabList.children().length;
	tabList.width(allTabsWidth);

	var tabScrollDiv = tabContainer.find('.right_menu_tab');
	var widthDiff = allTabsWidth- tabScrollDiv.width();
	if(widthDiff>0){
		tabScrollDiv.scrollLeft(widthDiff);
		tabContainer.find('.tab_nav.r').addClass('on');
	}
	
	return pageId;
}