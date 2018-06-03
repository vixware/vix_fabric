<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="waForm">
		<s:hidden id="daId" name="soldierTuneInfo.id" value="%{soldierTuneInfo.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="soldierTuneInfo.employeeCode" value="${soldierTuneInfo.employeeCode}" type="text" /></td>
					<th align="right">复转类型:&nbsp;</th>
					<td><input id="reTurnType" name="soldierTuneInfo.reTurnType" value="${soldierTuneInfo.reTurnType}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">军衔名称:&nbsp;</th>
					<td><input id="militaryRank" name="soldierTuneInfo.militaryRank" value="${soldierTuneInfo.militaryRank}" type="text" /></td>
					<th align="right">级别:&nbsp;</th>
					<td><input id="militaryRankLevel" name="soldierTuneInfo.militaryRankLevel" value="${soldierTuneInfo.militaryRankLevel}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">所在部队:&nbsp;</th>
					<td><input id="whereTheTroops" name="soldierTuneInfo.whereTheTroops" value="${soldierTuneInfo.whereTheTroops}" type="text" /></td>
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="soldierTuneInfo.remarks" value="${soldierTuneInfo.remarks}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#waForm").validationEngine();
</script>