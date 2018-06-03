<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var itemCatalogSetting = {
		view: {
			dblClickExpand: false
		},
		async: {
			enable: true,
			url:"${vix}/mdm/item/itemCatalogAction!findTreeToJson.action",
			autoParam:["id", "name=n", "level=lv"]
		},
		callback: {
			onClick: onICClick
		}
	};
	function onICClick(e, treeId, treeNode) {
		$("#itemCatalogId").attr("value", treeNode.id);
		$("#itemCatalogName").attr("value", treeNode.name);
		hideICMenu();
	}
	function showICMenu() {
		$("#itemCatalogMenuContent").css({left:133 + "px", top:110 + "px"}).slideDown("fast");
		$("body").bind("mousedown", onICBodyDown);
	}
	function hideICMenu() {
		$("#itemCatalogMenuContent").fadeOut("fast");
		$("body").unbind("mousedown", onICBodyDown);
	}
	function onICBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "itemCatalogMenuContent" || $(event.target).parents("#itemCatalogMenuContent").length>0)) {
			hideICMenu();
		}
	}
	$.fn.zTree.init($("#itemCatalogTree"), itemCatalogSetting);

	var customerAccountCategorySetting = {
			view: {
				dblClickExpand: false
			},
			async: {
				enable: true,
				url:"${vix}/crm/customer/crmCustomerAccountCategoryAction!findTreeToJson.action",
				autoParam:["id", "name=n", "level=lv"]
			},
			callback: {
				onClick: onClick
			}
		};
		function onClick(e, treeId, treeNode) {
			$("#customerAccountCategoryId").attr("value", treeNode.id);
			$("#customerAccountCategoryName").attr("value", treeNode.name);
			hideMenu();
		}
		function showMenu() {
			$("#customerAccountCategoryMenuContent").css({left:133 + "px", top:110 + "px"}).slideDown("fast");
			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#customerAccountCategoryMenuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "customerAccountCategoryMenuContent" || $(event.target).parents("#customerAccountCategoryMenuContent").length>0)) {
				hideMenu();
			}
		}
		$.fn.zTree.init($("#customerAccountCategoryTree"), customerAccountCategorySetting);

