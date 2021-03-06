<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function initdate() {
		//加载创建时间(新增)
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds());
		}
	}
	initdate();
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	/** 保存采购入库单 */
	function saveOrUpdateStockRecords() {
		var biztype = 1;
		if ($('#stockRecordsForm').validationEngine('validate')) {
			$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.personcode' : $("#personcode").val(),
			'stockRecords.purchasetypecode' : $("#purchasetypecode").val(),
			'stockRecords.bizclasscode' : $("#bizclasscode").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.creator' : $("#creator").val(),
			'stockRecords.memo' : memo.html(),
			'stockRecords.approver' : $("#approver").val(),
			'biztype' : biztype,
			'updateField' : updateField,
			'purchaseOrderid' : $("#purchaseOrderid").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/inboundWarehouseAction!goList.action');
			});
		}
	}
	//保存并新增
	function saveAndNew() {
		var biztype = 1;
		if ($('#stockRecordsForm').validationEngine('validate')) {
			$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.personcode' : $("#personcode").val(),
			'stockRecords.purchasetypecode' : $("#purchasetypecode").val(),
			'stockRecords.bizclasscode' : $("#bizclasscode").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.creator' : $("#creator").val(),
			'stockRecords.memo' : memo.html(),
			'stockRecords.approver' : $("#approver").val(),
			'biztype' : biztype,
			'updateField' : updateField,
			'purchaseOrderid' : $("#purchaseOrderid").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				saveOrUpdateInboundWarehouse(0);
			});
		}
	}
	$("#stockRecordsForm").validationEngine();
	/** 选择采购订单 */
	function choosePurchaseOrder() {
		$.ajax({
		url : '${vix}/inventory/inboundWarehouseAction!goChoosePurchaseOrder.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 900,
			height : 550,
			title : "选择订单",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != '') {
						$("#purchaseOrderid").val(returnValue);
						$.ajax({
						url : '${vix}/inventory/inboundWarehouseAction!converterPurchaseOrderToStockrecords.action?purchaseOrderid=' + returnValue + "&id=${stockRecords.id}",
						cache : false,
						success : function(result) {
							showMessage(result);
							setTimeout("", 1000);
							$('#stockrecordlines').datagrid("reload");
						}
						});
					} else {
						asyncbox.success("请选择订单!", "<s:text name='vix_message'/>");
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
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
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action?pageNo=${pageNo}');"><s:text name="inventory_inbound_business" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${stockRecords.id}" />
<input type="hidden" id="purchaseOrderid" name="purchaseOrderid" value="${purchaseOrderid}" />
<div class="content">
	<form id="stockRecordsForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateStockRecords()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#" onclick="saveAndNew()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22"
							title="保存并提交审批" src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="inventory_purchase_inbound" /> </b> </strong>
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
											<td width="15%" align="right">单据编号：</td>
											<td width="35%"><input id="code" name="code" value="${stockRecords.code}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<td width="15%" align="right">主题：</td>
											<td width="35%"><input id="name" name="name" value="${stockRecords.name}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">仓库：</td>
											<td><input type="hidden" id="warehouseId" name="warehouseId" value="${stockRecords.invWarehouse.id }" /><input type="text" name="warehouseName" id="warehouseName" value="${stockRecords.invWarehouse.name }" size="20" class="validate[required] text-input" /><span style="color: red;">*</span><input class="btn" type="button"
												value="选择" onclick="chooseWarehouse();" /></td>
											<td align="right">入库日期：</td>
											<td><input id="createTime" name="createTime" value="<s:date format="yyyy-MM-dd" name="%{#stockRecords.createTime}" />" onchange="fieldChanged(this);" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">入库类别：</td>
											<td><select id="bizclasscode" name="bizclasscode" disabled="disabled" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1" selected="selected">采购入库</option>
													<option value="2">生产入库</option>
													<option value="3">其他入库</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">取单类型：</td>
											<td><select id="purchasetypecode" name="purchasetypecode" disabled="disabled" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1" selected="selected">采购订单</option>
													<option value="2">生产订单</option>
													<option value="3">其他开单</option>
											</select><span style="color: red;">*</span><input class="btn" type="button" value="取单" onclick="choosePurchaseOrder();" /></td>
										</tr>
										<tr>
											<td align="right">业务员：</td>
											<td><input id="personcode" name="personcode" value="${stockRecords.personcode }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="memo" onchange="fieldChanged(this);">${stockRecords.memo }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var memo = KindEditor.create('textarea[name="memo"]', {
												basePath : '${vix}/plugin/KindEditor/',
												width : 700,
												height : 200,
												cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
												uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
												fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
												allowFileManager : true
												});
											</script></td>
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
							<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(4,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(id) {
								$.ajax({
								url : '${vix}/inventory/inboundWarehouseAction!goSaveOrUpdateStockRecordLines.action?id=' + id,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 1000,
									height : 650,
									title : "明细",
									html : html,
									callback : function(action) {
										if (action == 'cancel' || action == 'close') {
											$('#dlAddress2').datagrid("reload");
											$.ajax({
											url : '${vix}/inventory/inboundWarehouseAction!getStockRecordTotal.action?id=' + $("#id").val(),
											cache : false,
											success : function(json) {
												$("#totalAmount").val(json);
											}
											});
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
							$('#stockrecordlines').datagrid({
							url : '${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=${stockRecords.id}',
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'itemcode',
							title : '商品编码',
							width : 100,
							align : 'center'
							}, {
							field : 'itemname',
							title : '商品名称',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'specification',
							title : '规格型号',
							width : 100,
							align : 'center'
							}, {
							field : 'skuCode',
							title : 'sku编码',
							width : 100,
							align : 'center'
							}, {
							field : 'barCode',
							title : '条形码',
							width : 100,
							align : 'center'
							}, {
							field : 'batchcode',
							title : '批次号',
							width : 100,
							align : 'center'
							}, {
							field : 'unitcost',
							title : '单价',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'measureUnit',
							title : '单位',
							width : 100,
							align : 'center',
							formatter : function(value, rec, index) {
								var measureUnitName = '';
								if (value != null) {
									measureUnitName = value.name;
								}
								return measureUnitName;
							}
							}, {
							field : 'quantity',
							title : '数量',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'invShelf',
							title : '货位编码',
							width : 100,
							align : 'center',
							formatter : function(value, rec, index) {
								var invShelfCode = '';
								if (value != null) {
									invShelfCode = value.code;
								}
								return invShelfCode;
							}
							}, {
							field : 'suppliercode',
							title : '供应商',
							width : 100,
							align : 'center'
							}, {
							field : 'massdate',
							title : '保质期天数',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#stockrecordlines').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress(row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#stockrecordlines').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#stockrecordlines').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$.ajax({
									url : '${vix}/inventory/inboundWarehouseAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
									cache : false
									});
									$('#stockrecordlines').datagrid('deleteRow', index); //通过行号移除该行
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="stockrecordlines"></table>
						</div>
					</div>
				</div>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<th width="15%">制单人：</th>
								<td width="35%"><input id="creator" name="creator" value="${stockRecords.creator }" type="text" /></td>
								<th width="15%">审批人：</th>
								<td width="35%"><input id="approver" name="approver" value="${stockRecords.approver }" type="text" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>