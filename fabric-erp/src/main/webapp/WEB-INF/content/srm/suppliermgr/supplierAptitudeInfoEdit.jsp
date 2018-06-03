<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var isSaving = false;
function submitAptitudeInfoForm(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#aptitudeInfoForm #aptitudeInfoName').val()==''){
    	showMessage('请输入资质名称');
    	return false;
	}

	$("#aptitudeInfoForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/srm/managementBusinessPartnerAction!saveOrUpdateSupplierAptitudeInfo.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	showMessage('信息保存完毕');		    
		    	//$("#dlAptitudeInfo").datagrid('reload');
		    	reloadDataGrid1();
		    	
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
	<form id="aptitudeInfoForm" method="post">
		<s:hidden id="aptitudeInfoId" name="aptitudeInfo.id" value="%{aptitudeInfo.id}" theme="simple" />
		<s:hidden id="supplierCode" name="aptitudeInfo.supplierCode" value="%{aptitudeInfo.supplierCode}" theme="simple" />
		<input type="hidden" name="supplierId" value="${supplierId}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">资质名称:&nbsp;</td>
					<td><input id="aptitudeInfoName" name="aptitudeInfo.name" value="${aptitudeInfo.name}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<td align="right">资质有效期:&nbsp;</td>
					<td><input id="aptitudeInfoValidPeriod" name="aptitudeInfo.validPeriod" value="${aptitudeInfo.validPeriod}" type="text" class=" text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">资质描述:&nbsp;</td>
					<td><input id="aptitudeInfoDescription" name="aptitudeInfo.description" value="${aptitudeInfo.description}" type="text" /></td>
					<td align="right">资质文件:&nbsp;</td>
					<td><input id="aptitudeInfoFiles" name="aptitudeInfo.files" value="${aptitudeInfo.files}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="c_foot">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>