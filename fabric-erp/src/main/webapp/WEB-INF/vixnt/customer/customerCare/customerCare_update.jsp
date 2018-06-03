<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售后管理 </span><span>> 客户关怀 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixCustomerCareAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>客户关怀</h2>
		</header>
		<div class="widget-body">
			<form id="customerCareForm" class="form-horizontal">
				<input type="hidden" id="id" name="customerCare.id" value="${customerCare.id}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>主题:</label>
						<div class="col-md-3">
							<input id="subject" name="customerCare.subject" value="${customerCare.subject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>执行人:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="customerCare.employee.id" value="${customerCare.employee.id}" /> 
								<input id="employeeName" name="customerCare.employee.name" value="${customerCare.employee.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseEmployee();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="customerCare.customerAccount.id" value="${customerCare.customerAccount.id}" /> 
								<input id="customerName" name="customerCare.customerAccount.name" value="${customerCare.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">联系人:</label>
						<div class="col-md-3">
							<select id="contactPersonId" name="customerCare.contactPerson.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${contactPersonList}" var="entity">
									<option value="${entity.id}" <c:if test="${customerCare.contactPerson.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>项目:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="projectId" name="customerCare.crmProject.id" value="${customerCare.crmProject.id}" /> 
								<input id="projectName" name="customerCare.crmProject.subject" value="${customerCare.crmProject.subject}" onfocus="chooseCrmProject();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCrmProject();">选择</span>
							</div>
						</div> --%>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>关怀日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="createTime" name="customerCare.createTime" value="${customerCare.createTimeStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'createTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">关怀类型:</label>
						<div class="col-md-3">
							<select id="customerCareTypeId" name="customerCare.customerCareType.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${customerCareTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${customerCare.customerCareType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">关怀内容:</label>
						<div class="col-md-3">
							<textarea id="careContent" name="customerCare.careContent" class="form-control" rows="4">${customerCare.careContent}</textarea>
						</div>
						<label class="col-md-2 control-label">客户反馈:</label>
						<div class="col-md-3">
							<textarea id="customerFeedback" name="customerCare.customerFeedback" class="form-control" rows="4">${customerCare.customerFeedback}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="customerCare.memo" class="form-control" rows="4">${customerCare.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixCustomerCareAction!goList.action');">
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
	$("#customerCareForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#customerCareForm").validationEngine('validate')) {
			$("#customerCareForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixCustomerCareAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				showMessage(result);
				loadContent('', '${nvix}/nvixnt/nvixCustomerCareAction!goList.action');
			}
			});
		}
	}
	
	/** 加载联系人*/
	function loadContactPerson() {
		if ($("#customerId").val() != '') {
			$.ajax({
			url : '${nvix}/nvixnt/nvixContactPersonAction!loadContactPersonOption.action?parentId=' + $("#customerId").val(),
			cache : false,
			success : function(html) {
				var contactPersonId = $("#contactPersonId").val();
				$("#contactPersonId").html(html);
				if (contactPersonId != '') {
					$("#contactPersonId").val(contactPersonId);
				}
			}
			});
		}
	}
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer',function(){
			loadContactPerson();
		});
	}
	
	function chooseCrmProject() {
		chooseListData('${nvix}/nvixnt/nvixCrmProjectAction!goChooseCrmProject.action', 'single', '选择项目', 'project');
	}
	
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	}
</script>