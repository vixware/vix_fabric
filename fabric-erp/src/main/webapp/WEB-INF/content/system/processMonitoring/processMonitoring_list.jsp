<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
function searchForContent(){
	loadLogisticsName();
	loadDeliveryAreaName();
	pager("start","${vix}/system/formBindingAction!goSingleList.action?logisticsName="+logisticsName+"&deliveryAreaName="+deliveryAreaName,'expressFeeRules');
	resetForContent();
}
function resetForContent(){
	 $('#logisticsName').val('');
	 $('#deliveryAreaName').val('');
}
//载入分页列表数据
pager("start","${vix}/system/formBindingAction!goSingleList.action?1=1",'expressFeeRules');
/* 点击根节点列表页显示的数据 */
function loadRoot(){
	$('#selectId').val("");
	pager("start","${vix}/system/formBindingAction!goSingleList.action?1=1",'expressFeeRules');
}
//排序 
function orderBy(orderField){
	var orderBy = $("#categoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/system/formBindingAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&1=1"+"&parentId="+$("#selectId").val(),'expressFeeRules');
}

function expressFeeRulesPager(tag){
	pager(tag,"${vix}/system/formBindingAction!goSingleList.action?1=1",'expressFeeRules');
}
bindSearch();

function goChooseBusinessFormTemplate() {
	$.ajax({
	url : '${vix}/system/formBindingAction!goChooseBusinessFormTemplate.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 900,
		height : 550,
		title : "选择表单",
		html : html,
		callback : function(action, returnValue) {
			if (action == 'ok') {
				if (returnValue != '') {
					$.ajax({
					url : '${vix}/system/formBindingAction!binding.action?businessFormId=' + returnValue + "&organizationUnitId="+$("#selectId").val(),
					cache : false,
					success : function(result) {
						showMessage(result);
						setTimeout("", 1000);
					}
					});
				} else {
					asyncbox.success("请选择表单!", "<s:text name='vix_message'/>");
					return false;
				}
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> 系统管理 </a></li>
				<li><a href="#">表单管理 </a></li>
				<li><a href="#">表单绑定 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<a href="#" onclick="goChooseBusinessFormTemplate();"><span>绑定</span>
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
				<p>表单绑定</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
		</ul>
		<div>
			<label>公司:<input type="text" class="int" id="logisticsName" placeholder="公司"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="expressFeeRulesList" var="c">
				<li><a href="#" onclick="goSaveOrUpdate(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div id="tree_root" class="ztree" style="padding: 0; height: 500px; overflow: scroll;"></div>
			</div>
			<script type="text/javascript">
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/system/formBindingAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#treeType").val(treeNode.treeType);
					pager("start","${vix}/system/formBindingAction!goSingleList.action?organizationUnitId="+treeNode.id,"expressFeeRules");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="treeType" name="treeType" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="expressFeeRulesPager('start');"></a> </span> <span><a class="previous" onclick="expressFeeRulesPager('previous');"></a> </span> <em>(<b class="expressFeeRulesInfo"></b> <s:text name='cmn_to' /> <b class="expressFeeRulesTotalCount"></b>)
						</em> <span><a class="next" onclick="expressFeeRulesPager('next');"></a> </span> <span><a class="end" onclick="expressFeeRulesPager('end');"></a> </span>
					</div>
				</div>
				<div id="expressFeeRules" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="expressFeeRulesPager('start');"></a> </span> <span><a class="previous" onclick="expressFeeRulesPager('previous');"></a> </span> <em>(<b class="expressFeeRulesInfo"></b> <s:text name='cmn_to' /> <b class="expressFeeRulesTotalCount"></b>)
						</em> <span><a class="next" onclick="expressFeeRulesPager('next');"></a> </span> <span><a class="end" onclick="expressFeeRulesPager('end');"></a> </span>
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