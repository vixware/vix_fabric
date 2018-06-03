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
				<tr style="display: none;">
					<td><s:radio list="#{'0':'待批准','1':'已准批准','2':'被驳回','3':'结束'}" id="bookingSituation" name="bookingSituation" value="%{carUse.bookingSituation==0}" theme="simple"></s:radio></td>
				</tr>
				<tr height="40">
					<td>主题：</td>
					<td><input id="meetingName" name="meetingName" value="${meetingRoom.meetingName }" type="text" size="30" /></td>
				</tr>
				<tr height="50">
					<td>内容：</td>
					<td><textarea id="schedulecontent" class="example" rows="2" style="width: 380px">${meetingRoom.content}</textarea></td>
				</tr>
				<tr height="40">
					<td>开始：</td>
					<td><input id="startTime" name="startTime" value="<s:date format="yyyy-MM-dd HH:mm:ss" name="%{meetingRoom.startTime}" />" type="text" readonly="readonly" /><img onclick="showTime('startTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td>结束：</td>
					<td><input id="endTime" name="endTime" value="<s:date format="yyyy-MM-dd HH:mm:ss" name="%{meetingRoom.endTime}" />" type="text" readonly="readonly" /><img onclick="showTime('endTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td></td>
					<td><label><input type="checkbox" name="allDay" /></label> <label>全天</label><label><input type="checkbox" name="chooseEndTime" /></label><label>结束时间</label></td>
				</tr>
				<tr height="40">
					<td>提醒：</td>
					<td><select id="isRemind" name="isRemind">
							<option value="1">不提醒</option>
							<option value="2">指定时间</option>
					</select><input id="remindTime" name="remindTime" value="<s:date format="yyyy-MM-dd HH:mm:ss" name="%{meetingRoom.remindTime}" />" type="text" readonly="readonly" /><img onclick="showTime('remindTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td>重复：</td>
					<td><select id="isRepeat" name="isRepeat">
							<option value="0">不重复</option>
							<option value="1" selected="selected">每年</option>
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