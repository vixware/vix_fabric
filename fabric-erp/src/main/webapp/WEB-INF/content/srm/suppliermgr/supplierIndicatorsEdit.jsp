<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	_pad_addInputCheckNumEvent('quota');
	_pad_addInputCheckNumEvent('priceLimit');
});

var isSaving = false;
function submitsIndicatorsForm(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#indicatorsForm #bankName').val()==''){
    	showMessage('请输入开户银行');
    	return false;
	}
	
	if($('#indicatorsForm #bankAccount').val()==''){
    	showMessage('请输入银行账号');
    	return false;
	}
	

	$("#indicatorsForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/srm/managementBusinessPartnerAction!saveOrUpdateSupplierIndicators.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	showMessage('信息保存完毕');		    
		    	reloadDataGrid6();

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
	<form id="indicatorsForm" method="post">
		<s:hidden id="indicatorsId" name="supplierIndicators.id" value="%{supplierIndicators.id}" theme="simple" />
		<s:hidden id="supplierCode" name="supplierIndicators.supplierCode" value="%{supplierIndicators.supplierCode}" theme="simple" />
		<input type="hidden" name="supplierId" value="${supplierId}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">指标名称:&nbsp;</td>
					<td><input id="itemName" name="supplierIndicators.itemName" value="${supplierIndicators.itemName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<td align="right">权重:&nbsp;</td>
					<td><input id="weights" name="supplierIndicators.weights" value="${supplierIndicators.weights}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">配额数量:&nbsp;</td>
					<td><input id="quota" name="supplierIndicators.quota" value="${supplierIndicators.quota}" type="text" class="validate[required] text-input" /></td>
					<td align="right">金额限度:&nbsp;</td>
					<td><input id="priceLimit" name="supplierIndicators.priceLimit" value="${supplierIndicators.priceLimit}" type="text" class=" text-input" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="c_foot">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>