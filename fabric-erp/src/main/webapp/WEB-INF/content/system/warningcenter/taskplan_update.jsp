<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#taskPlanForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="taskPlanForm">
		<input type="hidden" id="taskPlanId" name="taskPlanId" value="${taskPlan.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">执行频率：</td>
					<td><input id="executionFrequency" name="executionFrequency" type="text" size="20" value="${taskPlan.executionFrequency}" /></td>
					<td align="right">单位：</td>
					<td><input id="executionFrequencyUtil" name="executionFrequencyUtil" type="text" size="20" value="${taskPlan.executionFrequencyUtil}" /></td>
				</tr>
				<tr height="40">
					<td align="right">下次执行时间：</td>
					<td><input id="nextTime" name="nextTime" value="<s:date name="%{taskPlan.nextTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" /> <img onclick="showTime('nextTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="middle"></td>
					<td align="right">上次执行时间：</td>
					<td><input id="lastTime" name="lastTime" value="<s:date name="%{taskPlan.lastTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" /> <img onclick="showTime('lastTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="middle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>