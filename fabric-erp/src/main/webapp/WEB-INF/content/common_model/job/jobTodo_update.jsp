<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#jobTodoForm").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="jobTodoForm">
		<input type="hidden" id="taskId" value="${jobTodo.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="center">审批意见</td>
					<td align="center" colspan="${linkDtoSize}"><textarea rows="5" cols="60" id="opinion"></textarea></td>
				</tr>
				<s:if test="listConditionPath.size > 1">
					<tr>
						<td>审批：</td>
						<s:iterator value="listConditionPath" var="ld" status="s">
							<td><input type="radio" id="approval_${s.count}" name="conditionRule" value='${ld.conditionRule}' />${ld.name}</td>
						</s:iterator>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="listConditionPath" var="ld">
						<input type="radio" id="approval_link_dto" name="conditionRule" value='${ld.conditionRule}' checked="checked" style="display: none;" />
					</s:iterator>
				</s:else>
			</table>
		</div>
	</form>
</div>