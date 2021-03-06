<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	$(function() {
		//设置单据类型选中(修改)
		$("#orderType").val('${purchaseReturn.orderType}');
		//设置业务类型选中(修改)
		$("#businessType").val('${purchaseReturn.businessType}');
		//设置币种选中(修改)
		$("#currency").val('${purchaseReturn.currency}');

		$("#purchaseReturnForm").validationEngine();

		_pad_addInputCheckNumEvent('taxRate');
		_pad_addInputCheckNumEvent('totalAmount');
	});

	function saveOrUpdateOrder(tag) {
		if ($('#purchaseReturnForm').validationEngine('validate')) {
			$.post('${vix}/purchase/purchaseReturnAction!saveOrUpdate.action', {
			'purchaseReturn.id' : $("#id").val(),
			'purchaseReturn.code' : $("#code").val(),
			'purchaseReturn.name' : $("#name").val(),
			'purchaseReturn.orderType' : $("#orderType").val(),
			'purchaseReturn.businessType' : $("#businessType").val(),
			'purchaseReturn.requireDepartment' : $("#requireDepartment").val(),
			'purchaseReturn.supplierName' : $("#supplierName").val(),
			'purchaseReturn.currency' : $("#currency").val(),
			'purchaseReturn.taxRate' : $("#taxRate").val(),
			'purchaseReturn.purchasePerson' : $("#purchasePerson").val(),
			'purchaseReturn.contactPerson' : $("#contactPerson").val(),
			'purchaseReturn.deliveryDate' : $("#deliveryDate").val(),
			'purchaseReturn.postingDate' : $("#postingDate").val(),
			'purchaseReturn.reason' : $("#reason").val(),
			'updateField' : updateField,
			'purchaseReturn.groupCode' : $("#groupCode").val()
			}, function(result) {
				if (result != null) {
					var entityId = $('#id').val();
					if (!entityId || entityId == '') {
						//新建
						$('#id').val(result);

						if (_pad_execute_after_save()) {
							showMessage('基本信息自动保存');
							return;
						}

					}
					showMessage('信息保存成功');
					if (tag == 1)
						_pad_page_view_back();
					else
						saveOrUpdate(0, $('#selectId').val());
				} else {
					showErrorMessage('信息保存失败');
				}
			});
		}
	}

	/** 选择单选供应商 */
	function chooseRadioSupplier() {
		$.ajax({
		url : '${vix}/purchase/purchaseOrderAction!goChooseRadioSupplier.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择供应商",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						//alert(returnValue); 
						var rv = returnValue.split(",");
						$("#supplierName").val(rv[1]);
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

	//选择需求部门 
	function chooseRequireDepartment() {
		$.ajax({
		url : '${vix}/hr/rePlanAction!goChooseCategory.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择采购组织",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#requireDepartment").val(result[1]);
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
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module"></div>
	</h2>
</div>

<div class="content">
	<form id="purchaseReturnForm">
		<input type="hidden" id="id" name="id" value="${purchaseReturn.id }" /> <input type="hidden" id="groupCode" name="groupCode" value="${purchaseReturn.groupCode }" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder(1);" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#" onclick="saveOrUpdateOrder(2);"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <s:if test="isAllowAudit == 1">
							<s:if test="stockRecords.status != 1">
								<a onclick="" href="#"><img width="22" height="22" alt="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a>
							</s:if>
						</s:if> <a class="f_btn edit_back" href="javascript:_pad_page_view_back();"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>采购退货单</b> <i></i>
					</strong>
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
											<td align="right">编码：</td>
											<td><input id="code" name="code" value="${purchaseReturn.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">主题：</td>
											<td><input id="name" name="name" value="${purchaseReturn.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">单据类型：</td>
											<td><select id="orderType" name="orderType" style="width: 50" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="PUR_RETURN">采购退货单</option>
											</select></td>
											<td align="right">业务类型：</td>
											<td><select id="businessType" name="businessType" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<s:iterator value="listPurchaseReturnBizType()" var="entity">
														<option value="${entity.code}">${entity.name}</option>
													</s:iterator>
											</select></td>
										</tr>
										<tr>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${purchaseReturn.supplierName }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /></td>
											<td align="right">需求部门：</td>
											<td><input id="requireDepartment" name="requireDepartment" value="${purchaseReturn.requireDepartment }" type="text" size="30" onchange="fieldChanged(this);" /> <input class="btn" type="button" value="选择" onclick="chooseRequireDepartment();" /></td>
										</tr>
										<tr>
											<td align="right">币种：</td>
											<td><select id="currency" name="currency" style="width: 50" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1">人民币</option>
													<option value="2">美元</option>
											</select></td>
											<td align="right">税率：</td>
											<td><input id="taxRate" name="taxRate" value="${purchaseReturn.taxRate }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">过账日期：</td>
											<td><input id="postingDate" name="postingDate" value="<s:date name="purchaseReturn.postingDate" format="yyyy-MM-dd"/>" type="text" readonly="readonly" onclick="showTime('postingDate','yyyy-MM-dd')" onchange="fieldChanged(this);" /> <img onclick="showTime('postingDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
											<td align="right">交货日期：</td>
											<td><input id="deliveryDate" name="deliveryDate" value="<s:date name="purchaseReturn.deliveryDate" format="yyyy-MM-dd"/>" readonly="readonly" type="text" class="validate[required] text-input" onclick="showTime('deliveryDate','yyyy-MM-dd')" onchange="fieldChanged(this);" /><span style="color: red;">*</span> <img
												onclick="showTime('deliveryDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">采购人：</td>
											<td><input id="purchasePerson" name="purchasePerson" value="${purchaseReturn.purchasePerson }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">联系人：</td>
											<td><input id="contactPerson" name="contactPerson" value="${purchaseReturn.contactPerson }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">退货原因：</td>
											<td><input id="reason" name="reason" value="${purchaseReturn.reason }" type="text" size="50" onchange="fieldChanged(this);" /></td>
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
							<table id="dlLineItem" class="easyui-datagrid" style="height: 300px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/purchase/purchaseReturnAction!getPurchaseReturnItemsJson.action'">
								<thead>
									<tr>
										<th data-options="field:'itemCode',align:'center',width:120,editor:'text'">编码</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">名称</th>
										<th data-options="field:'skuCode',align:'center',width:120,editor:'text'">SKU码</th>
										<th data-options="field:'barCode',align:'center',width:120,editor:'text'">BAR码</th>
										<th data-options="field:'specification',width:120,align:'center',editor:'text'">规格型号</th>
										<th data-options="field:'amount',width:120,align:'center',editor:'numberbox'">订货数量</th>
										<th data-options="field:'unit',width:120,align:'center',editor:'numberbox'">计量单位</th>
										<th data-options="field:'price',width:100,align:'center',editor:'numberbox'">单价</th>
										<th data-options="field:'total',width:100,align:'center',editor:'numberbox'">含税价</th>
										<th data-options="field:'taxRate',width:120,align:'center',editor:'numberbox'">税率</th>
										<th data-options="field:'recieveWareHouse',width:120,align:'center',editor:'text'">收货仓库</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem1(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="editItem1()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem1()"><span class="l-btn-left"><span
										class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid1() {
									$('#dlLineItem').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
									//修改下方数量
									$.ajax({
									url : '${vix}/purchase/purchaseReturnAction!getPurchaseReturnItemsCount.action?id=' + $("#id").val(),
									cache : false,
									type : 'json',
									success : function(json) {
										if (json) {
											$("#detail_total_fee").val(json.totalFee);
											$("#detail_total_fee_tax").val(json.totalFeeTax);
											$("#detail_total_tax").val(json.totalTax);
										}
									}
									});
								}
								reloadDataGrid1();

								function addItem1(id) {
									if (!id)
										id = 0;
									if (!_pad_check_entity_saved_id(saveOrUpdateOrder, addItem1))
										return;

									$.ajax({
									url : '${vix}/purchase/purchaseReturnAction!goAddPurchaseReturnItems.action',
									data : 'supplierId=' + $("#supplierId").val() + '&id=' + id,
									cache : false,
									type : 'post',
									success : function(html) {
										asyncbox.open({
										modal : true,
										width : 1000,
										height : 580,
										title : "明细",
										html : html,
										callback : function(action) {
											if (action == 'cancel' || action == 'close') {
												reloadDataGrid1();
											}
										},
										btnsbar : [ {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function editItem1() {
									var rows = $('#dlLineItem').datagrid('getSelected');
									if (null == rows) {
										alert("请选择一条数据！");
										return;
									}
									addItem1(rows.id);
								}

								function removeItem1() {
									var row = $('#dlLineItem').datagrid('getSelected');
									if (row) {
										asyncbox.confirm('是否删除该订单明细?', '提示信息', function(action) {
											if (action == 'ok') {
												$.ajax({
												url : '${vix}/purchase/purchaseReturnAction!deletePurchaseReturnItem.action?id=' + row.id,
												cache : false,
												success : function(html) {
													if (html == 'success') {
														showMessage('数据已删除');
														reloadDataGrid1();
													}
												}
												});
											}
										});
									} else {
										showMessage("请选择一条数据!");
									}
								}
							</script>
						</div>
					</div>
				</div>
				<dd class="clearfix">
					<div class="order_table">
						<table class="addtable_c">
							<tr>
								<td align="right">${vv:varView("vix_mdm_item")}总计:</td>
								<td><input id="detail_total_fee" type="text" size="30" onchange="fieldChanged(this);" /></td>
								<td align="right">税额:</td>
								<td><input id="detail_total_tax" type="text" size="30" onchange="fieldChanged(this);" /></td>
								<td align="right">含税总计:</td>
								<td><input id="detail_total_fee_tax" type="text" size="30" onchange="fieldChanged(this);" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
