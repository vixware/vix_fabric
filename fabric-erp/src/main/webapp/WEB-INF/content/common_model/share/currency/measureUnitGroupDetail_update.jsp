<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#measureUnitGroupDetailForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="measureUnitGroupDetailForm">
		<s:hidden id="measureUnitId" name="measureUnitId" value="%{measureUnit.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="measureUnitCode" name="measureUnitCode" value="${measureUnit.code}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">名称:&nbsp;</th>
					<td><input id="measureUnitName" name="measureUnitName" value="${measureUnit.name}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">英文名称:&nbsp;</th>
					<td><input id="measureUnitEnglishCode" name="measureUnitEnglishCode" value="${measureUnit.englishCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">对应条形码:&nbsp;</th>
					<td><input id="barCode" name="barCode" value="${measureUnit.barCode}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">是否主计量单位:&nbsp;</th>
					<td><input type="radio" name="isMeasurementUnit" value="是" />是<input type="radio" name="isMeasurementUnit" value="否" checked="checked" />否</td>
					<th align="right">换算率:&nbsp;</th>
					<td><input id="rate" name="rate" value="${measureUnit.rate}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>