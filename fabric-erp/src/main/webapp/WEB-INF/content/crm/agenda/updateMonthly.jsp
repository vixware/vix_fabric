<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
<div class="content" style="margin-top: 5px;">
	<form id="monthlyForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="monthlyId" name="monthly.id" value="%{monthly.id}" theme="simple" />
				<s:hidden id="monthlyCode" name="monthly.monthlyCode" value="%{monthly.monthlyCode}" theme="simple" />
				<tr height="30">
					<td align="right">主题:&nbsp;</td>
					<td colspan="3"><input id="name" name="monthly.name" value="${monthly.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">开始日期:&nbsp;</td>
					<td><input id="startTime" name="monthly.startTime" value="<s:property value='formatDateToTimeString(monthly.startTime)'/>" /> <img onclick="showTime('startTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">结束日期:&nbsp;</td>
					<td><input id="endTime" name="monthly.endTime" value="<s:property value='formatDateToTimeString(monthly.endTime)'/>" /> <img onclick="showTime('endTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td colspan="4">本月总结</td>
				</tr>
				<tr height="30">
					<td colspan="4"><textarea id="monthSummary" name="monthSummary">${monthly.monthSummary}</textarea></td>
				</tr>
				<tr height="30">
					<td colspan="4">下月计划</td>
				</tr>
				<tr height="30">
					<td colspan="4"><textarea id="nextMonthPlan" name="nextMonthPlan">${monthly.nextMonthPlan}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script>
	$("#monthlyForm").validationEngine();
	var monthSummary = KindEditor.create('textarea[name="monthSummary"]',{basePath:'${vix}/plugin/KindEditor/',width:750,height:150});
	var nextMonthPlan = KindEditor.create('textarea[name="nextMonthPlan"]',{basePath:'${vix}/plugin/KindEditor/',width:750,height:150});
</script>
