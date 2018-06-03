<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="addleft">
	<div class="addbtn">
		<p>
			<img src="${vix}/common/img/crm/calendar.png" /> 看周报
		</p>
	</div>
	<s:if test=" staffEmployeeList != null ">
		<div class="addbox">
			<div class="addtitle">人员选择</div>
			<p>
				<s:iterator value="staffEmployeeList" var="emp">
					<a id="${emp.id}" href="###" onclick="loadWeeklyContentByEmployee(${emp.id})">${emp.name}</a>
				</s:iterator>
				<script type="text/javascript">
					$("#"+$("#empId").val()).attr('style','font-weight:bold;');
					function loadWeeklyContentByEmployee(id){
						$("#empId").val(id);
						$.ajax({
							  url:'${vix}/crm/agenda/weeklyAction!goTab2ListContent.action?employeeId='+id,
							  cache: false,
							  success: function(html){
								  $("#newtab2").html(html);
							  }
						});
					}
				</script>
			</p>
		</div>
	</s:if>
</div>
<div class="addright">
	<div class="list borderlist lineh30" style="border: 0; margin-bottom: 20px;">
		<input type="hidden" id="nextWeekNumber" name="nextWeekNumber" value="${nextWeekNumber}" /> <input type="hidden" id="previousWeekNumber" name="previousWeekNumber" value="${previousWeekNumber}" />
		<table width="100%">
			<tr>
				<th style="text-align: center;" colspan="2"><a href="#" onclick="loadWeeklyContent('newtab2',$('#previousWeekNumber').val());">&lt;&lt;</a> ${currentWeek} <a href="#" onclick="loadWeeklyContent('newtab2',$('#nextWeekNumber').val());">&gt;&gt;</a></th>
			</tr>
			<tr>
				<td colspan="2"><b>本周计划：（由上周拟定）</b><br />
					<div>${preWeekly.nextWeekPlan}</div></td>
			</tr>
			<tr>
				<td><b>本周总结</b><br />
					<div>${weekly.weekSummary}</div></td>
				<td><b>下周计划</b><br />
					<div>${weekly.nextWeekPlan}</div></td>
			</tr>
			<tr>
				<td colspan="2"><b>补充回复：</b><br />
				<br /> <b>补充回复信息：</b><br /> <textarea name="" style="width: 80%; height: 100px; margin: 0 0 10px;" cols="" rows=""></textarea><br /> <input name="" type="button" class="btn" value="保存" /><br />&nbsp;</td>
			</tr>
		</table>
	</div>
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
</div>