<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#operatingConditionsForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="operatingConditionsForm">
		<s:hidden id="operatingConditionsId" name="operatingConditionsId" value="%{operatingConditions.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">客户数量:&nbsp;</th>
					<td><input id="customerNumbers" name="customerNumbers" value="${operatingConditions.customerNumbers}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">仓库规模:&nbsp;</th>
					<td><input id="warehouseScale" name="warehouseScale" value="${operatingConditions.warehouseScale}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">配送解决模式:&nbsp;</th>
					<td><input id="distributionMode" name="distributionMode" value="${operatingConditions.distributionMode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">车辆数量:&nbsp;</th>
					<td><input id="vehicleNumber" name="vehicleNumber" value="${operatingConditions.vehicleNumber}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">销售额:&nbsp;</th>
					<td><input id="salesAmount" name="salesAmount" value="${operatingConditions.salesAmount}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">销售利润:&nbsp;</th>
					<td><input id="salesProfit" name="salesProfit" value="${operatingConditions.salesProfit}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">销售年度:&nbsp;</th>
					<td><input id="year" name="year" value="${operatingConditions.year}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>