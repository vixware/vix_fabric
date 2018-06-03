<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#saleReturnFormItemForm").validationEngine();
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
	<form id="saleReturnFormItemForm">
		<s:hidden id="ddiId" name="saleReturnFormItem.id" value="%{saleReturnFormItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal; margin: 10px;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">${vv:varView('vix_mdm_item')}编码:&nbsp;</th>
					<td><input id="itemCode" name="saleReturnFormItem.itemCode" value="${saleReturnFormItem.itemCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView('vix_mdm_item')}名称:&nbsp;</th>
					<td><input id="itemName" name="saleReturnFormItem.itemName" value="${saleReturnFormItem.itemName}" type="text" /> <span class="abtn" style="cursor: pointer;" onclick="chooseItem();"><span>选择</span></span></td>
				</tr>
				<tr height="40">
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="saleReturnFormItem.specification" value="${saleReturnFormItem.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView('vix_mdm_item')}数量:&nbsp;</th>
					<td><input id="ddiCount" name="saleReturnFormItem.count" value="${saleReturnFormItem.count}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">${vv:varView('vix_mdm_item')}价格:&nbsp;</th>
					<td><input id="ddiPrice" name="saleReturnFormItem.price" value="${saleReturnFormItem.price}" type="text" class="validate[required] text-input" /></td>
					<th align="right">${vv:varView('vix_mdm_item')}单位:&nbsp;</th>
					<td><input id="ddiUnit" name="saleReturnFormItem.unit" value="${saleReturnFormItem.unit}" type="text" class="validate[required] text-input" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>