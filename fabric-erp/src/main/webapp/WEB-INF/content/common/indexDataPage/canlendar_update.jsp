<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#calendarsform").validationEngine();
var map = {"1":"d1", "2":"d2", "3":"d3", "4":"d4"};  
$(function() {
	$("#isRemind").val(${calendars.isRemind });
	$("#isRepeat").val(${calendars.isRepeat });
	if ('${calendars.allDay}' == "1") {
		$("#datetypea").attr("checked", true);
	}else {
		$("#datetypeb").attr("checked", true);
	}
	
	if('${calendars.isRepeat}' == "1"){
		$("#d1").show().siblings().hide();  
	}else if ('${calendars.isRepeat}' == "2"){
		$("#d2").show().siblings().hide();  
	}else if ('${calendars.isRepeat}' == "3"){
		$("#d3").show().siblings().hide();  
	}else if ('${calendars.isRepeat}' == "4"){
		$("#d4").show().siblings().hide();  
	}
});



function funccc() {
	$dp.$('weekday').value = $dp.cal.getP('y') + '-' + $dp.cal.getP('W', 'WW');
}

$("#isRepeat").bind("change", function(){
	var divId = map[this.value];
	$("#"+divId).show().siblings().hide();
});  
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="calendarsform">
		<s:hidden id="id" name="id" value="%{calendars.id}" theme="simple" />
		<s:hidden id="taskType" name="taskType" value="%{calendars.taskType}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td>主题：</td>
					<td><input id="name" name="name" value="${calendars.scheduleName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr>
					<td>内容：</td>
					<td><textarea id="calendarsContent" name="calendarsContent">${calendars.calendarsContent }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
						var calendarsContent = KindEditor.create('textarea[name="calendarsContent"]', {
						basePath : '${vix}/plugin/KindEditor/',
						width : 700,
						height : 200,
						cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
						uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
						fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
						allowFileManager : true
						});
					</script></td>
				</tr>
				<tr height="40">
					<td>时间：</td>
					<td><label><input id="datetypea" type="radio" name="allDay" value="1" /></label> <label>全天</label><label for="datetypeb"><input id="datetypeb" name="allDay" type="radio" value="0" />时间段： <input id="startTime" type="text" value="<s:date format="yyyy-MM-dd HH:mm:ss" name="%{calendars.startTime}" />"
							onFocus="var endTime=$dp.$('endTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd  HH:mm:ss'})" />至 <input id="endTime" type="text" value="<s:date format="yyyy-MM-dd HH:mm:ss" name="%{calendars.endTime}" />"
							onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd  HH:mm:ss'})" /></label><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td>是否提醒：</td>
					<td><label><input id="isRemind1" type="radio" name="isRemind" value="1" /></label><label>不提醒</label> <label><input id="isRemind2" type="radio" name="isRemind" value="2" /></label><label>提醒</label></td>
				</tr>
				<tr>
					<td>提醒时间：</td>
					<td><div id="d1">
							<input id="remindTime" type="text" onClick="WdatePicker({dateFmt:'MM-dd  HH:mm:ss'})" value="<s:date format="yyyy-MM-dd HH:mm:ss" name="%{calendars.remindTime}" />" />
						</div>
						<div id="d2" style="display: none;">
							<input id="remindTime" type="text" onClick="WdatePicker({dateFmt:'dd  HH:mm:ss'})" value="<s:date format="yyyy-MM-dd HH:mm:ss" name="%{calendars.remindTime}" />" />
						</div>
						<div id="d3" style="display: none;">
							<input id="remindTime" type="text" onClick="WdatePicker({dateFmt:'HH:mm:ss'})" value="<s:date format="yyyy-MM-dd HH:mm:ss" name="%{calendars.remindTime}" />" />
						</div>
						<div id="d4" style="display: none;">
							<input id="remindTime" class="int" type="text" onfocus="WdatePicker({isShowWeek:true,onpicked:funccc,errDealMode:3})" />
						</div></td>
				</tr>
				<tr height="40">
					<td>是否重复：</td>
					<td><select id="isRepeat" name="isRepeat">
							<option value="0" selected="selected">不重复</option>
							<option value="1">每年</option>
							<option value="2">每月</option>
							<option value="3">每日</option>
							<option value="4">每周</option>
					</select><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>