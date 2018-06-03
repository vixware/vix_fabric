<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdate() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/productAssemblyAction!saveOrUpdate.action', {
			'inventoryCurrentStock.id' : $("#id").val(),
			'inventoryCurrentStock.invWarehouse.id' : $("#invWarehouseId").val(),
			'inventoryCurrentStock.specification' : $("#specification").val(),
			'inventoryCurrentStock.invShelf.id' : $("#invShelfId").val(),
			'inventoryCurrentStock.masterUnit' : $("#masterUnit").val(),
			'inventoryCurrentStock.quantity' : $("#quantity").val(),
			'inventoryCurrentStock.itemcode' : $("#itemcode").val(),
			'inventoryCurrentStock.itemname' : $("#itemname").val(),
			'inventoryCurrentStock.price' : $("#price").val(),
			'inventoryCurrentStock.measureUnit.id' : $("#measureUnitId").val(),
			'item.id' : $("#itemId").val(),
			'updateField' : updateField
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/productAssemblyAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#order").validationEngine();
	function chooseInvWareHouse() {
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
						$("#invWarehouseId").val(result[0]);
						$("#invWarehouseName").val(result[1]);
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
	function chooseShelf() {
		$.ajax({
		url : '${vix}/inventory/warehouseAction!goChooseShelf.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择货位",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#invShelfId").val(result[0]);
						$("#invShelfName").val(result[1]);
					} else {
						asyncbox.success("请选择货位信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_productAssembly.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">库存管理 </a></li>
				<li><a href="#">商品拆装管理业务 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/productAssemblyAction!goList.action?pageNo=${pageNo}');">商品组装</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${inventoryCurrentStock.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdate();" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/inventory/productAssemblyAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
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
											<td><input id="itemcode" name="itemcode" value="${inventoryCurrentStock.itemcode }" type="text" size="35" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>商品名称：</th>
											<td><input id="itemname" name="itemname" value="${inventoryCurrentStock.itemname }" type="text" size="35" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>规格型号：</th>
											<td><input id="specification" name="specification" value="${inventoryCurrentStock.specification }" type="text" size="35" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>单价：</th>
											<td><input id="price" name="price" value="${inventoryCurrentStock.price}" type="text" size="35" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>单位：</th>
											<td><s:select id="measureUnitId" headerKey="-1" headerValue="--请选择--" list="%{measureUnitList}" listValue="name" listKey="id" multiple="false" theme="simple">
												</s:select></td>
											<th>要绑定的数量：</th>
											<td><input id="quantity" name="quantity" value="${inventoryCurrentStock.quantity}" type="text" size="35" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>仓库:&nbsp;</th>
											<td><input type="hidden" id="invWarehouseId" name="invWarehouseId" value="${inventoryCurrentStock.invWarehouse.id }" /><input type="text" name="invWarehouseName" id="invWarehouseName" value="${inventoryCurrentStock.invWarehouse.name }" size="20" class="validate[required] text-input" /><span style="color: red;">*</span><input
												class="btn" type="button" value="选择" onclick="chooseInvWareHouse();" /></td>
											<th>货位:&nbsp;</th>
											<td><input type="hidden" id="invShelfId" name="invShelfId" value="${inventoryCurrentStock.invShelf.id }" /><input type="text" name="invShelfName" id="invShelfName" value="${inventoryCurrentStock.invShelf.name }" size="20" class="validate[required] text-input" /><span style="color: red;">*</span><input class="btn" type="button"
												value="选择" onclick="chooseShelf();" /></td>
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
							$('#dlAddress2').datagrid({
							url : '${vix}/inventory/productAssemblyAction!getItemJson.action?id=${inventoryCurrentStock.id}',
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
							field : 'bindingNumber',
							title : '绑定数量',
							width : 200,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'quantity',
							title : '库存数量',
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
								saveDeliveryAddress('${vix}/inventory/productAssemblyAction!goItemList.action?=${item.id}');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/inventory/productAssemblyAction!goItemList.action?id=' + row.id);
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
									url : '${vix}/inventory/productAssemblyAction!deleteItemById.action?id=' + rows[i].id,
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