<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="addleft">
	<div class="addbtn">
		<p>
			<img src="${vix}/common/img/crm/calendar.png" /> 看月报
		</p>
	</div>
	<s:if test=" staffEmployeeList != null ">
		<div class="addbox">
			<div class="addtitle">人员选择</div>
			<p>
				<s:iterator value="staffEmployeeList" var="emp">
					<a id="${emp.id}" href="###" onclick="loadMonthlyContentByEmployee(${emp.id})">${emp.name}</a>
				</s:iterator>
				<script type="text/javascript">
					$("#"+$("#empId").val()).attr('style','font-weight:bold;');
					function loadMonthlyContentByEmployee(id){
						$("#empId").val(id);
						$.ajax({
							  url:'${vix}/crm/agenda/monthlyAction!goTab2ListContent.action?employeeId='+id,
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
		<input type="hidden" id="nextMonthNumber" name="nextMonthNumber" value="${nextMonthNumber}" /> <input type="hidden" id="previousMonthNumber" name="previousMonthNumber" value="${previousMonthNumber}" />
		<table width="100%">
			<tr>
				<th style="text-align: center;" colspan="2"><a href="#" onclick="loadWeeklyContent('newtab2',$('#previousMonthNumber').val());">&lt;&lt;</a> ${currentMonth} <a href="#" onclick="loadWeeklyContent('newtab2',$('#nextMonthNumber').val());">&gt;&gt;</a></th>
			</tr>
			<tr>
				<td colspan="2"><b>本月计划：（由上月拟定）</b><br />
					<div>${preMonthly.nextMonthPlan}</div></td>
			</tr>
			<tr>
				<td><b>本月总结</b><br />
					<div>${monthly.monthSummary}</div></td>
				<td><b>下月计划</b><br />
					<div>${monthly.nextMonthPlan}</div></td>
			</tr>
			<tr>
				<td colspan="2"><b>补充回复：</b><br />
				<br /> <b>补充回复信息：</b><br /> <textarea name="" style="width: 80%; height: 100px; margin: 0 0 10px;" cols="" rows=""></textarea><br /> <input name="" type="button" class="btn" value="保存" /><br />&nbsp;</td>
			</tr>
		</table>
	</div>
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
</div>