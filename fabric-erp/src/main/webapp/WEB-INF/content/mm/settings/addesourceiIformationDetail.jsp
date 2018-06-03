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
					<th align="right">工作中心:&nbsp;</th>
					<td><input id="org" name="detail.org" value="${detail.org}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">工作中心名称:&nbsp;</th>
					<td><input id="orgName" name="detail.orgName" value="${detail.orgName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">计算产能:&nbsp;</th>
					<td><input id="productivityCalculation" name="detail.productivityCalculation" value="${detail.productivityCalculation}" type="text" /></td>
					<th align="right">可用数量:&nbsp;</th>
					<td><input id="quantityAvailable" name="detail.quantityAvailable" value="${detail.quantityAvailable}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">利用率%:&nbsp;</th>
					<td><input id="utilizationRate" name="detail.utilizationRate" value="${detail.utilizationRate}" type="text" /></td>
					<th align="right">效率%:&nbsp;</th>
					<td><input id="efficiency" name="detail.efficiency" value="${detail.efficiency}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">有效排程相关:&nbsp;</th>
					<td><input id="effectiveSchedulingRelated" name="detail.effectiveSchedulingRelated" value="${detail.effectiveSchedulingRelated}" type="text" /></td>
					<th align="right">关键资源:&nbsp;</th>
					<td><input id="criticalResources" name="detail.criticalResources" value="${detail.criticalResources}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">资课费率%:&nbsp;</th>
					<td><input id="zikeRate" name="detail.zikeRate" value="${detail.zikeRate}" type="text" /></td>
					<td align="right">超载百分比 %：</td>
					<td><input id="overloadPercentage" name="detail.overloadPercentage" value="${detail.overloadPercentage}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#daForm" ).validationEngine ( );
</script>