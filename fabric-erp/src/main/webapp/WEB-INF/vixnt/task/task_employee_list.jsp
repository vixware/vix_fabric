<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-tasks fa-fw "></i> <a data-toggle="modal" href="#" style="cursor: pointer;" onclick="loadContent('','${nvix}/nvixnt/taskAndPlanAction!goList.action');">任务与计划 </a> <span>> <a data-toggle="modal" href="#" style="cursor: pointer;"
					onclick="loadContent('mid_projectstask','${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=${vixTask.id }');">任务详情 </a>
				</span> <span>> 人员列表 </span>
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="well well-sm">
				<div class="row">
					<div class="col-sm-12 col-md-12 col-lg-12">
						<div class="well well-light well-sm no-margin no-padding">
							<div class="row">
								<div class="col-sm-12">
									<div id="myCarousel" class="carousel fade profile-carousel">
										<div class="air air-top-left padding-10">
											<h4 class="txt-color-white font-md">
												<s:date name="%{vixTask.createTime}" format="yyyy-MM-dd HH:mm:ss" />
											</h4>
										</div>
										<ol class="carousel-indicators">
											<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
											<li data-target="#myCarousel" data-slide-to="1" class=""></li>
											<li data-target="#myCarousel" data-slide-to="2" class=""></li>
										</ol>
										<div class="carousel-inner">
											<!-- Slide 1 -->
											<div class="item active">
												<img src="${nvix}/vixntcommon/base/img/demo/s1.jpg" alt="demo user">
											</div>
											<!-- Slide 2 -->
											<div class="item">
												<img src="${nvix}/vixntcommon/base/img/demo/s2.jpg" alt="demo user">
											</div>
											<!-- Slide 3 -->
											<div class="item">
												<img src="${nvix}/vixntcommon/base/img/demo/m3.jpg" alt="demo user">
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="row">
										<div id="information">
											<div class="col-sm-3 profile-pic">
												<img src="${emp.headImgUrl }" alt="demo user">
												<div class="padding-10">
													<h4 class="font-md">
														<small>${emp.position}</small> <br> <strong>职位</strong>
													</h4>
												</div>
											</div>
											<div class="col-sm-5">
												<h1>
													<span>${emp.name}</span>
												</h1>
												<ul class="list-unstyled">
													<li>
														<p class="text-muted">
															<i class="fa fa-phone"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${emp.mobile}&nbsp;&nbsp;</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-envelope"></i>&nbsp;&nbsp; <a href="mailto:simmons@smartadmin"> ${emp.email}&nbsp;&nbsp; </a>
														</p>
													</li>
												</ul>
												<p class="font-md">
													<i>个人简介</i>
												</p>
												<p>${emp.memo}</p>
											</div>
										</div>
										<div class="col-sm-4">
											<h1>
												<small>联系人</small>
											</h1>
											<s:if test="empList.size > 0">
												<s:iterator value="empList" var="entity" status="s">
													<div class="user" title="" onclick="goInformation('${entity.id }');">
														<img src="${entity.headImgUrl }" alt="demo user"> <a href="javascript:void(0);">${entity.name}</a>
														<div class="email">${entity.mobile}</div>
													</div>
												</s:iterator>
											</s:if>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<hr>
									<div class="padding-10">
										<ul class="nav nav-tabs tabs-pull-right">
											<li class="active"><a href="#a1" data-toggle="tab">人员列表</a></li>
										</ul>
										<div class="tab-content padding-top-10">
											<div class="tab-pane fade in active" id="a1">
												<s:if test="empList.size > 0">
													<s:iterator value="empList" var="entity" status="s">
														<div class="user" title="" onclick="goInformation('${entity.id }','${vixTask.id}');">
															<img src="${entity.headImgUrl }" alt="demo user"> <a href="javascript:void(0);">${entity.name}</a>
															<div class="email">${entity.mobile}</div>
														</div>
													</s:iterator>
												</s:if>
											</div>
										</div>
									</div>
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
	function goInformation(id) {
		$.ajax({
		url : '${nvix}/nvixnt/taskAndPlanAction!goTaskEmployeeListContent.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#information").html(html);
		}
		});
	};
</script>