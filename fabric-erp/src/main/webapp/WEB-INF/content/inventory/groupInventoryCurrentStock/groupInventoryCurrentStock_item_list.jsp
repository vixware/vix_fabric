<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#subinventoryCurrentStockForm").validationEngine();

	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/inventory/groupInventoryCurrentStockAction!goInventoryCurrentStockListContent.action?name=" + name, 'itemPriceManage');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#orderOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/inventory/groupInventoryCurrentStockAction!goInventoryCurrentStockListContent.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'itemPriceManage');
	}

	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/inventory/groupInventoryCurrentStockAction!goInventoryCurrentStockListContent.action?name=" + name + '&companyCode=' + $("#companyCode").val(), 'itemPriceManage');
	}
	function chooseItemForPrice(id, code, name, price, measureUnit, skuCode, specification, quantity) {
		$("#inventoryCurrentStockId").val(id);
		$("#itemCode").val(code);
		$("#itemName").val(name);
		$("#skuCode").val(skuCode);
		$("#masterUnit").val(measureUnit);
		$("#itemprice").val(price);
	}
	function addSalesOrderItem() {
		if ($('#subinventoryCurrentStockForm').validationEngine('validate')) {
			$.post('${vix}/inventory/groupInventoryCurrentStockAction!saveOrUpdateInventoryCurrentStock.action', {
			'groupInventoryCurrentStockId' : $("#groupInventoryCurrentStockId").val(),
			'inventoryCurrentStock.id' : $("#inventoryCurrentStockId").val(),
			'inventoryCurrentStock.itemcode' : $("#itemCode").val(),
			'inventoryCurrentStock.itemname' : $("#itemName").val(),
			'inventoryCurrentStock.skuCode' : $("#skuCode").val(),
			'inventoryCurrentStock.price' : $("#itemprice").val(),
			'inventoryCurrentStock.groupAmount' : $("#groupAmount").val(),
			'inventoryCurrentStock.masterUnit' : $("#masterUnit").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				$('#dlAddress2').datagrid("reload");
				clearOrderItemContent();
			});
		} else {
			return false;
		}
	}
	function clearOrderItemContent() {
		$("#inventoryCurrentStockId").val('');
		$("#itemCode").val('');
		$("#itemName").val('');
		$("#skuCode").val('');
		$("#masterUnit").val('');
		$("#itemprice").val('');
	}
	function searchForContent() {
		loadName();
		pager("start", "${vix}/inventory/groupInventoryCurrentStockAction!goInventoryCurrentStockListContent.action?name=" + name, 'itemPriceManage');
	}
	function reset() {
		$("#nameS").val("");
	}
</script>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>名称<input type="text" class="int" id="nameS" onchange="searchForContent();"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="reset();" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" style="width: 400px;">
			<div class="left_content">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start','account');"></a></span> <span><a class="previous" onclick="currentPager('previous','account');"></a></span> <em>(<b class="itemPriceManageInfo"></b> <s:text name='cmn_to' /> <b class="itemPriceManageTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','account');"></a></span> <span><a class="end" onclick="currentPager('end','account');"></a></span>
					</div>
				</div>
				<div id="itemPriceManage" class="table" style="overflow-y: auto; height: 300px;"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start','account');"></a></span> <span><a class="previous" onclick="currentPager('previous','account');"></a></span> <em>(<b class="itemPriceManageInfo"></b> <s:text name='cmn_to' /> <b class="itemPriceManageTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','account');"></a></span> <span><a class="end" onclick="currentPager('end','account');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<form id="subinventoryCurrentStockForm">
					<s:hidden id="inventoryCurrentStockId" name="inventoryCurrentStockId" value="%{inventoryCurrentStock.id}" theme="simple" />
					<s:hidden id="groupInventoryCurrentStockId" name="groupInventoryCurrentStockId" value="%{groupInventoryCurrentStockId}" theme="simple" />
					<div class="box order_table" style="line-height: normal;">
						<table>
							<tr height="40">
								<th>商品编码:&nbsp;</th>
								<td><input id="itemCode" name="itemCode" value="${inventoryCurrentStock.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>商品名称:&nbsp;</th>
								<td><input id="itemName" name="itemName" value="${inventoryCurrentStock.itemname}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="40">
								<th>SKU编码:&nbsp;</th>
								<td><input id="skuCode" name="skuCode" value="${inventoryCurrentStock.skuCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>价格:&nbsp;</th>
								<td><input id="itemprice" name="itemprice" value="${inventoryCurrentStock.price}" type="text" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>单位:&nbsp;</th>
								<td><input id="masterUnit" name="masterUnit" value="${inventoryCurrentStock.masterUnit}" type="text" /></td>
							</tr>
							<tr height="40">
								<th>组合数量:&nbsp;</th>
								<td><input id="groupAmount" name="groupAmount" value="${inventoryCurrentStock.groupAmount}" type="text" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
							</tr>
						</table>
					</div>
					<div style="padding-left: 100px; padding-top: 15px;">
						<s:if test="inventoryCurrentStock.id > 0">
							<span class="abtn" style="cursor: pointer;" onclick="addSalesOrderItem();"><span>修改</span></span>
						</s:if>
						<s:else>
							<span class="abtn" style="cursor: pointer;" onclick="addSalesOrderItem();"><span>保存</span></span>
						</s:else>
						<span class="abtn" style="cursor: pointer;" onclick="clearOrderItemContent();"><span>清空</span></span>
					</div>
				</form>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>