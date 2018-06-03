<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	/** 保存采购入库单 */
	function saveOrUpdateOrder() {
		var biztype = 3;
		if ($('#inventoryForm').validationEngine('validate')) {
			$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.personcode' : $("#personcode").val(),
			'stockRecords.purchasetypecode' : $("#purchasetypecode").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.bizclasscode' : $("#bizclasscode").val(),
			'stockRecords.warehousecode' : $("#warehousecode").val(),
			'stockRecords.warehouseName' : $("#warehousename").val(),
			'stockRecords.department' : $("#department").val(),
			'stockRecords.creator' : $("#creator").val(),
			'stockRecords.approver' : $("#approver").val(),
			'updateField' : updateField,
			'biztype' : biztype
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/inboundWarehouseAction!goList.action');
			});
		}
	}
	function saveAndNew() {
		var biztype = 3;
		if ($('#inventoryForm').validationEngine('validate')) {
			$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.personcode' : $("#personcode").val(),
			'stockRecords.purchasetypecode' : $("#purchasetypecode").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.bizclasscode' : $("#bizclasscode").val(),
			'stockRecords.warehousecode' : $("#warehousecode").val(),
			'stockRecords.warehouseName' : $("#warehousename").val(),
			'stockRecords.department' : $("#department").val(),
			'stockRecords.creator' : $("#creator").val(),
			'updateField' : updateField,
			'stockRecords.approver' : $("#approver").val(),
			'biztype' : biztype
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				saveOrUpdateScarletLetterInbound(0);
			});
		}
	}
	$("#inventoryForm").validationEngine();
	/** 选择采购订单 */
	function choosePurchaseOrder() {
		$
				.ajax({
				url : '${vix}/inventory/inboundWarehouseAction!goChoosePurchaseOrder.action',
				cache : false,
				success : function(html) {
					asyncbox
							.open({
							modal : true,
							width : 900,
							height : 550,
							title : "选择订单",
							html : html,
							callback : function(action, returnValue) {
								if (action == 'ok') {
									if (returnValue != '') {
										$
												.ajax({
												url : '${vix}/inventory/inboundWarehouseAction!converterPurchaseOrderToStockrecords.action?purchaseOrderid=' + returnValue + "&id=${stockRecords.id}",
												cache : false,
												success : function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#dlAddress2').datagrid("reload");
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
	function initDate() {
		//加载创建时间(新增)
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	}
	initDate();
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
<div class="content">
	<form id="inventoryForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#" onclick="saveAndNew()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22"
							title="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>红字入库单</b></strong>
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
											<td align="right" width="15%">单据编号：</td>
											<td width="35%"><input id="code" name="code" value="${stockRecords.code}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"><span style="color: red;">*</span></td>
											<td align="right" width="15%">主题：</td>
											<td width="35%"><input id="name" name="name" value="${stockRecords.name}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">仓库：</td>
											<td><input type="hidden" id="warehouseId" name="warehouseId" value="${stockRecords.invWarehouse.id }" /><input type="text" name="warehouseName" id="warehouseName" value="${stockRecords.invWarehouse.name }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><input class="btn" type="button"
												value="选择" onclick="chooseWarehouse();" /></td>
											<td align="right">日期：</td>
											<td><input id="createTime" name="createTime" value="<s:date format="yyyy-MM-dd" name="%{#stockRecords.createTime}" />" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">入库类别：</td>
											<td><select id="bizclasscode" name="bizclasscode" disabled="disabled" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1">采购入库</option>
													<option value="2">生产入库</option>
													<option value="3">其他入库</option>
													<option value="4" selected="selected">红字入库</option>
											</select></td>
											<td align="right">取单类型：</td>
											<td><select id="purchasetypecode" name="purchasetypecode" disabled="disabled" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1" selected="selected">采购订单</option>
													<option value="2">生产订单</option>
													<option value="3">其他开单</option>
											</select><input class="btn" type="button" value="取单" onclick="choosePurchaseOrder();" /></td>
										</tr>
										<tr>
											<td align="right">业务员：</td>
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
							<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(4,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(id) {
								$.ajax({
								url : '${vix}/inventory/inboundWarehouseAction!goAddSaleOrderItem.action?id=' + id,
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
							title : '${vv:varView("vix_mdm_item")}编码',
							width : 100,
							align : 'center'
							}, {
							field : 'itemname',
							title : '${vv:varView("vix_mdm_item")}名称',
							width : 300,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'specification',
							title : '规格型号',
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
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'quantity',
							title : '数量',
							width : 100,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'price',
							title : '金额',
							width : 100,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'suppliercode',
							title : '供应商',
							width : 100,
							align : 'center'
							}, {
							field : 'producedate',
							title : '生产日期',
							width : 100,
							editor : 'datebox',
							align : 'center',
							formatter : function(val, rec) {
								if (val != null && val != "") {
									var d = new Date(val);
									return format(d);
								} else
									return "";
							}
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
							function format(date) {
								var y = date.getFullYear();
								var m = date.getMonth() + 1;
								var d = date.getDate();
								return y + '-' + m + '-' + d;
							}
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>
				</div>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td align="right" width="15%">制单人：</td>
								<td width="35%"><input id="creator" name="creator" value="${stockRecords.creator }" type="text" /></td>
								<th align="right" width="15%">审批人：</th>
								<td width="35%"><input id="approver" name="approver" value="${stockRecords.approver }" type="text" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
