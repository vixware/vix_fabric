<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售管理 <span onclick="">&gt;销售计划</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn bg-color-green txt-color-white" type="button" onclick="goAudit('${taskId}');">
					<i class="glyphicon glyphicon-ok-circle"></i> 同意
				</button>
				<button class="btn bg-color-red txt-color-white" type="button" onclick="reject('${taskId}');">
					<i class="glyphicon glyphicon-remove-circle"></i> 驳回
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>报价单</h2>
		</header>
		<div class="widget-body">
			<form id="salePlanForm" class="form-horizontal" action="${nvix}/nvixnt/nvixntSalePlanAction!saveOrUpdate.action">
				<input type="hidden" id="salePlanId" name="salePlan.id" value="${salePlan.id}"/>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
						<div class="col-md-3">
							<input id="code" name="salePlan.code" value="${salePlan.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
						<div class="col-md-3">
							<input id="name" name="salePlan.name" value="${salePlan.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">上级计划:</label>
						<div class="col-md-3">
								<input type="hidden" id="chooseSalePlanId" name="salePlan.parentSalePlan.id" value="${salePlan.parentSalePlan.id}"/>
								<input id="chooseSalePlanName" type="text"  value="${salePlan.parentSalePlan.name}" class="form-control" readonly="readonly"/>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>销售人员:</label>
						<div class="col-md-3">
								<input type="hidden" id="employeeId" name="salePlan.salesMan.id" value="${salePlan.salesMan.id}"/>
								<input id="employeeName" name="salePlan.salesMan.name" value="${salePlan.salesMan.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
						</div>
					</div>
					 <div class="form-group">
						 <label class="col-md-2 control-label"><span class="text-danger">*</span>销售组织:</label>
						<div class="col-md-3">
								<input type="hidden" id="chooseSaleOrgId" name="salePlan.saleOrg.id" value="${salePlan.saleOrg.id}"/>
								<input id="chooseSaleOrgName" type="text"  value="${salePlan.saleOrg.fullName}" class="form-control validate[required]" readonly="readonly"/>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>部门:</label>
						<div class="col-md-3">
								<input type="hidden" id="chooseSaleOrgDeptId" name="salePlan.departmet.id" value="${salePlan.departmet.id}"/>
								<input id="chooseSaleOrgDeptName" type="text"  value="${salePlan.departmet.fullName}" class="form-control validate[required]" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>销售商品:</label>
						<div class="col-md-3">
								<input type="hidden" id="itemId" name="salePlan.item.id" value="${salePlan.item.id}"/>
								<input id="itemName" name="salePlan.item.name" value="${salePlan.item.name}" onfocus="chooseItem();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
						</div>
						<label class="col-md-2 control-label">商品规格:</label>
						<div class="col-md-3">
							<input id="specifications" name="salePlan.specifications" value="${salePlan.specifications}" data-prompt-position="topLeft" class="form-control" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>数量:</label>
						<div class="col-md-3">
							<input id="amount" name="salePlan.amount" value="${salePlan.amount}" data-prompt-position="topLeft" class="form-control" type="text"/>
						</div>
					<label class="col-md-2 control-label"><span class="text-danger">*</span>计量单位组:</label>
						<div class="col-md-3">
							<select id="measureUnitGroupId" name="salePlan.measureUnitGroup.id" data-prompt-position="topLeft" disabled="disabled" class="form-control validate[required]" onchange="loadItemMeasureUnit();">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitGroupList}" var="entity">
									<option value="${entity.id}" <c:if test="${salePlan.measureUnitGroup.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>

					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>主计量单位:</label>
						<div class="col-md-3">
							<select id="measureUnitId" name="salePlan.measureUnit.id" data-prompt-position="topLeft" disabled="disabled" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitList}" var="entity">
									<option value="${entity.id}" <c:if test="${salePlan.measureUnit.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">辅助计量单位:</label>
						<div class="col-md-3">
							<select id="assitMeasureUnitId" name="salePlan.assitMeasureUnit.id" disabled="disabled" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitList}" var="entity">
									<option value="${entity.id}" <c:if test="${salePlan.assitMeasureUnit.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>计划日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="time" name="salePlan.time" value="${salePlan.timeStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'billDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">周期:</label>
						<div class="col-md-3">
							<input id="cycle" name="salePlan.cycle" value="${salePlan.cycle}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<s:if test="flowApprovalOpinionList.size > 0">
						<s:iterator value="flowApprovalOpinionList" var="entity" status="s">
							<div class="form-group">
								<label class="control-label col-md-2">审批人:</label>
								<div class="col-md-3">
									<input class="form-control" id="" name="" value="${entity.approvalPersonName}" type="text" readonly="readonly">
								</div>
								<label class="control-label col-md-2">审批意见:</label>
								<div class="col-md-3">
									<input class="form-control" id="" name="" value="${entity.opinion}" type="text" readonly="readonly">
								</div>
							</div>
						</s:iterator>
					</s:if>
				</fieldset>
				<div class="form-actions" style="margin-top:15px;">
					<div class="row">
						<div class="col-md-12">
							<button class="btn bg-color-green txt-color-white" type="button" onclick="goAudit('${taskId}');">
								<i class="glyphicon glyphicon-ok-circle"></i> 同意
							</button>
							<button class="btn bg-color-red txt-color-white" type="button" onclick="reject('${taskId}');">
								<i class="glyphicon glyphicon-remove-circle"></i> 驳回
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');">
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
function goAudit(id) {
	openSaveOrUpdateWindow('${nvix}/nvixnt/vreport/nvixJobTodoAction!goAudit.action?id=' + id, "jobTodoForm", "流程审批", 750, 350, null, null, function() {
		loadContent('', '${nvix}/nvixnt/vixNtIndexAction!goListContent.action');
	});
};

function reject(id) {
	updateEntityByConfirm('${nvix}/nvixnt/vreport/nvixJobTodoAction!reject.action?id=' + id, "是否驳回该申请?", null, null, function() {
		loadContent('', '${nvix}/nvixnt/vixNtIndexAction!goListContent.action');
	});
};
	pageSetUp();
</script>