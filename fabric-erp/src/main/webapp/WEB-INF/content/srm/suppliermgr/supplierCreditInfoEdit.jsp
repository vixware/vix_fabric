<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	_pad_addInputCheckNumEvent('creditAmount');
	_pad_addInputCheckNumEvent('discount');
	_pad_addInputCheckNumEvent('lastDealAmount');
});

var isSaving = false;
function submitCreditInfoForm(){
	if(isSaving)
		return;
	isSaving = true;
	
	//if($('#creditInfoForm #addressStreet').val()==''){
    //	showMessage('请输入地址信息');
    //	return false;
	//}
	

	$("#creditInfoForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/srm/managementBusinessPartnerAction!saveOrUpdateSupplierCreditInfo.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	showMessage('信息保存完毕');		    
		    	reloadDataGrid5();

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
	<form id="creditInfoForm" method="post">
		<s:hidden id="creditInfoId" name="creditInfo.id" value="%{creditInfo.id}" theme="simple" />
		<s:hidden id="supplierCode" name="creditInfo.supplierCode" value="%{creditInfo.supplierCode}" theme="simple" />
		<input type="hidden" name="supplierId" value="${supplierId}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">信用等级:&nbsp;</td>
					<td><input id="creditLevel" name="creditInfo.creditLevel" value="${creditInfo.creditLevel}" type="text" class="validate[required] text-input" /> <!--
						<span style="color: red;">*</span> 
 --></td>
					<td align="right">信用额度:&nbsp;</td>
					<td><input id="creditAmount" name="creditInfo.creditAmount" value="${creditInfo.creditAmount}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">折扣率:&nbsp;</td>
					<td><input id="discount" name="creditInfo.discount" value="${creditInfo.discount}" type="text" class="validate[required] text-input" /></td>
					<td align="right">付款条件:&nbsp;</td>
					<td><input id="payCondition" name="creditInfo.payCondition" value="${creditInfo.payCondition}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">最后交易金额:&nbsp;</td>
					<td><input id="lastDealAmount" name="creditInfo.lastDealAmount" value="${creditInfo.lastDealAmount}" type="text" class="validate[required] text-input" /></td>
					<td align="right">最后交易日期:&nbsp;</td>
					<td><input id="lastDealTime" name="lastDealTimeStr" class="priority_input priority_1" value="<s:date name="#creditInfo.lastDealTime" format="yyyy-MM-dd"/>" readonly="readonly" type="text" /> <img onclick="showTime('lastDealTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="c_foot">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>