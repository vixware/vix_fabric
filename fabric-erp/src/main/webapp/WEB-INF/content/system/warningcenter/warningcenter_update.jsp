<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#timedTaskForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="timedTaskForm">
		<input type="hidden" id="timedTaskId" name="timedTaskId" value="${timedTask.id}" /> <input type="hidden" id="warningTypeId" name="warningTypeId" value="${timedTask.warningType.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">任务源：</td>
					<td><input id="timedTaskName" name="timedTaskName" type="text" size="20" value="${timedTask.warningType.typeName}" /></td>
					<td align="right">任务类型：</td>
					<td><input id="type" name="type" type="text" size="20" value="${timedTask.type}" /></td>
				</tr>
				<tr height="40">
					<td align="right">任务主题：</td>
					<td><input id="subject" name="subject" type="text" size="20" value="${timedTask.subject}" /></td>
					<td align="right">任务内容：</td>
					<td><input id="taskcontent" name="taskcontent" type="text" size="20" value="${timedTask.content}" /></td>
				</tr>
				<tr height="40">
					<td align="right">创建日期：</td>
					<td><input id="entityTime" name="entityTime" value="<s:date name="%{timedTask.entityTime}" format="yyyy-MM-dd"/>" type="text" /> <img onclick="showTime('entityTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="middle"></td>

				</tr>
			</table>
		</div>
	</form>
</div>