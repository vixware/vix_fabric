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
			$.post('${vix}/inventory/productDisassemblyAction!saveOrUpdate.action', {
			'inventoryCurrentStock.id' : $("#id").val(),
			'inventoryCurrentStock.quantity' : $("#quantity").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/productDisassemblyAction!goList.action');
			});
		}
	}
	$("#order").validationEngine();

	function chooseItem() {
		$.ajax({
		url : '${vix}/inventory/productDisassemblyAction!goInventoryCurrentStock.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 960,
			height : 580,
			title : "选择${vv:varView("vix_mdm_item")}",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != '') {
						$.ajax({
						url : '${vix}/inventory/productDisassemblyAction!getItemListJson.action?inventoryCurrentStockId=' + returnValue,
						cache : false,
						success : function(json) {
							var obj = eval(json);
							$("#id").val(obj.id);
							$("#itemcode").val(obj.itemcode);
							$("#itemname").val(obj.itemname);
							$("#specification").val(obj.specification);
							$("#price").val(obj.price);
							$("#masterUnit").val(obj.masterUnit);
							$("#quantity").val(obj.quantity);
							loadproductDisassembly('${vix}/inventory/productDisassemblyAction!getItemJson.action?id=' + obj.id);
						}
						});
					} else {
						asyncbox.success("请选择${vv:varView("vix_mdm_item")}!", "<s:text name='vix_message'/>");
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
				<li><a href="#"><img src="${vix}/common/img/inv_productDisassembly.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">库存管理 </a></li>
				<li><a href="#">商品拆装管理业务 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/productDisassemblyAction!goList.action?pageNo=${pageNo}');">商品拆装</a></li>
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
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/inventory/productDisassemblyAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>商品信息</b></strong>
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
											<td><input id="itemcode" name="itemcode" value="${inventoryCurrentStock.itemcode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span><input class="btn" type="button" value="选择" onclick="chooseItem();" /></td>
											<th>商品名称：</th>
											<td><input id="itemname" name="itemname" value="${inventoryCurrentStock.itemname }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>规格型号：</th>
											<td><input id="specification" name="specification" value="${inventoryCurrentStock.specification }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>销售价格：</th>
											<td><input id="price" name="price" value="${inventoryCurrentStock.price}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>拆装数量：</th>
											<td><input id="quantity" name="quantity" value="${inventoryCurrentStock.quantity}" type="text" size="30" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<th>单位：</th>
											<td><input id="masterUnit" name="masterUnit" value="${inventoryCurrentStock.masterUnit}" type="text" size="30" /></td>
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
							$(function() {
								loadproductDisassembly('${vix}/inventory/productDisassemblyAction!getItemJson.action?id=' + $("#id").val());
							});
							function loadproductDisassembly(url) {
								$('#dlAddress2').datagrid({
								url : url,
								width : 'auto',
								height : 450,
								pagination : true,
								rownumbers : true,
								sortOrder : 'desc',
								/* fitColumns : true, */
								striped : true,
								frozenColumns : [ [ {
								field : 'id',
								title : '编号',
								width : 60,
								hidden : true,
								align : 'left'
								}, {
								field : 'itemcode',
								title : '商品编码',
								width : 200,
								align : 'left'
								}, {
								field : 'itemname',
								title : '商品名称',
								width : 500,
								align : 'left'
								} ] ],
								columns : [ [ {
								field : 'specification',
								title : '规格型号',
								width : 100,
								align : 'left'
								}, {
								field : 'masterUnit',
								title : '单位',
								width : 100,
								align : 'left'
								}, {
								field : 'bindingNumber',
								title : '绑定数量',
								width : 100,
								align : 'left',
								editor : 'numberbox',
								required : 'true'
								}, {
								field : 'quantity',
								title : '库存数量',
								width : 100,
								align : 'left',
								editor : 'numberbox',
								required : 'true'
								}, {
								field : 'price',
								title : '单价',
								width : 100,
								align : 'left',
								editor : 'numberbox',
								required : 'true'
								} ] ]
								});
							}
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
