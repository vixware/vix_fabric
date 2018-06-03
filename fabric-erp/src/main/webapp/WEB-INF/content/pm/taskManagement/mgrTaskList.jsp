<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

function saveOrUpdate(projectId, id, parentId){
	var title = '新增任务';
	var url = '${vix}/pm/taskManagementAction!mgrTaskLEdit.action';
	if(id>0){
		title = '任务信息';
		if(id>0)
			url += '?id='+id;
	}else if(projectId>0){
		url = url + '?projectId='+projectId;
		if(parentId>0)
			url = url + '&parentId='+parentId;			
	}else{
		alert('请选择任务所属项目');
		return;
	}
	var newPageId = _tabShow(title,url,'_p_saveOrUpdate');
}

$(function(){
	//载入tab数据
	_load_tab_page_content();

	if($( "#left").length){
		$( "#left").resizable({
			maxHeight: 680,
			minHeight: 680,
			maxWidth: 400,
			minWidth: 300,
			handles: 'e'
		});
	}
});


</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module"></div>
	</h2>
	<!-- 
	<div class="drop">
		<p>
		<input name="" type="button" onclick="saveOrUpdate(0,$('#selectId').val());"  class="btn" value="<s:text name="cmn_add"/>" />
		
		</p>
	</div>
 -->
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>Launched in January 1998</p>
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
		<div search_page_id="tab_home" class="grid_search search_simple">
			<label><input type="text" name="eqName" class="int more" placeholder="任务名称" id="nameS"></label> <strong id="lb_search_advanced"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="grid_search search_advanced">
			<label> 项目名称 <input name="eqCode" type="text" class="int" />
			</label> <label> 项目编号 <input name="madeName" type="text" class="int" />
			</label> <label> <input type="button" class="btn search" value="<s:text name='cmn_search'/>" /> <input type="button" class="btn reset" value="<s:text name='cmn_reset'/>" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<!--  暂时没有数据，具体需要以设么逻辑去罗列快捷数据，可以不浪费资源
        	<s:iterator value="categoryList" var="c">
        		<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
        	</s:iterator>
 -->
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /> <a href="javascript:void(0);" onclick="_tabLocateUrl('${vix}/pm/taskManagementAction!mgrTaskListContent.action','tab_home')"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript"> 
<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/pm/taskManagementAction!loadProjectTree.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectEqCode").val(treeNode.eqCode);
					$("#selectIdTreeId").val(treeNode.tId);
					$('#selectName').val(treeNode.name);

					_tabLocateUrl("${vix}/pm/taskManagementAction!mgrTaskListContent.action?projectId="+treeNode.id,'tab_home');
				}
				
				zTree = $.fn.zTree.init($("#tree_root"), setting);
//-->
</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="-1" /> <input type="hidden" id="selectEqCode" name="selectEqCode" /> <input type="hidden" id="selectName" name="selectName" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div id="tab_content" class="tabbable addable_tab">
				<ul id="myTab" class="right_menu nav nav-tabs">
					<li pageId="tab_home" class="current"><a href="#tab_home" page="${vix}/pm/taskManagementAction!mgrTaskListContent.action"> <img src="img/mail.png" alt="" /> 任务列表
					</a></li>
				</ul>
				<div id="tab_content_page" class="right_content">
					<div id="tab_home" class="table"></div>
				</div>
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