<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-tasks fa-fw"></i> <a data-toggle="modal" href="#" style="cursor: pointer;" onclick="loadContent('','${nvix}/nvixnt/taskAndPlanAction!goList.action');">任务与计划 </a> <span>> <a data-toggle="modal" href="#" style="cursor: pointer;"
					onclick="loadContent('mid_projectstask','${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=${vixTask.id }');">任务详情 </a>
				</span> <span>> 任务附件 </span>
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
												<s:iterator value="pager.resultList" var="entity" status="s">
													<li>
														<div class="file-list-list clearfix">
															<div class="pull-right file-buttons">
																<a href="#" onclick="downloadUploader('${entity.id }');"><i class="fa fa-download"></i></a><a href="#" onclick="deleteUploaderById('${entity.id }');"><i class="fa fa-trash-o"></i></a>
															</div>
															<div class="file-body">
																<div class="clearfix">
																	<span class="smart-form pull-left"><label class="checkbox"><input type="checkbox" name="checkbox" value="${entity.id }"><i></i>&nbsp;</label></span>
																	<div class="file-icon">
																		<c:choose>
																			<c:when test="${entity.fileType == 'xls' || entity.fileType == 'xlsx'}"><img src="${nvix}/vixntcommon/base/images/icon-01.png"></c:when>
																			<c:when test="${entity.fileType == 'mp3' || entity.fileType == 'mp4'}"><img src="${nvix}/vixntcommon/base/images/icon-03.png"></c:when>
																			<c:when test="${entity.fileType == 'txt'}"><img src="${nvix}/vixntcommon/base/images/icon-04.png"></c:when>
																			<c:when test="${entity.fileType == 'jpg' || entity.fileType == 'png' || entity.fileType == 'gif'}"><img src="${nvix}/vixntcommon/base/images/icon-05.png"></c:when>
																			<c:when test="${entity.fileType == 'ppt' || entity.fileType == 'pptx'}"><img src="${nvix}/vixntcommon/base/images/icon-07.png"></c:when>
																			<c:when test="${entity.fileType == 'doc' || entity.fileType == 'docx'}"><img src="${nvix}/vixntcommon/base/images/icon-08.png"></c:when>
																			<c:when test="${entity.fileType == 'pdf'}"><img src="${nvix}/vixntcommon/base/images/icon-10.png"></c:if>
																			<c:otherwise><img src="${nvix}/vixntcommon/base/images/icon-04.png"></c:otherwise>
																		</c:choose>
																		<div class="file-layer">
																			${entity.creator } 创建于 ${entity.createTimeTimeStr }
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
																		<div class="file-desc">${entity.creator }创建于${entity.createTimeTimeStr }&nbsp;&nbsp;&nbsp;&nbsp;${entity.downloadNum }人下载</div>
																	</div>
																</div>
															</div>
														</div>
													</li>
												</s:iterator>
											</ul>
											<div class="text-center">
												<ul class="pagination" id="myWorkLogPage"></ul>
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
		openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goAddUploader.action?parentId=' + id, "uploaderForm", "添加附件", 550, 350);
	};
	function deleteUploaderByIds() {
		var chk_value = [];
		$('input[name="checkbox"]:checked').each(function() {
			chk_value.push($(this).val());
		});
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteUploaderByIds.action?ids=' + chk_value, '是否删除附件?');
	};
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteUploaderById.action?id=' + id, '是否删除附件?');
	};
	function downloadUploader(id) {
		var url = "${nvix}/nvixnt/taskAndPlanAction!downloadUploader.action?id=" + id;
		window.open(url);
	};
	$.jqPaginator('#myWorkLogPage', {
		totalPages :${pager.totalPage} ,
		visiblePages :${pager.pageSize},
		currentPage : ${pager.pageNo},
		prev : '<li class="prev"><a href="javascript:;">上一页</a></li>',
		next : '<li class="next"><a href="javascript:;">下一页</a></li>',
		page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
		onPageChange : function(num, type) {
			if(type=='change'){
			$.ajax({
				url : '${nvix}/nvixnt/dailyJournalAction!goShowMyWorkLogPager.action?pageNo=' + num,
				cache : false,
				success : function(html) {
					$("#sdaily").html(html);
				}
				});
			}
		}
		});
</script>