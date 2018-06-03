<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>
<script type="text/javascript">
function chooseItem(){
	$.ajax({
		  url:'${vix}/mdm/item/itemAction!goChooseItem.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 960,
					height : 580,
					title:"选择${vv:varView("vix_mdm_item")}",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != ''){
								var i = returnValue.split(",");
								$("#itemId").val(i[0]);
								$("#itemCode").val(i[1]);
								$("#itemName").val(i[2]);
								$("#itemPrice").val(i[3]);
							}else{
								asyncbox.success("请选择${vv:varView("vix_mdm_item")}!","<s:text name='vix_message'/>");
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}


//选择供应商信息
function chooseRadioSupplier(){
		$.ajax({
			  url:'${vix}/contract/contractInquiryAction!goSubRadioSingleList.action',
			  cache: false,
			  success: function(html){
				  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
				  $(".ab_c .content").css("margin-bottom","0");
				  $('.ab_c .content').parent().css('position','relative');
				  asyncbox.open({
					 	modal:true,
						width : 1000,
						height : 500,
						title:"选择供应商信息",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var rv = returnValue.split(",");
									 $("#supplierId").val(rv[0]);
									 $("#suppliername").val(rv[1]);
								}else{
									 $("#supplierId").val('');
									 $("#suppliername").val('');
									asyncbox.success("请选择分类信息!","提示信息");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
function fixedPrice(){
	if($("#itemId").val() == ''){
		asyncbox.success("请选择${vv:varView("vix_mdm_item")}!","<s:text name='vix_message'/>");
		return false;	
	}
	if($("#sqiAmount").val() == ''){
		asyncbox.success("请输入${vv:varView("vix_mdm_item")}数量!","<s:text name='vix_message'/>");
		return false;
	}
	$.ajax({
		  url:'${vix}/contract/contractInquiryAction!goFixedPrice.action?id='+$("#itemId").val()+"&count="+$("#sqiAmount").val()+"&customerAccountId="+$("#customerAccountId").val(),
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
								 $("#price").val(returnValue);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="contractPricingConditionsForm">
		<s:hidden id="daId" name="pricingConditions.id" value="%{pricingConditions.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<td align="right">${vv:varView("vix_mdm_item")}:&nbsp;</td>
					<td><input id="itemName" value="${salesQuotationItem.item.name}" /> <input id="itemId" type="hidden" value="${salesQuotationItem.item.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem();">选择</span></span></td>
					<td align="right">${vv:varView("vix_mdm_item")}编码</td>
					<td><input id="itemCode" value="${salesQuotationItem.item.code}" /></td>
				</tr>
				<tr height="40">
					<th align="right">供应商:&nbsp;</th>
					<td><input id="suppliername" name="suppliernvalue=" value="${pricingConditions.supplierId}" type="text" size="8" class="validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="supplierId" name="supplierId" value="" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /></td>
					<td align="right">零售价:&nbsp;</td>
					<td><input id="itemPrice" value="${priceCondition.item.price}" readonly="readonly" /></td>
				</tr>
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}数量:&nbsp;</th>
					<td><input id="sqiAmount" name="salesQuotationItem.amount" value="${salesQuotationItem.amount}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView("vix_mdm_item")}价格:&nbsp;</th>
					<td><input id="price" name="salesQuotationItem.price" value="${salesQuotationItem.price}" type="text" /> <span style="color: red;">*</span> <span class="abtn" style="cursor: pointer;" onclick="fixedPrice();"><span>定价</span></span></td>
				</tr>
				<tr height="40">
					<th align="right">采购组织:&nbsp;</th>
					<td><input id="" name="" value="目前还不可以选择" type="text" size="8" class="validate[required] text-input" /> <input id="" type="hidden" /> <span style="color: red;">*</span> <input class="btn" type="button" value="选择" onclick="chooseItem();" /></td>
					<th align="right">描述:&nbsp;</th>
					<td><input id="description" name="pricingConditions.description" value="${pricingConditions.description}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">采购数量起:&nbsp;</th>
					<td><input id="minCount" name="pricingConditions.minCount" value="${pricingConditions.minCount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">采购数量止:&nbsp;</th>
					<td><input id="maxCount" name="pricingConditions.maxCount" value="${pricingConditions.maxCount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">价格:&nbsp;</th>
					<td><input id="price" name="pricingConditions.price" value="${pricingConditions.price}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">折扣:&nbsp;</th>
					<td><input id="discount" name="pricingConditions.discount" value="${pricingConditions.discount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">开始时间:&nbsp;</th>
					<td><input id="startTime" name="startTime" value="<fmt:formatDate value='${pricingConditions.startTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">结束时间:&nbsp;</th>
					<td><input id="endTime" name="endTime" value="<fmt:formatDate value='${pricingConditions.endTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#contractPricingConditionsForm" ).validationEngine ( );
</script>