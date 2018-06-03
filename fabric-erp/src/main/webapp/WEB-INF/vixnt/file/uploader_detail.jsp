<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 文件管理 <span>&gt; 我的文件</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixFileAction!goFile.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="well padding-10">
				<div class="row">
					<div class="col-md-12 padding-left-0">
						<h3 class="margin-top-0">
							<a href="javascript:void(0);" onclick="loadContent('log_logComment','${nvix}/nvixnt/dailyJournalAction!goList.action?source=${source }');"> ${communication.title} </a>
						</h3>
						<p class="note margin-bottom-10">
							<a href="javascript:void(0);"><i class="fa fa-user"></i>&nbsp;${communication.employee.name}&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-calendar"></i> ${communication.startTimeTimeStr} </a>
						</p>
						<p class="description margin-bottom-10">&nbsp;留言内容:${communication.logContent}</p>
						<div class="chat-body no-padding profile-message">
							<ul>
								<s:if test="evaluationReviewsList.size > 0">
									<s:iterator value="evaluationReviewsList" var="entity" status="s">
										<li class="message message-reply"><img src="${entity.employee.headImgUrl}"> <span class="message-text"> <a href="javascript:void(0);" class="username">${entity.employee.name}&nbsp;&nbsp;<i class="fa fa-calendar"></i>&nbsp; <fmt:formatDate value="${entity.evaluationTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;
											</a>&nbsp; ${entity.evaluationContent} &nbsp;<a href="javascript:void(0);" style="color: #E63F00;" onclick="deleteById('${entity.id }');"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;删除</a>
										</span></li>
									</s:iterator>
								</s:if>
								<div class="chat-footer">
									<form id="logCommentForm" action="" method="post">
										<input type="hidden" id="workLogId" name="communication.id" value="${communication.id}" /> <input type="hidden" id="evaluationReviewId" name="evaluationReview.id" value="${evaluationReview.id }" />
										<div class="textarea-div">
											<div class="typearea">
												<textarea id="textarea-expand" name="evaluationReview.evaluationContent" class="custom-scroll" onkeydown="return submitkeyclick(this,event)"></textarea>
											</div>
										</div>
									</form>
									<span class="textarea-controls">
										<button class="btn btn-sm btn-primary pull-right" onclick="saveOrUpdateLogComment()">
											<i class="glyphicon glyphicon-edit"></i>&nbsp;评论
										</button>
									</span>
								</div>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function saveOrUpdateLogComment() {
		var evaluationContent = $("#textarea-expand").val();

		// 验证
		if (!evaluationContent || $.trim(evaluationContent).length <= 0) {
			alert("请输入评论信息!");
			return;
		}
		if (evaluationContent.length > 50) {
			alert("评论,请不要超过50个字！");
			return;
		}

		$("#logCommentForm").ajaxSubmit({
		type : "post",
		url : "${nvix}/nvixnt/dailyJournalAction!saveOrUpdateLogComment.action",
		dataType : "text",
		success : function(id) {
			loadContent('log_logComment', '${nvix}/nvixnt/dailyJournalAction!goViewLog.action?id=' + id);
		}
		});
	};

	/**
	 * 回车发送消息
	 */
	function submitkeyclick(obj, evt) {
		evt = (evt) ? evt : ((window.event) ? window.event : "");
		keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
		if (keyCode == 13) {
			saveOrUpdateLogComment();
		}
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/dailyJournalAction!deleteEvaluationReviewById.action?id=' + id, '是否删除该评论?', null, null, function() {
			loadContent('log_logComment', '${nvix}/nvixnt/dailyJournalAction!goViewLog.action?id=${communication.id}');
		});
	};
</script>