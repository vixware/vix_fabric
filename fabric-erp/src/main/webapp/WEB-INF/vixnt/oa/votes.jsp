<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/common/oa/votes.js"></script>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 投票
			</h1>
		</div>
	</div>
	<section id="" class="">
		<!-- row -->
		<div class="row">
			<article class="col-sm-12 col-md-12 col-lg-6">
				<!-- new widget -->
				<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
					<header>
					<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>投票管理</h2>

						<ul class="nav nav-tabs pull-right in" id="myTab">
							<li class="active"><a data-toggle="tab" href="#s1"><span class="hidden-mobile hidden-tablet">创建投票</span></a></li>

							<li><a data-toggle="tab" href="#s2"><span class="hidden-mobile hidden-tablet">进行中</span></a></li>

							<li><a data-toggle="tab" href="#s3"><span class="hidden-mobile hidden-tablet">待发布</span></a></li>
						</ul>
					</header>
					<div>
						<div class="jarviswidget-editbox"></div>
						<div class="widget-body no-padding">
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade active in" id="s1">
									<div class="table-responsive">
										<table class="table table-striped table-hover">
											<thead>
												<tr>
													<th>发布范围</th>
													<th>标题</th>
													<th>开始时间</th>
													<th>结束时间</th>
													<th>状态</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">开发部，测试部</a></td>
													<td>项目问题</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td>待发布</td>
													<td class="text-primary">发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">开发部，测试部</a></td>
													<td>项目问题</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td>待发布</td>
													<td class="text-primary">发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">开发部，测试部</a></td>
													<td>项目问题</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td>待发布</td>
													<td class="text-primary">发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">开发部，测试部</a></td>
													<td>项目问题</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td>待发布</td>
													<td class="text-primary">发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">开发部，测试部</a></td>
													<td>项目问题</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td>待发布</td>
													<td class="text-primary">发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">开发部，测试部</a></td>
													<td>项目问题</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td>待发布</td>
													<td class="text-primary">发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">开发部，测试部</a></td>
													<td>项目问题</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td>待发布</td>
													<td class="text-primary">发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">开发部，测试部</a></td>
													<td>项目问题</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td>待发布</td>
													<td class="text-primary">发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">开发部，测试部</a></td>
													<td>项目问题</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td>待发布</td>
													<td class="text-primary">发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">开发部，测试部</a></td>
													<td>项目问题</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td>待发布</td>
													<td class="text-primary">发布</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div class="tab-pane fade in" id="s2">
									<div class="table-responsive">

										<table class="table table-striped table-hover">
											<thead>
												<tr>
													<th>标题</th>
													<th>发布范围</th>
													<th>结束时间</th>
													<th>状态</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">正在进行</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">正在进行</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">正在进行</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">正在进行</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">正在进行</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">正在进行</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">正在进行</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">正在进行</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">正在进行</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">正在进行</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">正在进行</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div class="tab-pane fade in" id="s3">
									<div class="table-responsive">

										<table class="table table-striped table-hover">
											<thead>
												<tr>
													<th>标题</th>
													<th>发布范围</th>
													<th>开始时间</th>
													<th>结束时间</th>
													<th>状态</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">待发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">待发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">待发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">待发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">待发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">待发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">待发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">待发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">待发布</td>
												</tr>
												<tr>
													<td><a data-toggle="modal" href="#taskid" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">任务主题</a></td>
													<td>开发部，测试部</td>
													<td>2015-10-11 12：00</td>
													<td>2015-10-11 12：00</td>
													<td class="text-primary">待发布</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</article>
			<article class="col-sm-12 col-md-12 col-lg-6">
				<!-- Widget ID (each widget will need unique ID)-->
				<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-2" data-widget-editbutton="false">
					<header>
						<h2>投票情况</h2>
					</header>
					<div>
						<!-- widget edit box -->
						<div class="jarviswidget-editbox"></div>
						<div class="widget-body no-padding">
							<div class="table-responsive">
								<table class="table table-striped table-hover">
									<thead>
										<tr>
											<th>标题</th>
											<th>投票进度</th>
											<th>人员</th>
											<th>结束时间</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><a data-toggle="modal" href="#taskid">水稻抗体超市”宣传画</a></td>
											<td><div class="padding-5">
													<p class="txt-color-darken font-sm no-margin">50%</p>
													<div class="progress progress-micro no-margin">
														<div class="progress-bar progress-bar-success" style="width: 50%;"></div>
													</div>
												</div></td>
											<td><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"></td>
											<td>2015-10-11 12:00</td>
											<td>进行中</td>
											<td><a href="#">投票</a></td>
										</tr>
										<tr>
											<td><a data-toggle="modal" href="#taskid">水稻抗体超市”宣传画</a></td>
											<td><div class="padding-5">
													<p class="txt-color-darken font-sm no-margin">50%</p>
													<div class="progress progress-micro no-margin">
														<div class="progress-bar progress-bar-success" style="width: 50%;"></div>
													</div>
												</div></td>
											<td><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"></td>
											<td>2015-10-11 12:00</td>
											<td>进行中</td>
											<td><a href="#">投票</a></td>
										</tr>
										<tr>
											<td><a data-toggle="modal" href="#taskid">水稻抗体超市”宣传画</a></td>
											<td><div class="padding-5">
													<p class="txt-color-darken font-sm no-margin">50%</p>
													<div class="progress progress-micro no-margin">
														<div class="progress-bar progress-bar-success" style="width: 50%;"></div>
													</div>
												</div></td>
											<td><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"></td>
											<td>2015-10-11 12:00</td>
											<td>进行中</td>
											<td><a href="#">投票</a></td>
										</tr>
										<tr>
											<td><a data-toggle="modal" href="#taskid">水稻抗体超市”宣传画</a></td>
											<td><div class="padding-5">
													<p class="txt-color-darken font-sm no-margin">50%</p>
													<div class="progress progress-micro no-margin">
														<div class="progress-bar progress-bar-success" style="width: 50%;"></div>
													</div>
												</div></td>
											<td><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"></td>
											<td>2015-10-11 12:00</td>
											<td>进行中</td>
											<td><a href="#">投票</a></td>
										</tr>
										<tr>
											<td><a data-toggle="modal" href="#taskid">水稻抗体超市”宣传画</a></td>
											<td><div class="padding-5">
													<p class="txt-color-darken font-sm no-margin">50%</p>
													<div class="progress progress-micro no-margin">
														<div class="progress-bar progress-bar-success" style="width: 50%;"></div>
													</div>
												</div></td>
											<td><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"></td>
											<td>2015-10-11 12:00</td>
											<td>进行中</td>
											<td><a href="#">投票</a></td>
										</tr>
										<tr>
											<td><a data-toggle="modal" href="#taskid">水稻抗体超市”宣传画</a></td>
											<td><div class="padding-5">
													<p class="txt-color-darken font-sm no-margin">50%</p>
													<div class="progress progress-micro no-margin">
														<div class="progress-bar progress-bar-success" style="width: 50%;"></div>
													</div>
												</div></td>
											<td><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"></td>
											<td>2015-10-11 12:00</td>
											<td>进行中</td>
											<td><a href="#">投票</a></td>
										</tr>
										<tr>
											<td><a data-toggle="modal" href="#taskid">水稻抗体超市”宣传画</a></td>
											<td><div class="padding-5">
													<p class="txt-color-darken font-sm no-margin">50%</p>
													<div class="progress progress-micro no-margin">
														<div class="progress-bar progress-bar-success" style="width: 50%;"></div>
													</div>
												</div></td>
											<td><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="avatars"></td>
											<td>2015-10-11 12:00</td>
											<td>进行中</td>
											<td><a href="#">投票</a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<div class="modal fade" id="taskid">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">任务主题</h4>
			</div>
			<div class="modal-body">
				<p>反馈内容</p>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
