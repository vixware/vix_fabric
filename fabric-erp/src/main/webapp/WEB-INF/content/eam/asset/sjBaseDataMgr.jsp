<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
}

function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/eam/accountManageAction!sjBaseDataMgr_eqLevel.action",'category');
}


function categoryTab(eventObj){
	var thisTab = $(eventObj);
	if(thisTab.not('li'))
		thisTab	= thisTab.parent();
	var otherTabs = thisTab.siblings();

	otherTabs.each(function(i){
		$(this).removeClass('current');
		var pageId = $(this).attr('pageId');
		$('#'+pageId).hide();
	});

	thisTab.addClass('current');
	var thisPageId = thisTab.attr('pageId');
	$('#'+thisPageId).show();

	var url = $(eventObj).attr('page_url');
	$.ajax({
		url:url,
		cache:false,
		success:function(html){
			$("#"+thisPageId).html(html);
		}
	});
}


$(function(){
	//载入分页列表数据
	$('.right_menu_tab').find('a:first').trigger('click');
	//左侧收缩
	bindSwitch();
	//按键效果
	addButtonClass();

});


</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />
					<s:text name="eam_enterpriseassetmanagement" /></a></li>
				<li><a href="#"><s:text name="eam_equipmentinformationmanagement" /></a></li>
				<li><a href="#">设备基础数据管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- c_head -->
<div class="box">
	<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
	<div id="right">
		<div id="tabNameDiv" class="right_menu nav_tabs">
			<div class="nav_tab">
				<div class="right_menu_tab">
					<ul>
						<li pageId="category" class="current"><a onclick="categoryTab(this)" page_url="${vix}/eam/accountManageAction!sjBaseDataMgr_eqLevel.action"><img src="img/mail.png" alt="" />设备层</a></li>
						<li pageId="category" class=""><a onclick="categoryTab(this)" page_url="${vix}/eam/accountManageAction!sjBaseDataMgr_eqType.action"><img src="img/mail.png" alt="" />设备类别</a></li>
						<li pageId="category" class=""><a onclick="categoryTab(this)" page_url="${vix}/eam/accountManageAction!sjBaseDataMgr_eqCategory.action"><img src="img/mail.png" alt="" />设备类型</a></li>
						<li pageId="category" class=""><a onclick="categoryTab(this)" page_url="${vix}/eam/accountManageAction!sjBaseDataMgr_eqTechParam.action"><img src="img/mail.png" alt="" />技术参数</a></li>
					</ul>
				</div>
				<div class="tab_nav l"></div>
				<div class="tab_nav r"></div>
			</div>
		</div>
		<div id="tabPageDiv" class="right_content">
			<div id="category" class="table"></div>
		</div>

	</div>
	<!-- right -->
</div>
<div class="c_foot">
	<span class="left_bg"></span> <span class="right_bg"></span>
</div>
<!-- c_foot -->
</div>

<script type="text/javascript"> 
<!--
//-->
</script>