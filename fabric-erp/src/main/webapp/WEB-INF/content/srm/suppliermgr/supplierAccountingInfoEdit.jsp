<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	_pad_addInputCheckNumEvent('limited');
});

var isSaving = false;
function submitAccountingInfoForm(){
	if(isSaving)
		return;
	isSaving = true;
	
	//if($('#accountingForm #addressStreet').val()==''){
    //	showMessage('请输入地址信息');
    //	return false;
	//}
	

	$("#accountingForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/srm/managementBusinessPartnerAction!saveOrUpdateSupplierAccountingInfo.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	showMessage('信息保存完毕');		    
		    	reloadDataGrid4();

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
	<form id="accountingForm" method="post">
		<s:hidden id="accountingId" name="accountingInfo.id" value="%{accountingInfo.id}" theme="simple" />
		<s:hidden id="supplierCode" name="accountingInfo.supplierCode" value="%{accountingInfo.supplierCode}" theme="simple" />
		<input type="hidden" name="supplierId" value="${supplierId}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">总帐分类:&nbsp;</td>
					<td><input id="generalAccountCatalog" name="accountingInfo.generalAccountCatalog" value="${accountingInfo.generalAccountCatalog}" type="text" class="validate[required] text-input" /></td>
					<td align="right">付款条件:&nbsp;</td>
					<td><input id="payCondition" name="accountingInfo.payCondition" value="${accountingInfo.payCondition}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">付款类型:&nbsp;</td>
					<td><input id="payType" name="accountingInfo.payType" value="${accountingInfo.payType}" type="text" class="validate[required] text-input" /></td>
					<td align="right">付款方式:&nbsp;</td>
					<td><input id="payStyle" name="accountingInfo.payStyle" value="${accountingInfo.payStyle}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">付款对象:&nbsp;</td>
					<td><input id="payTarget" name="accountingInfo.payTarget" value="${accountingInfo.payTarget}" type="text" class="validate[required] text-input" /></td>
					<td align="right">付款期限(天):&nbsp;</td>
					<td><input id="limited" name="accountingInfo.limited" value="${accountingInfo.limited}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">发票抬头:&nbsp;</td>
					<td><input id="invoiceHeader" name="accountingInfo.invoiceHeader" value="${accountingInfo.invoiceHeader}" type="text" class="validate[required] text-input" /></td>
					<td align="right">增值税开户银行:&nbsp;</td>
					<td><input id="vaBank" name="accountingInfo.vaBank" value="${accountingInfo.vaBank}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">审核人:&nbsp;</td>
					<td><input id="checked" name="accountingInfo.checked" value="${accountingInfo.checked}" type="text" class="validate[required] text-input" /></td>
					<td align="right">&nbsp;</td>
					<td></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="c_foot">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>