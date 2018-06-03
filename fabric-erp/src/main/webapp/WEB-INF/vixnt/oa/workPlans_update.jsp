<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="workPlansForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/workPlansAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="projectManagement.id" value="${projectManagement.id}" /> <input type="hidden" id="employeeIds" name="employeeIds" value="${employeeIds}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划标题:</label>
			<div class="col-md-10">
				<input id="projectManagement.proposalTitle" name="projectManagement.proposalTitle" value="${projectManagement.proposalTitle}" class="form-control validate[required]" type="text" data-prompt-position="topLeft" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 开始时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="initiateTime" name="projectManagement.initiateTime" value="${projectManagement.initiateTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /><span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'initiateTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 结束时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="overTime" name="projectManagement.overTime" value="<s:date name="%{projectManagement.overTime}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" data-prompt-position="topLeft" /><span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'overTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 上级领导:</label>
			<div class="col-md-10">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="employeeNames" value="${employeeNames }" class="form-control validate[required]" type="text" readonly="readonly" data-prompt-position="topLeft" />
							<div class="input-group-btn">
								<button onclick="chooseEmployee();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i> 选择
								</button>
								<button onclick="$('#employeeIds').val('');$('#employeeNames').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i> 清空
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划内容:</label>
			<div class="col-md-10">
				<textarea class="form-control validate[required]" rows="4" id="planContent" name="projectManagement.planContent" data-prompt-position="topLeft">${projectManagement.planContent}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#workPlansForm").validationEngine();
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goHigherLevelEmployee.action', 'multi', '选择人员', null, function() {
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
