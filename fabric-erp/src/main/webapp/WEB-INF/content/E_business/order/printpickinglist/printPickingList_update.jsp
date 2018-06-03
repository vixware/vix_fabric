<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function saveOrUpdateSalesOrder() {
		/** 明细 */
		var soData = $("#so").datagrid("getRows");
		var sojson = JSON.stringify(soData);
		if ($('#salesOrderForm').validationEngine('validate')) {
			$.post('${vix}/business/printPickingListAction!saveOrUpdate.action', {
			'salesOrder.id' : $("#id").val(),
			'sojson' : sojson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/business/printPickingListAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#salesOrderForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理 </a></li>
				<li><a href="#">订单管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/business/printPickingListAction!goList.action');">分拣单管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${orderBatch.id}" />
<div class="content">
	<form id="salesOrderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批" src="${vix}/common/img/dt_submit.png" />
					</a> <a href="#"><img width="22" height="22" title="取消" src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#" onclick="loadContent('${vix}/business/outBoundProcessAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>分拣单信息</b> </strong>
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
											<td align="right">批次号：</td>
											<td><input id="code" name="code" value="${orderBatch.code }" type="text" size="30" /></td>
											<td align="right">操作时间：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${orderBatch.createTime }' type='both' pattern='yyyy-MM-dd' />" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">操作人：</td>
											<td><input id="creator" name="creator" value="${orderBatch.creator }" type="text" size="30" /></td>
											<td align="right">状态：</td>
											<td><input id="status" name="status" value="${orderBatch.status }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />订单</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;" class="right_content">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '订单',
									modal : true,
									width : 725,
									height : 350,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#orderForm').validationEngine('validate')) {
												$.post('${vix}/business/printPickingListAction!saveOrUpdateSaleOrderItem.action', {
													'id' : $("#id").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#saleOrderItem').datagrid("reload");
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
							$('#order').datagrid({
							url : '${vix}/business/printPickingListAction!getOrderList.action?id=${orderBatch.id}',
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
							field : 'tradeNo',
							title : '订单编码',
							width : 150,
							align : 'center'
							}, {
							field : 'sellerNick',
							title : '卖家昵称',
							width : 150,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'channel.name',
							title : '网店',
							width : 150,
							align : 'center'
							}, {
							field : 'receiverMobile',
							title : '联系电话',
							width : 150,
							align : 'right',
							required : 'true'
							}, {
							field : 'buyerMessage',
							title : '买家留言',
							width : 300,
							align : 'right',
							required : 'true'
							}, {
							field : 'status',
							title : '处理状态',
							width : 50,
							align : 'right',
							required : 'true'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/business/printPickingListAction!goSaveSaleOrderItem.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#order').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/business/printPickingListAction!goSaveSaleOrderItem.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#order').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#order').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#order').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/business/printPickingListAction!deleteSaleOrderItemById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="order"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>