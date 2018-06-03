<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>


<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="details.id" value="%{details.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">计划内容:&nbsp;</th>
					<td><input id="projectContent" name="details.projectContent" value="${details.projectContent}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">招聘职位:&nbsp;</th>
					<td><input id="theJob" name="details.theJob" value="${hrCategory.parentCategory.name}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(2);" /> <span
						style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">职位说明:&nbsp;</th>
					<td><input id="jobDescription" name="details.jobDescription" value="${details.jobDescription}" type="text" /></td>
					<th align="right">工作职责:&nbsp;</th>
					<td><input id="operatingDuty" name="details.operatingDuty" value="${details.operatingDuty}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">负责人:&nbsp;</th>
					<td><input id="leadingOfficial2" name="details.leadingOfficial" value="${details.leadingOfficial}" type="text" /></td>
					<th align="right">工作地点:&nbsp;</th>
					<td><input id="jobAddress" name="details.jobAddress" value="${details.jobAddress}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">招聘要求:&nbsp;</th>
					<td><input id="recruitmentRequirements" name="details.recruitmentRequirements" value="${details.recruitmentRequirements}" type="text" /></td>
					<th align="right">费用预算:&nbsp;</th>
					<td><input id="expenseBudget" name="details.expenseBudget" value="${details.expenseBudget}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">计划开始时间:&nbsp;</th>
					<td><input id="plannedStartTime" name="plannedStartTime" value="<fmt:formatDate value='${details.plannedStartTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('plannedStartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">计划结束时间:&nbsp;</th>
					<td><input id="plannedEndTime" name="plannedEndTime" value="<fmt:formatDate value='${details.plannedEndTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('plannedEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#daForm" ).validationEngine ( );
</script>