<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	});
	function saveOrUpdateStockRecords() {
		if ($('#stockRecordsForm').validationEngine('validate')) {
			$.post('${vix}/drp/salesGoodsConsignmentWarehouseAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.warehousePerson' : $("#warehousePerson").val(),
			'stockRecords.createTime' : $("#createTime").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/salesGoodsConsignmentWarehouseAction!goList.action');
			});
		}
	}
	$("#stockRecordsForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">业务功能</a></li>
				<li><a href="#">代销商品管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/salesGoodsConsignmentWarehouseAction!goList.action');">代销销售商品入库 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${stockRecords.id }" />
<div class="content">
	<form id="stockRecordsForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateStockRecords()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/drp/salesGoodsConsignmentWarehouseAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>入库记录信息</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">编码：</td>
											<td><input id="code" name="code" value="${stockRecords.code }" type="text" size="30" /></td>
											<td align="right">主题：</td>
											<td><input id="name" name="name" value="${stockRecords.name }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">库管员：</td>
											<td><input id="warehousePerson" name="warehousePerson" value="${stockRecords.warehousePerson }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">入库时间：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${stockRecords.createTime }' type='both' pattern='yyyy-MM-dd' />" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
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
							function saveStockRecordLines(url) {
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
											if ($('#stockRecordLinesForm').validationEngine('validate')) {
												$.post('${vix}/drp/salesGoodsConsignmentWarehouseAction!saveOrUpdateStockRecordLines.action', {
												'id' : $("#id").val(),
												'stockRecordLines.id' : $("#stockRecordLinesId").val(),
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
													$('#stockRecordLines').datagrid("reload");
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
							$('#stockRecordLines')
									.datagrid({
									url : '${vix}/drp/salesGoodsConsignmentWarehouseAction!getStockRecordLinesJson.action?id=${stockRecords.id}',
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
										saveStockRecordLines('${vix}/drp/salesGoodsConsignmentWarehouseAction!goSaveOrUpdateStockRecordLines.action');
									}
									}, '-', {
									id : 'btnedit',
									text : '修改',
									iconCls : 'icon-edit',
									handler : function() {
										var row = $('#stockRecordLines').datagrid("getSelected"); //获取你选择的所有行
										if (row) {
											saveStockRecordLines('${vix}/drp/salesGoodsConsignmentWarehouseAction!goSaveOrUpdateStockRecordLines.action?id=' + row.id);
										}
									}
									}, '-', {
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										var rows = $('#stockRecordLines').datagrid("getSelections"); //获取你选择的所有行	
										//循环所选的行
										for ( var i = 0; i < rows.length; i++) {
											var index = $('#stockRecordLines').datagrid('getRowIndex', rows[i]);//获取某行的行号
											$('#stockRecordLines').datagrid('deleteRow', index); //通过行号移除该行
											$
													.ajax({
													url : '${vix}/drp/salesGoodsConsignmentWarehouseAction!deleteStockRecordLinesById.action?id=' + rows[i].id,
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
							<table id="stockRecordLines"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>