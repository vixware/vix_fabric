<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 门店管理 <span>&gt; 门店订单管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('drp_storerequiregoods','${nvix}/nvixnt/vixntStoreRequireGoodsAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>门店订单</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="salesOrderForm">
				<fieldset>
					<input type="hidden" id="requireGoodsOrderId" name="requireGoodsOrder.id" value="${requireGoodsOrder.id}" /> <input type="hidden" id="salesOrderBizType" name="requireGoodsOrder.bizType" value="${requireGoodsOrder.bizType}" /> <input type="hidden" id="channelDistributorId" name="requireGoodsOrder.channelDistributor.id"
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
							<input id="channelDistributorName" value="${requireGoodsOrder.channelDistributor.name }" class="form-control validate[required]" type="text" readonly="readonly" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>要货时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="deliveryTime" name="requireGoodsOrder.deliveryTime" value="${requireGoodsOrder.deliveryTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'deliveryTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">开始配送时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="startSendTime" name="requireGoodsOrder.startSendTime" value="${requireGoodsOrder.startSendTimeTimeStr}" data-prompt-position="topLeft" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'startSendTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">结束配送时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="endSendTime" name="requireGoodsOrder.endSendTime" value="${requireGoodsOrder.endSendTimeTimeStr}" data-prompt-position="topLeft" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'endSendTime'});"><i
									class="fa fa-calendar"></i></span>
							</div>
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
							<h2>拆单明细</h2>
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
											<button onclick="goSaveOrUpdateSaleOrderItem('');" type="button" class="btn btn-primary">
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
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('drp_storerequiregoods','${nvix}/nvixnt/vixntStoreRequireGoodsAction!goList.action');">
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
	"title" : "单据编码",
	"width" : "15%",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "主题",
	"width" : "30%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "供应商",
	"width" : "15%",
	"data" : function(data) {
		return data.supplierName;
	}
	}, {
	"title" : "采购时间",
	"width" : "15%",
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"name" : "status",
	"data" : function(data) {
		if (data.status == 0) {
			return "<span class='label label-info'>待配货</span>";
		} else if (data.status == 1) {
			return "<span class='label label-primary'>待发货</span>";
		} else if (data.status == 2) {
			return "<span class='label label-warning'>配送中</span>";
		} else if (data.status == 3) {
			return "<span class='label label-success'>已完成</span>";
		} else if (data.status == 4) {
			return "<span class='label label-success'>待分拣</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		return update;

	}
	} ];
	var saleOrderItemTable = initDataTable("saleOrderItemTableId", "${nvix}/nvixnt/vixntStoreRequireGoodsAction!goPurchaseOrderList.action", stockRecordLinesColumns, function(page, pageSize, orderField, orderBy) {
		var requireGoodsOrderId = $("#requireGoodsOrderId").val();
		var itemName = $("#itemName").val();
		itemName = Url.encode(itemName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"itemName" : itemName,
		"id" : requireGoodsOrderId
		};
	}, 10);

	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntSupplierSalesOrderAction!goSaveOrUpdatePurchaseOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>