<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>项目管理<span>> 项目附件列表</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectDetail.action?id=${id }');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>${project.projectName }</h2>
		</header>
		<div>
			<div class="widget-body no-padding clearfix file-wrap">
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in clearfix active" id="s1">
						<div class="tab-content">
							<div class="col-sm-12 col-md-12 list-warp tab-pane active" id="t1">
								<div class="file-toolbar clearfix">
									<span class="smart-form pull-left"></span> <a data-toggle="modal" href="#uploadFile" class="btn btn-primary" onclick="goAddUploader('${project.id }')"><i class="glyphicon glyphicon-upload"></i> 上传文件</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" class="btn btn-default"
										onclick="deleteUploaderByIds();"> <i class="fa fa-trash-o"></i> 删 除
									</a> <span class="pull-right file-toolbar-buttons listbtn"> <a href="javascript:;" data-type="large"><i class="glyphicon glyphicon-th-large"></i></a> <a href="javascript:;" data-type="list" class="active"><i class="glyphicon glyphicon-th-list"></i></a>
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
															<span class="smart-form pull-left"><label class="checkbox"><input type="checkbox" name="checkbox" value="${entity.id }"><i></i>&nbsp;</label></span>
															<div class="file-icon">
																<img src="${nvix}/vixntcommon/base/images/icon-01.png">
																<div class="file-layer">
																	${entity.creator } 创建于${entity.createTimeTimeStr }
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
									</s:if>
								</ul>
								<!-- <div class="text-center">
												<ul class="pagination pagination-sm">
													<li class="disabled"><a href="javascript:void(0);">Prev</a></li>
													<li class="active"><a href="javascript:void(0);">1</a></li>
													<li><a href="javascript:void(0);">2</a></li>
													<li><a href="javascript:void(0);">3</a></li>
													<li><a href="javascript:void(0);">...</a></li>
													<li><a href="javascript:void(0);">99</a></li>
													<li><a href="javascript:void(0);">Next</a></li>
												</ul>
											</div> -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
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
			openSaveOrUpdateWindow('${nvix}/nvixnt/nvixProjectAction!goAddUploader.action?id=' + id, "uploaderForm", "添加附件", 550, 350);
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