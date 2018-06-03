<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="css/service.css" type="text/css" rel="stylesheet">
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery-ui.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/underscore-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/backbone-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tmpl.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-debug.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-tinyPubSub.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script src="js/service.js" type="text/javascript"></script>
<script src="js/WdatePicker.js" type="text/javascript"></script>
<script src="js/newservice.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.scrollfollow.js"></script>
<script type="text/javascript">
$(function(){
	$('#follow').scrollFollow({
		offset:50,
	   container: 'followBox'
	  });
	  
});

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');
 
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goList.action');"><img src="img/pm_task.png" alt="" /> 协同办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goList.action');">任务管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goList.action');">任务列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goList.action');">任务过程查看 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="loadContent('${vix}/oa/taskMonitorAction!goList.action');"><span><s:text name='任务监控' /></span></a>
		</p>
	</div>
</div>
<!-- sub menu -->
<div class="content">
	<!-- c_head -->
	<div class="box">
		<div class="span12">
			<!-- <div class="align-right">
								<span class="green middle bolder">选择时间: &nbsp;</span>

								<div class="btn-toolbar inline middle no-margin">
									<div data-toggle="buttons-radio" class="btn-group no-margin">
										<button class="btn btn-small btn-yellow active">
											<span class="bigger-110">1</span>
										</button>

										<button class="btn btn-small btn-yellow">
											<span class="bigger-110">2</span>
										</button>
									</div>
								</div>
							</div> -->
			<div id="timeline-1">
				<input type="hidden" id="id" name="id" value="${taskDefinition.id}" />
				<div class="row-fluid">
					<div class="offset1 span10">
						<!-- 如果没有反馈信息显示如需下 -->
						<s:if test="pager.resultList.size()==0">
							<div class="timeline-container">
								<div class="timeline-label">
									<span class="label label-primary arrowed-in-right label-large"> <b>
											<td>没有反馈信息，反馈后才能查看!</td>
									</b>
									</span>
								</div>
								<div class="timeline-items">
									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<img src="${vix}/common/img/oa/avatar1.png" alt="" /> <span class="label label-info">${taskDefinition.validity}</span>
										</div>
										<div class="widget-box transparent">
											<div class="widget-header widget-header-small">
												<h5 class="smaller">
													<a href="#" class="blue">${taskDefinition.executiveAgency}</a> <span class="grey">${taskDefinition.questName}</span>
												</h5>

												<span class="widget-toolbar no-border"> <i class="icon-time bigger-110"></i>
													<td><a href="#" style="color: gray;"><fmt:formatDate value="${taskDefinition.startTime}" type="both" pattern="yyyy-MM-dd" /> </a></td>
												</span> <span class="widget-toolbar"> <a href="#" data-action="reload"> <i class="icon-refresh"></i>
												</a> <a href="#" data-action="collapse"> <i class="icon-chevron-up"></i>
												</a>
												</span>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="space-6"></div>

													<div class="widget-toolbox clearfix">
														<div class="pull-left">
															<i class="icon-hand-right grey bigger-125"></i> <a href="#" class="bigger-110">${taskDefinition.taskDescription} &hellip;</a>
														</div>
														<div class="pull-right action-buttons">
															<a href="#"> <i class="icon-ok green bigger-130"></i>
															</a> <a href="#"> <i class="icon-pencil blue bigger-125"></i>
															</a> <a href="#"> <i class="icon-remove red bigger-125"></i>
															</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</s:if>
						<s:iterator value="pager.resultList" var="entity" status="s">
							<div class="timeline-container">
								<div class="timeline-label">
									<span class="label label-primary arrowed-in-right label-large"> <b>
											<td id="feedbackTime_${entity.id}"><s:if test="#entity.timeDiff == 0">
													<span style="color: white;">今天</span>
												</s:if> <s:elseif test="#entity.timeDiff == 1">
													<span style="color: white;">昨天</span>
												</s:elseif> <s:elseif test="#entity.timeDiff == 2">
													<span style="color: white;">前天</span>
												</s:elseif> <s:else>
													<span style="color: white;"><s:date name="#entity.feedbackTime" format="yyyy-MM-dd" /></span>
												</s:else></td>
									</b>
									</span>
								</div>
								<div class="timeline-items">
									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<img src="${vix}/common/img/oa/avatar1.png" alt="" /> <span class="label label-info">${taskDefinition.validity}</span>
										</div>

										<div class="widget-box transparent">
											<div class="widget-header widget-header-small">
												<h5 class="smaller">
													<a href="#" class="blue">${taskDefinition.executiveAgency}</a> <span class="grey">${taskDefinition.questName}</span>
												</h5>

												<span class="widget-toolbar no-border"> <i class="icon-time bigger-110"></i>
													<td><a href="#" style="color: gray;"><fmt:formatDate value="${taskDefinition.startTime}" type="both" pattern="yyyy-MM-dd" /> </a></td>
												</span> <span class="widget-toolbar"> <a href="#" data-action="reload"> <i class="icon-refresh"></i>
												</a> <a href="#" data-action="collapse"> <i class="icon-chevron-up"></i>
												</a>
												</span>
											</div>

											<div class="widget-body">
												<div class="widget-main">
													<div class="space-6"></div>

													<div class="widget-toolbox clearfix">
														<div class="pull-left">
															<i class="icon-hand-right grey bigger-125"></i> <a href="#" class="bigger-110">${taskDefinition.taskDescription} &hellip;</a>
														</div>
														<div class="pull-right action-buttons">
															<a href="#"> <i class="icon-ok green bigger-130"></i>
															</a> <a href="#"> <i class="icon-pencil blue bigger-125"></i>
															</a> <a href="#"> <i class="icon-remove red bigger-125"></i>
															</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- 执行反馈内容 -->
									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<i class="timeline-indicator icon-food btn btn-success no-hover"></i>
										</div>
										<div class="widget-box transparent">
											<div class="widget-header widget-header-small hidden"></div>
											<div class="widget-body">
												<div class="widget-main">
													<td>${entity.executionFeedback}</td>
													<div class="pull-right">
														<i class="icon-time bigger-110"></i>
														<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.feedbackTime}" type="both" pattern="yyyy-MM-dd" /> </a></td>
													</div>
												</div>
											</div>
										</div>
									</div>


									<s:iterator value="feedbackUploaderList" var="entity" status="s">
										<div class="timeline-item clearfix">
											<div class="timeline-info">
												<i class="timeline-indicator icon-star btn btn-warning no-hover green"></i>
											</div>
											<div class="widget-box transparent">
												<div class="widget-header widget-header-small">
													<h5 class="smaller">任务反馈附件</h5>
													<span class="widget-toolbar no-border"> <i class="icon-time bigger-110"></i>
														<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.uploadTime}" type="both" pattern="yyyy-MM-dd" /> </a></td>
													</span>
												</div>
												<div class="widget-body">
													<div class="widget-main">
														<td>${entity.title}</td>
														<div class="pull-right">
															<i class="icon-chevron-left blue bigger-110"></i> &nbsp; <a href="${vix}/oa/feedbackAction!downloadEqDocument.action?id=${entity.id}">下载</a> &nbsp; <i class="icon-chevron-right blue bigger-110"></i>
														</div>
														<div class="space-6"></div>
														<div class="widget-toolbox clearfix">
															<div class="pull-right action-buttons">
																<div class="space-4"></div>
																<div>
																	<a href="#"> <i class="icon-heart red bigger-125"></i>
																	</a> <a href="#"> <i class="icon-facebook blue bigger-125"></i>
																	</a> <a href="#"> <i class="icon-reply light-green bigger-130"></i>
																	</a>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</s:iterator>

									<s:iterator value="uploaderList" var="entity" status="s">
										<div class="timeline-item clearfix">
											<div class="timeline-info">
												<i class="timeline-indicator icon-star btn btn-warning no-hover green"></i>
											</div>
											<div class="widget-box transparent">
												<div class="widget-header widget-header-small">
													<h5 class="smaller">任务附件</h5>
													<span class="widget-toolbar no-border"> <i class="icon-time bigger-110"></i>
														<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.uploadTime}" type="both" pattern="yyyy-MM-dd" /> </a></td>
													</span>
												</div>
												<div class="widget-body">
													<div class="widget-main">
														<td>${entity.title}</td>
														<div class="pull-right">
															<i class="icon-chevron-left blue bigger-110"></i> &nbsp; <a href="${vix}/oa/taskDefineAction!downloadEqDocument.action?id=${entity.id}">下载</a> &nbsp; <i class="icon-chevron-right blue bigger-110"></i>
														</div>
														<div class="space-6"></div>
														<div class="widget-toolbox clearfix">
															<div class="pull-right action-buttons">
																<div class="space-4"></div>

																<div>
																	<a href="#"> <i class="icon-heart red bigger-125"></i>
																	</a> <a href="#"> <i class="icon-facebook blue bigger-125"></i>
																	</a> <a href="#"> <i class="icon-reply light-green bigger-130"></i>
																	</a>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</s:iterator>
								</div>
							</div>
						</s:iterator>
					</div>
				</div>
			</div>




			<div id="timeline-2" class="hide">
				<div class="row-fluid">
					<div class="offset1 span10">
						<div class="timeline-container timeline-style2">
							<span class="timeline-label"> <b>今天</b>
							</span>

							<div class="timeline-items">
								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">11:15 下午</span> <i class="timeline-indicator btn btn-info no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												<span class="bigger-110"> <a href="#" class="purple bolder">Susan</a> reviewed a product
												</span> <br /> <i class="icon-hand-right grey bigger-125"></i> <a href="#">Click to read &hellip;</a>
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">12:30 下午</span> <i class="timeline-indicator btn btn-info no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												Going to <span class="green bolder">veg cafe</span> for lunch
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">11:15 下午</span> <i class="timeline-indicator btn btn-info no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												Designed a new logo for our website. Would appreciate feedback. <a href="#"> Click to see <i class="icon-zoom-in blue bigger-110"></i>
												</a>

												<div class="space-2"></div>

												<div class="action-buttons">
													<a href="#"> <i class="icon-heart red bigger-125"></i>
													</a> <a href="#"> <i class="icon-facebook blue bigger-125"></i>
													</a> <a href="#"> <i class="icon-reply light-green bigger-130"></i>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 上午</span> <i class="timeline-indicator btn btn-info no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">Took the final exam. Phew!</div>
										</div>
									</div>
								</div>
							</div>
							<!--/.timeline-items-->
						</div>
						<!--/.timeline-container-->

						<div class="timeline-container timeline-style2">
							<span class="timeline-label"> <b>昨天</b>
							</span>

							<div class="timeline-items">
								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 上午</span> <i class="timeline-indicator btn btn-success no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												<div class="clearfix">
													<div class="pull-left">
														<span class="orange2 bolder">Haloween party</span> Lots of fun at Haloween party. <br /> Take a look at some pics:
													</div>

													<div class="pull-right">
														<i class="icon-chevron-left blue bigger-110"></i> &nbsp; <img alt="Image 4" width="36" src="img/face_2.jpg" /> <img alt="Image 3" width="36" src="img/face_2.jpg" /> <img alt="Image 2" width="36" src="img/face_2.jpg" /> <img alt="Image 1" width="36" src="img/face_2.jpg" /> &nbsp; <i class="icon-chevron-right blue bigger-110"></i>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 上午</span> <i class="timeline-indicator btn btn-success no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												Anim pariatur cliche reprehenderit, enim eiusmod <span class="pink2 bolder">high life</span> accusamus terry richardson ad squid &hellip;
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 上午</span> <i class="timeline-indicator btn btn-success no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">Going to cafe for lunch</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 上午</span> <i class="timeline-indicator btn btn-success no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												<span class="red bolder">Critical security patch released</span> <br /> Please download the new patch for maximum security.
											</div>
										</div>
									</div>
								</div>
							</div>
							<!--/.timeline-items-->
						</div>
						<!--/.timeline-container-->

						<div class="timeline-container timeline-style2">
							<span class="timeline-label"> <b>5月17日</b>
							</span>

							<div class="timeline-items">
								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 上午</span> <i class="timeline-indicator btn btn-grey no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												<span class="bolder blue">Lorum Ipsum</span> Anim pariatur cliche reprehenderit, enim eiusmod <span class="purple bolder">high life</span> accusamus terry richardson ad squid &hellip;
											</div>
										</div>
									</div>
								</div>
							</div>
							<!--/.timeline-items-->
						</div>
						<!--/.timeline-container-->
					</div>
				</div>
			</div>
			<!--PAGE CONTENT ENDS-->
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<!-- content -->
<div id="footpanel">
	<ul id="mainpanel">
		<li><a href="http://www.designbombs.com" class="home">Inspiration <small>Design </small></a></li>
		<li><a href="http://www.designbombs.com" class="profile">View Profile <small>View Profile</small></a></li>
		<li><a href="http://www.designbombs.com" class="editprofile">Edit Profile <small>Edit Profile</small></a></li>
		<li><a href="http://www.designbombs.com" class="contacts">Contacts <small>Contacts</small></a></li>
		<li><a href="http://www.designbombs.com" class="messages">Messages (10) <small>Messages</small></a></li>
		<li><a href="http://www.designbombs.com" class="playlist">Play List <small>Play List</small></a></li>
		<li><a href="http://www.designbombs.com" class="videos">Videos <small>Videos</small></a></li>
		<li id="alertpanel"><a class="alerts" id="alerts_off">Alerts</a></li>
		<li id="chatpanel"><a href="#" class="chat">Friends (<strong>18</strong>)
		</a>
			<div class="subpanel">
				<h3>
					<span> &ndash; </span>Friends Online
				</h3>
				<ul>
					<li><span>Family Members</span></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><span>Other Friends</span></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
				</ul>
			</div></li>
	</ul>
