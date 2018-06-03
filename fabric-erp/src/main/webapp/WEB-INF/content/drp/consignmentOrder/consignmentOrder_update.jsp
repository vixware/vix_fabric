<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	$(function() {
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	});
	function saveOrUpdateConsignmentOrder() {
		if ($('#consignmentOrderForm').validationEngine('validate')) {
			$.post('${vix}/drp/consignmentOrderAction!saveOrUpdate.action', {
			'consignmentOrder.id' : $("#id").val(),
			'consignmentOrder.code' : $("#code").val(),
			'consignmentOrder.title' : $("#title").val(),
			'consignmentOrder.status' : $("#status").val(),
			'consignmentOrder.createTime' : $("#createTime").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/consignmentOrderAction!goList.action');
			});
		}
	}
	$("#consignmentOrderForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">业务功能 </a></li>
				<li><a href="#">代销商品管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/consignmentOrderAction!goList.action');"> 代销订单 </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="consignmentOrderForm">
		<input type="hidden" id="id" name="id" value="${consignmentOrder.id }" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateConsignmentOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/drp/consignmentOrderAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>订单信息</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">编码：</td>
											<td><input id="code" name="code" value="${consignmentOrder.code }" type="text" size="30" /></td>
											<td align="right">主题：</td>
											<td><input id="title" name="title" value="${consignmentOrder.title }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">状态：</td>
											<td><select id="status" name="status" style="width: 50" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">日期：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${consignmentOrder.createTime }' type='both' pattern='yyyy-MM-dd' />" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
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
											if ($('#consignmentOrderItemForm').validationEngine('validate')) {
												$.post('${vix}/drp/consignmentOrderAction!saveOrUpdateConsignmentOrderItem.action', {
												'id' : $("#id").val(),
												'consignmentOrderItem.id' : $("#consignmentOrderItemId").val(),
												'consignmentOrderItem.itemCode' : $("#itemCode").val(),
												'consignmentOrderItem.itemName' : $("#itemName").val(),
												'consignmentOrderItem.specification' : $("#specification").val(),
												'consignmentOrderItem.unit' : $("#unit").val(),
												'consignmentOrderItem.price' : $("#price").val(),
												'consignmentOrderItem.amount' : $("#amount").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#consignmentOrderItem').datagrid("reload");
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
							$('#consignmentOrderItem').datagrid({
							url : '${vix}/drp/consignmentOrderAction!getConsignmentOrderItemJson.action?id=${consignmentOrder.id}',
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
								saveDeliveryAddress('${vix}/drp/consignmentOrderAction!goSaveOrUpdateConsignmentOrderItem.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#consignmentOrderItem').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/drp/consignmentOrderAction!goSaveOrUpdateConsignmentOrderItem.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#consignmentOrderItem').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#consignmentOrderItem').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#consignmentOrderItem').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/drp/consignmentOrderAction!deleteConsignmentOrderItemById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="consignmentOrderItem"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>