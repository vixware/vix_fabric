<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
var code="";
var time = "";
var names = "";
function loadName(){
	name = $('#srm_name_or').val();
	name = Url.encode(name);
	name = Url.encode(name);
}
function loadCode(){
	code = $('#srm_code').val();
	code = Url.encode(code);
	code = Url.encode(code);	
}
function loadTime(){
	time = $('#entrytime').val();
	time = Url.encode(time);
	time = Url.encode(time);
}
function loadNames(){
	names = $('#nameS').val();
	names = Url.encode(names);
	names = Url.encode(names);
}
function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/system/warningCenterAction!goSaveOrUpdate.action?id='+id+"&parentId="+parentId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 300,
					title:"定时任务",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#timedTaskForm').validationEngine('validate')){
							$.post('${vix}/system/warningCenterAction!saveOrUpdate.action',
									 {
									  'timedTask.id':$("#timedTaskId").val(),
									  'timedTask.warningType.id':$("#warningTypeId").val(),
									  'timedTask.employee.id':$("#employeeId").val(),
									  'timedTask.subject':$("#subject").val(),
									  'timedTask.taskcontent':$("#taskcontent").val(),
									  'timedTask.entityTime':$("#entityTime").val(),
									  'timedTask.type':$("#type").val(),
									  'timedTask.creator':$("#creator").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/system/warningCenterAction!goList.action');
									}
								 );
							}else {
								return false;
							}
						}
						},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
function saveOrUpdateTaskPlan(id){
	$.ajax({
		  url:'${vix}/system/warningCenterAction!goSaveOrUpdateTaskPlan.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 300,
					title:"任务计划",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#taskPlanForm').validationEngine('validate')){
							$.post('${vix}/system/warningCenterAction!saveOrUpdateTaskPlan.action',
									 {
									  'taskPlan.id':$("#taskPlanId").val(),
									  'taskPlan.executionFrequency':$("#executionFrequency").val(),
									  'taskPlan.executionFrequencyUtil':$("#executionFrequencyUtil").val(),
									  'taskPlan.nextTime':$("#nextTime").val(),
									  'taskPlan.lastTime':$("#lastTime").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/system/warningCenterAction!goList.action');
									}
								 );
							}else {
								return false;
							}
						}
						},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
function saveOrUpdateNotice(id,parentId){
	$.ajax({
		  url:'${vix}/system/warningCenterAction!goSaveOrUpdateNotice.action?id='+id+"&parentId="+parentId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 300,
					title:"提醒设置",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#noticeForm').validationEngine('validate')){
							$.post('${vix}/system/warningCenterAction!saveOrUpdateNotice.action',
									 {
									  'notice.id':$("#noticeId").val(),
									  'notice.warningType.id':$("#warningTypeId").val(),
									  'notice.content':$("#noticecontent").val(),
									  'notice.remindTime':$("#remindTime").val(),
									  'notice.type':$("#type").val(),
									  'notice.priority':$("#priority").val(),
									  'notice.creator':$("#creator").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/system/warningCenterAction!goList.action');
									}
								 );
							}else {
								return false;
							}
						}
						},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
function deleteById(id){
	$.ajax({
		  url:'${vix}/system/warningCenterAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/system/warningCenterAction!goSingleList.action?name="+name,'timedTask');
			});
		  }
	});
}

/*搜索*/ 
function searchForRightContent(i){
	if(i==0){
		loadName();
		pager("start","${vix}/system/warningCenterAction!goSingleList.action?name="+name,'timedTask');
	}else{
		loadNames();
		loadCode();
		loadTime();
		pager("start","${vix}/system/warningCenterAction!goSingleList.action?name="+name+"&code="+code+'&entrytime='+time,'timedTask');
	}
}

//状态
function srmStatus(status){
	pager("state","${vix}/system/warningCenterAction!goSingleList.action?state="+state,'timedTask');
}
//最近使用
function srmRecent(rencentDate){
	pager("start","${vix}/system/warningCenterAction!goSingleList.action?updateTime="+rencentDate,'timedTask');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/system/warningCenterAction!goSingleList.action?name="+name,'timedTask');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/system/warningCenterAction!goSingleList.action?name="+name,'timedTask');
}
//分页
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/system/warningCenterAction!goSingleList.action?name="+name,'timedTask');
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
	pager("start","${vix}/system/warningCenterAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'timedTask');
}
function goSearch() {
	$.ajax({
	url : '${vix}/dtbcenter/vehiclePlanAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				loadcode();
				loadselectname();
				pager("start", "${vix}/dtbcenter/vehiclePlanAction!goSingleList.action?code=" + code + "&name=" + selectname, 'salesOrder');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" />系统管理 </a></li>
				<li><a href="#">预警中心 </a></li>
				<li><a href="#">预警和定时任务 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span>新增任务 </span> </a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<!-- 索引 -->
			</li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a> <!-- 状态 -->
				<ul>
					<li><a href="#" onclick="srmStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="cmn_unapproved_plan" /></a></li>
					<li><a href="#" onclick="srmStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="cmn_approval_by_plan" /></a></li>
					<li><a href="#" onclick="srmStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="cmn_approval_in" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /> </a> <!-- 最近使用 -->
				<ul>
					<li><a href="#" onclick="srmRecent('T1')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /></a></li>
					<li><a href="#" onclick="srmRecent('T2')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /></a></li>
					<li><a href="#" onclick="srmRecent('T3')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /></a></li>
					<li><a href="#" onclick="srmRecent('T4')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div>
			<!-- 内容 -->
			<label><s:text name="cmn_content" /> <input type="text" class="int" id="nameS"> </label> <label> <!-- 搜索 --> <input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent(0);"> <!-- 重置 --> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>"
				class="btn" name="" onclick="resetForContent(0);">
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="taskList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">	
			    var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/system/warningCenterAction!findModuleCategoryTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdType").val(treeNode.treeType);
					changePage("start","${vix}/system/warningCenterAction!goSingleList.action?id="+treeNode.id);
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/mail.png" alt="" /> 任务列表</li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /> </a></li>
								<li><a href="#"><s:text name='cmn_update' /> </a></li>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="timedTaskInfo"></b> <s:text name='cmn_to' /> <b class="timedTaskTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>

				</div>
				<div id="timedTask" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="timedTaskInfo"></b> <s:text name='cmn_to' /> <b class="timedTaskTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
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