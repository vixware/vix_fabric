<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function saveOrUpdate(tag) {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.sppliercode' : $("#sppliercode").val(),
			'stockRecords.inventoryType.id' : $("#inventoryTypeId").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.warehousePerson' : $("#warehousePerson").val(),
			'stockRecords.checkperson' : $("#checkperson").val(),
			'stockRecords.totalAmount' : $("#totalAmount").val(),
			'biztype' : 5
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				if (tag == 1) {
					loadContent('${vix}/inventory/inboundWarehouseAction!goList.action');
				} else {
					saveOrUpdateOtherInboundWarehouse(0);
				}
			});
		} else {
			return false;
		}
	}

	function approvalStockRecords(tag) {
		var dlData = $("#stockRecordLines").datagrid("getRows");
		var dlJson = JSON.stringify(dlData);
		var biztype = 2;
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.sppliercode' : $("#sppliercode").val(),
			'stockRecords.inventoryType.id' : $("#inventoryTypeId").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.warehousePerson' : $("#warehousePerson").val(),
			'stockRecords.checkperson' : $("#checkperson").val(),
			'stockRecords.totalAmount' : $("#totalAmount").val(),
			'updateField' : updateField,
			'biztype' : biztype,
			'dlJson' : dlJson,
			'tag' : tag
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/inboundWarehouseAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#order").validationEngine();
	var updateField = "";
	function salesOrderFieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function initdata() {
		//加载创建时间(新增)
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds());
		}

		$("#selectOptionId2").hide();
		$("#selectOptionId1").show();
	}
	initdata();
	function textareaChanged() {
		saveOrUpdateStockRecordLines();
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
				<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action');"><s:text name="inventory_inbound_business" /> </a></li>
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
					<span class="no_line"> <a onclick="saveOrUpdate(1)" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#" onclick="saveOrUpdate(2)"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <s:if test="isAllowAudit == 1">
							<s:if test="stockRecords.status != 1">
								<a onclick="approvalStockRecords(1);" href="###"><img width="22" height="22" alt="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a>
							</s:if>
						</s:if> <a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a></span> <strong><b>要货入库 </b> </strong>
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
											<th width="15%">单号：</th>
											<td width="35%"><input id="code" name="code" value="${stockRecords.code }" type="text" size="30" class="validate[required] text-input" onchange="salesOrderFieldChanged(this);"> <span style="color: red;">*</span></td>
											<th width="15%">主题：</th>
											<td width="35%"><input id="name" name="name" value="${stockRecords.name }" type="text" size="30" class="validate[required] text-input" onchange="salesOrderFieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>入库时间：</th>
											<td><input id="createTime" name="createTime" value="<s:date name="%{stockRecords.createTime}" format="yyyy-MM-dd HH:mm:ss"/>" onchange="salesOrderFieldChanged(this);" type="text" class="validate[required] text-input" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"><span style="color: red;">*</span></td>
											<th>验收人：</th>
											<td><input id="checkperson" name="checkperson" value="${stockRecords.checkperson }" type="text" size="30" onchange="salesOrderFieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>收货仓库：</th>
											<td><input type="hidden" id="warehouseId" name="warehouseId" value="${stockRecords.invWarehouse.id }" /><input type="text" name="warehouseName" id="warehouseName" value="${stockRecords.invWarehouse.name }" class="validate[required] text-input" size="30" /><span style="color: red;">*</span><input class="btn" type="button"
												value="选择" onclick="chooseWarehouse();" /></td>
											<th>库管员：</th>
											<td><input id="warehousePerson" name="warehousePerson" value="${stockRecords.warehousePerson }" type="text" size="30" onchange="salesOrderFieldChanged(this);" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;" id="selectOptionId1">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(id) {
								$.ajax({
								url : '${vix}/inventory/inboundWarehouseAction!goListStoreEnquiryRequest.action?id=' + id,
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
							$('#dlAddress2').datagrid({
							url : '${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=${stockRecords.id}',
							width : 'auto',
							height : 450,
							pagination : true,
							rownumbers : true,
							sortOrder : 'desc',
							//fitColumns : true,
							striped : true,
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
							field : 'positions',
							title : '货位编码',
							width : 100,
							align : 'right',
							required : 'true'
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
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress(0);
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress(row.id);
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
									url : '${vix}/inventory/inboundWarehouseAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
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
				</div>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th width="15%">制单人：</th>
											<td width="35%"><input id="creator" name="creator" value="${stockRecords.creator }" type="text" /></td>
											<th width="15%">审批人：</th>
											<td width="35%"><input id="approver" name="approver" value="${stockRecords.approver }" type="text" /></td>
										</tr>
										<tr>
											<th>总计：</th>
											<td><input id="totalAmount" name="totalAmount" value="${stockRecords.totalAmount }" type="text" /></td>
											<th></th>
											<th></th>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>