<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>

<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="detail.id" value="%{detail.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">行号:&nbsp;</th>
					<td><input id="lineNumber" name="detail.lineNumber" value="${detail.lineNumber}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">资源编码:&nbsp;</th>
					<td><input id="resourceCode" name="detail.resourceCode" value="${detail.resourceCode}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">资源名称:&nbsp;</th>
					<td><input id="resourceName" name="detail.resourceName" value="${detail.resourceName}" type="text" /></td>
					<th align="right">资源活动:&nbsp;</th>
					<td><input id="resourceActivities" name="detail.resourceActivities" value="${detail.resourceActivities}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">基础类型:&nbsp;</th>
					<td><input id="baTypes" name="detail.baTypes" value="${detail.baTypes}" type="text" /></td>
					<th align="right">工时:&nbsp;</th>
					<td><input id="workingHours" name="detail.workingHours" value="${detail.workingHours}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">工时(分子):&nbsp;</th>
					<td><input id="manHourMolecules" name="detail.manHourMolecules" value="${detail.manHourMolecules}" type="text" /></td>
					<th align="right">工时(分母):&nbsp;</th>
					<td><input id="workingHoursDenominator" name="detail.workingHoursDenominator" value="${detail.workingHoursDenominator}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">效率:&nbsp;</th>
					<td><input id="efficiency" name="detail.efficiency" value="${detail.efficiency}" type="text" /></td>
					<th align="right">是否计划:&nbsp;</th>
					<td><input id="whetherPlan" name="detail.whetherPlan" value="${detail.whetherPlan}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">计费类型:&nbsp;</th>
					<td><input id="chargingTypes" name="detail.chargingTypes" value="${detail.chargingTypes}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#daForm" ).validationEngine ( );
</script>