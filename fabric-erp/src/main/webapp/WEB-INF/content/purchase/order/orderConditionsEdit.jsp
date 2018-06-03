<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	_pad_addInputCheckNumEvent('#conditionsForm #conditionsAmount');
	_pad_addInputCheckNumEvent('#conditionsForm #netTotal');
});

var isSaving = false;
function submitConditionsForm(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#conditionsForm #itemName').val()==''){
    	showMessage('请输入${vv:varView("vix_mdm_item")}名称');
    	isSaving = false;
    	return false;
	}
	
	$("#conditionsForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/purchase/purchaseOrderAction!orderConditionsSave.action",
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
	<form id="conditionsForm" method="post">
		<s:hidden id="adderssId" name="conditions.id" value="%{conditions.id}" theme="simple" />
		<input type="hidden" name="purchaseId" value="${purchaseId}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">${vv:varView("vix_mdm_item")}编码:&nbsp;</td>
					<td><input id="itemCode" name="conditions.itemCode" value="${conditions.itemCode}" type="text" class=" text-input" /></td>
					<td align="right">${vv:varView("vix_mdm_item")}名称:&nbsp;</td>
					<td><input id="itemName" name="conditions.itemName" value="${conditions.itemName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">订货数量:&nbsp;</td>
					<td><input id="conditionsAmount" name="conditions.amount" value="${conditions.amount}" type="text" class=" text-input" /></td>
					<td align="right">金额:&nbsp;</td>
					<td><input id="netTotal" name="conditions.netTotal" value="${conditions.netTotal}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">货币:&nbsp;</td>
					<td><input id="conditionScoin" name="conditions.coin" value="${conditions.coin}" type="text" class=" text-input" /></td>
					<td align="right">单位:&nbsp;</td>
					<td><input id="conditionsUnit" name="conditions.unit" value="${conditions.unit}" type="text" class=" text-input" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="c_foot">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>