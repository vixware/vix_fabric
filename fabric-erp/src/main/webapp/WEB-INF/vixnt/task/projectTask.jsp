<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${nvix}/vixntcommon/base/css/task.css" >
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> 任务与计划 <span>> 任务分组 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="widget-grid" class="">
		<div class="row">
			<div class="col-sm-3 col-md-3 col-lg-3">
				<div class="well" style="min-height:800px;">
					<!-- <button class="close" data-dismiss="alert">×</button> -->
					<input id="projectId" type="hidden" value="${project.id}">
					<input id="parentId" type="hidden" value="${vixTask.id}">
					<h4 class="semi-bold text-center">项目列表</h4>
					<hr/>
					<div id="projectTaskPrecent">
						<c:if test="${projectList != null && fn:length(projectList) > 0}">
							<c:forEach items="${projectList}" var="project" varStatus="s">
								<div class="task-list">
									<h6>${project.projectName}</h6>
									<p class="txt-color-gray">${project.note}</p>
									<c:forEach items="${projectTaskMap}" var="map"> 
										<c:if test="${map.key == project.id}">
											<c:forEach items="${map.value}" var="task" varStatus="ss">
												<h6 class="task <c:if test="${s.index == 0 && ss.index == 0}">select</c:if>" style="cursor: pointer;" id="${task.id}">
													${task.questName}<i class="fa fa-check"></i>
												</h6>
												<%-- <span class="txt-color-blue" <c:if test="${s.index != 0 && ss.index != 0}">style="display:none;"</c:if> ><i class="fa fa-lg fa-check"></i></span> --%>
											</c:forEach>
											<%-- 未开始
											<div class="progress progress-xs">
												<div class="progress-bar bg-color-orange" style="width: ${fn:split(map.value,':')[0]}"></div>
											</div>
											进行中
											<div class="progress progress-xs">
												<div class="progress-bar bg-color-blue1" style="width: ${fn:split(map.value,':')[1]}"></div>
											</div>
											已完成
											<div class="progress progress-xs">
												<div class="progress-bar bg-color-green1" style="width: ${fn:split(map.value,':')[2]}"></div>
											</div>
											超时完成
											<div class="progress progress-xs">
												<div class="progress-bar bg-color-red1" style="width: ${fn:split(map.value,':')[3]}"></div>
											</div> --%>
										</c:if>
									</c:forEach>
									<%-- <span class="txt-color-blue chooseRight" <c:if test="${s.index != 0}">style="display:none;"</c:if> ><i class="fa fa-lg fa-check"></i></span> --%>
								</div>
								<hr/>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
			<div class="col-sm-9 col-md-9 col-lg-9" id="projectTask">
				<div class="col-sm-3 col-md-3 col-lg-3">
					<div class="content-box">
						<div class="content-box-header">
							未开始●${fn:length(noStartVixTaskList)}
						</div>
						<c:if test="${noStartVixTaskList != null && fn:length(noStartVixTaskList) > 0}">
							<c:forEach items="${noStartVixTaskList}" var="task">
								<div class="well">
									<form class="smart-form">
										<section>
											<label class="checkbox"> 
												<!-- <input type="checkbox" name="checkbox"> <i></i> -->
												<div class="chat-body no-padding profile-message">
													<ul>
														<li class="message" style="margin:0;">
															<img src="${nvix}/vixntcommon/base/images/touxiang.png" class="" alt="sunny" width="36px;"> 
															<span class="message-text" onclick="goSaveOrUpdate('${task.id}','新增任务');"> 
																${task.questName}
															</span>
															<%-- <a href="javascript:void(0);" class="btn bg-color-orange txt-color-white"><span class="fa fa-calendar"></span>&nbsp;&nbsp;${task.taskEndTimeStr}</a> --%>
															<a href="javascript:void(0);" onclick="goProjectTaskDetails('${task.id}');" class="btn bg-color-blue txt-color-white"><span class="fa fa-eye">&nbsp;&nbsp;查看</span></a>
															<a href="javascript:void(0);" onclick="goSaveOrUpdateExecutionFeedback('${task.id}','${task.startFeedbackTime}','${task.endFeedbackTime}');" class="btn bg-color-blue txt-color-white"><span class="fa fa-comment">&nbsp;&nbsp;反馈</span></a>
															<a href="javascript:void(0);" onclick="deleteById('${task.id}');" class="btn bg-color-red txt-color-white"><span class="fa fa-times">&nbsp;&nbsp;删除</span></a>
															<a href="javascript:void(0);" onclick="goOpenTask('${task.id}');" class="btn bg-color-green txt-color-white"><span class="fa fa-check-circle-o">&nbsp;&nbsp;开始任务</span></a>
														</li>	
													</ul>
												</div>
											</label>
										</section>
									</form>
								</div>
							</c:forEach>
						</c:if>
						<div style="height:45px;"></div>
						<p class="addTask"><a href="javascript:void(0);" onclick="goSaveOrUpdate('','新增任务');" ><img src="${nvix}/vixntcommon/base/images/addition.png">&nbsp;添加任务</a></p>
					</div>
				</div>
				<div class="col-sm-3 col-md-3 col-lg-3">
					 <div class="content-box">
						<div class="content-box-header">
							进行中●${fn:length(progressVixTaskList)}
						</div>
						<c:if test="${progressVixTaskList != null && fn:length(progressVixTaskList) > 0}">
							<c:forEach items="${progressVixTaskList}" var="task">
								<div class="alert alert-warning">
									<form class="smart-form">
										<section>
											<label class="checkbox"> 
												<!-- <input type="checkbox" name="checkbox"> <i></i> -->
												<div class="chat-body no-padding profile-message">
													<ul>
														<li class="message" style="margin:0;">
															<img src="${nvix}/vixntcommon/base/images/touxiang.png" class="" alt="sunny" width="36px;"> 
															<span class="message-text"> 
																${task.questName}
															</span>
															<%-- <a href="javascript:void(0);" class="btn bg-color-orange txt-color-white"><span class="fa fa-calendar"></span>&nbsp;&nbsp;${task.taskEndTimeStr}</a> --%>
															<a href="javascript:void(0);" onclick="goProjectTaskDetails('${task.id}');" class="btn bg-color-blue txt-color-white"><span class="fa fa-eye">&nbsp;&nbsp;查看</span></a>
															<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white"><span class="fa fa-list"></span>&nbsp;&nbsp;${task.taskSchedule}%</a>
															<a href="javascript:void(0);" onclick="goSaveOrUpdateExecutionFeedback('${task.id}','${task.startFeedbackTime}','${task.endFeedbackTime}');" class="btn bg-color-blue txt-color-white"><span class="fa fa-comment">&nbsp;&nbsp;反馈</span></a>
														</li>	
													</ul>
												</div>
											</label>
										</section>
									</form>
								</div>	
							</c:forEach>
						</c:if>
					</div>
				</div>
				<div class="col-sm-3 col-md-3 col-lg-3">
					<div class="content-box">
						<div class="content-box-header">
							已完成●${fn:length(completeVixTaskList)}
						</div>
						<c:if test="${completeVixTaskList != null && fn:length(completeVixTaskList) > 0}">
							<c:forEach items="${completeVixTaskList}" var="task">
								<div class="alert alert-succ">
									<form class="smart-form">
										<section>
											<label class="checkbox"> 
												<!-- <input type="checkbox" name="checkbox"> <i></i> -->
												<div class="chat-body no-padding profile-message">
													<ul>
														<li class="message" style="margin:0;">
															<img src="${nvix}/vixntcommon/base/images/touxiang.png" class="" alt="sunny" width="36px;"> 
															<span class="message-text"> 
																${task.questName}
															</span>
															<%-- <a href="javascript:void(0);" class="btn bg-color-orange txt-color-white"><span class="fa fa-calendar"></span>&nbsp;&nbsp;${task.taskEndTimeStr}</a> --%>
															<a href="javascript:void(0);" onclick="goProjectTaskDetails('${task.id}');" class="btn bg-color-blue txt-color-white"><span class="fa fa-eye">&nbsp;&nbsp;查看</span></a>
															<a href="javascript:void(0);" class="btn bg-color-green txt-color-white"><span class="fa fa-list"></span>&nbsp;&nbsp;${task.taskSchedule}%</a>
														</li>	
													</ul>
												</div>
											</label>
										</section>
									</form>
								</div>		
							</c:forEach>
						</c:if>
					</div>
				</div>
				<div class="col-sm-3 col-md-3 col-lg-3" >
					<div class="content-box">
						<div class="content-box-header">
							超时完成●${fn:length(timeoutVixTaskList)}
						</div>
						<c:if test="${timeoutVixTaskList != null && fn:length(timeoutVixTaskList) > 0}">
							<c:forEach items="${timeoutVixTaskList}" var="task">
								<div class="alert alert-danger">
									<form class="smart-form">
										<section>
											<label class="checkbox"> 
												<!-- <input type="checkbox" name="checkbox"> <i></i> -->
												<div class="chat-body no-padding profile-message">
													<ul>
														<li class="message" style="margin:0;">
															<img src="${nvix}/vixntcommon/base/images/touxiang.png" class="" alt="sunny" width="36px;"> 
															<span class="message-text"> 
																${task.questName}
															</span>
															<%-- <a href="javascript:void(0);" class="btn bg-color-orange txt-color-white"><span class="fa fa-calendar"></span>&nbsp;&nbsp;${task.taskEndTimeStr}</a> --%>
															<a href="javascript:void(0);" onclick="goProjectTaskDetails('${task.id}');" class="btn bg-color-blue txt-color-white"><span class="fa fa-eye">&nbsp;&nbsp;查看</span></a>
															<a href="javascript:void(0);" class="btn bg-color-gery txt-color-white"><span class="fa fa-list"></span>&nbsp;&nbsp;${task.taskSchedule}%</a>
															<!-- <a href="javascript:void(0);" class="btn bg-color-gery txt-color-white"><span class="fa fa-comment">反馈</span></a> -->
														</li>	
													</ul>
												</div>
											</label>
										</section>
									</form>
								</div>	
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<script type="text/javascript">
	/* var task = $('.task-list');
	task.click(function(){
		$(this).children(".chooseRight").show();
		$(this).siblings().children(".chooseRight").hide();
	    $(this).addClass('select').siblings().removeClass('select');
	    var id = $(this).attr("id");
	    $("#projectId").val(id);
	    $("#projectTask").load('${nvix}/nvixnt/taskAndPlanAction!loadProjectTask.action?parentId=' + $('#projectId').val());
	}); */
	var task = $('.task-list .task');
	task.click(function(){
		//$(this).children("i").addClass("txt-color-green");
		//$(this).siblings().children("i").removeClass("txt-color-green");
		var n = task.index($(this));
        $('.task-list .task:not(:eq('+n+'))').removeClass('select');
        $(this).addClass('select');
		//$(this).addClass('select').siblings().removeClass('select');
		$("#parentId").val($(this).attr("id"));
		$("#projectTask").load('${nvix}/nvixnt/taskAndPlanAction!loadProjectTask.action?parentId=' + $('#parentId').val());
	});
	
	// 添加任务
	function goSaveOrUpdate(id, title) {
		if ($('#parentId').val() != '') {
			goSaveOrUpdateEntity('${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateTask.action?id=' + id + '&parentId=' + $('#parentId').val() + '&source=task');
		} else {
			layer.alert("请选择项目阶段!");
		}
	};
	//任务详情
	function goProjectTaskDetails(id) {
		loadContent('mid_projectstask', '${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=' + id + "&source=projectTask");
	};
	//新增反馈
	function goSaveOrUpdateExecutionFeedback(id, executionFeedbackId) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateExecutionFeedback.action?id=' + id, "executionFeedbackForm", "反馈", 750, 350, null,null,function(){
			$("#projectTask").load('${nvix}/nvixnt/taskAndPlanAction!loadProjectTask.action?parentId=' + $('#parentId').val());
			//$("#projectTaskPrecent").load('${nvix}/nvixnt/taskAndPlanAction!loadProjectTaskPrecent.action');
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!deleteTaskById.action?id=' + id, '是否删除任务?', null, '删除任务', function(){
			$("#projectTask").load('${nvix}/nvixnt/taskAndPlanAction!loadProjectTask.action?parentId=' + $('#parentId').val());
			//$("#projectTaskPrecent").load('${nvix}/nvixnt/taskAndPlanAction!loadProjectTaskPrecent.action');
		});
	};
	// 开始任务
	function goOpenTask(id){
		$.ajax({
			url : '${nvix}/nvixnt/taskAndPlanAction!goOpenTask.action?id=' + id,
			cache : false,
			success : function(result) {
				var r = result.split(":");
				if(r[0] != '0'){
					$("#projectTask").load('${nvix}/nvixnt/taskAndPlanAction!loadProjectTask.action?parentId=' + $('#parentId').val());
				}else{
					layer.alert(r[1]);
					return ;
				}
			}
		});
	}
</script>