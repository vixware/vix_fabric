<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售后管理 </span><span>> 客户服务 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixCustomerServiceAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>客户服务</h2>
		</header>
		<div class="widget-body">
			<form id="customerServiceForm" class="form-horizontal">
				<input type="hidden" id="id" name="customerServices.id" value="${customerServices.id}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>主题:</label>
						<div class="col-md-3">
							<input id="subject" name="customerServices.subject" value="${customerServices.subject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>执行人:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="customerServices.employee.id" value="${customerServices.employee.id}" /> 
								<input id="employeeName" name="customerServices.employee.name" value="${customerServices.employee.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseEmployee();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="customerServices.customerAccount.id" value="${customerServices.customerAccount.id}" /> 
								<input id="customerName" name="customerServices.customerAccount.name" value="${customerServices.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">联系人:</label>
						<div class="col-md-3">
							<select id="contactPersonId" name="customerServices.contactPerson.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${contactPersonList}" var="entity">
									<option value="${entity.id}" <c:if test="${customerServices.contactPerson.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">服务类型:</label>
						<div class="col-md-3">
							<select id="serviceTypeId" name="customerServices.serviceType.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${serviceTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${customerServices.serviceType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">服务方式:</label>
						<div class="col-md-3">
							<select id="serviceModeId" name="customerServices.serviceMode.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${serviceModeList}" var="entity">
									<option value="${entity.id}" <c:if test="${customerServices.serviceMode.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开始日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="startDate" name="customerServices.startDate" value="${customerServices.startDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>状态:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <c:if test="${customerServices.status == 0}">active</c:if>"> 
									<input type="radio" value="0" id="status" name="customerServices.status" <c:if test="${customerServices.status == 0}">checked="checked"</c:if> class="validate[required]" >无需处理
								</label> 
								<label class="btn btn-default <c:if test="${customerServices.status == 1}">active</c:if>"> 
									<input type="radio" value="1" id="status" name="customerServices.status" <c:if test="${customerServices.status == 1}">checked="checked"</c:if> class="validate[required]" >未处理
								</label>
								<label class="btn btn-default <c:if test="${customerServices.status == 2}">active</c:if>"> 
									<input type="radio" value="2" id="status" name="customerServices.status" <c:if test="${customerServices.status == 2}">checked="checked"</c:if> class="validate[required]" >处理中
								</label> 
								<label class="btn btn-default <c:if test="${customerServices.status == 3}">active</c:if>"> 
									<input type="radio" value="3" id="status" name="customerServices.status" <c:if test="${customerServices.status == 3}">checked="checked"</c:if> class="validate[required]" >处理完成
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">服务内容:</label>
						<div class="col-md-8">
							<textarea id="serviceContent" name="customerServices.serviceContent" class="form-control" rows="4">${customerServices.serviceContent}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">客户反馈:</label>
						<div class="col-md-8">
							<textarea id="customerFeedback" name="customerServices.customerFeedback" class="form-control" rows="4">${customerServices.customerFeedback}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="customerServices.memo" class="form-control" rows="4">${customerServices.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixCustomerServiceAction!goList.action');">
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
	$("#customerServiceForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#customerServiceForm").validationEngine('validate')) {
			$("#customerServiceForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixCustomerServiceAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				showMessage(result);
				loadContent('', '${nvix}/nvixnt/nvixCustomerServiceAction!goList.action');
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