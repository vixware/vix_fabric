<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	//_pad_addInputCheckNumEvent('#approvalOpinionForm #conditionsAmount');
});

var isSaving = false;
function submitApprovalOpinionForm(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#approvalOpinionForm #itemName').val()==''){
    	showMessage('请输入审批人');
    	isSaving = false;
    	return false;
	}
	
	$("#approvalOpinionForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/purchase/purchaseOrderAction!orderApprovalOpinionSave.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	showMessage('信息保存完毕');		    
		    	reloadDataGrid7();

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
	<form id="approvalOpinionForm" method="post">
		<s:hidden id="adderssId" name="approvalOpinion.id" value="%{approvalOpinion.id}" theme="simple" />
		<input type="hidden" name="purchaseId" value="${purchaseId}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">审批人:&nbsp;</td>
					<td><input id="checkPerson" readonly="readonly" name="approvalOpinion.checkPerson" value="${approvalOpinion.checkPerson}" type="text" class=" text-input" /> <input type="hidden" name="approvalOpinion.checkPersonId" value="${approvalOpinion.checkPersonId}" /> <span style="color: red;">*</span></td>
					<td align="right"></td>
					<td></td>
				</tr>
				<tr height="30">
					<td align="right">审批意见:&nbsp;</td>
					<td colspan="3"><textarea id="approvalOpinionContent" name="approvalOpinion.content" class=" text-input" style="width: 400px; height: 80px;">${approvalOpinion.content}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="c_foot">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>