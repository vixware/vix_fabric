<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${nvix}/vixntcommon/base/css/task.css" >
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> 任务与计划 <span>> 任务 </span>
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
														<a href="javascript:void(0);" onclick="goShowTaskDetails('${task.id}');" class="btn bg-color-blue txt-color-white"><span class="fa fa-eye">&nbsp;&nbsp;查看</span></a>
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
														<a href="javascript:void(0);" onclick="goShowTaskDetails('${task.id}');" class="btn bg-color-blue txt-color-white"><span class="fa fa-eye">&nbsp;&nbsp;查看</span></a>
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
			<div class="col-sm-3 col-md-3 col-lg-3" >
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
														<a href="javascript:void(0);" onclick="goShowTaskDetails('${task.id}');" class="btn bg-color-blue txt-color-white"><span class="fa fa-eye">&nbsp;&nbsp;查看</span></a>
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
														<a href="javascript:void(0);" onclick="goShowTaskDetails('${task.id}');" class="btn bg-color-blue txt-color-white"><span class="fa fa-eye">&nbsp;&nbsp;查看</span></a>
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
	</section>
</div>
<script type="text/javascript">
	//新增任务
	function goSaveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdate.action?id=' + id, "taskForm", title, 900, 650, null, null, function() {
			loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goTask.action');
		});
	};
	//跳转到任务详情
	function goShowTaskDetails(id) {
		loadContent('mid_projectstask', '${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=' + id + '&source=taskandplan');
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteById.action?id=' + id, '是否删除任务?', null, '删除任务', function(){
			loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goTask.action');
		});
	};
	//新增反馈
	function goSaveOrUpdateExecutionFeedback(id, startFeedbackTime, endFeedbackTime) {
		$.ajax({
		url : '${nvix}/nvixnt/taskAndPlanAction!isFeedback.action?id=' + id,
		cache : false,
		success : function(data) {
			if (data == 1) {
				openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateExecutionFeedback.action?id=' + id, "executionFeedbackForm", "反馈", 750, 350, null,null,function(){
					loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goTask.action');
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
	// 开始任务
	function goOpenTask(id){
		$.ajax({
			url : '${nvix}/nvixnt/taskAndPlanAction!goOpenTask.action?id=' + id,
			cache : false,
			success : function(result) {
				var r = result.split(":");
				if(r[0] != '0'){
					loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goTask.action');
				}else{
					layer.alert(r[1]);
					return ;
				}
			}
		});
	}
</script>