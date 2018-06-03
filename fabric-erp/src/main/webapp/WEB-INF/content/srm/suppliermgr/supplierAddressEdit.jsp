<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var isSaving = false;
function submitAddressForm(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#addressForm #addressStreet').val()==''){
    	showMessage('请输入地址信息');
    	return false;
	}
	

	$("#addressForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/srm/managementBusinessPartnerAction!saveOrUpdateSupplierAddress.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	showMessage('信息保存完毕');		    
		    	reloadDataGrid2();

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
	<form id="addressForm" method="post">
		<s:hidden id="adderssId" name="supplierAddress.id" value="%{supplierAddress.id}" theme="simple" />
		<s:hidden id="supplierCode" name="supplierAddress.supplierCode" value="%{supplierAddress.supplierCode}" theme="simple" />
		<input type="hidden" name="supplierId" value="${supplierId}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">国家:&nbsp;</td>
					<td><input id="addressCountry" name="supplierAddress.country" value="${supplierAddress.country}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<td align="right">地址:&nbsp;</td>
					<td><input id="addressStreet" name="supplierAddress.street" value="${supplierAddress.street}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">省:&nbsp;</td>
					<td><input id="addressProvince" name="supplierAddress.province" value="${supplierAddress.province}" type="text" class=" text-input" /> <span style="color: red;">*</span></td>
					<td align="right">邮政编码:&nbsp;</td>
					<td><input id="addressPostCode" name="supplierAddress.postCode" value="${supplierAddress.postCode}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">市:&nbsp;</td>
					<td><input id="addressCity" name="supplierAddress.city" value="${supplierAddress.city}" type="text" class=" text-input" /> <span style="color: red;">*</span></td>
					<td align="right">电话:&nbsp;</td>
					<td><input id="addressTelephone" name="supplierAddress.telephone" value="${supplierAddress.telephone}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">邮政信箱:&nbsp;</td>
					<td><input id="addressPostMail" name="supplierAddress.postMail" value="${supplierAddress.postMail}" type="text" class=" text-input" /> <span style="color: red;">*</span></td>
					<td align="right">传真:&nbsp;</td>
					<td><input id="addressFax" name="supplierAddress.fax" value="${supplierAddress.fax}" type="text" class=" text-input" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="c_foot">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>