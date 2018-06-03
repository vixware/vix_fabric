<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateSalesOrder() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/orderProcessingAction!saveOrUpdate.action', {
			'salesOrder.id' : $("#id").val(),
			'salesOrder.code' : $("#code").val(),
			'salesOrder.createTime' : $("#createTime").val(),
			'salesOrder.customerCode' : $("#customerCode").val(),
			'salesOrder.customerName' : $("#customerName").val(),
			'salesOrder.customerCompany' : $("#customerCompany").val(),
			'salesOrder.contact' : $("#contact").val(),
			'salesOrder.totalWeight' : $("#totalWeight").val(),
			'salesOrder.weightUnit' : $("#weightUnit").val(),
			'salesOrder.loadPoint' : $("#loadPoint").val(),
			'salesOrder.source' : $("#source").val(),
			'salesOrder.target' : $("#target").val(),
			'salesOrder.carrier' : $("#carrier").val(),
			'salesOrder.transferStyle' : $("#transferStyle").val(),
			'salesOrder.deliveryTimeInPlan' : $("#deliveryTimeInPlan").val(),
			'salesOrder.amount' : $("#amount").val(),
			'salesOrder.currency' : $("#currency").val(),
			'salesOrder.tax' : $("#tax").val(),
			'updateField' : updateField,
			'salesOrder.payCondition' : $("#payCondition").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/orderProcessingAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#order").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/receiveorders.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ldm_dtbcenter_management" /> </a></li>
				<li><a href="#"><s:text name="ldm_order_receiving" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/orderProcessingAction!goList.action');"><s:text name="ldm_order_processing" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${salesOrder.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateSalesOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/orderProcessingAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>销售订单 </b> </strong>
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
											<th>订单编码：</th>
											<td><input id="code" name="code" value="${salesOrder.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>订单日期：</th>
											<td><input id="createTime" name="createTime" value="${salesOrder.createTime}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>客户公司：</th>
											<td><input id="customerCompany" name="customerCompany" value="${salesOrder.customerCompany}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">联系人：</td>
											<td><input id="contact" name="contact" type="text" value="${salesOrder.ucContact }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">装运点：</td>
											<td><input id="loadPoint" name="loadPoint" type="text" value="${salesOrder.loadPoint }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">发运地：</td>
											<td><input id="source" name="source" type="text" value="${salesOrder.source }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">目的地：</td>
											<td><input id="target" name="target" type="text" value="${salesOrder.target }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">承运商：</td>
											<td><input id="carrier" name="carrier" type="text" value="${salesOrder.carrier }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">运输方式：</td>
											<td><input id="transferStyle" name="transferStyle" type="text" value="${salesOrder.transferStyle }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">计划发运日期：</td>
											<td><input id="deliveryTimeInPlan" name="deliveryTimeInPlan" value="${salesOrder.deliveryTimeInPlan}" type="text" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('deliveryTimeInPlan','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">金额：</td>
											<td><input id="amount" name="amount" type="text" value="${salesOrder.amount }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">币种：</td>
											<td><input id="currencyType" name="currencyType" type="text" value="${salesOrder.currencyType.name }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">税率：</td>
											<td><input id="tax" name="tax" type="text" value="${salesOrder.tax }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">付款条件：</td>
											<td><input id="payCondition" name="payCondition" type="text" value="${salesOrder.payCondition }" size="30" onchange="fieldChanged(this);" /></td>
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
								url : '${vix}/dtbcenter/orderProcessingAction!goSaveOrUpdateSaleOrderItem.action?id=' + id + "&parentId=" + $("#id")
										.val(),
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 900,
									height : 550,
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
							url : '${vix}/dtbcenter/orderProcessingAction!getSaleOrderItemJson.action?id=${salesOrder.id}',
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
							width : 200,
							align : 'center'
							}, {
							field : 'itemname',
							title : '商品名称',
							width : 300,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'skuCode',
							title : 'SKU编码',
							width : 200,
							align : 'center'
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
							field : 'tvquantity',
							title : '数量',
							width : 100,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
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
									saveDeliveryAddress(row.id)
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
									url : '${vix}/dtbcenter/orderProcessingAction!deleteSaleOrderItemById.action?id=' + rows[i].id,
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
			</dl>
		</div>
	</form>
</div>