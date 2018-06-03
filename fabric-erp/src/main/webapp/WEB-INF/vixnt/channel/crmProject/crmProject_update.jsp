<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 渠道管理 <span>>渠道项目报备 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/channel/vixntChannelCrmProjectAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>项目报备</h2>
		</header>
		<div class="widget-body">
			<form id="crmProjectForm" class="form-horizontal">
				<input type="hidden" id="id" name="crmProject.id" value="${crmProject.id}" /> 
				<input type="hidden" id="isDeleted" name="crmProject.isDeleted" value="${crmProject.isDeleted}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>项目主题:</label>
						<div class="col-md-3">
							<input id="subject" name="crmProject.subject" value="${crmProject.subject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="crmProject.customerAccount.id" value="${crmProject.customerAccount.id}" /> 
								<input id="customerName" name="crmProject.customerAccount.name" value="${crmProject.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>项目状态:</label>
						<div class="col-md-3">
							<select id="projectStatusId" name="crmProject.projectStatus.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${projectStatusList}" var="entity">
									<option value="${entity.id}" <c:if test="${crmProject.projectStatus.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>项目阶段:</label>
						<div class="col-md-3">
							<select id="projectStageId" name="crmProject.projectStage.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${projectStageList}" var="entity">
									<option value="${entity.id}" <c:if test="${crmProject.projectStage.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>负责人:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="crmProject.personInCharge.id" value="${crmProject.personInCharge.id}" /> 
								<input id="employeeName" name="crmProject.personInCharge.name" value="${crmProject.personInCharge.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseEmployee();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>立项时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="projectEstablishDate" name="crmProject.projectEstablishDate" value="${crmProject.projectEstablishDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'projectEstablishDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">可能性:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="possibility" name="crmProject.possibility" value="${crmProject.possibility}" data-prompt-position="topLeft" class="form-control validate[custom[number],min[1],max[100]]" type="text" />
								<span class="input-group-addon">(1-100)%</span>
							</div>
						</div>
						<label class="col-md-2 control-label">售前阶段:</label>
						<div class="col-md-3">
							<select id="projectSalePreviousStageId" name="crmProject.projectSalePreviousStage.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${projectSalePreviousStageList}" var="entity">
									<option value="${entity.id}" <c:if test="${crmProject.projectSalePreviousStage.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">预计费用:</label>
						<div class="col-md-3">
							<input id="forecastMoney" name="crmProject.forecastMoney" value="${crmProject.forecastMoney}" data-prompt-position="topLeft" class="form-control validate[custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label">预计签单日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="forecastSignDate" name="crmProject.forecastSignDate" value="${crmProject.forecastSignDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'forecastSignDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>渠道:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="channelDistributorId" name="crmProject.channelDistributor.id" value="${crmProject.channelDistributor.id}" /> 
								<input id="channelDistributorName" name="crmProject.channelDistributor.name" value="${crmProject.channelDistributor.name}" onfocus="chooseChannelDistributor();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseChannelDistributor();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">项目阶段备注:</label>
						<div class="col-md-8">
							<textarea id="projectStageMemo" name="crmProject.projectStageMemo" class="form-control" rows="4">${crmProject.projectStageMemo}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">项目概要:</label>
						<div class="col-md-8">
							<textarea id="projectSummary" name="crmProject.projectSummary" class="form-control" rows="4">${crmProject.projectSummary}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-primary" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/channel/vixntChannelCrmProjectAction!goList.action');">
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
	$("#crmProjectForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#crmProjectForm").validationEngine('validate')) {
			$("#crmProjectForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/channel/vixntChannelCrmProjectAction!saveOrUpdate.action?id=" + $("#id").val(),
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					showMessage(result);
					loadContent('', '${nvix}/nvixnt/channel/vixntChannelCrmProjectAction!goList.action');
				}
			});
		}
	}
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer');
	}
	
	function chooseChannelDistributor() {
		chooseListData('${nvix}/nvixnt/channel/vixntChannelAction!goChooseChannelDistributor.action', 'single', '选择渠道', 'channelDistributor');
	}
	
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	}
</script>