<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		if (document.getElementById("settlementDate").value == "") {
			var myDate = new Date();
			$("#settlementDate").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	});
	function saveOrUpdateSettlementStatement() {
		if ($('#settlementStatementForm').validationEngine('validate')) {
			$.post('${vix}/chain/productViewAction!saveOrUpdate.action', {
			'settlementStatement.id' : $("#id").val(),
			'settlementStatement.code' : $("#code").val(),
			'settlementStatement.name' : $("#name").val(),
			'settlementStatement.settlement' : $("#settlement").val(),
			'settlementStatement.amount' : $("#amount").val(),
			'settlementStatement.settlementDate' : $("#settlementDate").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/chain/productViewAction!goList.action');
			});
		}
	}
	$("#settlementStatementForm").validationEngine();
	function saveDeliveryAddress() {
		$.ajax({
		url : '${vix}/chain/productViewAction!goChooseSalesOrder.action',
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
						$.ajax({
						url : '${vix}/chain/productViewAction!toSettlementStatementItem.action?salesOrderId=' + returnValue + "&id=${settlementStatement.id}",
						cache : false,
						success : function(result) {
							showMessage(result);
							setTimeout("", 1000);
							$('#settlementStatementItem').datagrid("reload");
						}
						});
					} else {
						asyncbox.success("请选择订单!", "提示信息");
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	/** 选择单选供应商 */
	function chooseSupplier() {
		$.ajax({
		url : '${vix}/inventory/inboundWarehouseAction!goChooseSupplier.action',
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
						$("#supplierName").val(rv[1]);
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="chain" /> </a></li>
				<li><a href="#"><s:text name="cbm_settlement_management" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/chain/productViewAction!goList.action');"> <s:text name="cbm_product_view" />
				</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${settlementStatement.id }" />
<div class="content">
	<form id="settlementStatementForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateSettlementStatement()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#"><img width="22" height="22" title="取消" src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#" onclick="loadContent('${vix}/chain/productViewAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>结算单信息</b> </strong>
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
											<td><input id="code" name="code" value="${settlementStatement.code }" type="text" size="30" /></td>
											<td align="right">主题：</td>
											<td><input id="name" name="name" value="${settlementStatement.name }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">供应商：</td>
											<td><input id="supplierName" name="supplierName" value="${settlementStatement.supplier.name }" type="text" size="30" class="validate[required] text-input" /><input class="btn" type="button" value="选择" onclick="chooseSupplier();" /><span style="color: red;">*</span></td>
											<td align="right">结算方式：</td>
											<td><input id="settlement" name="settlement" value="${settlementStatement.settlement }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">结算金额：</td>
											<td><input id="amount" name="amount" value="${settlementStatement.amount }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">币种：</td>
											<td><input id="currencyTypeName" name="currencyTypeName" value="${settlementStatement.currencyType.name }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">结算日期：</td>
											<td><input id="settlementDate" name="settlementDate" value="<fmt:formatDate value='${settlementStatement.settlementDate }' type='both' pattern='yyyy-MM-dd' />" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('settlementDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
							$('#settlementStatementItem').datagrid({
							url : '${vix}/chain/productViewAction!getSettlementStatementItemJson.action?id=${settlementStatement.id}',
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
							field : 'itemCode',
							title : '商品编码',
							width : 100,
							align : 'center'
							}, {
							field : 'itemName',
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
							field : 'price',
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
							field : 'amount',
							title : '数量',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'netTotal',
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
								saveDeliveryAddress();
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#settlementStatementItem').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveStockRecordLines('${vix}/chain/productViewAction!goSaveOrUpdateSettlementStatementItem.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#settlementStatementItem').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#settlementStatementItem').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#settlementStatementItem').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/chain/productViewAction!deleteSettlementStatementById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="settlementStatementItem"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>
<!-- content -->