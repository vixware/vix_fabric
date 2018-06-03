<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
<div class="content" style="margin-top: 6px;">
	<form id="dailyForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="dailyId" name="daily.id" value="%{daily.id}" theme="simple" />
				<s:hidden id="dailyCode" name="daily.dailyCode" value="%{daily.dailyCode}" theme="simple" />
				<tr height="30">
					<td align="right">主题:&nbsp;</td>
					<td><input id="name" name="daily.name" value="${daily.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">日期:&nbsp;</td>
					<td><input id="startTime" name="daily.startTime" value="<s:property value='formatDateToTimeString(daily.startTime)'/>" /> <img onclick="showTime('startTime')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td colspan="4">今日计划(由昨日拟定)</td>
				</tr>
				<tr height="30">
					<td colspan="4">${yesterdayDaily.todaySummary}</td>
				</tr>
				<tr height="30">
					<td colspan="4">今日总结</td>
				</tr>
				<tr height="30">
					<td colspan="4"><textarea id="todaySummary" name="todaySummary">${daily.todaySummary}</textarea></td>
				</tr>
				<tr height="30">
					<td colspan="4">明日计划</td>
				</tr>
				<tr height="30">
					<td colspan="4"><textarea id="tomorrowPlan" name="tomorrowPlan">${daily.tomorrowPlan}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script>
	$("#dailyForm").validationEngine();
	var todaySummary = KindEditor.create('textarea[name="todaySummary"]',{basePath:'${vix}/plugin/KindEditor/',width:750,height:150});
	var tomorrowPlan = KindEditor.create('textarea[name="tomorrowPlan"]',{basePath:'${vix}/plugin/KindEditor/',width:750,height:150});
</script>
