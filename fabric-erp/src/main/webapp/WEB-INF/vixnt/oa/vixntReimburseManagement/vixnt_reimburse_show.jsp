<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 办公 <span onclick="">&gt; 报销管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('before');">
					<i class="fa fa-chevron-left"></i> 上一条
				</button>
				<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('after');">
					<i class="fa fa-chevron-right"></i> 下一条
				</button>
				<button class="btn bg-color-blueDark txt-color-white" type="button" onclick="print();">
					<i class="fa fa-print"></i> 打印
				</button>
				<button class="btn bg-color-orange txt-color-white" type="button" onclick="">
					<i class="fa fa-envelope-o"></i> 发送邮件
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntReimburseAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-list-alt"></i>
						</span>
						<h2>报销单</h2>
					</header>
					<div>
						<div class="widget-body">
							<form class="form-horizontal" id="travelExpenseForm">
								<fieldset>
									<input type="hidden" id="travelExpenseId" name="travelExpense.id" value="${travelExpense.id}" /> <input type="hidden" id="employeeIds" name="employeeIds" value="${employeeIds}" /> <input type="hidden" id="employeeId" name="travelExpense.employee.id" value="${travelExpense.employee.id}" />
									<div class="form-group">
										<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
										<div class="col-md-8">
											<input id="name" name="travelExpense.name" value="${travelExpense.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-2">报销人:</label>
										<div class="col-md-3">
											<input id="travelExpenseEmployeeName" name="travelExpense.employee.name" value="${travelExpense.employee.name}" type="text" class="form-control validate[required]">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label"><span class="text-danger">*</span>开始日期:</label>
										<div class="col-md-3">
											<div class="input-group">
												<input id="startTime" name="travelExpense.startTime" value="${travelExpense.startTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
													onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'startTime'});"><i class="fa fa-calendar"></i></span>
											</div>
										</div>
										<label class="col-md-2 control-label"><span class="text-danger">*</span>结束日期:</label>
										<div class="col-md-3">
											<div class="input-group">
												<input id="endTime" name="travelExpense.endTime" value="${travelExpense.endTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'endTime'});"><i
													class="fa fa-calendar"></i></span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">备注</label>
										<div class="col-md-8">
											<textarea id="memo" name="memo" class="form-control" rows="4">${travelExpense.memo }</textarea>
										</div>
									</div>
									<div class="widget-body no-padding">
										<div class="jarviswidget" style="margin: 0; padding: 12px 12px 12px 12px;" data-widget-togglebutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
											<header>
												<ul class="nav nav-tabs pull-left in">
													<li class="active"><a data-toggle="tab" href="#orderDetailTab" onclick="orderDetailTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">交通费用</span>
													</a></li>
													<li><a data-toggle="tab" href="#orderDeliveryPlanTab" onclick="orderDeliveryPlanTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">住宿费用</span>
													</a></li>
													<li><a data-toggle="tab" href="#orderInvoiceTab" onclick="orderInvoiceTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">日常费用</span>
													</a></li>
												</ul>
											</header>
											<div class="widget-body" style="padding: 0;">
												<div class="tab-content">
													<div class="tab-pane no-padding active" id="orderDetailTab">
														<div id="orderDetailSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="" class="form-control" />
																</div>
																<button onclick="transportCostsTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
															</div>
														</div>
														<table id="transportCostsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="orderDeliveryPlanTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="" class="form-control" />
																</div>
																<button onclick="accommodationFeeTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
															</div>
														</div>
														<table id="accommodationFeeTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="orderInvoiceTab">
														<div id="orderInvoiceSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="" class="form-control" />
																</div>
																<button onclick="currentExpenseDetailTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
															</div>
														</div>
														<table id="currentExpenseDetailTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-2" for="append">合计</label>
										<div class="col-md-3">
											<input class="form-control" id="cost" name="travelExpense.cost" type="text" value="${travelExpense.cost}">
										</div>
										<label class="col-md-2 control-label"><span class="text-danger">*</span>编写日期:</label>
										<div class="col-md-3">
											<div class="input-group">
												<input id="createTime" name="travelExpense.createTime" value="${travelExpense.createTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
													onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'createTime'});"><i class="fa fa-calendar"></i></span>
											</div>
										</div>
									</div>
									<s:if test="flowApprovalOpinionList.size > 0">
										<s:iterator value="flowApprovalOpinionList" var="entity" status="s">
											<div class="form-group">
												<label class="control-label col-md-2">审批人:</label>
												<div class="col-md-3">
													<input class="form-control" id="" name="" value="${entity.approvalPersonName}" type="text" readonly="readonly">
												</div>
												<label class="control-label col-md-2">审批意见:</label>
												<div class="col-md-3">
													<input class="form-control" id="" name="" value="${entity.opinion}" type="text" readonly="readonly">
												</div>
											</div>
										</s:iterator>
									</s:if>
								</fieldset>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-12">
											<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('before');">
												<i class="fa fa-chevron-left"></i> 上一条
											</button>
											<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('after');">
												<i class="fa fa-chevron-right"></i> 下一条
											</button>
											<button class="btn bg-color-blueDark txt-color-white" type="button" onclick="print();">
												<i class="fa fa-print"></i> 打印
											</button>
											<button class="btn bg-color-orange txt-color-white" type="button" onclick="">
												<i class="fa fa-envelope-o"></i> 发送邮件
											</button>
											<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntReimburseAction!goList.action');">
												<i class="fa fa-rotate-left"></i> 返回
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	//交通费用
	var transportCostsColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "出发地",
	"width" : "20%",
	"name" : "fromPlace",
	"data" : function(data) {
		return data.fromPlace;
	}
	}, {
	"title" : "目的地",
	"width" : "15%",
	"name" : "toPlace",
	"data" : function(data) {
		return data.toPlace;
	}
	}, {
	"title" : "乘坐交通工具时间",
	"width" : "15%",
	"name" : "usingTime",
	"data" : function(data) {
		return data.usingTime;
	}
	}, {
	"title" : "费用",
	"width" : "10%",
	"name" : "cost",
	"data" : function(data) {
		return data.cost;
	}
	}];
	var transportCostsTable = initDataTable("transportCostsTableId", "${nvix}/nvixnt/vixntReimburseAction!goTransportCostsPager.action?id=${travelExpense.id}", transportCostsColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	//住宿费用
	var accommodationFeeColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "住宿标准",
	"width" : "20%",
	"name" : "lodgingFee",
	"data" : function(data) {
		return data.lodgingFee;
	}
	}, {
	"title" : "房价",
	"width" : "15%",
	"name" : "housePrices",
	"data" : function(data) {
		return data.housePrices;
	}
	}, {
	"title" : "天数",
	"width" : "15%",
	"name" : "days",
	"data" : function(data) {
		return data.days;
	}
	}, {
	"title" : "开始入住时间",
	"width" : "10%",
	"name" : "startAccomodationsTime",
	"data" : function(data) {
		return data.startAccomodationsTime;
	}
	}, {
	"title" : "结束入住时间",
	"width" : "10%",
	"name" : "endAccomodationsTime",
	"data" : function(data) {
		return data.endAccomodationsTime;
	}
	}, {
	"title" : "费用",
	"width" : "10%",
	"name" : "cost",
	"data" : function(data) {
		return data.cost;
	}
	} ];
	var accommodationFeeTable = initDataTable("accommodationFeeTableId", "${nvix}/nvixnt/vixntReimburseAction!goAccommodationFeePager.action?id=${travelExpense.id}", accommodationFeeColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	//日常费用
	var currentExpenseDetailColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "报销标准",
	"width" : "20%",
	"name" : "expensesStandard",
	"data" : function(data) {
		return data.expensesStandard;
	}
	}, {
	"title" : "月份",
	"width" : "30%",
	"name" : "expensesMonth",
	"data" : function(data) {
		return data.expensesMonth;
	}
	}, {
	"title" : "报销金额",
	"width" : "15%",
	"name" : "expensesAmountDetail",
	"data" : function(data) {
		return data.expensesAmountDetail;
	}
	}, {
	"title" : "开支日期",
	"width" : "15%",
	"name" : "expensesDate",
	"data" : function(data) {
		return data.expensesDate;
	}
	}];
	var currentExpenseDetailTable = initDataTable("currentExpenseDetailTableId", "${nvix}/nvixnt/vixntReimburseAction!goCurrentExpenseDetailPager.action?id=${travelExpense.id}", currentExpenseDetailColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	function goBeforeOrAfter(tag){
		goSaveOrUpdateEntity('${nvix}/nvixnt/vixntReimburseAction!goShowBeforeAndAfter.action?str='+tag+'&id='+$('#travelExpenseId').val());
	}
	var LODOP;
	function print(){
		$.ajax({
			url : '${nvix}/nvixnt/vixntReimburseAction!printTravelExpense.action?id='+$("#travelExpenseId").val(),
			cache : false,
			success : function(html) {
				LODOP = getLodop();
				LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
				LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
				LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);// 打印后自动关闭预览窗口
				LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
				LODOP.SET_PRINT_PAGESIZE(3, "240mm", "45mm", "");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
				LODOP.SET_PREVIEW_WINDOW(1, 0, 0, 1024, 550, ""); // 2上下打印工具条都显示
				/* LODOP.PRINT(); */
				LODOP.PREVIEW();
			}
			});
	}
</script>