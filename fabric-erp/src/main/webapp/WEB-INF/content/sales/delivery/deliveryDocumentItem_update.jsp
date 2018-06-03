<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#deliveryDocumentItemForm").validationEngine();
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
</script>
<div class="content" style="background: #DCE7F1">
	<form id="deliveryDocumentItemForm">
		<s:hidden id="ddiId" name="deliveryDocumentItem.id" value="%{deliveryDocumentItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal; margin: 10px;">
			<table class="table-padding020">
				<tr height="30">
					<th align="right">${vv:varView('vix_mdm_item')}编码:&nbsp;</th>
					<td><input id="itemCode" name="deliveryDocumentItem.itemCode" value="${deliveryDocumentItem.itemCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView('vix_mdm_item')}名称:&nbsp;</th>
					<td><input id="itemName" name="deliveryDocumentItem.itemName" value="${deliveryDocumentItem.itemName}" type="text" /> <span class="abtn" style="cursor: pointer;" onclick="chooseItem();"><span>选择</span></span></td>
				</tr>
				<tr height="30">
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="deliveryDocumentItem.specification" value="${deliveryDocumentItem.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView('vix_mdm_item')}数量:&nbsp;</th>
					<td><input id="ddiCount" name="deliveryDocumentItem.count" value="${deliveryDocumentItem.count}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<th align="right">${vv:varView('vix_mdm_item')}价格:&nbsp;</th>
					<td><input id="ddiPrice" name="deliveryDocumentItem.price" value="${deliveryDocumentItem.price}" type="text" class="validate[required] text-input" /></td>
					<th align="right">${vv:varView('vix_mdm_item')}单位:&nbsp;</th>
					<td><input id="ddiUnit" name="deliveryDocumentItem.unit" value="${deliveryDocumentItem.unit}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="30">
					<th align="right">收货地址:&nbsp;</th>
					<td><input id="recieveAddress" name="deliveryDocumentItem.recieveAddress" value="${deliveryDocumentItem.recieveAddress}" type="text" class="validate[required] text-input" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>