<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="viewRoomsForm" class="form-horizontal" style="padding: 20px 40px;">
	<input type="hidden" id="id" name="meetingRequest.id" value="${meetingRequest.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">会议室:</label>
			<div class="col-md-4">
				<input id="meetingName" name="meetingRequest.meetingName" disabled="disabled" value="${meetingRequest.meetingName}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">可容纳人数:</label>
			<div class="col-md-4">
				<input id="capacity" name="meetingRequest.capacity" disabled="disabled" value="${meetingRequest.capacity}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">管理员:</label>
			<div class="col-md-10">
				<input id="creator" name="meetingRequest.creator" disabled="disabled" placeholder="如没有显示管理员，此会议室无管理员！" value="${meetingRequest.creator}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">会议室描述:</label>
			<div class="col-md-10">
				<input id="description" name="meetingRequest.description" disabled="disabled" placeholder="如没有显示描述，此会议室无情况描述！" value="${meetingRequest.description}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">设备情况:</label>
			<div class="col-md-10">
				<input id="equipment" name="meetingRequest.equipment" disabled="disabled" placeholder="如没有显示设备情况，此会议室无设备！" value="${meetingRequest.equipment}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<input id="memo" name="meetingRequest.memo" disabled="disabled" placeholder="如没有显示备注，此会议室无备注！" value="${meetingRequest.memo}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>
