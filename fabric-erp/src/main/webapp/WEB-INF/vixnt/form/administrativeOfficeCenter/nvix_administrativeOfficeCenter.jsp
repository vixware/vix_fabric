<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-file-text-o "></i> 表单管理 <span>> 行政办公中心 </span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>表单</h2>
			<ul class="nav nav-tabs in pull-right" id="myTab">
				<li class="active"><a data-toggle="tab" href="#s1"><span class="hidden-mobile hidden-tablet">单据类型</span></a></li>
			</ul>
		</header>
		<div>
			<div class="widget-body no-padding clearfix file-wrap">
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in clearfix active" id="s1">
						<div class="tab-content">
							<s:iterator value="businessFormTemplateMap" id="column">
								<div class="col-sm-12 col-md-12 col-lg-10 list-warp tab-pane" id="<s:property value="#column.key" />">
									<div class="file-toolbar clearfix">
										<span class="smart-form pull-left"></span> 
										<a data-toggle="modal" href="#" class="btn btn-primary" onclick="loadContent('','${nvix}/vixntcommon/form/businessFormdata_list.jsp');">
										<i class="glyphicon glyphicon-upload"></i> 我的表单</a> 
										<a data-toggle="modal" href="#addFile" class="btn btn-default" onclick="loadContent('','${nvix}/nvixnt/vreport/nvixFormAction!goList.action');">
										<i class="fa fa-plus-circle"></i> 我的代办</a> 
										<span class="pull-right file-toolbar-buttons listbtn"> 
											<a href="javascript:;" data-type="large"><i class="glyphicon glyphicon-th-large"></i></a> 
											<a href="javascript:;" data-type="list" class="active"><i class="glyphicon glyphicon-th-list"></i></a>
										</span>
									</div>
									<ul class="file-list clearfix fileList" id="fileList" style="height: 450px">
										<s:iterator value="#column.value" id="col" status="st">
											<li>
												<div class="file-list-list clearfix">
													<div class="pull-right file-buttons">
														<a href="#" onclick="goBusinessFormTemplate('<s:property value="id" />');"><i class="fa fa-edit"></i></a>
													</div>
													<div class="file-body">
														<div class="clearfix">
															<span class="smart-form pull-left"></span>
															<div class="file-icon">
																<img src="${nvix}/vixntcommon/base/images/icon-01.png">
																<div class="file-layer">
																	<div class="clearfix">
																		<div class="pull-right layer-buttons">
																			<a href="#" onclick="goBusinessFormTemplate('<s:property value="id" />');"><i class="fa fa-edit"></i></a>
																		</div>
																	</div>
																</div>
															</div>
															<div class="file-line">
																<div class="file-title">
																	<s:property value="templateName" />
																</div>
																<div class="file-desc">
																	<s:property value="templateName" />
																</div>
															</div>
														</div>
													</div>
												</div>
											</li>
										</s:iterator>
									</ul>
								</div>
							</s:iterator>
							<div class="col-sm-12 col-md-12 col-lg-10 list-warp tab-pane active" id="t3">
								<div class="file-toolbar clearfix">
									<span class="smart-form pull-left"></span> 
									<a data-toggle="modal" href="#" class="btn btn-primary" onclick="loadContent('','${nvix}/vixntcommon/form/businessFormdata_list.jsp');">
									<i class="glyphicon glyphicon-upload"></i> 我的表单</a> 
									<a data-toggle="modal" href="#addFile" class="btn btn-default" onclick="loadContent('','${nvix}/nvixnt/vreport/nvixFormAction!goList.action');">
									<i class="fa fa-plus-circle"></i> 我的代办</a> 
									<span class="pull-right file-toolbar-buttons listbtn"> 
										<a href="javascript:;" data-type="large"><i class="glyphicon glyphicon-th-large"></i></a> 
										<a href="javascript:;" data-type="list" class="active"><i class="glyphicon glyphicon-th-list"></i></a>
									</span>
								</div>
								<ul class="file-list clearfix fileList" id="fileList" style="height: 450px">
									<s:iterator value="businessFormTemplateList" id="col" status="st">
										<li>
											<div class="file-list-list clearfix">
												<div class="pull-right file-buttons">
													<a href="#" onclick="goBusinessFormTemplate('<s:property value="id" />');"><i class="fa fa-edit">&nbsp;&nbsp;表单填写</i></a>
												</div>
												<div class="file-body">
													<div class="clearfix">
														<span class="smart-form pull-left"></span>
														<div class="file-icon">
															<img src="${nvix}/vixntcommon/base/images/icon-01.png">
															<div class="file-layer">
																<div class="clearfix">
																	<div class="pull-right layer-buttons">
																		<a href="#" onclick="goBusinessFormTemplate('<s:property value="id" />');"><i class="fa fa-pencil">&nbsp;&nbsp;表单填写</i></a>
																	</div>
																</div>
															</div>
														</div>
														<div class="file-line">
															<div class="file-title">
																<s:property value="templateName" />
															</div>
															<div class="file-desc">
																<s:property value="templateName" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</li>
									</s:iterator>
								</ul>
							</div>
						</div>
						<div class="col-sm-12 col-md-12 col-lg-2 slidebox no-padding">
							<div class="file-menu">
								<div class="file-menu-item">
									<a href="javascript:;" class="file-menu-link"><i class="fa fa-star"></i>常用单据</a>
									<ul class="file-menu-list">
										<li class="active"><a href="#t3" data-toggle="tab" aria-expanded="true"><i class="fa fa-folder-o"></i>全部单据</a></li>
										<s:iterator value="businessFormTypeList" var="entity" status="s">
											<li><a href="#${entity.id }" data-toggle="tab" aria-expanded="true"><i class="fa fa-folder-o"></i>${entity.typeName }</a></li>
										</s:iterator>
									</ul>
								</div>
								<div class="file-menu-item">
									<a href="javascript:;" class="file-menu-link"><i class="fa fa-trash-o"></i>回收站</a>
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
	function goBusinessFormTemplate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vreport/nvixAdministrativeAction!goBusinessFormTemplate.action?businessFormTemplateId=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	$(document).ready(function() {
		// list
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
</script>