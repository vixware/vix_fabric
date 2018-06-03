<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#orginialMeasureUnitForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="orginialMeasureUnitForm">
		<s:hidden id="measureUnitId" name="measureUnitId" value="%{orginialMeasureUnit.id}" theme="simple" />
		<s:hidden id="id" name="id" value="%{id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="measureUnitCode" name="measureUnitCode" value="${orginialMeasureUnit.code}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">名称:&nbsp;</th>
					<td><input id="measureUnitName" name="measureUnitName" value="${orginialMeasureUnit.name}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">是否主计量单位:&nbsp;</th>
					<td><input type="radio" name="isMeasurementUnit" value="是" />是<input type="radio" name="isMeasurementUnit" value="否" checked="checked" />否</td>
					<th align="right">换算率:&nbsp;</th>
					<td><input id="rate" name="rate" value="${orginialMeasureUnit.rate}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">英文名称:&nbsp;</th>
					<td><input id="measureUnitEnglishCode" name="measureUnitEnglishCode" value="${orginialMeasureUnit.englishCode}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>