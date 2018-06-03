<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>人事事务管理<span>> 离职 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goDimission.action');">
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
			<form class="form-horizontal" id="regularForm">
			    <input type="hidden" id="regularId" name="regular.id" value="${regular.id}" />
			    <input type="hidden" id="types" name="regular.types" value="dimission" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 事务主题:</label>
						<div class="col-md-3">
							<input id="theme" name="regular.theme" value="${regular.theme}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 姓名:</label>
						<div class="col-md-3">
							<input id="litigant" name="regular.litigant" value="${regular.litigant}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 工号:</label>
						<div class="col-md-3">
							<input id="jobNumber" name="regular.jobNumber" value="${regular.jobNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 批准文号:</label>
						<div class="col-md-3">
							<input id="licenseNumber" name="regular.licenseNumber" value="${regular.licenseNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>离职类别:</label>
						<div class="col-md-3">
							<select id="becomeFullMemberState" name="regular.becomeFullMemberState" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${regular.becomeFullMemberState == "1"}'>selected="selected"</c:if>>离职</option>
								<option value="2" <c:if test='${regular.becomeFullMemberState == "2"}'>selected="selected"</c:if>>离退</option>
								<option value="3" <c:if test='${regular.becomeFullMemberState == "3"}'>selected="selected"</c:if>>辞退</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>事务状态:</label>
						<div class="col-md-3">
							<select id="transactionState" name="regular.transactionState" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${regular.transactionState == "1"}'>selected="selected"</c:if>>通过</option>
								<option value="0" <c:if test='${regular.transactionState == "0"}'>selected="selected"</c:if>>待议</option>
								<option value="-1" <c:if test='${regular.transactionState == "-1"}'>selected="selected"</c:if>>未通过</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 所属部门:</label>
						<div class="col-md-3">
							<input id="probationDepartment" name="regular.probationDepartment" value="${regular.probationDepartment}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 所属职位:</label>
						<div class="col-md-3">
							<input id="probationPost" name="regular.probationPost" value="${regular.probationPost}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">审批人:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="regular.employee.id" value="${regular.employee.id}"/>
								<input id="employeeName" name="regular.employee.name" value="${regular.employee.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseEmployee();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>批准日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="licenseDate" name="regular.licenseDate" value="${regular.licenseTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'licenseTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">离职原因:</label>
						<div class="col-md-8">
							<textarea id="becomeFullMembercause" name="regular.becomeFullMembercause" class="form-control" rows="4">${regular.becomeFullMembercause}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goDimission.action');">
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
	$("#regularForm").validationEngine();
	function saveOrUpdate() {
		if ($("#regularForm").validationEngine('validate')) {
			$("#regularForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixHrManageAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixHrManageAction!goDimission.action');
			}
			});
		} else {
			return false;
		}
	};
	
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/hr/nvixHrManageAction!goCheckPersonChoose.action', 'single', '审批人', 'employee');
	}
</script>