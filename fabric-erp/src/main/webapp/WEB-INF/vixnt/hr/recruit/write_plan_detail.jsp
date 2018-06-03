<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="planItemForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/hr/nvixRecruitPlanAction!saveOrUpdatePlanDetail.action">
	<input id="planDetailId" name="planDetail.id" value="${planDetail.id}" type="hidden" />
	<input id="planDetailHrplanId" name="planDetail.hrRecruitplan.id" value="${planDetail.hrRecruitplan.id}" type="hidden" />
	
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划内容:</label>
			<div class="col-md-3">
				<input id="projectContent" name="planDetail.projectContent" value="${planDetail.projectContent}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 招聘职位:</label>
			<div class="col-md-3">
				<input id="theJob" name="planDetail.theJob" value="${planDetail.theJob}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 职位说明:</label>
			<div class="col-md-3">
				<input id="jobDescription" name="planDetail.jobDescription" value="${planDetail.jobDescription}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 工作职责:</label>
			<div class="col-md-3">
				<input id="operatingDuty" name="planDetail.operatingDuty" value="${planDetail.operatingDuty}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
	    <div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 负责人:</label>
			<div class="col-md-3">
				<input id="leadingOfficial" name="planDetail.leadingOfficial" value="${planDetail.leadingOfficial}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 工作地点:</label>
			<div class="col-md-3">
				<input id="jobAddress" name="planDetail.jobAddress" value="${planDetail.jobAddress}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 招聘要求:</label>
			<div class="col-md-3">
				<input id="recruitmentRequirements" name="planDetail.recruitmentRequirements" value="${planDetail.recruitmentRequirements}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 费用预算:</label>
			<div class="col-md-3">
				<input id="expenseBudget" name="planDetail.expenseBudget" value="${planDetail.expenseBudget}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
	   	<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>计划开始时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="plannedStartTime" name="planDetail.plannedStartTime" value="${planDetail.plannedStartTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'plannedStartTimeStr'});">
					<i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>计划结束时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="plannedEndTime" name="planDetail.plannedEndTime" value="${planDetail.plannedEndTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'plannedEndTimeStr'});">
					<i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
  </fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#planItemForm").validationEngine();
</script>
