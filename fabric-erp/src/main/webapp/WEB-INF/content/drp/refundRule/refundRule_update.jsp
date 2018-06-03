<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateRefundRule() {
		var refundRuleDetailData = $("#refundRuleDetail").datagrid("getRows");
		var refundRuleDetailJson = JSON.stringify(refundRuleDetailData);
		if ($('#refundRuleForm').validationEngine('validate')) {
			$.post('${vix}/drp/refundRuleAction!saveOrUpdate.action', {
			'refundRule.id' : $("#id").val(),
			'refundRule.code' : $("#code").val(),
			'refundRule.rrName' : $("#rrName").val(),
			'refundRule.rrType' : $("#rrType").val(),
			'refundRule.customerAccount.id' : $("#customerAccountId").val(),
			'refundRule.inventoryCurrentStock.id' : $("#inventoryCurrentStockId").val(),
			'refundRule.computeTarget' : $("#computeTarget").val(),
			'refundRule.computeStyle' : $("#computeStyle").val(),
			'updateField' : updateField,
			'refundRuleDetailJson' : refundRuleDetailJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/refundRuleAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#refundRuleForm").validationEngine();
	function chooseCustomerAccount() {
		$.ajax({
		url : '${vix}/drp/membershipCardmanagementAction!goChooseCustomerAccount.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择会员",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#customerAccountId").val(rv[0]);
						$("#customerName").val(rv[1]);
					} else {
						asyncbox.success("请选择分类信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function chooseInventoryCurrentStock() {
		$.ajax({
		url : '${vix}/drp/refundRuleAction!goChooseInventoryCurrentStock.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择商品",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#itemCode").val(rv[0]);
						$("#itemName").val(rv[1]);
						$("#inventoryCurrentStockId").val(rv[2]);
					} else {
						asyncbox.success("请选择商品信息!", "提示信息");
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
				<li><a href="#"><img src="${vix}/common/img/drp_distribution_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">经销商管理</a></li>
				<li><a href="#">返点管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/refundRuleAction!goList.action');">规则设定 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${refundRule.id}" />
<input type="hidden" id="code" name="code" value="${refundRule.code}" />
<div class="content">
	<form id="refundRuleForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateRefundRule()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/refundRuleAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">规则编码：</td>
											<td><input id="code" name="code" value="${refundRule.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"><span style="color: red;">*</span></td>
											<td align="right">规则名称：</td>
											<td><input id="rrName" name="rrName" value="${refundRule.rrName }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">商品编码：</td>
											<td><input id="itemCode" name="itemCode" value="${refundRule.inventoryCurrentStock.itemcode }" type="text" size="30" onchange="fieldChanged(this);" /><input type="hidden" id="inventoryCurrentStockId" name="inventoryCurrentStockId" value="${refundRule.inventoryCurrentStock.id}" /> <input class="btn" type="button" value="选择"
												onclick="chooseInventoryCurrentStock();" /></td>
											<td align="right">商品名称：</td>
											<td><input id="itemName" name="itemName" value="${refundRule.inventoryCurrentStock.itemname }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">客户姓名：</td>
											<td><input id="customerName" name="customerName" value="${refundRule.customerAccount.name }" type="text" size="30" onchange="fieldChanged(this);" /><input type="hidden" id="customerAccountId" name="customerAccountId" value="${refundRule.customerAccount.id}" /> <input class="btn" type="button" value="选择"
												onclick="chooseCustomerAccount();" /></td>
											<td align="right">计算对象：</td>
											<td><input id="computeTarget" name="computeTarget" value="${refundRule.computeTarget }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">计算方式：</td>
											<td><select id="computeStyle" name="computeStyle" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1" selected="selected">销售额</option>
													<option value="2">实际付款额</option>
											</select></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />规则明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="refundRuleDetail" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers : true,toolbar: '#dlAddressTb',url: '${vix}/drp/refundRuleAction!getRefundRuleDetailJson.action?id=${refundRule.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'fieldFrom',width:100,align:'center',editor:'numberbox'">从</th>
										<th data-options="field:'fieldTo',width:100,align:'center',editor:'numberbox'">到</th>
										<th data-options="field:'unit',width:100,align:'center',editor:'text'">计量单位</th>
										<th data-options="field:'ratio',width:100,align:'center',editor:'numberbox'">返款比率</th>
										<th data-options="field:'currency',width:100,align:'center',editor:'text'">币种</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span> </a>
							</div>
							<script type="text/javascript">
								$('#refundRuleDetail').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#refundRuleDetail').datagrid('validateRow', editIndexDlAddress)) {
										$('#refundRuleDetail').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#refundRuleDetail').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#refundRuleDetail').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#refundRuleDetail').datagrid('appendRow', {});
										editIndexDlAddress = $('#refundRuleDetail').datagrid('getRows').length - 1;
										$('#refundRuleDetail').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#refundRuleDetail').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#refundRuleDetail').datagrid('acceptChanges');
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
