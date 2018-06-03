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
	pager("start", "${vix}/inventory/productAssemblyAction!goItemListContent.action?name=" + name, 'itemPriceManage');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#orderOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/inventory/productAssemblyAction!goItemListContent.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'itemPriceManage');
	}

	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/inventory/productAssemblyAction!goItemListContent.action?name=" + name + '&companyCode=' + $("#companyCode").val(), 'itemPriceManage');
	}
	function chooseItemForPrice(id, code, name, price, measureUnit, skuCode, specification, quantity) {
		$("#itemCode").val(code);
		$("#itemName").val(name);
		$("#skuCode").val(skuCode);
		$("#itemmasterUnit").val(measureUnit);
		$("#itemspecification").val(specification);
		$("#itemprice").val(price);
		$("#itemquantity").val(quantity);
	}
	function addSalesOrderItem() {
		if ($('#subinventoryCurrentStockForm').validationEngine('validate')) {
			$.post('${vix}/inventory/productAssemblyAction!saveOrUpdateItem.action', {
			'id' : $("#id").val(),
			'subinventoryCurrentStock.id' : $("#oiId").val(),
			'subinventoryCurrentStock.itemcode' : $("#itemCode").val(),
			'subinventoryCurrentStock.itemname' : $("#itemName").val(),
			'subinventoryCurrentStock.skuCode' : $("#skuCode").val(),
			'subinventoryCurrentStock.specification' : $("#itemspecification").val(),
			'subinventoryCurrentStock.price' : $("#itemprice").val(),
			'subinventoryCurrentStock.masterUnit' : $("#itemmasterUnit").val(),
			'subinventoryCurrentStock.quantity' : $("#itemquantity").val(),
			'subinventoryCurrentStock.bindingNumber' : $("#bindingNumber").val()
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
		$("#itemCode").val('');
		$("#itemName").val('');
		$("#skuCode").val('');
		$("#itemmasterUnit").val('');
		$("#itemspecification").val('');
		$("#itemquantity").val('');
		$("#itemprice").val('');
	}
	function searchForContent() {
		loadName();
		pager("start", "${vix}/inventory/productAssemblyAction!goItemListContent.action?name=" + name, 'itemPriceManage');
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
				<div id="itemPriceManage" class="table"></div>
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
					<s:hidden id="oiId" name="oiId" value="%{subinventoryCurrentStock.id}" theme="simple" />
					<s:hidden id="itemquantity" name="itemquantity" value="%{subinventoryCurrentStock.quantity}" theme="simple" />
					<div class="box order_table" style="line-height: normal;">
						<table>
							<tr height="40">
								<th>商品编码:&nbsp;</th>
								<td><input id="itemCode" name="itemCode" value="${subinventoryCurrentStock.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>商品名称:&nbsp;</th>
								<td><input id="itemName" name="itemName" value="${subinventoryCurrentStock.itemname}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="40">
								<th>规格型号:&nbsp;</th>
								<td><input id="itemspecification" name="itemspecification" value="${subinventoryCurrentStock.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>SKU编码:&nbsp;</th>
								<td><input id="skuCode" name="skuCode" value="${subinventoryCurrentStock.skuCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>价格:&nbsp;</th>
								<td><input id="itemprice" name="itemprice" value="${subinventoryCurrentStock.price}" type="text" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>单位:&nbsp;</th>
								<td><input id="itemmasterUnit" name="itemmasterUnit" value="${subinventoryCurrentStock.itemmasterUnit}" type="text" /></td>
							</tr>
							<tr height="40">
								<th>数量:&nbsp;</th>
								<td><input id="bindingNumber" name="bindingNumber" value="${subinventoryCurrentStock.bindingNumber}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
							</tr>
						</table>
					</div>
					<div style="padding-left: 150px; padding-top: 15px;">
						<s:if test="subinventoryCurrentStock.id > 0">
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