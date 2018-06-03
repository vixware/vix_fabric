<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="applicationMgForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/conferenceManagementAction!saveOrUpdateMeetingSummary.action">
	<input type="hidden" id="meetingSummaryId" name="meetingSummary.id" value="${meetingSummary.id}" /> 
	<input type="hidden" id="applicationMgId" name="meetingSummary.applicationMg.id" value="${meetingSummary.applicationMg.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>标题:</label>
			<div class="col-md-4">
				<input id="name" name="meetingSummary.name" placeholder="标题" value="${meetingSummary.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 正文:</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="4" id="meetingContent" name="meetingSummary.meetingContent" style="width: 550px; height: 200px;">${meetingSummary.meetingContent}</textarea>
			</div>
		</div>
	</fieldset>
</form>