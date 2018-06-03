<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#expressFeeRulesForm").validationEngine();
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="expressFeeRulesForm">
		<s:hidden id="expressFeeRulesId" name="expressFeeRulesId" value="%{expressFeeRules.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">快递公司:&nbsp;</td>
					<td><s:select id="logisticsId" headerKey="-1" headerValue="--请选择--" list="%{logisticsList}" listValue="name" listKey="id" value="%{expressFeeRules.logistics.id}" multiple="false" theme="simple" onchange="fieldChanged(this);">
						</s:select><span style="color: red;">*</span></td>
					<td align="right">区域:&nbsp;</td>
					<td><s:select id="deliveryAreaId" headerKey="-1" headerValue="--请选择--" list="%{deliveryAreaList}" listValue="name" listKey="id" value="%{expressFeeRules.deliveryArea.id}" multiple="false" theme="simple" onchange="fieldChanged(this);">
						</s:select><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">首重:&nbsp;</td>
					<td><input type="text" id="firstWeight" name="firstWeight" value="${expressFeeRules.firstWeight}" class="validate[required] text-input" onchange="fieldChanged(this);" />克<span style="color: red;">*</span></td>
					<td align="right">首重费用:&nbsp;</td>
					<td><input type="text" id="firstCost" name="firstCost" value="${expressFeeRules.firstCost}" class="validate[required] text-input" onchange="fieldChanged(this);" />元<span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">续重单位量:&nbsp;</td>
					<td><input type="text" id="perWeight" name="perWeight" value="${expressFeeRules.perWeight}" class="validate[required] text-input" onchange="fieldChanged(this);" />克<span style="color: red;">*</span></td>
					<td align="right">续重费用:&nbsp;</td>
					<td><input type="text" id="perCost" name="perCost" value="${expressFeeRules.perCost}" class="validate[required] text-input" onchange="fieldChanged(this);" />元<span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td><input type="text" id="memo" name="memo" value="${expressFeeRules.memo}" onchange="fieldChanged(this);" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>