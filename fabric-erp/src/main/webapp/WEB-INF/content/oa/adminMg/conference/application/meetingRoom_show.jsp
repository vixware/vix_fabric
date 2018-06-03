<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#meetingRoomform").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="meetingRoomform">
		<s:hidden id="id" name="id" value="%{meetingRoom.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td width="15%">主题：</td>
					<td><span>${meetingRoom.meetingName }</span></td>
				</tr>
				<tr height="40">
					<td>内容：</td>
					<td><span>${meetingRoom.content}</span></td>
				</tr>
				<tr height="40">
					<td>开始时间：</td>
					<td><span><s:date format="yyyy-MM-dd HH:mm:ss" name="%{meetingRoom.startTime}" /></span></td>
				</tr>
				<tr height="40">
					<td>结束时间：</td>
					<td><span><s:date format="yyyy-MM-dd HH:mm:ss" name="%{meetingRoom.endTime}" /></span></td>
				</tr>
				<tr height="40">
					<td>提醒：</td>
					<td><select id="isRemind" name="isRemind" disabled="disabled">
							<option value="1">不提醒</option>
							<option value="2">指定时间</option>
					</select></td>
				</tr>
				<tr height="40">
					<td>重复：</td>
					<td><select id="isRepeat" name="isRepeat" disabled="disabled">
							<option value="0" selected="selected">不重复</option>
							<option value="1">每年</option>
							<option value="2">每月</option>
							<option value="3">每日</option>
							<option value="4">每周</option>
							<option value="5">每工作日</option>
					</select></td>
				</tr>
			</table>
		</div>
	</form>
</div>