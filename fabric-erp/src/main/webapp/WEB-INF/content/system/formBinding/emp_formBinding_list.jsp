<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
	function searchForContent() {
		loadLogisticsName();
		loadDeliveryAreaName();
		pager("start", "${vix}/system/formBindingAction!goSingleList.action?logisticsName=" + logisticsName + "&deliveryAreaName=" + deliveryAreaName, 'expressFeeRules');
		resetForContent();
	}
	function resetForContent() {
		$('#logisticsName').val('');
		$('#deliveryAreaName').val('');
	}
	//载入分页列表数据
	pager("start", "${vix}/system/formBindingAction!goSingleList.action?1=1", 'expressFeeRules');
	/* 点击根节点列表页显示的数据 */
	function loadRoot() {
		$('#selectId').val("");
		pager("start", "${vix}/system/formBindingAction!goSingleList.action?1=1", 'expressFeeRules');
	}
	//排序 
	function orderBy(orderField) {
		var orderBy = $("#categoryOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/system/formBindingAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&1=1" + "&parentId=" + $("#selectId").val(), 'expressFeeRules');
	}

	function expressFeeRulesPager(tag) {
		pager(tag, "${vix}/system/formBindingAction!goSingleList.action?1=1", 'expressFeeRules');
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
						url : '${vix}/system/formBindingAction!bindingEmp.action?businessFormId=' + returnValue + "&empId=" + $("#selectId").val(),
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
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 450px; overflow: auto;">
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;
				var setting = {
				async : {
				enable : true,
				url : "${vix}/oa/expensesSummaryPersonAction!findTreeToJson.action",
				autoParam : [ "id", "treeType" ]
				},
				callback : {
					onClick : onClick
				}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdType").val(treeNode.treeType);
					pager("start", "${vix}/oa/expensesSummaryPersonAction!goSingleList.action?id=" + treeNode.id + "&treeType=" + treeNode.treeType, "currentExpense");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="treeType" name="treeType" />
		<div id="right">
			<div class="right_content">
				<iframe src="${flow_ip}/form/businessform/vix_businessform_list" style="width: 100%; height: 550px; border-style: none; margin: 0px; padding: 0px;"> </iframe>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>