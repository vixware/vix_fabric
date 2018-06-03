<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#saleOrderItemForm").validationEngine();
	var itemCategorySetting = {
	view : {
		dblClickExpand : false
	},
	async : {
	enable : true,
	url : "${vix}/inventory/inboundWarehouseAction!findTreeToJson.action",
	autoParam : [ "id", "name=n", "level=lv" ]
	},
	callback : {
		onClick : onClick
	}
	};
	function onClick(e, treeId, treeNode) {
		$("#itemCategoryId").attr("value", treeNode.id);
		$("#itemCategory").attr("value", treeNode.name);
		hideMenu();
		searchForContent();
	}
	function showMenu() {
		$("#itemMenuContent").css({
		left : 70 + "px",
		top : 76 + "px"
		}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#itemMenuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "itemMenuContent" || $(event.target).parents("#itemMenuContent").length > 0)) {
			hideMenu();
		}
	}
	$.fn.zTree.init($("#itemCategoryTree"), itemCategorySetting);

	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function chooseItemForPrice(id, code, name, price, measureUnit, skuCode, specification) {
		$("#itemCode").val(code);
		$("#skuCode").val(skuCode);
		$("#itemName").val(name);
		$("#unit").val(measureUnit);
		$("#quantity").val("1");
		$("#specification").val(specification);
		$("#unitcost").val(price);
		$.ajax({
		url : '${vix}/inventory/inboundWarehouseAction!getBatchCode.action?itemCode=' + code,
		cache : false,
		success : function(json) {
			$("#batchcode").val(json);
		}
		});
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/inventory/inboundWarehouseAction!goListItemList.action?name=" + name, 'itemPriceManage');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#orderOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/inventory/inboundWarehouseAction!goListItemList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'itemPriceManage');
	}

	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/inventory/inboundWarehouseAction!goListItemList.action?name=" + name + '&companyCode=' + $("#companyCode").val(), 'itemPriceManage');
	}
	function addSalesOrderItem() {
		if ($('#saleOrderItemForm').validationEngine('validate')) {
			$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdateWimStockRecordLines.action', {
			'id' : $("#id").val(),
			'stockRecordLines.id' : $("#oiId").val(),
			'stockRecordLines.itemcode' : $("#itemCode").val(),
			'stockRecordLines.itemname' : $("#itemName").val(),
			'stockRecordLines.specification' : $("#specification").val(),
			'stockRecordLines.unitcost' : $("#unitcost").val(),
			'stockRecordLines.quantity' : $("#quantity").val(),
			'stockRecordLines.suppliercode' : $("#suppliercode").val(),
			'stockRecordLines.unit' : $("#unit").val(),
			'stockRecordLines.skuCode' : $("#skuCode").val(),
			'stockRecordLines.batchcode' : $("#batchcode").val(),
			'stockRecordLines.massunitEndTime' : $("#massunitEndTime").val(),
			'stockRecordLines.invShelf.id' : $("#invShelfId").val(),
			'stockRecordLines.supplier.id' : $("#supplierId").val(),
			'stockRecordLines.invWarehouse.id' : $("#invWarehouseId").val(),
			'stockRecordLines.measureUnit.id' : $("#measureUnitId").val(),
			'isDisassembly' : $(":checkbox[name=isDisassembly][checked]").val(),
			'disassemblyItemCode' : $("#disassemblyItemCode").val(),
			'disassemblyNumber' : $("#disassemblyNumber").val(),
			'otherMeasureUnit' : $("#otherMeasureUnit").val()
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
		$("#itemCode").val("");
		$("#itemName").val("");
		$("#quantity").val("");
		$("#specification").val("");
		$("#skuCode").val("");
		$("#unitcost").val("");
		$("#unit").val("");
		$("#suppliercode").val("");
		$("#massunitEndTime").val("");
		$("#batchcode").val("");
		$("#giftJson").val("");
		$("#invShelfId").val("");
		$("#invShelfName").val("");
		$("#invWarehouseId").val("");
		$("#invWarehouseName").val("");
	}
	function chooseSpecification(code) {
		var spec = "";
		$("input[type=radio][id^='" + code + "_']:checked").each(function() {
			spec += $(this).val();
		});
		$("#specification").val(spec);
	}
	function searchForContent() {
		loadName();
		var categoryId = $("#itemCategoryId").val();
		pager("start", "${vix}/inventory/inboundWarehouseAction!goListItemList.action?name=" + name + "&categoryId=" + categoryId, 'itemPriceManage');
	}
	function reset() {
		$("#itemCategoryId").val("");
		$("#itemCategory").val("");
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
						setWarehouse(result[0]);
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
	function setWarehouse(shelfId) {
		$.ajax({
		url : '${vix}/inventory/warehouseAction!loadWarehouseByShelf.action?shelfId=' + shelfId,
		cache : false,
		success : function(returnValue) {
			if (returnValue != undefined) {
				var result = returnValue.split(",");
				$("#invWarehouseId").val(result[0]);
				$("#invWarehouseName").val(result[1]);
			}
		}
		});
	}
</script>
<style>
.nt_title_1 {
	background: none repeat scroll 0 0 #fafafa;
	display: inline;
	float: left;
	margin: -30px 0 0;
	padding: 0 5px;
}
</style>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label> 分类 <input id="itemCategoryId" type="hidden" value="" /> <input id="itemCategory" type="text" readonly="readonly" onfocus="showMenu(); return false;" class="int" value="" />
			</label> <label>名称<input type="text" class="int" id="nameS" onchange="searchForContent();"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="reset();" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" style="width: 300px;">
			<div class="left_content">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start','account');"></a></span> <span><a class="previous" onclick="currentPager('previous','account');"></a></span> <em>(<b class="itemPriceManageInfo"></b> <s:text name='cmn_to' /> <b class="itemPriceManageTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','account');"></a></span> <span><a class="end" onclick="currentPager('end','account');"></a></span>
					</div>
				</div>
				<div id="itemPriceManage" class="table" style="overflow-y: auto; height: 400px;"></div>
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
				<form id="saleOrderItemForm">
					<s:hidden id="oiId" name="oiId" value="%{stockRecordLines.id}" theme="simple" />
					<div class="box order_table" style="line-height: normal;">
						<table>
							<tr height="40">
								<th>商品编码:</th>
								<td><input id="itemCode" name="itemCode" value="${stockRecordLines.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
								<th>商品名称:</th>
								<td><input id="itemName" name="itemName" value="${stockRecordLines.itemname}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="40">
								<th>规格型号:</th>
								<td><input id="specification" name="specification" value="${stockRecordLines.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
								<th>价格:</th>
								<td><input id="unitcost" name="unitcost" value="${stockRecordLines.unitcost}" type="text" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>数量:</th>
								<td><input id="quantity" name="quantity" value="${stockRecordLines.quantity}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
								<th>货位:</th>
								<td><input type="hidden" id="invShelfId" name="invShelfId" value="${stockRecordLines.invShelf.id }" /><input type="text" name="invShelfName" id="invShelfName" value="${stockRecordLines.invShelf.name }" size="20" class="validate[required] text-input" /><input class="btn" type="button" value="选择" onclick="chooseShelf();" /><span
									style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>单位:</th>
								<td><s:select id="measureUnitId" headerKey="-1" headerValue="--请选择--" list="%{measureUnitList}" listValue="name" listKey="id" multiple="false" theme="simple">
									</s:select></td>
								<th>仓库:</th>
								<td><input type="hidden" id="invWarehouseId" name="invWarehouseId" value="${stockRecordLines.invWarehouse.id }" /><input type="text" name="invWarehouseName" id="invWarehouseName" value="${stockRecordLines.invWarehouse.name }" size="20" class="validate[required] text-input" /><input class="btn" type="button" value="选择"
									onclick="chooseInvWareHouse();" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>SKU编码:</th>
								<td><input id="skuCode" name="skuCode" value="${stockRecordLines.skuCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
								<th>供应商:</th>
								<td><input type="hidden" id="supplierId" name="supplierId" value="${stockRecordLines.supplier.id }" /> <input type="text" name="supplierName" id="supplierName" value="${stockRecordLines.supplier.name }" size="20" class="text-input" /> <input class="btn" type="button" value="选择" onclick="chooseSupplier();" /></td>
							</tr>
							<tr height="40">
								<s:if test='inventoryParameters.isBatch=="1"'>
									<th>批次号:</th>
									<td><input id="batchcode" name="batchcode" value="${stockRecordLines.batchcode}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								</s:if>
								<s:if test='inventoryParameters.isShelfLife=="1"'>
									<th>到期日:</th>
									<td><input id="massunitEndTime" name="massunitEndTime" value="<s:date name="%{stockRecordLines.massunitEndTime}" format="yyyy-MM-dd"/>" type="text" class="validate[required] text-input" /><img onclick="showTime('massunitEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
										style="color: red;">*</span></td>
								</s:if>
							</tr>

						</table>
					</div>
					<div class="box order_table" style="line-height: normal;">
						<table>
							<tr height="40">
								<th><input type="checkbox" name=isDisassembly id="isDisassembly" value="1" />&nbsp;是否拆装</th>
							</tr>
							<tr height="40">
								<th>新商品编码:</th>
								<td><input id="disassemblyItemCode" name="disassemblyItemCode" type="text" /></td>
								<th>拆装数量:</th>
								<td><input id="disassemblyNumber" name="disassemblyNumber" type="text" /></td>
							</tr>
							<tr height="40">
								<th>拆装后单位:</th>
								<td><s:select id="otherMeasureUnit" headerKey="-1" headerValue="--请选择--" list="%{measureUnitList}" listValue="name" listKey="id" multiple="false" theme="simple">
									</s:select></td>
							</tr>
						</table>
					</div>
					<div style="padding-left: 150px; padding-top: 15px;">
						<span class="abtn" style="cursor: pointer; margin-left: 120px;" onclick="addSalesOrderItem();"><span>保存</span></span> <span class="abtn" style="cursor: pointer;" onclick="clearOrderItemContent();"><span>清空</span></span>
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
<div id="itemMenuContent" class="itemMenuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf;">
	<ul id="itemCategoryTree" class="ztree" style="margin-top: 0; width: 155px;"></ul>
</div>