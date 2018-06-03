<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/vixntcommon/page/resource_js.jsp"%>
<%@ include file="/vixntcommon/page/resource_css.jsp"%>
</head>
<body class="">
	<s:include value="header.jsp" />
	<aside id="left-panel"> <!-- User info -->
	<div class="login-info">
		<span> <!-- User image size is adjusted inside CSS, it should stay as it --> <a href="javascript:void(0);" id="show-shortcut" data-action="toggleShortcut"> <img src="img/avatars/sunny.png" alt="me" class="online" /> <span> john.doe </span> <i class="fa fa-angle-down"></i>
		</a>

		</span>
	</div>
	<!-- end user info --> <!-- NAVIGATION : This navigation is also responsive--> <nav>
	<ul>
		<li class="chat-users top-menu-invisible"><a href="#"><i class="fa fa-lg fa-fw fa-comment-o"><em class="bg-color-pink flash animated">!</em></i> <span class="menu-item-parent">Smart Chat API <sup>beta</sup></span></a>
			<ul>
				<li>
					<!-- DISPLAY USERS -->
					<div class="display-users">

						<input class="form-control chat-user-filter" placeholder="Filter" type="text"> <a href="#" class="usr" data-chat-id="cha1" data-chat-fname="Sadi" data-chat-lname="Orlaf" data-chat-status="busy" data-chat-alertmsg="Sadi Orlaf is in a meeting. Please do not disturb!" data-chat-alertshow="true" data-rel="popover-hover"
							data-placement="right" data-html="true" data-content="
											<div class='usr-card'>
												<img src='img/avatars/5.png' alt='Sadi Orlaf'>
												<div class='usr-card-content'>
													<h3>Sadi Orlaf</h3>
													<p>Marketing Executive</p>
												</div>
											</div>
										"> <i></i>Sadi Orlaf
						</a> <a href="#" class="usr" data-chat-id="cha2" data-chat-fname="Jessica" data-chat-lname="Dolof" data-chat-status="online" data-chat-alertmsg="" data-chat-alertshow="false" data-rel="popover-hover" data-placement="right" data-html="true"
							data-content="
											<div class='usr-card'>
												<img src='img/avatars/1.png' alt='Jessica Dolof'>
												<div class='usr-card-content'>
													<h3>Jessica Dolof</h3>
													<p>Sales Administrator</p>
												</div>
											</div>
										"> <i></i>Jessica Dolof
						</a> <a href="#" class="usr" data-chat-id="cha3" data-chat-fname="Zekarburg" data-chat-lname="Almandalie" data-chat-status="online" data-rel="popover-hover" data-placement="right" data-html="true"
							data-content="
											<div class='usr-card'>
												<img src='img/avatars/3.png' alt='Zekarburg Almandalie'>
												<div class='usr-card-content'>
													<h3>Zekarburg Almandalie</h3>
													<p>Sales Admin</p>
												</div>
											</div>
										"> <i></i>Zekarburg Almandalie
						</a> <a href="#" class="usr" data-chat-id="cha4" data-chat-fname="Barley" data-chat-lname="Krazurkth" data-chat-status="away" data-rel="popover-hover" data-placement="right" data-html="true"
							data-content="
											<div class='usr-card'>
												<img src='img/avatars/4.png' alt='Barley Krazurkth'>
												<div class='usr-card-content'>
													<h3>Barley Krazurkth</h3>
													<p>Sales Director</p>
												</div>
											</div>
										"> <i></i>Barley Krazurkth
						</a> <a href="#" class="usr offline" data-chat-id="cha5" data-chat-fname="Farhana" data-chat-lname="Amrin" data-chat-status="incognito" data-rel="popover-hover" data-placement="right" data-html="true"
							data-content="
											<div class='usr-card'>
												<img src='img/avatars/female.png' alt='Farhana Amrin'>
												<div class='usr-card-content'>
													<h3>Farhana Amrin</h3>
													<p>Support Admin <small><i class='fa fa-music'></i> Playing Beethoven Classics</small></p>
												</div>
											</div>
										">
								<i></i>Farhana Amrin (offline)
						</a> <a href="#" class="usr offline" data-chat-id="cha6" data-chat-fname="Lezley" data-chat-lname="Jacob" data-chat-status="incognito" data-rel="popover-hover" data-placement="right" data-html="true"
							data-content="
											<div class='usr-card'>
												<img src='img/avatars/male.png' alt='Lezley Jacob'>
												<div class='usr-card-content'>
													<h3>Lezley Jacob</h3>
													<p>Sales Director</p>
												</div>
											</div>
										"> <i></i>Lezley Jacob (offline)
						</a> <a href="ajax/chat.html" class="btn btn-xs btn-default btn-block sa-chat-learnmore-btn">About the API</a>
					</div> <!-- END DISPLAY USERS -->
				</li>
			</ul></li>
	</ul>
	</nav> <span class="minifyme" data-action="minifyMenu"> <i class="fa fa-arrow-circle-left hit"></i>
	</span> </aside>
	<div id="main" role="main">
		<div id="ribbon">
			<span class="ribbon-button-alignment"> <span id="refresh" class="btn btn-ribbon" data-action="resetWidgets" data-title="refresh" rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true"> <i class="fa fa-refresh"></i>
			</span>
			</span>
			<ol class="breadcrumb">
				<li>首页</li>
				<li>返回</li>
			</ol>
		</div>
		<div id="content">
			<div class="row hidden-mobile">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa-fw fa fa-puzzle-piece"></i> 沟通管理 <span>> 项目沟通 </span>
					</h1>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
					<div class="page-title">
						<a class="btn btn-default" data-toggle="modal" href="#testid">Upload</a> <a href="javascript:void(0);" class="btn btn-default">Load Library</a>
					</div>
				</div>
			</div>
			<!-- row -->
			<div class="row">
				<!-- SuperBox -->
				<div class="col-sm-12">
					<!-- widget grid -->
					<section id="" class=""> <!-- row -->
					<div class="row">
						<article class="col-sm-12 col-md-12 col-lg-12"> <!-- new widget -->
						<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
							<header>
							<h2>项目沟通</h2>
							</header>
							<!-- widget div-->
							<div>
								<div class="widget-body no-padding">
									<div class="col-sm-12 col-md-12 col-lg-8">
										<br>
											<div class="row">
												<div class="col-sm-12 col-md-12 col-lg-12  task-detail-title">
													<span class="pull-right"><span class="badge"><i class="fa fa-user"></i> 17</span></span> <span class="text-danger">#genenral</span><span class=" text-muted">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh</span>
												</div>
											</div>
											<ul class="media-list task-detail-list">
												<li class="media clearfix"><a class="pull-left" href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/sunny-big.png" class="btn-circle no-padding btn-xl"></a>
													<div class="media-body">
														<h4 class="media-heading">
															<span class="txt-color-blue">Samantha Stosur</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small class="text-muted">12:12;09</small>
														</h4>
														<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat commodo consequat.</p>
													</div></li>
												<li class="media clearfix"><a class="pull-left" href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/sunny-big.png" class="btn-circle no-padding btn-xl"></a>
													<div class="media-body">
														<h4 class="media-heading">
															<span class="txt-color-blue">Samantha Stosur</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small class="text-muted">12:12;09</small>
														</h4>
														<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat commodo consequat.</p>
													</div></li>
												<li class="media clearfix"><a class="pull-left" href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/sunny-big.png" class="btn-circle no-padding btn-xl"></a>
													<div class="media-body">
														<h4 class="media-heading">
															<span class="txt-color-blue">Samantha Stosur</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small class="text-muted">12:12;09</small>
														</h4>
														<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat commodo consequat.</p>
													</div></li>
												<li class="media clearfix"><a class="pull-left" href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/sunny-big.png" class="btn-circle no-padding btn-xl"></a>
													<div class="media-body">
														<h4 class="media-heading">
															<span class="txt-color-blue">Samantha Stosur</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small class="text-muted">12:12;09</small>
														</h4>
														<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat commodo consequat.</p>
													</div></li>
												<li class="media clearfix"><a class="pull-left" href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/sunny-big.png" class="btn-circle no-padding btn-xl"></a>
													<div class="media-body">
														<h4 class="media-heading">
															<span class="txt-color-blue">Samantha Stosur</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small class="text-muted">12:12;09</small>
														</h4>
														<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat commodo consequat.</p>
													</div></li>
												<li class="media clearfix"><a class="pull-left" href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/sunny-big.png" class="btn-circle no-padding btn-xl"></a>
													<div class="media-body">
														<h4 class="media-heading">
															<span class="txt-color-blue">Samantha Stosur</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small class="text-muted">12:12;09</small>
														</h4>
														<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat commodo consequat.</p>
													</div></li>
												<li class="media clearfix"><a class="pull-left" href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/sunny-big.png" class="btn-circle no-padding btn-xl"></a>
													<div class="media-body">
														<h4 class="media-heading">
															<span class="txt-color-blue">Samantha Stosur</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small class="text-muted">12:12;09</small>
														</h4>
														<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat commodo consequat.</p>
													</div></li>
											</ul> <br>
												<div class="chat-box">
													<!-- widget edit box -->
													<div class="jarviswidget-editbox">
														<!-- This area used as dropdown edit box -->

													</div>
													<!-- end widget edit box -->

													<!-- widget div-->
													<div>
														<!-- widget edit box -->
														<div class="jarviswidget-editbox">
															<div>
																<label>Title:</label> <input type="text">
															</div>
														</div>
														<!-- end widget edit box -->

														<div class="widget-body widget-hide-overflow no-padding-bottom">
															<!-- content goes here -->

															<!-- CHAT CONTAINER -->
															<div id="chat-container">
																<span class="chat-list-open-close"><i class="fa fa-user"></i><b>!</b></span>

																<div class="chat-list-body custom-scroll">
																	<ul id="chat-users">
																		<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/5.png" alt="">Robin Berry <span class="badge badge-inverse">23</span><span class="state"><i class="fa fa-circle txt-color-green pull-right"></i></span></a></li>
																		<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Mark Zeukartech <span class="state"><i class="last-online pull-right">2hrs</i></span></a></li>
																		<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Belmain Dolson <span class="state"><i class="last-online pull-right">45m</i></span></a></li>
																		<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Galvitch Drewbery <span class="state"><i class="fa fa-circle txt-color-green pull-right"></i></span></a></li>
																		<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Sadi Orlaf <span class="state"><i class="fa fa-circle txt-color-green pull-right"></i></span></a></li>
																		<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Markus <span class="state"><i class="last-online pull-right">2m</i></span></a></li>
																		<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/sunny.png" alt="">Sunny <span class="state"><i class="last-online pull-right">2m</i></span></a></li>
																		<li><a href="javascript:void(0);"><img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="">Denmark <span class="state"><i class="last-online pull-right">2m</i></span></a></li>
																	</ul>
																</div>
																<div class="chat-list-footer">
																	<div class="control-group">
																		<form class="smart-form">
																			<section> <label class="input"> <input type="text" id="filter-chat-list" placeholder="Filter"></label> </section>
																		</form>
																	</div>
																</div>
															</div>
															<!-- CHAT BODY -->
															<div id="chat-body" class="chat-body custom-scroll">
																<ul>
																	<li class="message"><img src="${nvix}/vixntcommon/base/img/avatars/5.png" class="online" alt="">
																			<div class="message-text">
																				<time> 2014-01-13 </time>
																				<a href="javascript:void(0);" class="username">Sadi Orlaf</a> Hey did you meet the new board of director? He's a bit of an arse if you ask me...anyway here is the report you requested. I am off to launch with Lisa and Andrew, you wanna join?
																				<p class="chat-file row">
																					<b class="pull-left col-sm-6"> <!--<i class="fa fa-spinner fa-spin"></i>--> <i class="fa fa-file"></i> report-2013-demographic-report-annual-earnings.xls
																					</b> <span class="col-sm-6 pull-right"> <a href="javascript:void(0);" class="btn btn-xs btn-default">cancel</a> <a href="javascript:void(0);" class="btn btn-xs btn-success">save</a>
																					</span>
																				</p>
																				<p class="chat-file row">
																					<b class="pull-left col-sm-6"> <i class="fa fa-ok txt-color-green"></i> tobacco-report-2012.doc
																					</b> <span class="col-sm-6 pull-right"> <a href="javascript:void(0);" class="btn btn-xs btn-primary">open</a>
																					</span>
																				</p>
																			</div></li>
																	<li class="message"><img src="${nvix}/vixntcommon/base/img/avatars/sunny.png" class="online" alt="">
																			<div class="message-text">
																				<time> 2014-01-13 </time>
																				<a href="javascript:void(0);" class="username">John Doe</a> Haha! Yeah I know what you mean. Thanks for the file Sadi! <i class="fa fa-smile-o txt-color-orange"></i>
																			</div></li>
																</ul>
															</div>
															<!-- CHAT FOOTER -->
															<div class="chat-footer">
																<div class="textarea-div">
																	<div class="typearea">
																		<textarea placeholder="Write a reply..." id="textarea-expand" class="custom-scroll"></textarea>
																	</div>
																</div>
																<!-- CHAT REPLY/SEND -->
																<span class="textarea-controls">
																	<button class="btn btn-sm btn-primary pull-right">Reply</button> <span class="pull-right smart-form" style="margin-top: 3px; margin-right: 10px;"> <label class="checkbox pull-right"> <input type="checkbox" name="subscription" id="subscription"> <i></i>Press <strong> ENTER </strong> to send </label>
																</span> <a href="javascript:void(0);" class="pull-left"><i class="fa fa-camera fa-fw fa-lg"></i></a>
																</span>
															</div>
															<!-- end content -->
														</div>
													</div>
													<!-- end widget div -->
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
													<span class="pull-left"><img src="images/quot.png"> <img src="${nvix}/vixntcommon/base/img/avatars/1.png" class="btn-circle no-padding"></span>
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
						<!-- end widget --> </article>
					</div>
					<!-- end row --> </section>
					<!-- end widget grid -->
				</div>
				<!-- /SuperBox -->
				<div class="superbox-show" style="height: 300px; display: none"></div>
			</div>
		</div>
	</div>
	<script src="${nvix}/vixntcommon/js/demo.min.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/css/demo.min.css"></link>
	<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/css/your_style.css"></link>
	<s:include value="footer.jsp" />
</body>
</html>