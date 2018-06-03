<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="meetingRequestForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/conferenceManagementAction!saveMeetingRD.action">
	<input type="hidden" id="id" name="meetingRequest.id" value="${meetingRequest.id}" /> <input type="hidden" id="creator" name="meetingRequest.creator" value="${meetingRequest.creator}" /> <input type="hidden" id="description" name="meetingRequest.description" value="${meetingRequest.description}" /> <input type="hidden" id="equipment"
		name="meetingRequest.equipment" value="${meetingRequest.equipment}" /> <input type="hidden" id="memo" name="meetingRequest.memo" value="${meetingRequest.memo}" /> <input type="hidden" id="code" name="meetingRequest.code" value="${meetingRequest.code}" /> <input type="hidden" id="capacity" name="meetingRequest.capacity"
		value="${meetingRequest.capacity}" /> <input type="hidden" id="meetingName" name="meetingRequest.meetingName" value="${meetingRequest.meetingName}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">会议室名称:</label>
			<div class="col-md-4">
				<input id="meetingName" name="meetingRequest.meetingName" disabled="disabled" placeholder="如无显示名称，此会议室无名称！" value="${meetingRequest.meetingName}" class="form-control required" type="text" />
			</div>
			<label class="col-md-2 control-label">可容纳人数:</label>
			<div class="col-md-4">
				<input id="capacity" name="meetingRequest.capacity" disabled="disabled" placeholder="如无显示可容纳人数，此会议室未规定！" value="${meetingRequest.capacity}" class="form-control required" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">会议室编码:</label>
			<div class="col-md-4">
				<input id="code" name="meetingRequest.code" disabled="disabled" placeholder="如无会议室编码，此会议室无编码！" value="${meetingRequest.code}" class="form-control required" type="text" />
			</div>
			<label class="col-md-2 control-label">是否启用:</label>
			<div class="col-md-4">
				<s:if test="meetingRequest.meetingRoomStatus == 0">
					<label class="radio radio-inline"> <input type="radio" id="meetingRoomStatus" name="meetingRequest.meetingRoomStatus" value="0" checked="checked" class="rad"> 启用
					</label>
					<label class="radio radio-inline"> <input type="radio" id="meetingRoomStatus" name="meetingRequest.meetingRoomStatus" value="1" class="rad"> 禁用
					</label>
				</s:if>

				<s:elseif test="meetingRequest.meetingRoomStatus == 1">
					<label class="radio radio-inline"> <input type="radio" id="meetingRoomStatus" name="meetingRequest.meetingRoomStatus" value="0" class="rad"> 启用
					</label>
					<label class="radio radio-inline"> <input type="radio" id="meetingRoomStatus" name="meetingRequest.meetingRoomStatus" value="1" checked="checked" class="rad"> 禁用
					</label>
				</s:elseif>
			</div>
		</div>

		<s:if test="meetingRequest.meetingRoomStatus == 0">
			<div id="show" style="display: none;">
				<div class="form-group">
					<label class="col-md-2 control-label">开始时间:</label>
					<div class="col-md-4">
						<input id="allowedStartTime" name="meetingRequest.allowedStartTime" placeholder="点击选择禁用开始时间" value="<s:date name="%{meetingRequest.allowedStartTime}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</div>
					<label class="col-md-2 control-label">结束时间:</label>
					<div class="col-md-4">
						<input id="allowedEndTime" name="meetingRequest.allowedEndTime" placeholder="无结束时间需手动解除" value="<s:date name="%{meetingRequest.allowedEndTime}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">禁用原因:</label>
					<div class="col-md-10">
						<textarea class="form-control" rows="4" id="name" name="meetingRequest.name" value="${meetingRequest.name}" placeholder="会议室禁用情况描述">${meetingRequest.name}</textarea>
					</div>
				</div>
			</div>
		</s:if>
		<s:elseif test="meetingRequest.meetingRoomStatus == 1">
			<div id="show">
				<div class="form-group">
					<label class="col-md-2 control-label">开始时间:</label>
					<div class="col-md-4">
						<input id="allowedStartTime" name="meetingRequest.allowedStartTime" placeholder="点击选择禁用开始时间" value="<s:date name="%{meetingRequest.allowedStartTime}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</div>
					<label class="col-md-2 control-label">结束时间:</label>
					<div class="col-md-4">
						<input id="allowedEndTime" name="meetingRequest.allowedEndTime" placeholder="无结束时间需手动解除" value="<s:date name="%{meetingRequest.allowedEndTime}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label"> 禁用原因:</label>
					<div class="col-md-10">
						<textarea class="form-control" rows="4" id="name" name="meetingRequest.name" value="${meetingRequest.name}" placeholder="会议室禁用情况描述">${meetingRequest.name}</textarea>
					</div>
				</div>
			</div>
		</s:elseif>
	</fieldset>
</form>
<script type="text/javascript">
//表单校验
$("#meetingRequestForm").validationEngine();

$(function(){
   $(".rad").click(function(){
  	  if($(this).attr("value")=="1")
   		 $("#show").show();
  	  else
         $("#show").hide();
    });
});
</script>
