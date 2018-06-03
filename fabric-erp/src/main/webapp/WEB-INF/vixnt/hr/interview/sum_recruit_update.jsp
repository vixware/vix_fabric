<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-book"></i> 招聘管理<span onclick="">&gt; 招聘总结</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixInterviewAction!goSumRecruit.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>新增招聘总结</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="planForm">
				<input type="hidden" id="hrRecruitSummaryId" name="hrRecruitSummary.id" value="${hrRecruitSummary.id}" />
				<fieldset>
				<legend>基本信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 招聘计划姓名:</label>
						<div class="col-md-3">
							<input id="recruitmentPlanName" name="hrRecruitSummary.recruitmentPlanName" value="${hrRecruitSummary.recruitmentPlanName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 招聘活动名称:</label>
						<div class="col-md-3">
							<input id="recruitmentActivityName" name="hrRecruitSummary.recruitmentActivityName" value="${hrRecruitSummary.recruitmentActivityName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
					    <label class="col-md-2 control-label"><span class="text-danger">*</span> 计划招聘人数:</label>
						<div class="col-md-3">
							<input id="recruitmentPlanning" name="hrRecruitSummary.recruitmentPlanning" value="${hrRecruitSummary.recruitmentPlanning}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 实际招聘人数:</label>
						<div class="col-md-3">
							<input id="actualBigintOfRecruitment" name="hrRecruitSummary.actualBigintOfRecruitment" value="${hrRecruitSummary.actualBigintOfRecruitment}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 已招聘职位:</label>
						<div class="col-md-3">
							<input id="hasTheJob" name="hrRecruitSummary.hasTheJob" value="${hrRecruitSummary.hasTheJob}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 实际费用:</label>
						<div class="col-md-3">
							<input id="expensePocket" name="hrRecruitSummary.expensePocket" value="${hrRecruitSummary.expensePocket}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}负责招聘部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="forOrgTreeMenu" class="input-group">
										<input id="responsibleForRecruitmentDepartment" name="hrRecruitSummary.responsibleForRecruitmentDepartment" type="text" onfocus="showForplanOrg(); return false;" value="${hrRecruitSummary.responsibleForRecruitmentDepartment}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#responsibleForRecruitmentDepartment').val('');">清空</button>
										</div>
										<div id="forlanOrgMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="forplanOrgTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 负责招聘人:</label>
						<div class="col-md-3">
							<input id="recruiter" name="hrRecruitSummary.recruiter" value="${hrRecruitSummary.recruiter}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 实际开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="actualStartTime" name="hrRecruitSummary.actualStartTime" value="${hrRecruitSummary.actualStartTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'actualStartTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>实际结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="actualEndTime" name="hrRecruitSummary.actualEndTime" value="${hrRecruitSummary.actualEndTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'actualEndTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 招聘成功率:</label>
						<div class="col-md-3">
							<input id="successRateOfRecruitment" name="hrRecruitSummary.successRateOfRecruitment" value="${hrRecruitSummary.successRateOfRecruitment}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" placeholder="%"/>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 审批状态:</label>
						<div class="col-md-3">
							<select id="approvalStatus" name="hrRecruitSummary.approvalStatus" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${hrRecruitSummary.approvalStatus == "1"}'>selected="selected"</c:if>>通过</option>
								<option value="2" <c:if test='${hrRecruitSummary.approvalStatus == "2"}'>selected="selected"</c:if>>未通过</option>
								<option value="3" <c:if test='${hrRecruitSummary.approvalStatus == "3"}'>selected="selected"</c:if>>待议</option>
							</select>
						</div>
					</div>
				 	<div class="form-group">
						<label class="col-md-2 control-label">招聘结果评价:</label>
						<div class="col-md-8">
							<textarea id="comment" name="hrRecruitSummary.comment" class="form-control" rows="4">${hrRecruitSummary.comment}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="remark" name="hrRecruitSummary.remark" class="form-control" rows="4">${hrRecruitSummary.remark}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixInterviewAction!goSumRecruit.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#planForm").validationEngine();
	function saveOrUpdate() {
		if ($("#planForm").validationEngine('validate')) {
			$("#planForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixInterviewAction!saveOrUpdateSumRecruit.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixInterviewAction!goSumRecruit.action');
			}
			});
		} else {
			return false;
		}
	};
	/** 初始化部门下拉列表树 */
	var showForplanOrg = initDropListTree("forOrgTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "responsibleForRecruitmentDepartment", "forplanOrgTree", "forlanOrgMenu"); 
</script>