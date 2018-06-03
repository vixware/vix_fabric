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
		url : '${vix}/inventory/outBoundAction!goBeforeStockRecords.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//下一条
	function goAfterStockRecords(id) {
		$.ajax({
		url : '${vix}/inventory/outBoundAction!goAfterStockRecords.action?id=' + id,
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
				<li><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action');">出库管理 </a></li>
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
					<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a><a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"
						onclick="goAudit('${taskId}');"><img width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goBeforeStockRecords('${stockRecords.id }');"><img width="22" height="22" title="上一条"
							src="${vix}/common/img/dt_before.png"> </a> <a href="#" onclick="goAfterStockRecords('${stockRecords.id }');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#" onclick="goPrintStockRecords('${stockRecords.id}');"><img width="22" height="22" title="打印"
							src="${vix}/common/img/dt_print.png"> </a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" alt="关联单据" src="${vix}/common/img/dt_report.png"> </a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#" onclick="goShowPurchaseOrder('${stockRecords.id}');"><img src="${vix}/common/img/icon_10.png" alt="">引用单据</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a></span> <strong><b><s:text
								name="inventory_purchase_inbound" /> </b><i></i> </strong>
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
											<td><input id="code" name="code" value="${stockRecords.code }" type="text" size="30" class="validate[required] text-input" onchange="salesOrderFieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>出库时间：</th>
											<td><input id="createTime" name="createTime" value="${stockRecords.createTime}" onchange="salesOrderFieldChanged(this);" type="text" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>客户：</th>
											<td><input id="customercode" name="customercode" value="${stockRecords.customercode }" type="text" size="30" /></td>
											<th>源单类型：</th>
											<td><select id="bizsource" name="bizsource" disabled="disabled">
													<option value="0">请选择</option>
													<option value="1" selected="selected">销售开单</option>
													<option value="2">生产开单</option>
													<option value="3">其他开单</option>
											</select><input class="btn" type="button" value="取单" onclick="choosePurchaseOrder();" /></td>
										</tr>
										<tr>
											<th>开单类型：</th>
											<td><select id="saletypecode" name="saletypecode" disabled="disabled">
													<option value="0">请选择</option>
													<option value="1">入库单</option>
													<option value="2" selected="selected">出库单</option>
											</select></td>
											<th>发货仓库：</th>
											<td><input type="hidden" id="warehouseId" name="warehouseId" value="${stockRecords.invWarehouse.id }" /><input type="text" name="warehouseName" id="warehouseName" value="${stockRecords.invWarehouse.name }" size="20" class="validate[required] text-input" /><span style="color: red;">*</span><input class="btn" type="button"
												value="选择" onclick="chooseWarehouse();" /></td>
										</tr>
										<tr>
											<th>发货地址：</th>
											<td><input id="shipaddress" name="shipaddress" value="${stockRecords.shipaddress }" type="text" size="30" /></td>
											<th>业务员：</th>
											<td><input id="personcode" name="personcode" value="${stockRecords.personcode }" type="text" size="30" /></td>
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
									title : '${vv:varView("vix_mdm_item")}明细',
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
							sortOrder : 'desc',
							striped : true,
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							align : 'center'
							}, {
							field : 'itemcode',
							title : '${vv:varView("vix_mdm_item")}编码',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'itemname',
							title : '${vv:varView("vix_mdm_item")}名称',
							width : 100,
							align : 'center'
							}, {
							field : 'unit',
							title : '单位',
							width : 100,
							align : 'center'
							}, {
							field : 'specification',
							title : '规格型号',
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
							field : 'unitcost',
							title : '单价',
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
							}, {
							field : 'suppliercode',
							title : '供应商编码',
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
							}, {
							field : 'poCode',
							title : '引用单号',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/inventory/outBoundAction!goAddSaleOrderItem.action?id=' + row.id);
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
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table>
										<tr>
											<th>制单人：</th>
											<td><input id="creator" name="creator" value="${stockRecords.creator }" type="text" /></td>
											<th>审批人：</th>
											<td><input id="approver" name="approver" value="${stockRecords.approver }" type="text" /></td>
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
