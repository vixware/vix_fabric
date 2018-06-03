<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="hidden" id="id" name="crmProject.id" value="${crmProject.id}" /> 
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>>项目视图 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('${crmProject.id}');">
					<i class="fa fa-pencil"></i> 修改
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/nvixCrmProjectAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
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
										<div class="air air-top-left padding-10">
											<h4 class="txt-color-white font-lg">${crmProject.name}</h4>
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
											<s:if test="crmProject.customerAccount.logo != null && crmProject.customerAccount.logo != ''">
												<img src="${crmProject.customerAccount.logo}" alt="" width="100px" height="100px">
											</s:if>
											<s:else>
												<img src="${nvix}/vixntcommon/base/images/logo.jpg" alt="">
											</s:else>
											<div class="padding-10">
												<h4 class="font-md">
													<strong>
														${crmProject.personInCharge.name}
													</strong> 
													<br> <small>负责人</small>
												</h4>
												<br> 
												<h4 class="font-md">
													<strong>${crmProject.projectEstablishDateStr}</strong> <br> <small>立项时间</small>
												</h4>
												<br> 
											</div>
										</div>
										<div class="col-sm-6">
											<h1>
												${crmProject.subject} <br> <small> 客户：${crmProject.customerAccount.name}</small>
											</h1>
											<div class="col-sm-9">
												<ul class="list-unstyled">
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">可能性：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${crmProject.possibility}%</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">售前阶段：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${crmProject.projectSalePreviousStage.name}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">预计费用：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${crmProject.forecastMoney}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">预计签单日期：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${crmProject.forecastSignDateStr}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">项目组成员：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${employeeNames}</span>
														</p>
													</li>
												</ul>
												<br>
												<p>
													<strong class="txt-color-darken">项目概要</strong>
												</p>
												<p>${crmProject.projectSummary}</p>
											</div>
											<div class="col-sm-3">
												<div class="padding-10 text-right">
													<h4 class="font-md">
														<strong class="txt-color-red">${crmProject.projectStatus.name}</strong> <br> <small>项目状态</small>
													</h4>
													<br>
													<h4 class="font-md">
														<strong class="txt-color-red">${crmProject.projectStage.name}</strong> <br> <small>项目阶段</small>
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
													<hr class="simple">
													<li><a href="javascript:void(0);" class="margin-top-0">费用支出：<span class="pull-right">￥${expenseAmountStr}</span></a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="tab_menu">
										<ul>
											<li class="selected" onclick="saleChanceTable.ajax.reload();">销售机会</li>
											<li onclick="saleLeadTable.ajax.reload();">销售线索</li>
											<li onclick="backSectionPlanTable.ajax.reload();">回款计划</li>
											<li onclick="backSectionRecordTable.ajax.reload();">回款记录</li>
											<li onclick="saleActivityTable.ajax.reload();">销售活动</li>
											<li onclick="uploaderTable.ajax.reload();">附件列表</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="tab-cons">
										<!-- 销售机会 -->
										<div class="common">
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
																	<th>客户</th>
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
										</div>
										<!-- 销售线索 -->
										<div class="common">
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
																	<th>客户</th>
																	<th>头衔</th>
																	<th>类型</th>
																	<th>概率</th>
																</tr>
															</thead>
														</table>
													</div>
												</div>
											</div>
										</div>
										<!-- 回款计划 -->
										<div class="common">
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
																	<th>客户</th>
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
										</div>
										<!-- 回款记录 -->
										<div class="common">
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
																	<th>客户</th>
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
										</div>
										<div class="common">
											<div class="jarviswidget">
												<header>
													<span class="widget-icon"> <i class="fa fa-table"></i></span>
													<h2>销售活动</h2>
												</header>
												<div>
													<div class="widget-body no-padding">
														<div style="margin: 5px;">
															<div class="form-group" style="margin: 0 0px;">
																<div class="col-md-3">
																	<input type="text" value="" placeholder="活动主题" class="form-control" id="title"> 
																</div>
																<button onclick="saleActivityTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#title').val('');saleActivityTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
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
																	<th>客户</th>
																	<th>负责人</th>
																	<th>创建人</th>
																	<th>活动地点</th>
																</tr>
															</thead>
														</table>
													</div>
												</div>
											</div>
										</div>
										<div class="common">
											<div class="jarviswidget">
												<header>
													<span class="widget-icon"> <i class="fa fa-table"></i></span>
													<h2>附件列表</h2>
												</header>
												<div>
													<div class="widget-body no-padding">
														<div style="margin: 5px;">
														</div>
														<table id="uploaderTableId" class="table table-striped table-bordered table-hover" width="100%">
															<thead>
																<tr>
																	<th width="10%">编号</th>
																	<th width="60%">附件名称</th>
																	<th width="10%">上传时间</th>
																	<th width="10%">下载次数</th>
																	<th width="10%">操作</th>
																</tr>
															</thead>
														</table>
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
		</div>
	</div>
