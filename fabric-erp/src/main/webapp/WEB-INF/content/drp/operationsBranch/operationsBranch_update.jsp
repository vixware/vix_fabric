<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	$(function() {
		//设置单据类型选中(修改)
		$("#bizStyle").val('${purchasePlan.style}');
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	});
	/** 保存采购计划 */
	function saveOrUpdatePlan() {
		var orderItemStr = "";
		/** 明细 */
		var dlData = $("#dlLineItem").datagrid("getRows");
		var piJson = JSON.stringify(dlData);
		if ($('#purchasePlanForm').validationEngine('validate')) {
			$.post('${vix}/drp/operationsBranchAction!saveOrUpdate.action', {
			'purchasePlan.id' : $("#id").val(),
			'purchasePlan.purchasePlanCode' : $("#purchasePlanCode").val(),
			'purchasePlan.purchasePlanName' : $("#purchasePlanName").val(),
			'purchasePlan.style' : $("#bizStyle").val(),
			'purchasePlan.amount' : $("#amount").val(),
			'purchasePlan.supplierName' : $("#supplierName").val(),
			'purchasePlan.createTime' : $("#createTime").val(),
			'purchasePlan.executeDepartment' : $("#executeDepartment").val(),
			'purchasePlan.planMaker' : $("#planMaker").val(),
			'purchasePlan.description' : $("#description").val(),
			'orderItemStr' : orderItemStr,
			'piJson' : piJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/operationsBranchAction!goList.action?menuId=menuOrder');
			});
		}
	}
	$("#purchasePlanForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">市场管理</a></li>
				<li><a href="#">市场调查</a></li>
				<li><a href="#">数据采集</a></li>
				<li><a href="#">分支机构运营状况</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${purchasePlan.id }" />
<div class="content">
	<form id="purchasePlanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/operationsBranchAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>分支机构经营状况 </b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">客户数量：</td>
											<td><input id="purchasePlanCode" name="purchasePlanCode" value="${purchasePlan.purchasePlanCode }" type="text" size="30" /></td>
											<td align="right">仓库规模：</td>
											<td><input id="purchasePlanName" name="purchasePlanName" value="${purchasePlan.purchasePlanName }" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<td align="right">配送解决模式：</td>
											<td><input id="purchasePlanCode" name="purchasePlanCode" value="${purchasePlan.purchasePlanCode }" type="text" size="30" /></td>
											<td align="right">车辆：</td>
											<td><input id="purchasePlanName" name="purchasePlanName" value="${purchasePlan.purchasePlanName }" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<td align="right">上年销售额：</td>
											<td><input id="purchasePlanCode" name="purchasePlanCode" value="${purchasePlan.purchasePlanCode }" type="text" size="30" /></td>
											<td align="right">上年利润：</td>
											<td><input id="purchasePlanName" name="purchasePlanName" value="${purchasePlan.purchasePlanName }" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>