<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-book"></i> 甄选录用<span onclick="">&gt; 录用管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixInterviewAction!goEmployList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>新增录用管理</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="planForm">
				<input type="hidden" id="hrHirManageId" name="hrHirManage.id" value="${hrHirManage.id}" />
				<fieldset>
				<legend>基本信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 应聘人姓名:</label>
						<div class="col-md-3">
							<input id="candidateName" name="hrHirManage.candidateName" value="${hrHirManage.candidateName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 性别:</label>
						<div class="col-md-3">
							<select id="sex" name="hrHirManage.sex" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${hrHirManage.sex == "1"}'>selected="selected"</c:if>>男</option>
								<option value="2" <c:if test='${hrHirManage.sex == "2"}'>selected="selected"</c:if>>女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
					    <label class="col-md-2 control-label"><span class="text-danger">*</span> 年龄:</label>
						<div class="col-md-3">
							<input id="age" name="hrHirManage.age" value="${hrHirManage.age}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}应聘部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="forOrgTreeMenu" class="input-group">
										<input id="applicantsDepartment" name="hrHirManage.applicantsDepartment" type="text" onfocus="showForplanOrg(); return false;" value="${hrHirManage.applicantsDepartment}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#applicantsDepartment').val('');">清空</button>
										</div>
										<div id="forlanOrgMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="forplanOrgTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 笔试结果:</label>
						<div class="col-md-3">
							<input id="writtenTestResults" name="hrHirManage.writtenTestResults" value="${hrHirManage.writtenTestResults}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 面试结果:</label>
						<div class="col-md-3">
							<input id="resultsOfInterview" name="hrHirManage.resultsOfInterview" value="${hrHirManage.resultsOfInterview}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 应聘职位:</label>
						<div class="col-md-3">
							<input id="employmentObjective" name="hrHirManage.employmentObjective" value="${hrHirManage.employmentObjective}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 预计到岗日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="expectedArrivalDate" name="hrHirManage.expectedArrivalDate" value="${hrInterviewManagement.expectedArrivalDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'expectedArrivalDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 转入类别:</label>
						<div class="col-md-3">
							<select id="intoTheCategory" name="hrHirManage.intoTheCategory" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${hrHirManage.intoTheCategory == "1"}'>selected="selected"</c:if>>普通人才</option>
								<option value="2" <c:if test='${hrHirManage.intoTheCategory == "2"}'>selected="selected"</c:if>>后备人才</option>
								<option value="3" <c:if test='${hrHirManage.intoTheCategory == "3"}'>selected="selected"</c:if>>高级人才</option>
							</select>
						</div>
					</div>
				 	<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="remarks" name="hrHirManage.remarks" class="form-control" rows="4">${hrHirManage.remarks}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">录用意见:</label>
						<div class="col-md-8">
							<textarea id="employmentViews" name="hrHirManage.employmentViews" class="form-control" rows="4">${hrHirManage.employmentViews}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixInterviewAction!goEmployList.action');">
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
			url : "${nvix}/nvixnt/hr/nvixInterviewAction!saveOrUpdateEmploy.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixInterviewAction!goEmployList.action');
			}
			});
		} else {
			return false;
		}
	};
	/** 初始化部门下拉列表树 */
	var showForplanOrg = initDropListTree("forOrgTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "applicantsDepartment", "forplanOrgTree", "forlanOrgMenu"); 
</script>