<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">

</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="workRecord.id" value="%{workRecord.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="workRecord.employeeCode" value="${workRecord.employeeCode}" type="text" /></td>
					<th align="right">所在单位:&nbsp;</th>
					<td><input id="unitToWhichTheyBelong" name="workRecord.unitToWhichTheyBelong" value="${workRecord.unitToWhichTheyBelong}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">所在部门:&nbsp;</th>
					<td><input id="workdepartment" name="workRecord.workdepartment" value="${workRecord.workdepartment}" type="text" /></td>
					<th align="right">所在岗位:&nbsp;</th>
					<td><input id="whereThePost" name="workRecord.whereThePost" value="${workRecord.whereThePost}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">职级:&nbsp;</th>
					<td><input id="workrank" name="workRecord.workrank" value="${workRecord.workrank}" type="text" /></td>
					<th align="right">专业经历:&nbsp;</th>
					<td><input id="professionalExperience" name="workRecord.professionalExperience" value="${workRecord.professionalExperience}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">证明人:&nbsp;</th>
					<td><input id="witnesses" name="workRecord.witnesses" value="${workRecord.witnesses}" type="text" /></td>
					<th align="right">任职文号:&nbsp;</th>
					<td><input id="officeSymbol" name="workRecord.officeSymbol" value="${workRecord.officeSymbol}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="workRecord.remarks" value="${workRecord.remarks}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#daForm").validationEngine();
</script>