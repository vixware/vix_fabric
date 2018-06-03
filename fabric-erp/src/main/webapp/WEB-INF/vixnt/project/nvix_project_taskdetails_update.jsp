<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<s:if test="source =='task'">
					<i class="fa-fw fa fa-tasks"></i>任务与计划 <span>> 项目任务管理</span><span>> 任务详情</span>
				</s:if>
				<s:else>
					<i class="fa fa-puzzle-piece fa-fw "></i>项目管理 <span>> 项目跟踪管理</span><span>> 任务详情</span>
				</s:else>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<c:if test="${source == 'task'}">
					<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/taskAndPlanAction!goProjectTask.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
				<c:if test="${source == 'project'}">
					<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectTracing.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
					<header>
						<h2>${vixTask.questName }</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div class="col-sm-12 col-md-12 col-lg-7">
								<br>
								<div class="row">
									<div class="col-sm-12 col-md-12 col-lg-12">
										<%-- <button type="button" class="btn btn-default" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goShowTaskEmployeeList.action?id=${vixTask.id }&source=task');">
											<span class="glyphicon glyphicon-list text-muted"></span>（${employeeNum }）
										</button> --%>
										<button type="button" class="btn btn-default" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateTaskAttachment.action?id=${vixTask.id }&source=${source}');">
											<span class="glyphicon glyphicon-paperclip text-muted"></span> （${uploaderNum }）
										</button>
										<button type="button" class="btn btn-default" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateTaskDiscuss.action?id=${vixTask.id }&source=${source}');">
											<span class="glyphicon glyphicon-comment text-muted"></span> （${evaluationReviewNum }）
										</button>
										<button type="button" class="btn btn-default" onclick="goSaveOrUpdateExecutionFeedback('${vixTask.id}','');">
											<span class="glyphicon glyphicon-comment text-muted"></span> 反馈
										</button>
										<button type="button" class="btn btn-default" onclick="goSaveOrUpdate('','${vixTask.id}','新增任务');">
											<span class="glyphicon glyphicon-plus text-muted"></span> 新增子任务
										</button>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-sm-12 col-md-12 col-lg-12">
										任务计划开始时间:<font color="red">${vixTask.taskStartTimeTimeStr}</font>
									</div>
								</div>
								<div class="row" id="taskExecutionfeedbackContentId">
									<div class="col-sm-12 col-md-12 col-lg-12">
										<div class="pull-right">&nbsp;${vixTask.taskSchedule}%</div>
										<div class="progress progress-sm">
											<div class="progress-bar" style="width: ${vixTask.taskSchedule}%;"></div>
										</div>
									</div>
								</div>
								<div class="row">
									<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-fullscreenbutton="false" data-widget-sortable="false">
											<header>
												<span class="widget-icon"> <i class="fa fa-pencil"></i>
												</span>
												<h2>任务详情</h2>
											</header>
											<div>
												<div class="jarviswidget-editbox"></div>
												<div class="widget-body no-padding">
													<textarea name="ckeditor"> ${vixTask.taskDescription }</textarea>
												</div>
											</div>
										</div>
									</article>
								</div>
								</form>
								<div class="jarviswidget" id="" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
									<header>
										<h2>&nbsp;</h2>
										<ul class="nav nav-tabs pull-right in" id="myTab">
											<li class="active"><a data-toggle="tab" href="#d1"><span class="hidden-mobile hidden-tablet">评论</span></a></li>
											<li><a data-toggle="tab" href="#d2"><span class="hidden-mobile hidden-tablet">任务反馈</span></a></li>
											<li><a data-toggle="tab" href="#d3"><span class="hidden-mobile hidden-tablet">子任务列表</span></a></li>
										</ul>
									</header>
									<div>
										<div class="widget-body no-padding">
											<div class="tab-content">
												<div class="tab-pane fade in active" id="d1">
													<div class="jarviswidget-editbox"></div>
													<div>
														<div class="jarviswidget-editbox">
															<div>
																<label>Title:</label> <input type="text" />
															</div>
														</div>
														<div class="widget-body widget-hide-overflow no-padding-bottom">
															<div id="chat-container">
																<span class="chat-list-open-close"><i class="fa fa-user"></i><b>!</b></span>
																<div class="chat-list-body custom-scroll">
																	<ul id="chat-users">
																		<s:if test="employeeList.size > 0">
																			<s:iterator value="employeeList" var="entity" status="s">
																				<li><a href="javascript:void(0);"><img src="${entity.headImgUrl }" alt="">${entity.name } <span class="badge badge-inverse">0</span><span class="state"><i class="fa fa-circle txt-color-green pull-right"></i></span></a></li>
																			</s:iterator>
																		</s:if>
																	</ul>
																</div>
																<div class="chat-list-footer">
																	<div class="control-group">
																		<form class="smart-form">
																			<section>
																				<label class="input"> <input type="text" id="filter-chat-list" placeholder="Filter">
																				</label>
																			</section>
																		</form>
																	</div>
																</div>
															</div>
															<!-- CHAT BODY -->
															<div id="evaluationReviewId" class="chat-body custom-scroll">
																<ul>
																	<s:if test="evaluationReviewList.size > 0">
																		<s:iterator value="evaluationReviewList" var="entity" status="s">
																			<li class="message"><img src="${entity.employee.headImgUrl }" alt="" height="50px" width="50px">
																				<div class="message-text">
																					<time>
																						<s:date name="%{entity.evaluationTime}" format="yyyy-MM-dd HH:mm:ss" />
																					</time>
																					<a href="javascript:void(0);" class="username">${entity.employee.name }</a> ${entity.evaluationContent }
																				</div></li>
																		</s:iterator>
																	</s:if>
																</ul>
															</div>
															<div class="chat-footer">
																<form id="editEvaluationReviewForm" action="" method="post">
																	<input type="hidden" id="vixTaskId" name="vixTask.id" value="${vixTask.id }" />
																	<div class="textarea-div">
																		<div class="typearea">
																			<textarea id="evaluationContentId" name="evaluationReview.evaluationContent" class="custom-scroll" onkeydown="return submitkeyclick(this,event)"></textarea>
																		</div>
																	</div>
																</form>
																<span class="textarea-controls">
																	<button class="btn btn-sm btn-primary pull-right" onclick="saveOrUpdateEvaluationReview()">发送</button> <span class="pull-right smart-form" style="margin-top: 3px; margin-right: 10px;"> <label class="checkbox pull-right"> <input type="checkbox" name="subscription" id="subscription" checked="checked"> <i></i>按
																			<strong> ENTER </strong> 键发送消息
																	</label>
																</span>
																</span>
															</div>
														</div>
													</div>
												</div>
												<div class="tab-pane fade in" id="d2">
													<div class="row">
														<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
															<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
																<header>
																	<span class="widget-icon"> <i class="fa fa-table"></i>
																	</span>
																	<h2>反馈列表</h2>
																</header>
																<div style="overflow: hidden; position: relative; padding: 0;">
																	<div class="jarviswidget-editbox"></div>
																	<div class="widget-body" style="padding: 8px;">
																		<div class="tab-content">
																			<div class="tab-pane active no-padding" id="hr">
																				<table id="executionFeedbackTableId" class="table table-striped table-bordered table-hover" width="100%">
																					<thead>
																						<tr>
																							<th data-class="expand" width="5%">编号</th>
																							<th data-class="expand" width="55%">内容</th>
																							<th data-class="expand" width="20%">时间</th>
																							<th width="20%">操作</th>
																						</tr>
																					</thead>
																				</table>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</article>
													</div>
												</div>
												<div class="tab-pane fade in" id="d3">
													<div class="row">
														<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
															<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
																<header>
																	<span class="widget-icon"> <i class="fa fa-table"></i>
																	</span>
																	<h2>子任务列表</h2>
																</header>
																<div style="overflow: hidden; position: relative; padding: 0;">
																	<div class="jarviswidget-editbox"></div>
																	<div class="widget-body" style="padding: 8px;">
																		<div class="tab-content">
																			<div class="tab-pane active no-padding" id="hr">
																				<div class="table-responsive">
																					<table id="vixTaskTableId" class="table table-striped table-bordered table-hover" width="100%">
																						<thead>
																							<tr>
																								<th data-class="expand">编号</th>
																								<th data-class="expand">任务标题</th>
																								<th data-class="expand">创建时间</th>
																								<th>操作</th>
																							</tr>
																						</thead>
																					</table>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</article>
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
										<h3>任务分组</h3>
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
										<input type="hidden" id="employeeIds" name="employeeIds" value="" /> <input type="hidden" id="vixTaskId" name="vixTask.id" value="${vixTask.id }" />
										<div class="col-sm-12">
											<h3>分配</h3>
											<div class="row">
												<s:if test="empList.size > 0">
													<s:iterator value="empList" var="entity" status="s">
														<div class="col-sm-3 col-md-3 col-lg-3 text-center app-del" onclick="deleteTaskEmployee('${entity.id }','${vixTask.id }');">
															<span class="glyphicon glyphicon-minus"></span> <a data-toggle="modal" href="#"><img src="${entity.headImgUrl }" class="btn-circle no-padding btn-xl"></a>
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
												<a data-toggle="modal" href="#" class="btn btn-default btn-block" onclick="goUpdateEndTime('${vixTask.id}');"><i class="fa fa-clock-o"></i> 设置截止时间</a>
											</div>
											<div class="col-sm-6 col-md-6 col-lg-6">
												<a data-toggle="modal" href="#" class="btn btn-default btn-block" onclick="goAddUploader('${vixTask.id}');"><i class="fa fa-plus-circle"></i> 添加附件</a>
											</div>
										</div>
										<br>
										<div class="row">
											<div class="col-sm-6 col-md-6 col-lg-6">
												<a href="javascript:void(0);" class="btn btn-default btn-block archivingbtn" onclick="updateFinishTask('${vixTask.id }');"><i class="fa fa-folder-o"></i> 结束任务</a>
											</div>
											<div class="col-sm-6 col-md-6 col-lg-6">
												<a href="javascript:void(0);" class="btn btn-default btn-block deletebtn" onclick="deleteById('${vixTask.id}');"><i class="fa fa-trash-o"></i> 删除任务</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>

