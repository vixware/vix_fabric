<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i> 采购管理 <span onclick="">&gt; 采购订单</span>
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
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>订单</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="purchaseOrderForm">
				<fieldset>
				<input type="hidden" id="taskId" value="${jobTodo.id }" />
				<input type="hidden" id="jobTodoUrl" value="${jobTodo.url }" />
				<input type="hidden" id="billType" value="${jobTodo.sourceClass}" />
				<input type="hidden" id="billId" value="${jobTodo.sourceClassPk}" />
					<input type="hidden" id="purchaseOrderId" name="purchaseOrder.id" value="${purchaseOrder.id}" /> <input type="hidden" id="supplierId" name="purchaseOrder.supplier.id" value="${purchaseOrder.supplier.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 单号:</label>
						<div class="col-md-3">
							<input id="code" name="purchaseOrder.code" value="${purchaseOrder.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" name="purchaseOrder.name" value="${purchaseOrder.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">采购人:</label>
						<div class="col-md-3">
							<input id="purchasePersonId" name="purchaseOrder.purchasePersonId" value="${purchaseOrder.purchasePersonId}" type="hidden"> <input id="purchasePerson" name="purchaseOrder.purchasePerson" value="${purchaseOrder.purchasePerson}" type="text" class="form-control">
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>采购日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="createTime" name="purchaseOrder.createTime" value="${purchaseOrder.createTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'createTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 供应商:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="supplierName" value="${purchaseOrder.supplier.name }" class="form-control validate[required]" type="text" readonly="readonly" />
										<div class="input-group-btn">
											<button onclick="goChooseSupplier();" type="button" class="btn btn-info">
												<i class="glyphicon glyphicon-search"></i>
											</button>
											<button onclick="$('#supplierId').val('');$('#supplierName').val('');" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="purchaseOrder.memo" class="form-control" rows="4">${purchaseOrder.memo }</textarea>
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>订单明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="名称" class="form-control" id="searchName">
										</div>
										<button onclick="purchaseOrderLineItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchName').val('');purchaseOrderLineItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</div>
								</div>
								<table id="purchaseOrderLineItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">合计:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control" id="totalAmount" name="purchaseOrder.totalAmount" value="${purchaseOrder.totalAmount }" type="text" readonly="readonly"> <span class="input-group-addon">(¥)</span>
									</div>
								</div>
							</div>
						</div>
						<label class="control-label col-md-2">创建人:</label>
						<div class="col-md-3">
							<input class="form-control" id="creator" name="purchaseOrder.creator" value="${purchaseOrder.creator }" type="text">
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
								<button class="btn bg-color-green txt-color-white" type="button" onclick="goAudit('${taskId}');">
									<i class="glyphicon glyphicon-ok-circle"></i> 同意
								</button>
								<button class="btn bg-color-red txt-color-white" type="button" onclick="reject('${taskId}');">
									<i class="glyphicon glyphicon-remove-circle"></i> 驳回
								</button>
								<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');">
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
	var purchaseOrderLineItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "商品编码",
	"name" : "itemCode",
	"data" : function(data) {
		return data.itemCode;
	}
	}, {
	"title" : "商品名称",
	"name" : "itemName",
	"data" : function(data) {
		return data.itemName;
	}
	}, /* {
	"title" : "规格型号",
	"name" : "specification",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"title" : "SKU编码",
	"name" : "skuCode",
	"data" : function(data) {
		return data.skuCode;
	}
	}, */{
	"title" : "单价",
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	},{
	"title" : "税率",
	"name" : "price",
	"data" : function(data) {
		return data.taxRate;
	}
	}, {
	"title" : "数量",
	"name" : "amount",
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"title" : "单位",
	"name" : "unit",
	"data" : function(data) {
		return data.unit;
	}
	}];
	var purchaseOrderLineItemTable = initDataTable("purchaseOrderLineItemTableId", "${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goPurchaseOrderLineItemList.action", purchaseOrderLineItemColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#purchaseOrderId").val();
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchName,
		"id" : id
		};
	});

	
	$("#jobTodoForm").validationEngine();
	function goAudit(taskId) {
		asyncbox.confirm('确定要提交审批吗?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${nvix}/nvixnt/vreport/nvixJobTodoAction!goAudit.action?id=' + taskId,
				cache : false,
				success : function(html) {
					asyncbox.open({
					modal : true,
					width : 660,
					height : 380,
					title : "流程审批",
					html : html,
					callback : function(action) {
						if (action == 'ok' && $('#jobTodoForm').validationEngine('validate')) {
							$.post('${nvix}/nvixnt/vreport/nvixJobTodoAction!audit.action', {
							'id' : $("#taskId").val(),
							'opinion' : $("#opinion").val(),
							'conditionRule' : $(":radio[name=conditionRule][checked]").val()
							}, function(result) {
								asyncbox.success(result, "<s:text name='cmn_message'/>", function(action) {
									loadContent('${nvix}/nvixnt/vreport/nvixJobTodoAction!goList.action');
								});
							});
						}
					},
					btnsbar : $.btn.OKCANCEL
					});
				}
				});
			}
		});
	}
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