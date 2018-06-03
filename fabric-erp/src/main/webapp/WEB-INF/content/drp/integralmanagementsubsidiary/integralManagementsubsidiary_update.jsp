<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	$(function() {
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
			$.post('${vix}/drp/memberStorereturnAction!saveOrUpdate.action', {
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
				loadContent('${vix}/drp/memberStorereturnAction!goList.action?menuId=menuOrder');
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
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" />供应链</a></li>
				<li><a href="#">分销管理</a></li>
				<li><a href="#">积分管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/integralManagementsubsidiaryAction!goList.action');">积分管理明细</a></li>
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
						onclick="loadContent('${vix}/drp/integralManagementsubsidiaryAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>积分明细 </b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">编号：</td>
											<td><input id="purchasePlanCode" name="purchasePlanCode" value="${purchasePlan.purchasePlanCode }" type="text" size="30" /></td>
											<td align="right">客户姓名：</td>
											<td><input id="purchasePlanName" name="purchasePlanName" value="${purchasePlan.purchasePlanName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">单价：</td>
											<td><input id="purchasePlanCode" name="purchasePlanCode" value="${purchasePlan.purchasePlanCode }" type="text" size="30" /></td>
											<td align="right">折扣：</td>
											<td><input id="purchasePlanName" name="purchasePlanName" value="${purchasePlan.purchasePlanName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">使用积分：</td>
											<td><input id="purchasePlanCode" name="purchasePlanCode" value="${purchasePlan.purchasePlanCode }" type="text" size="30" /></td>
											<td align="right">剩余积分：</td>
											<td><input id="purchasePlanName" name="purchasePlanName" value="${purchasePlan.purchasePlanName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">订单号：</td>
											<td><input id="purchasePlanCode" name="purchasePlanCode" value="${purchasePlan.purchasePlanCode }" type="text" size="30" /></td>
											<td align="right">订单编码：</td>
											<td><input id="purchasePlanName" name="purchasePlanName" value="${purchasePlan.purchasePlanName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">购买时间：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${purchasePlan.createTime }' type='both' pattern='yyyy-MM-dd' />" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/drp/integralManagementsubsidiaryAction!getPurchasePlanItemsJson.action?id=${purchasePlan.id}',onClickRow: onDlClickRow1">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'itemCode',align:'center',width:120,editor:'text'">编码</th>
										<th data-options="field:'itemName',width:120,align:'center',editor:'text'">名称</th>
										<th data-options="field:'specification',width:120,align:'center',editor:'text'">规格型号</th>
										<th data-options="field:'itemType',width:120,align:'center',editor:'text'">类型</th>
										<th data-options="field:'amount',width:120,align:'center',editor:'numberbox'">数量</th>
										<th data-options="field:'price',width:120,align:'center',editor:'numberbox'">单价</th>
										<th data-options="field:'total',width:120,align:'center',editor:'numberbox'">金额</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
									onclick="saveDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlLineItem').datagrid();
								var editIndexDlLineItem = undefined;
								function endDlEditing1() {
									if (editIndexDlLineItem == undefined) {
										return true;
									}
									if ($('#dlLineItem').datagrid('validateRow', editIndexDlLineItem)) {
										$('#dlLineItem').datagrid('endEdit', editIndexDlLineItem);
										editIndexDlLineItem = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow1(index) {
									if (editIndexDlLineItem != index) {
										if (endDlEditing1()) {
											$('#dlLineItem').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlLineItem = index;
										} else {
											$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem);
										}
									}
								}
								function appendDlLineItem() {
									if (endDlEditing1()) {
										$('#dlLineItem').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlLineItem = $('#dlLineItem').datagrid('getRows').length - 1;
										$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem).datagrid('beginEdit', editIndexDlLineItem);
									}
								}
								function removeDlLineItem() {
									if (editIndexDlLineItem == undefined) {
										return;
									}
									$('#dlLineItem').datagrid('cancelEdit', editIndexDlLineItem).datagrid('deleteRow', editIndexDlLineItem);
									editIndexDlLineItem = undefined;
								}
								function saveDlLineItem() {
									if (endDlEditing1()) {
										$('#dlLineItem').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>