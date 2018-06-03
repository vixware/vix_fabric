<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript"> 
$("#commissionPlanItemForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="commissionPlanItemForm">
		<s:hidden id="cpiId" name="commissionPlanItem.id" value="%{commissionPlanItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr>
					<td align="right">条款编码：</td>
					<td><input type="text" size="30" id="cpiCode" name="commissionPlanItem.cpiCode" value="${commissionPlanItem.cpiCode}" /></td>
					<td align="right">名称：</td>
					<td><input type="text" size="30" id="name" name="commissionPlanItem.name" value="${commissionPlanItem.name}" /></td>
				</tr>
				<tr>
					<td align="right">业务类别：</td>
					<td><input type="text" size="30" id="bizType" name="commissionPlanItem.bizType" value="${commissionPlanItem.bizType}" /></td>
					<td align="right">${vv:varView('vix_mdm_item')}类别：</td>
					<td><input type="text" size="30" id="itemType" name="commissionPlanItem.itemType" value="${commissionPlanItem.itemType}" /></td>
				</tr>
				<tr>
					<td align="right">计算基础：</td>
					<td><input type="text" size="30" id="computeBaseTarget" name="commissionPlanItem.computeBaseTarget" value="${commissionPlanItem.computeBaseTarget}" /></td>
					<td align="right">业绩考评方式：</td>
					<td><s:select id="salesPerformanceEvaluationMethodId" list="%{salesPerformanceEvaluationMethodList}" headerKey="" headerValue="--请选择--" listValue="name" listKey="id" value="%{commissionPlanItem.performanceEvaluationMethod.id}" multiple="false" theme="simple"></s:select></td>
				</tr>
				<tr>
					<td align="right">业务调整系数：</td>
					<td><input type="text" size="30" id="adjustCoefficient" name="commissionPlanItem.adjustCoefficient" value="${commissionPlanItem.adjustCoefficient}" /></td>
					<td align="right">计算方式：</td>
					<td><input type="text" size="30" id="computeStyle" name="commissionPlanItem.computeStyle" value="${commissionPlanItem.computeStyle}" /></td>
				</tr>
				<tr>
					<td align="right">是否考虑累计业绩：</td>
					<td><input type="text" size="30" id="isGrandTotal" name="commissionPlanItem.isGrandTotal" value="${commissionPlanItem.isGrandTotal}" /></td>
					<td align="right">固定佣金比率：</td>
					<td><input type="text" size="30" id="fixedCommissionTerm" name="commissionPlanItem.fixedCommissionTerm" value="${commissionPlanItem.fixedCommissionTerm}" /></td>
				</tr>
				<tr>
					<td align="right">固定佣金：</td>
					<td colspan="3"><input type="text" size="30" id="fixedCommission" name="commissionPlanItem.fixedCommission" value="${commissionPlanItem.fixedCommission}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
