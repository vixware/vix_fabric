<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateOrder() {
		var deliveryPersonData = $("#deliveryPersonTable").datagrid("getSelections");
		var deliveryPersonJson = JSON.stringify(deliveryPersonData);
		if ($('#deliveryPlanForm').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/distributionManagementAction!saveOrUpdate.action', {
			'deliveryPlan.id' : $("#id").val(),
			'deliveryPlan.dpCode' : $("#dpCode").val(),
			'deliveryPlan.deliveryPlanTime' : $("#deliveryPlanTime").val(),
			'deliveryPlan.department' : $("#department").val(),
			'deliveryPlan.salePersonCode' : $("#salePersonCode").val(),
			'deliveryPlan.loadTime' : $("#loadTime").val(),
			'deliveryPlan.vehicleDeliveryTime' : $("#vehicleDeliveryTime").val(),
			'deliveryPlan.transferCharget' : $("#transferCharget").val(),
			'deliveryPlan.status' : $("#status").val(),
			'updateField' : updateField,
			'deliveryPersonJson' : deliveryPersonJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/distributionManagementAction!goList.action');
			});
		}
	}
	$("#deliveryPlanForm").validationEngine();
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
												url : '${vix}/dtbcenter/distributionManagementAction!converterSalesOrderToTruckingOrder.action?salesOrderid=' + returnValue + "&id=${deliveryPlan.id}",
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
												url : '${vix}/dtbcenter/distributionManagementAction!converterDispatchRouteToDeliveryReceipt.action?dispatchRouteid=' + returnValue + "&id=${deliveryPlan.id}",
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
	function saveVehicle(url) {
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
							title : "选择车辆",
							html : html,
							callback : function(action, returnValue) {
								if (action == 'ok') {
									if (returnValue != '') {
										$
												.ajax({
												url : '${vix}/dtbcenter/distributionManagementAction!converterVehicleToDeliveryPlan.action?vehicleid=' + returnValue + "&id=${deliveryPlan.id}",
												cache : false,
												success : function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#vehicleTable').datagrid("reload");
												}
												});
									} else {
										asyncbox.success("请选择车辆!", "<s:text name='vix_message'/>");
										return false;
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
				<li><a href="#"><img src="${vix}/common/img/distribution_plan.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ldm_dtbcenter_management" /> </a></li>
				<li><a href="#"><s:text name="ldm_stowage_scheduling_management" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/distributionManagementAction!goList.action');"><s:text name="ldm_distribution" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="deliveryPlan.id" value="${deliveryPlan.id}" />
<div class="content">
	<form id="deliveryPlanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/distributionManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="ldm_distribution" /> </b> </strong>
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
											<td align="right">计划编码：</td>
											<td><input id="dpCode" name="dpCode" value="${deliveryPlan.dpCode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<td align="right">配送时间：</td>
											<td><input id="deliveryPlanTime" name="deliveryPlanTime" value="${deliveryPlan.deliveryPlanTime}" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('deliveryPlanTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">部门：</td>
											<td><select id="department" name="department" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1" selected="selected">部门1</option>
													<option value="2">部门2</option>
													<option value="3">部门3</option>
											</select></td>
											<td align="right">业务员：</td>
											<td><input id="salePersonCode" name="salePersonCode" value="${deliveryPlan.salePersonCode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">运输负责人：</td>
											<td><input id="transferCharget" name="transferCharget" value="${deliveryPlan.transferCharget }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">状态：</td>
											<td><select id="status" name="status" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="配送中" selected="selected">配送中</option>
													<option value="代配送">代配送</option>
													<option value="配送完成">配送完成</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">提货时间：</td>
											<td><input id="loadTime" name="loadTime" value="${deliveryPlan.loadTime}" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('loadTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">发车时间：</td>
											<td><input id="vehicleDeliveryTime" name="vehicleDeliveryTime" value="${deliveryPlan.vehicleDeliveryTime}" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('vehicleDeliveryTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
							<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(5,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />订单</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(5,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />车辆</a></li>
							<%-- <li><a onclick="javascript:$('#a3').attr('style','');tab(5,3,'a',event)"><img
									src="${vix}/common/img/mail.png" alt="" />配送人员</a></li> --%>
							<li><a onclick="javascript:$('#a4').attr('style','');tab(5,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />运输路线</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							$('#delieryNotificationtable').datagrid({
							url : '${vix}/dtbcenter/distributionManagementAction!getSalesOrderJson.action?id=${deliveryPlan.id}',
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
								saveDeliveryAddress('${vix}/dtbcenter/distributionManagementAction!goChooseSalesOrder.action');
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
									url : '${vix}/dtbcenter/distributionManagementAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
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
							$('#vehicleTable').datagrid({
							url : '${vix}/dtbcenter/distributionManagementAction!getVehicleJson.action?id=${deliveryPlan.id}',
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
							field : 'vehicleNO',
							title : '车牌号',
							width : 100,
							align : 'center'
							}, {
							field : 'type',
							title : '车辆类型',
							width : 100,
							align : 'center'
							}, {
							field : 'carryingcapacity',
							title : '载重',
							width : 100,
							align : 'right'
							}, {
							field : 'capacity',
							title : '容量',
							width : 100,
							align : 'right'
							}, {
							field : 'driver',
							title : '驾驶员',
							width : 100,
							align : 'right'
							}, {
							field : 'vehicleStauts',
							title : '车辆状态',
							width : 100,
							align : 'right'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveVehicle('${vix}/dtbcenter/distributionManagementAction!goChooseVehicle.action');
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#vehicleTable').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#vehicleTable').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#vehicleTable').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/dtbcenter/distributionManagementAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
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
							<table id="vehicleTable"></table>
						</div>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="deliveryPersonTable" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',rownumbers : true,url: '${vix}/dtbcenter/distributionManagementAction!getDeliveryPersonJson.action?id=${deliveryPlan.id}',onClickRow: onDlClickRow3">
								<thead frozen="true">
									<tr>
										<th data-options="field:'ck',checkbox:'true'"></th>
									</tr>
								</thead>
								<thead>
									<tr>
										<th data-options="field:'employeeCode',width:100,align:'center'">员工编号</th>
										<th data-options="field:'employeeName',width:100,align:'center'">姓名</th>
										<th data-options="field:'sex',width:100,align:'center'">性别</th>
										<th data-options="field:'department',width:100,align:'center'">所属部门</th>
										<th data-options="field:'workNumber',width:100,align:'center'">工号</th>
										<th data-options="field:'mobile',width:100,align:'center'">手机号码</th>
										<th data-options="field:'telephone',width:100,align:'center'">联系电话</th>
										<th data-options="field:'status',width:100,align:'center'">状态</th>
									</tr>
								</thead>
							</table>
							<%-- <div id="deliveryPersonTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-add',plain:true" onclick="appendDlBankInfo()"><s:text
										name="cmn_add" /></a> <a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlBankInfo()"><s:text
										name="cmn_delete" /></a> <a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-save',plain:true" onclick="saveDlBankInfo()"><s:text
										name="cmn_save" /></a>
							</div> --%>
							<script type="text/javascript">
								$('#deliveryPersonTable').datagrid();
								var deliveryPerson = undefined;
								function endDlEditing3() {
									if (deliveryPerson == undefined) {
										return true;
									}
									if ($('#deliveryPersonTable').datagrid('validateRow', deliveryPerson)) {
										$('#deliveryPersonTable').datagrid('endEdit', deliveryPerson);
										deliveryPerson = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow3(index) {
									if (deliveryPerson != index) {
										if (endDlEditing3()) {
											$('#deliveryPersonTable').datagrid('selectRow', index).datagrid('beginEdit', index);
											deliveryPerson = index;
										} else {
											$('#deliveryPersonTable').datagrid('selectRow', deliveryPerson);
										}
									}
								}
								function appendDlBankInfo() {
									if (endDlEditing3()) {
										$('#deliveryPersonTable').datagrid('appendRow', {
											status : 'P'
										});
										deliveryPerson = $('#deliveryPersonTable').datagrid('getRows').length - 1;
										$('#deliveryPersonTable').datagrid('selectRow', deliveryPerson).datagrid('beginEdit', deliveryPerson);
									}
								}
								function removeDlBankInfo() {
									if (deliveryPerson == undefined) {
										return;
									}
									$('#deliveryPersonTable').datagrid('cancelEdit', deliveryPerson).datagrid('deleteRow', deliveryPerson);
									deliveryPerson = undefined;
								}
								function saveDlBankInfo() {
									if (endDlEditing3()) {
										$('#deliveryPersonTable').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
					<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							$('#dispatchRouteTable').datagrid({
							url : '${vix}/dtbcenter/distributionManagementAction!getDispatchRouteJson.action?id=${deliveryPlan.id}',
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
								saveDispatchRoute('${vix}/dtbcenter/distributionManagementAction!goChooseDispatchRoute.action');
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
									url : '${vix}/dtbcenter/distributionManagementAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
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
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<th width="12%">体积：</th>
								<td width="14%"><input id="salePerson1" name="salePerson1" value="" type="text" onchange="fieldChanged(this);" /></td>
								<th width="12%">总重量：</th>
								<td width="12%"><input id="price" name="price" value="" type="text" onchange="fieldChanged(this);" /> <script type="text/javascript">
									$(function() {
										$('#price').priceFormat({
										prefix : '',
										centsLimit : 3
										});
									});
								</script></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>