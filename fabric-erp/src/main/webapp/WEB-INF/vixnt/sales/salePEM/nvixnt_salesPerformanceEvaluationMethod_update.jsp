<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="salesPerformanceEvaluationMethodForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/nvixntSalesPerformanceEvaluationMethodAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="salesPerformanceEvaluationMethod.id" value="${salesPerformanceEvaluationMethod.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-3">
				<select name="salesPerformanceEvaluationMethod.name" class="form-control validate[required]">
					<option>---请选择---</option>
					<option value="1" <c:if test="${salesPerformanceEvaluationMethod.name eq '1'}">selected="selected"</c:if>>定额金额完成百分比</option>
					<option value="2" <c:if test="${salesPerformanceEvaluationMethod.name eq '2'}">selected="selected"</c:if>>实际销售金额</option>
					<option value="3" <c:if test="${salesPerformanceEvaluationMethod.name eq '3'}">selected="selected"</c:if>>定额数量完成百分比</option>
					<option value="4" <c:if test="${salesPerformanceEvaluationMethod.name eq '4'}">selected="selected"</c:if>>实际销售数量</option>
					<option value="5" <c:if test="${salesPerformanceEvaluationMethod.name eq '5'}">selected="selected"</c:if>>毛利</option>
<%-- 					<option value="定额金额完成百分比" <c:if test="${salesPerformanceEvaluationMethod.name eq '定额金额完成百分比'}">selected="selected"</c:if>>定额金额完成百分比</option>
					<option value="实际销售金额" <c:if test="${salesPerformanceEvaluationMethod.name eq '实际销售金额'}">selected="selected"</c:if>>实际销售金额</option>
					<option value="定额数量完成百分比" <c:if test="${salesPerformanceEvaluationMethod.name eq '定额数量完成百分比'}">selected="selected"</c:if>>定额数量完成百分比</option>
					<option value="实际销售数量" <c:if test="${salesPerformanceEvaluationMethod.name eq '实际销售数量'}">selected="selected"</c:if>>实际销售数量</option>
					<option value="毛利" <c:if test="${salesPerformanceEvaluationMethod.name eq '毛利'}">selected="selected"</c:if>>毛利</option> --%>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否默认:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test='salesPerformanceEvaluationMethod.isDefault == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="salesPerformanceEvaluationMethod.isDefault" class="validate[required]" <s:if test='salesPerformanceEvaluationMethod.isDefault == "0"'>checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test='salesPerformanceEvaluationMethod.isDefault == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="salesPerformanceEvaluationMethod.isDefault" <s:if test='salesPerformanceEvaluationMethod.isDefault == "1"'>checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否启用:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test='salesPerformanceEvaluationMethod.enable == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="salesPerformanceEvaluationMethod.enable" class="validate[required]" <s:if test='salesPerformanceEvaluationMethod.enable == "0"'>checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test='salesPerformanceEvaluationMethod.enable == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="salesPerformanceEvaluationMethod.enable" <s:if test='salesPerformanceEvaluationMethod.enable == "1"'>checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="salesPerformanceEvaluationMethod.memo" class="form-control">${salesPerformanceEvaluationMethod.memo} </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#salesPerformanceEvaluationMethodForm").validationEngine();
</script>