<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-cubes"></i> 库存管理 <span onclick="">&gt; 出库管理</span>
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
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>入库单</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="stockRecordsForm">
				<fieldset>
					<input type="hidden" id="stockRecordsId" name="stockRecords.id" value="${stockRecords.id}" /> <input type="hidden" id="warehouseId" name="stockRecords.invWarehouse.id" value="${stockRecords.invWarehouse.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 单号:</label>
						<div class="col-md-3">
							<input id="code" name="stockRecords.code" value="${stockRecords.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" name="stockRecords.name" value="${stockRecords.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 出库仓库:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<input id="warehouseName" value="${stockRecords.invWarehouse.name }" class="form-control validate[required]" type="text" readonly="readonly" />
								</div>
							</div>
						</div>

						<label class="col-md-2 control-label"><span class="text-danger">*</span>出库日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="createTime" name="stockRecords.createTime" value="${stockRecords.startTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'createTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">发货人:</label>
						<div class="col-md-3">
							<input id="checkperson" name="stockRecords.checkperson" value="${stockRecords.checkperson}" type="text" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="stockRecords.memo" class="form-control" rows="4">${stockRecords.memo }</textarea>
						</div>
					</div>
					<div id="" class="jarviswidget jarviswidget-color-white">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>出库单明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="商品名称" class="form-control" id="searchProductName">
										</div>
										<button onclick="stockRecordLinesTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchProductName').val('');stockRecordLinesTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</div>
								</div>
								<table id="stockRecordLinesTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">合计:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control" id="totalAmount" name="stockRecords.totalAmount" value="${stockRecords.totalAmount }" type="text"> <span class="input-group-addon">(¥)</span>
									</div>
								</div>
							</div>
						</div>
						<label class="control-label col-md-2">创建人:</label>
						<div class="col-md-3">
							<input class="form-control" id="creator" name="stockRecords.creator" value="${stockRecords.creator }" type="text">
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
	var stockRecordLinesColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "商品编码",
	"name" : "itemcode",
	"data" : function(data) {
		return data.itemcode;
	}
	}, {
	"title" : "商品名称",
	"name" : "itemname",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
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
	}, {
	"title" : "条形码",
	"name" : "barCode",
	"data" : function(data) {
		return data.barCode;
	}
	}, {
	"title" : "批次号",
	"name" : "batchcode",
	"data" : function(data) {
		return data.batchcode;
	}
	}, {
	"title" : "单价",
	"name" : "unitcost",
	"data" : function(data) {
		return data.unitcost;
	}
	}, {
	"title" : "单位",
	"name" : "unit",
	"data" : function(data) {
		return data.unit;
	}
	}, {
	"title" : "数量",
	"name" : "quantity",
	"data" : function(data) {
		return data.quantity;
	}
	}, {
	"title" : "货位",
	"name" : "invShelfName",
	"data" : function(data) {
		return data.invShelfName;
	}
	}, {
	"title" : "操作",
	"width" : "7%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateStockRecordLines('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var stockRecordLinesTable = initDataTable("stockRecordLinesTableId", "${nvix}/nvixnt/vixntOutBoundAction!goStockRecordLinesList.action", stockRecordLinesColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#stockRecordsId").val();
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

	function goChooseWarehouse() {
		chooseListData('${nvix}/nvixnt/vixntInboundWarehouseAction!goChooseWarehouse.action', 'single', '选择仓库', 'warehouse');
	};
	//保存入库单
	$("#stockRecordsForm").validationEngine();
	function saveOrUpdate() {
		if ($("#stockRecordsForm").validationEngine('validate')) {
			$("#stockRecordsForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntOutBoundAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntOutBoundAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	function goSaveOrUpdateStockRecordLines(id, title) {
		if (id != null && id != '') {
			openWindowForShow('${nvix}/nvixnt/vixntOutBoundAction!goSaveOrUpdateStockRecordLines.action?id=' + id, title, 850, 675);
		} else {
			if ($("#stockRecordsForm").validationEngine('validate')) {
				$("#stockRecordsForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntOutBoundAction!saveOrUpdate.action",
				dataType : "text",
				success : function(salesOrderId) {
					$("#stockRecordsId").val(salesOrderId);
					openWindowForShow('${nvix}/nvixnt/vixntOutBoundAction!goSaveOrUpdateStockRecordLines.action?id=' + id + "&salesOrderId=" + salesOrderId, title, 850, 675);
				}
				});
			} else {
				return false;
			}
		}
	};
	function saveOrUpdateStockRecordLines() {
		if ($("#stockRecordsId").val() != '' && $("#stockRecordsId").val() != null) {
			$.post('${nvix}/nvixnt/vixntOutBoundAction!saveOrUpdateStockRecordLines.action', {
			'id' : $("#stockRecordsId").val(),
			'itemcode' : $("#scanitemcode").val()
			}, function(result) {
				stockRecordLinesTable.ajax.reload();
				$("#scanitemcode").val('');
				$("#totalAmount").val(result);
			});
		} else {
			if ($("#stockRecordsForm").validationEngine('validate')) {
				$("#stockRecordsForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntOutBoundAction!saveOrUpdate.action",
				dataType : "text",
				success : function(id) {
					$.post('${nvix}/nvixnt/vixntOutBoundAction!saveOrUpdateStockRecordLines.action', {
					'id' : id,
					'itemcode' : $("#scanitemcode").val()
					}, function(result) {
						stockRecordLinesTable.ajax.reload();
						$("#scanitemcode").val('');
						$("#totalAmount").val(result);
					});
				}
				});
			} else {
				return false;
			}
		}
	};
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