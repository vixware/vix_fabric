<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#measureUnitTypeForm").validationEngine();
</script>
<form id="measureUnitTypeForm">
	<div style="padding: 5px;">
		<s:hidden id="measureUnitTypeId" name="measureUnitTypeId" value="%{measureUnitType.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input id="measureUnitTypeCode" name="measureUnitTypeCode" value="${measureUnitType.code}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">名称:&nbsp;</td>
					<td><input id="measureUnitTypeName" name="measureUnitTypeName" value="${measureUnitType.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</div>
</form>