<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>主题 : <input type="text" name="theme" id="theme" class="int" /></td>
				<td>用车人 : <input type="text" name="reasons" id="reasons" class="int" /></td>
			</tr>
			<tr height="30">
				<td>开始时间： <input id="meetingStartTime" type="text" name="meetingStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})">
				</td>
				<td>结束时间： <input id="meetingEndTime" type="text" name="meetingEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})">
				</td>
			</tr>
			<tr height="30">
				<td>参与人 : <input type="text" name="participants" id="participants" class="int" /></td>
				<td>事由 : <input type="text" name="carName" id="carName" class="int" /></td>
			</tr>
		</table>
	</div>
</div>