</div>
<div id="footpanel_switch">
	<ul id="mainpanel">
		<li id="alertpanel"><a class="alerts" id="alerts_on">Alerts</a></li>
	</ul>
</div>
<!-- pos_menu -->

<!-- <div id="dialog" title="Basic dialog">
	<p>这是默认对话框用于显示信息。对话框窗口可以移动,调整大小和封闭的“x”图标。</p>
</div> -->
<!-- dialog -->
<script src="js/menu.js" type="text/javascript"></script>
<script src="js/loadtree.js" type="text/javascript"></script>
<div id="helpContent" title="Viewing and using brush files">
	<p>&nbsp;&nbsp;You can view brush files in the Browser just like fonts or other files. This means that you can assign ratings or organize them just like any other file in ACDSee. Because many .abr files are actually groups of images in one file, you need to open them in the Viewer to see the individual images.</p>
	<p>
		<img src="img/address_book.png">This icon indicates an .abr brush file in ACDSee.
	</p>
	<p>
		<strong>To view brush files:</strong>
	</p>
	<p>&nbsp;&nbsp;1. In the Browser, navigate to the folder containing your brush files.</p>
	<p>&nbsp;&nbsp;2. To see just the top image in any .abr file, hover over the thumbnail to activate the pop-up, or click on it to see that image in the Preview pane.</p>
	<p>&nbsp;&nbsp;3. To view the other images in the .abr file, double-click on it to open it in the Viewer.</p>
	<p>&nbsp;&nbsp;The file opens in the Viewer showing the individual images in a pane on the left-hand side.</p>
	<p>&nbsp;&nbsp;4. To see the number of images, and select them by number, click the down-arrow at the top of the sidebar, and then select the number of the image.</p>
	<p>&nbsp;&nbsp;5. To scroll through the images, click the right and left arrows at the top of the sidebar, or on each image.</p>
	<p>
		<strong>To use brush files in Adobe Photoshop:</strong>
	</p>
	<p>&nbsp;&nbsp;With both Adobe Photoshop and ACDSee open, drag the file from the File List (in the Browser) onto the Photoshop window.</p>
	<p>&nbsp;&nbsp;Even though nothing appears to happen, the brush is loaded into the Photoshop brush library. To view the new brushes, open the library and scroll to the bottom of the pane.</p>
	<p class="helpTd">To make it even easier to use brushes in Photo Shop, you can configure it to be your default editor. Then you can use Ctrl +E to open Photoshop and use the brush right away.</p>
</div>
<script>
$(function(){
	$('[data-action=collapse]').click(function(){
		$('i',this).toggleClass('icon-chevron-down');
		$(this).closest('.widget-box').find('.widget-body').slideToggle('fast');
		return false;
	});
	
	$('[data-action=reload]').click(function(){
		$(this).closest('.widget-box').append('<div class="widget-box-layer"><i class="icon-spinner icon-spin icon-2x white"></i></div>');
		(function(el){
			setTimeout(function(){
				el.find('.widget-box-layer').remove();
			},3000);
		})($(this).closest('.widget-box'));
	});
	$('[data-toggle=buttons-radio]').on('click', function(e){
		var target = $(e.target);
		var which = parseInt($.trim(target.text()));
		$('button',this).removeClass('active');
		target.addClass('active');
		
		
		$('[id*="timeline-"]').hide();
		$('#timeline-'+which).show();
	});

});
</script>



