<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 办公 <span>&gt;工作计划详情</span>
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="well padding-10">
				<div class="row">
					<div class="col-md-12 padding-left-0">
						<h3 class="margin-top-0">
							<a href="javascript:void(0);" onclick="loadContent('postil','${nvix}/nvixnt/workPlansAction!goList.action');"> ${projectManagement.proposalTitle} </a>
						</h3>
						<p class="note margin-bottom-10">
							<a href="javascript:void(0);"><i class="fa fa-user"></i>&nbsp;${projectManagement.uploadPersonName}&nbsp;&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-calendar"></i> <fmt:formatDate value="${projectManagement.initiateTime}" type="both" pattern="yyyy-MM-dd" />&nbsp;&nbsp; </a> <a href="javascript:void(0);"> <fmt:formatDate
									value="${projectManagement.initiateTime}" type="both" pattern="HH:mm:ss" />&nbsp;&nbsp;-- <fmt:formatDate value="${projectManagement.overTime}" type="both" pattern="HH:mm:ss" />&nbsp;&nbsp;
							</a>
						</p>
						<p class="description margin-bottom-10">${projectManagement.planContent}</p>
						<div class="chat-body no-padding profile-message">
							<ul>
								<s:if test="evaluationReviewsList.size > 0">
									<s:iterator value="evaluationReviewsList" var="entity" status="s">
										<li class="message message-reply"><img src="${entity.employee.headImgUrl}"> <span class="message-text"> <a href="javascript:void(0);" class="username">${entity.employee.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <i class="fa fa-calendar"></i>&nbsp; <fmt:formatDate value="${entity.evaluationTime}" type="both"
														pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;
											</a> ${entity.evaluationContent}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" style="color: #E63F00;" onclick="deleteById('${entity.id }');"><i class="fa fa-trash-o"></i>删除 </a>
										</span></li>
									</s:iterator>
								</s:if>
								<div class="chat-footer">
									<form id="postilForm" action="" method="post">
										<input type="hidden" id="projectManagementId" name="projectManagement.id" value="${projectManagement.id}" /> <input type="hidden" id="evaluationReviewId" name="evaluationReview.id" value="${evaluationReview.id }" />
										<div class="textarea-div">
											<div class="typearea">
												<textarea id="textarea-expand" name="evaluationReview.evaluationContent" class="custom-scroll" onkeydown="return submitkeyclick(this,event)"></textarea>
											</div>
										</div>
									</form>
									<span class="textarea-controls">
										<button class="btn btn-sm btn-primary pull-right" onclick="saveOrUpdatePostil()">
											<i class="glyphicon glyphicon-edit"></i>&nbsp;&nbsp;批注
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
	function saveOrUpdatePostil() {
		var planContent = $("#textarea-expand").val();

		// 验证
		if (!planContent || $.trim(planContent).length <= 0) {
			alert("请输入评论信息!");
			return;
		}
		if (planContent.length > 50) {
			alert("评论,请不要超过50个字！");
			return;
		}

		$("#postilForm").ajaxSubmit({
		type : "post",
		url : "${nvix}/nvixnt/workPlansAction!saveOrUpdatePostil.action",
		dataType : "text",
		success : function(id) {
			loadContent('postil', '${nvix}/nvixnt/workPlansAction!goViewPlan.action?id=${projectManagement.id}');
		}
		});
	};

	/**
	 * 回车发送消息
	 */
	function submitkeyclick(obj, evt) {
		evt = (evt) ? evt : ((window.event) ? window.event : "")
		keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
		if (keyCode == 13) {
			saveOrUpdatePostil();
		}
	};
</script>
