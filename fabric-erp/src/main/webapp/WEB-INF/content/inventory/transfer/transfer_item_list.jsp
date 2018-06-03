<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#wimTransvouchitemForm").validationEngine();

	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function chooseItemForPrice(id, itemcode, itemname, skuCode) {
		$("#itemCode").val(itemcode);
		$("#itemName").val(itemname);
		$("#skuCode").val(skuCode);
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/inventory/allocateTransferAction!goInventoryListContent.action?name=" + name, 'inventoryCurrentStock');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#orderOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/inventory/allocateTransferAction!goInventoryListContent.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'inventoryCurrentStock');
	}

	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/inventory/allocateTransferAction!goInventoryListContent.action?name=" + name + '&companyCode=' + $("#companyCode").val(), 'inventoryCurrentStock');
	}
	function addSalesOrderItem() {
		if ($('#wimTransvouchitemForm').validationEngine('validate')) {
			$.post('${vix}/inventory/allocateTransferAction!saveOrUpdateWimTransvouchItem.action', {
			'wimTransvouchitem.id' : $("#wimTransvouchitemId").val(),
			'wimTransvouchitem.wimTransvouch.id' : $("#wimTransvouchId").val(),
			'wimTransvouchitem.itemcode' : $("#itemCode").val(),
			'wimTransvouchitem.itemname' : $("#itemName").val(),
			'wimTransvouchitem.skuCode' : $("#skuCode").val(),
			'wimTransvouchitem.measureUnit.id' : $("#measureUnitId").val(),
			'wimTransvouchitem.tvquantity' : $("#quantity").val(),
			'wimTransvouchitem.invShelf.id' : $("#invShelfId").val(),
			'wimTransvouchitem.invWarehouse.id' : $("#wimTransvouchitemInvWarehouseId").val()
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
		$("#itemCode").val('');
		$("#itemName").val('');
		$("#skuCode").val('');
		$("#measureUnitId").val('');
		$("#quantity").val('');
		$("#wimTransvouchitemInvWarehouseId").val('');
		$("#wimTransvouchitemInvWarehouseName").val('');
		$("#invShelfId").val('');
		$("#invShelfName").val('');
	}
	function searchForContent() {
		loadName();
		pager("start", "${vix}/inventory/allocateTransferAction!goInventoryListContent.action?name=" + name, 'inventoryCurrentStock');
	}
	function reset() {
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
						$("#wimTransvouchitemInvWarehouseId").val(result[0]);
						$("#wimTransvouchitemInvWarehouseName").val(result[1]);
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
		<div id="left" style="width: 475px;">
			<div class="left_content">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start','account');"></a></span> <span><a class="previous" onclick="currentPager('previous','account');"></a></span> <em>(<b class="inventoryCurrentStockInfo"></b> <s:text name='cmn_to' /> <b class="inventoryCurrentStockTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','account');"></a></span> <span><a class="end" onclick="currentPager('end','account');"></a></span>
					</div>
				</div>
				<div id="inventoryCurrentStock" style="overflow-y: auto; height: 300px;"></div>
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
				<form id="wimTransvouchitemForm">
					<s:hidden id="wimTransvouchId" name="wimTransvouchId" value="%{wimTransvouchitem.wimTransvouch.id}" theme="simple" />
					<s:hidden id="wimTransvouchitemId" name="wimTransvouchitemId" value="%{wimTransvouchitem.id}" theme="simple" />
					<div class="box order_table" style="line-height: normal;">
						<table>
							<tr height="40">
								<th>商品编码:&nbsp;</th>
								<td><input id="itemCode" name="itemCode" value="${wimTransvouchitem.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>商品名称:&nbsp;</th>
								<td><input id="itemName" name="itemName" value="${wimTransvouchitem.itemname}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="40">
								<th>SKU编码:&nbsp;</th>
								<td><input id="skuCode" name="skuCode" value="${wimTransvouchitem.skuCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>单位:&nbsp;</th>
								<td><s:select id="measureUnitId" headerKey="-1" headerValue="--请选择--" list="%{measureUnitList}" listValue="name" listKey="id" multiple="false" theme="simple">
									</s:select> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>数量:&nbsp;</th>
								<td><input id="quantity" name="quantity" value="${wimTransvouchitem.tvquantity}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>货位:&nbsp;</th>
								<td><input type="hidden" id="invShelfId" name="invShelfId" value="${wimTransvouchitem.invShelf.id }" /><input type="text" name="invShelfName" id="invShelfName" value="${wimTransvouchitem.invShelf.name }" size="20" /> <input class="btn" type="button" value="选择" onclick="chooseShelf();" /></td>
							</tr>
						</table>
					</div>
					<div style="padding-left: 120px; padding-top: 15px;">
						<s:if test="saleOrderItem.id > 0">
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