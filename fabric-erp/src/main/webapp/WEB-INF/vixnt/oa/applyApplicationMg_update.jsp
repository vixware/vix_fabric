<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="applicationMgForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/conferenceManagementAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="applicationMg.id" value="${applicationMg.id}" /> <input type="hidden" id="meetingRequestId" name="applicationMg.meetingRequest.id" value="${applicationMg.meetingRequest.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>会议标题:</label>
			<div class="col-md-4">
				<input id="meetingTheme" name="applicationMg.meetingTheme" placeholder="会议标题" value="${applicationMg.meetingTheme}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 会议室:</label>
			<div class="col-md-4">
				<input id="meetingName" name="applicationMg.meetingRequest.meetingName" placeholder="会议标题" value="${applicationMg.meetingRequest.meetingName}" class="form-control validate[required]" type="text" />
				<%-- <select id="meetingRequestId" name="applicationMg.meetingRequest.id" data-prompt-position="topLeft" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${meetingRequestList}" var="entity">
						<option value="${entity.id}" <c:if test="${applicationMg.meetingRequest.id == entity.id}">selected="selected"</c:if>>${entity.meetingName}</option>
					</c:forEach>
				</select> --%>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
			<div class="col-md-4">
				<input id="meetingStartTime" name="applicationMg.meetingStartTime" placeholder="点击选择会议开始时间" value="<s:date name="%{applicationMg.meetingStartTime}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
			<div class="col-md-4">
				<input id="meetingEndTime" name="applicationMg.meetingEndTime" placeholder="点击选择会议结束时间" value="<s:date name="%{applicationMg.meetingEndTime}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">是否申请:</label>
			<div class="col-md-4">
				<s:if test="applicationMg.bookingSituation == 0">
					<label class="radio radio-inline"> <input type="radio" id="bookingSituation" name="applicationMg.bookingSituation" value="0" checked="checked"> 是
					</label>
					<label class="radio radio-inline"> <input type="radio" id="bookingSituation" name="applicationMg.bookingSituation" value="1"> 否
					</label>
				</s:if>

				<s:elseif test="applicationMg.bookingSituation == 1">
					<label class="radio radio-inline"> <input type="radio" id="bookingSituation" name="applicationMg.bookingSituation" value="0"> 是
					</label>
					<label class="radio radio-inline"> <input type="radio" id="bookingSituation" name="applicationMg.bookingSituation" value="1" checked="checked"> 否
					</label>
				</s:elseif>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 参与对象:</label>
			<div class="col-md-7">
				<input id="employeeNames" name="applicationMg.participants" placeholder="会议参与人！" value="${applicationMg.participants}" class="form-control required" type="text" />
			</div>
			<div class="col-md-3">
				<button onclick="chooseEmployee();" type="button" class="btn btn-info">
					<i class="glyphicon glyphicon-search"></i> 选择
				</button>
				<button onclick="$('#employeeNames').val('');" type="button" class="btn btn-default">
					<i class="glyphicon glyphicon-repeat"></i> 清空
				</button>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 会议描述:</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="4" id="meetingDescription" name="applicationMg.meetingDescription" placeholder="会议申请情况描述" value="${applicationMg.meetingDescription}">${applicationMg.meetingDescription}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#applicationMgForm").validationEngine();

	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goEmployeeChoose.action', 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#employeeIds').val(emp[0]);
				$('#employeeNames').val(emp[1]);
			} else {
				layer.alert("请选择人员!");
			}
		});
	};
</script>
