<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>


<script type="text/javascript">

</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="caForm">
		<s:hidden id="daId" name="politicalStatus.id" value="%{politicalStatus.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="politicalStatus.employeeCode" value="${politicalStatus.employeeCode}" type="text" /></td>
					<th align="right">姓名:&nbsp;</th>
					<td><input id="politicaName" name="politicalStatus.politicaName" value="${politicalStatus.politicaName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">身份证号:&nbsp;</th>
					<td><input id="idNumber" name="politicalStatus.idNumber" value="${politicalStatus.idNumber}" type="text" /></td>
					<th align="right">政治面貌:&nbsp;</th>
					<td><input id="politicalStatus" name="politicalStatus.politicalStatus" value="${politicalStatus.politicalStatus}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">参加党派时间:&nbsp;</th>
					<td><input id="participateInPartyTime" name="participateInPartyTime" value="<fmt:formatDate value='${politicalStatus.participateInPartyTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('participateInPartyTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
					<th align="right">参加党派时所在单位:&nbsp;</th>
					<td><input id="joinAPartisanUnit" name="politicalStatus.joinAPartisanUnit" value="${politicalStatus.joinAPartisanUnit}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="politicalStatus.remarks" value="${politicalStatus.remarks}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#caForm").validationEngine();
</script>