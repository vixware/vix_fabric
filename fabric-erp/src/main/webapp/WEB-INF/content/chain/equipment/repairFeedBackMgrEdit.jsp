<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style type="text/css">
.order dd table td {
	padding: 3px 5px;
}
</style>
<script type="text/javascript">
$(function(){
	loadRepairFeedback();
});

function loadRepairFeedback(){
	_p_a_load('${vix}/chain/mmxEquipmentAction!repairMgrFeedbackList.action?id=${repairRecord.id}','feedback_list');	
}

var isSaving = false;
function saveOrUpdateRepairFeedback(){
	if(isSaving)
		return;
	isSaving = true;

	$("#repairFeedbackForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/chain/mmxEquipmentAction!saveRepairFeedback.action",
	     dataType: "text",
	     success: function(result){
	    	if(result>0){
		    	var dataId = result;
				$('#repairFeedbackForm #feedbackNote').val('');
				$('#repairFeedbackForm #fileToUpload').val('');				
		    	
				setTimeout(loadRepairFeedback, 700);
		    	
		    	showMessage('信息保存完毕');
		    	
		    	isSaving = false;
	    	}else{
	    		showErrorMessage('信息保存失败');
		    	isSaving = false;
	    	}
	     }
	 });
	return false;
}
</script>

<div class="content" style="margin-top: 10px;">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> </span>
			</dt>
			<dd class="clearfix">
				<div class="order_table">
					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b class="downup"></b> <strong>基本信息</strong>
							</dt>
							<dd style="display: block;">
								<table style="border: none;">
									<tr>
										<td align="right" width="100">门店：</td>
										<td>${repairRecord.mendian}</td>
										<td align="right"></td>
										<td></td>
									</tr>
									<tr>
										<td align="right">设备：</td>
										<td>${repairRecord.eqTitle}</td>
										<td align="right"></td>
										<td></td>
									</tr>
									<tr>
										<td align="right" width="100">报修说明：</td>
										<td colspan="3">${repairRecord.note}</td>
									</tr>
									<tr>
										<td align="right">相关附件：</td>
										<td colspan="3"><s:if test="repairRecord.docFileName!=null">
												<a href="${vix}/chain/mmxEquipmentAction!downloadRepairDoc.action?id=${repairRecord.id}">下载文件</a>
											</s:if></td>
									</tr>
								</table>
							</dd>
						</dl>
					</div>

					<form id="repairFeedbackForm" action="" method="post">
						<input type="hidden" id="repairRecordId" name="repairFeedback.repairId" value="${repairRecord.id}" />
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>提交反馈信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">反馈内容：</td>
											<td colspan="3"><textarea id="feedbackNote" name="repairFeedback.note" style="width: 450px; height: 50px;"></textarea></td>
										</tr>
										<tr>
											<td align="right">相关附件：</td>
											<td colspan="3"><input type="file" id="fileToUpload" name="fileToUpload" style="width: 450px;" /></td>
										</tr>
										<tr>
											<td align="right"></td>
											<td colspan="3"><input name="" type="button" onclick="saveOrUpdateRepairFeedback();" class="btn" value="提交" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</form>

					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b class="downup"></b> <strong>反馈信息</strong>
							</dt>
							<dd id="feedback_list" style="display: block;"></dd>
						</dl>
					</div>

				</div>
			</dd>
		</dl>
	</div>
</div>