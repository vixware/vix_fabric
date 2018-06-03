<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateOrder() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/groupInventoryCurrentStockAction!saveOrUpdate.action', {
			'groupInventoryCurrentStock.id' : $("#id").val(),
			'groupInventoryCurrentStock.itemcode' : $("#itemcode").val(),
			'groupInventoryCurrentStock.itemname' : $("#itemname").val(),
			'groupInventoryCurrentStock.specification' : $("#specification").val(),
			'groupInventoryCurrentStock.price' : $("#price").val(),
			'groupInventoryCurrentStock.masterUnit' : $("#groupInventoryCurrentStockMasterUnit").val(),
			'updateField' : updateField,
			'groupInventoryCurrentStock.invWarehouse.id' : $("#warehouseId").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/groupInventoryCurrentStockAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#order").validationEngine();
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
				<li><a href="#"><img src="${vix}/common/img/inv_productAssembly.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理 </a></li>
				<li><a href="#">商品管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/groupInventoryCurrentStockAction!goList.action?pageNo=${pageNo}');">虚拟商品组合管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${groupInventoryCurrentStock.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/inventory/groupInventoryCurrentStockAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>商品信息</b> </strong>
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
											<th>商品编码：</th>
											<td><input id="itemcode" name="itemcode" value="${groupInventoryCurrentStock.itemcode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>商品名称：</th>
											<td><input id="itemname" name="itemname" value="${groupInventoryCurrentStock.itemname }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>规格型号：</th>
											<td><input id="specification" name="specification" value="${groupInventoryCurrentStock.specification }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>单价：</th>
											<td><input id="price" name="price" value="${groupInventoryCurrentStock.price}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>单位：</th>
											<td><input id="groupInventoryCurrentStockMasterUnit" name="groupInventoryCurrentStockMasterUnit" value="${groupInventoryCurrentStock.masterUnit}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>仓库：</th>
											<td><input type="hidden" id="warehouseId" name="warehouseId" value="${groupInventoryCurrentStock.invWarehouse.id }" onchange="fieldChanged(this);" /><input type="text" name="warehouseName" id="warehouseName" value="${groupInventoryCurrentStock.invWarehouse.name }" class="validate[required] text-input" size="30" /><span
												style="color: red;">*</span><input class="btn" type="button" value="选择" onclick="chooseWarehouse();" /></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />子商品信息</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '商品明细',
									modal : true,
									width : 750,
									height : 550,
									html : html,
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							$('#dlAddress2')
									.datagrid({
									url : '${vix}/inventory/groupInventoryCurrentStockAction!getInventoryCurrentStockJson.action?id=${groupInventoryCurrentStock.id}',
									width : 'auto',
									height : 450,
									pagination : true,
									rownumbers : true,
									sortOrder : 'desc',
									fitColumns : true,
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
									width : 200,
									align : 'center'
									} ] ],
									columns : [ [ {
									field : 'specification',
									title : '规格型号',
									width : 200,
									align : 'center'
									}, {
									field : 'masterUnit',
									title : '单位',
									width : 200,
									align : 'center'
									}, {
									field : 'quantity',
									title : '库存数量',
									width : 200,
									align : 'right',
									editor : 'numberbox',
									required : 'true'
									}, {
									field : 'groupAmount',
									title : '组合数量',
									width : 200,
									align : 'right',
									editor : 'numberbox',
									required : 'true'
									}, {
									field : 'price',
									title : '单价',
									width : 200,
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
										saveDeliveryAddress('${vix}/inventory/groupInventoryCurrentStockAction!goInventoryCurrentStockList.action?groupInventoryCurrentStockId=${groupInventoryCurrentStock.id}');
									}
									}, '-', {
									id : 'btnedit',
									text : '修改',
									iconCls : 'icon-edit',
									handler : function() {
										var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
										if (row) {
											saveDeliveryAddress('${vix}/inventory/groupInventoryCurrentStockAction!goInventoryCurrentStockList.action?id=' + row.id);
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
											url : '${vix}/inventory/groupInventoryCurrentStockAction!deleteItemById.action?id=' + rows[i].id,
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
		<!--submenu-->
	</form>
</div>