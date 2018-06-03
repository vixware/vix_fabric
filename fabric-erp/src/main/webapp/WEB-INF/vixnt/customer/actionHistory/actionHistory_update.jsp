<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售前管理 </span><span>> 行动历史管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixActionHistoryAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>行动历史</h2>
		</header>
		<div class="widget-body">
			<form id="actionHistoryForm" class="form-horizontal">
				<input type="hidden" id="id" name="actionHistory.id" value="${actionHistory.id}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>主题:</label>
						<div class="col-md-3">
							<input id="subject" name="actionHistory.subject" value="${actionHistory.subject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>行动日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="actionDate" name="actionHistory.actionDate" value="${actionHistory.actionDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'actionDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="actionHistory.customerAccount.id" value="${actionHistory.customerAccount.id}" /> 
								<input id="customerName" name="actionHistory.customerAccount.name" value="${actionHistory.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>项目:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="projectId" name="actionHistory.crmProject.id" value="${actionHistory.crmProject.id}" /> 
								<input id="projectName" name="actionHistory.crmProject.subject" value="${actionHistory.crmProject.subject}" onfocus="chooseCrmProject();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCrmProject();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>描述:</label>
						<div class="col-md-8">
							<textarea id="description" name="actionHistory.description" class="form-control validate[required]" rows="4">${actionHistory.description}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixActionHistoryAction!goList.action');">
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
	$("#actionHistoryForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#actionHistoryForm").validationEngine('validate')) {
			$("#actionHistoryForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixActionHistoryAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				showMessage(result);
				loadContent('', '${nvix}/nvixnt/nvixActionHistoryAction!goList.action');
			}
			});
		}
	}
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer');
	}
	
	function chooseCrmProject() {
		chooseListData('${nvix}/nvixnt/nvixCrmProjectAction!goChooseCrmProject.action', 'single', '选择项目', 'project');
	}
</script>