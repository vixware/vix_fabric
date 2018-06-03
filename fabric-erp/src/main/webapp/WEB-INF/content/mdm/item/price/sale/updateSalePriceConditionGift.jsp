<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var itemCategorySetting = {
	view: {
		dblClickExpand: false
	},
	async: {
		enable: true,
		url:"${vix}/mdm/item/itemCatalogAction!findTreeToJson.action",
		autoParam:["id", "name=n", "level=lv"]
	},
	callback: {
		onClick: onClick
	}
};
function onClick(e, treeId, treeNode) {
	$("#itemCategoryId").attr("value", treeNode.id);
	$("#itemCategory").attr("value", treeNode.name);
	hideMenu();
	searchForContent();
}
function showMenu() {
	$("#itemMenuContent").css({left:70 + "px", top:76 + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#itemMenuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "itemMenuContent" || $(event.target).parents("#itemMenuContent").length>0)) {
		hideMenu();
	}
}
$.fn.zTree.init($("#itemCategoryTree"), itemCategorySetting);

var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
$("#priceConditionGiftForm").validationEngine();

function chooseItemForPrice(id,code,name,price){
	$("#itemGiftId").val(id);
	$("#itemGiftCode").val(code);
	$("#itemGiftName").val(name);
	$("#itemGiftPrice").val(price);
}
loadName();
//载入分页列表数据
pager("start","${vix}/mdm/item/saleItemPriceAction!goListContent.action?name="+name,'itemPriceManage');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#orderOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/mdm/item/saleItemPriceAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'itemPriceManage');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/mdm/item/saleItemPriceAction!goListContent.action?name="+name+'&companyCode='+$("#companyCode").val(),'itemPriceManage');
}
function addPriceConditionGift(){
	if($('#priceConditionGiftForm').validationEngine('validate')){
		$.post('${vix}/mdm/item/salePriceConditionGiftAction!saveOrUpdate.action',
				 {'priceConditionGift.id':$("#pcgId").val(),
				  'priceConditionGift.item.id':$("#itemGiftId").val(),
				  'priceConditionGift.count':$("#itemGiftCount").val(),
				  'priceConditionGift.specification':$("#itemGiftSpecification").val(),
				  'priceConditionGift.price':$("#itemGiftPrice").val(),
				  'priceConditionGift.memo':$("#itemGiftMemo").val(),
				  'pcaId':$("#pcAreaId").val(),
				  'type':$("#pcAreaType").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					$("#itemGiftId").val("");
					$("#itemGiftCode").val("");
					$("#itemGiftName").val("");
					$("#itemGiftSpecification").val("");
					$("#itemGiftPrice").val("");
					$("#itemGiftCount").val("");
					$("#itemGiftMemo").val("");
				}
			);
	}else{
			return false;
		}
}
function chooseSpecification(code){
	var spec = "";
	$("input[type=radio][id^='"+code+"_']:checked").each(function(){
		spec += $(this).val();
	});
	$("#itemGiftSpecification").val(spec);
}
function searchForContent(){
	loadName();
	var categoryId = $("#itemCategoryId").val();
	pager("start","${vix}/mdm/item/saleItemPriceAction!goListContent.action?name="+name+"&categoryId="+categoryId,'itemPriceManage');
}
function reset(){
	$("#itemCategoryId").val("");
	$("#itemCategory").val("");
	$("#nameS").val("");
}
$('input.btn[type="button"]').hover(function(){$(this).addClass("btnhover");},function(){$(this).removeClass("btnhover");});
</script>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<!-- 页面左上角按钮部分 -->
		<div>
			<label> 分类 <input id="itemCategoryId" type="hidden" value="" /> <input id="itemCategory" type="text" readonly="readonly" onfocus="showMenu(); return false;" class="int" value="" />
			</label> <label>名称<input type="text" class="int" id="nameS" onchange="searchForContent();"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="reset();" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" style="height: 390px; width: 600px;">
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
			<div class="right_content" style="height: 390px;">
				<form id="priceConditionGiftForm">
					<s:hidden id="pcgId" name="priceConditionGift.id" value="%{priceConditionGift.id}" theme="simple" />
					<s:hidden id="pcAreaId" value="%{pcaId}" theme="simple" />
					<s:hidden id="pcAreaType" value="%{type}" theme="simple" />
					<div class="box order_table" style="line-height: normal;">
						<table>
							<tr height="30">
								<td align="right">${vv:varView('vix_mdm_item')}编码</td>
								<td><input id="itemGiftCode" value="${priceConditionGift.item.code}" readonly="readonly" /></td>
							</tr>
							<tr height="30">
								<td align="right">${vv:varView('vix_mdm_item')}:&nbsp;</td>
								<td><input id="itemGiftName" value="${priceConditionGift.item.name}" " readonly="readonly" class="validate[required] text-input" /><span style="color: red;">*</span> <input id="itemGiftId" type="hidden" value="${priceConditionGift.item.id}" /></td>
							</tr>
							<tr height="30">
								<td align="right">规格:&nbsp;</td>
								<td><input id="itemGiftSpecification" value="${priceConditionGift.specification}" type="text" /></td>
							</tr>
							<tr height="30">
								<td align="right">数量:&nbsp;</td>
								<td><input id="itemGiftCount" value="${priceConditionGift.count}" type="text" class="validate[required,custom[number]] text-input" /></td>
							</tr>
							<tr height="30">
								<td align="right">单价:&nbsp;</td>
								<td><input id="itemGiftPrice" value="${priceConditionGift.price}" type="text" class="validate[required,custom[number]] text-input" /></td>
							</tr>
							<tr height="30">
								<td align="right">备注:&nbsp;</td>
								<td><input id="itemGiftMemo" value="${priceConditionGift.memo}" type="text" /></td>
							</tr>
						</table>
					</div>
					<div style="padding-left: 50px; padding-top: 15px;">
						<span class="abtn" style="cursor: pointer;" onclick="addPriceConditionGift();"> <s:if test="priceConditionGift.id > 0">
								<span>修改</span>
							</s:if> <s:else>
								<span>新增</span>
							</s:else>
						</span>
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