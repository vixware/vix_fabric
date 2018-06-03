<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训活动<span>> 新增 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goActivityList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>基本信息</h2> 
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="planForm">
				<input type="hidden" id="activityId" name="activity.id" value="${activity.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 活动编码:</label>
						<div class="col-md-3">
							<input id="activityCode" name="activity.activityCode" value="${activity.activityCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 活动名称:</label>
						<div class="col-md-3">
							<input id="activityName" name="activity.activityName" value="${activity.activityName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}所属部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="departmentTreeMenu" class="input-group">
										<input id="activityDepartmentOrLeadings" name="activity.activityDepartmentOrLeadings" type="text" onfocus="showHrplanOrg(); return false;" value="${activity.activityDepartmentOrLeadings}" type="text" style="cursor:pointer;" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#activityDepartmentOrLeadings').val('');">清空</button>
										</div>
										<div id="departmentMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="departmentTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>预算费用:</label>
						<div class="col-md-3">
							<input id="budgetExpense" name="activity.budgetExpense" value="${activity.budgetExpense}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
				    <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>拟培训时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="trainingTime" name="activity.trainingTime" value="${activity.trainingTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'trainingTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>活动开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="activityStartDate" name="activity.activityStartDate" value="${activity.activityStartDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'activityStartDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>活动结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="activityEndDate" name="activity.activityEndDate" value="${activity.activityEndDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'activityEndDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>培训方式:</label>
						<div class="col-md-3">
							<select id="trainingMethod" name="activity.trainingMethod" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${activity.trainingMethod == "1"}'>selected="selected"</c:if>>学习</option>
								<option value="2" <c:if test='${activity.trainingMethod == "2"}'>selected="selected"</c:if>>授课</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>考核方式:</label>
						<div class="col-md-3">
							<select id="examinationMethod" name="activity.examinationMethod" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${activity.examinationMethod == "1"}'>selected="selected"</c:if>>笔记</option>
								<option value="2" <c:if test='${activity.examinationMethod == "2"}'>selected="selected"</c:if>>评价</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">培训内容:</label>
						<div class="col-md-8">
							<textarea id="trainingContent" name="activity.trainingContent" class="form-control" rows="4">${activity.trainingContent}</textarea>
						</div>
					</div>
					
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goActivityList.action');">
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
			url : "${nvix}/nvixnt/hr/nvixTrainProjectAction!saveOrUpdateActivity.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixTrainProjectAction!goActivityList.action');
			}
			});
		} else {
			return false;
		}
	};
   
	/** 初始化部门下拉列表树 */
	var showHrplanOrg = initDropListTree("departmentTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "activityDepartmentOrLeadings", "departmentTree", "departmentMenu");
</script>