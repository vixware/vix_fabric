<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-file"></i> 文件管理
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="loadContent('','${nvix}/nvixnt/nvixFileAction!goUploaderTypeList.action');">
					<i class="fa fa-file-text-o"></i>&nbsp;分类管理
				</button>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<section id="" class="">
				<div class="row">
					<article class="col-sm-12 col-md-12 col-lg-12">
						<div class="jarviswidget" id="">
							<header><span class="widget-icon"> <i class="fa fa-table"></i></span>
								<h2>文档</h2>
								<ul class="nav nav-tabs in pull-right" id="myTab">
									<li class="active"><a data-toggle="tab" href="#s1"><span class="hidden-mobile hidden-tablet">分类</span></a></li>
									<!-- <li><a data-toggle="tab" href="#s2"><span class="hidden-mobile hidden-tablet">项目</span></a></li> -->
								</ul>
							</header>
							<div>
								<div class="widget-body no-padding clearfix file-wrap">
									<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade in clearfix active" id="s1">
											<div class="tab-content">
												<div class="col-sm-12 col-md-12 col-lg-10 list-warp tab-pane active">
													<div class="file-toolbar clearfix">
														<span class="smart-form pull-left"><label class="checkbox"><input type="checkbox" name="checkbox"><i></i>&nbsp;</label></span> <a data-toggle="modal" href="#" class="btn btn-primary" onclick="goAddUploader();"> <i class="glyphicon glyphicon-upload"></i>&nbsp;&nbsp;上传文档
														</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" class="btn btn-primary" onclick="deleteUploaderByIds();"> <i class="glyphicon glyphicon-trash"></i>&nbsp;&nbsp;删 除
														</a> <span class="pull-right file-toolbar-buttons listbtn"> <a href="javascript:;" data-type="large"><i class="glyphicon glyphicon-th-large"></i></a> <a href="javascript:;" data-type="list" class="active"><i class="glyphicon glyphicon-th-list"></i></a>
														</span>
													</div>
													<ul class="file-list clearfix fileList">
														<div id="sdaily" style="height: 500px">
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
																					<c:if test='${entity.fileType == "xlsx"}'>
																						<img src="${nvix}/vixntcommon/base/images/icon-01.png">
																					</c:if>
																					<c:if test='${entity.fileType == "xls"}'>
																						<img src="${nvix}/vixntcommon/base/images/icon-01.png">
																					</c:if>
																					<c:if test='${entity.fileType == "docx"}'>
																						<img src="${nvix}/vixntcommon/base/images/icon-08.png">
																					</c:if>
																					<c:if test='${entity.fileType == "doc"}'>
																						<img src="${nvix}/vixntcommon/base/images/icon-08.png">
																					</c:if>
																					<c:if test='${entity.fileType == "txt"}'>
																						<img src="${nvix}/vixntcommon/base/images/icon-04.png">
																					</c:if>
																					<c:if test='${entity.fileType == "pdf"}'>
																						<img src="${nvix}/vixntcommon/base/images/icon-10.png">
																					</c:if>
																					<div class="file-layer">
																						${entity.creator } 创建于 ${fn:substring(entity.createTime, 0,19)}
																						<div class="clearfix">
																							<div class="pull-right layer-buttons">
																								<a href="javascript:;" class="comment-btn" onclick="downloadUploader('${entity.id }');"><i class="fa fa-download"></i></a> <a href="#" onclick="deleteUploaderById('${entity.id }');"><i class="fa fa-trash-o"></i></a>
																							</div>
																						</div>
																					</div>
																				</div>
																				<div class="file-line">
																					<div class="file-title">${entity.fileName }</div>
																					<div class="file-desc">${entity.creator }创建于${fn:substring(entity.createTime, 0,19)}</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</li>
															</s:iterator>
														</div>
													</ul>
													<div class="text-center">
														<ul class="pagination" id="myWorkLogPage"></ul>
													</div>
												</div>
											</div>
											<div class="col-sm-12 col-md-12 col-lg-2 slidebox no-padding">
												<div class="file-menu">
													<div class="file-menu-item">
														<ul class="file-menu-list">
															<s:iterator value="uploaderTypeList" var="entity" status="s">
																<li><a href="#" onclick="searchDaily('${entity.id }');" data-toggle="tab" aria-expanded="true"><i class="fa fa-folder-o"></i> ${entity.name }</a></li>
															</s:iterator>
														</ul>
													</div>
												</div>
											</div>
										</div>
										<div class="tab-pane fade in clearfix" id="s2">
											<div class="tab-content">
												<div class="col-sm-12 col-md-12 col-lg-10 list-warp tab-pane active">
													<div class="file-toolbar clearfix">
														<span class="smart-form pull-left"><label class="checkbox"><input type="checkbox" name="checkbox"><i></i>&nbsp;</label></span> <a data-toggle="modal" href="#" class="btn btn-primary" onclick="goAddUploader();"><i class="glyphicon glyphicon-upload"></i>&nbsp;&nbsp;上传文档</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"
															class="btn btn-primary" onclick="deleteUploaderByIds();"> <i class="glyphicon glyphicon-trash"></i>&nbsp;&nbsp;删 除
														</a> <span class="pull-right file-toolbar-buttons listbtn"> <a href="javascript:;" data-type="large"><i class="glyphicon glyphicon-th-large"></i></a> <a href="javascript:;" data-type="list" class="active"><i class="glyphicon glyphicon-th-list"></i></a>
														</span>
													</div>
													<ul class="file-list clearfix fileList">
														<div id="myProjectUploader" style="height: 500px">
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
																					<img src="${nvix}/vixntcommon/base/images/icon-01.png">
																					<div class="file-layer">
																						${entity.creator } 创建于 ${fn:substring(entity.createTime, 0,19)}
																						<div class="clearfix">
																							<div class="pull-right layer-buttons">
																								<a href="javascript:;" class="comment-btn" onclick="downloadUploader('${entity.id }');"><i class="fa fa-download"></i></a> <a href="#" onclick="deleteUploaderById('${entity.id }');"><i class="fa fa-trash-o"></i></a>
																							</div>
																						</div>
																					</div>
																				</div>
																				<div class="file-line">
																					<div class="file-title">${entity.fileName }</div>
																					<div class="file-desc">${entity.creator }创建于${fn:substring(entity.createTime, 0,19)}</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</li>
															</s:iterator>
														</div>
													</ul>
													<div class="text-center">
														<ul class="pagination" id="myUploaderPage"></ul>
													</div>
												</div>
												<div class="col-sm-12 col-md-12 col-lg-2 slidebox no-padding">
													<div class="file-menu">
														<div class="file-menu-item">
															<ul class="file-menu-list">
																<s:if test="projectList.size > 0">
																	<s:iterator value="projectList" var="entity" status="s">
																		<li><a href="#" data-toggle="tab" aria-expanded="true" onclick="goProjectUploader('${entity.id }');"><i class="fa fa-puzzle-piece"></i>${entity.projectName }</a></li>
																	</s:iterator>
																</s:if>
																<s:else>
																	<li><a href="#" data-toggle="tab" aria-expanded="true">暂无项目</a></li>
																</s:else>
															</ul>
														</div>
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
		<div class="superbox-show" style="height: 300px; display: none"></div>
	</div>
