<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}

	function initdata() {
		if (document.getElementById("scheduleTime").value == "") {
			var myDate = new Date();
			$("#scheduleTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	}
	function saveOrUpdateOrder() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/sentCarSingleManagementAction!saveOrUpdate.action', {
			'deliveryReceipt.id' : $("#id").val(),
			'deliveryReceipt.scheduleCode' : $("#scheduleCode").val(),
			'deliveryReceipt.scheduleTime' : $("#scheduleTime").val(),
			'deliveryReceipt.scheduleType' : $("#scheduleType").val(),
			'deliveryReceipt.vehicleNO' : $("#vehicleNO").val(),
			'deliveryReceipt.vehicleNumber' : $("#vehicleNumber").val(),
			'deliveryReceipt.driver' : $("#driver").val(),
			'deliveryReceipt.driverMobile' : $("#driverMobile").val(),
			'deliveryReceipt.dispatchTime' : $("#dispatchTime").val(),
			'deliveryReceipt.mileage' : $("#mileage").val(),
			'deliveryReceipt.carryingCapacity' : $("#carryingCapacity").val(),
			'deliveryReceipt.carryingCubage' : $("#carryingCubage").val(),
			'deliveryReceipt.sendInPlan' : $("#sendInPlan").val(),
			'deliveryReceipt.scheduler' : $("#scheduler").val(),
			'updateField' : updateField,
			'deliveryReceipt.carrier' : $("#carrier").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/sentCarSingleManagementAction!goList.action');
			});
		} else {
			return false;
		}
	}
	/*表单验证  */
	$("#order").validationEngine();
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
												url : '${vix}/dtbcenter/sentCarSingleManagementAction!converterSalesOrderToTruckingOrder.action?salesOrderid=' + returnValue + "&id=${deliveryReceipt.id}",
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
	function saveDispatchRoute(url) {
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
							title : "选择路线",
							html : html,
							callback : function(action, returnValue) {
								if (action == 'ok') {
									if (returnValue != '') {
										$
												.ajax({
												url : '${vix}/dtbcenter/sentCarSingleManagementAction!converterDispatchRouteToDeliveryReceipt.action?dispatchRouteid=' + returnValue + "&id=${deliveryReceipt.id}",
												cache : false,
												success : function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#dispatchRouteTable').datagrid("reload");
												}
												});
									} else {
										asyncbox.success("请选择路线!", "<s:text name='vix_message'/>");
										return false;
									}
								}
							},
							btnsbar : $.btn.OKCANCEL
							});
				}
				});
	}
	/** 选择车辆 */
	function choosePurchaseOrder() {
		$.ajax({
		url : '${vix}/dtbcenter/sentCarSingleManagementAction!goChooseVehicle.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 900,
			height : 550,
			title : "选择车辆",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#vehicleNO").val(result[1]);
						$("#carryingCapacity").val(result[2]);
						$("#carryingCubage").val(result[3]);
						$("#driver").val(result[4]);
						$("#driverMobile").val(result[5]);
					} else {
						$("#vehicleNO").val("");
						asyncbox.success("请选择车辆信息!", "提示信息");
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
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/sentCarSingleManagementAction!goList.action?pageNo=${pageNo}');"><s:text name="dtbcenter_sentcarsingleManagement" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${deliveryReceipt.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/sentCarSingleManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="dtbcenter_sentcarsingle" /> </b> </strong>
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
											<th>调度单号：</th>
											<td><input id="scheduleCode" name="scheduleCode" value="${deliveryReceipt.scheduleCode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>调度时间：</th>
											<td><input id="scheduleTime" name="scheduleTime" value="${deliveryReceipt.scheduleTime}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('scheduleTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
										</tr>
										<tr>
											<th>调度类型：</th>
											<td><select id="scheduleType" name="scheduleType" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1" selected="selected">类型1</option>
													<option value="2">类型2</option>
													<option value="3">类型3</option>
											</select></td>
											<th>车牌号：</th>
											<td><input id="vehicleNO" name="vehicleNO" value="${deliveryReceipt.vehicleNO }" type="text" size="20" class="validate[required] text-input" onchange="fieldChanged(this);"> <input class="btn" type="button" value="选车" onclick="choosePurchaseOrder();" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>载重 ：</th>
											<td><input id="carryingCapacity" name="carryingCapacity" value="${deliveryReceipt.carryingCapacity }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>承载体积 ：</th>
											<td><input id="carryingCubage" name="carryingCubage" value="${deliveryReceipt.carryingCubage }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>驾驶员：</th>
											<td><select id="driver" name="driver" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1" selected="selected">张三</option>
													<option value="2">李四</option>
													<option value="3">王五</option>
											</select></td>
											<th>驾驶员电话：</th>
											<td><input id="driverMobile" name="driverMobile" value="${deliveryReceipt.driverMobile }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>计划发车时间 ：</th>
											<td><input id="sendInPlan" name="sendInPlan" value="${deliveryReceipt.sendInPlan}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('sendInPlan','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th>实际发车时间：</th>
											<td><input id="dispatchTime" name="dispatchTime" value="${deliveryReceipt.dispatchTime}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('dispatchTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
										</tr>
										<tr>
											<th>出发里程数 ：</th>
											<td><input id="mileage" name="mileage" value="${deliveryReceipt.mileage }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>车次：</th>
											<td><input id="vehicleNumber" name="vehicleNumber" value="${deliveryReceipt.vehicleNumber }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>承运方：</th>
											<td><input id="carrier" name="carrier" value="${deliveryReceipt.carrier }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>调度员 ：</th>
											<td><input id="scheduler" name="scheduler" value="${deliveryReceipt.scheduler }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
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
							<li><a onclick="javascript:$('#a2').attr('style','');tab(2,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />运输路线</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							$('#delieryNotificationtable').datagrid({
							url : '${vix}/dtbcenter/sentCarSingleManagementAction!getSalesOrderJson.action?id=${deliveryReceipt.id}',
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
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/dtbcenter/sentCarSingleManagementAction!goChooseSalesOrder.action');
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
									url : '${vix}/dtbcenter/sentCarSingleManagementAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
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
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							$('#dispatchRouteTable').datagrid({
							url : '${vix}/dtbcenter/sentCarSingleManagementAction!getDispatchRouteJson.action?id=${deliveryReceipt.id}',
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
							field : 'pathSerial',
							title : '路线序号',
							width : 100,
							align : 'center'
							}, {
							field : 'name',
							title : '路线名称',
							width : 100,
							align : 'center'
							}, {
							field : 'source',
							title : '起始地',
							width : 100,
							align : 'right'
							}, {
							field : 'target',
							title : '目的地',
							width : 100,
							align : 'right'
							}, {
							field : 'mileage',
							title : '里程',
							width : 100,
							align : 'right'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDispatchRoute('${vix}/dtbcenter/sentCarSingleManagementAction!goChooseDispatchRoute.action');
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#dispatchRouteTable').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#dispatchRouteTable').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#dispatchRouteTable').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/dtbcenter/sentCarSingleManagementAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
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
							<table id="dispatchRouteTable"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>