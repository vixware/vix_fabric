<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<h2>${month}- ${month} 人员</h2>
<p class="drt clearfix">
	<span class="gray">注意：未写月报的日期不再下面显示</span>
</p>
<div class="addbox">
	<div class="addtitle">${month}</div>
	<div class="addbox_content">
		<p class="gray">
			<b>今月总结</b>
		</p>
		<p class="gray">${monthly.monthSummary}</p>
		<p class="gray">
			<b>明月计划</b>
		</p>
		<p class="gray">${monthly.nextMonthPlan}</p>
		<p class="gray">
			<b>批示和回复</b>
		</p>
		<p class="gray">${monthly.nextMonthPlan}</p>
	</div>
</div>
<h4>写回复</h4>
<form action="" method="get">
	<textarea class="area" name="" cols="" rows=""></textarea>
	<br />
	<input name="" type="button" value="确认回复" class="btn" />
</form>