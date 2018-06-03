<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-book"></i> 招聘管理 <span onclick="">&gt; 用人申请</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goApplyList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>用人申请</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="planForm">
				<input type="hidden" id="applicationId" name="hrRecruitApplication.id" value="${hrRecruitApplication.id}" />
				<fieldset>
				<legend>基本信息</legend>
				    <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 申请编号:</label>
						<div class="col-md-3">
							<input id="applicationNumber" name="hrRecruitApplication.applicationNumber" value="${hrRecruitApplication.applicationNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					    <label class="col-md-2 control-label"><span class="text-danger">*</span> 申请名称:</label>
						<div class="col-md-3">
							<input id="applicationName" name="hrRecruitApplication.applicationName" value="${hrRecruitApplication.applicationName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 申请职位:</label>
						<div class="col-md-3">
							<input id="jobApplication" name="hrRecruitApplication.jobApplication" value="${hrRecruitApplication.jobApplication}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}申请部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="applyTreeMenu" class="input-group">
										<input id="applicationDepartment" name="hrRecruitApplication.applicationDepartment" type="text" onfocus="showApplyOrg(); return false;" value="${hrRecruitApplication.applicationDepartment}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#applicationDepartment').val('');">清空</button>
										</div>
										<div id="applyMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="applyTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>审批状态:</label>
						<div class="col-md-3">
							<select id="approvalStatus" name="hrRecruitApplication.approvalStatus" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${hrRecruitApplication.approvalStatus == "1"}'>selected="selected"</c:if>>通过</option>
								<option value="2" <c:if test='${hrRecruitApplication.approvalStatus == "2"}'>selected="selected"</c:if>>未通过</option>
								<option value="3" <c:if test='${hrRecruitApplication.approvalStatus == "3"}'>selected="selected"</c:if>>待议</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>申请日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="applicationDate" name="hrRecruitApplication.applicationDate" value="${hrRecruitApplication.applicationDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'applicationDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>用工到位日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="laborTime" name="hrRecruitApplication.laborTime" value="${hrRecruitApplication.laborTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'laborTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">申请理由:</label>
						<div class="col-md-8">
							<textarea id="reasonOfTheApplication" name="hrRecruitApplication.reasonOfTheApplication" class="form-control" rows="4">${hrRecruitApplication.reasonOfTheApplication}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="remarks" name="hrRecruitApplication.remarks" class="form-control" rows="4">${hrRecruitApplication.remarks}</textarea>
						</div>
					</div>
				 	<legend>申请内容</legend>
					<div class="form-group">
					<label class="col-md-2 control-label"></label>
						<div class="col-md-8">
						  <textarea id="noticeContent" name="hrRecruitApplication.noticeContent">${hrRecruitApplication.noticeContent}</textarea>
						  <script type="text/javascript" src="${nvix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
						        var editor = KindEditor.create('#noticeContent',
										{
											width:"100%",
											height:300,
											cssPath : '${nvix}/plugin/KindEditor/plugins/code/prettify.css',
											uploadJson : '${nvix}/nvixnt/hr/nvixRecruitStaffAction!uploadFile.action',
											allowFileManager : true 
										}
									);
						  </script>
					  </div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goApplyList.action');">
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
		editor.sync();
		if ($("#planForm").validationEngine('validate')) {
			$("#planForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixRecruitStaffAction!saveOrUpdateApply.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixRecruitStaffAction!goApplyList.action');
			}
			});
		} else {
			return false;
		}
	};
	/** 初始化部门下拉列表树 */
	var showApplyOrg = initDropListTree("applyTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "applicationDepartment", "applyTree", "applyMenu");  
</script>