<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">

	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-desktop fa-fw "></i> <a data-toggle="modal" href="#" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListNews.action');">任务与计划 </a> <span>> <a data-toggle="modal" href="#" onclick="loadContent('${nvix}/nvixnt/redirectAction!goListProjectsTask.action');">项目任务管理 </a>
				</span> <span>> 任务时间表 </span>
			</h1>
		</div>
	</div>
	<!-- widget grid -->
	<section id="" class="">

		<!-- row -->
		<div class="row">

			<!-- NEW WIDGET START -->
			<article class="col-sm-12 col-md-12 col-lg-6">

				<div class="jarviswidget jarviswidget-color-orange" id="" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-lg fa-calendar"></i>
						</span>
						<h2>时间表</h2>
					</header>
					<div>
						<div class="jarviswidget-editbox"></div>
						<div class="widget-body">
							<div class="widget-body-toolbar bg-color-white">
								<form class="form-inline" role="form">
									<div class="row">
										<div class="col-sm-12 col-md-10">
											<div class="form-group">
												<label class="sr-only">Task title</label> <input type="email" class="form-control input-sm" placeholder="My Task">
											</div>
											<div class="form-group">
												<label class="sr-only">Pick Week</label> <select class="form-control input-sm">
													<option>Week 1</option>
													<option>Week 2</option>
												</select>
											</div>
											<div class="form-group">
												<label class="sr-only">Days</label> <select class="form-control input-sm">
													<option>Monday</option>
													<option>Tuesday</option>
													<option>Wednesday</option>
													<option>Thursday</option>
													<option>Friday</option>
													<option>Saturday</option>
													<option>Sunday</option>
												</select>
											</div>
										</div>
										<div class="col-sm-12 col-md-2 text-align-right">
											<button type="submit" class="btn btn-warning btn-xs">
												<i class="fa fa-plus"></i> Add
											</button>
										</div>
									</div>
								</form>
							</div>
							<div class="tree">
								<ul>
									<li><span><i class="fa fa-lg fa-calendar"></i> 2013, Week 2</span>
										<ul>
											<li><span class="label label-success"><i class="fa fa-lg fa-plus-circle"></i> Monday, January 7: 8.00 hours</span>
												<ul>
													<li><span><i class="fa fa-clock-o"></i> 8.00</span> &ndash; <a href="javascript:void(0);">Changed CSS to accomodate...</a></li>
												</ul></li>
											<li><span class="label label-success"><i class="fa fa-lg fa-minus-circle"></i> Tuesday, January 8: 8.00 hours</span>
												<ul>
													<li><span><i class="fa fa-clock-o"></i> 6.00</span> &ndash; <a href="javascript:void(0);">Altered code...</a></li>
													<li><span><i class="fa fa-clock-o"></i> 2.00</span> &ndash; <a href="javascript:void(0);">Simplified our approach to...</a></li>
												</ul></li>
											<li><span class="label label-warning"><i class="fa fa-lg fa-minus-circle"></i> Wednesday, January 9: 6.00 hours</span>
												<ul>
													<li><span><i class="fa fa-clock-o"></i> 3.00</span> &ndash; <a href="javascript:void(0);">Fixed bug caused by...</a></li>
													<li><span><i class="fa fa-clock-o"></i> 3.00</span> &ndash; <a href="javascript:void(0);">Comitting latest code to Git...</a></li>
												</ul></li>
											<li><span class="label label-danger"><i class="fa fa-lg fa-minus-circle"></i> Wednesday, January 9: 4.00 hours</span>
												<ul>
													<li><span><i class="fa fa-clock-o"></i> 2.00</span> &ndash; <a href="javascript:void(0);">Create component that...</a></li>
												</ul></li>
										</ul></li>
									<li><span><i class="fa fa-lg fa-calendar"></i> 2013, Week 3</span>
										<ul>
											<li><span class="label label-success"><i class="fa fa-lg fa-minus-circle"></i> Monday, January 14: 8.00 hours</span>
												<ul>
													<li><span><i class="fa fa-clock-o"></i> 7.75</span> &ndash; <a href="javascript:void(0);">Writing documentation...</a></li>
													<li><span><i class="fa fa-clock-o"></i> 0.25</span> &ndash; <a href="javascript:void(0);">Reverting code back to...</a></li>
												</ul></li>
										</ul></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</article>
			<article class="col-sm-12 col-md-12 col-lg-6">
				<div class="jarviswidget jarviswidget-color-blue" id="wid-id-1" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-sitemap"></i>
						</span>
						<h2>项目结构</h2>

					</header>
					<div>
						<div class="jarviswidget-editbox"></div>
						<div class="widget-body">
							<div class="tree smart-form">
								<ul>
									<li><span><i class="fa fa-lg fa-folder-open"></i> Parent</span>
										<ul>
											<li><span><i class="fa fa-lg fa-plus-circle"></i> Administrators</span>
												<ul>
													<li style="display: none"><span> <label class="checkbox inline-block"> <input type="checkbox" name="checkbox-inline"> <i></i>Michael.Jackson
														</label>
													</span></li>
													<li style="display: none"><span> <label class="checkbox inline-block"> <input type="checkbox" checked="checked" name="checkbox-inline"> <i></i>Sunny.Ahmed
														</label>
													</span></li>
													<li style="display: none"><span> <label class="checkbox inline-block"> <input type="checkbox" checked="checked" name="checkbox-inline"> <i></i>Jackie.Chan
														</label>
													</span></li>
												</ul></li>
											<li><span><i class="fa fa-lg fa-minus-circle"></i> Child</span>
												<ul>
													<li><span><i class="icon-leaf"></i> Grand Child</span></li>
													<li><span><i class="icon-leaf"></i> Grand Child</span></li>
													<li><span><i class="fa fa-lg fa-plus-circle"></i> Grand Child</span>
														<ul>
															<li style="display: none"><span><i class="fa fa-lg fa-plus-circle"></i> Great Grand Child</span>
																<ul>
																	<li style="display: none"><span><i class="icon-leaf"></i> Great great Grand Child</span></li>
																	<li style="display: none"><span><i class="icon-leaf"></i> Great great Grand Child</span></li>
																</ul></li>
															<li style="display: none"><span><i class="icon-leaf"></i> Great Grand Child</span></li>
															<li style="display: none"><span><i class="icon-leaf"></i> Great Grand Child</span></li>
														</ul></li>
												</ul></li>
										</ul></li>
									<li><span><i class="fa fa-lg fa-folder-open"></i> Parent2</span>
										<ul>
											<li><span><i class="icon-leaf"></i> Child</span></li>
										</ul></li>
								</ul>
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
		$('.tree > ul').attr('role', 'tree').find('ul').attr('role', 'group');
		$('.tree').find('li:has(ul)').addClass('parent_li').attr('role', 'treeitem').find(' > span').attr('title', 'Collapse this branch').on('click', function(e) {
			var children = $(this).parent('li.parent_li').find(' > ul > li');
			if (children.is(':visible')) {
				children.hide('fast');
				$(this).attr('title', 'Expand this branch').find(' > i').removeClass().addClass('fa fa-lg fa-plus-circle');
			} else {
				children.show('fast');
				$(this).attr('title', 'Collapse this branch').find(' > i').removeClass().addClass('fa fa-lg fa-minus-circle');
			}
			e.stopPropagation();
		});
	})
</script>