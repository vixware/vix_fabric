<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="content">
	<div class="row hidden-mobile">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-tasks fa-fw"></i> <a data-toggle="modal" href="#" style="cursor: pointer;" onclick="loadContent('','${nvix}/nvixnt/taskAndPlanAction!goList.action');">任务与计划 </a> <span>> <a data-toggle="modal" href="#" style="cursor: pointer;"
					onclick="loadContent('mid_projectstask','${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=${vixTask.id }');">任务详情 </a>
				</span> <span>> 沟通 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=${vixTask.id }&source=${source}');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<!-- row -->
		<div class="row">
			<article class="col-sm-12 col-md-12 col-lg-12">
				<!-- new widget -->
				<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
					<header>
						<h2>任务沟通</h2>
					</header>
					<!-- widget div-->
					<div>
						<div class="widget-body no-padding">
							<div class="col-sm-12 col-md-12 col-lg-12">
								<br>
								<div class="row">
									<div class="col-sm-12 col-md-12 col-lg-12  task-detail-title">
										<span class="pull-right"><span class="badge"><i class="fa fa-user"></i>${employeeNum }</span></span> <span class="text-danger"></span><span class=" text-muted">${vixTask.questName }</span>
									</div>
								</div>
								<ul class="media-list task-detail-list">
									<s:if test="evaluationReviewList.size > 0">
										<s:iterator value="evaluationReviewList" var="entity" status="s">
											<li class="media clearfix"><a class="pull-left" href="javascript:void(0);"><img src="${entity.employee.headImgUrl }" class="btn-circle no-padding btn-xl"></a>
												<div class="media-body">
													<h4 class="media-heading">
														<span class="txt-color-blue">${entity.employee.name }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small class="text-muted">${fn:substring(entity.evaluationTime, 0,19)}</small>
													</h4>
													<p>${entity.evaluationContent }</p>
												</div></li>
										</s:iterator>
									</s:if>
								</ul>
								<br>
								<div class="chat-box">
									<div class="jarviswidget-editbox"></div>
									<div>
										<div class="jarviswidget-editbox">
											<div>
												<label>Title:</label> <input type="text">
											</div>
										</div>
										<div class="widget-body widget-hide-overflow no-padding-bottom">
											<%-- <div id="chat-container">
												<span class="chat-list-open-close"><i class="fa fa-user"></i><b>!</b></span>
												<div class="chat-list-body custom-scroll">
													<ul id="chat-users">
														<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/5.png" alt="">Robin Berry <span class="badge badge-inverse">23</span><span class="state"><i class="fa fa-circle txt-color-green pull-right"></i></span></a></li>
														<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Mark Zeukartech <span class="state"><i class="last-online pull-right">2hrs</i></span></a></li>
														<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Belmain Dolson <span class="state"><i class="last-online pull-right">45m</i></span></a></li>
														<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Galvitch Drewbery <span class="state"><i class="fa fa-circle txt-color-green pull-right"></i></span></a></li>
														<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Sadi Orlaf <span class="state"><i class="fa fa-circle txt-color-green pull-right"></i></span></a></li>
														<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Markus <span class="state"><i class="last-online pull-right">2m</i></span></a></li>
														<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/sunny.png" alt="">Sunny <span class="state"><i class="last-online pull-right">2m</i></span></a></li>
														<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Denmark <span class="state"><i class="last-online pull-right">2m</i></span></a></li>
													</ul>
												</div>
												<div class="chat-list-footer">
													<div class="control-group">
														<form class="smart-form">
															<section>
																<label class="input"> <input type="text" id="filter-chat-list" placeholder="Filter"></label>
															</section>
														</form>
													</div>
												</div>
											</div> --%>
											<!-- CHAT BODY -->
											<div id="evaluationReviewId" class="chat-body custom-scroll">
												<ul>
													<s:if test="evaluationReviewList.size > 0">
														<s:iterator value="evaluationReviewList" var="entity" status="s">
															<li class="message"><img src="${entity.employee.headImgUrl }" alt="" height="50px" width="50px">
																<div class="message-text">
																	<time> ${fn:substring(entity.evaluationTime, 0,19)} </time>
																	<a href="javascript:void(0);" class="username">${entity.employee.name }</a> ${entity.evaluationContent } <i class="fa fa-smile-o txt-color-orange"></i>
																</div></li>
														</s:iterator>
													</s:if>
												</ul>
											</div>
											<!-- CHAT FOOTER -->
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
													<button class="btn btn-sm btn-primary pull-right" onclick="saveOrUpdateEvaluationReview()">发送</button> <span class="pull-right smart-form" style="margin-top: 3px; margin-right: 10px;"> <label class="checkbox pull-right"> <input type="checkbox" name="subscription" id="subscription"> <i></i>按 <strong>
																ENTER </strong> 键发送消息
													</label>
												</span>
												</span>
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
	function saveOrUpdateEvaluationReview() {
		var evaluationContent = $("#evaluationContentId").val();
		// 验证
		if (!evaluationContent || $.trim(evaluationContent).length <= 0) {
			alert("请输入回复信息!");
			return;
		}
		if (evaluationContent.length > 150) {
			alert("留言,请不要超过150个字！");
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
	function submitkeyclick(obj, evt) {
		if ($('#subscription').is(':checked')) {
			evt = (evt) ? evt : ((window.event) ? window.event : "")
			keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
			if (keyCode == 13) {
				saveOrUpdateEvaluationReview();
			}
		}
	};
</script>