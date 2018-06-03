<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>> 售前管理 </span><span>>销售机会 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixChanceAndTrackingAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>销售机会</h2>
		</header>
		<div class="widget-body">
			<form id="saleChanceForm" class="form-horizontal">
				<input type="hidden" id="id" name="saleChance.id" value="${saleChance.id}" /> 
				<input type="hidden" id="isDeleted" name="saleChance.isDeleted" value="${saleChance.isDeleted}" /> 
				<input type="hidden" id="createTime" name="saleChance.createTime" value="${saleChance.createTimeStr}" /> 
				<input type="hidden" id="updateTime" name="saleChance.updateTime" value="${saleChance.updateTimeStr}" /> 
				<fieldset>
					<legend>机会信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>机会主题:</label>
						<div class="col-md-3">
							<input id="projectName" name="saleChance.subject" value="${saleChance.subject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>销售机会状态:</label>
						<div class="col-md-3">
							<select id="saleChanceStatusId" name="saleChance.saleChanceStatus.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${saleChanceStatusList}" var="entity">
									<option value="${entity.id}" <c:if test="${saleChance.saleChanceStatus.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="saleChance.customerAccount.id" value="${saleChance.customerAccount.id}" /> 
								<input id="customerName" name="saleChance.customerAccount.name" value="${saleChance.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">联系人:</label>
						<div class="col-md-3">
							<select id="contactPersonId" name="saleChance.contactPerson.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${contactPersonList}" var="entity">
									<option value="${entity.id}" <c:if test="${saleChance.contactPerson.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">项目:</label>
						<div class="col-md-3">
							<select id="crmProjectId" name="saleChance.crmProject.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${crmProjectList}" var="entity">
									<option value="${entity.id}" <c:if test="${saleChance.crmProject.id == entity.id}">selected="selected"</c:if>>${entity.subject}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<legend>基本情况</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>发现时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="findDate" name="saleChance.findDate" value="${saleChance.findDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'findDate'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">销售机会来源:</label>
						<div class="col-md-3">
							<select id="saleSourceId" name="saleChance.saleSource.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${saleSourceList}" var="entity">
									<option value="${entity.id}" <c:if test="${saleChance.saleSource.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">负责人:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="saleChance.employee.id" value="${saleChance.employee.id}" />  
								<input id="employeeName" name="saleChance.employee.name" value="${saleChance.employee.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseEmployee();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">提供人:</label>
						<div class="col-md-3">
							<input id="sourcePerson" name="saleChance.sourcePerson" value="${saleChance.sourcePerson}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">销售机会类型:</label>
						<div class="col-md-3">
							<select id="saleTypeId" name="saleChance.saleType.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${saleTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${saleChance.saleType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">客户需求:</label>
						<div class="col-md-8">
							<textarea id="requirement" name="saleChance.requirement" class="form-control" rows="4">${saleChance.requirement}</textarea>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>领导:</label>
						<div class="col-md-3">
							<input id="leadSource" name="saleChance.leadSource" value="${saleChance.leadSource}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>创建人:</label>
						<div class="col-md-3">
							<input id="createdBy" name="saleChance.createdBy" value="${saleChance.createdBy}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-3">
							<input id="memo" name="saleChance.memo" value="${saleChance.memo}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div> --%>
					<legend>预期</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>预期金额:</label>
						<div class="col-md-3">
							<input id="expectedValue" name="saleChance.expectedValue" value="${saleChance.expectedValue}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>预计签单日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="preOrderDate" name="saleChance.preOrderDate" value="${saleChance.preOrderDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'preOrderDate'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<legend>当前状态</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>阶段:</label>
						<div class="col-md-3">
							<select id="saleStageId" name="saleChance.saleStage.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${saleStageList}" var="entity">
									<option value="${entity.id}" <c:if test="${saleChance.saleStage.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">阶段停留:</label>
						<div class="col-md-3">
							<input id="phaseStay" name="saleChance.phaseStay" value="${saleChance.stagnateDay}天" class="form-control" type="text" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">阶段备注:</label>
						<div class="col-md-8">
							<input id="phase" name="saleChance.phase" value="${saleChance.phase}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">可能性(百分比):</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="possibility" name="saleChance.possibility" value="${saleChance.possibility}" data-prompt-position="topLeft" class="form-control validate[custom[number],min[1],max[100]]" type="text" />
								<span class="input-group-addon">(1-100)%</span>
							</div>
						</div>
						<label class="col-md-2 control-label">竞争对手可能性(百分比):</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="compaignProbability" name="saleChance.compaignProbability" value="${saleChance.compaignProbability}" data-prompt-position="topLeft" class="form-control validate[custom[number],min[1],max[100]]" type="text" />
								<span class="input-group-addon">(1-100)%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">货币类型:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="saleChance.currencyType.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${saleChance.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">关闭日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="dateClosed" name="saleChance.dateClosed" value="${saleChance.dateClosedStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'dateClosed'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">下一步计划:</label>
						<div class="col-md-8">
							<input id="nextStepPlan" name="saleChance.nextStepPlan" value="${saleChance.nextStepPlan}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">概率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="probability" name="saleChance.probability" value="${saleChance.probability}" data-prompt-position="topLeft" class="form-control validate[custom[number],min[1],max[100]]" type="text" />
								<span class="input-group-addon">(1-100)%</span>
							</div>
						</div>
						<label class="col-md-2 control-label">预测结算日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="precastCleanDate" name="saleChance.precastCleanDate" value="${saleChance.precastCleanDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'precastCleanDate'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">预测结束日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="precastCloseDate" name="saleChance.precastCloseDate" value="${saleChance.precastCloseDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'precastCloseDate'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">预计费用:</label>
						<div class="col-md-3">
							<input id="estimatedCost" name="saleChance.estimatedCost" value="${saleChance.estimatedCost}" data-prompt-position="topLeft" class="form-control validate[custom[number]]" type="text" />
						</div>
					</div>
					<legend>费用信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label">结算日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="cleanDate" name="saleChance.cleanDate" value="${saleChance.cleanDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'cleanDate'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">毛利总计:</label>
						<div class="col-md-3">
							<input id="grossTotalMargin" name="saleChance.grossTotalMargin" value="${saleChance.grossTotalMargin}" data-prompt-position="topLeft" class="form-control validate[custom[number]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">金额:</label>
						<div class="col-md-3">
							<input id="amount" name="saleChance.amount" value="${saleChance.amount}" data-prompt-position="topLeft" class="form-control validate[custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label">数量:</label>
						<div class="col-md-3">
							<input id="count" name="saleChance.count" value="${saleChance.count}" data-prompt-position="topLeft" class="form-control validate[custom[number]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">实际费用:</label>
						<div class="col-md-3">
							<input id="acturalCost" name="saleChance.acturalCost" value="${saleChance.acturalCost}" data-prompt-position="topLeft" class="form-control validate[custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label">潜在金额:</label>
						<div class="col-md-3">
							<input id="potentialAmount" name="saleChance.potentialAmount" value="${saleChance.potentialAmount}" data-prompt-position="topLeft" class="form-control validate[custom[number]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">加权金额:</label>
						<div class="col-md-3">
							<input id="weightedAmount" name="saleChance.weightedAmount" value="${saleChance.weightedAmount}" data-prompt-position="topLeft" class="form-control validate[custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label">毛利率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="grossMargin" name="saleChance.grossMargin" value="${saleChance.grossMargin}" data-prompt-position="topLeft" class="form-control validate[custom[number],min[1],max[100]]" type="text" />
								<span class="input-group-addon">(1-100)%</span>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixChanceAndTrackingAction!goList.action');">
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
	$("#saleChanceForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#saleChanceForm").validationEngine('validate')) {
			$("#saleChanceForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixChanceAndTrackingAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				showMessage(result);
				loadContent('', '${nvix}/nvixnt/nvixChanceAndTrackingAction!goList.action');
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
			loadContactPerson();
			loadCrmProject();
		});
	}
	
	function chooseCrmProject() {
		chooseListData('${nvix}/nvixnt/nvixCrmProjectAction!goChooseCrmProject.action', 'single', '选择项目', 'project');
	}
	
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	}
</script>