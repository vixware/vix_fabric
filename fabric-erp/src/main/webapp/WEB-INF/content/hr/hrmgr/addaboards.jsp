<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>


<script type="text/javascript">

</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="waForm">
		<s:hidden id="daId" name="aboard.id" value="%{aboard.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="aboard.employeeCode" value="${aboard.employeeCode}" type="text" /></td>
					<th align="right">出国目的:&nbsp;</th>
					<td><input id="purposeOfJourney" name="aboard.purposeOfJourney" value="${aboard.purposeOfJourney}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">国家:&nbsp;</th>
					<td><input id="country" name="aboard.country" value="${aboard.country}" type="text" /></td>
					<th align="right">访问单位:&nbsp;</th>
					<td><input id="accessUnit" name="aboard.accessUnit" value="${aboard.accessUnit}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">出国日期:&nbsp;</th>
					<td><input id="dateOfDeparture" name="dateOfDeparture" value="<fmt:formatDate value='${aboard.dateOfDeparture}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('dateOfDeparture','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">回国日期:&nbsp;</th>
					<td><input id="returnDate" name="returnDate" value="<fmt:formatDate value='${aboard.returnDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('returnDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">签证类型:&nbsp;</th>
					<td><input id="typeOfVisa" name="aboard.typeOfVisa" value="${aboard.typeOfVisa}" type="text" /></td>
					<th align="right">签证编号:&nbsp;</th>
					<td><input id="visaNumber" name="aboard.visaNumber" value="${aboard.visaNumber}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">派遣单位:&nbsp;</th>
					<td><input id="dispatchUnit" name="aboard.dispatchUnit" value="${aboard.dispatchUnit}" type="text" /></td>
					<th align="right">团组名称:&nbsp;</th>
					<td><input id="goupsName" name="aboard.goupsName" value="${aboard.goupsName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">组团单位名称:&nbsp;</th>
					<td><input id="groupUnitName" name="aboard.groupUnitName" value="${aboard.groupUnitName}" type="text" /></td>
					<th align="right">在团组内身份:&nbsp;</th>
					<td><input id="groupsWithinTheIdentity" name="aboard.groupsWithinTheIdentity" value="${aboard.groupsWithinTheIdentity}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">批准单位:&nbsp;</th>
					<td><input id="approvedBy" name="aboard.approvedBy" value="${aboard.approvedBy}" type="text" /></td>
					<th align="right">批准日期:&nbsp;</th>
					<td><input id="approvalDate" name="approvalDate" value="<fmt:formatDate value='${aboard.approvalDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('approvalDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				</tr>
				<tr height="40">
					<th align="right">批准文件名:&nbsp;</th>
					<td><input id="approvalFileName" name="aboard.approvalFileName" value="${aboard.approvalFileName}" type="text" /></td>
					<th align="right">批准文号:&nbsp;</th>
					<td><input id="approvalNumber" name="aboard.approvalNumber" value="${aboard.approvalNumber}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">费用供应商:&nbsp;</th>
					<td><input id="costSuppliers" name="aboard.costSuppliers" value="${aboard.costSuppliers}" type="text" /></td>
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="aboard.remarks" value="${aboard.remarks}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#waForm").validationEngine();
</script>