function loadGiftDetail(){
	$.ajax({
		  url:'${vix}/mdm/item/salePriceConditionGiftAction!goListContent.action?type=pcpa&pcaId='+$("#pcpaId").val(),
		  cache: false,
		  success: function(html){
			  $("#priceConditionGift").html(html);
		  }
	});
};
loadGiftDetail();
function saveOrUpdatePriceConditionGiftDetail(id){
	$.ajax({
		  url:'${vix}/mdm/item/salePriceConditionGiftAction!goSaveOrUpdate.action?type=pcpa&id='+id+"&pcaId="+$("#pcpaId").val(),
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 960,
					height : 540,
					title:"赠品信息",
					html:html,
					callback : function(action){
						if(action == 'cancle' || action == 'close'){
							loadGiftDetail();
						}
					},
					btnsbar : [{
						text    : '关闭',
						action  : 'cancle'
					}]
				});
		  }
	});
}
function deletePriceConditionGiftEntity(id){
	asyncbox.confirm('确定要删除该赠品么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/mdm/item/salePriceConditionGiftAction!deleteById.action?id='+id,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"提示信息",function(action){
						loadGiftDetail();
					});
				  }
			});
		}
	});
}
//切换价格类型
function changeAreaPriceType(){
	var apr = $(":radio[name=areaPriceType][checked]").val();
	if(apr == 'all'){
		$("#refund").removeAttr("disabled");
		$("#discount").removeAttr("disabled");
	}
	if(apr == 'refund'){
		$("#discount").val('');
		$("#discount").attr("disabled","disabled");
		$("#refund").removeAttr("disabled");
	}
	if(apr == 'discount'){
		$("#refund").val('');
		$("#refund").attr("disabled","disabled");
		$("#discount").removeAttr("disabled");
	}
}
$("#priceConditionAreaForm").validationEngine();
changePriceConditionType();
changeAreaPriceType();
</script>
<div class="content" style="padding-top: 10px; margin: 0;">
	<form id="priceConditionAreaForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="pcpaId" name="priceConditionPriceArea.id" value="%{priceConditionPriceArea.id}" theme="simple" />
				<tr height="30">
					<td align="right">条件类型:&nbsp;</td>
					<td><s:if test="priceConditionPriceArea.conditionType == null">
							<select id="conditionType" onchange="changePriceConditionType();">
								<s:iterator value="pcpaMap.entrySet().iterator()" var="key">
									<option value="<s:property value='key'/>"><s:text name="getText(value)" /></option>
								</s:iterator>
							</select>
						</s:if> <s:else>
							<s:if test="priceConditionPriceArea.conditionType == 'purchaseFrameworkAgreement'">
								<s:text name="getText('mdm_purchaseFrameworkAgreement')" />
								<input type="hidden" id="conditionType" name="conditionType" value="purchaseFrameworkAgreement" />
							</s:if>
							<s:if test="priceConditionPriceArea.conditionType == 'saleFrameworkAgreement'">
								<s:text name="getText('mdm_saleFrameworkAgreement')" />
								<input type="hidden" id="conditionType" name="conditionType" value="saleFrameworkAgreement" />
							</s:if>
							<s:if test="priceConditionPriceArea.conditionType == 'customerAccountCategory'">
								<s:text name="getText('mdm_customerAccountCategory')" />
								<input type="hidden" id="conditionType" name="conditionType" value="customerAccountCategory" />
							</s:if>
							<s:if test="priceConditionPriceArea.conditionType == 'itemCategory'">
								<s:text name="getText('mdm_itemCategory')" />
								<input type="hidden" id="conditionType" name="conditionType" value="itemCategory" />
							</s:if>
							<s:if test="priceConditionPriceArea.conditionType == 'itemGroup'">
								<s:text name="getText('mdm_itemGroup')" />
								<input type="hidden" id="conditionType" name="conditionType" value="itemGroup" />
							</s:if>
							<s:if test="priceConditionPriceArea.conditionType == 'channelDistributor'">
								<s:text name="getText('mdm_channelDistributor')" />
								<input type="hidden" id="conditionType" name="conditionType" value="channelDistributor" />
							</s:if>
							<s:if test="priceConditionPriceArea.conditionType == 'customerAccount'">
								<s:text name="getText('mdm_customerAccount')" />
								<input type="hidden" id="conditionType" name="conditionType" value="customerAccount" />
							</s:if>
							<s:if test="priceConditionPriceArea.conditionType == 'item'">
								<s:text name="getText('mdm_item')" />
								<input type="hidden" id="conditionType" name="conditionType" value="item" />
							</s:if>
							<s:if test="priceConditionPriceArea.conditionType == 'supplier'">
								<s:text name="getText('mdm_supplier')" />
								<input type="hidden" id="conditionType" name="conditionType" value="supplier" />
							</s:if>
							<s:if test="priceConditionPriceArea.conditionType == 'customerAccountGroup'">
								<s:text name="getText('mdm_customerAccountGroup')" />
								<input type="hidden" id="conditionType" name="conditionType" value="customerAccountGroup" />
							</s:if>
							<s:if test="priceConditionPriceArea.conditionType == 'customerAccountAndItem'">
								<s:text name="getText('mdm_customerAccountAndItem')" />
								<input type="hidden" id="conditionType" name="conditionType" value="customerAccountAndItem" />
							</s:if>
						</s:else></td>
					<td name="regionalTbody" align="right">地域:&nbsp;</td>
					<td name="regionalTbody"><s:select id="regionalId" list="%{regionalList}" headerKey="-1" headerValue="----无----" listValue="name" listKey="id" value="%{priceConditionPriceArea.regional.id}" multiple="false" theme="simple"></s:select></td>
				</tr>
				<tr id="purchaseFrameworkAgreementTbody" height="30">
					<td align="right">供应商:</td>
					<td><input id="supplierIdPfa" type="hidden" value="${priceConditionPriceArea.supplier.id}" /> <input id="supplierNamePfa" name="supplierName" value="${priceConditionPriceArea.supplier.name }" type="text" class="validate[required] text-input" readonly="readonly" /> <input class="btn" type="button" value="选择"
						onclick="chooseSupplier('Pfa');" /></td>
					<td align="right">${vv:varView('vix_mdm_item')}:</td>
					<td><input id="itemNamePfg" value="${priceConditionPriceArea.item.name}" type="text" class="validate[required] text-input" readonly="readonly" /> <input id="itemIdPfg" type="hidden" value="${priceConditionPriceArea.item.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem('Pfg');">选择</span></span> <span>零售价格:<span
							id="itemPricePfg">${priceConditionPriceArea.item.price}</span></span></td>
				</tr>
				<tr id="saleFrameworkAgreementTbody" height="30">
					<td align="right">客户:</td>
					<td><input id="customerNameSfa" name="priceConditionPriceArea.customerAccount.name" value="${priceConditionPriceArea.customerAccount.name}" type="text" class="validate[required] text-input" readonly="readonly" /> <input type="hidden" id="customerAccountIdSfa" name="customerAccountId" value="${priceConditionPriceArea.customerAccount.id}" />
						<span><a class="abtn" href="#" onclick="chooseCustomerAccount('Sfa');"><span>选择</span></a></span></td>
					<td align="right">${vv:varView('vix_mdm_item')}:</td>
					<td><input id="itemNameSfa" value="${priceConditionPriceArea.item.name}" type="text" class="validate[required] text-input" readonly="readonly" /> <input id="itemIdSfa" type="hidden" value="${priceConditionPriceArea.item.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem('Sfa');">选择</span></span> <span>零售价格:<span
							id="itemPriceSfa">${priceConditionPriceArea.item.price}</span></span></td>
				</tr>
				<tr id="customerAccountCategoryTbody" height="30">
					<td align="right">客户分类:</td>
					<td><input id="customerAccountCategoryName" value="${priceConditionPriceArea.customerAccountCategory.name}" type="text" onfocus="showMenu(); return false;" /> <input id="customerAccountCategoryId" type="hidden" value="${priceConditionPriceArea.customerAccountCategory.id}" /></td>
					<td align="right">${vv:varView('vix_mdm_item')}:</td>
					<td><input id="itemNameCac" value="${priceConditionPriceArea.item.name}" type="text" class="validate[required] text-input" readonly="readonly" /> <input id="itemIdCac" type="hidden" value="${priceConditionPriceArea.item.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem('Cac');">选择</span></span> <span>零售价格:<span
							id="itemPriceCac">${priceConditionPriceArea.item.price}</span></span></td>
				</tr>
				<tr id="itemCategoryTbody" height="30">
					<td align="right">${vv:varView('vix_mdm_item')}分类:</td>
					<td colspan="3"><input id="itemCatalogName" value="${priceConditionPriceArea.itemCatalog.name}" type="text" onfocus="showICMenu(); return false;" /> <input id="itemCatalogId" type="hidden" value="${priceConditionPriceArea.itemCatalog.id}" /></td>
				</tr>
				<tr id="itemGroupTbody" height="30">
					<td align="right">${vv:varView('vix_mdm_item')}组:</td>
					<td colspan="3"><s:select id="itemGroupId" headerKey="-1" headerValue="--请选择--" list="%{itemGroupList}" listValue="name" listKey="id" value="%{priceConditionPriceArea.itemGroup.id}" multiple="false" theme="simple"></s:select></td>
				</tr>
				<tr id="customerAccountGroupTbody" height="30">
					<td align="right">客户组:</td>
					<td colspan="3"><s:select id="customerAccountGroupId" headerKey="-1" headerValue="--请选择--" list="%{customerAccountGroupList}" listValue="name" listKey="id" value="%{priceConditionPriceArea.customerAccountGroup.id}" multiple="false" theme="simple"></s:select></td>
				</tr>
				<tr id="channelDistributorTbody" height="30">
					<td align="right">分销商:</td>
					<td><input type="hidden" id="channelDistributorAreaIdCd" value="${priceConditionPriceArea.channelDistributor.id}" /> <input id="channelDistributorAreaNameCd" name="priceConditionPriceArea.channelDistributor.name" value="${priceConditionPriceArea.channelDistributor.name}" type="text" readonly="readonly" /> <a class="abtn" href="#"
						onclick="chooseAreaChannelDistributor('Cd');"><span>选择</span></a></td>
					<td align="right">${vv:varView('vix_mdm_item')}:</td>
					<td><input id="itemNameCd" value="${priceConditionPriceArea.item.name}" type="text" class="validate[required] text-input" readonly="readonly" /> <input id="itemIdCd" type="hidden" value="${priceConditionPriceArea.item.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem('Cd');">选择</span></span> <span>零售价格:<span
							id="itemPriceCd">${priceConditionPriceArea.item.price}</span></span></td>
				</tr>
				<tr id="customerAccountTbody" height="30">
					<td align="right">客户:</td>
					<td colspan="3"><input id="customerNameCa" name="priceConditionPriceArea.customerAccount.name" value="${priceConditionPriceArea.customerAccount.name}" type="text" class="validate[required] text-input" readonly="readonly" /> <input type="hidden" id="customerAccountIdCa" name="customerAccountId"
						value="${priceConditionPriceArea.customerAccount.id}" /> <span><a class="abtn" href="#" onclick="chooseCustomerAccount('Ca');"><span>选择</span></a></span></td>
				</tr>
				<tr id="itemTbody" height="30">
					<td align="right">${vv:varView('vix_mdm_item')}:</td>
					<td><input id="itemNameI" value="${priceConditionPriceArea.item.name}" type="text" class="validate[required] text-input" readonly="readonly" /> <input id="itemIdI" type="hidden" value="${priceConditionPriceArea.item.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem('I');">选择</span></span></td>
					<td align="right">零售价格:</td>
					<td><span id="itemPriceI">${priceConditionPriceArea.item.price}</span></td>
				</tr>
				<tr id="customerAccountAndItemTbody" height="30">
					<td align="right">客户:</td>
					<td><input id="customerNameCaai" name="priceConditionPriceArea.customerAccount.name" value="${priceConditionPriceArea.customerAccount.name}" type="text" class="validate[required] text-input" readonly="readonly" /> <input type="hidden" id="customerAccountIdCaai" name="customerAccountId" value="${priceConditionPriceArea.customerAccount.id}" />
						<span><a class="abtn" href="#" onclick="chooseCustomerAccount('Caai');"><span>选择</span></a></span></td>
					<td align="right">${vv:varView('vix_mdm_item')}:</td>
					<td><input id="itemNameCaai" value="${priceConditionPriceArea.item.name}" type="text" class="validate[required] text-input" readonly="readonly" /> <input id="itemIdCaai" type="hidden" value="${priceConditionPriceArea.item.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem('Caai');">选择</span></span> <span>零售价格:<span
							id="itemPriceCaai">${priceConditionPriceArea.item.price}</span></span></td>
				</tr>
				<tr id="supplierTbody" height="30">
					<td align="right">供应商:</td>
					<td><input id="supplierIdS" type="hidden" value="${priceConditionPriceArea.supplier.id}" /> <input id="supplierNameS" name="supplierName" value="${priceConditionPriceArea.supplier.name }" type="text" class="validate[required] text-input" readonly="readonly" /> <input class="btn" type="button" value="选择" onclick="chooseSupplier('S');" />
					</td>
					<td align="right">${vv:varView('vix_mdm_item')}:</td>
					<td><input id="itemNameS" value="${priceConditionPriceArea.item.name}" type="text" class="validate[required] text-input" readonly="readonly" /> <input id="itemIdS" type="hidden" value="${priceConditionPriceArea.item.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem('S');">选择</span></span> <span>零售价格:<span
							id="itemPriceS">${priceConditionPriceArea.item.price}</span></span></td>
				</tr>
				<tr height="30">
					<td align="right">累计金额从:&nbsp;</td>
					<td><input id="startTotalAmount" name="priceConditionPriceArea.startTotalAmount" value="${priceConditionPriceArea.startTotalAmount}" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
					<td align="right">累计金额到 :&nbsp;</td>
					<td><input id="endTotalAmount" name="priceConditionPriceArea.endTotalAmount" value="${priceConditionPriceArea.endTotalAmount}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr id="priceTypeTbody" height="30">
					<td align="right">价格类型:&nbsp;</td>
					<td><s:radio id="areaPriceType" name="areaPriceType" list="#{'all':'全部','refund':'返款','discount':'折扣'}" listKey="key" listValue="value" value="%{priceConditionPriceArea.areaPriceType}" theme="simple" onchange="changeAreaPriceType();" /></td>
					<td align="right">返款:&nbsp;</td>
					<td><input id="refund" name="priceConditionPriceArea.refund" value="${priceConditionPriceArea.refund}" /></td>
				</tr>
				<tr height="30">
					<td align="right">折扣:&nbsp;</td>
					<td><input id="discount" name="priceConditionPriceArea.discount" value="${priceConditionPriceArea.discount}" />折扣为0-1的小数</td>
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input id="memo" name="priceConditionPriceArea.memo" value="${priceConditionPriceArea.memo}" /></td>
				</tr>
			</table>
		</div>
		<div class="clearfix" style="background: #FFF; position: relative; margin: 0 7px;">
			<div class="list_table" style="margin: 0; width: 100%">
				<p>
					<a class="abtn" href="###" onclick="saveOrUpdatePriceConditionGiftDetail(0);"><span style="width: 55px;">添加赠品</span></a>
				</p>
			</div>
			<div id="priceConditionGift" class="list_table" style="margin: 0; width: 100%"></div>
		</div>
	</form>
</div>
<div id="itemCatalogMenuContent" class="itemCatalogMenuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf;">
	<ul id="itemCatalogTree" class="ztree" style="margin-top: 0; width: 155px;"></ul>
</div>
<div id="customerAccountCategoryMenuContent" class="customerAccountCategoryMenuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf;">
	<ul id="customerAccountCategoryTree" class="ztree" style="margin-top: 0; width: 155px;"></ul>
</div>