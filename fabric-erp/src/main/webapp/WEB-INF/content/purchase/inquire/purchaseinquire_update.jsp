<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	$(function() {
		//设置业务类型选中
		$("#bizType").val('${purchaseInquire.bizType}');
		//设置单据类型选中
		$("#formType").val('${purchaseInquire.formType}');
		//设置币种选中
		$("#currency").val('${purchaseInquire.currency}');

		$("#purchaseInquireForm").validationEngine();

		//_pad_addInputCheckNumEvent('total');
	});

	/** 保存采购询价单 */
	function saveOrUpdateOrder(tag) {
		var orderItemStr = "";
		/** 明细 */
		if ($('#purchaseInquireForm').validationEngine('validate')) {
			$.post('${vix}/purchase/purchaseInquireAction!saveOrUpdate.action', {
			'purchaseInquire.id' : $("#id").val(),
			'purchaseInquire.code' : $("#code").val(),
			'purchaseInquire.name' : $("#inquireName").val(),
			'purchaseInquire.project' : $("#project").val(),
			'purchaseInquire.bizType' : $("#bizType").val(),
			'purchaseInquire.formType' : $("#formType").val(),
			'purchaseInquire.purchasePerson' : $("#purchasePerson").val(),
			'purchaseInquire.appDate' : $("#appDate").val(),
			'purchaseInquire.checkPerson' : $("#checkPerson").val(),
			'purchaseInquire.checkTime' : $("#checkTime").val(),
			'purchaseInquire.currency' : $("#currency").val(),
			'purchaseInquire.rate' : $("#rate").val(),
			'purchaseInquire.supplierCode' : $("#supplierCode").val(),
			'purchaseInquire.supplierName' : $("#supplierName").val(),
			'purchaseInquire.supplierId' : $("#supplierId").val(),
			'updateField' : updateField,
			'purchaseInquire.groupCode' : $("#groupCode").val()
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

	function showPrice(itemId, count, requireTime) {
		$
				.ajax({
				url : '${vix}/mdm/item/purchaseItemPriceAction!goFixedPrice.action?priceConditionType=purchase&id=' + itemId + "&count=" + count + "&billCreateDate=" + requireTime,
				cache : false,
				success : function(html) {
					asyncbox.open({
					modal : true,
					width : 960,
					height : 580,
					title : "定价",
					html : html,
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
			$(".ab_outer .list td input[type='checkbox']").css("margin-left", 10);
			$(".ab_c .content").css("margin-bottom", "0");
			$('.ab_c .content').parent().css('position', 'relative');
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
							var rt = result.split(",");
							setTimeout("", 1000);
							$("#supplierCode").val(rt[1]);
							$("#supplierName").val(rt[2]);
						});
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
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module"></div>
	</h2>
</div>

<div class="content">
	<form id="purchaseInquireForm">
		<input type="hidden" id="id" name="id" value="${purchaseInquire.id }" /> <input type="hidden" id="groupCode" name="groupCode" value="${purchaseInquire.groupCode }" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder(1)" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#" onclick="saveOrUpdateOrder(2)"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <s:if test="isAllowAudit == 1">
							<s:if test="stockRecords.status != 1">
								<a onclick="" href="#"><img width="22" height="22" alt="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a>
							</s:if>
						</s:if> <a class="f_btn edit_back" href="javascript:_pad_page_view_back();"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增采购询价</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> </span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">询价单编码：</td>
											<td><input id="code" name="code" type="text" size="30" value="${purchaseInquire.code }" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">询价单主题：</td>
											<td><input id="inquireName" name="inquireName" type="text" size="30" value="${purchaseInquire.name }" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">项目：</td>
											<td><input id="project" name="project" value="${purchaseInquire.project }" type="text" size="30" onchange="fieldChanged(this);" /> &nbsp;&nbsp;&nbsp; <input class="btn" type="button" value="选择" onclick="chooseRadioProject();" /></td>
											<td align="right">业务类型：</td>
											<td><select id="bizType" name="bizType" style="width: 50" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<s:iterator value="listPurchaseInquireBizType()" var="entity">
														<option value="${entity.code}">${entity.name}</option>
													</s:iterator>
											</select></td>
										</tr>
										<tr>
											<td align="right">单据类型：</td>
											<td><select id="formType" name="formType" style="width: 50" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="PUR_INQUERY">采购询价</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${purchaseOrder.supplierName }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span> <input type="hidden" id="supplierCode" name="supplierCode" value="${purchaseInquire.supplierCode }" /> <input
												type="hidden" id="supplierId" name="supplierId" value="${purchaseInquire.supplierId }" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /> <input class="btn" type="button" value="快速新建" onclick="createSupplier();" /></td>
										</tr>
										<tr>
											<td align="right">币种：</td>
											<td><select id="currency" name="currency" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<c:forEach var="cy" items="${currencyTypeList }">
														<option value="${cy.code }">${cy.name }</option>
													</c:forEach>
											</select><span style="color: red;">*</span></td>
											<td align="right">税率：</td>
											<td><input id="rate" name="rate" value="${purchaseInquire.rate }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">询价人：</td>
											<td><input id="purchasePerson" name="purchasePerson" value="${purchaseInquire.purchasePerson }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">询价日期：</td>
											<td><input id="appDate" name="appDate" value="<s:date name="purchaseInquire.appDate" format="yyyy-MM-dd"/>" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /> <img onclick="showTime('appDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
												style="color: red;">*</span></td>
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
							<table id="item" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#itemTb',url: '${vix}/purchase/purchaseInquireAction!getPurchaseInquiryDetailJson.action'">
								<thead>
									<tr>
										<th data-options="field:'itemCode',width:80,align:'center'">编码</th>
										<th data-options="field:'itemName',width:120,align:'center'">名称</th>
										<th data-options="field:'skuCode',align:'center',width:120,editor:'text'">SKU码</th>
										<th data-options="field:'barCode',align:'center',width:120,editor:'text'">BAR码</th>
										<th data-options="field:'specification',width:100,align:'center'">规格型号</th>
										<th data-options="field:'unit',width:80,align:'center'">主计量单位</th>
										<th data-options="field:'amount',width:60,align:'center'">数量</th>
										<th data-options="field:'price',width:60,align:'center'">无税单价</th>
										<th data-options="field:'taxRate',width:60,align:'center'">税率</th>
										<th data-options="field:'total',width:60,align:'center'">含税总价</th>
										<th data-options="field:'requireTime',width:80,align:'center'">需求日期</th>
										<th data-options="field:'supplier',width:120,align:'center'">建议供应商</th>
									</tr>
								</thead>
							</table>
							<div id="itemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-add',plain:true" onclick="updateItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-edit',plain:true" onclick="showItemPrice()"><span
									class="l-btn-left"><img alt="" src="${vix}/common/img/system/itemPrice.png"><span style="padding: 0;" class="l-btn-text l-btn-icon-left">定价</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span class="l-btn-left"><span
										class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid1() {
									$('#item').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
									//修改下方数量
									$.ajax({
									url : '${vix}/purchase/purchaseInquireAction!getPurchaseInquireDetailsCount.action?id=' + $("#id").val(),
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
								function addItem(id) {
									if (!_pad_check_entity_saved_id(saveOrUpdateOrder, addItem))
										return;

									$.ajax({
									url : '${vix}/purchase/purchaseInquireAction!goSaveOrUpdatePurchaseInquireItem.action?id=' + id,
									cache : false,
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
								function removeItem() {
									var row = $('#item').datagrid('getSelected');
									if (row) {
										asyncbox.confirm('是否删除该订单明细?', '提示信息', function(action) {
											if (action == 'ok') {
												var index = $('#item').datagrid('getRowIndex', row);
												$('#item').datagrid('deleteRow', index);
												$.ajax({
												url : '${vix}/purchase/purchaseInquireAction!deletePurchaseInquireItem.action?id=' + row.id,
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

								function showItemPrice() {
									var row = $('#item').datagrid('getSelected');
									if (row) {
										var itemId = row.itemId;
										var count = row.amount;
										var requireTime = row.requireTime;
										showPrice(itemId, count, requireTime);
									} else {
										showMessage("请选择一条数据!");
									}
								}
								function updateItem() {
									var row = $('#item').datagrid('getSelected');
									if (row) {
										addItem(row.id);
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
							<tr>
								<td align="right">审批人：</td>
								<td><input id="checkPerson" name="checkPerson" value="${purchaseInquire.checkPerson }" type="text" size="30" onchange="fieldChanged(this);" /></td>
								<td align="right">审批日期：</td>
								<td><input id="checkTime" name="checkTime" value="<s:date name="purchaseInquire.checkTime" format="yyyy-MM-dd"/>" type="text" onchange="fieldChanged(this);" /> <img onclick="showTime('checkTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>