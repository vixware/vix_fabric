<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>>项目视图 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/nvixCrmProjectAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12 col-md-12">
			<div class="well">
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-body">
							<form class="form-horizontal" id="customerAccountForm">
								<input type="hidden" id="id" name="crmProject.id" value="${crmProject.id}" /> 
								<legend>项目信息:</legend>
								<fieldset>
									<div class="form-group">
										<label class="col-md-2 control-label">项目主题:</label>
										<div class="col-md-3">
											<input id="subject" value="${crmProject.subject}" class="form-control" type="text" readonly="readonly" />
										</div>
										<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
										<div class="col-md-3">
											<input id="customerName" value="${crmProject.customerAccount.name}" class="form-control" type="text" readonly="readonly" /> 
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label"><span class="text-danger">*</span>项目状态:</label>
										<div class="col-md-3">
											<input id="projectStatus" value="${crmProject.projectStatus.name}" class="form-control" type="text" readonly="readonly" /> 
										</div>
										<label class="col-md-2 control-label"><span class="text-danger">*</span>项目阶段:</label>
										<div class="col-md-3">
											<input id="projectStage" value="${crmProject.projectStage.name}" class="form-control" type="text" readonly="readonly" /> 
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label"><span class="text-danger">*</span>负责人:</label>
										<div class="col-md-3">
											<input id="employeeName" value="${crmProject.personInCharge.name}" class="form-control" type="text" readonly="readonly" /> 
										</div>
										<label class="col-md-2 control-label"><span class="text-danger">*</span>立项时间:</label>
										<div class="col-md-3">
											<input id="projectEstablishDate" value="${crmProject.projectEstablishDateStr}" class="form-control" type="text" readonly="readonly" /> 
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">可能性:</label>
										<div class="col-md-3">
											<div class="input-group">
												<input id="possibility" value="${crmProject.possibility}" class="form-control" type="text" readonly="readonly" />
												<span class="input-group-addon">(1-100)%</span>
											</div>
										</div>
										<label class="col-md-2 control-label">售前阶段:</label>
										<div class="col-md-3">
											<input id="projectSalePreviousStage" value="${crmProject.projectSalePreviousStage.name}" class="form-control" type="text" readonly="readonly" /> 
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">预计费用:</label>
										<div class="col-md-3">
											<input id="forecastMoney" value="${crmProject.forecastMoney}" class="form-control" type="text" readonly="readonly" />
										</div>
										<label class="col-md-2 control-label">预计签单日期:</label>
										<div class="col-md-3">
											<input id="forecastSignDate" value="${crmProject.forecastSignDateStr}" class="form-control" type="text" readonly="readonly" /> 
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">项目组成员:</label>
										<div class="col-md-8">
											<input id="employeeNames" class="form-control validate[required]" value="${employeeNames}" readonly="readonly">
										</div>
									</div>
									<%-- <div class="form-group">
										<label class="col-md-2 control-label">项目阶段备注:</label>
										<div class="col-md-8">
											<textarea id="projectStageMemo" name="crmProject.projectStageMemo" class="form-control" rows="4">${crmProject.projectStageMemo}</textarea>
										</div>
									</div> --%>
									<div class="form-group">
										<label class="col-md-2 control-label">项目概要:</label>
										<div class="col-md-8">
											<textarea id="projectSummary" class="form-control" rows="4" readonly="readonly">${crmProject.projectSummary}</textarea>
										</div>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
				<!-- 菜单选项切换部分 -->
				<div class="row">
					<div class="col-xs-12">
						<div class="tab_menu">
							<ul>
								<!-- <li class="selected" onclick="">项目分析</li> -->
								<li class="selected" onclick="saleChanceTable.ajax.reload();">销售机会</li>
								<li onclick="saleLeadTable.ajax.reload();">销售线索</li>
								<li onclick="backSectionPlanTable.ajax.reload();">回款计划</li>
								<li onclick="backSectionRecordTable.ajax.reload();">回款记录</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="tab-cons">
							<%-- <div class="common">
								<!-- 块信息展示 -->
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<hr class="simple" style="border-color: #c0c0c0;">
										<div class="row">
											<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
												<h5>
													<span class="font-lg txt-color-red padding-5">￥${planAmountStr}</span>
													<div class="padding-5"></div>
													<strong class="txt-color-greenLight">回款计划</strong>
												</h5>
												<div class="padding-5"></div>
											</div>
											<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
												<h5>
													<span class="font-lg txt-color-red padding-5">￥${amountStr}</span>
													<div class="padding-5"></div>
													<strong class="txt-color-greenLight">已回款</strong>
												</h5>
												<div class="padding-5"></div>
											</div>
										</div>
										<hr class="simple" style="border-color: #c0c0c0;">
									</div>
								</div>
							</div> --%>
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
	
</script>