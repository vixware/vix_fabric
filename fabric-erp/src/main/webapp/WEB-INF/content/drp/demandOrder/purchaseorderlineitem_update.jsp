<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function chooseItemForPrice(id, itemCode, itemName, masterUnit) {
		$("#itemId").val(id);
		$("#itemCode").val(itemCode);
		$("#itemName").val(itemName);
		$("#unit").val(masterUnit);
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/drp/demandOrderAction!goPurchaseOrderLineItemListContent.action?parentId=" + $('#selectId').val(), 'inventoryCurrentStock');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#orderOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/drp/demandOrderAction!goPurchaseOrderLineItemListContent.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'inventoryCurrentStock');
	}

	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/drp/demandOrderAction!goPurchaseOrderLineItemListContent.action?name=" + name, 'inventoryCurrentStock');
	}
	$("#purchaseOrderLineItemForm").validationEngine();
	function addSalesOrderItem() {
		if ($('#purchaseOrderLineItemForm').validationEngine('validate')) {
			$.post('${vix}/drp/demandOrderAction!saveOrUpdatePurchaseOrderLineItem.action', {
			'purchaseOrderLineItem.id' : $("#purchaseOrderLineItemId").val(),
			'purchaseOrderLineItem.purchaseOrder.id' : $("#purchaseOrderId").val(),
			'purchaseOrderLineItem.itemCode' : $("#itemCode").val(),
			'purchaseOrderLineItem.itemName' : $("#itemName").val(),
			'purchaseOrderLineItem.unit' : $("#unit").val(),
			'purchaseOrderLineItem.amount' : $("#amount").val(),
			'purchaseOrderLineItem.price' : $("#price").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				clearOrderItemContent();
			});
		} else {
			return false;
		}
	}
	function clearOrderItemContent() {
		$("#purchaseOrderLineItemId").val('');
		$("#itemCode").val('');
		$("#itemName").val('');
		$("#unit").val('');
		$("#amount").val('');
		$("#price").val('');
	}
	function searchForContent() {
		loadName();
		pager("start", "${vix}/drp/demandOrderAction!goPurchaseOrderLineItemListContent.action?name=" + name, 'inventoryCurrentStock');
	}
	function getPrice(a) {
		$.ajax({
		url : '${vix}/drp/channelPriceAction!getPrice.action?itemId=' + $("#itemId").val() + "&channelDistributorId=" + $('#selectId').val() + "&count=" + a.value,
		cache : false,
		success : function(json) {
			$("#price").val(json);
		}
		});
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
		<div id="left" style="width: 500px;">
			<div class="left_content">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start','account');"></a></span> <span><a class="previous" onclick="currentPager('previous','account');"></a></span> <em>(<b class="inventoryCurrentStockInfo"></b> <s:text name='cmn_to' /> <b class="inventoryCurrentStockTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','account');"></a></span> <span><a class="end" onclick="currentPager('end','account');"></a></span>
					</div>
				</div>
				<div id="inventoryCurrentStock"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start','account');"></a></span> <span><a class="previous" onclick="currentPager('previous','account');"></a></span> <em>(<b class="inventoryCurrentStockInfo"></b> <s:text name='cmn_to' /> <b class="inventoryCurrentStockTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','account');"></a></span> <span><a class="end" onclick="currentPager('end','account');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<form id="purchaseOrderLineItemForm">
					<s:hidden id="purchaseOrderId" name="purchaseOrderId" value="%{purchaseOrderLineItem.purchaseOrder.id}" theme="simple" />
					<s:hidden id="purchaseOrderLineItemId" name="purchaseOrderLineItemId" value="%{purchaseOrderLineItem.id}" theme="simple" />
					<s:hidden id="itemId" name="itemId" theme="simple" />
					<s:hidden id="selectId" name="selectId" value="%{parentId}" theme="simple" />
					<div class="box order_table" style="line-height: normal;">
						<table>
							<tr height="40">
								<th>商品编码:&nbsp;</th>
								<td><input id="itemCode" name="itemCode" value="${purchaseOrderLineItem.itemCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>商品名称:&nbsp;</th>
								<td><input id="itemName" name="itemName" value="${purchaseOrderLineItem.itemName}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="40">
								<th>单位:&nbsp;</th>
								<td><input id="unit" name="unit" value="${purchaseOrderLineItem.unit}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>数量:&nbsp;</th>
								<td><input id="amount" name="amount" value="${purchaseOrderLineItem.amount}" type="text" class="validate[required,custom[number]] text-input" onchange="getPrice(this)" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>价格:&nbsp;</th>
								<td><input id="price" name="price" value="${purchaseOrderLineItem.price}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
							</tr>
						</table>
					</div>
					<div style="padding-left: 120px; padding-top: 15px;">
						<span class="abtn" style="cursor: pointer;" onclick="addSalesOrderItem();"><span>保存</span></span> <span class="abtn" style="cursor: pointer;" onclick="clearOrderItemContent();"><span>清空</span></span>
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