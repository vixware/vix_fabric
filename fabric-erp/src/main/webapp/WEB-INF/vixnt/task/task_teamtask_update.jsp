<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="taskForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/taskAndPlanAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="vixTask.id" value="${vixTask.id}" /> <input type="hidden" id="code" name="vixTask.code" value="${vixTask.code}" /> <input type="hidden" id="taskemployeeId" name="employeeIds" value="${employeeIds}" /> <input type="hidden" id="parentVixTaskId" name="vixTask.parentVixTask.id" value="${vixTask.parentVixTask.id}" />
	<input type="hidden" id="projectId" name="vixTask.project.id" value="${vixTask.project.id}" /> <input type="hidden" id="taskSchedule" name="vixTask.taskSchedule" value="${vixTask.taskSchedule}" /> <input type="hidden" id="complete" name="vixTask.complete" value="${vixTask.complete}" /> <input type="hidden" id="status" name="vixTask.status"
		value="${vixTask.status}" /> <input type="hidden" id="isTemp" name="vixTask.isTemp" value="${vixTask.isTemp}" /> <input type="hidden" id="isDeleted" name="vixTask.isDeleted" value="${vixTask.isDeleted}" /> <input type="hidden" id="flag" name="vixTask.flag" value="1" />
	<input type="hidden" id="createTime" name="vixTask.createTime" value="${vixTask.createTimeTimeStr}" />
	<input type="hidden" id="updateTime" name="vixTask.updateTime" value="${vixTask.updateTimeTimeStr}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 任务主题:</label>
			<div class="col-md-10">
				<input id="questName" name="vixTask.questName" value="${vixTask.questName}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 类型:</label>
			<div class="col-md-4">
				<select id="type" name="vixTask.taskType.id" class="form-control validate[required]">
					<c:forEach items="${taskTypeList}" var="taskType">
						<option value="${taskType.id}" <c:if test='${taskType.id == vixTask.taskType.id}'>selected="selected"</c:if>>${taskType.name}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 优先级:</label>
			<div class="col-md-4">
				<select id="priority" name="vixTask.priority" class="form-control validate[required]">
					<option value="1" <c:if test='${vixTask.priority eq 1}'>selected="selected"</c:if>>普通</option>
					<option value="2" <c:if test='${vixTask.priority eq 2}'>selected="selected"</c:if>>!不紧急但重要</option>
					<option value="3" <c:if test='${vixTask.priority eq 3}'>selected="selected"</c:if>>!!紧急但不重要</option>
					<option value="4" <c:if test='${vixTask.priority eq 4}'>selected="selected"</c:if>>!!!紧急且重要</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划开始时间:</label>
			<div class="col-md-4">
				<input id="taskStartTime" name="vixTask.taskStartTime" value="${vixTask.taskStartTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划结束时间:</label>
			<div class="col-md-4">
				<input id="taskEndTime" name="vixTask.taskEndTime" value="${vixTask.taskEndTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 实际开始时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="realityStartTime" name="vixTask.realityStartTime" value="${vixTask.realityStartTimeTimeStr}" data-prompt-position="topLeft" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'realityStartTime'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"> 实际结束时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="realityEndTime" name="vixTask.realityEndTime" value="${vixTask.realityEndTimeTimeStr}" data-prompt-position="topLeft" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'realityEndTime'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 任务描述:</label>
			<div class="col-md-10">
				<textarea name="vixTask.taskDescription" class="form-control">${vixTask.taskDescription } </textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 执行人:</label>
			<div class="col-md-10">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="taskemployeeName" name="employeeNames" value="${employeeNames }" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="chooseEmployee();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#taskemployeeId').val('');$('#taskemployeeName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 附件列表:</label>
			<div class="col-md-10">
				<div class="jarviswidget">
					<header style="height: 1px; border-color: #ddd !important"></header>
					<div>
						<div class="widget-body no-padding">
							<div style="margin: 5px;">
								<div class="form-group" style="margin: 0 5px;">
									<div class="listMenu-float-right">
										<input type="file" id="docToUpload" name="docToUpload" onchange="fileChange();" style="width: 100%;">
									</div>
								</div>
							</div>
							<table id="uploaderTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="10%">编号</th>
										<th width="60%">附件名称</th>
										<th width="20%">上传时间</th>
										<th width="10%">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#taskForm").validationEngine();
	var uploaderColumns = [ {
		"data" : function(data) {
			return "";
		}
	}, {
		"data" : function(data) {
			return data.fileName;
		}
	}, {
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
	}, {
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteUploaderById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];

	var uploaderTable = initDataTable("uploaderTableId", "${nvix}/nvixnt/taskAndPlanAction!goUploaderList.action?id=${vixTask.id}", uploaderColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	function fileChange() {
		uploadFileOrImage("taskFileForm", "${nvix}/nvixnt/taskAndPlanAction!saveOrUpdateUploader.action?id=${vixTask.id}", "docToUpload", "file", function(data) {
			uploaderTable.ajax.reload();
			showMessage("文件上传成功!");
		});
	};
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goTeamEmployeeChoose.action', 'single', '选择人员', 'taskemployee');
	};
	/* function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goTeamEmployeeChoose.action', 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#taskemployeeIds').val(emp[0]);
				$('#taskemployeeNames').val(emp[1]);
			} else {
				layer.alert("请选择人员!");
			}
		}, 750, 550);
	}; */

	//删除附件
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteUploaderById.action?id=' + id, '是否删除附件?', uploaderTable);
	};
</script>