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
	url : "${vix}/dtbcenter/orderProcessingAction!findTreeToJson.action",
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
		$("#itemName").val(name);
		$("#skuCode").val(skuCode);
		$("#unit").val(measureUnit);
		$("#quantity").val("1");
		$("#specification").val(specification);
		$("#unitcost").val(price);
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/dtbcenter/orderProcessingAction!goChooseItemListContent.action?name=" + name, 'itemPriceManage');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#orderOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/dtbcenter/orderProcessingAction!goChooseItemListContent.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'itemPriceManage');
	}

	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/dtbcenter/orderProcessingAction!goChooseItemListContent.action?name=" + name + '&companyCode=' + $("#companyCode").val(), 'itemPriceManage');
	}
	function addSalesOrderItem() {
		if ($('#saleOrderItemForm').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/orderProcessingAction!saveOrUpdateSaleOrderItem.action', {
			'id' : $("#id").val(),
			'saleOrderItem.id' : $("#oiId").val(),
			'saleOrderItem.itemcode' : $("#itemCode").val(),
			'saleOrderItem.itemname' : $("#itemName").val(),
			'saleOrderItem.specification' : $("#specification").val(),
			'saleOrderItem.unitcost' : $("#unitcost").val(),
			'saleOrderItem.quantity' : $("#quantity").val(),
			'saleOrderItem.unit' : $("#unit").val(),
			'saleOrderItem.skuCode' : $("#skuCode").val(),
			'saleOrderItem.measureUnit.id' : $("#measureUnitId").val(),
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
		pager("start", "${vix}/dtbcenter/orderProcessingAction!goChooseItemListContent.action?name=" + name + "&categoryId=" + categoryId, 'itemPriceManage');
	}
	function reset() {
		$("#itemCategoryId").val("");
		$("#itemCategory").val("");
		$("#nameS").val("");
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
					<s:hidden id="oiId" name="oiId" value="%{saleOrderItem.id}" theme="simple" />
					<div class="box order_table" style="line-height: normal;">
						<table>
							<tr height="40">
								<th>商品编码:</th>
								<td><input id="itemCode" name="itemCode" value="${saleOrderItem.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
								<th>商品名称:</th>
								<td><input id="itemName" name="itemName" value="${saleOrderItem.itemname}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="40">
								<th>规格型号:</th>
								<td><input id="specification" name="specification" value="${saleOrderItem.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
								<th>价格:</th>
								<td><input id="unitcost" name="unitcost" value="${saleOrderItem.unitcost}" type="text" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>数量:</th>
								<td><input id="quantity" name="quantity" value="${saleOrderItem.quantity}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
								<th>单位:</th>
								<td><s:select id="measureUnitId" headerKey="-1" headerValue="--请选择--" list="%{measureUnitList}" listValue="name" listKey="id" multiple="false" theme="simple">
									</s:select></td>
							</tr>
							<tr height="40">
								<th>SKU编码:</th>
								<td><input id="skuCode" name="skuCode" value="${saleOrderItem.skuCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
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