<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var isSaving = false;
function submitBankInfoForm(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#bankInfoForm #bankName').val()==''){
    	showMessage('请输入开户银行');
    	return false;
	}
	
	if($('#bankInfoForm #bankAccount').val()==''){
    	showMessage('请输入银行账号');
    	return false;
	}
	

	$("#bankInfoForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/srm/managementBusinessPartnerAction!saveOrUpdateSupplierBankInfo.action",
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
	<form id="bankInfoForm" method="post">
		<s:hidden id="bankInfoId" name="supplierBankInfo.id" value="%{supplierBankInfo.id}" theme="simple" />
		<s:hidden id="supplierCode" name="supplierBankInfo.supplierCode" value="%{supplierBankInfo.supplierCode}" theme="simple" />
		<input type="hidden" name="supplierId" value="${supplierId}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">开户银行:&nbsp;</td>
					<td><input id="bankName" name="supplierBankInfo.bankName" value="${supplierBankInfo.bankName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<td align="right">开户银行代码:&nbsp;</td>
					<td><input id="bankCode" name="supplierBankInfo.bankCode" value="${supplierBankInfo.bankCode}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">银行帐号:&nbsp;</td>
					<td><input id="bankAccount" name="supplierBankInfo.bankAccount" value="${supplierBankInfo.bankAccount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
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