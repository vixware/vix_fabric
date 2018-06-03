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
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<s:if test="isAllowAudit == 1">
					<button type="button" class="btn btn-success" onclick="approvalSalesOrder('');">
						<i class="fa fa-save"></i> &nbsp;提交审批
					</button>
				</s:if>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntReimburseAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget">
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
										<label class="col-md-2 control-label">填报日期:</label>
										<div class="col-md-3">
											<div class="input-group">
												<input id="createTime" name="travelExpense.createTime" value="${travelExpense.createTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
													onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'createTime'});"><i class="fa fa-calendar"></i></span>
											</div>
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
												<input id="endTime" name="travelExpense.endTime" value="${travelExpense.endTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
													onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'endTime'});"><i class="fa fa-calendar"></i></span>
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
																<div class=" listMenu-float-right">
																	<button onclick="goSaveOrUpdateTransportCosts('', '新增交通费用');" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i> 新增
																	</button>
																</div>
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
																<div class=" listMenu-float-right">
																	<button onclick="goSaveOrUpdateAccommodationFee('', '新增住宿费用');" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i> 新增
																	</button>
																</div>
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
																<div class=" listMenu-float-right">
																	<button onclick="goSaveOrUpdateCurrentExpenseDetail('', '新增日常费用');" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i> 新增
																	</button>
																</div>
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
											<input class="form-control" id="cost" name="travelExpense.cost" value="${travelExpense.cost}" readonly="readonly" type="text">
										</div>
										<label class="col-md-2 control-label"><span class="text-danger">*</span>编写日期:</label>
										<div class="col-md-3">
											<div class="input-group">
												<input id="createTime" name="travelExpense.createTime" value="${travelExpense.createTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
													onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'createTime'});"><i class="fa fa-calendar"></i></span>
											</div>
										</div>
									</div>
								</fieldset>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-12">
											<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">
												<i class="fa fa-save"></i> 保存
											</button>
											<s:if test="isAllowAudit == 1">
												<button type="button" class="btn btn-success" onclick="approvalSalesOrder('');">
													<i class="fa fa-save"></i> &nbsp;提交审批
												</button>
											</s:if>
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
		return data.usingTimeTimeStr;
	}
	}, {
	"title" : "费用",
	"width" : "10%",
	"name" : "cost",
	"data" : function(data) {
		return data.cost;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var transportCostsTable = initDataTable("transportCostsTableId", "${nvix}/nvixnt/vixntReimburseAction!goTransportCostsPager.action", transportCostsColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#travelExpenseId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id
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
		return data.startAccomodationsTimeTimeStr;
	}
	}, {
	"title" : "结束入住时间",
	"width" : "10%",
	"name" : "endAccomodationsTime",
	"data" : function(data) {
		return data.endAccomodationsTimeTimeStr;
	}
	}, {
	"title" : "费用",
	"width" : "10%",
	"name" : "cost",
	"data" : function(data) {
		return data.cost;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var accommodationFeeTable = initDataTable("accommodationFeeTableId", "${nvix}/nvixnt/vixntReimburseAction!goAccommodationFeePager.action", accommodationFeeColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#travelExpenseId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id
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
		return data.expensesDateTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var currentExpenseDetailTable = initDataTable("currentExpenseDetailTableId", "${nvix}/nvixnt/vixntReimburseAction!goCurrentExpenseDetailPager.action", currentExpenseDetailColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#travelExpenseId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id
		};
	});

	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goEmployeeChoose.action', 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#employeeIds').val(emp[0]);
				$('#employeeNames').val(emp[1]);
			} else {
				layer.alert("请选择人员!");
			}
		});
	};
	function approvalSalesOrder() {
		layer.confirm('订单提交审批后,将不可修改,确定要审批订单吗?', {
			btn : [ '确定', '取消' ]
		}, function(action) {
			layer.close(action);
			audit();
		}, function(action) {
			layer.close(action);
		});
	}
	$("#travelExpenseForm").validationEngine();
	function saveOrUpdate() {
		if ($("#travelExpenseForm").validationEngine('validate')) {
			$("#travelExpenseForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntReimburseAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntReimburseAction!goList.action');
			}
			});
		} else {
			return false;
		}
	}
	function audit() {
		if ($("#travelExpenseForm").validationEngine('validate')) {
			$("#travelExpenseForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntReimburseAction!audit.action",
			dataType : "text",
			success : function(result) {
				showMessage(result, 'success');
				loadContent('', '${nvix}/nvixnt/vixntReimburseAction!goList.action');
			}
			});
		} else {
			return false;
		}
	}

	//新增交通费用
	function goSaveOrUpdateTransportCosts(id, title) {
		if ($("#travelExpenseForm").validationEngine('validate')) {
			$("#travelExpenseForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntReimburseAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				var result = id.split(":");
				if (result.length > 1) {
					$("#travelExpenseId").val(result[0]);
					openSaveOrUpdateWindow('${nvix}/nvixnt/vixntReimburseAction!goSaveOrUpdateTransportCosts.action?travelExpenseId=' + result[0], "transportCostsForm", title, 750, 600, transportCostsTable, null, function() {
						getTravelExpenseCost();
					});
				}
			}
			});
		} else {
			return false;
		}
	};
	function getTravelExpenseCost() {
		$.ajax({
		url : '${nvix}/nvixnt/vixntReimburseAction!getTravelExpenseCost.action?id=' + $("#travelExpenseId").val(),
		cache : false,
		success : function(json) {
			$("#cost").val(json);
		}
		});
	}
	//新增住宿费用
	function goSaveOrUpdateAccommodationFee(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntReimburseAction!goSaveOrUpdateAccommodationFee.action?id=' + id + "&travelExpenseId=" + $("#travelExpenseId").val(), "accommodationFeeForm", title, 750, 600, accommodationFeeTable, null, function() {
			getTravelExpenseCost();
		});
	};
	//新增日常费用
	function goSaveOrUpdateCurrentExpenseDetail(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntReimburseAction!goSaveOrUpdateCurrentExpenseDetail.action?id=' + id + "&travelExpenseId=" + $("#travelExpenseId").val(), "currentExpenseDetailForm", title, 750, 600, currentExpenseDetailTable, null, function() {
			getTravelExpenseCost();
		});
	};
</script>