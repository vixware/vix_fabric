<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	var itemCatalogSetting = {
	view : {
		dblClickExpand : false
	},
	async : {
	enable : true,
	url : "${vix}/mdm/item/itemCatalogAction!findTreeToJson.action",
	autoParam : [ "id", "name=n", "level=lv" ]
	},
	callback : {
		onClick : onICClick
	}
	};
	function onICClick(e, treeId, treeNode) {
		$("#itemCatalogId").attr("value", treeNode.id);
		$("#itemCatalogName").attr("value", treeNode.name);
		hideICMenu();
	}
	function showICMenu() {
		$("#itemCatalogMenuContent").css({
		left : 133 + "px",
		top : 110 + "px"
		}).slideDown("fast");
		$("body").bind("mousedown", onICBodyDown);
	}
	function hideICMenu() {
		$("#itemCatalogMenuContent").fadeOut("fast");
		$("body").unbind("mousedown", onICBodyDown);
	}
	function onICBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "itemCatalogMenuContent" || $(event.target).parents("#itemCatalogMenuContent").length > 0)) {
			hideICMenu();
		}
	}
	$.fn.zTree.init($("#itemCatalogTree"), itemCatalogSetting);

	var customerAccountCategorySetting = {
	view : {
		dblClickExpand : false
	},
	async : {
	enable : true,
	url : "${vix}/crm/customer/crmCustomerAccountCategoryAction!findTreeToJson.action",
	autoParam : [ "id", "name=n", "level=lv" ]
	},
	callback : {
		onClick : onClick
	}
	};
	function onClick(e, treeId, treeNode) {
		$("#customerAccountCategoryId").attr("value", treeNode.id);
		$("#customerAccountCategoryName").attr("value", treeNode.name);
		hideMenu();
	}
	function showMenu() {
		$("#customerAccountCategoryMenuContent").css({
		left : 133 + "px",
		top : 110 + "px"
		}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#customerAccountCategoryMenuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "customerAccountCategoryMenuContent" || $(event.target)
				.parents("#customerAccountCategoryMenuContent").length > 0)) {
			hideMenu();
		}
	}
	$.fn.zTree.init($("#customerAccountCategoryTree"), customerAccountCategorySetting);

	function loadGiftDetail() {
		$.ajax({
		url : '${vix}/mdm/item/salePriceConditionGiftAction!goListContent.action?type=pcpa&pcaId=' + $("#pcpaId").val(),
		cache : false,
		success : function(html) {
			$("#priceConditionGift").html(html);
		}
		});
	};
	loadGiftDetail();
	function saveOrUpdatePriceConditionGiftDetail(id) {
		$.ajax({
		url : '${vix}/mdm/item/salePriceConditionGiftAction!goSaveOrUpdate.action?type=pcpa&id=' + id + "&pcaId=" + $("#pcpaId").val(),
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 960,
			height : 540,
			title : "赠品信息",
			html : html,
			callback : function(action) {
				if (action == 'cancle' || action == 'close') {
					loadGiftDetail();
				}
			},
			btnsbar : [ {
			text : '关闭',
			action : 'cancle'
			} ]
			});
		}
		});
	}
	function deletePriceConditionGiftEntity(id) {
		asyncbox.confirm('确定要删除该赠品么?', '_$tag_______________________', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/mdm/item/salePriceConditionGiftAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					asyncbox.success(html, "提示信息", function(action) {
						loadGiftDetail();
					});
				}
				});
			}
		});
	}
	//切换价格类型
	function changeAreaPriceType() {
		var apr = $(":radio[name=areaPriceType][checked]").val();
		if (apr == 'all') {
			$("#refund").removeAttr("disabled");
			$("#discount").removeAttr("disabled");
		}
		if (apr == 'refund') {
			$("#discount").val('');
			$("#discount").attr("disabled", "disabled");
			$("#refund").removeAttr("disabled");
		}
		if (apr == 'discount') {
			$("#refund").val('');
			$("#refund").attr("disabled", "disabled");
			$("#discount").removeAttr("disabled");
		}
	}
	$("#priceConditionAreaForm").validationEngine();
	changeAreaPriceType();
</script>
<div class="content" style="padding-top: 10px; margin: 0;">
	<form id="priceConditionAreaForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="pcpaId" name="channelPriceConditionPriceArea.id" value="%{channelPriceConditionPriceArea.id}" theme="simple" />
				<s:hidden id="channelPriceConditionId" name="channelPriceConditionId" value="%{channelPriceCondition.id}" theme="simple" />
				<%-- <tr id="channelDistributorTbody" height="30">
					<td align="right">商品:</td>
					<td><input id="itemNameCd" value="${channelPriceConditionPriceArea.item.name}" type="text" class="validate[required] text-input" readonly="readonly" /> <input id="itemIdCd" type="hidden" value="${channelPriceConditionPriceArea.item.id}" /> <span class="abtn"><span
							style="cursor: pointer;" onclick="chooseItem('Cd');">选择</span></span> <span>零售价格:<span id="itemPriceCd">${channelPriceConditionPriceArea.item.price}</span></span></td>
				</tr> --%>
				<tr height="30">
					<td align="right">累计金额从:&nbsp;</td>
					<td><input id="startTotalAmount" name="channelPriceConditionPriceArea.startTotalAmount" value="${channelPriceConditionPriceArea.startTotalAmount}" class="validate[required,custom[number]] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
					<td align="right">累计金额到 :&nbsp;</td>
					<td><input id="endTotalAmount" name="channelPriceConditionPriceArea.endTotalAmount" value="${channelPriceConditionPriceArea.endTotalAmount}" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">折扣:&nbsp;</td>
					<td><input id="discount" name="channelPriceConditionPriceArea.discount" value="${channelPriceConditionPriceArea.discount}" onchange="fieldChanged(this);" />折扣为0-1的小数</td>
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input id="memo" name="channelPriceConditionPriceArea.memo" value="${channelPriceConditionPriceArea.memo}" onchange="fieldChanged(this);" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div id="itemCatalogMenuContent" class="itemCatalogMenuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf;">
	<ul id="itemCatalogTree" class="ztree" style="margin-top: 0; width: 155px;"></ul>
</div>
<div id="customerAccountCategoryMenuContent" class="customerAccountCategoryMenuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf;">
	<ul id="customerAccountCategoryTree" class="ztree" style="margin-top: 0; width: 155px;"></ul>
</div>