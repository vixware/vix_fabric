<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="np_left_title">
	<h2>写月报</h2>
	<span>[未写]</span><br />
	<span class="gray">本月计划由上月拟定</span>
</div>
<p class="lineh30" align="center">&lt;&lt;${currentMonth}&gt;&gt;</p>
<input type="hidden" id="id" name="id" value="${monthly.id}" />
<input type="hidden" id="monthlyCode" name="monthlyCode" value="${monthly.monthlyCode}" />
<table width="100%" class="lineh30" style="margin-bottom: 20px;">
	<tr height="30">
		<td align="right">名称:&nbsp;</td>
		<td colspan="3"><input id="name" name="monthly.name" value="${monthly.name}" /></td>
	</tr>
	<tr height="30">
		<td align="right">开始日期:&nbsp;</td>
		<td><input id="startTime" name="monthly.startTime" value="${monthly.startTime}" /> <img onclick="showTime('startTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
		<td align="right">结束日期:&nbsp;</td>
		<td><input id="endTime" name="monthly.endTime" value="${monthly.endTime}" /> <img onclick="showTime('endTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
	</tr>
	<tr>
		<td width="50%" colspan="2"><b>本月总结</b><br /> <textarea id="monthSummary" name="monthSummary" style="width: 90%; height: 100px;">${monthly.monthSummary}</textarea></td>
		<td width="50%" colspan="2"><b>下月计划：</b>行动计划参考<br /> <textarea id="nextMonthPlan" name="nextMonthPlan" style="width: 90%; height: 100px;">${monthly.nextMonthPlan}</textarea></td>
	</tr>
	<tr>
		<td colspan="2"><input name="" type="button" class="btn" value="暂存" onclick="saveMonthly('0')" /> <input name="" type="button" class="btn" value="交月报" onclick="saveMonthly('1')" /></td>
	</tr>
</table>
<s:if test="weeklyList.size > 0 ">
	<div class="np_left_title">
		<h2>当周行动和日报参考：</h2>
	</div>
</s:if>
<div class="task_list borderlist lineh30 hovertd" style="border: 0px none; padding: 0;">
	<table width="100%">
		<tr>
			<s:iterator value="weeklyList" var="weekly">
				<td class="ar bggray" style="text-align: center;">第${weekly.weekNumber}周</td>
			</s:iterator>
		</tr>
		<tr>
			<s:iterator value="weeklyList" var="weekly">
				<td>
					<div class="table_sc">${weekly.nextWeekPlan}</div>
				</td>
			</s:iterator>
		</tr>
		<tr>
			<s:iterator value="weeklyList" var="weekly">
				<td class="tc bggray"><span class="gray">周报-工作总结：</span></td>
			</s:iterator>
		</tr>
		<tr>
			<s:iterator value="weeklyList" var="weekly">
				<td>
					<div class="table_sc">${weekly.weekSummary}</div>
				</td>
			</s:iterator>
		</tr>
	</table>
</div>
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
<script>
	var monthSummary = KindEditor.create('textarea[name="monthSummary"]',{basePath:'${vix}/plugin/KindEditor/',width:'98%',height:'98%'});
	var nextMonthPlan = KindEditor.create('textarea[name="nextMonthPlan"]',{basePath:'${vix}/plugin/KindEditor/',width:'98%',height:'98%'});
	function saveMonthly(status){
		$.post('${vix}/crm/agenda/monthlyAction!saveOrUpdate.action',
				{'monthly.id': $("#id").val(),
				  'monthly.name':$("#name").val(),
				  'monthly.monthlyCode':$("#monthlyCode").val(),
				  'monthly.startTime':$("#startTime").val(),
				  'monthly.endTime':$("#endTime").val(),
			      'monthly.monthSummary':monthSummary.html(),
				  'monthly.nextMonthPlan':nextMonthPlan.html()
				},
				function(result){
					showMessage(result);
				}
			);
	}
</script>