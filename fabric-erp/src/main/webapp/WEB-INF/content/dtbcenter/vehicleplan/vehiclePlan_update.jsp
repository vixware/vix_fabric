<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateOrder() {
		var dlData = $("#dlAddress").datagrid("getRows");
		var dlJson = JSON.stringify(dlData);
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/vehiclePlanAction!saveOrUpdate.action', {
			'stockTaking.id' : $("#id").val(),
			'dlJson' : dlJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/vehiclePlanAction!goList.action');
			});
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
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ldm_dtbcenter_management" /> </a></li>
				<li><a href="#"><s:text name="ldm_stowage_scheduling_management" /> </a></li>
				<li><a href="#"> <s:text name="ldm_vehicle_plan" />
				</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="stockTaking.id" value="${stockTaking.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/vehiclePlanAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="ldm_vehicle_plan" /> </b> </strong>
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
											<td><input id="stocktakingcode" name="stocktakingcode" value="${stockTaking.stocktakingcode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>调度时间：</th>
											<td><input id="createTime" name="createTime" value="${stockTaking.createTime}" type="text" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>起运城区：</th>
											<td><input id="warehousecode" name="warehousecode" value="${stockTaking.warehousecode}" class="validate[required] text-input" type="text" size="30" /></td>
											<th>目的城区：</th>
											<td><input id="departmentCode" name="departmentCode" value="${stockTaking.departmentCode}" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>承运方：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
											<th>车牌号：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>出发里程数：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
											<th>车辆调度状态：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>车辆可用状态：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
											<th>驾驶员：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>驾驶员电话：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
											<th>回程里程数：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>是否发生货物纠纷：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
											<th>是否发生交通事故：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>状态：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
											<th>备注：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" /></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />托运清单</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(4,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />货物明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/dtbcenter/vehiclePlanAction!getStockTakingItemJson.action?id=${stockTaking.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',width:60,align:'center'">编号</th>
										<th data-options="field:'stcode',width:100,align:'center',editor:'text',required:'true'">装载单号</th>
										<th data-options="field:'itemcode',width:100,align:'center',editor:'text',required:'true'">${vv:varView("vix_mdm_item")}编码</th>
										<th data-options="field:'itemname',width:100,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}名称</th>
										<th data-options="field:'itemtype',width:100,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}类型</th>
										<th data-options="field:'specification',width:100,align:'center',editor:'text'">规格型号</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /> </span> </span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /> </span> </span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /> </span>
								</span> </a>
							</div>
							<script type="text/javascript">
								$('#dlAddress').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#dlAddress').datagrid('validateRow', editIndexDlAddress)) {
										$('#dlAddress').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								$.fn.datebox.defaults.formatter = function(date) {
									var y = date.getFullYear();
									var m = date.getMonth() + 1;
									var d = date.getDate();
									return y + '/' + m + '/' + d;
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#dlAddress').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#dlAddress').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#dlAddress').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlAddress = $('#dlAddress').datagrid('getRows').length - 1;
										$('#dlAddress').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#dlAddress').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#dlAddress').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 724,
									height : 340,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#daForm').validationEngine('validate')) {
												$.post('${vix}/dtbcenter/vehiclePlanAction!saveOrUpdateStockTakingItem.action', {
												'id' : $("#id").val(),
												'wimStockrecordlines.id' : $("#daId").val(),
												'wimStockrecordlines.itemcode' : $("#itemcode").val(),
												'wimStockrecordlines.itemname' : $("#itemname").val(),
												'wimStockrecordlines.itemtype' : $("#itemtype").val(),
												'wimStockrecordlines.specification' : $("#specification").val(),
												'wimStockrecordlines.quantity' : $("#quantity").val(),
												'wimStockrecordlines.unitcost' : $("#unitcost").val(),
												'wimStockrecordlines.price' : $("#price").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#dlAddress2').datagrid("reload");
												});
											}
										}
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							$('#dlAddress2').datagrid({
							url : '${vix}/dtbcenter/vehiclePlanAction!getStockTakingItemJson.action?id=${wimTransvouch.id}',
							width : 'auto',
							height : 450,
							fitColumns : true,
							columns : [ [ {
							field : 'id',
							title : '编号',
							width : 100,
							align : 'center'
							}, {
							field : 'itemcode',
							title : '${vv:varView("vix_mdm_item")}编码',
							width : 200,
							align : 'center'
							}, {
							field : 'itemname',
							title : '${vv:varView("vix_mdm_item")}名称',
							width : 200,
							align : 'center'
							}, {
							field : 'itemtype',
							title : '${vv:varView("vix_mdm_item")}类型',
							width : 200,
							align : 'center'
							}, {
							field : 'specification',
							title : '规格型号',
							width : 200,
							align : 'center'
							}, {
							field : 'quantity',
							title : '数量',
							width : 150,
							align : 'right'
							}, {
							field : 'unitcost',
							title : '单价',
							width : 150,
							align : 'right'
							}, {
							field : 'price',
							title : '金额',
							width : 150,
							align : 'right'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/dtbcenter/vehiclePlanAction!goSaveOrUpdateStockTakingItem.action?id=0');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/dtbcenter/vehiclePlanAction!goSaveOrUpdateStockTakingItem.action?id=' + row.id);
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
									url : '${vix}/dtbcenter/vehiclePlanAction!deleteStockTakingItemById.action?id=' + rows[i].id,
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
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>