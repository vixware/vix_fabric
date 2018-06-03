<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}

	/** 保存销售出库单 */
	function saveOrUpdateOrder() {
		/** 明细 */
		var biztype = 4;
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/outBoundAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.customercode' : $("#customercode").val(),
			'stockRecords.bizsource' : $("#bizsource").val(),
			'stockRecords.saletypecode' : $("#saletypecode").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.shipaddress' : $("#shipaddress").val(),
			'stockRecords.department' : $("#department").val(),
			'stockRecords.personcode' : $("#personcode").val(),
			'updateField' : updateField,
			'biztype' : biztype
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/outBoundAction!goList.action?');
			});
		}
	}
	function saveAndNew() {
		/** 明细 */
		var biztype = 4;
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/outBoundAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.customercode' : $("#customercode").val(),
			'stockRecords.bizsource' : $("#bizsource").val(),
			'stockRecords.saletypecode' : $("#saletypecode").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.shipaddress' : $("#shipaddress").val(),
			'stockRecords.department' : $("#department").val(),
			'stockRecords.personcode' : $("#personcode").val(),
			'updateField' : updateField,
			'biztype' : biztype
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				saveOrUpdateBusinessOrder(0);
			});
		}
	}
	$("#order").validationEngine();
	function chooseWarehouse() {
		$.ajax({
		url : '${vix}/inventory/warehouseAction!goChooseWarehouse.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择仓库",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#warehouseId").val(result[0]);
						$("#warehouseName").val(result[1]);
					} else {
						asyncbox.success("请选择仓库信息!", "提示信息");
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
				<li><a href="#"><img src="${vix}/common/img/inv_outbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action?pageNo=${pageNo}');"> <s:text name="inventory_outbound_business" />
				</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${stockRecords.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#" onclick="saveAndNew()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22"
							title="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>网店订单 </b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>单据编号：</th>
											<td><input id="code" name="code" value="${stockRecords.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>出库时间：</th>
											<td><input id="createTime" name="createTime" value="${stockRecords.createTime}" onchange="fieldChanged(this);" type="text" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>客户：</th>
											<td><input id="customercode" name="customercode" value="${stockRecords.customercode }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>源单类型：</th>
											<td><select id="bizsource" name="bizsource" disabled="disabled" onchange="fieldChanged(this);">
													<option value="0">请选择</option>
													<option value="1" selected="selected">网店订单</option>
													<option value="2">生产开单</option>
													<option value="3">其他开单</option>
											</select></td>
										</tr>
										<tr>
											<th>开单类型：</th>
											<td><select id="saletypecode" name="saletypecode" disabled="disabled" onchange="fieldChanged(this);">
													<option value="0">请选择</option>
													<option value="1">入库单</option>
													<option value="2" selected="selected">出库单</option>
											</select></td>
											<th>发货仓库：</th>
											<td><input type="hidden" id="warehouseId" name="warehouseId" value="${stockRecords.invWarehouse.id }" onchange="fieldChanged(this);" /><input type="text" name="warehouseName" id="warehouseName" value="${stockRecords.invWarehouse.name }" size="20" /><input class="btn" type="button" value="选择" onclick="chooseWarehouse();" /></td>
										</tr>
										<tr>
											<th>发货地址：</th>
											<td><input id="shipaddress" name="shipaddress" value="${stockRecords.shipaddress }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>业务员：</th>
											<td><input id="personcode" name="personcode" value="${stockRecords.personcode }" type="text" size="30" onchange="fieldChanged(this);" /></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '商品明细',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#orderItemForm').validationEngine('validate')) {
												$.post('${vix}/inventory/outBoundAction!saveOrUpdateWimStockRecordLines.action', {
												'id' : $("#id").val(),
												'wimStockrecordlines.id' : $("#oiId").val(),
												'wimStockrecordlines.itemcode' : $("#itemcode").val(),
												'wimStockrecordlines.itemname' : $("#itemname").val(),
												'wimStockrecordlines.specification' : $("#specification").val(),
												'wimStockrecordlines.unitcost' : $("#unitcost").val(),
												'wimStockrecordlines.quantity' : $("#quantity").val(),
												'wimStockrecordlines.suppliercode' : $("#suppliercode").val(),
												'wimStockrecordlines.purchaseorderitemcode' : $("#purchaseorderitemcode").val(),
												'wimStockrecordlines.unit' : $("#unit").val(),
												'wimStockrecordlines.producedate' : $("#producedate").val(),
												'wimStockrecordlines.massdate' : $("#massdate").val(),
												'wimStockrecordlines.price' : $("#price").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#dlAddress2').datagrid("reload");
													$.ajax({
													url : '${vix}/inventory/outBoundAction!getOrderItemTotal.action?id=' + $("#id").val(),
													cache : false,
													success : function(json) {
														$("#price").val(json);
													}
													});
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
							$('#dlAddress2').datagrid({
							url : '${vix}/inventory/outBoundAction!getOrderItemJson.action?id=${stockRecords.id}',
							width : 'auto',
							height : 450,
							pagination : true,
							rownumbers : true,
							fitColumns : true,
							sortOrder : 'desc',
							striped : true,
							frozenColumns : [ [ {
							field : 'itemcode',
							title : '${vv:varView("vix_mdm_item")}编码',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'itemname',
							title : '${vv:varView("vix_mdm_item")}名称',
							width : 150,
							align : 'center'
							}, {
							field : 'specification',
							title : '规格型号',
							width : 150,
							align : 'center'
							}, {
							field : 'quantity',
							title : '数量',
							width : 60,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'unitcost',
							title : '单价',
							width : 60,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'price',
							title : '金额',
							width : 60,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'code',
							title : '网店订单明细编码',
							width : 100,
							align : 'center'
							}, {
							field : 'poCode',
							title : '网店订单编码',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/inventory/outBoundAction!goAddSaleOrderItem.action?=${stockRecords.id}');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/inventory/outBoundAction!getOrderItemJson.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#dlAddress2').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#dlAddress2').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#dlAddress2').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/inventory/outBoundAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>
					<dd class="clearfix">
						<div class="order_table">
							<table>
								<tr>
									<th width="12%">部门：</th>
									<td width="14%"><input id="department" name="department" value="${stockRecords.department }" type="text" /></td>
									<th width="12%">制单人：</th>
									<td width="14%"><input id="creator" name="creator" value="${stockRecords.creator }" type="text" /></td>
									<th width="12%">审批人：</th>
									<td width="14%"><input id="approver" name="approver" value="${stockRecords.approver }" type="text" /></td>
								</tr>
							</table>
						</div>
					</dd>
			</dl>
		</div>
	</form>
</div>