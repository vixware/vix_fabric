<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="meetingRequestForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/conferenceManagementAction!saveMeetingRD.action">
	<input type="hidden" id="id" name="meetingRequest.id" value="${meetingRequest.id}" /> <input type="hidden" id="employeeIds" name="employeeIds" value="" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>会议室名称:</label>
			<div class="col-md-4">
				<input id="meetingName" name="meetingRequest.meetingName" placeholder="会议室名称" value="${meetingRequest.meetingName}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>可容纳人数:</label>
			<div class="col-md-4">
				<input id="capacity" name="meetingRequest.capacity" placeholder="可容纳人数" value="${meetingRequest.capacity}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>会议室编码:</label>
			<div class="col-md-4">
				<input id="code" name="meetingRequest.code" placeholder="会议室编码" value="${meetingRequest.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label">是否启用:</label>
			<div class="col-md-4">
				<s:if test="meetingRequest.meetingRoomStatus == 0">
					<label class="radio radio-inline"> <input type="radio" id="meetingRoomStatus" name="meetingRequest.meetingRoomStatus" value="0" checked="checked"> 启用
					</label>
					<label class="radio radio-inline"> <input type="radio" id="meetingRoomStatus" name="meetingRequest.meetingRoomStatus" value="1"> 禁用
					</label>
				</s:if>

				<s:elseif test="meetingRequest.meetingRoomStatus == 1">
					<label class="radio radio-inline"> <input type="radio" id="meetingRoomStatus" name="meetingRequest.meetingRoomStatus" value="0"> 启用
					</label>
					<label class="radio radio-inline"> <input type="radio" id="meetingRoomStatus" name="meetingRequest.meetingRoomStatus" value="1" checked="checked"> 禁用
					</label>
				</s:elseif>

				<s:else>
					<label class="radio radio-inline"> <input type="radio" id="meetingRoomStatus" name="meetingRequest.meetingRoomStatus" value="0" checked="checked"> 启用
					</label>
					<label class="radio radio-inline"> <input type="radio" id="meetingRoomStatus" name="meetingRequest.meetingRoomStatus" value="1"> 禁用
					</label>
				</s:else>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 管理员:</label>
			<div class="col-md-7">
				<input id="employeeNames" name="meetingRequest.creator" placeholder="会议室管理员" value="${meetingRequest.creator}" class="form-control" type="text" />
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
			<label class="col-md-2 control-label"> 会议室描述:</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="4" id="description" name="meetingRequest.description" value="${meetingRequest.description}" placeholder="会议室情况描述">${meetingRequest.description}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 设备情况:</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="4" id="equipment" name="meetingRequest.equipment" value="${meetingRequest.equipment}" placeholder="会议室设备情况">${meetingRequest.equipment}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 备注:</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="4" id="memo" name="meetingRequest.memo" value="${meetingRequest.memo}" placeholder="备注">${meetingRequest.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#meetingRequestForm").validationEngine();

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