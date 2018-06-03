<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 办公 <span onclick="">&gt; 日报</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/dailyJournalAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>日报/工作日志</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="workLogForm">
				<fieldset>
					<input type="hidden" id="id" name="workLog.id" value="${workLog.id}" /> <input type="hidden" id="employeeIds" name="employeeIds" value="${employeeIds }" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 日志标题:</label>
						<div class="col-md-3">
							<input id="title" name="workLog.title" value="${workLog.title}" class="form-control validate[required]" type="text" placeholder="日志标题" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 日志类型:</label>
						<div class="col-md-3">
							<s:if test="workLog.logType == 0">
								<label class="radio radio-inline"> <input type="radio" id="logType" name="workLog.logType" value="0" checked="checked"> 工作日志
								</label>
								<label class="radio radio-inline"> <input type="radio" id="logType" name="workLog.logType" value="1"> 个人日志
								</label>
							</s:if>
							<s:elseif test="workLog.logType == 1">
								<label class="radio radio-inline"> <input type="radio" id="logType" name="workLog.logType" value="0"> 工作日志
								</label>
								<label class="radio radio-inline"> <input type="radio" id="logType" name="workLog.logType" value="1" checked="checked"> 个人日志
								</label>
							</s:elseif>
							<s:else>
								<label class="radio radio-inline"> <input type="radio" id="logType" name="workLog.logType" value="0" checked="checked"> 工作日志
								</label>
								<label class="radio radio-inline"> <input type="radio" id="logType" name="workLog.logType" value="1"> 个人日志
								</label>
							</s:else>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="startTime" name="workLog.startTime" value="${workLog.startTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'startTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="logDate" name="workLog.logDate" value="${workLog.logDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'logDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> <span class="text-danger">*</span> 上级领导:
						</label>
						<div class="col-md-8">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="employeeNames" class="form-control validate[required]" value="${employeeNames }">
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
						<label class="col-md-2 control-label"> <span class="text-danger">*</span> 日志详情:
						</label>
						<div class="col-md-8">
							<div class="jarviswidget jarviswidget-color-gray">
								<header>
									<span class="widget-icon"> <i class="fa fa-pencil"></i>
									</span>
									<h2>日志详情</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<textarea class="form-control no-border" rows="4" id="logContent" name="workLog.logContent"> ${workLog.logContent }</textarea>
										<div class="widget-footer">
											<button class="btn btn-sm btn-primary" type="button" onclick="getTaskInfo();">
												<i class="fa fa-copy"></i> 获取任务反馈
											</button>
											<button class="btn btn-sm btn-default pull-left" type="button" onclick="$('#logContent').val('');">
												<i class="glyphicon glyphicon-repeat"></i> 清空
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 附件列表: </label>
						<div class="col-md-8">
							<div id="" class="jarviswidget">
								<header>
									<span class="widget-icon"><i class="fa fa-table"></i></span>
									<h2>附件</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin: 5px;">
											<div class="form-group" style="margin: 0 5px;">
												<div class="listMenu-float-right">
													<input type="file" id="docToUpload" name="docToUpload" onchange="fileChange();" style="width: 100%;">
												</div>
											</div>
										</div>
										<table id="uploaderTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/dailyJournalAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	//$('#employeeNames').tagsinput();
	var uploaderColumns = [ {
	"width" : "10%",
	"orderable" : false,
	"title" : "编号",
	"data" : function(data) {
		return "";
	}
	}, {
	"width" : "50%",
	"name" : "fileName",
	"title" : "附件名称",
	"data" : function(data) {
		return data.fileName;
	}
	}, {
	"title" : "上传时间",
	"width" : "30%",
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteUploaderById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
	}
	} ];

	var uploaderTable = initDataTable("uploaderTableId", "${nvix}/nvixnt/dailyJournalAction!goUploaderList.action?id=${workLog.id}", uploaderColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	//上传附件
	function fileChange() {
		uploadFileOrImage("taskFileForm", "${nvix}/nvixnt/dailyJournalAction!saveOrUpdateUploader.action?id=${workLog.id}", "docToUpload", "file", function(data) {
			uploaderTable.ajax.reload();
			showMessage("文件上传成功!");
		});
	};
	//获取反馈内容
	function getTaskInfo() {
		chooseListData('${nvix}/nvixnt/dailyJournalAction!goTaskChoose.action', 'multi', '选择任务', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$.ajax({
				url : '${nvix}/nvixnt/dailyJournalAction!getTaskInfo.action?taskIds=' + emp[0],
				cache : false,
				success : function(html) {
					$('#logContent').val(html);
				}
				});
			} else {
				layer.alert("请选择任务!");
			}
		}, 750, 550);
	};
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
	//删除附件
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/dailyJournalAction!deleteUploaderById.action?id=' + id, '是否删除附件?', uploaderTable);
	};
	//保存日报
	$("#workLogForm").validationEngine();
	function saveOrUpdate() {
		if ($("#workLogForm").validationEngine('validate')) {
			$("#workLogForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/dailyJournalAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/dailyJournalAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};

	$("#logContent").markdown({
		autofocus : false
	});
</script>