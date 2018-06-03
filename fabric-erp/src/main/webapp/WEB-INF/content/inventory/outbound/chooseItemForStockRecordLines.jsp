<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#saleOrderItemForm").validationEngine();

	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function chooseItemForPrice(id, code, name, price, invWarehouseId, invWarehouseName) {
		$('#masterInventoryCurrentStockId').val(id);
		$("#itemCode").val(code);
		$("#itemName").val(name);
		$("#quantity").val("1");
		$("#specification").val("");
		$("#unitcost").val(price);
		$("#invWarehouseId").val(invWarehouseId);
		$("#invWarehouseName").val(invWarehouseName);
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/inventory/outBoundAction!goListItemList.action?name=" + name, 'inv');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#orderOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/inventory/outBoundAction!goListItemList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'inv');
	}

	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/inventory/outBoundAction!goListItemList.action?name=" + name, 'inv');
	}
	function addSalesOrderItem() {
		if ($('#saleOrderItemForm').validationEngine('validate')) {
			$.post('${vix}/inventory/outBoundAction!saveOrUpdateWimStockRecordLines.action', {
			'id' : $("#id").val(),
			'stockRecordLines.id' : $("#oiId").val(),
			'stockRecordLines.itemcode' : $("#itemCode").val(),
			'stockRecordLines.itemname' : $("#itemName").val(),
			'stockRecordLines.specification' : $("#specification").val(),
			'stockRecordLines.unitcost' : $("#unitcost").val(),
			'stockRecordLines.quantity' : $("#quantity").val(),
			'stockRecordLines.skuCode' : $("#skuCode").val(),
			'stockRecordLines.batchcode' : $("#batchcode").val(),
			'stockRecordLines.invWarehouse.id' : $("#invWarehouseId").val()
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
		$('#masterInventoryCurrentStockId').val('');
		$("#itemCode").val('');
		$("#itemName").val('');
		$("#skuCode").val('');
		$("#quantity").val('');
		$("#specification").val('');
		$("#unitcost").val('');
		$("#giftJson").val('');
	}
	function searchForContent() {
		loadName();
		pager("start", "${vix}/inventory/outBoundAction!goListItemList.action?name=" + name, 'inv');
	}
	function reset() {
		$("#nameS").val("");
	}
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

	//查询各批次库存数据
	function choosebatch(id) {
		$.ajax({
		url : '${vix}/inventory/outBoundAction!goInventoryCurrentStockList.action?masterInventoryCurrentStockId=' + id,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 550,
			height : 385,
			title : "选择批次",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#inventoryCurrentStockId").val(result[0]);
						$("#batchcode").val(result[1]);
					} else {
						asyncbox.success("请选择批次信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
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
		<div id="left" style="width: 550px;">
			<div class="left_content">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="invInfo"></b> <s:text name='cmn_to' /> <b class="invTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="inv" class="table"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="invInfo"></b> <s:text name='cmn_to' /> <b class="invTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<form id="saleOrderItemForm">
					<s:hidden id="oiId" name="oiId" value="%{stockRecoXrdLines.id}" theme="simple" />
					<s:hidden id="masterInventoryCurrentStockId" name="masterInventoryCurrentStockId" theme="simple" />
					<div class="box order_table">
						<table>
							<tr height="40">
								<th>商品编码:&nbsp;</th>
								<td><input id="itemCode" name="itemCode" value="${stockRecordLines.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>商品名称:&nbsp;</th>
								<td><input id="itemName" name="itemName" value="${stockRecordLines.itemname}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>规格型号:&nbsp;</th>
								<td><input id="specification" name="specification" value="${stockRecordLines.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>SKU编码:&nbsp;</th>
								<td><input id="skuCode" name="skuCode" value="${stockRecordLines.skuCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>数量:&nbsp;</th>
								<td><input id="quantity" name="quantity" value="${stockRecordLines.quantity}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>价格:&nbsp;</th>
								<td><input id="unitcost" name="unitcost" value="${stockRecordLines.unitcost}" type="text" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>仓库:&nbsp;</th>
								<td><input type="hidden" id="invWarehouseId" name="invWarehouseId" value="${stockRecordLines.invWarehouse.id }" /><input type="text" name="invWarehouseName" id="invWarehouseName" value="${stockRecordLines.invWarehouse.name }" size="20" class="validate[required] text-input" /><span style="color: red;">*</span><input class="btn"
									type="button" value="选择" onclick="chooseInvWareHouse();" /></td>
							</tr>
							<s:if test='inventoryParameters.isBatch=="1"'>
								<tr height="40">
									<th>批次:&nbsp;</th>
									<td><input type="hidden" id="inventoryCurrentStockId" name="inventoryCurrentStockId" /><input type="text" name="batchcode" id="batchcode" size="20" class="validate[required] text-input" /><span style="color: red;">*</span><input class="btn" type="button" value="选择" onclick="choosebatch($('#masterInventoryCurrentStockId').val());" /></td>
								</tr>
							</s:if>
						</table>
					</div>
					<div style="padding-left: 150px; padding-top: 15px;">
						<s:if test="stockRecordLines.id > 0">
							<span class="abtn" style="cursor: pointer;" onclick="addSalesOrderItem();"><span>修改明细</span></span>
						</s:if>
						<s:else>
							<span class="abtn" style="cursor: pointer;" onclick="addSalesOrderItem();"><span>保存明细</span></span>
						</s:else>
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