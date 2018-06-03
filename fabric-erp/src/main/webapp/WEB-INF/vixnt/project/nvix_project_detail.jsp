<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-puzzle-piece"></i> <a style="cursor: pointer;" onclick="loadContent('mid_project','${nvix}/nvixnt/nvixProjectAction!goList.action');">项目管理</a> <span>> 项目详情 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<c:if test="${source == 'project'}">
					<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectAction!goList.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
				<c:if test="${source == 'home'}">
					<button class="btn btn-default" type="button" onclick="loadContent('mid_index','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>${project.projectName }</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div class="col-sm-12 col-md-12 col-lg-7">
					<br>
					<div class="row">
						<div class="col-sm-12 col-md-12 col-lg-12">
							<button type="button" class="btn btn-default" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goShowProjectEmployeeList.action?projectId=${project.id }');">
								<span class="glyphicon glyphicon-list text-muted"></span> （${employeeNum }）
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goListProjectUploaderList.action?id=${project.id }');">
								<span class="glyphicon glyphicon-paperclip text-muted"></span> （${uploaderNum }）
							</button>
							<%-- <button type="button" class="btn btn-default" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectDiscuss.action?id=${project.id }');">
								<span class="glyphicon glyphicon-comment text-muted"></span> （${evaluationReviewNum }）
							</button> --%>
							<button type="button" class="btn btn-default" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectTracing.action');">
								<span class="glyphicon glyphicon-plus text-muted"></span> 任务
							</button>
							<button type="button" class="btn btn-default" onclick="goSaveOrUpdateFeedback('${project.id}','');">
								<span class="glyphicon glyphicon-comment text-muted"></span> 反馈
							</button>
						</div>
					</div>
					<br>
					<div class="row" id="projectScheduleId">
						<div class="col-sm-12 col-md-12 col-lg-12">
							<div class="pull-right">&nbsp;${project.projectSchedule}%</div>
							<div class="progress progress-sm">
								<div class="progress-bar bg-color-greenLight" style="width: ${project.projectSchedule}%;"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="jarviswidget">
								<header>
									<span class="widget-icon"> <i class="fa fa-pencil"></i>
									</span>
									<h2>项目描述</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox"></div>
									<div class="widget-body no-padding">
										<textarea name="ckeditor"> ${project.note }</textarea>
									</div>
								</div>
							</div>
						</article>
					</div>
					<div class="jarviswidget">
						<header>
							<h2>&nbsp;</h2>
							<ul class="nav nav-tabs pull-right in" id="myTab">
								<li class="active"><a data-toggle="tab" href="#d1"><span class="hidden-mobile hidden-tablet">任务</span></a></li>
								<li><a data-toggle="tab" href="#d2"><span class="hidden-mobile hidden-tablet">反馈</span></a></li>
							</ul>
						</header>
						<!-- widget div-->
						<div>
							<div class="widget-body no-padding">
								<div class="tab-content">
									<div class="tab-pane fade in active" id="d1">
										<div class="table-responsive">
											<table id="vixTaskTableId" class="table table-striped table-bordered table-hover" width="100%">
												<thead>
													<tr>
														<th width="10%" data-class="expand">编号</th>
														<th width="55%" data-class="expand">任务标题</th>
														<th width="25%" data-class="expand">创建时间</th>
														<th width="10%">操作</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
									<div class="tab-pane fade in" id="d2">
										<div class="table-responsive">
											<table id="feedbackTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-5">
					<div class="row">
						<div class="col-sm-12">
							<h3>标签</h3>
							<div class="row">
								<div class="col-sm-4 col-md-4 col-lg-4">
									<a href="javascript:void(0);" class="btn btn-block bg-color-red txt-color-white"><i class="fa fa-repeat fa-4x"></i><br>优化待定</a>
								</div>
								<div class="col-sm-4 col-md-4 col-lg-4">
									<a href="javascript:void(0);" class="btn btn-block bg-color-red txt-color-white"><i class="fa fa-star fa-4x"></i><br>重 要</a>
								</div>
								<div class="col-sm-4 col-md-4 col-lg-4">
									<a href="javascript:void(0);" class="btn btn-block bg-color-red txt-color-white"><i class="fa fa-bookmark fa-4x"></i><br>设置标签</a>
								</div>
							</div>
						</div>
					</div>
					<div class="row" id="taskemployeeid">
						<form id="taskEmployeeForm" class="form-horizontal" style="padding: 20px 15px;" action="">
							<input type="hidden" id="employeeIds" name="employeeIds" value="" /> <input type="hidden" id="projectId" name="project.id" value="${project.id }" />
							<div class="col-sm-12">
								<h3>分配</h3>
								<div class="row">
									<s:if test="empList.size > 0">
										<s:iterator value="empList" var="entity" status="s">
											<div class="col-sm-3 col-md-3 col-lg-3 text-center app-del" onclick="deleteProjectEmployee('${entity.id }','${project.id }');">
												<span class="glyphicon glyphicon-minus"></span> <a data-toggle="modal" href="#"><s:if test="entity.headImgUrl !=null">
														<img src="${entity.headImgUrl }" class="btn-circle no-padding btn-xl">
													</s:if> <s:else>
														<img src="${nvix}/vixntcommon/base/images/touxiang.png" class="btn-circle no-padding btn-xl">
													</s:else>${entity.name } </a>
											</div>
										</s:iterator>
									</s:if>
									<div class="col-sm-3 col-md-3 col-lg-3 text-center app-del">
										<a data-toggle="modal" id="assignId" onclick="chooseEmployee();" style="cursor: pointer;"><img src="${nvix}/vixntcommon/base/img/add.png" class="btn-circle no-padding btn-xl"></a>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<h3>操作</h3>
							<div class="row">
								<div class="col-sm-6 col-md-6 col-lg-6 text-center">
									<a data-toggle="modal" href="#setTimeId" class="btn btn-default btn-block" onclick="goUpdateProjectEndTime('${project.id}')"><i class="fa fa-clock-o"></i> 设置截止时间</a>
								</div>
								<div class="col-sm-6 col-md-6 col-lg-6">
									<a data-toggle="modal" href="#" class="btn btn-default btn-block" onclick="goAddUploader('${project.id}');"><i class="fa fa-plus-circle"></i> 添加附件</a>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-sm-6 col-md-6 col-lg-6">
									<a href="javascript:void(0);" class="btn btn-default btn-block archivingbtn" onclick="updateFinishProject('${project.id }');"><i class="fa fa-folder-o"></i> 完成</a>
								</div>
								<div class="col-sm-6 col-md-6 col-lg-6">
									<a href="javascript:void(0);" class="btn btn-default btn-block deletebtn" onclick="deleteProjectById('${project.id}');"><i class="fa fa-trash-o"></i> 删除</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	pageSetUp();

	var vixTaskColumns = [ {
		"data" : function(data) {
			return "";
		}
	}, {
		"data" : function(data) {
			return data.questName;
		}
	}, {
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
	}, {
		"data" : function(data) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateTask('','" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteTaskById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			//return update + "&nbsp;&nbsp;" + del;
			return del;
		}
	} ];
	var vixTaskTable = initDataTable("vixTaskTableId", "${nvix}/nvixnt/nvixProjectAction!goAllProjectTaskList.action?parentId=${project.id}", vixTaskColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});

	var feedbackColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "反馈内容",
	"width" : "40%",
	"data" : function(data) {
		return data.executionFeedback;
	}
	}, {
	"title" : "反馈人",
	"width" : "20%",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"title" : "反馈时间",
	"width" : "20%",
	"data" : function(data) {
		return data.feedbackTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateFeedback('','" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteExecutionFeedbackById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];
	var feedbackTable = initDataTable("feedbackTableId", "${nvix}/nvixnt/nvixProjectAction!goProjectExecutionFeedbackList.action?parentId=${project.id}", feedbackColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	//新增任务
	function goSaveOrUpdateTask(parentId, id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateTask.action?parentId=' + parentId + "&id=" + id, "taskForm", "新增任务", 900, 650, vixTaskTable);
	};
	//新增反馈
	function goSaveOrUpdateFeedback(id, executionFeedbackId) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateFeedback.action?id=' + id + "&executionFeedbackId=" + executionFeedbackId, "executionFeedbackForm", "新增反馈", 750, 350, feedbackTable, null, function() {
			$.ajax({
			url : '${nvix}/nvixnt/nvixProjectAction!goProjectSchedule.action?id=${project.id }',
			cache : false,
			success : function(html) {
				$("#projectScheduleId").html(html);
			}
			});
		});
	};
	//设置截止时间
	function goUpdateProjectEndTime(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixProjectAction!goUpdateProjectEndTime.action?id=' + id, "projectEndTimeForm", "设置截止时间", 375, 200);
	};
	//添加项目附件
	function goAddUploader(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixProjectAction!goAddUploader.action?id=' + id, "uploaderForm", "添加附件", 425, 215);
	};
	$(document).ready(function() {
		CKEDITOR.replace('ckeditor', {
		height : '250px',
		toolbar : 'Basic',
		startupFocus : true
		});
	});
	//删除项目
	function deleteProjectById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!deleteProjectById.action?id=' + id, '是否删除项目?')
	}
	//完成项目
	function updateFinishProject(id) {
		updateEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!updateFinishProject.action?id=' + id, '确定该项目已完成了吗?');
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
			updateProjectEmployee();
		});
	};
	function updateProjectEmployee() {
		$("#taskEmployeeForm").ajaxSubmit({
		type : "post",
		url : "${nvix}/nvixnt/nvixProjectAction!updateProjectEmployee.action",
		dataType : "text",
		success : function(html) {
			$("#taskemployeeid").html(html);
		}
		});
	};
	//删除执行人
	function deleteProjectEmployee(id, projectId) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!deleteProjectEmployeeById.action?id=' + id + "&projectId=" + projectId, '是否删除执行人?', null, null, function() {
			$.ajax({
			url : '${nvix}/nvixnt/nvixProjectAction!goShowProjectEmployee.action?id=${project.id }',
			cache : false,
			success : function(html) {
				$("#taskemployeeid").html(html);
			}
			});
		});
	};
	//删除反馈
	function deleteExecutionFeedbackById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!deleteExecutionFeedbackById.action?id=' + id, '是否删除项目反馈?', feedbackTable);
	};
	//删除任务
	function deleteTaskById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!deleteTaskById.action?id=' + id, '是否删除任务?', vixTaskTable);
	};
	$('.app-del').hover(function() {
		$(this).children('span').css('display', 'block');
	}, function() {
		$(this).children('span').css('display', 'none');
	});
</script>