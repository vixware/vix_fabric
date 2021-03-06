<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="hidden" id="source" value="${source}">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>项目管理 <span>> 任务编辑 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate('');">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-success" type="button" onclick="saveOrUpdateAdd();">
					<i class="glyphicon glyphicon-plus"></i> 保存并新增
				</button>
				<!-- <button class="btn btn-default" type="button" onclick="clearInfo();">
					<i class="glyphicon glyphicon-repeat"></i> 清空
				</button> -->
				<c:if test="${source == 'project'}">
					<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectTracing.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
				<c:if test="${source == 'task'}">
					<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/taskAndPlanAction!goProjectTask.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
				<c:if test="${source == 'gtt'}">
					<c:if test="${vixTask.type eq 0}">
						<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectTaskGtt.action?id=${vixTask.parentVixTask.project.id }');">
							<i class="fa fa-rotate-left"></i> 返回
						</button>
					</c:if>
					<c:if test="${vixTask.type eq 1}">
						<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectTaskGtt.action?id=${vixTask.project.id }');">
							<i class="fa fa-rotate-left"></i> 返回
						</button>
					</c:if>
				</c:if>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>任务</h2>
		</header>
		<div class="widget-body">
			<form id="taskForm" class="form-horizontal" >
				<input type="hidden" id="vixTaskId" name="vixTask.id" value="${vixTask.id}" /> 
				<input type="hidden" id="code" name="vixTask.code" value="${vixTask.code}" /> 
				<input type="hidden" id="taskemployeeId" name="employeeIds" value="${employeeIds }" /> 
				<input type="hidden" id="imagesId" name="imagesId" value="" /> 
				<input type="hidden" id="parentVixTaskId" name="vixTask.parentVixTask.id" value="${vixTask.parentVixTask.id}" /> 
				<input type="hidden" id="projectId" name="vixTask.project.id" value="${vixTask.project.id }" /> 
				<input type="hidden" id="taskSchedule" name="vixTask.taskSchedule" value="${vixTask.taskSchedule}" /> 
				<input type="hidden" id="complete" name="vixTask.complete" value="${vixTask.complete}" /> 
				<input type="hidden" id="status" name="vixTask.status" value="${vixTask.status}" /> 
				<input type="hidden" id="isDeleted" name="vixTask.isDeleted" value="${vixTask.isDeleted}" /> 
				<input type="hidden" id="isTemp" name="vixTask.isTemp" value="${vixTask.isTemp}" /> 
				<input type="hidden" id="flag" name="vixTask.flag" value="2" />
				<input type="hidden" id="treeType"  value="${treeType}" />
				<input type="hidden" id="createTime" name="vixTask.createTime" value="${vixTask.createTimeTimeStr}" />
				<input type="hidden" id="updateTime" name="vixTask.updateTime" value="${vixTask.updateTimeTimeStr}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 任务主题:</label>
						<div class="col-md-3">
							<input id="questName" name="vixTask.questName" value="${vixTask.questName}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"> 上级任务:</label>
						<div class="col-md-3">
							<input id="parentVixTaskName"  value="${vixTask.parentVixTask.questName}" class="form-control" type="text" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 任务类型:</label>
						<div class="col-md-3">
							<s:if test="vixTask.type == null">
								<select id="type" name="vixTask.type" class="form-control validate[required]">
									<option value="0" <c:if test='${treeType == "T"}'>selected="selected"</c:if>>普通任务</option>
									<%-- <option value="1" <c:if test='${vixTask.type eq 1}'>selected="selected"</c:if>>里程碑</option> --%>
									<option value="1" <c:if test='${treeType == "P"}'>selected="selected"</c:if>>虚拟任务</option>
								</select>
							</s:if>
							<s:else>
								<select id="type" name="vixTask.type" class="form-control validate[required]">
									<option value="0" <c:if test='${vixTask.type eq 0}'>selected="selected"</c:if>>普通任务</option>
									<%-- <option value="1" <c:if test='${vixTask.type eq 1}'>selected="selected"</c:if>>里程碑</option> --%>
									<option value="1" <c:if test='${vixTask.type eq 1}'>selected="selected"</c:if>>虚拟任务</option>
								</select>
							</s:else>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 优先级:</label>
						<div class="col-md-3">
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
						<div class="col-md-3">
							<input id="taskStartTime" name="vixTask.taskStartTime" value="${vixTask.taskStartTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划结束时间:</label>
						<div class="col-md-3">
							<input id="taskEndTime" name="vixTask.taskEndTime" value="${vixTask.taskEndTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 实际开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="realityStartTime" name="vixTask.realityStartTime" value="${vixTask.realityStartTimeTimeStr}" data-prompt-position="topLeft" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'realityStartTime'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"> 实际结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="realityEndTime" name="vixTask.realityEndTime" value="${vixTask.realityEndTimeTimeStr}" data-prompt-position="topLeft" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'realityEndTime'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 任务描述:</label>
						<div class="col-md-8">
							<textarea name="vixTask.taskDescription" class="form-control validate[required]">${vixTask.taskDescription } </textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 执行人:</label>
						<div class="col-md-8">
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
						<div class="col-md-8">
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
													<th width="50%">附件名称</th>
													<th width="25%">上传时间</th>
													<th width="15%">操作</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-success" type="button" onclick="saveOrUpdateAdd();">
								<i class="glyphicon glyphicon-plus"></i> 保存并新增
							</button>
							<!-- <button class="btn btn-default" type="button" onclick="clearInfo();">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button> -->
							<c:if test="${source == 'project'}">
								<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectTracing.action');">
									<i class="fa fa-rotate-left"></i> 返回
								</button>
							</c:if>
							<c:if test="${source == 'task'}">
								<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/taskAndPlanAction!goProjectTask.action');">
									<i class="fa fa-rotate-left"></i> 返回
								</button>
							</c:if>
							<c:if test="${source == 'gtt'}">
								<c:if test="${vixTask.type eq 0}">
									<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectTaskGtt.action?id=${vixTask.parentVixTask.project.id }');">
										<i class="fa fa-rotate-left"></i> 返回
									</button>
								</c:if>
								<c:if test="${vixTask.type eq 1}">
									<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectTaskGtt.action?id=${vixTask.project.id }');">
										<i class="fa fa-rotate-left"></i> 返回
									</button>
								</c:if>
							</c:if>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
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
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];

	var uploaderTable = initDataTable("uploaderTableId", "${nvix}/nvixnt/nvixProjectAction!goUploaderList.action?id=${vixTask.id}", uploaderColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	
	function saveOrUpdate(id) {
		//表单验证
		if ($("#taskForm").validationEngine('validate')) {
			$("#taskForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixProjectAction!saveOrUpdateTask.action?id=" + $("#vixTaskId").val(),
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					var r = result.split(":");
					showMessage(r[0]);
					var source = $("#source").val();
					if(source == 'project'){
						loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectTracing.action');
					}else if(source == 'task'){
						loadContent('', '${nvix}/nvixnt/taskAndPlanAction!goProjectTask.action');
					}
					
				}
			});
		}
	}
	
	function saveOrUpdateAdd() {
		//表单验证
		if ($("#taskForm").validationEngine('validate')) {
			$("#taskForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixProjectAction!saveOrUpdateTask.action?id=" + $("#vixTaskId").val(),
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					var r = result.split(":");
					showMessage(r[0]);
					var treeType = $("#treeType").val();
					goSaveOrUpdateEntity('${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateTaskAdd.action?id=' + r[1] + '&treeType=' + treeType);
				}
			});
		}
	}
	
	function fileChange() {
		uploadFileOrImage("taskFileForm", "${nvix}/nvixnt/nvixProjectAction!saveOrUpdateUploader.action?id=${vixTask.id}", "docToUpload", "file", function(data) {
			uploaderTable.ajax.reload();
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
				showMessage("文件上传成功!");
			}
		});
	};
	//删除附件
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!deleteUploaderById.action?id=' + id, '是否删除附件?', uploaderTable);
	};
	
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixProjectAction!goProjectEmployeeChoose.action?id=' + $('#projectId').val(), 'single', '选择人员', 'taskemployee');
	};
	/* function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixProjectAction!goProjectEmployeeChoose.action?id=' + $('#projectId').val(), 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#taskemployeeIds').val(emp[0]);
				$('#taskemployeeNames').val(emp[1]);
			} else {
				layer.alert("请选择人员!");
			}
		});
	} */
</script>