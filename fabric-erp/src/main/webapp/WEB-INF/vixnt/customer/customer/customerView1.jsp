<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="hidden" id="type" value="${customerAccount.type}" /> 
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>>客户管理  </span><span>>客户视图 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
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
		<div class="col-sm-12 col-md-12">
			<div class="well">
				<!-- 菜单选项切换部分 -->
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
							<!-- 销售活动  -->
							<div class="common">
								<article style="overflow: hidden; zoom: 1;">
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
															<input type="text" value="" placeholder="关怀主题" class="form-control" id="searchCustomerCareName">
														</div>
														<button onclick="saleActivityTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="$('#searchCustomerCareName').val('');saleActivityTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
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
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
								</article>
							</div>
							<!-- 详细需求 -->
							<div class="common">
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
							</div>
							<!-- 竞争对手-->
							<div class="common">
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
							</div>
							<!-- 解决方案 -->
							<div class="common">
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
							</div>
							<!-- 开票记录 -->
							<div class="common">
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
							<!-- 费用支出 -->
							<div class="common">
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
</script>
<script>
	pageSetUp();
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
		{"orderable" : false,"data" : function(data) {return data.address;}}
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
	
</script>