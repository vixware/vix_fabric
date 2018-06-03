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

});

var isSaving = false;
function saveEqsbwd(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#sbwdForm #title').val()==''){
    	showMessage('请输入文档名称');
    	return false;
	}
	var eqId = $('#wdEqId').val();
	var popWinId = 'sbwd_eq_'+eqId;

	$("#sbwdForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/eam/accountManageAction!saveEqSbwd.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	var dataId = result;
		    	$('#documentId').val(dataId);
		    	//刷新的方法需要更换  !!!!!!!!!!!!!!!!!!!!!!!!!
		    	var eqId = $('#eqId').val();
		    	_pad_all_loadPage('${vix}/eam/accountManageAction!eqSbwdPager.action?eqId='+eqId,'eq_sbwd_grid_div');

		    	showMessage('信息保存完毕');
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
<s:form id="sbwdForm" name="sbwdForm" action="" method="post" theme="simple" enctype="multipart/form-data">
	<input type="hidden" name="document.equipment.id" id="wdEqId" value="<s:property value="eqId"/>" />
	<s:hidden id="documentId" name="document.id" theme="simple"></s:hidden>
	<div class="order_table">
		<table>
			<tr>
				<th>文档名称：</th>
				<td><s:textfield id="title" name="document.title" type="text"></s:textfield><font color="red">*</font></td>
				<th>文档类型：</th>
				<td><s:textfield id="fileType" name="document.fileType" type="text"></s:textfield></td>
			</tr>
			<tr>
				<th>上传文件：</th>
				<td colspan="3"><input type="file" id="fileToUpload" name="fileToUpload" style="width: 90%;" /></td>
			</tr>
			<tr>
				<th>文件备注：</th>
				<td colspan="3" style="padding-top: 5px;"><textarea id="note" name="document.note" rows="1" cols="1" style="width: 90%; height: 50px;"><s:property value="document.note" escape="false" /></textarea></td>
			</tr>

		</table>
	</div>

</s:form>