</div>
<script type="text/javascript">
	$.jqPaginator('#myWorkLogPage', {
		totalPages :${pager.totalPage} ,
		visiblePages :${pager.pageSize},
		currentPage : ${pager.pageNo},
		prev : '<li class="prev"><a href="javascript:;">上一页</a></li>',
		next : '<li class="next"><a href="javascript:;">下一页</a></li>',
		page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
		onPageChange : function(num, type) {
		$.ajax({
			url : '${nvix}/nvixnt/nvixFileAction!goUploaderPager.action?pageNo=' + num,
			cache : false,
			success : function(html) {
				$("#sdaily").html(html);
				show_buttons();
			}
			});
		}
		});
	$.jqPaginator('#myUploaderPage', {
		totalPages :${pager.totalPage} ,
		visiblePages :${pager.pageSize},
		currentPage : ${pager.pageNo},
		prev : '<li class="prev"><a href="javascript:;">上一页</a></li>',
		next : '<li class="next"><a href="javascript:;">下一页</a></li>',
		page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
		onPageChange : function(num, type) {
		$.ajax({
			url : '${nvix}/nvixnt/nvixFileAction!goProjectUploaderPager.action?pageNo=' + num,
			cache : false,
			success : function(html) {
				$("#myProjectUploader").html(html);
				show_buttons();
			}
			});
		}
		});
	$("#uploaderForm").validationEngine();
	//添加附件
	function goAddUploader() {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixFileAction!goAddUploader.action', "uploaderForm", "上传文档", 550, 350, null, null, function() {
			loadContent('', '${nvix}/nvixnt/nvixFileAction!goList.action');
		});
	};
	function deleteUploaderByIds() {
		var chk_value = '';
		$('input[name="checkbox"]:checked').each(function() {
			chk_value +="," + $(this).val();
		});
		if (chk_value != null && chk_value != '') {
			$.ajax({
			url : '${nvix}/nvixnt/nvixFileAction!checkDeclassified.action?ids=' + chk_value,
			cache : false,
			success : function(tag) {
				  if (tag == '1') {
					deleteEntityByConfirm('${nvix}/nvixnt/nvixFileAction!deleteUploaderByIds.action?ids=' + chk_value, '是否删除文档?',null,null,function (){
						loadContent('','${nvix}/nvixnt/nvixFileAction!goList.action');
					});
				} else {
					layer.alert("没有删除该文件的权限!");
				} 
			}
			});
		} else {
			layer.alert("请选择要删除的数据!");
		}
	};
	function deleteUploaderById(id) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixFileAction!checkDeclassified.action?ids=' + id,
		cache : false,
		success : function(tag) {
			 if (tag == '1') {
				deleteEntityByConfirm('${nvix}/nvixnt/nvixFileAction!deleteUploaderByIds.action?ids=' + id, '是否删除附件?',null,null,function (){
					loadContent('','${nvix}/nvixnt/nvixFileAction!goList.action');
				});
			} else {
				layer.alert("没有删除该文件的权限!");
			} 
		}
		});
	};
	function downloadUploader(id) {
		var url = "${nvix}/nvixnt/nvixFileAction!downloadUploader.action?id=" + id;
		window.open(url);
	};

	function searchDaily(id) {
		$.ajax({
			url : '${nvix}/nvixnt/nvixFileAction!goUploaderPager.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#sdaily").html(html);
				show_buttons();
			}
			});
	};
	function goProjectUploader(id) {
		$.ajax({
			url : '${nvix}/nvixnt/nvixFileAction!goProjectUploaderPager.action?projectId=' + id,
			cache : false,
			success : function(html) {
				$("#myProjectUploader").html(html);
				show_buttons();
			}
			});
	};
	$(document).ready(function() {
		$('.superbox').SuperBox();
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
		pageSetUp();
	});
	
	function show_buttons(){
		$('.fileList li').hover(function() {
			if ($(this).closest('.file-large').length > 0) {
				$(this).addClass('show-layer');
			} else {
				$(this).addClass('show-buttons');
			}
		}, function() {
			$(this).removeClass('show-layer').removeClass('show-buttons');
		});
	}
</script>
