<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 连锁经营管理 <span>&gt; 订单管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('drp_salesorder','${nvix}/nvixnt/vixntSalesOrderAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>要货单</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="salesOrderForm">
				<fieldset>
					<input type="hidden" id="salesOrderId" name="requireGoodsOrder.id" value="${requireGoodsOrder.id}" /> <input type="hidden" id="salesOrderBizType" name="requireGoodsOrder.bizType" value="${requireGoodsOrder.bizType}" /> <input type="hidden" id="channelDistributorId" name="requireGoodsOrder.channelDistributor.id"
						value="${requireGoodsOrder.channelDistributor.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 单号:</label>
						<div class="col-md-3">
							<input id="code" name="requireGoodsOrder.code" value="${requireGoodsOrder.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" name="requireGoodsOrder.name" value="${requireGoodsOrder.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 门店:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="channelDistributorName" value="${requireGoodsOrder.channelDistributor.name }" class="form-control validate[required]" type="text" readonly="readonly" />
										<div class="input-group-btn">
											<button onclick="goChooseChannelDistributor();" type="button" class="btn btn-info">
												<i class="glyphicon glyphicon-search"></i>
											</button>
											<button onclick="$('#channelDistributorId').val('');$('#channelDistributorName').val('');" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>要货时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="deliveryTime" name="requireGoodsOrder.deliveryTime" value="${requireGoodsOrder.deliveryTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'deliveryTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">经办人:</label>
						<div class="col-md-3">
							<input class="form-control" id="creator" name="requireGoodsOrder.creator" value="${requireGoodsOrder.creator }" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="requireGoodsOrder.memo" class="form-control" rows="4">${requireGoodsOrder.memo }</textarea>
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>要货单明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="商品名称" class="form-control" id="itemName">
										</div>
										<button onclick="saleOrderItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#itemName').val('');saleOrderItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="goSaveOrUpdateSaleOrderItem();" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="新增明细" />
											</button>
										</div>
									</div>
								</div>
								<table id="saleOrderItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">合计:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control" id="amount" name="requireGoodsOrder.amount" value="${requireGoodsOrder.amount }" type="text"> <span class="input-group-addon">(¥)</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-default" type="button" onclick="loadContent('drp_salesorder','${nvix}/nvixnt/vixntSalesOrderAction!goList.action');">
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
		return "";
	}
	}, {
	"title" : "商品编码",
	"name" : "itemCode",
	"data" : function(data) {
		return data.itemCode;
	}
	}, {
	"title" : "商品名称",
	"name" : "title",
	"data" : function(data) {
		return data.title;
	}
	}, {
	"title" : "单价",
	"name" : "price",
	"data" : function(data) {
		return data.price;
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
	}, {
	"title" : "金额",
	"name" : "netTotal",
	"data" : function(data) {
		return data.netTotal;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateSaleOrderItem('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteSaleOrderItemById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var saleOrderItemTable = initDataTable("saleOrderItemTableId", "${nvix}/nvixnt/vixntSalesOrderAction!goSaleOrderItemList.action", stockRecordLinesColumns, function(page, pageSize, orderField, orderBy) {
		var salesOrderId = $("#salesOrderId").val();
		var itemName = $("#itemName").val();
		itemName = Url.encode(itemName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"itemName" : itemName,
		"id" : salesOrderId
		};
	}, 10, '1');
	//选择供应商
	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};
	function goChooseChannelDistributor() {
		chooseListData('${nvix}/nvixnt/vixntSalesOrderAction!goChooseChannelDistributor.action', 'single', '选择供应商', 'channelDistributor');
	};
	$("#salesOrderForm").validationEngine();
	function saveOrUpdate() {
		if ($("#salesOrderForm").validationEngine('validate')) {
			$("#salesOrderForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntSalesOrderAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntSalesOrderAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	function goSaveOrUpdateSaleOrderItem(id, title) {
		if ($("#salesOrderForm").validationEngine('validate')) {
			$("#salesOrderForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntSalesOrderAction!saveOrUpdate.action",
			dataType : "text",
			success : function(salesOrderId) {
				$('#salesOrderId').val(salesOrderId);
				openWindowForShow('${nvix}/nvixnt/vixntSalesOrderAction!goSaveOrUpdateSaleOrderItem.action?id=' + id, title, 850, 675);
			}
			});
		} else {
			return false;
		}
	};
	//删除
	function deleteSaleOrderItemById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntSalesOrderAction!deleteSaleOrderItemById.action?id=' + id, '是否删除要货单明细?', saleOrderItemTable);
	};
</script>