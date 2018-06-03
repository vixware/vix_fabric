<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#orderItemForm").validationEngine();
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
								var i = returnValue.split(",");
								$("#itemId").val(i[0]);
								$("#itemCode").val(i[1]);
								$("#itemName").val(i[2]);
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
	if($("#soiAmount").val() == ''){
		asyncbox.success("请输入${vv:varView('vix_mdm_item')}数量!","<s:text name='vix_message'/>");
		return false;
	}
	$.ajax({
		  url:'${vix}/mdm/item/saleItemPriceAction!goFixedPrice.action?id='+$("#itemId").val()+"&count="+$("#soiAmount").val()+"&billCreateDate="+$("#billDate").val()+"&customerAccountId="+$("#customerAccountId").val(),
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
	<form id="orderItemForm">
		<s:hidden id="oiId" name="orderItem.id" value="%{orderItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">${vv:varView('vix_mdm_item')}编码:&nbsp;</th>
					<td><input id="itemCode" name="orderItem.item.code" value="${orderItem.item.code}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView('vix_mdm_item')}名称:&nbsp;</th>
					<td><input id="itemId" type="hidden" value="${orderItem.item.id}" /> <input id="itemName" name="orderItem.itemName" value="${orderItem.item.name}" type="text" /> <span class="abtn" style="cursor: pointer;" onclick="chooseItem();"><span>选择</span></span></td>
				</tr>
				<tr height="40">
					<th align="right">${vv:varView('vix_mdm_item')}数量:&nbsp;</th>
					<td><input id="soiAmount" name="orderItem.amount" value="${orderItem.amount}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="orderItem.specification" value="${orderItem.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">${vv:varView('vix_mdm_item')}价格:&nbsp;</th>
					<td colspan="3"><input id="price" name="orderItem.price" value="${orderItem.price}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span> <span class="abtn" style="cursor: pointer;" onclick="fixedPrice();"><span>定价</span></span></td>
				</tr>
			</table>
		</div>
	</form>
</div>