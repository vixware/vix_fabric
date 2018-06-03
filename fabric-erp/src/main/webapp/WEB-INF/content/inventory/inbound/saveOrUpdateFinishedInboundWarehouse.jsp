<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	/** 保存产成品入库单 */
	function saveOrUpdate() {
		/** 业务类型 */
		var biztype = 0;
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.producebatch' : $("#producebatch").val(),
			'stockRecords.checkperson' : $("#checkperson").val(),
			'stockRecords.departmentCode' : $("#departmentCode").val(),
			'stockRecords.bizclasscode' : $("#bizclasscode").val(),
			'stockRecords.warehousePerson' : $("#warehousePerson").val(),
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
		/** 业务类型 */
		var biztype = 0;
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.producebatch' : $("#producebatch").val(),
			'stockRecords.checkperson' : $("#checkperson").val(),
			'stockRecords.departmentCode' : $("#departmentCode").val(),
			'stockRecords.bizclasscode' : $("#bizclasscode").val(),
			'stockRecords.warehousePerson' : $("#warehousePerson").val(),
			'updateField' : updateField,
			'biztype' : biztype
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				saveOrUpdateFinishedInboundWarehouse(0);
			});
		}
	}
	$("#order").validationEngine();
	function initData() {
		//设置入库类别选中
		$("#bizclasscode").val('${stockRecords.bizclasscode}');
		//加载创建时间(新增)
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	}
	initData();
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
					<span class="no_line"> <a onclick="saveOrUpdate()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#" onclick="saveAndNew()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="inventory_finishedgoods_inbound" /> </b> </strong>
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
											<th width="20%">入库单号：</th>
											<td width="30%"><input id="code" name="code" size="30" value="${stockRecords.code}" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<th width="20%">主题：</th>
											<td width="30%"><input id="name" name="name" size="30" value="${stockRecords.name}" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>入库时间：</th>
											<td><input id="createTime" name="createTime" value="${stockRecords.createTime}" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
											<th>入库类别：</th>
											<td><s:select id="inventoryTypeId" headerKey="-1" headerValue="--请选择--" list="%{inventoryTypeList}" onchange="fieldChanged(this);" listValue="name" listKey="id" value="%{stockRecords.inventoryType.id}" multiple="false" theme="simple">
												</s:select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>收货仓库：</th>
											<td><input type="hidden" id="warehouseId" name="warehouseId" value="${stockRecords.invWarehouse.id }" /><input type="text" name="warehouseName" id="warehouseName" value="${stockRecords.invWarehouse.name }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><input class="btn" type="button"
												value="选择" onclick="chooseWarehouse();" /></td>
											<th>生产单位：</th>
											<td><input id="departmentCode" name="departmentCode" size="30" value="${stockRecords.departmentCode}" type="text" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>库管员：</th>
											<td><input id="warehousePerson" name="warehousePerson" size="30" value="${stockRecords.warehousePerson }" type="text" onchange="fieldChanged(this);" /></td>
											<th>检验员：</th>
											<td><input id="checkperson" name="checkperson" size="30" value="${stockRecords.checkperson}" type="text" onchange="fieldChanged(this);" /></td>
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
							$('#dlAddress2').datagrid({
							url : '${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=${stockRecords.id}',
							width : 'auto',
							height : 450,
							pagination : true,
							rownumbers : true,
							sortOrder : 'desc',
							striped : true,
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'itemname',
							title : '${vv:varView("vix_mdm_item")}名称',
							width : 300,
							align : 'center'
							}, {
							field : 'itemcode',
							title : '${vv:varView("vix_mdm_item")}编码',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'specification',
							title : '规格型号',
							width : 100,
							align : 'center'
							}, {
							field : 'unit',
							title : '单位',
							width : 100,
							align : 'center'
							}, {
							field : 'quantity',
							title : '数量',
							width : 100,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'unitcost',
							title : '单价',
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
							}, {
							field : 'purchaseorderitemcode',
							title : '引用单号',
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
					<dd class="clearfix">
						<div class="order_table">
							<div class="voucher newvoucher">
								<dl>
									<dd style="display: block;">
										<table style="border: none;">
											<tr>
												<th width="15%">制单人：</th>
												<td width="35%"><input id="creator" name="creator" value="${stockRecords.creator }" type="text" /></td>
												<th width="15%">总金额：</th>
												<td width="35%"><input id="totalamount" name="totalamount" value="" type="text" /> <script type="text/javascript">
													$(function() {
														$('#totalamount').priceFormat({
														prefix : '',
														centsLimit : 3
														});
													});
												</script></td>
											</tr>
										</table>
									</dd>
								</dl>
							</div>
						</div>
					</dd>
				</div>
			</dl>
		</div>
	</form>
</div>