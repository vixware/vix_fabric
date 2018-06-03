<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
/* 内容 */
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/crm/business/marketingActivitiesAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
}
function goStart(id){
	$.ajax({
		  url:'${vix}/crm/business/marketingActivitiesAction!goStartFlowTask.action?id='+id,
		  cache: false,
		  success: function(result){
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/crm/business/marketingActivitiesAction!goList.action');
		  }
	});
}
function goStop(id){
	$.ajax({
		  url:'${vix}/crm/business/marketingActivitiesAction!goStopFlowTask.action?id='+id,
		  cache: false,
		  success: function(result){
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/crm/business/marketingActivitiesAction!goList.action');
		  }
	});
}
//载入分页列表数据
pager("start","${vix}/crm/business/marketingActivitiesAction!goListContent.action?1=1",'marketingAutomationProcess');
//排序 
function orderBy(orderField){
	var orderBy = $("#marketingAutomationProcessOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/business/marketingActivitiesAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy,'marketingAutomationProcess');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/crm/business/marketingActivitiesAction!goListContent.action?name="+name,'marketingAutomationProcess');
}
function chooseMemberShipSubdivision(id) {
	$.ajax({
	url : '${vix}/crm/business/marketingActivitiesAction!goMemberShipSubdivisionList.action?id='+id,
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 900,
		height : 550,
		title : "选择会员细分条件",
		html : html,
		callback : function(action, returnValue) {
			if (action == 'ok') {
				if (returnValue != '') {
					$.ajax({
					url : '${vix}/crm/business/marketingActivitiesAction!bindMemberShipSubdivision.action?memberShipSubdivisionId=' + returnValue + "&id="+$('#id').val(),
					cache : false,
					success : function(result) {
						showMessage(result);
						setTimeout("", 1000);
					}
					});
				} else {
					asyncbox.success("请选择会员细分条件!", "<s:text name='vix_message'/>");
					return false;
				}
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
}
/** 状态 */
function listbystatus(status) {
	pager("start", "${vix}/crm/business/marketingActivitiesAction!goListContent.action?status=" + status, 'marketingAutomationProcess');
}
/* 最近使用 */
function leastRecentlyUsed(days) {
	pager("start", "${vix}/crm/business/marketingActivitiesAction!goListContent.action?days=" + days, 'marketingAutomationProcess');
}
function searchForContent() {
	loadName();
	pager("start","${vix}/crm/business/marketingActivitiesAction!goListContent.action?name="+name,'marketingAutomationProcess');
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">会员交互管理 </a></li>
				<li><a href="#">会员管理 </a></li>
				<li><a href="#">智能营销 </a></li>
				<li><a href="#">营销活动</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate($('#selectId').val());"><span>新增</span> </a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="listbystatus('0')"><img alt="" src="img/uncommitted.png"> 未提交 </a></li>
					<li><a href="#" onclick="listbystatus('1')"><img alt="" src="img/unaudited.png"> 待审批 </a></li>
					<li><a href="#" onclick="listbystatus('2')"><img alt="" src="img/verifying.png"> 审批中 </a></li>
					<li><a href="#" onclick="listbystatus('3')"><img alt="" src="img/approved.png"> 已完成 </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="nameS"> </label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn"
				onclick="resetForContent(0)"> </label>
			<%-- <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label> --%>
		</div>
		<%-- <div class="search_advanced">
			<label><s:text name="wim_requisition_number1" /><input type="text" class="int" name="" id="code1"> </label> <label><s:text name="wim_v_warehouse" />：<input type="text" class="int" name="" id="outwarehousecodes"></label> <label><s:text name="wim_call_warehouse" />：<input type="text" class="int" name=""
				id="inwarehousecodes"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(1);"> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(1)">
			</label>
		</div> --%>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="wimTransvouchList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.tvcode}</a></li>
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
						url:"${vix}/crm/business/marketingActivitiesAction!findMembershipSubdivisionTree.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/crm/business/marketingActivitiesAction!goListContent.action?parentId="+treeNode.id,'marketingAutomationProcess');
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
			<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="orderBatchInfo"></b> <s:text name='cmn_to' /> <b class="orderBatchTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="marketingAutomationProcess" class="table"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="orderBatchInfo"></b> <s:text name='cmn_to' /> <b class="orderBatchTotalCount"></b>)
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