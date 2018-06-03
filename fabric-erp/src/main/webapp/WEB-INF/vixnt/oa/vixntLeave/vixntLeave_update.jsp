<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="tripRecordForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/vixntLeaveAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="tripRecord.id" value="${tripRecord.id}" /> 
	<input type="hidden" id="employeeIds" name="employeeIds" value="${employeeIds}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
			<div class="col-md-8">
				<input id="name" name="tripRecord.name" value="${tripRecord.name}" placeholder="主题" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 开始时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="vacateStartdate" name="tripRecord.vacateStartdate" value="${tripRecord.vacateStartdateTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'vacateStartdate'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 结束时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="vacateendDate" name="tripRecord.vacateendDate" value="${tripRecord.vacateendDateTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'vacateendDate'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">请假天数:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="dates" name="tripRecord.dates" value="${tripRecord.dates}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">天</i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">请假小时数:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="minutes" name="tripRecord.minutes" value="${tripRecord.minutes}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">小时</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 请假类型:</label>
			<div class="col-md-3">
				<select id="vacateType" name="tripRecord.vacateType" class="form-control validate[required]">
					<option value="">请选择类型</option>
					<option value="1" <c:if test='${tripRecord.vacateType eq 1}'>selected="selected"</c:if>>调休</option>
					<option value="2" <c:if test='${tripRecord.vacateType eq 2}'>selected="selected"</c:if>>事假</option>
					<option value="3" <c:if test='${tripRecord.vacateType eq 3}'>selected="selected"</c:if>>病假</option>
					<option value="4" <c:if test='${tripRecord.vacateType eq 4}'>selected="selected"</c:if>>年休假</option>
					<option value="5" <c:if test='${tripRecord.vacateType eq 5}'>selected="selected"</c:if>>婚假</option>
					<option value="6" <c:if test='${tripRecord.vacateType eq 6}'>selected="selected"</c:if>>产假/陪产假</option>
					<option value="7" <c:if test='${tripRecord.vacateType eq 7}'>selected="selected"</c:if>>丧假</option>
					<option value="8" <c:if test='${tripRecord.vacateType eq 8}'>selected="selected"</c:if>>远途出差</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 审批人:</label>
			<div class="col-md-8">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="employeeNames" name="employeeNames" value="${employeeNames }" class="form-control validate[required]" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="chooseEmployee();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#employeeIds').val('');$('#employeeNames').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 请假原因:</label>
			<div class="col-md-8">
				<textarea class="form-control validate[required]" rows="4" id="vacateReason" name="tripRecord.vacateReason">${tripRecord.vacateReason}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#tripRecordForm").validationEngine();
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