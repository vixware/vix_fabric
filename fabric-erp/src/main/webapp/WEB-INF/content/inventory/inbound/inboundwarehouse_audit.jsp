<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	if ($(".ms").length > 0) {
		$(".ms").hover(function() {
			$(">ul", this).stop().slideDown(100);
		}, function() {
			$(">ul", this).stop().slideUp(100);
		});
		$(".ms ul li").hover(function() {
			$(">a", this).addClass("hover");
			$(">ul", this).stop().slideDown(100);
		}, function() {
			$(">a", this).removeClass("hover");
			$(">ul", this).stop().slideUp(100);
		});
	}
	//上一条数据
	function goBeforeStockRecords(id) {
		$.ajax({
		url : '${vix}/inventory/inboundWarehouseAction!goBeforeStockRecords.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//下一条
	function goAfterStockRecords(id) {
		$.ajax({
		url : '${vix}/inventory/inboundWarehouseAction!goAfterStockRecords.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
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
<div class="content" id="stockRecordsContent">
	<input type="hidden" id="id" name="id" value="${stockRecords.id}" /> <input type="hidden" id="taskId" name="taskId" value="${taskId}" />
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#" onclick="goAudit('${taskId}');"><img width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a></span> <strong><b><s:text name="inventory_purchase_inbound" /> </b><i></i> </strong>
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
											<th>入库单号：</th>
											<td><input id="code" name="code" value="${stockRecords.code }" type="text" size="30" class="validate[required] text-input" onchange="salesOrderFieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>主题：</th>
											<td><input id="name" name="name" value="${stockRecords.name }" type="text" size="30" class="validate[required] text-input" onchange="salesOrderFieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>入库时间：</th>
											<td><input id="createTime" name="createTime" value="${stockRecords.createTime}" onchange="salesOrderFieldChanged(this);" type="text" class="validate[required] text-input" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
												style="color: red;">*</span></td>
											<th>收货仓库：</th>
											<td><input id="warehousecode" name="warehousecode" value="${stockRecords.warehousecode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>部门：</th>
											<td><input id="departmentCode" name="departmentCode" value="${stockRecords.departmentCode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<th>源单类型：</th>
											<td><select id="bizsource" name="bizsource" class="validate[required] text-input">
													<option value="" selected="selected">请选择</option>
													<option value="1">采购订单</option>
													<option value="2">生产订单</option>
													<option value="3">其他开单</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>入库类别：</th>
											<td><input id="inventoryTypeId" name="inventoryTypeId" value="${stockRecords.inventoryType.name }" type="text" size="30" /></td>
											<th>库管员：</th>
											<td><input id="warehousePerson" name="warehousePerson" value="${stockRecords.warehousePerson }" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>验收人：</th>
											<td><input id="checkperson" name="checkperson" value="${stockRecords.checkperson }" type="text" size="30" /></td>
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
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '${vv:varView("vix_mdm_item")}明细',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#orderItemForm').validationEngine('validate')) {
												$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdateWimStockRecordLines.action', {
												'id' : $("#id").val(),
												'stockRecordLines.id' : $("#oiId").val(),
												'stockRecordLines.itemcode' : $("#itemcode").val(),
												'stockRecordLines.itemname' : $("#itemname").val(),
												'stockRecordLines.specification' : $("#specification").val(),
												'stockRecordLines.unitcost' : $("#unitcost").val(),
												'stockRecordLines.quantity' : $("#quantity").val(),
												'stockRecordLines.suppliercode' : $("#suppliercode").val(),
												'stockRecordLines.purchaseorderitemcode' : $("#purchaseorderitemcode").val(),
												'stockRecordLines.unit' : $("#unit").val(),
												'stockRecordLines.price' : $("#price").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#dlAddress2').datagrid("reload");
													$.ajax({
													url : '${vix}/inventory/inboundWarehouseAction!getStockRecordTotal.action?id=' + $("#id").val(),
													cache : false,
													success : function(json) {
														$("#totalamount").val(json);
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
							url : '${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=${stockRecords.id}',
							width : 'auto',
							height : 450,
							pagination : true,
							rownumbers : true,
							fitColumns : true,
							sortOrder : 'desc',
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
							field : 'unitcost',
							title : '单价',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'unit',
							title : '单位',
							width : 100,
							align : 'center'
							}, {
							field : 'quantity',
							title : '数量',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'price',
							title : '金额',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/inventory/inboundWarehouseAction!goAddSaleOrderItem.action?=${stockRecords.id}');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=' + row.id);
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
								<th>制单人：</th>
								<td><input id="salePerson1" name="salePerson1" value="" type="text" /></td>
								<th>操作员：</th>
								<td><input id="salePerson1" name="salePerson1" value="" type="text" /></td>
							</tr>
							<tr>
								<th>总金额：</th>
								<td><input id="totalamount" name="totalamount" value="" type="text" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
