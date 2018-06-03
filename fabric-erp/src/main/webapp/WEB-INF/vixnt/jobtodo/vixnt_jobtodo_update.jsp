<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="jobTodoForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/vreport/nvixJobTodoAction!audit.action">
	<input type="hidden" id="id" name="id" value="${id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"> 审批意见:</label>
			<div class="col-md-8">
				<textarea id="opinion" name="opinion" class="form-control"> </textarea>
			</div>
		</div>
		<s:if test="listConditionPath.size > 1">
			<div class="form-group">
				<label class="col-md-2 control-label"> 审批:</label>
				<s:iterator value="listConditionPath" var="ld" status="s">
					<div class="col-md-8">
						<input type="radio" id="approval_${s.count}" name="conditionRule" value='${ld.conditionRule}' />${ld.name}</div>
				</s:iterator>
			</div>
		</s:if>
		<s:else>
			<s:iterator value="listConditionPath" var="ld">
				<input type="radio" id="approval_link_dto" name="conditionRule" value='${ld.conditionRule}' checked="checked" style="display: none;" />
			</s:iterator>
		</s:else>
	</fieldset>
</form>
<script type="text/javascript">
	$("#jobTodoForm").validationEngine();
</script>