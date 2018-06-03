<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="schedulingForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/schedulingAction!saveOrUpdate.action" method="post" enctype="multipart/form-data">
	<input type="hidden" id="id" name="scheduling.id" value="${scheduling.id}" /> <input type="hidden" id="orgId" name="scheduling.org.id" value="${scheduling.org.id}" /> <input type="hidden" id="unitId" name="scheduling.unit.id" value="${scheduling.unit.id}" /> <input type="hidden" id="empId" name="scheduling.emp.id" value="${scheduling.emp.id}" />
	<fieldset>
		<s:if test="scheduling.org.id != null">
			<div class="form-group">
				<label class="col-md-2 control-label"><span class="text-danger">*</span>公司名称:</label>
				<div class="col-md-4">
					<input id="orgName" name="scheduling.org.briefName" value="${scheduling.org.briefName}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" readonly="readonly" />
				</div>
				<%-- <label class="col-md-2 control-label">是否启用:</label>
				<div class="col-md-4">
					<div data-toggle="buttons" class="btn-group">
						<label class="btn btn-default <s:if test="scheduling.isEnable == 1">active</s:if>">
							<input type="radio" value="1" id="isEnable" name="periodRule.isEnable" <s:if test="periodRule.isEnable == 1">checked="checked"</s:if>>是
						</label>
						<label class="btn btn-default <s:if test="scheduling.isEnable == 0">active</s:if>">
							<input type="radio" value="0" id="isEnable" name="scheduling.isEnable" <s:if test="scheduling.isEnable == 0">checked="checked"</s:if>>否
						</label>
					</div>
				</div> --%>
			</div>
		</s:if>
		<s:elseif test="scheduling.unit.id != null">
			<div class="form-group">
				<label class="col-md-2 control-label"><span class="text-danger">*</span>部门名称:</label>
				<div class="col-md-4">
					<input id="unitName" name="scheduling.unit.fs" value="${scheduling.unit.fs}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" readonly="readonly" />
				</div>
				<%-- <label class="col-md-2 control-label">是否启用:</label>
				<div class="col-md-4">
					<div data-toggle="buttons" class="btn-group">
						<label class="btn btn-default <s:if test="scheduling.isEnable == 1">active</s:if>">
							<input type="radio" value="1" id="isEnable" name="scheduling.isEnable" <s:if test="scheduling.isEnable == 1">checked="checked"</s:if>>是
						</label>
						<label class="btn btn-default <s:if test="scheduling.isEnable == 0">active</s:if>">
							<input type="radio" value="0" id="isEnable" name="scheduling.isEnable" <s:if test="scheduling.isEnable == 0">checked="checked"</s:if>>否
						</label>
					</div>
				</div> --%>
			</div>
		</s:elseif>
		<s:else>
			<div class="form-group">
				<label class="col-md-2 control-label"><span class="text-danger">*</span>职员姓名:</label>
				<div class="col-md-4">
					<input id="empName" name="scheduling.emp.name" value="${scheduling.emp.name}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" readonly="readonly" />
				</div>
			</div>
		</s:else>
		<div class="form-group">
			<label class="col-md-2 control-label">是否启用:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="scheduling.isEnable == 1">active</s:if>"> <input type="radio" value="1" id="isEnable" name="scheduling.isEnable" <s:if test="scheduling.isEnable == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="scheduling.isEnable == 0">active</s:if>"> <input type="radio" value="0" id="isEnable" name="scheduling.isEnable" <s:if test="scheduling.isEnable == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>班次:</label>
			<div class="col-md-4">
				<select id="attRuleSetId" name="scheduling.attRuleSet.id" data-prompt-position="topLeft" class="form-control">
					<option value="">------请选择------</option>
					<c:forEach items="${attRuleSetList}" var="entity">
						<option value="${entity.id}" <c:if test="${scheduling.attRuleSet.id == entity.id}">selected="selected"</c:if>>${entity.shiftName}</option>
					</c:forEach>
				</select>
			</div>
			<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>时间段:</label>
			<div class="col-md-4">
				<select id="periodRuleId" name="scheduling.periodRule.id" data-prompt-position="topLeft" class="form-control">
					<option value="">------请选择------</option>
					<c:forEach items="${periodRuleList}" var="entity">
						 <option value="${entity.id}" <c:if test="${scheduling.periodRule.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div> --%>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">执行开始日期:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="scheduleStartTime" name="scheduling.scheduleStartTime" value="${scheduling.scheduleStartTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'scheduleStartTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">执行结束日期:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="scheduleEndTime" name="scheduling.scheduleEndTime" value="${scheduling.scheduleEndTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'scheduleEndTime'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea rows="4" id="memo" name="scheduling.memo" value="${scheduling.memo}" class="form-control">${scheduling.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#attSettingForm").validationEngine();
</script>