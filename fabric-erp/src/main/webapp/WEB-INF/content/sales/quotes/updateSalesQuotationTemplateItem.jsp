<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#salesQuotationTemplateItemForm").validationEngine();
function chooseItem(){
	$.ajax({
		  url:'${vix}/mdm/item/itemAction!goChooseItem.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 580,
					title:"选择${vv:varView('vix_mdm_item')}",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != ''){
								var r = returnValue.split(",");
								$("#itemId").val(r[0]);
								$("#itemCode").val(r[1]);
								$("#itemName").val(r[2]);
							}else{
								asyncbox.success("请选择${vv:varView('vix_mdm_item')}!","<s:text name='vix_message'/>");
								return false;
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
		asyncbox.success("请选择${vv:varView('vix_mdm_item')}!","<s:text name='vix_message'/>");
		return false;	
	}
	if($("#sqiAmount").val() == ''){
		asyncbox.success("请输入${vv:varView('vix_mdm_item')}数量!","<s:text name='vix_message'/>");
		return false;
	}
	$.ajax({
		  url:'${vix}/mdm/item/saleItemPriceAction!goFixedPrice.action?id='+$("#itemId").val()+"&count="+$("#sqiAmount").val()+"&billCreateDate="+$("#billDate").val(),
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
	<form id="salesQuotationTemplateItemForm">
		<s:hidden id="sqId" name="salesQuotationTemplateItem.id" value="%{salesQuotationTemplateItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<td align="right">${vv:varView('vix_mdm_item')}:&nbsp;</td>
					<td><input id="itemName" value="${salesQuotationTemplateItem.item.name}" /> <input id="itemId" type="hidden" value="${salesQuotationTemplateItem.item.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem();">选择</span></span></td>
					<td align="right">${vv:varView('vix_mdm_item')}编码</td>
					<td><input id="itemCode" value="${salesQuotationTemplateItem.item.code}" /></td>
				</tr>
				<tr height="40">
					<th align="right">${vv:varView('vix_mdm_item')}数量:&nbsp;</th>
					<td><input id="sqiAmount" name="salesQuotationTemplateItem.amount" value="${salesQuotationTemplateItem.amount}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView('vix_mdm_item')}价格:&nbsp;</th>
					<td><input id="price" name="salesQuotationTemplateItem.price" value="${salesQuotationTemplateItem.price}" type="text" /> <span style="color: red;">*</span> <span class="abtn" style="cursor: pointer;" onclick="fixedPrice();"><span>定价</span></span></td>
				</tr>
			</table>
		</div>
	</form>
</div>