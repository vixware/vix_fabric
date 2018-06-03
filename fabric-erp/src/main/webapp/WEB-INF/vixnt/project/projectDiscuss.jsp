<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/vixntcommon/page/resource_css.jsp"%>
</head>
<body class="">
	<aside id="left-panel">
		<div class="login-info">
			<span> 
				<a href="javascript:void(0);" id="show-shortcut" data-action="toggleShortcut"> 
					<s:if test="userInfo.employee.headImgUrl!=null">
						<img src="${userInfo.employee.headImgUrl}" />
					</s:if> 
					<s:else>
						<img src="${nvix}/vixntcommon/base/img/avatars/sunny.png" />
					</s:else> 
					<span> ${userInfo.account}</span> <i class="fa fa-angle-down"></i>
				</a>
			</span>
		</div>
		<nav>
			<ul>
				<c:forEach items="${projectList}" var="project" varStatus="s">
					<li <c:if test="${s.index == 0}">class='active'</c:if>><a id="${project.id}" href="javascript:void(0);" title="${project.projectName}" onclick="loadProjectDiscuss('${project.id}')"><i class="fa fa-lg fa-fw fa-puzzle-piece"></i> <span class="menu-item-parent">${project.projectName}</span></a></li>
				</c:forEach>
			</ul>
		</nav> 
		<span class="minifyme" data-action="minifyMenu"> <i class="fa fa-arrow-circle-left hit"></i></span> 
	</aside>
	<div id="main" role="main">
		<div id="content">
			<div class="row hidden-mobile">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa-fw fa fa-puzzle-piece"></i> 项目管理 <span>> 项目沟通 </span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<section id="" class=""> 
						<div class="row">
							<article class="col-sm-12 col-md-12 col-lg-12">
								<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
									<header>
										<h2>项目沟通</h2>
									</header>
									<div id="evaluationReviewId">
										<div class="widget-body no-padding">
											<div class="col-sm-12 col-md-12 col-lg-8">
												<br>
												<div class="row">
													<div class="col-sm-12 col-md-12 col-lg-12  task-detail-title">
														<span class="pull-right"><span class="badge"><i class="fa fa-user"></i> ${employeeNum }</span></span> 
														<span class="text-danger"></span><span class=" text-muted">${project.projectName }</span>
													</div>
												</div>
												<ul class="media-list task-detail-list">
													<s:if test="evaluationReviewList.size > 0">
														<s:iterator value="evaluationReviewList" var="entity" status="s">
															<li class="media clearfix">
																<a class="pull-left" href="javascript:void(0);"> 
																	<s:if test="entity.employee.headImgUrl !=null">
																		<img src="${entity.employee.headImgUrl }" class="btn-circle no-padding btn-xl">
																	</s:if> 
																	<s:else>
																		<img src="${nvix}/vixntcommon/base/images/touxiang.png" class="btn-circle no-padding btn-xl">
																	</s:else>
																</a>
																<div class="media-body">
																	<h4 class="media-heading">
																		<span class="txt-color-blue">${entity.employee.name }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<small class="text-muted">${entity.evaluationTimeTimeStr}</small>
																	</h4>
																	<p>${entity.evaluationContent }</p>
																</div>
															</li>
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
																		<s:if test="empList.size > 0">
																			<s:iterator value="empList" var="entity" status="s">
																				<li>
																					<a href="javascript:void(0);">
																						<s:if test="entity.headImgUrl !=null">
																							<img src="${entity.headImgUrl }" alt="">${entity.name } 
																						</s:if> 
																						<s:else>
																							<img src="${nvix}/vixntcommon/base/images/touxiang.png" alt="">${entity.name } 
																						</s:else>
																						<span class="badge badge-inverse">23</span>
																						<span class="state"><i class="fa fa-circle txt-color-green pull-right"></i></span>
																					</a>
																				</li>
																			</s:iterator>
																		</s:if>
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
															<div id="chat-body" class="chat-body custom-scroll">
																<ul>
																	<s:if test="evaluationReviewList.size > 0">
																		<s:iterator value="evaluationReviewList" var="entity" status="s">
																			<li class="message"><s:if test="entity.employee.headImgUrl !=null">
																					<img src="${entity.employee.headImgUrl }" alt="" height="50px" width="50px">
																				</s:if> <s:else>
																					<img src="${nvix}/vixntcommon/base/images/touxiang.png" alt="" height="50px" width="50px">
																				</s:else>
																				<div class="message-text">
																					<time> ${entity.evaluationTimeTimeStr} </time>
																					<a href="javascript:void(0);" class="username">${entity.employee.name }</a> ${entity.evaluationContent } <!-- <i class="fa fa-smile-o txt-color-orange"></i> -->
																				</div></li>
																		</s:iterator>
																	</s:if>
																</ul>
															</div>
															<div class="chat-footer">
																<form id="editEvaluationReviewForm" action="" method="post">
																	<input type="hidden" id="projectId" name="project.id" value="${project.id }" />
																	<div class="textarea-div">
																		<div class="typearea">
																			<textarea id="evaluationContentId" name="evaluationReview.evaluationContent" class="custom-scroll" onkeydown="return submitkeyclick(this,event)"></textarea>
																		</div>
																	</div>
																</form>
																<span class="textarea-controls">
																	<button class="btn btn-sm btn-primary pull-right" onclick="saveOrUpdateEvaluationReview()">发送</button> 
																	<span class="pull-right smart-form" style="margin-top: 3px; margin-right: 10px;"> 
																		<label class="checkbox pull-right"> 
																			<input type="checkbox" name="subscription" id="subscription"> <i></i>按 <strong> ENTER </strong> 键发送消息
																		</label>
																	</span>
																</span>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-12 col-md-12 col-lg-4">
												<br>
													<div class="input-group">
														<input class="form-control" type="text" placeholder="Search...">
															<div class="input-group-btn">
																<button class="btn btn-success btn-primary" type="button">
																	<i class="fa fa-search"></i> Search
																</button>
															</div>
													</div>
													<div class="acititiy-wrap">
														<a href="#" class="acitiviy-ref pull-left"><i class="fa fa-repeat"></i></a>
														<div class="dropdown">
															<span class="pull-right"><i class="fa fa-arrows-alt"></i></span> <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Acititiy <b class="caret"></b></a>
															<ul class="dropdown-menu">
																<li><a href="#" data-toggle="tab">Acititiy</a></li>
																<li><a href="#" data-toggle="tab">Acititiy</a></li>
															</ul>
														</div>
													</div>
													<div class="acititiy-item">
														<p>
															<span class="text-primary">Lorem ipsum</span> dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat commodo consequat.
														</p>
														<div class="media">
															<span class="pull-left"><img src="${nvix}/vixntcommon/base/images/quot.png"> <img src="${nvix}/vixntcommon/base/img/avatars/1.png" class="btn-circle no-padding"></span>
															<div class="media-body">
																<div class="text-muted">nonummy nibh euismod tincidunt ut</div>
																<div class=" text-primary">laoreet dolore</div>
															</div>
														</div>
														<p>
															<span class="text-muted">nonummy</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-primary">laoreet dolore</span>
														</p>
														<p>
															<span class="text-muted">nonummy</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-primary">laoreet dolore</span>
														</p>
														<p>
															dolore magna&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-primary">erat volutpat commodo</span>
														</p>
														<p>
															<span class="text-muted">nonummy</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-primary">laoreet dolore</span>
														</p>
													</div>
													<div class="acititiy-item">
														<p>
															<span class="text-primary">Lorem ipsum</span> dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat commodo consequat.
														</p>
														<div class="media">
															<span class="pull-left"><img src="${nvix}/vixntcommon/base/images/quot.png"> <img src="${nvix}/vixntcommon/base/img/avatars/1.png" class="btn-circle no-padding"></span>
															<div class="media-body">
																<div class="text-muted">nonummy nibh euismod tincidunt ut</div>
																<div class=" text-primary">laoreet dolore</div>
															</div>
														</div>
														<p>
															<span class="text-muted">nonummy</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-primary">laoreet dolore</span>
														</p>
														<p>
															<span class="text-muted">nonummy</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-primary">laoreet dolore</span>
														</p>
														<p>
															dolore magna&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-primary">erat volutpat commodo</span>
														</p>
														<p>
															<span class="text-muted">nonummy</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-primary">laoreet dolore</span>
														</p>
													</div>
											</div>
										</div>
									</div>
								</div>
							</article>
						</div>
					</section>
				</div>
				<div class="superbox-show" style="height: 300px; display: none"></div>
			</div>
		</div>
	</div>
	<%@ include file="/vixntcommon/page/resource_js.jsp"%>
	<%@ include file="/WEB-INF/vixnt/common/nvix_footer.jsp"%>
	<script src="${nvix}/vixntcommon/common/nvix_index.js"></script>
<script type="text/javascript">
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
		url : "${nvix}/nvixnt/nvixProjectAction!saveOrUpdateEvaluationReview.action",
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
	function loadCrmProjectDiscuss(id){
		$("#evaluationReviewId").load("${nvix}/nvixnt/nvixProjectAction!loadProjectDiscuss.action?id=" + id);
	}
</script>
</body>
</html>