<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<style type="text/css">
.newvoucher dl dd table.feedback_info {
	border-bottom: 1px solid #aaaaaa;
	margin-bottom: 8px;
	margin-left: 30px;
	padding: 10px;
	width: 90%;
}

.newvoucher dl dd table.feedback_info td {
	padding: 3px 8px;
}
</style>
<script type="text/javascript">
	$(function() {
		//$('#mmx_equipment_grid_table').addGridDropdownAction('操作1','javascript:void(0)');
	});

	function deleteRepairFeedback(id) {
		asyncbox.confirm('确定要删除数据?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : "${vix}/chain/mmxEquipmentAction!deleteRepairFeedbackRecord.action",
				data : "id=" + id,
				dataType : "text",
				success : function(data) {
					if (data == 'success') {
						$('#fb_data_block_' + id).remove();
						showMessage('数据删除完毕');
					} else {
						showErrorMessage('操作失败');
					}
				}
				});
			}
		});
	}
</script>

<s:iterator value="pager.resultList" var="entity" status="s">
	<table id="fb_data_block_${entity.id}" class="feedback_info">
		<tr>
			<td align="right" width="130">反馈人：</td>
			<td>${entity.submitUser}</td>
			<td align="right" width="180">反馈时间：</td>
			<td><span style="float: left;"><s:date name="#entity.submitTime" format="yyyy-MM-dd hh:mm" /></span> <a href="javascript:void(0)" onclick="deleteRepairFeedback('${entity.id}')" style="float: right; margin-right: 8px;">删除</a></td>
		</tr>
		<s:if test="#entity.docFileName!=null">
			<tr>
				<td align="right" width="130">反馈附件：</td>
				<td><a href="${vix}/chain/mmxEquipmentAction!downloadRepairFeedbackDoc.action?id='${entity.id}'">下载文件</a></td>
				<td align="right"></td>
				<td></td>
			</tr>
		</s:if>
		<tr>
			<td align="right">反馈内容：</td>
			<td colspan="3">${entity.note}</td>
		</tr>
	</table>
</s:iterator>
