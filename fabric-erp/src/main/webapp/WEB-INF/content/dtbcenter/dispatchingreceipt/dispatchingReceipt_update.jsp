<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateOrder() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/dispatchingReceiptAction!saveOrUpdate.action', {
			'dispatchingReceipt.id' : $("#id").val(),
			'dispatchingReceipt.code' : $("#code").val(),
			'dispatchingReceipt.fillTime' : $("#fillTime").val(),
			'dispatchingReceipt.person' : $("#person").val(),
			'dispatchingReceipt.reason' : $("#reason").val(),
			'dispatchingReceipt.usageOfPlan' : $("#usageOfPlan").val(),
			'dispatchingReceipt.deparmentManager' : $("#deparmentManager").val(),
			'dispatchingReceipt.officeManager' : $("#officeManager").val(),
			'dispatchingReceipt.send' : $("#send").val(),
			'dispatchingReceipt.vehicle' : $("#vehicle").val(),
			'dispatchingReceipt.driver' : $("#driver").val(),
			'updateField' : updateField,
			'dispatchingReceipt.actualUsageInfo' : $("#actualUsageInfo").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/dispatchingReceiptAction!goList.action');
			});
		}
	}
	$("#order").validationEngine();
	$(function() {
		//加载创建时间(新增)
		if (document.getElementById("fillTime").value == "") {
			var myDate = new Date();
			$("#fillTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours() + ":" + myDate
					.getMinutes() + ":" + myDate.getSeconds());
		}
	});
	function saveDeliveryAddress(url) {
		$
				.ajax({
				url : url,
				cache : false,
				success : function(html) {
					asyncbox
							.open({
							modal : true,
							width : 900,
							height : 550,
							title : "选择订单",
							html : html,
							callback : function(action, returnValue) {
								if (action == 'ok') {
									if (returnValue != '') {
										$
												.ajax({
												url : '${vix}/dtbcenter/dispatchingReceiptAction!converterDispatchingReceiptToSalesOrder.action?salesOrderid=' + returnValue + "&id=${dispatchingReceipt.id}",
												cache : false,
												success : function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#delieryNotificationtable').datagrid("reload");
												}
												});
									} else {
										asyncbox.success("请选择订单!", "<s:text name='vix_message'/>");
										return false;
									}
								}
							},
							btnsbar : $.btn.OKCANCEL
							});
				}
				});
	}
	function choosePurchaseOrder() {
		$
				.ajax({
				url : '${vix}/dtbcenter/dispatchingReceiptAction!goChooseTruckingOrder.action',
				cache : false,
				success : function(html) {
					asyncbox
							.open({
							modal : true,
							width : 900,
							height : 550,
							title : "选择派车单",
							html : html,
							callback : function(action, returnValue) {
								if (action == 'ok') {
									if (returnValue != undefined) {
										var result = returnValue.split(",");
										$
												.ajax({
												url : '${vix}/dtbcenter/dispatchingReceiptAction!converterDispatchingReceiptToSalesOrder.action?deliveryReceiptId=' + result[0] + "&id=${dispatchingReceipt.id}",
												cache : false,
												success : function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#delieryNotificationtable').datagrid("reload");
												}
												});
										$("#vehicle").val(result[1]);
									} else {
										$("#vehicle").val("");
										asyncbox.success("请选择派车单信息!", "提示信息");
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
				<li><a href="#"><img src="${vix}/common/img/dtbcenter_transportation_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter_transpotmgr" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/dispatchingReceiptAction!goList.action?pageNo=${pageNo}');"><s:text name="dtbcenter_deliveryReceipt_business" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="dispatchingReceipt.id" value="${dispatchingReceipt.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/dispatchingReceiptAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="dtbcenter_deliveryReceipt" /> </b> </strong>
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
											<th>编号：</th>
											<td><input id="code" name="code" value="${dispatchingReceipt.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <input class="btn" type="button" value="选派车单" onclick="choosePurchaseOrder();" /><span style="color: red;">*</span></td>
											<th>填写日期：</th>
											<td><input id="fillTime" name="fillTime" value="${dispatchingReceipt.fillTime}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('fillTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>用车人员：</th>
											<td><input id="person" name="person" value="${dispatchingReceipt.person}" class="validate[required] text-input" type="text" size="30" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<th>用车事由：</th>
											<td><input id="reason" name="reason" value="${dispatchingReceipt.reason}" class="validate[required] text-input" type="text" size="30" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>车辆：</th>
											<td><input id="vehicle" name="vehicle" value="${dispatchingReceipt.vehicle}" class="validate[required] text-input" type="text" size="30" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<th>司机：</th>
											<td><input id="driver" name="driver" value="${dispatchingReceipt.driver}" class="validate[required] text-input" type="text" size="30" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>计划用车情况：</th>
											<td><input id="usageOfPlan" name="usageOfPlan" value="${dispatchingReceipt.usageOfPlan}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>部门经理：</th>
											<td><input id="deparmentManager" name="deparmentManager" value="${dispatchingReceipt.deparmentManager}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>行政部门经理：</th>
											<td><input id="officeManager" name="officeManager" value="${dispatchingReceipt.officeManager }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>派车：</th>
											<td><input id="send" name="send" value="${dispatchingReceipt.send }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>实际出车情况：</th>
											<td><input id="actualUsageInfo" name="actualUsageInfo" value="${dispatchingReceipt.actualUsageInfo }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
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
							<li class="current"><a onclick="javascript:tab(2,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />销售订单</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							$('#delieryNotificationtable').datagrid({
							url : '${vix}/dtbcenter/dispatchingReceiptAction!getSalesOrderJson.action?id=${dispatchingReceipt.id}',
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
							columns : [ [ {
							field : 'code',
							title : '订单编码',
							width : 100,
							align : 'center'
							}, {
							field : 'customerCode',
							title : '客户代码',
							width : 100,
							align : 'center'
							}, {
							field : 'customerName',
							title : '客户名称 ',
							width : 100,
							align : 'center'
							}, {
							field : 'customerCompany',
							title : '客户公司',
							width : 100,
							align : 'right'
							}, {
							field : 'salePerson',
							title : '销售人员',
							width : 100,
							align : 'right'
							}, {
							field : 'loadPoint',
							title : '装运点',
							width : 100,
							align : 'right'
							}, {
							field : 'source',
							title : '发运地',
							width : 100,
							align : 'center'
							}, {
							field : 'target',
							title : '目的地',
							width : 100,
							align : 'center'
							}, {
							field : 'carrier',
							title : '承运商',
							width : 100,
							align : 'center'
							}, {
							field : 'transferStyle',
							title : '运输方式',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								$('#btnedit').linkbutton('enable');
								/* saveDeliveryAddress ('${vix}/dtbcenter/dispatchingReceiptAction!goChooseSalesOrder.action'); */
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#delieryNotificationtable').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#delieryNotificationtable').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#delieryNotificationtable').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
										/* url : '${vix}/dtbcenter/dispatchingReceiptAction!deleteWimStockrecordlinesById.action?id=' + rows [ i ].id , */
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
							<table id="delieryNotificationtable"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>