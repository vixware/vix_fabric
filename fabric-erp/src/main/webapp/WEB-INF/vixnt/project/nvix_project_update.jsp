<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="projectForm" class="form-horizontal" style="padding: 40px;" action="${nvix}/nvixnt/nvixProjectAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="project.id" value="${project.id}" /> <input type="hidden" id="isTemp" name="project.isTemp" value="${project.isTemp}" /> <input type="hidden" id="status" name="project.status" value="${project.status}" /> <input type="hidden" id="isDeleted"
		name="project.isDeleted" value="${project.isDeleted}" /> <input type="hidden" id="projectSchedule" name="project.projectSchedule" value="${project.projectSchedule}" /> <input type="hidden" id="taskNum" name="project.taskNum" value="${project.taskNum}" /> <input type="hidden" id="employeeId"
		name="employeeIds" value="${employeeIds }" /> <input type="hidden" id="imagesId" name="imagesId" value="${imagesId}" /> <input type="hidden" id="projectCode" name="project.projectCode" value="${project.projectCode}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 项目名称:</label>
			<div class="col-md-10">
				<input id="projectName" name="project.projectName" value="${project.projectName}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划开始时间:</label>
			<div class="col-md-4">
				<input id="estimateStartTime" name="project.estimateStartTime" value="${project.estimateStartTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划结束时间:</label>
			<div class="col-md-4">
				<input id="estimateEndTime" name="project.estimateEndTime" value="${project.estimateEndTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 开始时间:</label>
			<div class="col-md-4">
				<input id="startTime" name="project.startTime" value="${project.startTimeTimeStr}" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			</div>
			<label class="col-md-2 control-label"> 结束时间:</label>
			<div class="col-md-4">
				<input id="endTime" name="project.endTime" value="${project.endTimeTimeStr}" class="form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 项目内容:</label>
			<div class="col-md-10">
				<textarea name="project.note" class="form-control">${project.note } </textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 负责人员:</label>
			<div class="col-md-10">
				<div class="input-group">
					<input id="employeeName" name="employeeNames" value="${employeeNames }" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly"/>
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
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 项目缩略图:</label>
			<div class="col-md-10">
				<div style="float: left; display: inline;">
					<img id="mainPic" onerror="$('#mainPic').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${project.pictureurl}" width="30px" height="30px" onmouseover="$('#mainPicBig').attr('style','position: absolute;padding:-50px;z-index:9999;');"
						onmouseout="$('#mainPicBig').attr('style','display:none;');" /> <img id="mainPicBig" onerror="$('#mainPicBig').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${project.pictureurl}" width="100" height="100" style="display: none; position: absolute;" /> &nbsp;&nbsp;
				</div>
				<input type="file" id="fileToUpload" name="fileToUpload" onchange="imageChange();" style="width: 80%;">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 附件:</label>
			<div class="col-md-10">
				<div class="jarviswidget jarviswidget-color-darken">
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
										<th width="55%">附件名称</th>
										<th width="25%">上传时间</th>
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
	$("#projectForm").validationEngine();
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

	var uploaderTable = initDataTable("uploaderTableId", "${nvix}/nvixnt/nvixProjectAction!goProjectUploaderList.action?id=${project.id}", uploaderColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	function imageChange() {
		uploadFileOrImage("projectImageForm", "${nvix}/nvixnt/nvixProjectAction!saveOrUpdatePicture.action", "fileToUpload", "image", function(data) {
			var d = data.split(",");
			if (d[0] == '0') {
				showMessage(d[1]);
			} else {
				$('#imagesId').val(d[0]);
				$("#mainPic").attr("src", d[1]);
				$("#mainPicBig").attr("src", d[1]);
				showMessage("图片上传成功!");
			}
		});
	};
	function fileChange() {
		uploadFileOrImage("taskFileForm", "${nvix}/nvixnt/nvixProjectAction!saveOrUpdateProjectUploader.action?id=${project.id}", "docToUpload", "file", function(data) {
			uploaderTable.ajax.reload();
			showMessage("文件上传成功!");
		});
	};
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	};
	/*function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goEmployeeChoose.action', 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#employeeIds').val(emp[0]);
				$('#employeeNames').val(emp[1]);
			} else {
				layer.alert("请选择人员!");
			}
		});
	}; */
	//删除附件
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!deleteUploaderById.action?id=' + id, '是否删除附件?', uploaderTable);
	};
</script>