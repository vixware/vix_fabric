<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="returnRuleItemForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/nvixntReturnRuleAction!saveOrUpdateReturnRuleItem.action">
	<input id="returnRuleItemReturnRuleId" name="returnRuleItem.returnRule.id" value="${returnRuleItem.returnRule.id}" type="hidden" />
	<input id="returnRuleItemId" name="returnRuleItem.id" value="${returnRuleItem.id}" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">从:</label>
			<div class="col-md-3">
				<input id="from" type="text" name="returnRuleItem.from" value="${returnRuleItem.from}"  class="form-control validate[required]">
			</div>
			<label class="col-md-2 control-label">到:</label>
			<div class="col-md-3">
				<input id="to" type="text" name="returnRuleItem.to" value="${returnRuleItem.to}"  class="form-control validate[required]">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">返款比率:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="ratio" type="text" name="returnRuleItem.ratio" value="${returnRuleItem.ratio}"  class="form-control validate[required]"><span class="input-group-addon">%</span>
				</div>
			</div>
			<label class="col-md-2 control-label">计量单位:</label>
			<div class="col-md-3">
				<select id="unit" name="returnRuleItem.unit" class="form-control">
					<c:forEach items="${measureUnitList}" var="entity">
						<option value="${entity.name}" <c:if test='${returnRuleItem.unit eq entity.name}'>selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">币种:</label>
			<div class="col-md-3">
				<select id="currencyType" name="returnRuleItem.currencyType.id" class="form-control">
					<c:forEach items="${currencyTypeList}" var="entity">
						<option value="${entity.id}" <c:if test='${returnRuleItem.currencyType.id eq entity.id}'>selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$(function(){
		if(!$("#returnRuleItemReturnRuleId").val()){
			$("#returnRuleItemReturnRuleId").val($("#returnRuleId").val());
		}
	})
	$("#returnRuleItemForm").validationEngine();
</script>