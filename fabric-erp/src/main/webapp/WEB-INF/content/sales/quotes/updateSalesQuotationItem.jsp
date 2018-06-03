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
$("#salesQuotationItemForm").validationEngine();
function fixedPrice(){
	if($("#itemId").val() == ''){
		asyncbox.success("请选择${vv:varView('vix_mdm_item')}!","<s:text name='vix_message'/>");
		return false;	
	}
	if($("#sqiAmount").val() == ''){
		asyncbox.success("请输入${vv:varView('vix_mdm_item')}数量!","<s:text name='vix_message'/>");
		return false;
	}
	var bdString = $("#bdString").val();
	var bcdDate =  $("#billDate").val() + " " + bdString.split(" ")[1];
	$.ajax({
		  url:'${vix}/mdm/item/saleItemPriceAction!goFixedPrice.action?id='+$("#itemId").val()+"&count="+$("#sqiAmount").val()+"&billCreateDate="+ bcdDate +"&customerAccountId="+$("#customerAccountId").val()+"&regionalId="+$("#regionalId").val(),
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 580,
					title:"定价",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != ''){
								var rv = returnValue.split(":");
								 $("#price").val(rv[0]);
								 if(rv.length == 2){
									 $("#giftJson").val(rv[1]);
								 }
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseItemForPrice(id,code,name,price){
	$("#itemId").val(id);
	$("#itemCode").val(code);
	$("#itemName").val(name);
	$("#sqiAmount").val("1");
	$("#specification").val("");
	$("#price").val(price);
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
function addSalesQuotationItem(){
	if($('#salesQuotationItemForm').validationEngine('validate')){
		$.post('${vix}/sales/quotes/salesQuotationAction!saveOrUpdateSalesQuotationItem.action',
				{'salesQuotationItem.id':$("#sqId").val(),
				  'salesQuotationItem.item.id':$("#itemId").val(),
				  'salesQuotationItem.specification':$("#specification").val(),
				  'salesQuotationItem.price':$("#price").val(),
				  'salesQuotationItem.amount':$("#sqiAmount").val(),
				  'salesQuotationItem.salesQuotation.id':$("#id").val(),
				  'giftJson':$("#giftJson").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					clearQuoItem();
				}
			);
	}else{
		return false;
	}
}
function clearQuoItem(){
	$("#itemId").val("");
	$("#itemCode").val("");
	$("#itemName").val("");
	$("#specification").val("");
	$("#price").val("");
	$("#sqiAmount").val("");
}
function chooseSpecification(code){
	var spec = "";
	$("input[type=radio][id^='"+code+"_']:checked").each(function(){
		spec += $(this).val();
	});
	$("#specification").val(spec);
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
				<form id="salesQuotationItemForm">
					<s:hidden id="sqId" name="salesQuotationItem.id" value="%{salesQuotationItem.id}" theme="simple" />
					<div class="box order_table" style="line-height: normal;">
						<table class="table-padding020">
							<tr height="30">
								<td align="right">编码:</td>
								<td><input id="itemCode" value="${salesQuotationItem.item.code}" /></td>
							</tr>
							<tr height="30">
								<td align="right">名称:</td>
								<td><input id="itemName" value="${salesQuotationItem.item.name}" /> <input id="itemId" type="hidden" value="${salesQuotationItem.item.id}" /></td>
							</tr>
							<tr height="30">
								<th align="right">规格:</th>
								<td><input id="specification" name="salesQuotationItem.specification" value="${salesQuotationItem.specification}" type="text" readonly="readonly" /></td>
							</tr>
							<tr height="30">
								<th align="right">数量:</th>
								<td><input id="sqiAmount" name="salesQuotationItem.amount" value="${salesQuotationItem.amount}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="30">
								<th align="right">价格:</th>
								<td><input id="price" name="salesQuotationItem.price" value="${salesQuotationItem.price}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span> <span class="abtn" style="cursor: pointer;" onclick="fixedPrice();"><span>定价</span></span></td>
							</tr>
						</table>
						<input type="hidden" id="giftJson" value="" />
					</div>
					<div style="padding-left: 150px; padding-top: 15px;">
						<s:if test="salesQuotationItem.id > 0">
							<span class="abtn" style="cursor: pointer;" onclick="addSalesQuotationItem();"><span>修改</span></span>
						</s:if>
						<s:else>
							<span class="abtn" style="cursor: pointer;" onclick="addSalesQuotationItem();"><span>新增</span></span>
						</s:else>
						<span class="abtn" style="cursor: pointer;" onclick="clearQuoItem();"><span>清空</span></span>

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