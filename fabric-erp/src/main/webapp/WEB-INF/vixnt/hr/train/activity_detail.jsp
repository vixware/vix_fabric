<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="planItemForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/hr/nvixTrainAction!saveOrUpdateActivityDetail.action">
	<input id="trainDetailId" name="trainDetail.id" value="${trainDetail.id}" type="hidden" />
	<input id="trainDetailHrplanId" name="trainDetail.trainingPlanning.id" value="${trainDetail.trainingPlanning.id}" type="hidden" />
	
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 培训方式:</label>
			<div class="col-md-3">
				<input id="trainingWay" name="trainDetail.trainingWay" value="${trainDetail.trainingWay}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 培训目标:</label>
			<div class="col-md-3">
				<input id="trainingGoal" name="trainDetail.trainingGoal" value="${trainDetail.trainingGoal}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 师资来源:</label>
			<div class="col-md-3">
				<input id="sourcesOfTeachers" name="trainDetail.sourcesOfTeachers" value="${trainDetail.sourcesOfTeachers}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 培训课程:</label>
			<div class="col-md-3">
				<input id="trainingCourse" name="trainDetail.trainingCourse" value="${trainDetail.trainingCourse}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
	    <div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 培训地点:</label>
			<div class="col-md-3">
				<input id="trainingSite" name="trainDetail.trainingSite" value="${trainDetail.trainingSite}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 培训机构:</label>
			<div class="col-md-3">
				<input id="trainingInstitutions" name="trainDetail.trainingInstitutions" value="${trainDetail.trainingInstitutions}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
	   	<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>计划开始时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="planStartDate" name="trainDetail.planStartDate" value="${trainDetail.planStartDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'planStartDateStr'});">
					<i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>计划结束时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="planEndDate" name="trainDetail.planEndDate" value="${trainDetail.planEndDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'planEndDateStr'});">
					<i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 课程学时:</label>
			<div class="col-md-3">
				<input id="curriculumClassHours" name="trainDetail.curriculumClassHours" value="${trainDetail.curriculumClassHours}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 培训内容:</label>
			<div class="col-md-3">
				<input id="trainingContent" name="trainDetail.trainingContent" value="${trainDetail.trainingContent}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
  </fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#planItemForm").validationEngine();
</script>
