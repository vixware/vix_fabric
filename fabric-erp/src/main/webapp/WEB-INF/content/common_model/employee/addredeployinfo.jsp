<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="caForm">
		<s:hidden id="daId" name="redeployInfo.id" value="%{redeployInfo.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="redeployInfo.employeeCode" value="${redeployInfo.employeeCode}" type="text" /></td>
					<th align="right">借调前原单位:&nbsp;</th>
					<td><input id="secondedbeforeUnits" name="redeployInfo.secondedbeforeUnits" value="${redeployInfo.secondedbeforeUnits}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">借调前原部门:&nbsp;</th>
					<td><input id="secondedbeforeDep" name="redeployInfo.secondedbeforeDep" value="${redeployInfo.secondedbeforeDep}" type="text" /></td>
					<th align="right">借调前原岗位:&nbsp;</th>
					<td><input id="secondedbeforePost" name="redeployInfo.secondedbeforePost" value="${redeployInfo.secondedbeforePost}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">借调去往单位:&nbsp;</th>
					<td><input id="secondedDestinedForUnits" name="redeployInfo.secondedDestinedForUnits" value="${redeployInfo.secondedDestinedForUnits}" type="text" /></td>
					<th align="right">借调去往部门:&nbsp;</th>
					<td><input id="secondedDestinedSector" name="redeployInfo.secondedDestinedSector" value="${redeployInfo.secondedDestinedSector}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">借调去往岗位:&nbsp;</th>
					<td><input id="secondedPosts" name="redeployInfo.secondedPosts" value="${redeployInfo.secondedPosts}" type="text" /></td>
					<th align="right">期限:&nbsp;</th>
					<td><input id="deadline" name="redeployInfo.deadline" value="${redeployInfo.deadline}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="redeployInfo.remarks" value="${redeployInfo.remarks}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#caForm").validationEngine();
</script>