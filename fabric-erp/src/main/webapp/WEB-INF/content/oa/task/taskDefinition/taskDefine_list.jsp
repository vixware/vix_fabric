<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var questName = "";
var executiveAgency ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadQuestName(){
	questName = $('#task_questName').val();
	questName = Url.encode(questName);
	questName = Url.encode(questName);
}
function loadUploadPersonName(){
	uploadPersonName = $('#task_uploadPersonName').val();
	uploadPersonName = Url.encode(uploadPersonName);
	uploadPersonName = Url.encode(uploadPersonName);
}
function loadValidity(){
	validity = $('#task_validity').val();
	validity = Url.encode(validity);
	validity = Url.encode(validity);
}
function loadTaskWeights(){
	taskWeights = $('#task_taskWeights').val();
	taskWeights = Url.encode(taskWeights);
	taskWeights = Url.encode(taskWeights);
}
function loadExecutiveAgency(){
	executiveAgency = $('#task_executiveAgency').val();
	executiveAgency = Url.encode(executiveAgency);
	executiveAgency = Url.encode(executiveAgency);
}
function loadAssessDepartment(){
	assessDepartment = $('#task_assessDepartment').val();
	assessDepartment = Url.encode(assessDepartment);
	assessDepartment = Url.encode(assessDepartment);
}

/*判断搜索内容是否为空*/
function validateSearch(temp){
	if(null == temp || "" == temp){
		return false;
	}
	return true;
}

/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#task_questName").val("");
		$("#task_uploadPersonName").val("");
		$("#task_validity").val("");
		$("#task_taskWeights").val("");
		$("#task_executiveAgency").val("");
		$("#task_assessDepartment").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/taskDefineAction!goSearchList.action?i="+i+"&questName="+name,'task');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/taskDefineAction!goSearch.action',
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
				pager("start", "${vix}/oa/taskDefineAction!goSearchList.action?questName=" + $('#questName').val() 
						+ "&uploadPersonName=" + $('#uploadPersonName').val()
						+ "&validity=" + $('#validity').val()
						+ "&taskWeights=" + $('#taskWeights').val()
						+ "&executiveAgency=" + $('#executiveAgency').val()
						+ "&assessDepartment=" + $('#assessDepartment').val(), 'task');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

//状态
 function saleOrderStatus(isPublish){
 	pager("start","${vix}/oa/taskDefineAction!goSingleList.action?isPublish="+isPublish,'task');
 }
 //最近使用
 function leastRecentlyUsed(updateTime){
 	pager("start","${vix}/oa/taskDefineAction!goSingleList.action?updateTime="+updateTime,'task');
 }

$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').animate({height: 'toggle', opacity: 'toggle'},500,function(){$('#number').css('overflow','visible');});
	return false;
});

/* $(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
}); */

$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/oa/task/taskDefinition/taskDefineAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}  
 
function updateIsPublish(id,isPublish){
	var param={'id':id};  
	$.ajax({ 
		url:"${vix}/oa/taskDefineAction!updateIsPublish.action",
		data:param,
		dataType:"text",
		success:function(data){
			/* alert('修改成功'); */
			loadName();
			pager("start","${vix}/oa/taskDefineAction!goSingleList.action?name="+name,'task');
		}
		});
}
 

function deleteById(id){
	$.ajax({
		  url:'${vix}/oa/task/taskDefinition/taskDefineAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='任务列表'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/oa/task/taskDefinition/taskDefineAction!goSingleList.action?name="+name,'task');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/oa/task/taskDefinition/taskDefineAction!goSingleList.action?name="+name,'task');
}
 
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/task/taskDefinition/taskDefineAction!goSingleList.action?name="+name,'task');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/oa/task/taskDefinition/taskDefineAction!goSingleList.action?name=",'task');
}

//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#taskOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/task/taskDefinition/taskDefineAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'task');
}

bindSearch();
var orderStatus = '';
function taskPager(tag){
	loadCode();
	pager(tag,"${vix}/oa/task/taskDefinition/taskDefineAction!goSingleList.action?name="+name+'&orderStatus='+orderStatus,'task');
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});

function showOrder(id){
	$.ajax({
		  url:'${vix}/oa/task/taskDefinition/taskDefineAction!showOrder.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pm_task.png" alt="" /> 协同办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/task/taskDefinition/taskDefineAction!goList.action?pageNo=${pageNo}');">任务管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/task/taskDefinition/taskDefineAction!goList.action?pageNo=${pageNo}');">任务列表</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a>
		</p>
	</div>
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
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="生效" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="终止" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="最近完成" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>标题: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="taskDefinitionList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.questName}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable switch" style="height: 500px; width: 7px;">
			<div id="switch_box" class="switch_btn off"></div>
			<div class="left_content current" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/oa/taskDefineAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/oa/taskDefineAction!goSingleList.action?parentId="+treeNode.id,"task");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/mail.png" alt="" />
					<s:text name="任务列表" /></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<%-- <ul>
						<li class="tp">
							<a href="#"><s:text name='cmn_operate'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_add'/></a></li>
								<li><a href="#"><s:text name='cmn_update'/></a></li>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCategoryCount1">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="taskPager('start','task');"></a></span> <span><a class="previous" onclick="taskPager('previous','task');"></a></span> <em>(<b class="taskInfo"></b> <s:text name='cmn_to' /> <b class="taskTotalCount"></b>)
						</em> <span><a class="next" onclick="taskPager('next','task');"></a></span> <span><a class="end" onclick="taskPager('end','task');"></a></span>
					</div>
				</div>
				<div id="task" class="table"></div>
				<div class="pagelist drop">
					<%-- <ul>
						<li class="ed">
							<a href="#"><s:text name='cmn_choose'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_mail'/></a></li>
								<li><a href="#"><s:text name="cmn_merger"/></a></li>
								<li><a href="#"><s:text name="cmn_export"/></a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCategoryCount2">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="taskPager('start','task');"></a></span> <span><a class="previous" onclick="taskPager('previous','task');"></a></span> <em>(<b class="taskInfo"></b> <s:text name='cmn_to' /> <b class="taskTotalCount"></b>)
						</em> <span><a class="next" onclick="taskPager('next','task');"></a></span> <span><a class="end" onclick="taskPager('end','task');"></a></span>
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