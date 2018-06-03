<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>> 售前管理 </span><span>>销售活动 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixSaleActivityAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>销售活动</h2>
		</header>
		<div class="widget-body">
			<form id="activityForm" class="form-horizontal">
				<input type="hidden" id="id" name="activity.id" value="${activity.id}" /> 
				<input type="hidden" id="isDeleted" name="activity.isDeleted" value="${activity.isDeleted}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>活动主题:</label>
						<div class="col-md-3">
							<input id="title" name="activity.title" value="${activity.title}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>活动:</label>
						<div class="col-md-3">
							<input id="activity" name="activity.activity" value="${activity.activity}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div> --%>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="activity.customerAccount.id" value="${activity.customerAccount.id}" /> 
								<input id="customerName" name="activity.customerAccount.name" value="${activity.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">项目:</label>
						<div class="col-md-3">
							<select id="crmProjectId" name="activity.crmProject.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${crmProjectList}" var="entity">
									<option value="${entity.id}" <c:if test="${activity.crmProject.id == entity.id}">selected="selected"</c:if>>${entity.subject}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>负责人:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="activity.personInCharge.id" value="${activity.personInCharge.id}" /> 
								<input id="employeeName" name="activity.personInCharge.name" value="${activity.personInCharge.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseEmployee();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>活动类型:</label>
						<div class="col-md-3">
							<select id="saleActivityId" name="activity.saleActivity.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${saleActivityList}" var="entity">
									<option value="${entity.id}" <c:if test="${activity.saleActivity.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">活动日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="date" name="activity.date" value="${activity.dateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'date'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>地址:</label>
						<div class="col-md-3">
							<input id="address" name="activity.address" value="${activity.address}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>货币类型:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="activity.currencyType.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${activity.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>预计费用:</label>
						<div class="col-md-3">
							<input id="estimatedCost" name="activity.estimatedCost" value="${activity.estimatedCost}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>发生费用:</label>
						<div class="col-md-3">
							<input id="costsIncurred" name="activity.costsIncurred" value="${activity.costsIncurred}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>批准费用:</label>
						<div class="col-md-3">
							<input id="approvalOfFees" name="activity.approvalOfFees" value="${activity.approvalOfFees}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>预计销售额:</label>
						<div class="col-md-3">
							<input id="projectedSales" name="activity.projectedSales" value="${activity.projectedSales}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开始日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="startDate" name="activity.startDate" value="${activity.startDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>结束日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="endDate" name="activity.endDate" value="${activity.endDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>内容:</label>
						<div class="col-md-8">
							<textarea id="content" name="activity.content" class="form-control validate[required]" rows="4">${activity.content}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixSaleActivityAction!goList.action');">
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
	$("#activityForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#activityForm").validationEngine('validate')) {
			var startTime = $("#startDate").val() + " 00:00:01";;
			var endTime = $("#endDate").val() + " 00:00:01";;
			var tag = dateTimeRange(startTime,endTime);
			if(!tag){
				layer.alert("结束时间不能小于开始时间!");
				return tag;
			}else{
				$("#activityForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/nvixSaleActivityAction!saveOrUpdate.action?id=" + $("#id").val(),
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					success : function(result) {
						showMessage(result);
						loadContent('', '${nvix}/nvixnt/nvixSaleActivityAction!goList.action');
					}
				});
			}
		}
	}
	
	// 加载项目
	function loadCrmProject() {
		if ($("#customerId").val() != '') {
			$.ajax({
			url : '${nvix}/nvixnt/nvixCrmProjectAction!loadCrmProject.action?id=' + $("#customerId").val(),
			cache : false,
			success : function(html) {
				var crmProjectId = $("#crmProjectId").val();
				$("#crmProjectId").html(html);
			}
			});
		}
	}
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer',function(){
			loadCrmProject();
		});
	}
	
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	}
</script>