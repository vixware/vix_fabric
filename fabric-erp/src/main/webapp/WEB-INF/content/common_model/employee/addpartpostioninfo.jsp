<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="dechtitleForm">
		<s:hidden id="daId" name="partPostionInfo.id" value="%{partPostionInfo.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="partPostionInfo.employeeCode" value="${partPostionInfo.employeeCode}" type="text" /></td>
					<th align="right">兼职类型:&nbsp;</th>
					<td><input id="partTimeType" name="partPostionInfo.partTimeType" value="${partPostionInfo.partTimeType}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">兼职岗位名称:&nbsp;</th>
					<td><input id="partTimeName" name="partPostionInfo.partTimeName" value="${partPostionInfo.partTimeName}" type="text" /></td>
					<th align="right">兼职开始日期:&nbsp;</th>
					<td><input id="partTimeStartDate" name="partTimeStartDate" value="<fmt:formatDate value='${partPostionInfo.partTimeStartDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('partTimeStartDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
					</td>
				</tr>
				<tr height="40">
					<th align="right">兼职结束日期:&nbsp;</th>
					<td><input id="partTimeEndDate" name="partTimeEndDate" value="<fmt:formatDate value='${partPostionInfo.partTimeEndDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('partTimeEndDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">岗位排序:&nbsp;</th>
					<td><input id="positionsSort" name="partPostionInfo.positionsSort" value="${partPostionInfo.positionsSort}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="partPostionInfo.remarks" value="${partPostionInfo.remarks}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#dechtitleForm").validationEngine();
</script>