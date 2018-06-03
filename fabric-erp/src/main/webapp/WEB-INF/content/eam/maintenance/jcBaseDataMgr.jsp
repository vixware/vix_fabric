<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
}


function goSearchEquipment(){
	var searchKW = $('#nameS').val();
	var searchTabTitle = searchKW?'搜索-'+searchKW:'搜索';
	_tabShow(searchTabTitle,"${vix}/eam/preMaintainAction!jcBaseDataMgrList.action?name="+searchKW,'_p_search');
}

function clearSearch(){
	$('#nameS').val('');
	return false;
}

function saveOrUpdate(id,parentId){
	var title = '创建基础数据';
	if(id>0)
		title = '基础数据信息';
	var newPageId = _tabShow(title,'${vix}/eam/preMaintainAction!jcBaseDataMgrDetail.action','_p_saveOrUpdate');
}

function deleteById(id){
	$.ajax({
		  url:'${vix}/eam/preMaintainAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/eam/preMaintainAction!jcBaseDataMgrList.action?name="+name,'category');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/eam/preMaintainAction!jcBaseDataMgrList.action?name="+name,'category');
}

function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/eam/preMaintainAction!jcBaseDataMgrList.action?name=",'category');
}

//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#categoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/eam/preMaintainAction!jcBaseDataMgrList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
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
	
	categoryPager('start',thisPageId);
}

//列表翻页
function categoryPager(tag,entity){
	loadName();
	if(entity == 'category'){
		pager(tag,"${vix}/eam/preMaintainAction!jcBaseDataMgrList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
	}
}

$(function(){
	if($('#numBox').length)
		$('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});

	//搜索内容处理
	loadName();
	//载入分页列表数据
	pager("start","${vix}/eam/preMaintainAction!jcBaseDataMgrList.action?name="+name,'category');
	//高级搜索效果
	bindSearch();
	//左侧收缩
	bindSwitch();
	//索引效果
	bindIndexSearch();
	//按键效果
	addButtonClass();

	//放到css.css里了
	//$('.right_menu').css({padding:'0 24px'});
	//$('.right_menu_tab ul').css("display","table");
	
	initNavTabsEvent();
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
				<li><a href="#">预防性维护管理</a></li>
				<li><a href="#">基础数据管理</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
			<li><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_mode" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_seven_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_month" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label><input type="text" class="int more" placeholder="设备编号 或 设备名称" id="nameS"></label> <label> <input name="searchBtn" id="searchBtn" onclick="goSearchEquipment()" type="button" class="btn" value="<s:text name='cmn_search'/>" /> <input name="" type="button" onclick="javascript:return clearSearch()" class="btn"
				value="<s:text name='cmn_reset'/>" />
			</label> <strong id="lb_search_advanced"><s:text name='cmn_advance_search' /></strong> <label style="margin-left: 30px;"> <input name="" type="button" onclick="saveOrUpdate(0,$('#selectId').val());" class="btn" value="<s:text name="cmn_add"/>" />
			</label>
		</div>
		<div class="search_advanced">
			<label> 制造商 <input name="" type="text" class="int" />
			</label> <label> 位置 <input name="" type="text" class="int" />
			</label> <label> 安装日期 <input id="_installDate1" name="_installDate1" class="int" value="" readonly="readonly" type="text" /> <img onclick="showTime('_installDate1','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
			</label> <label> 至 <input id="_installDate2" name="_installDate2" class="int" value="" readonly="readonly" type="text" /> <img onclick="showTime('_installDate2','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="categoryList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript"> 
<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/eam/preMaintainAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/eam/preMaintainAction!jcBaseDataMgrList.action?parentId="+treeNode.id,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
//-->
</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div id="tabNameDiv" class="right_menu nav_tabs">
				<div class="nav_tab">
					<div class="right_menu_tab">
						<ul>
							<li pageId="category" class="current"><a onclick="categoryTab(this)"><img src="img/mail.png" alt="" />基础数据管理</a></li>
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