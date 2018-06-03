<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
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