<script type="text/javascript">
	pageSetUp();
	$(document).ready(function() {
		//富文本编辑器
		CKEDITOR.replace('ckeditor', {
		height : '200px',
		startupFocus : true
		});
	});
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
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','','修改任务');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
	} ];
	var vixTaskTable = initDataTable("vixTaskTableId", "${nvix}/nvixnt/nvixProjectAction!goSubTaskList.action?id=${vixTask.id}", vixTaskColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});

	var productCategoryColumns = [ {
		"data" : function(data) {
			return "";
		}
	}, {
		"data" : function(data) {
			return data.executionFeedback;
		}
	}, {
		"data" : function(data) {
			return data.feedbackTimeTimeStr;
		}
	}, {
		"data" : function(data) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateExecutionFeedback('','" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteExecutionFeedbackById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
	} ];
	var executionFeedbackTable = initDataTable("executionFeedbackTableId", "${nvix}/nvixnt/nvixProjectAction!goExecutionFeedbackList.action?id=${vixTask.id}", productCategoryColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	//新增任务
	function goSaveOrUpdate(id, parentId, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateTask.action?id=' + id + "&parentId=" + parentId + "&treeType=T", "taskForm", title, 900, 650, vixTaskTable);
	};
	//新增反馈
	/* function goSaveOrUpdateExecutionFeedback(id, executionFeedbackId) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateExecutionFeedback.action?id=' + id + "&executionFeedbackId=" + executionFeedbackId, "executionFeedbackForm", "新增反馈", 750, 250, executionFeedbackTable, null, function() {
			$.ajax({
			url : '${nvix}/nvixnt/nvixProjectAction!goTaskExecutionfeedbackContent.action?id=${vixTask.id }',
			cache : false,
			success : function(html) {
				$("#taskExecutionfeedbackContentId").html(html);
			}
			});
		});
	}; */
	function goSaveOrUpdateExecutionFeedback(id, startFeedbackTime, endFeedbackTime) {
		$.ajax({
		url : '${nvix}/nvixnt/taskAndPlanAction!isFeedback.action?id=' + id,
		cache : false,
		success : function(data) {
			if (data == 1) {
				openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateExecutionFeedback.action?id=' + id, "executionFeedbackForm", "新增反馈", 750, 350, executionFeedbackTable, null, function() {
					$.ajax({
					url : '${nvix}/nvixnt/taskAndPlanAction!goTaskExecutionfeedbackContent.action?id=${vixTask.id }',
					cache : false,
					success : function(html) {
						$("#taskExecutionfeedbackContentId").html(html);
					}
					});
				});
			} else {
				if (startFeedbackTime != null && startFeedbackTime != '' && endFeedbackTime != null && endFeedbackTime != '') {
					layer.alert('该任务已经过了反馈时间,不能反馈!' + '反馈时间:' + startFeedbackTime + '-' + endFeedbackTime, {
						icon : 5
					});
				} else {
					layer.alert('该任务已经过了反馈时间,不能反馈!', {
						icon : 5
					});
				}
			}
		}
		});
	};
	//删除反馈
	function deleteExecutionFeedbackById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteExecutionFeedbackById.action?id=' + id, '是否删除任务反馈?', executionFeedbackTable);
	};
	//设置截止时间
	function goUpdateEndTime(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goUpdateEndTime.action?id=' + id, "endTimeForm", "设置截止时间", 375, 200);
	};
	function saveOrUpdateEvaluationReview() {
		var evaluationContent = $("#evaluationContentId").val();

		// 验证
		if (!evaluationContent || $.trim(evaluationContent).length <= 0) {
			alert("请输入评论信息!");
			return;
		}
		if (evaluationContent.length > 50) {
			alert("评论,请不要超过50个字！");
			return;
		}

		$("#editEvaluationReviewForm").ajaxSubmit({
		type : "post",
		url : "${nvix}/nvixnt/taskAndPlanAction!saveOrUpdateEvaluationReview.action",
		dataType : "text",
		success : function(html) {
			$("#evaluationReviewId").html(html);
			$("#evaluationContentId").val('');
		}
		});
	};

	//回车发送消息
	function submitkeyclick(obj, evt) {
		if ($('#subscription').is(':checked')) {
			evt = (evt) ? evt : ((window.event) ? window.event : "")
			keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
			if (keyCode == 13) {
				saveOrUpdateEvaluationReview();
			}
		}
	};

	//添加执行人
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goEmployeeChoose.action', 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#employeeIds').val(emp[0]);
				$('#employeeNames').val(emp[1]);
			} else {
				layer.alert("请选择人员!");
			}
			updateVixTaskEmployee();
		});
	};
	function updateVixTaskEmployee() {
		$("#taskEmployeeForm").ajaxSubmit({
		type : "post",
		url : "${nvix}/nvixnt/taskAndPlanAction!updateVixTaskEmployee.action",
		dataType : "text",
		success : function(html) {
			$("#taskemployeeid").html(html);
		}
		});
	};
	//删除任务
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteById.action?id=' + id, '是否删除任务?', vixTaskTable);
	};

	//添加附件
	function goAddUploader(parentId) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goAddUploader.action?parentId=' + parentId, "uploaderForm", "添加附件", 550, 200);
	};

	//结束任务
	function updateFinishTask(id) {
		updateEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!updateFinishTask.action?id=' + id, '确定结束任务吗?', vixTaskTable);
	};

	//删除执行人
	function deleteTaskEmployee(id, taskId) {
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteVixTaskEmployeeById.action?id=' + id + "&taskId=" + taskId, '是否删除执行人?', null, null, function() {
			$.ajax({
			url : '${nvix}/nvixnt/taskAndPlanAction!goShowTaskEmployee.action?id=${vixTask.id }',
			cache : false,
			success : function(html) {
				$("#taskemployeeid").html(html);
			}
			});
		});
	};

	$('.app-del').hover(function() {
		$(this).children('span').css('display', 'block');
	}, function() {
		$(this).children('span').css('display', 'none');
	});
</script>