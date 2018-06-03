<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#salesDistributionForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="salesDistributionForm">
		<s:hidden id="salesDistributionId" name="salesDistributionId" value="%{salesDistribution.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">区域编码:&nbsp;</th>
					<td><input id="salesDistributionCode" name="salesDistributionCode" value="${salesDistribution.code}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">区域名称:&nbsp;</th>
					<td><input id="salesDistributionName" name="salesDistributionName" value="${salesDistribution.name}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">销售经理:&nbsp;</th>
					<td><input id="salesManager" name="salesManager" value="${salesDistribution.salesManager}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">内勤人员:&nbsp;</th>
					<td><input id="officeStaff" name="officeStaff" value="${salesDistribution.officeStaff}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">区域范围:&nbsp;</th>
					<td><input id="areaCoverage" name="areaCoverage" value="${salesDistribution.areaCoverage}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>