</div>
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
	
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixCrmProjectAction!goSaveOrUpdate.action?id=' + id);
	};
</script>
<script>
	pageSetUp();
	// 销售机会
	var saleChanceColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.subject;}}, 
		{"orderable" : false,"data" : function(data) {return data.customerAccount == null ? '' : data.customerAccount.name;}}, 
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

   	var saleChanceTable = initDataTable("saleChanceTableId", "${nvix}/nvixnt/nvixCrmProjectAction!getSaleChanceListJson.action", saleChanceColumns, function(page, pageSize, orderField, orderBy) {
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
		{"orderable" : false,"data" : function(data) {return data.customerAccount == null ? '' : data.customerAccount.name;}}, 
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
	
	var saleLeadTable = initDataTable("saleLeadTableId", "${nvix}/nvixnt/nvixCrmProjectAction!getSaleLeadListJson.action", saleLeadColumns, function(page, pageSize, orderField, orderBy) {
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
		{"orderable" : false,"data" : function(data) {return data.customerAccount == null ? '' : data.customerAccount.name;}}, 
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

	var backSectionPlanTable = initDataTable("backSectionPlanTableId", "${nvix}/nvixnt/nvixCrmProjectAction!getBackSectionPlanListJson.action", backSectionPlanColumns, function(page, pageSize, orderField, orderBy) {
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
		{"orderable" : false,"data" : function(data) {return data.customerAccount == null ? '' : data.customerAccount.name;}}, 
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

	var backSectionRecordTable = initDataTable("backSectionRecordTableId", "${nvix}/nvixnt/nvixCrmProjectAction!getBackSectionRecordListJson.action", backSectionRecordColumns, function(page, pageSize, orderField, orderBy) {
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
	// 销售活动
	var saleActivityColumns = [ 
		{"orderable" : false,"data" : function(data) {return "&nbsp;";}}, 
		{"orderable" : false,"data" : function(data) {return data.title;}}, 
		{"orderable" : false,"data" : function(data) {
			if(data.saleActivity != null){
				return "<span class='label label-info'>"+data.saleActivity.name+"</span>";
			}
		}}, 
		{"orderable" : false,"data" : function(data) {return data.customerAccount.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.personInCharge.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.created_by.name;}}, 
		{"orderable" : false,"data" : function(data) {return data.address;}}
	];

	var saleActivityTable = initDataTable("saleActivityTableId", "${nvix}/nvixnt/nvixCrmProjectAction!getSaleActivityListJson.action", saleActivityColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#id").val();
		var name = $("#title").val();
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
	// 附件列表
	var uploaderColumns = [ 
		{"data" : function(data) {return "";}}, 
		{"data" : function(data) {return data.fileName;}}, 
		{"data" : function(data) {return data.createTimeTimeStr;}}, 
		{"data" : function(data) {return data.downloadNum + '次';}}, 
		{"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"downloadUploader('" + data.id + "');\" title='下载'><span class='txt-color-blue pull-right'><i class='fa fa-download'></i></span></a>";
		}} 
	];

	var uploaderTable = initDataTable("uploaderTableId", "${nvix}/nvixnt/nvixCrmProjectAction!goUploaderList.action", uploaderColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#id").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"id" : id
		};
	});
	function downloadUploader(id) {
		var url = "${nvix}/nvixnt/nvixCrmProjectAction!downloadUploader.action?id=" + id;
		window.open(url);
	};
</script>