<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="hidden" id="type" value="${customerAccount.type}" /> 
	<input type="hidden" id="id" name="customerAccount.id" value="${customerAccount.id}" /> 
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>>客户管理  </span><span>>客户视图 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('${customerAccount.id}','${customerAccount.type}');">
					<i class="fa fa-pencil"></i> 修改
				</button>
				<c:if test="${source == 'list'}">
					<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixCustomerAction!goList.action?syncTag=' +$('#type').val());">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
				<c:if test="${source == 'view'}">
					<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixCustomerAction!goCustomerList.action?syncTag=' +$('#type').val());">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
			</div>
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
										<!-- <div class="air air-bottom-right padding-10">
											<a href="javascript:void(0);" class="btn txt-color-white bg-color-red btn-sm"><i class="fa fa-heart"></i> 已关注</a>&nbsp; <a href="javascript:void(0);" class="btn txt-color-white bg-color-blue1 btn-sm"><i class="fa fa-check"></i> 成&nbsp;功</a>
										</div> -->
										<div class="air air-top-left padding-10">
											<h4 class="txt-color-white font-lg">${customerAccount.name}</h4>
										</div>
										
										<div class="carousel-inner">
											<div class="item active">
												<img src="${nvix}/vixntcommon/base/images/headerBG.jpg" alt="demo user">
											</div>
										</div>
									</div>
								</div>

								<div class="col-sm-12">
									<div class="row">
										<div class="col-sm-3 profile-pic">
											<s:if test="customerAccount.logo != null && customerAccount.logo != ''">
												<img src="${customerAccount.logo}" alt="" width="100px" height="100px">
											</s:if>
											<s:else>
												<img src="${nvix}/vixntcommon/base/images/logo.jpg" alt="">
											</s:else>
											<div class="padding-10">
												<h4 class="font-md">
													<strong>
														<c:if test="${customerAccount.employee != null}">
															${customerAccount.employee.name}
														</c:if>
													</strong> 
													<br> <small>客户所有者</small>
												</h4>
												<br> 
												<h4 class="font-md">
													<strong>${customerAccount.createTimeStr}</strong> <br> <small>创建时间</small>
												</h4>
												<br> 
												<h4 class="font-md">
													<c:if test="${customerAccount.creditLevel == 1}">
														<strong>高</strong>
													</c:if>
													<c:if test="${customerAccount.creditLevel == 2}">
														<strong>中</strong>
													</c:if>
													<c:if test="${customerAccount.creditLevel == 3}">
														<strong>低</strong>
													</c:if> 
													<br> <small>信用等级</small>
												</h4>
												<br> 
												<h4 class="font-md">
													<c:if test="${customerAccount.valueAssessment == 1}">
														<strong>高</strong>
													</c:if>
													<c:if test="${customerAccount.valueAssessment == 2}">
														<strong>中</strong>
													</c:if>
													<c:if test="${customerAccount.valueAssessment == 3}">
														<strong>低</strong>
													</c:if>
													<br><small>价值评估</small>
												</h4>
												<br> 
												<h4 class="font-md">
													<strong>${customerAccount.updateTimeStr}</strong> <br> <small>更新日期</small>
												</h4>
												<br> 
												<h4 class="font-md">
													<strong>${customerAccount.stagnateDay}天</strong> <br> <small>停滞天数</small>
												</h4>
											</div>
										</div>
										<div class="col-sm-6">
											<h1>
												${customerAccount.shorterName} <br> <small> 英文名：${customerAccount.englishName}</small>
											</h1>
											<div class="col-sm-9">
												<ul class="list-unstyled">
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">客户编码：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${customerAccount.code}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">客户种类：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${customerAccount.customerType.name}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">关系等级：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${customerAccount.relationshipClass.name}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">账户类型：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${customerAccount.accountType.name}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">所属行业：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${customerAccount.industry.name}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">人员规模：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${customerAccount.staffScale.name}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">省份：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${customerAccount.province.name}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">城市：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${customerAccount.city.name}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">区县：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${customerAccount.district.name}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">年收入：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${customerAccount.annualRevenueStr}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">电话传真：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${customerAccount.phoneFax}</span>
														</p>
													</li>
												</ul>
												<br>
												<p>
													<strong class="txt-color-darken">公司简介</strong>
												</p>
												<p>${customerAccount.companyBrief}</p>
												<br>
												<p>
													<strong class="txt-color-darken">备注</strong>
												</p>
												<p>${customerAccount.memo}</p>
											</div>
											<div class="col-sm-3">
												<div class="padding-10 text-right">
													<h4 class="font-md">
														<strong class="txt-color-red">${customerAccount.customerAccountCategory.name}</strong> <br> <small>客户分类</small>
													</h4>
													<br>
													<h4 class="font-md">
														<c:if test="${customerAccount.isHotCustomer == 1}">
															<strong class="txt-color-red">是</strong> 
														</c:if>
														<c:if test="${customerAccount.isHotCustomer == 0}">
															<strong class="txt-color-red">否</strong> 
														</c:if>
														<br> <small>热点客户</small>
													</h4>
													<br> 
													<h4 class="font-md">
														<strong class="txt-color-pink">${customerAccount.hotLevel.name}</strong> <br> <small>客户热度</small>
													</h4>
													<br>
													<h4 class="font-md">
														<strong class="txt-color-pink">${customerAccount.customerSource.name}</strong> <br> <small>客户来源</small>
													</h4>
													<br>
													<h4 class="font-md">
														<strong class="txt-color-pink">${customerAccount.customerStage.name}</strong> <br> <small>客户阶段</small>
													</h4>
												</div>
											</div>
											
											<br>
										</div>
										<div class="col-sm-3">
											<div class="margin-top-10"></div>
											<div class="well padding-10">
												<h5 class="margin-top-0">
													回款信息:
												</h5>
												<ul class="no-padding list-unstyled">
													<hr class="simple">
													<li><a href="javascript:void(0);" class="margin-top-0">合同：<span class="pull-right">￥${contractAmountStr}</span></a></li>
													<hr class="simple">
													<li><a href="javascript:void(0);" class="margin-top-0">回款计划：<span class="pull-right">￥${planAmountStr}</span></a></li>
													<hr class="simple">
													<li><a href="javascript:void(0);" class="margin-top-0">已回款：<span class="pull-right">￥${amountStr}</span></a></li>
													<!-- <hr class="simple">
													<li><a href="javascript:void(0);" class="margin-top-0">已发货 / 交付产品：<span class="pull-right">￥0.00</span></a></li> -->
													<hr class="simple">
													<li><a href="javascript:void(0);" class="margin-top-0">已开票金额：<span class="pull-right">￥${payAmountStr}</span></a></li>
												</ul>
											</div>
											
											<!-- <div class="well padding-10">
												<h5 class="margin-top-0">
													来往账单:
												</h5>
												<ul class="no-padding list-unstyled">
													<hr class="simple">
													<li><a href="javascript:void(0);" class="margin-top-0">账单费用：<span class="pull-right">￥0.00</span></a></li>
												</ul>
											</div> -->
											
											<div class="well padding-10">
												<h5 class="margin-top-0">
													费用支出<!-- （<a href="javascript:void(0);" class="margin-top-0">新建</a> / <a href="javascript:void(0);" class="margin-top-0">明细</a> / <a href="javascript:void(0);" class="margin-top-0">审批</a>） -->:
												</h5>
												<ul class="no-padding list-unstyled">
													<hr class="simple">
													<li><a href="javascript:void(0);" class="margin-top-0">费用：<span class="pull-right">￥${expenseAmountStr}</span></a></li>
												</ul>
											</div>
											
											<div class="tab-pane fade active in">
												<div class="alert alert-info fade in">
													<strong>我的联系人：</strong>
												</div>
												<c:if test="${contactPersonList != null && fn:length(contactPersonList) > 0}">
													<c:forEach items="${contactPersonList}" var="person">
														<div class="user" >
															<c:if test="${person.sex == 1}">
																<img src="${nvix}/vixntcommon/base/img/avatars/male.png" alt="${person.name}">
															</c:if>
															<c:if test="${person.sex == 0}">
																<img src="${nvix}/vixntcommon/base/img/avatars/female.png" alt="${person.name}">
															</c:if>
															<a href="javascript:void(0);" onclick="goContactPersonDetail('${person.id}');" >${person.name }</a>
															<div class="email">${person.mobile}</div>
														</div>
													</c:forEach>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- <div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<hr class="simple" style="border-color: #c0c0c0;">
									<div class="row">
										<div class="col-xs-3 col-sm-3 text-center">
											<h5>
												<span class="font-lg txt-color-red padding-5">14230.00元</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">消费总金额</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
												<strong class="txt-color-pink">本月消费金额：</strong>6120.00元
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
											<h5>
												<span class="font-lg txt-color-red padding-5">14230.00元</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">消费总金额</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
												<strong class="txt-color-pink">本月消费金额：</strong>6120.00元
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
											<h5>
												<span class="font-lg txt-color-red padding-5">14230.00元</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">消费总金额</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
												<strong class="txt-color-pink">本月消费金额：</strong>6120.00元
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
											<h5>
												<span class="font-lg txt-color-red padding-5">14230.00元</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">消费总金额</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
												<strong class="txt-color-pink">本月消费金额：</strong>6120.00元
											</p>
										</div>
									</div>
									<hr class="simple" style="border-color: #c0c0c0;">
								</div>
							</div> -->
							<div class="row">
								<div class="col-xs-12">
									<div class="tab_menu">
										<ul>
											<li class="selected">客户概览</li>
											<li>售前</li>
											<li>售中</li>
											<li>售后</li>
											<li>回款</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="tab-cons">
										<div class="common">
											<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
												<div class="jarviswidget" id="wid-id-2" data-widget-editbutton="false">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i>
														</span>
														<h2>客户分析</h2>
													</header>
													<div>
														<div class="jarviswidget-editbox"></div>
														<div class="widget-body no-padding">
															<div class="row">
																<div class="col-xs-4 text-center">
																	<h5>
																		<span class="font-md txt-color-blue padding-5">${contractAccount}<strong style="font-size:15px;">份</strong></span>
																		<div class="padding-5"></div>
																		<span class="font-xs txt-color-black">合同数量</span>
																	</h5>
																</div>
																<div class="col-xs-4 text-center">
																	<h5>
																		<span class="font-md txt-color-orange padding-5">5.0</span>
																		<div class="padding-5"></div>
																		<span class="font-xs txt-color-black">客户贡献度</span>
																	</h5>
																</div>
																<div class="col-xs-4 text-center">
																	<h5>
																		<c:if test="${customerOnYear == 0}">
																			<span class="font-md txt-color-blue padding-5">${customerOnYear}%</span>
																		</c:if>
																		<c:if test="${customerOnYear > 0}">
																			<span class="font-md txt-color-green padding-5">${customerOnYear}%<i style="font-size:15px;" class="fa fa-arrow-up txt-color-green"></i></span>
																		</c:if>
																		<c:if test="${customerOnYear < 0}">
																			<span class="font-md txt-color-red padding-5">${customerOnYear}%<i style="font-size:15px;" class="fa fa-arrow-down txt-color-red"></i></span>
																		</c:if>
																		<div class="padding-5"></div>
																		<span class="font-xs txt-color-black">客户同比</span>
																	</h5>
																</div>
															</div>
															<div id="mainNew" class="chart" style="height:300px;"></div>
														</div>
													</div>
												</div>
											</article>
											<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
												<div class="jarviswidget" id="wid-id-2" data-widget-editbutton="false">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i>
														</span>
														<h2>客户销售额占比</h2>
													</header>
													<div>
														<div class="jarviswidget-editbox"></div>
														<div class="widget-body no-padding">
															<div id="sales" class="chart" style="height:375px;"></div>
														</div>
													</div>
												</div>
											</article>
											<article class="col-sm-12 col-md-12 col-lg-12">
												<div class="jarviswidget">
													<header>
														<span class="widget-icon"> <i class="fa fa-table"></i>
														</span>
														<h2>近期销售活动</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div style="margin: 5px;">
																<div class="form-group" style="margin: 0 0px;">
																	<div class="col-md-3">
																		<input type="text" value="" placeholder="活动主题" class="form-control" id="searchActivityName">
																	</div>
																	<button onclick="recentSaleActivityTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																		<i class="glyphicon glyphicon-search"></i> 检索
																	</button>
																	<button onclick="$('#searchActivityName').val('');recentSaleActivityTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																		<i class="glyphicon glyphicon-repeat"></i> 清空
																	</button>
																</div>
															</div>
															<table id="recentSaleActivityTableId" class="table table-striped table-bordered table-hover" width="100%">
																<thead>
																	<tr>
																		<th width="8%">编号</th>
																		<th>主题</th>
																		<th>类型</th>
																		<th>负责人</th>
																		<th>创建人</th>
																		<th>活动地点</th>
																		<th>活动时间</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>
												</div>
											</article>
											<!-- <article class="col-sm-12 col-md-12 col-lg-12">
												<div class="jarviswidget">
													<header>
														<span class="widget-icon"> <i class="fa fa-table"></i>
														</span>
														<h2>项目</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div style="margin: 5px;">
																<div class="form-group" style="margin: 0 0px;">
																	<div class="col-md-3">
																		<input type="text" value="" placeholder="项目主题" class="form-control" id="searchCrmProjectName">
																	</div>
																	<button onclick="crmProjectTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																		<i class="glyphicon glyphicon-search"></i> 检索
																	</button>
																	<button onclick="$('#searchCrmProjectName').val('');crmProjectTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																		<i class="glyphicon glyphicon-repeat"></i> 清空
																	</button>
																</div>
															</div>
															<table id="crmProjectTableId" class="table table-striped table-bordered table-hover" width="100%">
																<thead>
																	<tr>
																		<th width="8%">编号</th>
																		<th>项目主题</th>
																		<th>负责人</th>
																		<th>项目阶段</th>
																		<th>项目状态</th>
																		<th>售前状态</th>
																		<th>立项日期</th>
																		<th>预计签单日期</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>
												</div>	
											</article> -->
										</div>
										<!-- 销售线索 -->
										<div class="common">
											<article class="col-sm-12 col-md-12 col-lg-12">
												<div class="jarviswidget">
													<header>
														<span class="widget-icon"> <i class="fa fa-table"></i>
														</span>
														<h2>销售机会</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div style="margin: 5px;">
																<div class="form-group" style="margin: 0 0px;">
																	<div class="col-md-3">
																		<input type="text" value="" placeholder="机会主题" class="form-control" id="searchSaleChanceName">
																	</div>
																	<button onclick="saleChanceTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																		<i class="glyphicon glyphicon-search"></i> 检索
																	</button>
																	<button onclick="$('#searchSaleChanceName').val('');saleChanceTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																		<i class="glyphicon glyphicon-repeat"></i> 清空
																	</button>
																</div>
															</div>
															<table id="saleChanceTableId" class="table table-striped table-bordered table-hover" width="100%">
																<thead>
																	<tr>
																		<th width="8%">编号</th>
																		<th>主题</th>
																		<th>类型</th>
																		<th>负责人</th>
																		<th>预计签单日期</th>
																		<th width="10%">预期金额</th>
																		<th>可能性</th>
																		<th>阶段</th>
																		<th>状态</th>
																		<th>阶段停留</th>
																		<th width="10%">更新时间</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>
												</div>	
											</article>
											<div class="row">
												<article class="col-sm-12 col-md-12 col-lg-6">
													<div class="jarviswidget">
														<header>
															<span class="widget-icon"> <i class="fa fa-table"></i></span>
															<h2>销售线索</h2>
														</header>
														<div>
															<div class="widget-body no-padding">
																<div style="margin: 5px;">
																	<div class="form-group" style="margin: 0 0px;">
																		<div class="col-md-3">
																			<input type="text" value="" placeholder="线索主题" class="form-control" id="searchSaleLeadName">
																		</div>
																		<button onclick="saleLeadTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																			<i class="glyphicon glyphicon-search"></i> 检索
																		</button>
																		<button onclick="$('#searchSaleLeadName').val('');saleLeadTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																			<i class="glyphicon glyphicon-repeat"></i> 清空
																		</button>
																	</div>
																</div>
																<table id="saleLeadTableId" class="table table-striped table-bordered table-hover" width="100%">
																	<thead>
																		<tr>
																			<th width="8%">编号</th>
																			<th>主题</th>
																			<th>头衔</th>
																			<th>类型</th>
																			<th>概率</th>
																		</tr>
																	</thead>
																</table>
															</div>
														</div>
													</div>
												</article>
												<article class="col-sm-12 col-md-12 col-lg-6">
													<div class="jarviswidget">
														<header>
															<span class="widget-icon"> <i class="fa fa-table"></i>
															</span>
															<h2>销售活动</h2>
														</header>
														<div>
															<div class="widget-body no-padding">
																<div style="margin: 5px;">
																	<div class="form-group" style="margin: 0 0px;">
																		<div class="col-md-3">
																			<input type="text" value="" placeholder="活动主题" class="form-control" id="searchSaleActivityName">
																		</div>
																		<button onclick="saleActivityTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																			<i class="glyphicon glyphicon-search"></i> 检索
																		</button>
																		<button onclick="$('#searchSaleActivityName').val('');saleActivityTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																			<i class="glyphicon glyphicon-repeat"></i> 清空
																		</button>
																	</div>
																</div>
																<table id="saleActivityTableId" class="table table-striped table-bordered table-hover" width="100%">
																	<thead>
																		<tr>
																			<th width="8%">编号</th>
																			<th>主题</th>
																			<th>类型</th>
																			<th>负责人</th>
																			<th>创建人</th>
																			<th>活动地点</th>
																			<th>活动时间</th>
																		</tr>
																	</thead>
																</table>
															</div>
														</div>
													</div>
												</article>
											</div>
											<div class="row">
												<article class="col-sm-12 col-md-12 col-lg-6">
													<div class="jarviswidget">
														<header>
															<span class="widget-icon"> <i class="fa fa-table"></i></span>
															<h2>详细需求</h2>
														</header>
														<div>
															<div class="widget-body no-padding">
																<div style="margin: 5px;">
																	<div class="form-group" style="margin: 0 0px;">
																		<div class="col-md-3">
																			<input type="text" value="" placeholder="主题" class="form-control" id="searchRequirementName">
																		</div>
																		<button onclick="projectRequirementTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																			<i class="glyphicon glyphicon-search"></i> 检索
																		</button>
																		<button onclick="$('#searchRequirementName').val('');projectRequirementTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																			<i class="glyphicon glyphicon-repeat"></i> 清空
																		</button>
																	</div>
																</div>
																<table id="projectRequirementTableId" class="table table-striped table-bordered table-hover" width="100%">
																	<thead>
																		<tr>
																			<th width="8%">编号</th>
																			<th>主题</th>
																			<th>对应机会</th>
																			<th>重要程度</th>
																			<th>提供人</th>
																			<th>日期</th>
																		</tr>
																	</thead>
																</table>
															</div>
														</div>
													</div>
												</article>
												<article class="col-sm-12 col-md-12 col-lg-6">
													<div class="jarviswidget">
														<header>
															<span class="widget-icon"> <i class="fa fa-table"></i></span>
															<h2>竞争对手</h2>
														</header>
														<div>
															<div class="widget-body no-padding">
																<div style="margin: 5px;">
																	<div class="form-group" style="margin: 0 0px;">
																		<div class="col-md-3">
																			<input type="text" value="" placeholder="公司名称" class="form-control" id="searchCompanyName"> 
																		</div>
																		<button onclick="competitorTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																			<i class="glyphicon glyphicon-search"></i> 检索
																		</button>
																		<button onclick="$('#searchCompanyName').val('');competitorTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																			<i class="glyphicon glyphicon-repeat"></i> 清空
																		</button>
																	</div>
																</div>
																<table id="competitorTableId" class="table table-striped table-bordered table-hover" width="100%">
																	<thead>
																		<tr>
																			<th width="8%">编号</th>
																			<th>公司名称</th>
																			<th>价格</th>
																			<th>竞争能力</th>
																			<th>对应机会</th>
																		</tr>
																	</thead>
																</table>
															</div>
														</div>
													</div>
												</article>
											</div>
											<div class="row">
												<article class="col-sm-12 col-md-12 col-lg-6">
													<div class="jarviswidget">
														<header>
															<span class="widget-icon"> <i class="fa fa-table"></i></span>
															<h2>解决方案</h2>
														</header>
														<div>
															<div class="widget-body no-padding">
																<div style="margin: 5px;">
																	<div class="form-group" style="margin: 0 0px;">
																		<div class="col-md-3">
																			<input type="text" value="" placeholder="主题" class="form-control" id="searchSolutionName">
																		</div>
																		<button onclick="projectSolutionTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																			<i class="glyphicon glyphicon-search"></i> 检索
																		</button>
																		<button onclick="$('#searchSolutionName').val('');projectSolutionTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																			<i class="glyphicon glyphicon-repeat"></i> 清空
																		</button>
																	</div>
																</div>
																<table id="projectSolutionTableId" class="table table-striped table-bordered table-hover" width="100%">
																	<thead>
																		<tr>
																			<th width="8%">编号</th>
																			<th>主题</th>
																			<th>对应机会</th>
																			<th>提交时间</th>
																		</tr>
																	</thead>
																</table>
															</div>
														</div>
													</div>
												</article>
											</div>
										</div>
										<!-- 开票记录 -->
										<div class="common">
											<article class="col-sm-12 col-md-12 col-lg-6">
												<div class="jarviswidget">
													<header>
														<span class="widget-icon"> <i class="fa fa-table"></i></span>
														<h2>开票记录</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div style="margin: 5px;">
																<div class="form-group" style="margin: 0 0px;">
																	<div class="col-md-3">
																		<input type="text" value="" placeholder="发票号" class="form-control" id="searchCode"> 
																	</div>
																	<button onclick="salesInvoiceTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																		<i class="glyphicon glyphicon-search"></i> 检索
																	</button>
																	<button onclick="$('#searchCode').val('');salesInvoiceTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																		<i class="glyphicon glyphicon-repeat"></i> 清空
																	</button>
																</div>
															</div>
															<table id="salesInvoiceTableId" class="table table-striped table-bordered table-hover" width="100%">
																<thead>
																	<tr>
																		<th width="8%">编号</th>
																		<th>开票内容</th>
																		<th>票据类型</th>
																		<th>发票金额</th>
																		<th>发票号码</th>
																		<th>订单名称</th>
																		<th>经手人</th>
																		<th>是否回款</th>
																		<th>开票日期</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>
												</div>
											</article>
											<article class="col-sm-12 col-md-12 col-lg-6">
												<div class="jarviswidget">
													<header>
														<span class="widget-icon"> <i class="fa fa-table"></i></span>
														<h2>费用支出</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div style="margin: 5px;">
																<div class="form-group" style="margin: 0 0px;">
																	<div class="col-md-3">
																		<input type="text" value="" placeholder="业务员姓名" class="form-control" id="searchExpenseName">
																	</div>
																	<button onclick="expensesTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																		<i class="glyphicon glyphicon-search"></i> 检索
																	</button>
																	<button onclick="$('#searchExpenseName').val('');expensesTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																		<i class="glyphicon glyphicon-repeat"></i> 清空
																	</button>
																</div>
															</div>
															<table id="expensesTableId" class="table table-striped table-bordered table-hover" width="100%">
																<thead>
																	<tr>
																		<th width="8%">编号</th>
																		<th>业务员</th>
																		<th>金额</th>
																		<th>类别</th>
																		<th>日期</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>
												</div>
											</article>
										</div>
										<!-- 客户关怀 -->
										<div class="common">
											<article class="col-sm-12 col-md-12 col-lg-12">
												<div class="jarviswidget">
													<header>
														<span class="widget-icon"> <i class="fa fa-table"></i></span>
														<h2>客户关怀</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div style="margin: 5px;">
																<div class="form-group" style="margin: 0 0px;">
																	<div class="col-md-3">
																		<input type="text" value="" placeholder="关怀主题" class="form-control" id="searchCustomerCareSubject">
																	</div>
																	<button onclick="customerCareTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																		<i class="glyphicon glyphicon-search"></i> 检索
																	</button>
																	<button onclick="$('#searchCustomerCareSubject').val('');customerCareTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																		<i class="glyphicon glyphicon-repeat"></i> 清空
																	</button>
																</div>
															</div>
															<table id="customerCareTableId" class="table table-striped table-bordered table-hover" width="100%">
																<thead>
																	<tr>
																		<th width="8%">编号</th>
																		<th>关怀主题</th>
																		<th>类型</th>
																		<th>执行人</th>
																		<th>日期</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>
												</div>
											</article>
										</div>
										<!-- 回款计划 -->
										<div class="common">
											<article class="col-sm-12 col-md-12 col-lg-6">
												<div class="jarviswidget">
													<header>
														<span class="widget-icon"> <i class="fa fa-table"></i></span>
														<h2>回款计划</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div style="margin: 5px;">
																<div class="form-group" style="margin: 0 0px;">
																	<div class="col-md-3">
																		<input type="text" value="" placeholder="所有人姓名" class="form-control" id="searchOwnerName">
																	</div>
																	<button onclick="backSectionPlanTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																		<i class="glyphicon glyphicon-search"></i> 检索
																	</button>
																	<button onclick="$('#searchOwnerName').val('');backSectionPlanTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																		<i class="glyphicon glyphicon-repeat"></i> 清空
																	</button>
																</div>
															</div>
															<table id="backSectionPlanTableId" class="table table-striped table-bordered table-hover" width="100%">
																<thead>
																	<tr>
																		<th width="8%">编号</th>
																		<th>金额</th>
																		<th>计划回款日期</th>
																		<th>期次</th>
																		<th>是否回款</th>
																		<th>订单</th>
																		<th>所有人</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>
												</div>
											</article>
											<article class="col-sm-12 col-md-12 col-lg-6">
												<div class="jarviswidget">
													<header>
														<span class="widget-icon"> <i class="fa fa-table"></i></span>
														<h2>回款记录</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div style="margin: 5px;">
																<div class="form-group" style="margin: 0 0px;">
																	<div class="col-md-3">
																		<input type="text" value="" placeholder="所有人姓名" class="form-control" id="searchName">
																	</div>
																	<button onclick="backSectionRecordTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																		<i class="glyphicon glyphicon-search"></i> 检索
																	</button>
																	<button onclick="$('#searchName').val('');backSectionRecordTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																		<i class="glyphicon glyphicon-repeat"></i> 清空
																	</button>
																</div>
															</div>
															<table id="backSectionRecordTableId" class="table table-striped table-bordered table-hover" width="100%">
																<thead>
																	<tr>
																		<th width="8%">编号</th>
																		<th>金额</th>
																		<th>期次</th>
																		<th>回款日期</th>
																		<th>是否开票</th>
																		<th>订单</th>
																		<th>所有人</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>
												</div>
											</article>
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
<script src="${nvix}/vixntcommon/base/js/echarts-all.js"></script>
<script type="text/javascript">
 var myChart = echarts.init(document.getElementById('mainNew')); 
 option = {
		    tooltip : {
		        trigger: 'axis'
		    },
		   /*  legend: {
		        data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
		    }, */
		   
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['2-1','2-2','2-3','2-4','2-5','2-6','2-7','2-8']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'邮件营销',
		            type:'line',
		            stack: '总量',
		            data:[120, 132, 101, 134, 90, 230, 210,200]
		        }
		    ]
		};
 myChart.setOption(option);
 var myChart1 = echarts.init(document.getElementById('sales')); 
 option1 = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:${names}
		    },
		    series: [
		        {
		            name:'销售额',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '50%'],

		            label: {
		                normal: {
		                    position: 'inner'
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:${salesJson}
		        },
		    ]
		};
 myChart1.setOption(option1);
