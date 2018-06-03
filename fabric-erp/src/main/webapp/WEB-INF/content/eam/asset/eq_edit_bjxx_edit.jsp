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

	addInputCheckNumEvent('price');
	addInputCheckNumEvent('account');

});

var isSaving = false;
function saveEqBjxx(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#bjxxForm #partCode').val()==''){
    	showMessage('请输入备件编码');
    	return false;
	}
	if($('#bjxxForm #partName').val()==''){
    	showMessage('请输入备件名称');
    	return false;
	}
	var eqId = $('#bjEqId').val();
	var popWinId = 'bjxx_eq_'+eqId;

	$("#bjxxForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/eam/accountManageAction!saveEqBjxx.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	var dataId = result;
		    	$('#sparePartId').val(dataId);
		    	showMessage('信息保存完毕');
		    	//刷新的方法需要更换  !!!!!!!!!!!!!!!!!!!!!!!!!
		    	var eqId = $('#eqId').val();
		    
		    	_pad_grid_loadPage('eq_bjxx_grid_div');
		    	//_pad_all_loadPage('${vix}/eam/accountManageAction!eqBjxxPager.action?eqId='+eqId,'eq_bjxx_grid_div');
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
<s:form id="bjxxForm" name="jbxxForm" action="" method="post" theme="simple">
	<input type="hidden" name="sparePart.equipment.id" id="bjEqId" value="<s:property value="eqId"/>" />
	<s:hidden id="sparePartId" name="sparePart.id" theme="simple"></s:hidden>
	<div class="order_table">
		<table>
			<tr>
				<th>备件编码：</th>
				<td><s:textfield id="partCode" name="sparePart.partCode" type="text"></s:textfield><font color="red">*</font></td>
				<th>备件名称：</th>
				<td><s:textfield id="partName" name="sparePart.partName" type="text"></s:textfield><font color="red">*</font></td>
			</tr>
			<tr>
				<th>规格型号：</th>
				<td><s:textfield type="text" id="specType" name="sparePart.specType"></s:textfield><font color="red">*</font></td>
				<th>购买日期：</th>
				<td><input id="_buyDate" name="sparePart.buyDate" value="<fmt:formatDate value='${sparePart.buyDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_buyDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
			<tr>
				<th>价格：</th>
				<td><s:textfield type="text" id="price" name="sparePart.price"></s:textfield></td>
				<th>入库日期：</th>
				<td><input id="_instoreDate" name="sparePart.instoreDate" value="<fmt:formatDate value='${sparePart.instoreDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_instoreDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
			<tr>
				<th>所在仓库：</th>
				<td><s:textfield id="depotCode" name="sparePart.depotCode" type="text"></s:textfield></td>
				<th>位置编码：</th>
				<td><s:textfield id="placeCode" name="sparePart.placeCode" type="text"></s:textfield></td>
			</tr>
			<tr>
				<th>单位：</th>
				<td><s:textfield id="unit" name="sparePart.unit" type="text"></s:textfield></td>
				<th>数量：</th>
				<td><s:textfield id="account" name="sparePart.account" type="text"></s:textfield></td>
			</tr>
			<tr>
				<th>制造商编码：</th>
				<td><s:textfield id="madeCode" name="sparePart.madeCode" type="text"></s:textfield></td>
				<th>供应商编码：</th>
				<td><s:textfield id="supplyCode" name="sparePart.supplyCode" type="text"></s:textfield></td>
			</tr>
			<!-- 
	  <tr>
	  	<th></th>
	  	<td id="pop_up_win_user_buttons">
	  		<input name="" id="" onclick="javascript:return saveEqBjxx()" type="button" class="btn" value="保存" />
	  	</td>
	  </tr>
 -->
		</table>
	</div>

</s:form>