<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="planItemForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/hr/nvixRecruitStaffAction!saveOrUpdateActivityDetail.action">
	<input id="activityDetailId" name="activitityDetail.id" value="${activitityDetail.id}" type="hidden" />
	<input id="activityId" name="activitityDetail.hrRecruitactivitity.id" value="${activitityDetail.hrRecruitactivitity.id}" type="hidden" />
	
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}招聘组织部门:</label>
			<div class="col-md-3">
				<div class="row">
					<div class="col-sm-12">
						<div id="applyTreeMenu" class="input-group">
							<input id="orgDepartment" name="activitityDetail.orgDepartment" type="text" onfocus="showApplyOrg(); return false;" value="${activitityDetail.orgDepartment}" type="text" readonly="readonly" class="form-control validate[required]" />
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#orgDepartment').val('');">清空</button>
							</div>
							<div id="applyMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
								<ul id="applyTree" class="ztree"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 招聘职位:</label>
			<div class="col-md-3">
				<input id="job" name="activitityDetail.job" value="${activitityDetail.job}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>发布类型:</label>
			<div class="col-md-3">
				<select id="publicationType" name="activitityDetail.publicationType" class="form-control validate[required]">
				    <option value="">请选择</option>
					<option value="1" <c:if test='${activitityDetail.publicationType == "1"}'>selected="selected"</c:if>>内部</option>
					<option value="2" <c:if test='${activitityDetail.publicationType == "2"}'>selected="selected"</c:if>>外部</option>
					<option value="3" <c:if test='${activitityDetail.publicationType == "3"}'>selected="selected"</c:if>>内部和外部</option>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>发布状态:</label>
			<div class="col-md-3">
				<select id="publicationStruts" name="activitityDetail.publicationStruts" class="form-control validate[required]">
				    <option value="">请选择</option>
					<option value="1" <c:if test='${activitityDetail.publicationStruts == "1"}'>selected="selected"</c:if>>已发布</option>
					<option value="2" <c:if test='${activitityDetail.publicationStruts == "2"}'>selected="selected"</c:if>>未发布</option>
				</select>
			</div>
		</div>
	    <div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>有限期限:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lifeSpan" name="activitityDetail.lifeSpan" value="${activitityDetail.lifeSpaStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'lifeSpaStr'});">
					<i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 工作地点:</label>
			<div class="col-md-3">
				<input id="workingPlace" name="activitityDetail.workingPlace" value="${activitityDetail.workingPlace}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 招聘在编人数:</label>
			<div class="col-md-3">
				<input id="recruitmentInTheSeriesNumber" name="activitityDetail.recruitmentInTheSeriesNumber" value="${activitityDetail.recruitmentInTheSeriesNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 招聘非在编人数:</label>
			<div class="col-md-3">
				<input id="recruitmentOfTheUnofficialNumber" name="activitityDetail.recruitmentOfTheUnofficialNumber" value="${activitityDetail.recruitmentOfTheUnofficialNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
	   	<div class="form-group">
			<label class="col-md-2 control-label">招聘要求:</label>
			<div class="col-md-8">
				<textarea id="recruitmentRequirements" name="activitityDetail.recruitmentRequirements" class="form-control" rows="4">${activitityDetail.recruitmentRequirements}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">工作职责:</label>
			<div class="col-md-8">
				<textarea id="operatingDuty" name="activitityDetail.operatingDuty" class="form-control" rows="4">${activitityDetail.operatingDuty}</textarea>
			</div>
		</div>
  </fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#planItemForm").validationEngine();
	/** 初始化部门下拉列表树 */
	var showApplyOrg = initDropListTree("applyTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "orgDepartment", "applyTree", "applyMenu");  
</script>
