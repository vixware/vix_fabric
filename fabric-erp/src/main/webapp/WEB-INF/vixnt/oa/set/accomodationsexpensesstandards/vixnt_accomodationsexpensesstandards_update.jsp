<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="transportForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixAccomodationsExpensesStandardsAction!saveOrUpdate.action">
	<input type="hidden" id="areaExpensesStandardsId" name="areaExpensesStandards.id" value="${areaExpensesStandards.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
			<div class="col-md-10">
				<input id="standardName" name="areaExpensesStandards.standardName" value="${areaExpensesStandards.standardName}" data-prompt-position="centerRight" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>岗位:</label>
			<div class="col-md-4">
				<select id="areaExpensesStandardsId" name="areaExpensesStandards.orgPosition.id" data-prompt-position="centerRight" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${orgPositionList}" var="entity">
						<option value="${entity.id}" <c:if test="${areaExpensesStandards.orgPosition.id == entity.id}">selected="selected"</c:if>>${entity.posName}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>出差区域:</label>
			<div class="col-md-4">
				<select id="areaLevelId" name="areaExpensesStandards.areaLevel.id" data-prompt-position="centerRight" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${areaLevelList}" var="entity">
						<option value="${entity.id}" <c:if test="${areaExpensesStandards.areaLevel.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>报销标准:</label>
			<div class="col-md-4">
				<input id="reimbursementAmount" name="areaExpensesStandards.reimbursementAmount" value="${areaExpensesStandards.reimbursementAmount}" data-prompt-position="centerRight" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea name="areaExpensesStandards.memo" class="form-control">${areaExpensesStandards.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#transportForm").validationEngine();
</script>