<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<%-- <script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script> --%>


<script type="text/javascript">

</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="retireInfo.id" value="%{retireInfo.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="retireInfo.employeeCode" value="${retireInfo.employeeCode}" type="text" /></td>
					<th align="right">离退休类别:&nbsp;</th>
					<td><input id="retiredCategory" name="retireInfo.retiredCategory" value="${retireInfo.retiredCategory}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">职级序列:&nbsp;</th>
					<td><input id="rankSequence" name="retireInfo.rankSequence" value="${retireInfo.rankSequence}" type="text" /></td>
					<th align="right">离退休日期:&nbsp;</th>
					<td><input id="retirementDate" name="retirementDate" value="<fmt:formatDate value='${retireInfo.retirementDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('retirementDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">离休金:&nbsp;</th>
					<td><input id="pensions" name="retireInfo.pensions" value="${retireInfo.pensions}" type="text" /></td>
					<th align="right">退休金:&nbsp;</th>
					<td><input id="retirementPay" name="retireInfo.retirementPay" value="${retireInfo.retirementPay}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">退职生活费:&nbsp;</th>
					<td><input id="subsistenceAllowanceFor" name="retireInfo.subsistenceAllowanceFor" value="${retireInfo.subsistenceAllowanceFor}" type="text" /></td>
					<th align="right">医疗费:&nbsp;</th>
					<td><input id="medicalFee" name="retireInfo.medicalFee" value="${retireInfo.medicalFee}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">补充医疗保险支付医疗费部分:&nbsp;</th>
					<td><input id="supplementaryMedicalInsuranceToPayMedicalExpensesPart" name="retireInfo.supplementaryMedicalInsuranceToPayMedicalExpensesPart" value="${retireInfo.supplementaryMedicalInsuranceToPayMedicalExpensesPart}" type="text" /></td>
					<th align="right">生活补贴(企业):&nbsp;</th>
					<td><input id="livingAllowanceEnterprise" name="retireInfo.livingAllowanceEnterprise" value="${retireInfo.livingAllowanceEnterprise}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">生活补贴(行业):&nbsp;</th>
					<td><input id="livingAllowanceIndustry" name="retireInfo.livingAllowanceIndustry" value="${retireInfo.livingAllowanceIndustry}" type="text" /></td>
					<th align="right">生活补贴(地方):&nbsp;</th>
					<td><input id="livingAllowanceLocal" name="retireInfo.livingAllowanceLocal" value="${retireInfo.livingAllowanceLocal}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">一次性补贴(地方):&nbsp;</th>
					<td><input id="aoneTimeSubsidyLocal" name="retireInfo.aoneTimeSubsidyLocal" value="${retireInfo.aoneTimeSubsidyLocal}" type="text" /></td>
					<th align="right">一次性补贴(特殊工种):&nbsp;</th>
					<td><input id="aoneTimeSubsidySpecial" name="retireInfo.aoneTimeSubsidySpecial" value="${retireInfo.aoneTimeSubsidySpecial}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">一次性补贴(病退):&nbsp;</th>
					<td><input id="aoneTimeSubsidyIllnessRetreat" name="retireInfo.aoneTimeSubsidyIllnessRetreat" value="${retireInfo.aoneTimeSubsidyIllnessRetreat}" type="text" /></td>
					<th align="right">应发总额:&nbsp;</th>
					<td><input id="shouldTheTotal" name="retireInfo.shouldTheTotal" value="${retireInfo.shouldTheTotal}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="retireInfo.remarks" value="${retireInfo.remarks}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#daForm").validationEngine();
</script>