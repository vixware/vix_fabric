<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id=dtechachieveForm>
		<s:hidden id="daId" name="techachieve.id" value="%{techachieve.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="techachieve.employeeCode" value="${techachieve.employeeCode}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">专业名称:&nbsp;</th>
					<td><input id="professionalName" name="techachieve.professionalName" value="${techachieve.professionalName}" type="text" /></td>
					<th align="right">专家名称:&nbsp;</th>
					<td><input id="expertName" name="techachieve.expertName" value="${techachieve.expertName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">专家级别:&nbsp;</th>
					<td><input id="expertLevel" name="techachieve.expertLevel" value="${techachieve.expertLevel}" type="text" /></td>
					<th align="right">专业类别:&nbsp;</th>
					<td><input id="professionalCategory" name="techachieve.professionalCategory" value="${techachieve.professionalCategory}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">专业技术成果:&nbsp;</th>
					<td><input id="professionalAndTechnicalAchievements" name="techachieve.professionalAndTechnicalAchievements" value="${techachieve.professionalAndTechnicalAchievements}" type="text" /></td>
					<th align="right">年度考评结果:&nbsp;</th>
					<td><input id="resultsOfTheAnnualAppraisal" name="techachieve.resultsOfTheAnnualAppraisal" value="${techachieve.resultsOfTheAnnualAppraisal}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">状态:&nbsp;</th>
					<td><input id="remarks" name="techachieve.remark" value="${techachieve.remarks}" type="text" /></td>
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="techachieve.remark" value="${techachieve.remarks}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#dtechachieveForm").validationEngine();
</script>