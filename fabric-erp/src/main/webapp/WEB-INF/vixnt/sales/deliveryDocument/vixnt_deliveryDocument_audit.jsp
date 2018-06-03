<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售管理 <span onclick="">&gt; 销售发货</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn bg-color-green txt-color-white" type="button" onclick="goAudit('${taskId}');">
					<i class="glyphicon glyphicon-ok-circle"></i> 同意
				</button>
				<button class="btn bg-color-red txt-color-white" type="button" onclick="reject('${taskId}');">
					<i class="glyphicon glyphicon-remove-circle"></i> 驳回
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('mid_salesDeliveryOrder','${nvix}/nvixnt/nvixntDeliveryDocumentAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>退货单</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="deliveryDocumentForm">
				<fieldset>
					<input type="hidden" id="taskId" value="${jobTodo.id }" />
					<input type="hidden" id="jobTodoUrl" value="${jobTodo.url }" />
					<input type="hidden" id="billType" value="${jobTodo.sourceClass}" />
					<input type="hidden" id="billId" value="${jobTodo.sourceClassPk}" />
					<input type="hidden" id="sourceClassName" name="deliveryDocument.sourceClassName" value="${deliveryDocument.sourceClassName}"/>
					<input type="hidden" id="sourceBillCode" name="deliveryDocument.sourceBillCode" value="${deliveryDocument.sourceBillCode}"/>
					<input type="hidden" id="deliveryDocumentId" name="deliveryDocument.id" value="${deliveryDocument.id}" /> 
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 发货单编码:</label>
						<div class="col-md-3">
							<input id="code" name="deliveryDocument.code" value="${deliveryDocument.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 客户:</label>
						<div class="col-md-3">
							<input id="customerName" value="${deliveryDocument.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>发运地:</label>
						<div class="col-md-3">
							<input id="source" name="deliveryDocument.source" value="${deliveryDocument.source}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>目的地:</label>
						<div class="col-md-3">
							<input id="target" name="deliveryDocument.target" value="${deliveryDocument.target}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>目的地:</label>
						<div class="col-md-3">
							<input id="target" name="deliveryDocument.target" value="${deliveryDocument.target}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div> --%>
						<label class="col-md-2 control-label">业务员:</label>
						<div class="col-md-3">
								<input id="chooseSalePersonName" name="deliveryDocument.salePerson.name" value="${deliveryDocument.salePerson.name}" onfocus="chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'chooseSalePerson');" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>交货时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="deliveryTime" name="deliveryDocument.deliveryTime" value="${deliveryDocument.deliveryTimeTimeStr }" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'returnTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">		
						<label class="col-md-2 control-label"><span class="text-danger">*</span>发货时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="shippedDate" name="deliveryDocument.shippedDate" value="${deliveryDocument.shippedDateTimeStr }" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'returnTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="deliveryDocument.memo" class="form-control" rows="4">${deliveryDocument.memo}</textarea>
						</div>
					</div>
					<div id="" class="jarviswidget jarviswidget-color-white">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>退货明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="商品名称" class="form-control" id="searchProductName">
										</div>
										<button onclick="deliveryDocumentItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchProductName').val('');deliveryDocumentItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</div>
								</div>
								<table id="deliveryDocumentItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">总金额:</label>
						<div class="col-md-3">
							<input id="amount" name="deliveryDocument.amount" value="${deliveryDocument.amount}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>审批信息</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<table id="flowApprovalOpinionTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn bg-color-green txt-color-white" type="button" onclick="goAudit('${taskId}');">
								<i class="glyphicon glyphicon-ok-circle"></i> 同意
							</button>
							<button class="btn bg-color-red txt-color-white" type="button" onclick="reject('${taskId}');">
								<i class="glyphicon glyphicon-remove-circle"></i> 驳回
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('mid_salesDeliveryOrder','${nvix}/nvixnt/nvixntDeliveryDocumentAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	var deliveryDocumentItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "商品编码",
	"data" : function(data) {
		return data.itemCode;
	}
	}, {
	"title" : "商品名称",
	"data" : function(data) {
		return data.itemName;
	}
	}, {
	"title" : "规格型号",
	"name" : "specification",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"title" : "${vv:varView('vix_mdm_item')}类型",
	"name" : "itemType",
	"data" : function(data) {
		return data.itemType;
	}
	}, {
	"title" : "发货数量",
	"name" : "count",
	"data" : function(data) {
		return data.count;
	}
	}, {
	"title" : "金额",
	"name" : "taxTotal",
	"data" : function(data) {
		return data.taxTotal;
	}
	}, {
	"title" : "单价",
	"name" : "unitcost",
	"data" : function(data) {
		return data.price;
	}
	}];
	var deliveryDocumentItemTable = initDataTable("deliveryDocumentItemTableId", "${nvix}/nvixnt/nvixntDeliveryDocumentAction!goSingleDeliveryDocumentItemList.action", deliveryDocumentItemColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#deliveryDocumentId").val();
		var searchProductName = $("#searchProductName").val();
		searchProductName = Url.encode(searchProductName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchProductName" : searchProductName,
		"id" : id
		};
	});

	var flowApprovalOpinionColumns = [ {
		"title" : "编号",
		"width" : "5%",
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"title" : "审批意见",
		"data" : function(data) {
			return data.opinion;
		}
		}, {
		"title" : "审批人",
		"data" : function(data) {
			return data.approvalPersonName;
		}
		}, {
		"title" : "审批时间",
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
		} ];
		var flowApprovalOpinionTable = initDataTable("flowApprovalOpinionTableId", "${nvix}/nvixnt/vixntInboundWarehouseAction!getFlowApprovalOpinion.action", flowApprovalOpinionColumns, function(page, pageSize, orderField, orderBy) {
			var deliveryDocumentId = $("#deliveryDocumentId").val();
			return {
			"page" : page,
			"pageSize" : pageSize,
			"id" : deliveryDocumentId
			};
		});
		function goAudit(id) {
			openSaveOrUpdateWindow('${nvix}/nvixnt/vreport/nvixJobTodoAction!goAudit.action?id=' + id, "jobTodoForm", "流程审批", 750, 350, null, null, function() {
				loadContent('', '${nvix}/nvixnt/vixNtIndexAction!goListContent.action');
			});
		};
		
		function reject(id) {
			updateEntityByConfirm('${nvix}/nvixnt/vreport/nvixJobTodoAction!reject.action?id=' + id, "是否驳回该申请?", null, null, function() {
				loadContent('', '${nvix}/nvixnt/vixNtIndexAction!goListContent.action');
			});
		};
</script>