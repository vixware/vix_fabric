<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style type="text/css">
.order .order_table input[type=text] {
	width: 130px;
}
</style>
<script type="text/javascript">
$(function(){

	addInputCheckNumEvent('countValue');
	changeWarrantyType();
});

function changeWarrantyType(){
	var val = $('#warrantyType').val();

	$('.warrantyType_input').hide();
	$('.warrantyType_'+val).show();
}

var isSaving = false;
function saveEqBxxx(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#bxxxForm #installObj').val()==''){
    	showMessage('请输入保修项目');
    	return false;
	}

	var eqId = $('#warEqId').val();
	var popWinId = 'bxxx_eq_'+eqId;

	$("#bxxxForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/eam/accountManageAction!saveEqBxxx.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	var dataId = result;
		    	$('#warrantyId').val(dataId);
		    	showMessage('信息保存完毕');
		    	//刷新的方法需要更换  !!!!!!!!!!!!!!!!!!!!!!!!!
		    	var eqId = $('#eqId').val();
		    	_pad_all_loadPage('${vix}/eam/accountManageAction!eqBxxxPager.action?eqId='+eqId,'eq_bxxx_grid_div');
		    	isSaving = false;
		    	$.close(popWinId);
	    	}else{
	    		showErrorMessage('信息保存失败');
		    	isSaving = false;
	    	}
	     }
	 });
	return false;
}

</script>
<s:form id="bxxxForm" name="jbxxForm" action="" method="post" theme="simple">
	<input type="hidden" name="warranty.equipment.id" id="warEqId" value="<s:property value="eqId"/>" />
	<s:hidden id="warrantyId" name="warranty.id" theme="simple"></s:hidden>
	<div class="order_table">
		<table>
			<tr>
				<th>保修项目：</th>
				<td><s:textfield id="installObj" name="warranty.installObj" type="text"></s:textfield><font color="red">*</font></td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>保修合同编号：</th>
				<td><s:textfield type="text" id="contractSn" name="warranty.contractSn"></s:textfield></td>
				<th>设备序列号：</th>
				<td><s:textfield id="serialNo" name="warranty.serialNo" type="text"></s:textfield></td>
			</tr>
			<tr>
				<th>负责人：</th>
				<td><s:textfield id="responsiPerson" name="warranty.responsiPerson" type="text"></s:textfield></td>
				<th>联系电话：</th>
				<td><s:textfield id="phone" name="warranty.phone" type="text"></s:textfield></td>
			</tr>
			<tr>
				<th>优先级：</th>
				<td><s:select id="priority" name="warranty.priority" list="#{'正常':'正常','高':'高','低':'低'}" theme="simple"></s:select></td>
				<th>保修类型：</th>
				<td><s:select id="warrantyMode" name="warranty.warrantyMode" list="#{'1':'制造保修','2':'附加保修','3':'外延的损坏保修'}" theme="simple"></s:select></td>
			</tr>
			<tr>
				<th>保修负责单位：</th>
				<td><s:textfield type="text" id="warrantyBy" name="warranty.warrantyBy"></s:textfield></td>
				<th>收费方式：</th>
				<td><s:select id="feeMode" name="warranty.feeMode" list="#{'免费':'免费','部分收费':'部分收费','人工费':'人工费','材料费':'材料费','其他':'其他'}" theme="simple"></s:select></td>
			</tr>
			<tr>
				<th>保修故障信息：</th>
				<td colspan="3" style="padding-top: 5px;"><textarea id="warrantyContent" name="warranty.content" rows="1" cols="1" style="width: 90%; height: 50px;"><s:property value="warranty.content" escape="false" /></textarea></td>
			</tr>
			<tr>
				<th>有效性类型：</th>
				<td><s:select id="warrantyType" name="warranty.warrantyType" onchange="changeWarrantyType()" list="#{'1':'有效期','2':'周期（计数器）'}" theme="simple"></s:select></td>
				<th></th>
				<td></td>
			</tr>
			<tr class="warrantyType_input warrantyType_1">
				<th><span class="priority_input priority_1">截止日期</span>：</th>
				<td><input id="warrantyExpire" name="warranty.warrantyExpire" class="priority_input priority_1" value="<fmt:formatDate value='${warranty.warrantyExpire}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('warrantyExpire','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
					height="22" align="absmiddle"></td>
				<th></th>
				<td></td>
			</tr>
			<tr class="warrantyType warrantyType_2" style="display: none;">
				<th>保修周期值：</th>
				<td><s:textfield id="countValue" name="warranty.countValue" type="text"></s:textfield></td>
				<th>保修周期单位：</th>
				<td><s:textfield id="countUnit" name="warranty.countUnit"></s:textfield></td>
			</tr>

		</table>
	</div>

</s:form>