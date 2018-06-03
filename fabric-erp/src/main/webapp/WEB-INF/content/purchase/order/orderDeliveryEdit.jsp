<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	_pad_addInputCheckNumEvent('#deliveryForm #deliveryAmount');
});

var isSaving = false;
function submitDeliveryForm(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#deliveryForm #itemName').val()==''){
    	showMessage('请输入${vv:varView("vix_mdm_item")}名称');
    	isSaving = false;
    	return false;
	}
	
	$("#deliveryForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/purchase/purchaseOrderAction!orderDeliverySave.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	showMessage('信息保存完毕');		    
		    	reloadDataGrid3();

		    	isSaving = false;
		    	$.close('popModalWin');
	    	}else{
	    		showErrorMessage('信息保存失败');
		    	isSaving = false;
	    	}
	     }
	 });
	return false;
}
</script>

<div class="c_head">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>
<div class="box">
	<form id="deliveryForm" method="post">
		<s:hidden id="adderssId" name="delivery.id" value="%{delivery.id}" theme="simple" />
		<input type="hidden" name="purchaseId" value="${purchaseId}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">${vv:varView("vix_mdm_item")}编码:&nbsp;</td>
					<td><input id="itemCode" name="delivery.itemCode" value="${delivery.itemCode}" type="text" class=" text-input" /></td>
					<td align="right">${vv:varView("vix_mdm_item")}名称:&nbsp;</td>
					<td><input id="itemName" name="delivery.itemName" value="${delivery.itemName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">订货数量:&nbsp;</td>
					<td><input id="deliveryAmount" name="delivery.amount" value="${delivery.amount}" type="text" class=" text-input" /></td>
					<td align="right">到货点:&nbsp;</td>
					<td><input id="deliveryRecievePointRef" name="delivery.recievePointRef" value="${delivery.recievePointRef}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">发运人:&nbsp;</td>
					<td><input id="deliveryDeliver" name="delivery.deliver" value="${delivery.deliver}" type="text" class=" text-input" /></td>
					<td align="right">发运时间:&nbsp;</td>
					<td><input id="deliveryTime" name="delivery.deliveryTime" value="<s:date name="delivery.deliveryTime" format="yyyy-MM-dd"/>" readonly="readonly" type="text" onclick="showTime('deliveryTime','yyyy-MM-dd')" /> <img onclick="showTime('deliveryTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="c_foot">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>