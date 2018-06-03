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
			$.post('${vix}/dtbcenter/wayBillProcessAction!saveOrUpdate.action', {
			'wayBill.id' : $("#id").val(),
			'wayBill.shippingOrderCode' : $("#shippingOrderCode").val(),
			'wayBill.committedTime' : $("#committedTime").val(),
			'wayBill.consignmentType' : $("#consignmentType").val(),
			'wayBill.mainTransportType' : $("#mainTransportType").val(),
			'wayBill.consignmentSide' : $("#consignmentSide").val(),
			'wayBill.receivingOrders' : $("#receivingOrders").val(),
			'wayBill.emergency' : $("#emergency").val(),
			'wayBill.customerServiceNumber' : $("#customerServiceNumber").val(),
			'wayBill.clearingForm' : $("#clearingForm").val(),
			'wayBill.salesman' : $("#salesman").val(),
			'wayBill.workRequest' : $("#workRequest").val(),
			'wayBill.transportationNature' : $("#transportationNature").val(),
			'wayBill.shipper' : $("#shipper").val(),
			'wayBill.shipperSite' : $("#shipperSite").val(),
			'wayBill.shipperLoadTime' : $("#shipperLoadTime").val(),
			'wayBill.shipperAddress' : $("#shipperAddress").val(),
			'wayBill.shipperContact' : $("#shipperContact").val(),
			'wayBill.shipperTel' : $("#shipperTel").val(),
			'wayBill.consignee' : $("#consignee").val(),
			'wayBill.consigneeSite' : $("#consigneeSite").val(),
			'wayBill.consigneeLoadTime' : $("#consigneeLoadTime").val(),
			'wayBill.consigneeAddress' : $("#consigneeAddress").val(),
			'wayBill.consigneeContact' : $("#consigneeContact").val(),
			'updateField' : updateField,
			'wayBill.consigneeTel' : $("#consigneeTel").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/wayBillProcessAction!goList.action');
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
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/wayBillProcessAction!goList.action');">托运单管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${wayBill.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateSalesOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/wayBillProcessAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>托运单信息 </b> </strong>
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
											<th>单号：</th>
											<td><input id="shippingOrderCode" name="shippingOrderCode" value="${wayBill.shippingOrderCode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>委托时间：</th>
											<td><input id="committedTime" name="committedTime" value="${wayBill.committedTime}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('committedTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>托运类型：</th>
											<td><input id="consignmentType" name="consignmentType" value="${wayBill.consignmentType}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>主要运输方式：</th>
											<td><input id="mainTransportType" name="mainTransportType" value="${wayBill.mainTransportType}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>托运方：</th>
											<td><input id="consignmentSide" name="consignmentSide" value="${wayBill.consignmentSide}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">接单方：</td>
											<td><input id="receivingOrders" name="receivingOrders" type="text" value="${wayBill.receivingOrders }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">紧急程度：</td>
											<td><input id="emergency" name="emergency" type="text" value="${wayBill.emergency }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">客户业务编号：</td>
											<td><input id="customerServiceNumber" name="customerServiceNumber" type="text" value="${wayBill.customerServiceNumber }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">结算方式：</td>
											<td><input id="clearingForm" name="clearingForm" type="text" value="${wayBill.clearingForm }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">业务员：</td>
											<td><input id="salesman" name="salesman" type="text" value="${wayBill.salesman }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">作业要求：</td>
											<td><input id="workRequest" name="workRequest" type="text" value="${wayBill.workRequest }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">运输性质：</td>
											<td><input id="transportationNature" name="transportationNature" type="text" value="${wayBill.transportationNature }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">发货方：</td>
											<td><input id="shipper" name="shipper" type="text" value="${wayBill.shipper }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">站点：</td>
											<td><input id="shipperSite" name="shipperSite" type="text" value="${wayBill.shipperSite }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">装货时间：</td>
											<td><input id="shipperLoadTime" name="shipperLoadTime" value="${wayBill.shipperLoadTime}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('shipperLoadTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td align="right">地址：</td>
											<td><input id="shipperAddress" name="shipperAddress" type="text" value="${wayBill.shipperAddress }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">联系人：</td>
											<td><input id="shipperContact" name="shipperContact" type="text" value="${wayBill.shipperContact }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">电话：</td>
											<td><input id="shipperTel" name="shipperTel" type="text" value="${wayBill.shipperTel }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">收货方：</td>
											<td><input id="consignee" name="consignee" type="text" value="${wayBill.consignee }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">收货方站点：</td>
											<td><input id="consigneeSite" name="consigneeSite" type="text" value="${wayBill.consigneeSite }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">到货时间：</td>
											<td><input id="consigneeLoadTime" name="consigneeLoadTime" value="${wayBill.consigneeLoadTime}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('consigneeLoadTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td align="right">收货方地址：</td>
											<td><input id="consigneeAddress" name="consigneeAddress" type="text" value="${wayBill.consigneeAddress }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">收货方联系人：</td>
											<td><input id="consigneeContact" name="consigneeContact" type="text" value="${wayBill.consigneeContact }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">收货方电话：</td>
											<td><input id="consigneeTel" name="consigneeTel" type="text" value="${wayBill.consigneeTel }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />订单明细</a></li>
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
												$.post('${vix}/dtbcenter/wayBillProcessAction!saveOrUpdateWayBillItem.action', {
												'id' : $("#id").val(),
												'wayBillItem.id' : $("#oiId").val(),
												'wayBillItem.itemCode' : $("#itemCode").val(),
												'wayBillItem.specification' : $("#specification").val(),
												'wayBillItem.price' : $("#price").val(),
												'wayBillItem.amount' : $("#amount").val(),
												'wayBillItem.unit' : $("#unit").val()
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
							url : '${vix}/dtbcenter/wayBillProcessAction!getWayBillItemJson.action?id=${wayBill.id}',
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
							field : 'itemCode',
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
							field : 'amount',
							title : '数量',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'price',
							title : '单价',
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
								saveDeliveryAddress('${vix}/dtbcenter/wayBillProcessAction!goAddSaleOrderItem.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/dtbcenter/wayBillProcessAction!goAddSaleOrderItem.action?id=' + row.id);
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
									url : '${vix}/dtbcenter/wayBillProcessAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
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
			</dl>
		</div>
	</form>
</div>