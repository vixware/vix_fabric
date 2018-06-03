<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>人事事务管理<span>> 返聘</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goRehire.action');">
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
			    <input type="hidden" id="types" name="regular.types" value="rehire" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 事务主题:</label>
						<div class="col-md-3">
							<input id="theme" name="regular.theme" value="${regular.theme}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 甲方名称:</label>
						<div class="col-md-3">
							<input id="litigant" name="regular.litigant" value="${regular.litigant}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 身份证号:</label>
						<div class="col-md-3">
							<input id="iDNumber" name="regular.iDNumber" value="${regular.iDNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 批准文号:</label>
						<div class="col-md-3">
							<input id="licenseNumber" name="regular.licenseNumber" value="${regular.licenseNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 电话:</label>
						<div class="col-md-3">
							<input id="telephone" name="regular.telephone" value="${regular.telephone}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 甲方地址:</label>
						<div class="col-md-3">
							<input id="jiaAddress" name="regular.jiaAddress" value="${regular.jiaAddress}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 乙方名称:</label>
						<div class="col-md-3">
							<input id="yiName" name="regular.yiName" value="${regular.yiName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 乙方住址:</label>
						<div class="col-md-3">
							<input id="yiAddress" name="regular.yiAddress" value="${regular.yiAddress}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>返聘类型:</label>
						<div class="col-md-3">
							<select id="becomeFullMemberState" name="regular.becomeFullMemberState" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${regular.becomeFullMemberState == "1"}'>selected="selected"</c:if>>技术顾问</option>
								<option value="2" <c:if test='${regular.becomeFullMemberState == "2"}'>selected="selected"</c:if>>业务顾问</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 返聘后所属部门:</label>
						<div class="col-md-3">
							<input id="afterThePromotionDepartment" name="regular.afterThePromotionDepartment" value="${regular.afterThePromotionDepartment}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 返聘后所属职位:</label>
						<div class="col-md-3">
							<input id="afterThePromotionPost" name="regular.afterThePromotionPost" value="${regular.afterThePromotionPost}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>返聘开始日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="leaveStartDate" name="regular.leaveStartDate" value="${regular.leaveStartTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'leaveStartTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>返聘结束日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="leaveEndDate" name="regular.leaveEndDate" value="${regular.leaveEndTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'leaveEndTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
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
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goRehire.action');">
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
				loadContent('', '${nvix}/nvixnt/hr/nvixHrManageAction!goRehire.action');
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