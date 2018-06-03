<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="np_left_title">
	<h2>写周报</h2>
	<span>[未写]</span><br />
	<span class="gray">本周计划由上周拟定</span>
</div>
<p class="lineh30" align="center">&lt;&lt;${currentWeek}&gt;&gt;</p>
<input type="hidden" id="id" name="id" value="${weekly.id}" />
<input type="hidden" id="weeklyCode" name="weeklyCode" value="${weekly.weeklyCode}" />
<table width="100%" class="lineh30" style="margin-bottom: 20px;">
	<tr height="30">
		<td align="right">主题:&nbsp;</td>
		<td colspan="3"><input id="name" name="weekly.name" value="${weekly.name}" /></td>
	</tr>
	<tr height="30">
		<td align="right">开始日期:&nbsp;</td>
		<td><input id="startTime" name="weekly.startTime" value="${weekly.startTime}" /> <img onclick="showTime('startTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
		<td align="right">结束日期:&nbsp;</td>
		<td><input id="endTime" name="weekly.endTime" value="${weekly.endTime}" /> <img onclick="showTime('endTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
	</tr>
	<tr>
		<td width="50%" colspan="2"><b>本周总结</b><br /> <textarea id="weekSummary" name="weekSummary" style="width: 90%; height: 100px;">${weekly.weekSummary}</textarea></td>
		<td width="50%" colspan="2"><b>下周计划：</b>行动计划参考<br /> <textarea id="nextWeekPlan" name="nextWeekPlan" style="width: 90%; height: 100px;">${weekly.nextWeekPlan}</textarea></td>
	</tr>
	<tr>
		<td colspan="2"><input name="" type="button" class="btn" value="暂存" onclick="saveWeekly('0')" /> <input name="" type="button" class="btn" value="交周报" onclick="saveWeekly('1')" /></td>
	</tr>
</table>
<div class="np_left_title">
	<h2>当周行动和日报参考：</h2>
</div>
<div class="task_list borderlist lineh30 hovertd" style="border: 0px none; padding: 0;">
	<table width="100%">
		<tr>
			<th width="14%" style="text-align: center;">周一</th>
			<th width="14%" style="text-align: center;">周二</th>
			<th width="14%" style="text-align: center;">周三</th>
			<th width="14%" style="text-align: center;">周四</th>
			<th width="14%" style="text-align: center;">周五</th>
			<th width="14%" style="text-align: center;">周六</th>
			<th width="14%" style="text-align: center;">周日</th>
		</tr>
		<tr>
			<td class="ar bggray" style="text-align: center;">${firstDailyDate}</td>
			<td class="ar bggray" style="text-align: center;">${secondDailyDate}</td>
			<td class="ar bggray" style="text-align: center;">${thirdDailyDate}</td>
			<td class="ar bggray" style="text-align: center;">${fourDailyDate}</td>
			<td class="ar bggray" style="text-align: center;">${fiveDailyDate}</td>
			<td class="ar bggray" style="text-align: center;">${sixDailyDate}</td>
			<td class="ar bggray" style="text-align: center;">${sevenDailyDate}</td>
		</tr>
		<tr>
			<s:iterator value="dailyList" var="daily">
				<td>
					<div class="table_sc">${daily.tomorrowPlan}</div>
				</td>
			</s:iterator>
		</tr>
		<tr>
			<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
			<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
			<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
			<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
			<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
			<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
			<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
		</tr>
		<tr>
			<s:iterator value="dailyList" var="daily">
				<td>
					<div class="table_sc">${daily.todaySummary}</div>
				</td>
			</s:iterator>
		</tr>
	</table>
</div>
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
<script>
	var weekSummary = KindEditor.create('textarea[name="weekSummary"]',{basePath:'${vix}/plugin/KindEditor/',width:'98%',height:'98%'});
	var nextWeekPlan = KindEditor.create('textarea[name="nextWeekPlan"]',{basePath:'${vix}/plugin/KindEditor/',width:'98%',height:'98%'});
	function saveWeekly(status){
		$.post('${vix}/crm/agenda/weeklyAction!saveOrUpdate.action',
				{'weekly.id': $("#id").val(),
				  'weekly.name':$("#name").val(),
				  'weekly.weeklyCode':$("#weeklyCode").val(),
				  'weekly.startTime':$("#startTime").val(),
				  'weekly.endTime':$("#endTime").val(),
			      'weekly.weekSummary':weekSummary.html(),
				  'weekly.nextWeekPlan':nextWeekPlan.html()
				},
				function(result){
					showMessage(result);
				}
			);
	}
</script>