<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
<div class="content" style="margin-top: 5px;">
	<form id="weeklyForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="weeklyId" name="weekly.id" value="%{weekly.id}" theme="simple" />
				<s:hidden id="weeklyCode" name="weekly.weeklyCode" value="%{weekly.weeklyCode}" theme="simple" />
				<tr height="30">
					<td align="right">主题:&nbsp;</td>
					<td colspan="3"><input id="name" name="weekly.name" value="${weekly.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">开始日期:&nbsp;</td>
					<td><input id="startTime" name="weekly.startTime" value="<s:property value='formatDateToTimeString(weekly.startTime)'/>" /> <img onclick="showTime('startTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">结束日期:&nbsp;</td>
					<td><input id="endTime" name="weekly.endTime" value="<s:property value='formatDateToTimeString(weekly.endTime)'/>" /> <img onclick="showTime('endTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td colspan="4">本周总结</td>
				</tr>
				<tr height="30">
					<td colspan="4"><textarea id="weekSummary" name="weekSummary">${weekly.weekSummary}</textarea></td>
				</tr>
				<tr height="30">
					<td colspan="4">下周计划</td>
				</tr>
				<tr height="30">
					<td colspan="4"><textarea id="nextWeekPlan" name="nextWeekPlan">${weekly.nextWeekPlan}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script>
	$("#weeklyForm").validationEngine();
	var weekSummary = KindEditor.create('textarea[name="weekSummary"]',{basePath:'${vix}/plugin/KindEditor/',width:750,height:150});
	var nextWeekPlan = KindEditor.create('textarea[name="nextWeekPlan"]',{basePath:'${vix}/plugin/KindEditor/',width:750,height:150});
</script>