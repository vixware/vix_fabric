<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" type="text/css" />
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
<input type="hidden" id="id" name="stockRecords.id" value="${stockRecords.id}" />
<div class="content">
	<form id="inventoryForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a><a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"
						onclick="goAudit('${taskId}');"><img width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goBeforeStockRecords('${stockRecords.id }');"><img width="22" height="22" title="上一条"
							src="${vix}/common/img/dt_before.png"> </a> <a href="#" onclick="goAfterStockRecords('${stockRecords.id }');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#" onclick="goPrintDelivery('${stockRecords.id}');"><img width="22" height="22" title="打印" src="${vix}/common/img/dt_print.png">
					</a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" alt="关联单据" src="${vix}/common/img/dt_report.png"> </a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#" onclick="goShowPurchaseOrder('${stockRecords.id}');"><img src="${vix}/common/img/icon_10.png" alt="">引用单据</a></li>
								<li><a href="#" onclick="goModifiedLeaveMark();"><img src="${vix}/common/img/icon_10.png" alt="">修改留痕</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a></span> <strong><b>红字入库单 </b><i></i> </strong>
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
											<td align="right">单据编号：</td>
											<td><input id="code" name="code" value="${stockRecords.code}" type="text" size="30" readonly="readonly"></td>
											<td align="right">日期：</td>
											<td><input id="createTime" name="createTime" value="<s:date format="yyyy-MM-dd" name="%{#stockRecords.createTime}" />" type="text" readonly="readonly" /><img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">仓库：</td>
											<td><input type="hidden" id="warehousecode" name="warehousecode" value="${stockRecords.invWarehouse.name}" readonly="readonly" /></td>
											<td align="right">业务员：</td>
											<td><input id="personcode" name="personcode" value="${stockRecords.personcode }" type="text" size="30" readonly="readonly" /></td>
										</tr>
										<tr>
											<td align="right">入库类别：</td>
											<td><select id="bizclasscode" name="bizclasscode" disabled="disabled">
													<option value="">请选择</option>
													<option value="1">采购入库</option>
													<option value="2">生产入库</option>
													<option value="3">其他入库</option>
													<option value="4" selected="selected">红字入库</option>
											</select></td>
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
							<li class="current"><a onclick="javascript:$('#a2').attr('style','');tab(4,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a2" style="position: relative; z-index: 1; background: #FFF;">
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
												'stockRecordLines.batchcode' : $("#batchcode").val(),
												'stockRecordLines.suppliercode' : $("#suppliercode").val(),
												'stockRecordLines.skuCode' : $("#skuCode").val(),
												'stockRecordLines.unit' : $("#unit").val(),
												'stockRecordLines.producedate' : $("#producedate").val(),
												'stockRecordLines.massdate' : $("#massdate").val(),
												'stockRecordLines.price' : $("#price").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#dlAddress2').datagrid("reload");
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
							width : 100,
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
							field : 'unit',
							title : '单位',
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
							}, {
							field : 'poCode',
							title : '引用单号',
							width : 100,
							align : 'center'
							} ] ]
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
