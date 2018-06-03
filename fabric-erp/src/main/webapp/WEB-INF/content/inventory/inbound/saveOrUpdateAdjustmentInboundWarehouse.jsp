<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateOrder() {
		var dlData = $("#stockRecordLines").datagrid("getRows");
		var dlJson = JSON.stringify(dlData);
		var biztype = 4;
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#id").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.sppliercode' : $("#sppliercode").val(),
			'stockRecords.inventoryType.id' : $("#inventoryTypeId").val(),
			'stockRecords.bizsource' : $("#bizsource").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.warehousePerson' : $("#warehousePerson").val(),
			'stockRecords.checkperson' : $("#checkperson").val(),
			'updateField' : updateField,
			'biztype' : biztype,
			'dlJson' : dlJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/inboundWarehouseAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#order").validationEngine();
	function initdata() {
		//加载创建时间(新增)
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
		//设置原单类型选中
		$("#bizsource").val('${stockRecords.bizsource}');
		//设置入库类别选中
		$("#inventoryTypeId").val('${stockRecords.inventoryType.id}');
	}
	function textareaChanged() {
		saveOrUpdateStockRecordLines();
	}

	function changeselectOptionId(selectOptionP) {
		if (selectOptionP == "1") {
			$("#selectOptionId2").hide();
			$("#selectOptionId1").show();
		} else if (selectOptionP == "2") {
			$("#selectOptionId1").hide();
			$("#selectOptionId2").show();
			$("#itemcode").focus();
			$('#stockRecordLines').datagrid("reload");
		}
	}
	changeselectOptionId("1");
	initdata();
	function chooseWarehouse() {
		$.ajax({
		url : '${vix}/inventory/warehouseAction!goChooseWarehouse.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择仓库",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#warehouseId").val(result[0]);
						$("#warehouseName").val(result[1]);
					} else {
						asyncbox.success("请选择仓库信息!", "提示信息");
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
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action');">入库调整单 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${stockRecords.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>入库调整单 </b> </strong>
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
											<th>入库单号：</th>
											<td><input id="code" name="code" value="${stockRecords.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>主题：</th>
											<td><input id="name" name="name" value="${stockRecords.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>入库时间：</th>
											<td><input id="createTime" name="stockRecords.createTime" value="<s:date name="%{stockRecords.createTime}" format="yyyy-MM-dd HH:mm:ss"/>" onchange="fieldChanged(this);" type="text" class="validate[required] text-input" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"><span style="color: red;">*</span></td>
											<th>收货仓库：</th>
											<td><input type="hidden" id="warehouseId" name="warehouseId" value="${stockRecords.invWarehouse.id }" /><input type="text" name="warehouseName" id="warehouseName" value="${stockRecords.invWarehouse.name }" size="20" class="validate[required] text-input" /><span style="color: red;">*</span><input class="btn" type="button"
												value="选择" onclick="chooseWarehouse();" /></td>
										</tr>
										<tr>
											<th>源单类型：</th>
											<td><select id="bizsource" name="stockRecords.bizsource" onchange="fieldChanged(this);">
													<option value="" selected="selected">请选择</option>
													<option value="1">采购订单</option>
													<option value="2">生产订单</option>
													<option value="3">其他开单</option>
											</select></td>
											<th>入库类别：</th>
											<td><s:select id="inventoryTypeId" headerKey="-1" headerValue="--请选择--" list="%{inventoryTypeList}" onchange="fieldChanged(this);" listValue="name" listKey="id" value="%{stockRecords.inventoryType.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr>
											<th>库管员：</th>
											<td><input id="warehousePerson" name="stockRecords.warehousePerson" value="${stockRecords.warehousePerson }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>验收人：</th>
											<td><input id="checkperson" name="stockRecords.checkperson" value="${stockRecords.checkperson }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>录入方式选择</th>
											<td><select id="selectOption" name="selectOption" onchange="changeselectOptionId(this.value)" class="validate[required] text-input">
													<option value="1">手动录入</option>
													<option value="2">扫描录入</option>
											</select></td>
											<th>商品编码：</th>
											<td><input id="itemcode" name="itemcode" type="text" onchange="textareaChanged();" /></td>
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
							function saveDeliveryAddress(id) {
								$.ajax({
								url : '${vix}/inventory/inboundWarehouseAction!goSaveOrUpdateStockRecordLines.action?id=' + id,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 1024,
									height : 600,
									title : "订单明细",
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
							url : '${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=${stockRecords.id}',
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
									saveDeliveryAddress('${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=' + row.id);
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
									url : '${vix}/inventory/inboundWarehouseAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
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
				<div class="clearfix" style="background: #FFF; position: relative;" id="selectOptionId2">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="stockRecordLines" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,fitColumns: true, toolbar: '#dlAddressTb',url: '${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=${stockRecords.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'itemcode',width:100,align:'center',editor:'text'">商品编码</th>
										<th data-options="field:'itemname',width:100,align:'center',editor:'text'">商品名称</th>
										<th data-options="field:'specification',width:100,align:'center',editor:'text'">规格型号</th>
										<th data-options="field:'unitcost',width:100,align:'center',editor:'numberbox'">单价</th>
										<th data-options="field:'unit',width:100,align:'center',editor:'text'">单位</th>
										<th data-options="field:'quantity',width:100,align:'center',editor:'numberbox'">数量</th>
										<th data-options="field:'price',width:100,align:'center',editor:'numberbox'">金额</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /> </span> </span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /> </span> </span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /> </span> </span> </a>
							</div>
							<script type="text/javascript">
								$('#stockRecordLines').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#stockRecordLines').datagrid('validateRow', editIndexDlAddress)) {
										$('#stockRecordLines').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#stockRecordLines').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#stockRecordLines').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#stockRecordLines').datagrid('appendRow', {});
										editIndexDlAddress = $('#stockRecordLines').datagrid('getRows').length - 1;
										$('#stockRecordLines').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#stockRecordLines').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#stockRecordLines').datagrid('acceptChanges');
									}
								}
								function saveOrUpdateStockRecordLines() {
									$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdateStockRecordLines.action', {
									'id' : $("#id").val(),
									'itemcode' : $("#itemcode").val()
									}, function(result) {
										showMessage(result);
										setTimeout("", 1000);
										$('#stockRecordLines').datagrid("reload");
										$("#itemcode").val('');
									});
								}
							</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>