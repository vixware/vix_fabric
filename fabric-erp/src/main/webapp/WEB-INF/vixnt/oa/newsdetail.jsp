<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-desktop"></i> 办公 <span>&gt; 新闻管理</span> <span>&nbsp; &gt; 新闻详情</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<c:if test="${syncTag == 'news'}">
					<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/newsAdministrationAction!goList.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
				<c:if test="${syncTag == 'home'}">
					<button class="btn btn-default" type="button" onclick="loadContent('mid_index','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-9">
			<div class="well padding-10">
				<div class="row">
					<div class="col-md-12 padding-left-0">
						<h3 class="margin-top-0">
							<a href="javascript:void(0);" onclick="loadContent('news','${nvix}/nvixnt/newsAdministrationAction!goList.action');"> ${trends.title} </a>
						</h3>
						<p class="note">
							<i class="fa fa-calendar"></i>
							<fmt:formatDate value="${trends.createTime}" type="both" pattern="yyyy-MM-dd" />
							&nbsp;&nbsp; <i class="fa fa-child"></i> ${trends.uploadPersonName}&nbsp;&nbsp; <i class="fa fa-comments"></i> ${trends.readTimes}条阅读记录&nbsp;&nbsp; <a href="${trends.sourceFromUrl}"><i class="fa fa-comments"></i> 新闻来源：${trends.sourceFromUrl}</a>
						</p>
						<div class="chat-body no-padding profile-message">${trends.content }</div>
						<div class="chat-body no-padding profile-message">
							<ul>
								<li class="message">
									<ul class="list-inline font-xs">
										<li><a href="javascript:void(0);" class="text-info" onclick="goNewsCommentarys('${trends.id}');"><i class="fa fa-comment-o"></i> 评论</a></li>
										<li><a href="javascript:void(0);" class="text-danger">所有评论： ${evaluationReviewNum}</a></li>
										<div id="newsCommentarys"></div>
									</ul> <br> <s:if test="evaluationReviewsList.size > 0">
										<s:iterator value="evaluationReviewsList" var="entity" status="s">
											<li class="message message-reply"><img src="${entity.employee.headImgUrl}"> <span class="message-text"> <a href="javascript:void(0);" class="username">${entity.employee.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <i class="fa fa-calendar"></i>&nbsp; <fmt:formatDate
															value="${entity.evaluationTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;
												</a> ${entity.evaluationContent}
											</span><br>
												<ul class="list-inline font-xs">
													<li><a href="javascript:void(0);" class="text-info" onclick="goReply('${entity.id}');"><i class="fa  fa-reply"></i> 回复</a></li>
													<li><a href="javascript:void(0);" id="show_${entity.id}" class="text-muted" onclick="goComment('${entity.id}');">显示所有回复（${entity.getSubEvaluationReviews().size()}）</a></li>
													<li><a href="javascript:void(0);" id="hide_${entity.id}" class="text-muted">隐藏</a></li>
													<div id="comment_${entity.id}"></div>
													<div id="reply_${entity.id}"></div>
													<br>
												</ul></li>
										</s:iterator>
									</s:if>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="well padding-10">
				<h5 class="margin-top-0">
					<i class="fa fa-tags"></i> 关键字:
				</h5>
				<div class="row">
					<div class="col-lg-6">
						<ul class="list-unstyled">
							<li><a href=""><span class="badge badge-info">${trends.keywords}</span></a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="well padding-10">
				<h5 class="margin-top-0">
					<i class="fa fa-thumbs-o-up"></i> 分享
				</h5>
				<ul class="no-padding no-margin">
					<p class="no-margin">
						<a title="Facebook" href=""> <span class="fa-stack fa-lg"> <i class="fa fa-square-o fa-stack-2x"></i> <i class="fa fa-facebook fa-stack-1x"></i>
						</span>
						</a> <a title="Twitter" href=""> <span class="fa-stack fa-lg"> <i class="fa fa-square-o fa-stack-2x"></i> <i class="fa fa-twitter fa-stack-1x"></i>
						</span>
						</a> <a title="Google+" href=""> <span class="fa-stack fa-lg"> <i class="fa fa-square-o fa-stack-2x"></i> <i class="fa fa-google-plus fa-stack-1x"></i>
						</span>
						</a> <a title="Linkedin" href=""> <span class="fa-stack fa-lg"> <i class="fa fa-square-o fa-stack-2x"></i> <i class="fa fa-linkedin fa-stack-1x"></i>
						</span>
						</a> <a title="GitHub" href=""> <span class="fa-stack fa-lg"> <i class="fa fa-square-o fa-stack-2x"></i> <i class="fa fa-github fa-stack-1x"></i>
						</span>
						</a> <a title="Bitbucket" href=""> <span class="fa-stack fa-lg"> <i class="fa fa-square-o fa-stack-2x"></i> <i class="fa fa-bitbucket fa-stack-1x"></i>
						</span>
						</a>
					</p>
				</ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	//新闻评论
	function goNewsCommentarys(id) {
		$.ajax({
		url : '${nvix}/nvixnt/newsAdministrationAction!goNewsCommentary.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#newsCommentarys").html(html);
		}
		});
	};

	//根据新闻评论ID查看新闻评论的评论 
	function goComment(id) {
		$.ajax({
		url : '${nvix}/nvixnt/newsAdministrationAction!goComment.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#comment_" + id).html(html);
		}
		});
	};

	//根据新闻评论评论新闻 
	function goReply(id) {
		$.ajax({
		url : '${nvix}/nvixnt/newsAdministrationAction!goReply.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#reply_" + id).html(html);
		}
		});
	};
	//显示和隐藏
	$(document).ready(function() {
		$("a[id^='hide_']").each(function() {
			$(this).click(function() {
				var id = $(this).attr("id").split("_")[1];
				$("#comment_" + id).hide();
			});
		});
		$("a[id^='show_']").each(function() {
			$(this).click(function() {
				var id = $(this).attr("id").split("_")[1];
				$("#comment_" + id).show();
			});
		});
	});
</script>
