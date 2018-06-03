<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#nonconformingProductDetailsForm").validationEngine();
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
	function chooseItemForPrice(id, code, name, price, measureUnit, skuCode) {
		$("#itemcode").val(code);
		$("#itemname").val(name);
		$("#specification").val("");
		$("#skuCode").val(skuCode);
		$("#unitcost").val(price);
		$("#unit").val(measureUnit);
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/inventory/unAcceptedProductAction!goSingleListItem.action?name=" + name, 'productDetails');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#orderOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/inventory/unAcceptedProductAction!goSingleListItem.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'productDetails');
	}

	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/inventory/unAcceptedProductAction!goSingleListItem.action?name=" + name + '&companyCode=' + $("#companyCode").val(), 'productDetails');
	}
	function addNonconformingProductDetails() {
		if ($('#nonconformingProductDetailsForm').validationEngine('validate')) {
			$.post('${vix}/inventory/unAcceptedProductAction!saveOrUpdateNonconformingProductDetails.action', {
			'id' : $("#nonconformingProductId").val(),
			'nonconformingProductDetails.id' : $("#nonconformingProductDetailsId").val(),
			'nonconformingProductDetails.itemcode' : $("#itemcode").val(),
			'nonconformingProductDetails.itemname' : $("#itemname").val(),
			'nonconformingProductDetails.specification' : $("#specification").val(),
			'nonconformingProductDetails.skuCode' : $("#skuCode").val(),
			'nonconformingProductDetails.unitcost' : $("#unitcost").val(),
			'nonconformingProductDetails.unit' : $("#unit").val(),
			'nonconformingProductDetails.quantity' : $("#quantity").val(),
			'nonconformingProductDetails.batchcode' : $("#batchcode").val(),
			'nonconformingProductDetails.invShelf.id' : $("#invShelfId").val(),
			'nonconformingProductDetails.invWarehouse.id' : $("#invWarehouseId").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				$('#nonconformingProductDetails').datagrid("reload");
				clearOrderItemContent();
			});
		} else {
			return false;
		}
	}
	function clearOrderItemContent() {
		$("#itemcode").val("");
		$("#itemname").val("");
		$("#specification").val("");
		$("#skuCode").val("");
		$("#unitcost").val("");
		$("#unit").val("");
		$("#quantity").val("");
		$("#invWarehouseId").val("");
		$("#invWarehouseName").val("");
		$("#invShelfId").val("");
		$("#invShelfName").val("");
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
		pager("start", "${vix}/inventory/unAcceptedProductAction!goSingleListItem.action?name=" + name + "&categoryId=" + categoryId, 'productDetails');
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
		<div id="left" style="width: 350px;">
			<div class="left_content">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start','account');"></a></span> <span><a class="previous" onclick="currentPager('previous','account');"></a></span> <em>(<b class="productDetailsInfo"></b> <s:text name='cmn_to' /> <b class="productDetailsTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','account');"></a></span> <span><a class="end" onclick="currentPager('end','account');"></a></span>
					</div>
				</div>
				<div id="productDetails" class="table" style="overflow-y: auto; height: 300px;"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start','account');"></a></span> <span><a class="previous" onclick="currentPager('previous','account');"></a></span> <em>(<b class="productDetailsInfo"></b> <s:text name='cmn_to' /> <b class="productDetailsTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','account');"></a></span> <span><a class="end" onclick="currentPager('end','account');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<form id="nonconformingProductDetailsForm">
					<s:hidden id="nonconformingProductDetailsId" name="nonconformingProductDetailsId" value="%{nonconformingProductDetails.id}" theme="simple" />
					<div class="box order_table" style="line-height: normal;">
						<table>
							<tr height="40">
								<th align="right">编码:&nbsp;</th>
								<td><input id="itemcode" name="itemcode" value="${nonconformingProductDetails.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
								<th align="right">名称:&nbsp;</th>
								<td><input id="itemname" name="itemname" value="${nonconformingProductDetails.itemname}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th align="right">规格:&nbsp;</th>
								<td><input id="specification" name="specification" value="${nonconformingProductDetails.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
								<th align="right">SKU:&nbsp;</th>
								<td><input id="skuCode" name="skuCode" value="${nonconformingProductDetails.skuCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th align="right">价格:&nbsp;</th>
								<td><input id="unitcost" name="unitcost" value="${nonconformingProductDetails.unitcost}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
								<th align="right">单位:&nbsp;</th>
								<td><input id="unit" name="unit" value="${nonconformingProductDetails.unit}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th align="right">数量:&nbsp;</th>
								<td><input id="quantity" name="quantity" value="${nonconformingProductDetails.quantity}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
								<th align="right">批次号:&nbsp;</th>
								<td><input id="batchcode" name="batchcode" value="${nonconformingProductDetails.batchcode}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>仓库:&nbsp;</th>
								<td colspan="3"><input type="hidden" id="invWarehouseId" name="invWarehouseId" value="${nonconformingProductDetails.invWarehouse.id }" /><input type="text" name="invWarehouseName" id="invWarehouseName" value="${nonconformingProductDetails.invWarehouse.name }" size="20" class="validate[required] text-input" /><span style="color: red;">*</span><input
									class="btn" type="button" value="选择" onclick="chooseInvWareHouse();" /></td>
							</tr>
							<tr height="40">
								<th>货位:&nbsp;</th>
								<td colspan="3"><input type="hidden" id="invShelfId" name="invShelfId" value="${nonconformingProductDetails.invShelf.id }" /><input type="text" name="invShelfName" id="invShelfName" value="${nonconformingProductDetails.invShelf.name }" size="20" class="validate[required] text-input" /><span style="color: red;">*</span><input
									class="btn" type="button" value="选择" onclick="chooseShelf();" /></td>
							</tr>
						</table>
					</div>
					<div style="padding-left: 150px; padding-top: 15px;">
						<s:if test="nonconformingProductDetails.id > 0">
							<span class="abtn" style="cursor: pointer; margin-left: 85px;" onclick="addNonconformingProductDetails();"><span>修改</span></span>
						</s:if>
						<s:else>
							<span class="abtn" style="cursor: pointer; margin-left: 85px;" onclick="addNonconformingProductDetails();"><span>保存</span></span>
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
<div id="itemMenuContent" class="itemMenuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf;">
	<ul id="itemCategoryTree" class="ztree" style="margin-top: 0; width: 155px;"></ul>
</div>