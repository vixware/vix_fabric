<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="backSectionPlanForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdateBackSectionPlan.action">
		<input type="hidden" id="id" name="backSectionPlan.id" value="${backSectionPlan.id}" /> 
		<input type="hidden" id="isDeleted" name="backSectionPlan.isDeleted" value="${backSectionPlan.isDeleted}" /> 
		<input type="hidden" id="backSectionPlanContractId" name="backSectionPlan.contract.id" value="${backSectionPlan.contract.id}" /> 
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
						<input id="customerName" name="backSectionPlan.customerAccount.name" value="${backSectionPlan.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" /> 
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
				<label class="col-md-2 control-label"><span class="text-danger">*</span>金额:</label>
				<div class="col-md-3">
					<input id="amount" name="backSectionPlan.amount" value="${backSectionPlan.amount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
				</div>
				<label class="col-md-2 control-label">外汇备注:</label>
				<div class="col-md-3">
					<input id="foreignCurrencyMemo" name="backSectionPlan.foreignCurrencyMemo" value="${backSectionPlan.foreignCurrencyMemo}" class="form-control" type="text" />
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
						<input id="chargerName" name="backSectionPlan.charger.name" value="${backSectionPlan.charger.name}" onfocus="chooseCharger();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" /> 
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
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">备注:</label>
				<div class="col-md-8">
					<textarea id="memo" name="backSectionPlan.memo" class="form-control" rows="4">${backSectionPlan.memo}</textarea>
				</div>
			</div>
		</fieldset>
	</form>
<script type="text/javascript">
	$("#backSectionPlanForm").validationEngine();

	$(function(){
		$("#backSectionPlanContractId").val($("#contractId").val());
	})
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
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer',function(){
			loadSalesOrder();
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
</script>