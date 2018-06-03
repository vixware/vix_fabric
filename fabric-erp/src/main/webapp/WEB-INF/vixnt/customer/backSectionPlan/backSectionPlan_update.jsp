<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>> 售中管理 </span><span>>回款计划 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-success" type="button" onclick="saveOrUpdateAdd();">
					<i class="glyphicon glyphicon-plus"></i> 保存并新增
				</button>
				<button class="btn btn-default" type="button" onclick="clearInfo();">
					<i class="glyphicon glyphicon-repeat"></i> 清空
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixBackSectionPlanAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>回款计划</h2>
		</header>
		<div class="widget-body">
			<form id="backSectionPlanForm" class="form-horizontal" >
				<input type="hidden" id="id" name="backSectionPlan.id" value="${backSectionPlan.id}" /> 
				<input type="hidden" id="isDeleted" name="backSectionPlan.isDeleted" value="${backSectionPlan.isDeleted}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>计划回款日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="backSectionPlanDate" name="backSectionPlan.backSectionPlanDate" value="${backSectionPlan.backSectionPlanDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'backSectionPlanDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>期次:</label>
						<div class="col-md-3">
							<input id="stageNumber" name="backSectionPlan.stageNumber" value="${backSectionPlan.stageNumber}" data-prompt-position="topLeft" class="form-control validate[required,custom[integer]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="backSectionPlan.customerAccount.id" value="${backSectionPlan.customerAccount.id}" /> 
								<input id="customerName" name="backSectionPlan.customerAccount.name" value="${backSectionPlan.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">销售订单:</label>
						<div class="col-md-3">
							<select id="salesOrderId" name="backSectionPlan.salesOrder.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${salesOrderList}" var="entity">
									<option value="${entity.id}" <c:if test="${backSectionPlan.salesOrder.id == entity.id}">selected="selected"</c:if>>${entity.title}</option>
								</c:forEach>
							</select>
						</div>
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>项目:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="crmProjectId" name="backSectionPlan.crmProject.id" value="${backSectionPlan.crmProject.id}" /> 
								<input id="crmProjectName" name="backSectionPlan.crmProject.subject" value="${backSectionPlan.crmProject.subject}" onfocus="chooseCrmProject();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCrmProject();">选择</span>
							</div>
						</div> --%>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">项目:</label>
						<div class="col-md-3">
							<select id="crmProjectId" name="backSectionPlan.crmProject.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${crmProjectList}" var="entity">
									<option value="${entity.id}" <c:if test="${backSectionPlan.crmProject.id == entity.id}">selected="selected"</c:if>>${entity.subject}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>金额:</label>
						<div class="col-md-3">
							<input id="amount" name="backSectionPlan.amount" value="${backSectionPlan.amount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>所有者:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="ownerId" name="backSectionPlan.owner.id" value="${backSectionPlan.owner.id}" /> 
								<input id="ownerName" name="backSectionPlan.owner.name" value="${backSectionPlan.owner.name}" onfocus="chooseOwner();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseOwner();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>负责人:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="chargerId" name="backSectionPlan.charger.id" value="${backSectionPlan.charger.id}" /> 
								<input id="chargerName" name="backSectionPlan.charger.name" value="${backSectionPlan.charger.name}" onfocus="chooseCharger();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCharger();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>回款状态:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <c:if test="${backSectionPlan.backSectionPlanStatus == 1}">active</c:if>"> 
									<input type="radio" value="1" id="backSectionPlanStatus" name="backSectionPlan.backSectionPlanStatus" <c:if test="${backSectionPlan.backSectionPlanStatus == 1}">checked="checked"</c:if> class="validate[required]" >是
								</label> 
								<label class="btn btn-default <c:if test="${backSectionPlan.backSectionPlanStatus == 0}">active</c:if>"> 
									<input type="radio" value="0" id="backSectionPlanStatus" name="backSectionPlan.backSectionPlanStatus" <c:if test="${backSectionPlan.backSectionPlanStatus == 0}">checked="checked"</c:if> class="validate[required]" >否
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label">外汇备注:</label>
						<div class="col-md-3">
							<input id="foreignCurrencyMemo" name="backSectionPlan.foreignCurrencyMemo" value="${backSectionPlan.foreignCurrencyMemo}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="backSectionPlan.memo" class="form-control" rows="4">${backSectionPlan.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-success" type="button" onclick="saveOrUpdateAdd();">
								<i class="glyphicon glyphicon-plus"></i> 保存并新增
							</button>
							<button class="btn btn-default" type="button" onclick="clearInfo();">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixBackSectionPlanAction!goList.action');">
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
	$("#backSectionPlanForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#backSectionPlanForm").validationEngine('validate')) {
			$("#backSectionPlanForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixBackSectionPlanAction!saveOrUpdate.action?id=" + $("#id").val(),
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					var r = result.split(":");
					showMessage(r[0]);
					loadContent('', '${nvix}/nvixnt/nvixBackSectionPlanAction!goList.action');
				}
			});
		}
	}
	
	function saveOrUpdateAdd(id) {
		//表单验证
		if ($("#backSectionPlanForm").validationEngine('validate')) {
			$("#backSectionPlanForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixBackSectionPlanAction!saveOrUpdate.action?id=" + $("#id").val(),
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					var r = result.split(":");
					showMessage(r[0]);
					goSaveOrUpdateEntity('${nvix}/nvixnt/nvixBackSectionPlanAction!goSaveOrUpdateAdd.action?id=' + r[1]);
				}
			});
		}
	}
	
	/** 加载销售订单 */
	function loadSalesOrder() {
		if ($("#customerId").val() != '') {
			$.ajax({
			url : '${nvix}/nvixnt/nvixSalesInvoiceAction!loadSalesOrder.action?id=' + $("#customerId").val(),
			cache : false,
			success : function(html) {
				var salesOrderId = $("#salesOrderId").val();
				$("#salesOrderId").html(html);
				/* if (contactPersonId != '') {
					$("#contactPersonId").val(contactPersonId);
				} */
			}
			});
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
			loadSalesOrder();
			loadCrmProject();
		});
	}
	function chooseCrmProject() {
		var customerId = $("#customerId").val();
		chooseListData('${nvix}/nvixnt/nvixBackSectionPlanAction!goChooseCrmProject.action?customerId=' + customerId, 'single', '选择项目', 'crmProject');
	}
	function chooseSaleOrder() {
		var customerId = $("#customerId").val();
		chooseListData('${nvix}/nvixnt/nvixBackSectionPlanAction!goChooseSaleOrder.action?customerId=' + customerId, 'single', '选择订单', 'saleOrder');
	}
	
	function chooseOwner() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'owner');
	}
	function chooseCharger() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'charger');
	}
	
	function clearInfo(){
		$("#id").val("");
		$("#backSectionPlanDate").val("");
		$("#stageNumber").val("");
		$("#customerId").val("");
		$("#customerName").val("");
		$("#salesOrderId").val("");
		$("#amount").val("");
		$("#foreignCurrencyMemo").val("");
		$("#ownerId").val("");
		$("#ownerName").val("");
		$("#chargerId").val("");
		$("#chargerName").val("");
		$("#memo").val("");
	}
</script>