</script>
<script type="text/javascript">
	var tab_menu_li = $('.tab_menu li');
	$('.tab-cons .common:gt(0)').hide();
	
	tab_menu_li.click(function(){
	    var index = $(this).index()+1;
	    $(this).addClass('selected')
	            .siblings().removeClass('selected');
	    var tab_menu_li_index = tab_menu_li.index(this);
	    $('.tab-cons .common').eq(tab_menu_li_index).show()
	            .siblings().hide();
	})
	// 联系人详情
	function goContactPersonDetail(id){
		openWindowForShow("${nvix}/nvixnt/nvixCustomerAction!goContactPersonDetail.action?id=" + id,'联系人详情',720,650);
	}
	function saveOrUpdate(id, syncTag) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixCustomerAction!goSaveOrUpdate.action?id=' + id + "&customerAccountType=" + syncTag);
	};
</script>
<script>
	pageSetUp();
	// 项目
	var crmProjectColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.subject;}}, 
		{"orderable" : false,"data" : function(data) {return data.personInCharge == null ? '' : data.personInCharge.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.projectStage == null ? '' : data.projectStage.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.projectStatus == null ? '' : data.projectStatus.name;}},
		{"orderable" : false,"data" : function(data) {return data.projectSalePreviousStage == null ? '' : data.projectSalePreviousStage.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.projectEstablishDateStr;}}, 
		{"orderable" : false,"data" : function(data) {return data.forecastSignDateStr;}}, 
	];

	var crmProjectTable = initDataTable("crmProjectTableId", "${nvix}/nvixnt/nvixCustomerAction!getCrmProjectListJson.action", crmProjectColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
		var name = $("#searchCrmProjectName").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"name" : name
		};
	});
	// 销售机会
	var saleChanceColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.subject;}}, 
		{"orderable" : false,"data" : function(data) {return data.saleType == null ? '' : data.saleType.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.employee == null ? '' : data.employee.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.preOrderDateStr;}}, 
		{"orderable" : false,"data" : function(data) {return data.expectedValue;}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.possibility != null && data.possibility > 0){
				return data.possibility+"%";
			}else{
				return "0%";
			}
		}},
		{"orderable" : false,"data" : function(data) {return data.saleStage == null ? '' : data.saleStage.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.saleChanceStatus == null ? '' : data.saleChanceStatus.name;}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.stagnateDay != 0){
				return data.stagnateDay+"天";
			}else{
				return "";
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.updateTimeStr;}}, 
   	];

   	var saleChanceTable = initDataTable("saleChanceTableId", "${nvix}/nvixnt/nvixCustomerAction!getSaleChanceListJson.action", saleChanceColumns, function(page, pageSize, orderField, orderBy) {
   		var parentId = $("#id").val();
   	   	var name = $("#searchSaleChanceName").val();
   	 	name = Url.encode(name);
   		return {
   		"page" : page,
   		"pageSize" : pageSize,
   		"orderField" : orderField,
   		"orderBy" : orderBy,
   		"parentId" : parentId,
   		"name" : name
   		};
   	});
	// 销售线索
	var saleLeadColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.subject;}}, 
		{"orderable" : false,"data" : function(data) {return data.title;}}, 
		{"orderable" : false,"data" : function(data) {return data.type;}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.probability != null && data.probability > 0){
				return data.probability+"%";
			}else{
				return "0%";
			}
		}}
	];
	
	var saleLeadTable = initDataTable("saleLeadTableId", "${nvix}/nvixnt/nvixCustomerAction!getSaleLeadListJson.action", saleLeadColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
	   	var name = $("#searchSaleLeadName").val();
	 	name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"name" : name
		};
	});
	// 销售活动
	var saleActivityColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.title;}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.saleActivity != null){
				return "<span class='label label-info'>"+data.saleActivity.name+"</span>";
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.personInCharge.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.created_by.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.address;}},
		{"orderable" : false,"data" : function(data) {return data.dateStr;}}
	];

	var saleActivityTable = initDataTable("saleActivityTableId", "${nvix}/nvixnt/nvixCustomerAction!getSaleActivityListJson.action", saleActivityColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
	   	var name = $("#searchSaleActivityName").val();
	 	name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"name" : name
		};
	});
	// 详细需求
	var projectRequirementColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.subject;}}, 
		{"orderable" : false,"data" : function(data) {return data.saleChance == null ? '' : data.saleChance.subject;}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.degree == '0'){
				return "<span class='label label-danger'>非常重要</span>";
			}else if(data.degree == '1'){
				return "<span class='label label-success'>重要</span>";
			}else if(data.degree == '2'){
				return "<span class='label label-info'>一般</span>";
			}else if(data.degree == '3'){
				return "<span class='label label-default'>不重要</span>";
			}else{
				return "";
			}
		}},
		{"orderable" : false,"data" : function(data) {return data.provider == null ? '' : data.provider.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.recordDateStr;}}, 
	];

	var projectRequirementTable = initDataTable("projectRequirementTableId", "${nvix}/nvixnt/nvixCustomerAction!getProjectRequirementListJson.action", projectRequirementColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
		var name = $("#searchRequirementName").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name,
		"parentId" : parentId
		};
	});
	// 竞争对手
	var competitorColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.companyName;}}, 
		{"orderable" : false,"data" : function(data) {return data.price;}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.competitive != null){
				return "<span class='label label-info'>"+data.competitive.name+"</span>";
			}
		}},
		{"orderable" : false,"data" : function(data) {return data.saleChance == null ? '' : data.saleChance.subject;}}, 
	];

	var competitorTable = initDataTable("competitorTableId", "${nvix}/nvixnt/nvixCustomerAction!getCompetitorListJson.action", competitorColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
		var companyName = $("#searchCompanyName").val();
		companyName = Url.encode(companyName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : companyName,
		"parentId" : parentId
		};
	});
	// 解决方案
	var projectSolutionColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.subject;}}, 
		{"orderable" : false,"data" : function(data) {return data.saleChance == null ? '' : data.saleChance.subject;}}, 
		{"orderable" : false,"data" : function(data) {return data.submitDateStr;}}, 
	];

	var projectSolutionTable = initDataTable("projectSolutionTableId", "${nvix}/nvixnt/nvixCustomerAction!getProjectSolutionListJson.action", projectSolutionColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
		var name = $("#searchSolutionName").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name,
		"parentId" : parentId
		};
	});
	// 开票记录
	var salesInvoiceColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.name}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.saleInvoiceType != null){
				return "<span class='label label-info'>"+data.saleInvoiceType.name+"</span>";
			}
		}},
		{"orderable" : false,"data" : function(data) {
			if(data.amount != null){
				return data.amount;
			}else{
				return 0;
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.code}}, 
		{"orderable" : false,"data" : function(data) {return data.salesOrder == null ? "" : data.salesOrder.title}}, 
		{"orderable" : false,"data" : function(data) {return data.employee == null ? "" : data.employee.name}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.isBackSection == 0){
				return "<span class='label label-info'>未回款</span>";
			}else if(data.isBackSection == 1){
				return "<span class='label label-success'>已回款</span>";
			}else{
				return "";
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.createTimeStr;}}, 
	];

	var salesInvoiceTable = initDataTable("salesInvoiceTableId", "${nvix}/nvixnt/nvixCustomerAction!getSalesInvoiceListJson.action", salesInvoiceColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
		var code = $("#searchCode").val();
		code = Url.encode(code);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : code,
		"parentId" : parentId
		};
	});
	// 回款计划
	var backSectionPlanColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.amount != null){
				return data.amount;
			}else{
				return 0;
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.backSectionPlanDateStr;}}, 
		{"orderable" : false,"data" : function(data) {return data.stageNumber;}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.backSectionPlanStatus == 1){
				return "<span class='label label-success'>是</span>";
			}else{
				return "<span class='label label-info'>否</span>";
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.salesOrder == null ? "" : data.salesOrder.title;}},
		{"orderable" : false,"data" : function(data) {return data.ownerName;}}
	];

	var backSectionPlanTable = initDataTable("backSectionPlanTableId", "${nvix}/nvixnt/nvixCustomerAction!getBackSectionPlanListJson.action", backSectionPlanColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
	   	var name = $("#searchOwnerName").val();
	 	name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"name" : name
		};
	});
	// 回款记录
	var backSectionRecordColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.amount != null){
				return data.amount;
			}else{
				return 0;
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.stageNumber;}}, 
		{"orderable" : false,"data" : function(data) {return data.backSectionDateStr;}},
		{"orderable" : false,"data" : function(data) {
			if(data.isBilling == 1){
				return "<span class='label label-success'>已开</span>";
			}else if(data.isBilling == 0){
				return "<span class='label label-info'>未开</span>";
			}else if(data.isBilling == 2){
				return "<span class='label label-info'>不开发票</span>";
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.salesOrder == null ? "" : data.salesOrder.title;}},
		{"orderable" : false,"data" : function(data) {return data.ownerName;}}
	];

	var backSectionRecordTable = initDataTable("backSectionRecordTableId", "${nvix}/nvixnt/nvixCustomerAction!getBackSectionRecordListJson.action", backSectionRecordColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
	   	var name = $("#searchName").val();
	 	name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"name" : name
		};
	});	
	// 费用支出
	var expensesColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}},
		{"orderable" : false,"data" : function(data) {return data.employee.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.amount}},
		{"orderable" : false,"data" : function(data) {
			if(data.expenseType != null){
				return "<span class='label label-info'>"+data.expenseType.name+"</span>";
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.expensesDateStr;}}, 
	];

	var expensesTable = initDataTable("expensesTableId", "${nvix}/nvixnt/nvixCustomerAction!getExpensesListJson.action", expensesColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
		var name = $("#searchExpenseName").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name,
		"parentId" : parentId
		};
	});
	// 关怀主题
	var customerCareColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.subject;}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.customerCareType != null){
				return "<span class='label label-info'>"+data.customerCareType.name+"</span>";
			}else{
				return "";
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.employee == null ? '' : data.employee.name;}},
		{"orderable" : false,"data" : function(data) {return data.createTimeStr;}}, 
	];

	var customerCareTable = initDataTable("customerCareTableId", "${nvix}/nvixnt/nvixCustomerAction!getCustomerCareListJson.action", customerCareColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
		var name = $("#searchCustomerCareSubject").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name,
		"parentId" : parentId
		};
	});
	
	// 近期销售活动
	var recentSaleActivityColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.title;}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.saleActivity != null){
				return "<span class='label label-info'>"+data.saleActivity.name+"</span>";
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.personInCharge.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.created_by.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.address;}},
		{"orderable" : false,"data" : function(data) {return data.dateStr;}}
	];

	var recentSaleActivityTable = initDataTable("recentSaleActivityTableId", "${nvix}/nvixnt/nvixCustomerAction!getRecentSaleActivityListJson.action", recentSaleActivityColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
	   	var name = $("#searchActivityName").val();
	 	name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"name" : name
		};
	});
	
</script>