<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 5px;">
	<div class="box order_table" style="line-height: normal;">
		<table>
			<s:hidden id="dailyId" name="daily.id" value="show" theme="simple" />
			<tr height="30">
				<td align="right">名称:&nbsp;</td>
				<td colspan="3">${daily.name}</td>
			</tr>
			<tr height="30">
				<td align="right">开始日期:&nbsp;</td>
				<td><s:property value='formatDateToTimeString(daily.startTime)' /></td>
				<td align="right">结束日期:&nbsp;</td>
				<td><s:property value='formatDateToTimeString(daily.endTime)' /></td>
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
				<td colspan="4">${daily.todaySummary}</td>
			</tr>
			<tr height="30">
				<td colspan="4">明日计划</td>
			</tr>
			<tr height="30">
				<td colspan="4">${daily.tomorrowPlan}</td>
			</tr>
		</table>
	</div>
</div>