<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
var name = "";
function loadName(){
	name = $('#nameS').val();
	name = Url.encode(name);
	name = Url.encode(name);
}
/*保存*/
function saveOrUpdate(id,parentId,treeType,pageNo){
		$.ajax({
			  url:'${vix}/drp/employeeManagementAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo+"&parentId="+$('#selectId').val()+"&treeType="+$('#treeType').val(),
			  cache: false,
			  success: function(html){
			  	$("#mainContent").html(html);
			  }
		});
	};
	//批量删除
	function deleteEntitys() {
		var ids = '';
		$("[name='chkId']").each(function() {
			if ($(this).attr('checked')) {
				ids += $(this).val() + ",";
			}
		});
		$.ajax({
			url : '${vix}/drp/employeeManagementAction!deleteByIds.action?ids=' + ids,
			cache : false,
			success : function(html) {
				asyncbox.success(html, "提示信息", function(action) {
					pager("start", "${vix}/drp/employeeManagementAction!goSingleList.action?1=1", 'employee');
				});
			}
		});
	}

/*搜索*/ 
function searchForContent(){
	loadName();
	pager("start","${vix}/drp/employeeManagementAction!goSingleList.action?name="+name,'employee');
}

/**重置搜索*/
function resetForContent(){
	$('#nameS').val('');
}

/** 状态 */
function saleOrderStatus(status) {
	pager("start", "${vix}/drp/employeeManagementAction!goSingleList.action?status=" + status, 'employee');
}
/* 最近使用 */
function leastRecentlyUsed(days) {
	pager("start", "${vix}/drp/employeeManagementAction!goSingleList.action?days=" + days, 'employee');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/drp/employeeManagementAction!goSingleList.action?name="+name,'employee');
//分页
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/drp/employeeManagementAction!goSingleList.action?name="+name,'employee');
}

//排序 
function orderBy(orderField){
	var orderBy = $("#employeeOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/drp/employeeManagementAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'employee');
}
//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/drp/employeeManagementAction!goSearch.action',
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
				 pager("start","${vix}/drp/employeeManagementAction!goSingleList.action?code="+$("#code").val()+"&name="+$("#selectName").val(),'employee');
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
				<li><a href="#"><img src="${vix}/common/img/drp_market.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">设置 </a></li>
				<li><a href="#">分销员工管理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name='cmn_add' /> </span> </a>
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
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>内容:<input type="text" class="int" id="nameS" placeholder="名称"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();" /></label> <label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="employeeList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});"><span style="display: none;">${c.chineseCharacter}</span>${c.name}</a></li>
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
						url:"${vix}/drp/distributionSystemRelationShipAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#treeType").val(treeNode.treeType);
					pager("start","${vix}/drp/employeeManagementAction!goSingleList.action?parentId="+treeNode.id+"&treeType="+treeNode.treeType,"employee");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/mail.png" alt="" /> 员工</li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /> </a></li>
								<li><a href="#"><s:text name='cmn_update' /> </a></li>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="employeeInfo"></b> <s:text name='cmn_to' /> <b class="employeeTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>

				</div>
				<div id="employee" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="employeeInfo"></b> <s:text name='cmn_to' /> <b class="employeeTotalCount"></b>)
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