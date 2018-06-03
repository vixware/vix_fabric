<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="commissionPlanItemForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/nvixntCommissionPlanAction!saveOrUpdateCommissionPlanItem.action">
	<input id="commissionPlanItemReturnRuleId" name="commissionPlanItem.commissionPlan.id" value="${commissionPlanItem.commissionPlan.id}" type="hidden" />
	<input id="commissionPlanItemId" name="commissionPlanItem.id" value="${commissionPlanItem.id}" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-3">
				<input id="code" name="commissionPlanItem.code" value="${commissionPlanItem.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text"/>
			</div>
			<label class="col-md-2 control-label">名称:</label>
			<div class="col-md-3">
				<input id="name" name="commissionPlanItem.name" value="${commissionPlanItem.name}"  data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">业务类别:</label>
			<div class="col-md-3">
				<input id="bizType" type="text" name="commissionPlanItem.bizType" value="${commissionPlanItem.bizType}"  class="form-control validate[required]">
			</div>
			<%-- <label class="col-md-2 control-label">${vv:varView('vix_mdm_item')}分类:</label> --%>
			<label class="col-md-2 control-label">物料类别:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="itemType" name="commissionPlanItem.itemType" value="${commissionPlanItem.itemType}" onfocus="chooseItem();" data-prompt-position="topLeft" class="form-control" type="hidden" readonly="readonly" />
					<input id="itemCatalogNames" name="commissionPlanItem.itemTypeName" value="${commissionPlanItem.itemTypeName}" onfocus="chooseItem();" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
					<span class="input-group-addon" style="cursor:pointer;" onclick="chooseItem();">选择</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">计算基础:</label>
			<div class="col-md-3">
				<input id="computeBaseTarget" type="text" name="commissionPlanItem.computeBaseTarget" value="${commissionPlanItem.computeBaseTarget}"  class="form-control validate[required]">
			</div>
			<label class="col-md-2 control-label">业绩考评方式:</label>
			<div class="col-md-3">
				<select name="commissionPlanItem.performanceEvaluationMethod.id" class="form-control validate[required]">
					<option>---请选择---</option>
					<c:forEach items="${salesPerformanceEvaluationMethodList}" var="entity">
						<option value="${entity.id}" <c:if test="${entity.id eq commissionPlanItem.performanceEvaluationMethod.id}">selected="selected"</c:if>>
						<c:if test="${entity.name eq '1'}">定额金额完成百分比</c:if>
						<c:if test="${entity.name eq '2'}">实际销售金额</c:if>
						<c:if test="${entity.name eq '3'}">定额数量完成百分比</c:if>
						<c:if test="${entity.name eq '4'}">定额销售数量</c:if>
						<c:if test="${entity.name eq '5'}">毛利</c:if>
						</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">业务调整系数:</label>
			<div class="col-md-3">
				<input id="adjustCoefficient" type="text" name="commissionPlanItem.adjustCoefficient" value="${commissionPlanItem.adjustCoefficient}"  class="form-control validate[required]">
			</div>
			<label class="col-md-2 control-label">计算方式:</label>
			<div class="col-md-3">
				<input id="computeStyle" type="text" name="commissionPlanItem.computeStyle" value="${commissionPlanItem.computeStyle}"  class="form-control validate[required]">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">是否考虑累计业绩:</label>
			<div class="col-md-3">
				<input id="isGrandTotal" type="text" name="commissionPlanItem.isGrandTotal" value="${commissionPlanItem.isGrandTotal}"  class="form-control validate[required]">
			</div>
			<label class="col-md-2 control-label">固定佣金比率:</label>
			<div class="col-md-3">
				<input id="fixedCommissionTerm" type="text" name="commissionPlanItem.fixedCommissionTerm" value="${commissionPlanItem.fixedCommissionTerm}"  class="form-control validate[required]">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">固定佣金:</label>
			<div class="col-md-3">
				<input id="fixedCommission" type="text" name="commissionPlanItem.fixedCommission" value="${commissionPlanItem.fixedCommission}"  class="form-control validate[required]">
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$(function(){
		if(!$("#commissionPlanItemReturnRuleId").val()){
			$("#commissionPlanItemReturnRuleId").val($("#commissionPlanId").val());
		}
	})
	function chooseItem(){
		var ids = $("#objectExpandId").val();
		chooseListData('${nvix}/nvixnt/nvixntCommissionPlanAction!goChooseItemCatalog.action', 'multi', '选择分类', null, function() {
			var ItemCatagory = chooseMap.valueSetWithClear().split(":");
			if (ItemCatagory != '' && ItemCatagory.length == 2) {
				$('#itemType').val(ItemCatagory[0]);
				$('#itemCatalogNames').val(ItemCatagory[1]);
			} else {
				layer.alert("请选择商品类型!");
			}
		});
	}
	$("#commissionPlanItemForm").validationEngine();
</script>