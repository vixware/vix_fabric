<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var isSaving = false;
	function submitsAttachForm() {
		if (isSaving)
			return;
		isSaving = true;

		if ($('#attachForm #fileToUpload').val() == '') {
			showMessage('请选择文件');
			return false;
		}

		$("#attachForm").ajaxSubmit({
		type : "post",
		url : "${vix}/purchase/purchaseApplyAction!uploadAttachments.action",
		dataType : "text",
		success : function(result) {
			if (result == 'success') {
				showMessage('信息保存完毕');
				reloadDataGrid2();

				isSaving = false;
				$.close('popModalWin');
			} else {
				showErrorMessage('信息保存失败');
				isSaving = false;
			}
		}
		});
		return false;
	}
</script>
<div class="content" style="background: #DCE7F1">
	<form id="attachForm" method="post">
		<input type="hidden" name="purchaseId" value="${purchaseId}" />
		<div style="margin: 15px;">
			<input type="file" id="fileToUpload" name="fileToUpload" maxlength="30" style="text-align: right;" />
		</div>
	</form>
</div>