<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<script type="text/javascript">
	function initdata() {
		//设置单据类型选中(修改)
		$("#orderType").val('${purchaseOrder.orderTypeCode}');
		//设置业务类型选中(修改)
		$("#bizType").val('${purchaseOrder.businessTypeCode}');
		//设置币种选中(修改)
		$("#currency").val('${purchaseOrder.currencyCode}');
		//设置状态选中(修改)
		$("#status").val('${purchaseOrder.status}');

		$("#purchaseOrderForm").validationEngine();

		_pad_addInputCheckNumEvent('rate');
	}
	initdata();
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	/** 保存采购订单 */
	function saveOrUpdateOrder(tag) {
		if ($('#purchaseOrderForm').validationEngine('validate')) {
			$.post('${vix}/purchase/purchaseOrderAction!saveOrUpdate.action', {
			'purchaseOrder.id' : $("#id").val(),
			'purchaseOrder.code' : $("#code").val(),
			'purchaseOrder.name' : $("#name").val(),
			'purchaseOrder.orderTypeCode' : $("#orderTypeCode").val(),
			'purchaseOrder.orderType' : $("#orderType").find("option:selected").text(),
			'purchaseOrder.businessTypeCode' : $("#bizTypeCode").val(),
			'purchaseOrder.businessType' : $("#bizType").find("option:selected").text(),
			'purchaseOrder.supplierCode' : $("#supplierCode").val(),
			'purchaseOrder.supplierName' : $("#supplierName").val(),
			'purchaseOrder.supplierId' : $("#supplierId").val(),
			'purchaseOrder.purchaseOrg' : $("#purchaseOrg").val(),
			'purchaseOrder.purchaseOrgId' : $("#purchaseOrgId").val(),
			'purchaseOrder.purchasePerson' : $("#purchasePerson").val(),
			'purchaseOrder.currencyCode' : $("#currency").val(),
			'purchaseOrder.currency' : $("#currency").find("option:selected").text(),
			//'purchaseOrder.taxRate':$("#taxRate").val(),
			'purchaseOrder.postingDate' : $("#postingDate").val(),
			'purchaseOrder.deliveryDate' : $("#deliveryDate").val(),
			'purchaseOrder.createTime' : $("#createTime").val(),
			'purchaseOrder.contactPerson' : $("#contactPerson").val(),
			'purchaseOrder.mark' : $("#mark").val(),
			'purchaseOrder.project' : $("#project").val(),
			'purchaseOrder.rate' : $("#rate").val(),
			'purchaseOrder.prepayments' : $("#prepayments").val(),
			'purchaseOrder.status' : $("#status").val(),
			'purchaseOrder.memo' : $("#memo").val(),
			'purchaseOrder.payPeriod' : $("#payPeriod").val(),
			'purchaseOrder.account' : $("#account").val(),
			'purchaseOrder.payerCode' : $("#payerCode").val(),
			'purchaseOrder.payerName' : $("#payerName").val(),
			'purchaseOrder.contractTime' : $("#contractTime").val(),
			'purchaseOrder.carrier' : $("#carrier").val(),
			'purchaseOrder.subtotal' : $("#subtotal").val(),
			'purchaseOrder.shipping' : $("#shipping").val(),
			'purchaseOrder.tax' : $("#tax").val(),
			'updateField' : updateField,
			'purchaseOrder.groupCode' : $("#groupCode").val()
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
					if (tag == 1) {
						_pad_page_view_back();
					} else {
						saveOrUpdate(0, $('#selectId').val());
					}
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
						$("#supplierCode").val(rv[2]);
						$("#supplierName").val(rv[1]);
						$("#supplierId").val(rv[0]);
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
	/** 快速新建供应商 */
	function createSupplier() {
		$.ajax({
		url : '${vix}/purchase/purchaseOrderAction!goAddQuicklySupplier.action?',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 800,
			height : 500,
			title : "快速新建供应商",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#supplierForm').validationEngine('validate')) {
						$.post('${vix}/purchase/purchaseOrderAction!saveOrUpdateSupplier.action', {
						'supplier.id' : $("#srmId").val(),
						'supplier.code' : $("#newSupplierCode").val(),
						'supplier.name' : $("#newSupplierName").val(),
						'supplier.shortName' : $("#newShortName").val(),
						'supplier.artificialPerson' : $("#newArtificialPerson").val(),
						'supplier.supplierCategory.id' : $("#newParentId").val(),
						'supplier.catalog' : $("#newCatalog").val(),
						'supplier.type' : $("#newType").val(),
						'supplier.openingBank' : $("#newOpeningBank").val(),
						'supplier.bankAccount' : $("#newBankAccount").val(),
						'supplier.contacts' : $("#newContacts").val(),
						'supplier.telephone' : $("#newTelephone").val(),
						'supplier.portraiture' : $("#newPortraiture").val(),
						'supplier.email' : $("#newEmail").val(),
						'supplier.deliveryAddress' : $("#newDeliveryAddress").val()
						}, function(result) {
							var rv = result.split(",");
							setTimeout("", 1000);
							$("#supplierCode").val(rv[2]);
							$("#supplierName").val(rv[1]);
							$("#supplierId").val(rv[0]);
						}, 'text');
					} else {
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	//弹出组织树
	function chooseParentCategory() {
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
						if (result[0] && result[0].length > 1) {
							$("#purchaseOrgId").val(result[0].substring(0, result[0].length - 1));
							$("#purchaseOrg").val(result[1]);
						}
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

	/** 选择项目 */
	function chooseRadioProject() {
		$.ajax({
		url : '${vix}/purchase/purchaseOrderAction!goChooseRadioProject.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择项目",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						//alert(returnValue); 
						var rv = returnValue.split(",");
						$("#project").val(rv[1]);
					} else {
						asyncbox.success("请选择项目!", "提示信息");
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
	<form id="purchaseOrderForm">
		<input type="hidden" id="id" name="id" value="${purchaseOrder.id}" /> <input type="hidden" id="groupCode" name="groupCode" value="${purchaseOrder.groupCode }" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder(1);" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#" onclick="saveOrUpdateOrder(2);"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <s:if test="isAllowAudit == 1">
							<s:if test="stockRecords.status != 1">
								<a onclick="" href="#"><img width="22" height="22" alt="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a>
							</s:if>
						</s:if> <a class="f_btn edit_back" href="javascript:_pad_page_view_back();"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增采购订单</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">订单编码：</td>
											<td><input id="code" name="code" value="${purchaseOrder.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">订单主题：</td>
											<td><input id="name" name="name" value="${purchaseOrder.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">单据类型：</td>
											<td><select id="orderType" name="orderType" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="PUR_ORDER">采购订单</option>
											</select> <input type="hidden" id="orderTypeCode" name="orderTypeCode" value="${purchaseOrder.orderTypeCode }" /> <span style="color: red;">*</span></td>
											<td align="right">状态：</td>
											<td><select id="status" name="status" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="S1">待确认</option>
													<option value="S2">审批中</option>
													<option value="S3">已发货</option>
													<option value="S4">已完成</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${purchaseOrder.supplierName }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span> <input type="hidden" id="supplierCode" name="supplierCode" value="${purchaseOrder.supplierCode }" /> <input
												type="hidden" id="supplierId" name="supplierId" value="${purchaseOrder.supplierId }" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /> <input class="btn" type="button" value="快速新建" onclick="createSupplier();" /></td>
											<td align="right">业务类型：</td>
											<td><select id="bizType" name="bizType" onclick="fillingBizTypeCode();" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<s:iterator value="listPurchaseOrderBizType()" var="entity">
														<option value="${entity.code}">${entity.name}</option>
													</s:iterator>
											</select> <input type="hidden" id="bizTypeCode" name="bizTypeCode" value="${purchaseOrder.businessTypeCode }" /></td>
										</tr>
										<tr>
											<td align="right">项目：</td>
											<td><input id="project" name="project" value="${purchaseOrder.project }" type="text" size="30" onchange="fieldChanged(this);" /> &nbsp;&nbsp; <input class="btn" type="button" value="选择" onclick="chooseRadioProject();" /></td>
											<td align="right">联系人：</td>
											<td><input id="contactPerson" name="contactPerson" value="${purchaseOrder.contactPerson }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">币种：</td>
											<td><select id="currency" name="currency" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<c:forEach var="cy" items="${currencyTypeList }">
														<option value="${cy.code }">${cy.name }</option>
													</c:forEach>
											</select> <input type="hidden" id="currencyCode" name="currencyCode" value="${purchaseOrder.currencyCode }" /></td>
											<td align="right">汇率：</td>
											<td><input id="rate" name="rate" value="${purchaseOrder.rate }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">采购人：</td>
											<td><input id="purchasePerson" name="purchasePerson" value="${purchaseOrder.purchasePerson }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">采购组织：</td>
											<td><input id="purchaseOrgId" name="purchaseOrgId" value="${purchaseOrder.purchaseOrgId }" type="hidden" onchange="fieldChanged(this);" /> <input id="purchaseOrg" name="purchaseOrg" value="${purchaseOrder.purchaseOrg }" type="text" size="30" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory();" /></td>
										</tr>
										<tr>
											<td align="right">交货日期：</td>
											<td><input id="deliveryDate" name="deliveryDate" value="<s:date name="purchaseOrder.deliveryDate" format="yyyy-MM-dd"/>" readonly="readonly" type="text" onclick="showTime('deliveryDate','yyyy-MM-dd')" onchange="fieldChanged(this);" /> <img onclick="showTime('deliveryDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
												width="16" height="22" align="absmiddle"></td>
											<td align="right">过账日期：</td>
											<td><input id="postingDate" name="postingDate" value="<s:date name="purchaseOrder.postingDate" format="yyyy-MM-dd"/>" readonly="readonly" type="text" onclick="showTime('postingDate','yyyy-MM-dd')" onchange="fieldChanged(this);" /> <img onclick="showTime('postingDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">创建日期：</td>
											<td><input id="createTime" name="createTime" value="<s:date name="purchaseOrder.createTime" format="yyyy-MM-dd"/>" readonly="readonly" type="text" class="validate[required] text-input" onclick="showTime('createTime','yyyy-MM-dd')" onchange="fieldChanged(this);" /><span style="color: red;">*</span> <img
												onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right"></td>
											<td></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="memo" class="example" rows="2" style="width: 700px" onchange="fieldChanged(this);">${purchaseOrder.memo }</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>其他信息</strong>
								</dt>

								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right">预付账款：</td>
											<td><input id="prepayments" name="prepayments" value="${purchaseOrder.prepayments }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">付款周期：</td>
											<td><input id="payPeriod" name="payPeriod" value="${purchaseOrder.payPeriod }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">合同名称：</td>
											<td><input id="" name="" value="" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">会计科目：</td>
											<td><input id="account" name="account" value="${purchaseOrder.account }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">付款人编码：</td>
											<td><input id="payerCode" name="payerCode" value="${purchaseOrder.payerCode }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">付款人名称：</td>
											<td><input id="payerName" name="payerName" value="${purchaseOrder.payerName }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">合同日期：</td>
											<td><input id="contractTime" name="contractTime" value="<s:date name="purchaseOrder.contractTime" format="yyyy-MM-dd"/>" readonly="readonly" type="text" onclick="showTime('contractTime','yyyy-MM-dd')" onchange="fieldChanged(this);" /> <img onclick="showTime('contractTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
												width="16" height="22" align="absmiddle"></td>
											<td align="right">承运人：</td>
											<td><input id="carrier" name="carrier" value="${purchaseOrder.carrier }" type="text" size="30" onchange="fieldChanged(this);" /></td>
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
							<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />交货地址</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(8,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />发运计划</a></li>
							<li><a onclick="javascript:$('#a4').attr('style','');tab(8,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />发票</a></li>
							<li><a onclick="javascript:$('#a5').attr('style','');tab(8,5,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />价格条件</a></li>
							<li><a onclick="javascript:$('#a6').attr('style','');tab(8,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />附件/文本</a></li>
							<li><a onclick="javascript:$('#a7').attr('style','');tab(8,7,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />审批</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 300px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/purchase/purchaseOrderAction!getPurchaseOrderLineItemsJson.action'">
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
									url : '${vix}/purchase/purchaseOrderAction!getPurchaseOrderLineItemsCount.action?id=' + $("#id").val(),
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
									url : '${vix}/purchase/purchaseOrderAction!goAddPurchaseOrderLineItem.action',
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
												url : '${vix}/purchase/purchaseOrderAction!deletePurchaseOrderItem.action?id=' + row.id,
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
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlReceivedAddress" class="easyui-datagrid" style="height: 300px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlReceivedAddressTb',url: '${vix}/purchase/purchaseOrderAction!getReceivedAddressJson.action'">
								<thead>
									<tr>
										<th data-options="field:'code',width:200,align:'center',editor:'text'">地址代码</th>
										<th data-options="field:'country',width:200,align:'center',editor:'text'">国家</th>
										<th data-options="field:'provience',width:200,align:'center',editor:'text'">省</th>
										<th data-options="field:'city',width:200,align:'center',editor:'text'">市</th>
										<th data-options="field:'street',width:200,align:'center',editor:'text'">街道</th>
										<th data-options="field:'postCode',width:200,align:'center',editor:'text'">邮政编码</th>
									</tr>
								</thead>
							</table>
							<div id="dlReceivedAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem2(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-add',plain:true" onclick="updateItem2()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem2()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid2() {
									$('#dlReceivedAddress').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
								}
								reloadDataGrid2();
								function addItem2(id) {
									if (!id)
										id = 0;
									if (!_pad_check_entity_saved_id(saveOrUpdateOrder, addItem2))
										return;

									$.ajax({
									url : '${vix}/purchase/purchaseOrderAction!orderAddressEdit.action',
									data : 'id=' + id + '&purchaseId=' + $('#id').val(),
									cache : false,
									success : function(html) {
										asyncbox.open({
										id : 'popModalWin',
										modal : true,
										width : 1000,
										height : 250,
										title : "交货地址",
										html : html,
										callback : function(action) {
											if (action == 'close' || action == 'cancel') {
												return true;
											} else if (action == 'submit') {
												submitAddressForm();
											}
											return false;
										},
										btnsbar : [ {
										text : '提交',
										action : 'submit'
										}, {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function removeItem2() {
									var row = $('#dlReceivedAddress').datagrid('getSelected');
									if (row) {
										asyncbox.confirm('是否删除信息?', '提示信息', function(action) {
											if (action == 'ok') {
												$.ajax({
												url : '${vix}/purchase/purchaseOrderAction!deleteOrderAddress.action?id=' + row.id,
												cache : false,
												success : function(html) {
													if (html == 'success') {
														showMessage('数据已删除');
														reloadDataGrid2();
													}
												}
												});
											}
										});
									} else {
										showMessage("请选择一条数据!");
									}
								}
								function updateItem2() {
									var row = $('#dlReceivedAddress').datagrid('getSelected');
									if (row) {
										addItem2(row.id);
									} else {
										showMessage("请选择一条数据!");
									}
								}
							</script>
						</div>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlDeliveryPlan" class="easyui-datagrid" style="height: 300px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlDeliveryPlanTb',url: '${vix}/purchase/purchaseOrderAction!getDeliveryPlanJson.action'">
								<thead>
									<tr>
										<th data-options="field:'itemCode',width:200,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}编码</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}名称</th>
										<th data-options="field:'amount',width:200,align:'center',editor:'text'">订货数量</th>
										<th data-options="field:'recievePointRef',width:200,align:'center',editor:'text'">到货点</th>
										<th data-options="field:'deliver',width:200,align:'center',editor:'text'">发运人</th>
										<th data-options="field:'deliveryTime',width:200,align:'center',editor:'datebox'">发运时间</th>
									</tr>
								</thead>
							</table>
							<div id="dlDeliveryPlanTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem3(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-add',plain:true" onclick="updateItem3()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem3()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid3() {
									$('#dlDeliveryPlan').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
								}
								reloadDataGrid3();
								function addItem3(id) {
									if (!id)
										id = 0;
									if (!_pad_check_entity_saved_id(saveOrUpdateOrder, addItem3))
										return;

									$.ajax({
									url : '${vix}/purchase/purchaseOrderAction!orderDeliveryEdit.action',
									data : 'id=' + id + '&purchaseId=' + $('#id').val(),
									cache : false,
									success : function(html) {
										asyncbox.open({
										id : 'popModalWin',
										modal : true,
										width : 1000,
										height : 250,
										title : "发运计划",
										html : html,
										callback : function(action) {
											if (action == 'close' || action == 'cancel') {
												return true;
											} else if (action == 'submit') {
												submitDeliveryForm();
											}
											return false;
										},
										btnsbar : [ {
										text : '提交',
										action : 'submit'
										}, {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function removeItem3() {
									var row = $('#dlDeliveryPlan').datagrid('getSelected');
									if (row) {
										asyncbox.confirm('是否删除信息?', '提示信息', function(action) {
											if (action == 'ok') {
												$.ajax({
												url : '${vix}/purchase/purchaseOrderAction!deleteOrderDelivery.action?id=' + row.id,
												cache : false,
												success : function(html) {
													if (html == 'success') {
														showMessage('数据已删除');
														reloadDataGrid3();
													}
												}
												});
											}
										});
									} else {
										showMessage("请选择一条数据!");
									}
								}
								function updateItem3() {
									var row = $('#dlDeliveryPlan').datagrid('getSelected');
									if (row) {
										addItem3(row.id);
									} else {
										showMessage("请选择一条数据!");
									}
								}
							</script>
						</div>
					</div>
					<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlInvoice" class="easyui-datagrid" style="height: 300px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlInvoiceTb',url: '${vix}/purchase/purchaseOrderAction!getPurchaseInvoiceJson.action'">
								<thead>
									<tr>
										<th data-options="field:'name',width:200,align:'center',editor:'text'">名称</th>
										<th data-options="field:'orderType',width:200,align:'center',editor:'text'">发票类型</th>
										<th data-options="field:'currency',width:200,align:'center',editor:'text'">币种</th>
										<th data-options="field:'totalAmount',width:200,align:'center',editor:'text'">金额(未税)</th>
										<th data-options="field:'taxRate',width:200,align:'center',editor:'text'">税率</th>
										<th data-options="field:'supplierName',width:200,align:'center',editor:'text'">供应商</th>
										<th data-options="field:'purchasePerson',width:200,align:'center',editor:'text'">采购人</th>
										<th data-options="field:'deliveryDate',width:200,align:'center',editor:'datebox'">交货日期</th>
									</tr>
								</thead>
							</table>
							<div id="dlInvoiceTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem4(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-add',plain:true" onclick="updateItem4()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem4()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid4() {
									$('#dlInvoice').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
								}
								reloadDataGrid4();
								function addItem4(id) {
									if (!id)
										id = 0;
									if (!_pad_check_entity_saved_id(saveOrUpdateOrder, addItem4))
										return;

									$.ajax({
									url : '${vix}/purchase/purchaseOrderAction!orderInvoiceEdit.action',
									data : 'id=' + id + '&purchaseId=' + $('#id').val(),
									cache : false,
									success : function(html) {
										asyncbox.open({
										id : 'popModalWin',
										modal : true,
										width : 1000,
										height : 270,
										title : "发票",
										html : html,
										callback : function(action) {
											if (action == 'close' || action == 'cancel') {
												return true;
											} else if (action == 'submit') {
												submitInvoiceForm();
											}
											return false;
										},
										btnsbar : [ {
										text : '提交',
										action : 'submit'
										}, {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function removeItem4() {
									var row = $('#dlInvoice').datagrid('getSelected');
									if (row) {
										asyncbox.confirm('是否删除信息?', '提示信息', function(action) {
											if (action == 'ok') {
												$.ajax({
												url : '${vix}/purchase/purchaseOrderAction!deleteOrderInvoice.action?id=' + row.id,
												cache : false,
												success : function(html) {
													if (html == 'success') {
														showMessage('数据已删除');
														reloadDataGrid4();
													}
												}
												});
											}
										});
									} else {
										showMessage("请选择一条数据!");
									}
								}
								function updateItem4() {
									var row = $('#dlInvoice').datagrid('getSelected');
									if (row) {
										addItem4(row.id);
									} else {
										showMessage("请选择一条数据!");
									}
								}
							</script>
						</div>
					</div>
					<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlPriceConditions" class="easyui-datagrid" style="height: 300px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlPriceConditionsTb',url: '${vix}/purchase/purchaseOrderAction!getPriceConditionsJson.action'">
								<thead>
									<tr>
										<th data-options="field:'itemCode',width:200,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}编码</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}名称</th>
										<th data-options="field:'amount',width:200,align:'center',editor:'numberbox'">订货数量</th>
										<th data-options="field:'netTotal',width:200,align:'center',editor:'numberbox'">金额</th>
										<th data-options="field:'coin',width:100,align:'center',editor:'text'">货币</th>
										<th data-options="field:'unit',width:100,align:'center',editor:'text'">单位</th>
									</tr>
								</thead>
							</table>
							<div id="dlPriceConditionsTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem5(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-add',plain:true" onclick="updateItem5()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem5()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid5() {
									$('#dlPriceConditions').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
								}
								reloadDataGrid5();
								function addItem5(id) {
									if (!id)
										id = 0;
									if (!_pad_check_entity_saved_id(saveOrUpdateOrder, addItem5))
										return;

									$.ajax({
									url : '${vix}/purchase/purchaseOrderAction!orderConditionsEdit.action',
									data : 'id=' + id + '&purchaseId=' + $('#id').val(),
									cache : false,
									success : function(html) {
										asyncbox.open({
										id : 'popModalWin',
										modal : true,
										width : 1000,
										height : 270,
										title : "价格条件",
										html : html,
										callback : function(action) {
											if (action == 'close' || action == 'cancel') {
												return true;
											} else if (action == 'submit') {
												submitConditionsForm();
											}
											return false;
										},
										btnsbar : [ {
										text : '提交',
										action : 'submit'
										}, {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function removeItem5() {
									var row = $('#dlPriceConditions').datagrid('getSelected');
									if (row) {
										asyncbox.confirm('是否删除信息?', '提示信息', function(action) {
											if (action == 'ok') {
												$.ajax({
												url : '${vix}/purchase/purchaseOrderAction!deleteOrderConditions.action?id=' + row.id,
												cache : false,
												success : function(html) {
													if (html == 'success') {
														showMessage('数据已删除');
														reloadDataGrid5();
													}
												}
												});
											}
										});
									} else {
										showMessage("请选择一条数据!");
									}
								}
								function updateItem5() {
									var row = $('#dlPriceConditions').datagrid('getSelected');
									if (row) {
										addItem5(row.id);
									} else {
										showMessage("请选择一条数据!");
									}
								}
							</script>
						</div>
					</div>
					<div id="a6" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							$('#soAttach').datagrid({
							url : '${vix}/purchase/purchaseOrderAction!getAttachmentsJson.action',
							title : '订单附件',
							width : 900,
							height : '300',
							fitColumns : true,
							columns : [ [ {
							field : 'id',
							title : '操作',
							width : 80,
							formatter : showOpt6
							}, {
							field : 'name',
							title : '名称',
							width : 180
							}, ] ],
							toolbar : [ {
							id : 'saBtnadd',
							text : '添加',
							iconCls : 'icon-add',
							handler : addAttachFile
							} ]
							});

							function addAttachFile() {
								if (!_pad_check_entity_saved_id(saveOrUpdateOrder, addAttachFile))
									return;

								$.ajax({
								url : '${vix}/purchase/purchaseOrderAction!addPOAttachments.action?purchaseId=' + $('#id').val(),
								cache : false,
								success : function(html) {
									asyncbox.open({
									id : 'popModalWin',
									modal : true,
									width : 364,
									height : 160,
									title : "上传附件",
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											submitsAttachForm();
										}
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							function reloadDataGrid6() {
								$('#soAttach').datagrid({
									queryParams : {
										id : $('#id').val()
									}
								});
							}
							reloadDataGrid6();

							function deleteItem6(id) {
								asyncbox.confirm('确定要删除?', '提示信息', function(action) {
									if (action == 'ok') {
										$.ajax({
										url : "${vix}/purchase/purchaseOrderAction!deleteAttachments.action",
										data : "id=" + id,
										dataType : "text",
										success : function(data) {
											if (data == 'success') {
												showMessage('操作完毕');
												reloadDataGrid6()
											} else {
												showErrorMessage('操作失败');
											}
										}
										});
									}
								});
							}
							function downloadItem6(id) {
								window.open('${vix}/purchase/purchaseOrderAction!downloadOrderAttachment.action?id=' + id)
							}
							function showOpt6(value, row, index) {
								var edit = "<a href='javascript:downloadItem6(" + row.id + ")'>下载</a>&nbsp;";
								var del = "<a href='javascript:deleteItem6(" + row.id + ")'>删除</a>&nbsp;";
								return edit + del;
							}
						</script>
						<div style="padding: 8px;">
							<table id="soAttach" style="height: 300px; margin: 6px;"></table>
						</div>
					</div>
					<div id="a7" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlApprovalOpinion" class="easyui-datagrid" style="height: 300px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlApprovalOpinionTb',url: '${vix}/purchase/purchaseOrderAction!getApprovalOpinionJson.action'">
								<thead>
									<tr>
										<th data-options="field:'checkPerson',width:200,align:'center',editor:'text'">审批人</th>
										<th data-options="field:'content',width:500,align:'center',editor:'text'">审批意见</th>
										<th data-options="field:'createTime',width:200,align:'center',editor:'datebox'">审批日期</th>
									</tr>
								</thead>
							</table>
							<div id="dlApprovalOpinionTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem7(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-add',plain:true" onclick="updateItem7()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem7()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid7() {
									$('#dlApprovalOpinion').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
								}
								reloadDataGrid7();
								function addItem7(id) {
									if (!id)
										id = 0;
									if (!_pad_check_entity_saved_id(saveOrUpdateOrder, addItem7))
										return;

									$.ajax({
									url : '${vix}/purchase/purchaseOrderAction!orderApprovalOpinionEdit.action',
									data : 'id=' + id + '&purchaseId=' + $('#id').val(),
									cache : false,
									success : function(html) {
										asyncbox.open({
										id : 'popModalWin',
										modal : true,
										width : 1000,
										height : 270,
										title : "审批",
										html : html,
										callback : function(action) {
											if (action == 'close' || action == 'cancel') {
												return true;
											} else if (action == 'submit') {
												submitApprovalOpinionForm();
											}
											return false;
										},
										btnsbar : [ {
										text : '提交',
										action : 'submit'
										}, {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function removeItem7() {
									var row = $('#dlApprovalOpinion').datagrid('getSelected');
									if (row) {
										asyncbox.confirm('是否删除信息?', '提示信息', function(action) {
											if (action == 'ok') {
												$.ajax({
												url : '${vix}/purchase/purchaseOrderAction!deleteOrderApprovalOpinion.action?id=' + row.id,
												cache : false,
												success : function(html) {
													if (html == 'success') {
														showMessage('数据已删除');
														reloadDataGrid7();
													}
												}
												});
											}
										});
									} else {
										showMessage("请选择一条数据!");
									}
								}
								function updateItem7() {
									var row = $('#dlApprovalOpinion').datagrid('getSelected');
									if (row) {
										addItem7(row.id);
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
<!-- content -->