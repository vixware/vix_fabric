<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="taskForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixProjectAction!saveOrUpdateTask.action">
	<input type="hidden" id="id" name="vixTask.id" value="${vixTask.id}" /> <input type="hidden" id="code" name="vixTask.code" value="${vixTask.code}" /> <input type="hidden" id="employeeIds" name="employeeIds" value="" /> <input type="hidden" id="fileId" name="fileId" value="" /> <input type="hidden" id="parentVixTaskId"
		name="vixTask.parentVixTask.id" value="${vixTask.parentVixTask.id}" /><input type="hidden" id="imagesId" name="imagesId" value="${imagesId}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 任务主题:</label>
			<div class="col-md-4">
				<input id="questName" name="vixTask.questName" value="${vixTask.questName}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 优先级:</label>
			<div class="col-md-4">
				<input id="priority" name="vixTask.priority" value="${vixTask.priority}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 预计开始时间:</label>
			<div class="col-md-4">
				<input id="taskStartTime" name="vixTask.taskStartTime" value="${vixTask.taskStartTime}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 预计结束时间:</label>
			<div class="col-md-4">
				<input id="taskEndTime" name="vixTask.taskEndTime" value="${vixTask.taskEndTime}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
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
			<div class="col-md-7">
				<input id="employeeNames" name="employeeNames" value="" class="form-control validate[required]" type="text" />
			</div>
			<div class="col-md-3">
				<button onclick="chooseEmployee();" type="button" class="btn btn-info">
					<i class="glyphicon glyphicon-search"></i> 选择
				</button>
				<button onclick="$('#employeeNames').val('');uploaderTable.ajax.reload();" type="button" class="btn btn-default">
					<i class="glyphicon glyphicon-repeat"></i> 清空
				</button>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 任务缩略图:</label>
			<div class="col-md-10">
				<div style="float: left; display: inline;">
					<img id="mainPic" onerror="$('#mainPic').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${nvix}/${project.pictureurl}" width="30px" height="30px" onmouseover="$('#mainPicBig').attr('style','position: absolute;padding:-50px;z-index:9999;');" onmouseout="$('#mainPicBig').attr('style','display:none;');" /> <img id="mainPicBig"
						onerror="$('#mainPicBig').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${nvix}/${project.pictureurl}" width="100" height="100" style="display: none; position: absolute;" /> &nbsp;&nbsp;
				</div>
				<input type="file" id="fileToUpload" name="fileToUpload" onchange="imagesChange();" style="width: 80%;">
			</div>
		</div>
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
								<th width="50%">附件名称</th>
								<th width="25%">上传时间</th>
								<th width="15%">操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
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
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
	} ];

	var uploaderTable = initDataTable("uploaderTableId", "${nvix}/nvixnt/nvixProjectAction!goUploaderList.action?id=${vixTask.id}", uploaderColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId
		};
	});
	function fileChange() {
		uploadFileOrImage("taskFileForm", "${nvix}/nvixnt/nvixProjectAction!saveOrUpdateUploader.action?id=" + id + "&imagesId=" + imagesId, "docToUpload", "file", function(data) {
			$('#fileId').val(data);
			showMessage("文件上传成功!");
		});
	};
	function imagesChange() {
		uploadFileOrImage("taskImageForm", "${nvix}/nvixnt/nvixProjectAction!saveOrUpdateTaskPicture.action", "fileToUpload", "image", function(data) {
			var d = data.split(",");
			if (d[0] == '0') {
				showMessage(d[1]);
			} else {
				$('#imagesId').val(d[0]);
				$("#mainPic").attr("src", "${nvix}" + d[1]);
				$("#mainPicBig").attr("src", "${nvix}" + d[1]);
				showMessage("图片上传成功!");
			}
		});
	};
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
	}
</script>