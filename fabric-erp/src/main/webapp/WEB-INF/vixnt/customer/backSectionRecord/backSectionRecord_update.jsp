<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>> 售中管理 </span><span>>回款记录 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixBackSectionRecordAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>回款记录</h2>
		</header>
		<div class="widget-body">
			<form id="backSectionRecordForm" class="form-horizontal" >
				<input type="hidden" id="id" name="backSectionRecord.id" value="${backSectionRecord.id}" /> 
				<input type="hidden" id="isDeleted" name="backSectionRecord.isDeleted" value="${backSectionRecord.isDeleted}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>回款日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="backSectionDate" name="backSectionRecord.backSectionDate" value="${backSectionRecord.backSectionDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'backSectionDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>期次:</label>
						<div class="col-md-3">
							<input id="stageNumber" name="backSectionRecord.stageNumber" value="${backSectionRecord.stageNumber}" data-prompt-position="topLeft" class="form-control validate[required,custom[integer]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="backSectionRecord.customerAccount.id" value="${backSectionRecord.customerAccount.id}" /> 
								<input id="customerName" name="backSectionRecord.customerAccount.name" value="${backSectionRecord.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">销售订单:</label>
						<div class="col-md-3">
							<select id="salesOrderId" name="backSectionRecord.salesOrder.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${salesOrderList}" var="entity">
									<option value="${entity.id}" <c:if test="${backSectionRecord.salesOrder.id == entity.id}">selected="selected"</c:if>>${entity.title}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">项目:</label>
						<div class="col-md-3">
							<select id="crmProjectId" name="backSectionRecord.crmProject.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${crmProjectList}" var="entity">
									<option value="${entity.id}" <c:if test="${backSectionRecord.crmProject.id == entity.id}">selected="selected"</c:if>>${entity.subject}</option>
								</c:forEach>
							</select>
							<%-- <div class="input-group">
								<input type="hidden" id="crmProjectId" name="backSectionRecord.crmProject.id" value="${backSectionRecord.crmProject.id}" /> 
								<input id="crmProjectName" name="backSectionRecord.crmProject.subject" value="${backSectionRecord.crmProject.subject}" onfocus="chooseCrmProject();" data-prompt-position="topLeft" class="form-control" type="text" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCrmProject();">选择</span>
							</div> --%>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>所有者:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="ownerId" name="backSectionRecord.owner.id" value="${backSectionRecord.owner.id}" /> 
								<input id="ownerName" name="backSectionRecord.owner.name" value="${backSectionRecord.owner.name}" onfocus="chooseOwner();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseOwner();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>金额:</label>
						<div class="col-md-3">
							<input id="amount" name="backSectionRecord.amount" value="${backSectionRecord.amount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
						</div>
						<label class="col-md-2 control-label">外汇备注:</label>
						<div class="col-md-3">
							<input id="foreignCurrencyMemo" name="backSectionRecord.foreignCurrencyMemo" value="${backSectionRecord.foreignCurrencyMemo}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否开票:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <c:if test="${backSectionRecord.isBilling == 1}">active</c:if>"> 
									<input type="radio" value="1" id="isBilling" name="backSectionRecord.isBilling" <c:if test="${backSectionRecord.isBilling == 1}">checked="checked"</c:if> class="validate[required]" >已开
								</label> 
								<label class="btn btn-default <c:if test="${backSectionRecord.isBilling == 0}">active</c:if>"> 
									<input type="radio" value="0" id="isBilling" name="backSectionRecord.isBilling" <c:if test="${backSectionRecord.isBilling == 0}">checked="checked"</c:if> class="validate[required]" >未开
								</label>
								<label class="btn btn-default <c:if test="${backSectionRecord.isBilling == 2}">active</c:if>"> 
									<input type="radio" value="2" id="isBilling" name="backSectionRecord.isBilling" <c:if test="${backSectionRecord.isBilling == 2}">checked="checked"</c:if> class="validate[required]" >不开发票
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>付款方式:</label>
						<div class="col-md-3">
							<select id="paymentTypeId" name="backSectionRecord.paymentType.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${paymentTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${backSectionRecord.paymentType.id == entity.id}">selected="selected"</c:if>>${entity.payment}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">付款分类:</label>
						<div class="col-md-3">
							<select id="paymentCategoryId" name="backSectionRecord.paymentCategory.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${paymentCategoryList}" var="entity">
									<option value="${entity.id}" <c:if test="${backSectionRecord.paymentCategory.id == entity.id}">selected="selected"</c:if>>${entity.payment}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="backSectionRecord.memo" class="form-control" rows="4">${backSectionRecord.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixBackSectionRecordAction!goList.action');">
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
	$("#backSectionRecordForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#backSectionRecordForm").validationEngine('validate')) {
			$("#backSectionRecordForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixBackSectionRecordAction!saveOrUpdate.action?id=" + $("#id").val(),
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					showMessage(result);
					loadContent('', '${nvix}/nvixnt/nvixBackSectionRecordAction!goList.action');
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
</script>