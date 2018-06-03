<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="sMSCostRulesForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/messageCostRulesAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="sMSCostRules.id" value="${sMSCostRules.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 单条费用:</label>
			<div class="col-md-3">
				<input id="price" name="sMSCostRules.price" value="${sMSCostRules.price}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"> <span class="text-danger">*</span>是否启用:
			</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="sMSCostRules.status == 0">active</s:if>"> <input type="radio" value="0" id="status" name="sMSCostRules.status" <s:if test="sMSCostRules.status == 0">checked="checked"</s:if> class="validate[required]">是
					</label> <label class="btn btn-default <s:if test="sMSCostRules.status == 1">active</s:if>"> <input type="radio" value="1" id="status" name="sMSCostRules.status" <s:if test="sMSCostRules.status == 1">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="sMSCostRules.memo" class="form-control">${sMSCostRules.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#sMSCostRulesForm").validationEngine();
</script>