<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function initdate() {
		$("#selectOptionId2").hide();
		$("#selectOptionId1").show();
		//加载创建时间(新增)
		if (document.getElementById("billDate").value == "") {
			var myDate = new Date();
			$("#billDate").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	}
	initdate();
	function saveOrUpdateSalesOrder() {
		/** 明细 */
		var soData = $("#so").datagrid("getRows");
		var sojson = JSON.stringify(soData);
		if ($('#salesOrderForm').validationEngine('validate')) {
			$.post('${vix}/drp/storeSalesrecordAction!saveOrUpdate.action', {
			'salesOrder.id' : $("#id").val(),
			'salesOrder.channelDistributor.id' : $("#channelDistributorId").val(),
			'salesOrder.code' : $("#code").val(),
			'salesOrder.billDate' : $("#billDate").val(),
			'salesOrder.usageCustomer' : $("#usageCustomer").val(),
			'salesOrder.amount' : $("#salesOrderAmount").val(),
			'updateField' : updateField,
			'sojson' : sojson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/storeSalesrecordAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#salesOrderForm").validationEngine();
	var updateField = "";
	function salesOrderFieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}

	function textareaChanged() {
		saveOrUpdateStockRecordLines();
	}

	function changeDisplay(selectOptionP) {
		if (selectOptionP == "1") {
			$("#selectOptionId2").hide();
			$("#selectOptionId1").show();
		} else if (selectOptionP == "2") {
			$("#selectOptionId1").hide();
			$("#selectOptionId2").show();
			$("#itemcode").focus();
			$('#so').datagrid("reload");
		}
	}
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/distributionSystemRelationShipAction!goChooseOrganization.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择父公司",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#channelDistributorId").val(result[0]);
						$("#channelDistributorName").val(result[1]);
					} else {
						$("#channelDistributorId").val("");
						$("#channelDistributorName").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
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
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_store_management" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeSalesrecordAction!goList.action');">门店销售记录</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${salesOrder.id}" />
<div class="content">
	<form id="salesOrderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateSalesOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/drp/storeSalesrecordAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>销售订单信息</b> </strong>
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
											<td align="right">编码：</td>
											<td><input id="code" name="code" value="${salesOrder.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">时间：</td>
											<td><input id="billDate" name="billDate" value="<fmt:formatDate value='${salesOrder.billDate }' type='both' pattern='yyyy-MM-dd' />" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('billDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">门店：</td>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${salesOrder.channelDistributor.name }" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${salesOrder.channelDistributor.id}" /> <input
												class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /><span style="color: red;">*</span></td>
											<td align="right">客户姓名：</td>
											<td><input id="usageCustomer" name="usageCustomer" value="${salesOrder.usageCustomer }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">金额：</td>
											<td><input id="salesOrderAmount" name="salesOrderAmount" value="${salesOrder.amount }" type="text" size="30" onchange="fieldChanged(this);" /></td>
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
											<td><select id="selectOption" name="selectOption" onchange="changeDisplay(this.value)" class="validate[required] text-input">
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
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;" class="right_content">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '订单明细',
									modal : true,
									width : 725,
									height : 350,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#saleOrderItemForm').validationEngine('validate')) {
												$.post('${vix}/drp/storeSalesrecordAction!saveOrUpdateSaleOrderItem.action', {
												'id' : $("#id").val(),
												'saleOrderItem.id' : $("#saleOrderItemId").val(),
												'saleOrderItem.item.id' : $("#itemId").val(),
												'saleOrderItem.itemCode' : $("#itemCode").val(),
												'saleOrderItem.price' : $("#itemprice").val(),
												'saleOrderItem.amount' : $("#saleOrderItemAmount").val()
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
							$('#saleOrderItem').datagrid({
							url : '${vix}/drp/storeSalesrecordAction!getSaleOrderItemJson.action?id=${salesOrder.id}',
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
							field : 'price',
							title : '单价',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
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
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/drp/storeSalesrecordAction!goSaveSaleOrderItem.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#saleOrderItem').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/drp/storeSalesrecordAction!goSaveSaleOrderItem.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#saleOrderItem').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#saleOrderItem').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#saleOrderItem').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/drp/storeSalesrecordAction!deleteSaleOrderItemById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="saleOrderItem"></table>
						</div>
					</div>
				</div>
				<div class="clearfix" style="background: #FFF; position: relative;" id="selectOptionId2">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;" class="right_content">
						<div style="padding: 8px;">
							<table id="so" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,fitColumns: true, toolbar: '#dlAddressTb',url: '${vix}/drp/storeSalesrecordAction!getSaleOrderItemJson.action?id=${salesOrder.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'itemCode',width:100,align:'center',editor:'text'">商品编码</th>
										<th data-options="field:'specification',width:100,align:'center',editor:'text'">规格型号</th>
										<th data-options="field:'price',width:100,align:'center',editor:'numberbox'">单价</th>
										<th data-options="field:'unit',width:100,align:'center',editor:'text'">单位</th>
										<th data-options="field:'amount',width:100,align:'center',editor:'numberbox'">数量</th>
										<th data-options="field:'netTotal',width:100,align:'center',editor:'numberbox'">金额</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /> </span> </span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /> </span> </span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /> </span> </span> </a>
							</div>
							<script type="text/javascript">
								$('#so').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#so').datagrid('validateRow', editIndexDlAddress)) {
										$('#so').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#so').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#so').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#so').datagrid('appendRow', {});
										editIndexDlAddress = $('#so').datagrid('getRows').length - 1;
										$('#so').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#so').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#so').datagrid('acceptChanges');
									}
								}
								function saveOrUpdateStockRecordLines() {
									$.post('${vix}/drp/storeSalesrecordAction!saveSaleOrderItem.action', {
									'id' : $("#id").val(),
									'itemcode' : $("#itemcode").val()
									}, function(result) {
										showMessage(result);
										setTimeout("", 1000);
										$('#so').datagrid("reload");
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