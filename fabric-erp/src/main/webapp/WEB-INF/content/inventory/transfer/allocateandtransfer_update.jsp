<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	$("#wimTransvouchForm").validationEngine();
	function saveWimTransvouch(tag) {
		if ($('#wimTransvouchForm').validationEngine('validate')) {
			$.post('${vix}/inventory/allocateTransferAction!saveOrUpdate.action', {
			'wimTransvouch.id' : $("#id").val(),
			'wimTransvouch.code' : $("#code").val(),
			'wimTransvouch.name' : $("#name").val(),
			'wimTransvouch.tvdate' : $("#tvdate").val(),
			'wimTransvouch.outInvWarehouse.id' : $("#outInvWarehouseId").val(),
			'wimTransvouch.inInvWarehouse.id' : $("#inInvWarehouseId").val(),
			'wimTransvouch.allocationPerson' : $("#allocationPerson").val(),
			'wimTransvouch.creator' : $("#creator").val(),
			'updateField' : updateField,
			'wimTransvouch.approvalPerson' : $("#approvalPerson").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				if (tag == 1)
					loadContent('${vix}/inventory/allocateTransferAction!goList.action');
				else
					saveOrUpdate(0);
			});
		}
	}
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
						$("#outInvWarehouseName").val(result[1]);
						$("#outInvWarehouseId").val(result[0]);
					} else {
						$("#outInvWarehouseName").val("");
						$("#outInvWarehouseId").val("");
						asyncbox.success("请选择仓库信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function chooseInWarehouse() {
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
						$("#inInvWarehouseName").val(result[1]);
						$("#inInvWarehouseId").val(result[0]);
					} else {
						$("#inInvWarehouseName").val("");
						$("#inInvWarehouseId").val("");
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
				<li><a href="#"><img src="${vix}/common/img/inv_transfer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/allocateTransferAction!goList.action?pageNo=${pageNo}');"><s:text name="inventory_transfer_business" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${wimTransvouch.id}" />
<div class="content">
	<form id="wimTransvouchForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveWimTransvouch(1)" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a onclick="saveWimTransvouch(2)" href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22"
							title="保存并提交审批" src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/inventory/allocateTransferAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="inventory_transfer" /> </b> </strong>
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
											<th>单号：</th>
											<td><input id="code" name="code" value="${wimTransvouch.code }" type="text" size="40" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>主题：</th>
											<td><input id="name" name="name" value="${wimTransvouch.name }" type="text" size="40" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>调拨人：</th>
											<td><input id="allocationPerson" name="allocationPerson" value="${wimTransvouch.allocationPerson }" type="text" size="40" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>调拨日期：</th>
											<td><input id="tvdate" type="text" size="25" value="<s:date name="%{wimTransvouch.tvdate}" format="yyyy-MM-dd HH:mm:ss"/>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>转出仓库：</th>
											<td><input type="hidden" id="outInvWarehouseId" name="outInvWarehouseId" value="${wimTransvouch.outInvWarehouse.id}" onchange="fieldChanged(this);" /> <input id="outInvWarehouseName" name="outInvWarehouseName" value="${wimTransvouch.outInvWarehouse.name}" type="text" size="40" class="validate[required] text-input" /><input
												class="btn" type="button" value="选仓" onclick="chooseWarehouse();" /><span style="color: red;">*</span></td>
											<th>转入仓库：</th>
											<td><input type="hidden" id="inInvWarehouseId" name="inInvWarehouseId" value="${wimTransvouch.inInvWarehouse.id}" onchange="fieldChanged(this);" /> <input id="inInvWarehouseName" name="inInvWarehouseName" value="${wimTransvouch.inInvWarehouse.name}" type="text" size="40" class="validate[required] text-input" /><input class="btn"
												type="button" value="选仓" onclick="chooseInWarehouse();" /><span style="color: red;">*</span></td>
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
								url : '${vix}/inventory/allocateTransferAction!goInventoryList.action?id=' + id + "&parentId=" + $("#id").val(),
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 900,
									height : 550,
									title : "库存明细",
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
							url : '${vix}/inventory/allocateTransferAction!getWimTransvouchItemJson.action?id=${wimTransvouch.id}',
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
									url : '${vix}/inventory/allocateTransferAction!deleteWimTransvouchItemById.action?id=' + rows[i].id,
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
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>制单人：</th>
											<td><input id="creator" name="creator" value="${wimTransvouch.creator }" type="text" onchange="fieldChanged(this);" size="40" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<th>审批人：</th>
											<td><input id="approvalPerson" name="approvalPerson" value="${wimTransvouch.approvalPerson }" type="text" size="40" onchange="fieldChanged(this);"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
