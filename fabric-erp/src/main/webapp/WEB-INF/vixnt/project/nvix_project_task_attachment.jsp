<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<s:if test="source =='task'">
					<i class="fa fa-tasks fa-fw "></i>
					<a data-toggle="modal" href="#" style="cursor: pointer;" onclick="loadContent('','${nvix}/nvixnt/taskAndPlanAction!goList.action');">任务与计划 </a>
					<span>> <a data-toggle="modal" href="#" style="cursor: pointer;" onclick="loadContent('mid_projectstask','${nvix}/nvixnt/taskAndPlanAction!goProjectTaskManagement.action }');">项目任务管理 </a>
					</span>
					<span>> <a data-toggle="modal" href="#" style="cursor: pointer;" onclick="loadContent('mid_projectstask','${nvix}/nvixnt/nvixProjectAction!goProjectTaskDetails.action?id=${vixTask.id }&source=task');">任务详情 </a>
					</span>
					<span>> 任务附件列表</span>
				</s:if>
				<s:else>
					<i class="fa fa-puzzle-piece fa-fw "></i>
					<a data-toggle="modal" href="#" style="cursor: pointer;" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goList.action');">项目 </a>
					<span>> <a data-toggle="modal" href="#" style="cursor: pointer;" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectTracing.action');">项目跟踪管理 </a> <a data-toggle="modal" href="#" style="cursor: pointer;" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectTaskDetails.action?id=${vixTask.id}');">> 任务详情
					</a> <span>> 任务附件列表</span>
				</s:else>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('mid_teamtask', '${nvix}/nvixnt/nvixProjectAction!goProjectTaskDetails.action?id=${vixTask.id}&source=${source}');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-sm-12 col-md-12 col-lg-12">
				<!-- new widget -->
				<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
					<header>
						<h2>${vixTask.questName }</h2>
					</header>
					<div>
						<div class="widget-body no-padding clearfix file-wrap">
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade in clearfix active" id="s1">
									<div class="tab-content">
										<div class="col-sm-12 col-md-12 list-warp tab-pane active" id="t1">
											<div class="file-toolbar clearfix">
												<span class="smart-form pull-left"><label class="checkbox"><input type="checkbox" name="checkbox"><i></i>&nbsp;</label></span> <a data-toggle="modal" href="#uploadFile" class="btn btn-primary" onclick="goAddUploader('${vixTask.id }')"><i class="glyphicon glyphicon-upload"></i> 上传文件</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
													href="#" class="btn btn-default" onclick="deleteUploaderByIds();">删 除</a> <span class="pull-right file-toolbar-buttons listbtn"> <a href="javascript:;" data-type="large"><i class="glyphicon glyphicon-th-large"></i></a> <a href="javascript:;" data-type="list" class="active"><i class="glyphicon glyphicon-th-list"></i></a>
												</span>
											</div>
											<ul class="file-list clearfix fileList" id="fileList">
												<s:if test="uploaderList.size > 0">
													<s:iterator value="uploaderList" var="entity" status="s">
														<li>
															<div class="file-list-list clearfix">
																<div class="pull-right file-buttons">
																	<a href="#" onclick="downloadUploader('${entity.id }');"><i class="fa fa-download"></i></a><a href="#" onclick="deleteUploaderById('${entity.id }');"><i class="fa fa-trash-o"></i></a>
																</div>
																<div class="file-body">
																	<div class="clearfix">
																		<span class="smart-form pull-left"><label class="checkbox"><input type="checkbox" name="checkbox"><i></i>&nbsp;</label></span>
																		<div class="file-icon">
																			<img src="${nvix}/vixntcommon/base/images/icon-01.png">
																			<div class="file-layer">
																				${entity.creator } 创建于
																				<s:date name="%{entity.createTime}" format="yyyy-MM-dd HH:mm:ss" />
																				<div class="clearfix">
																					<div class="pull-right layer-buttons">
																						<a href="javascript:;" class="comment-btn" onclick="downloadUploader('${entity.id }');"><i class="fa fa-download"></i></a> <a href="#" onclick="deleteUploaderById('${entity.id }');"><i class="fa fa-trash-o"></i></a>
																					</div>
																					${entity.downloadNum }人下载
																				</div>
																			</div>
																		</div>
																		<div class="file-line">
																			<div class="file-title">${entity.fileName }</div>
																			<div class="file-desc">${entity.creator }创建于<s:date name="%{entity.createTime}" format="yyyy-MM-dd HH:mm:ss" />
																				&nbsp;&nbsp;&nbsp;&nbsp;${entity.downloadNum }人下载
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</li>
													</s:iterator>
												</s:if>
											</ul>
											<div class="text-center">
												<ul class="pagination pagination-sm">
													<li class="disabled"><a href="javascript:void(0);">Prev</a></li>
													<li class="active"><a href="javascript:void(0);">1</a></li>
													<li><a href="javascript:void(0);">2</a></li>
													<li><a href="javascript:void(0);">3</a></li>
													<li><a href="javascript:void(0);">...</a></li>
													<li><a href="javascript:void(0);">99</a></li>
													<li><a href="javascript:void(0);">Next</a></li>
												</ul>
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
	$(document).ready(function() {
		pageSetUp();
		$('.comment-btn').on('click', function() {
			if ($(this).hasClass('active')) {
				$(this).removeClass('active');
				$(this).closest('li').find('.chat-body').stop().slideUp(200);
			} else {
				$(this).addClass('active');
				$(this).closest('li').find('.chat-body').stop().slideDown(200);
			}
		});
		$('.listbtn a').on('click', function() {
			if ($(this).hasClass('active')) {
				return false;
			}
			var type = $(this).attr('data-type');
			$('.listbtn a').removeClass('active');
			$('[data-type="' + type + '"]').addClass('active');

			if (type == 'large') {
				$('.fileList').addClass('file-large');
			} else {
				$('.fileList').removeClass('file-large');
			}
		});
		$('.fileList li').hover(function() {
			if ($(this).closest('.file-large').length > 0) {
				$(this).addClass('show-layer');
			} else {
				$(this).addClass('show-buttons');
			}
		}, function() {
			$(this).removeClass('show-layer').removeClass('show-buttons');
		});
	});
	//添加附件
	function goAddUploader(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixProjectAction!goAddTaskUploader.action?id=' + id, "uploaderForm", "添加附件", 550, 350);
	};
	function deleteUploaderByIds() {
		var chk_value = [];
		$('input[name="checkbox"]:checked').each(function() {
			chk_value.push($(this).val());
		});
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!deleteUploaderByIds.action?ids=' + chk_value, '是否删除附件?');
	};
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!deleteUploaderById.action?id=' + id, '是否删除附件?');
	};
	function downloadUploader(id) {
		var url = "${nvix}/nvixnt/nvixProjectAction!downloadUploader.action?id=" + id;
		window.open(url);
	};
</script>