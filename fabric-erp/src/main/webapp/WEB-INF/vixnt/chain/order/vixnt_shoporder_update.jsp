<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 供应链管理 <span>&gt; 门店管理</span> <span>&gt; 门店销售记录</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('drp_storeorder','${nvix}/nvixnt/vixntOrderAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>销售订单</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="salesOrderForm">
				<fieldset>
					<input type="hidden" id="orderId" name="order.id" value="${order.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 单号:</label>
						<div class="col-md-3">
							<input id="outerId" name="order.outerId" value="${order.outerId}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 金额:</label>
						<div class="col-md-3">
							<input id="totalFee" name="order.totalFee" value="${order.totalFee}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 客户:</label>
						<div class="col-md-3">
							<input id="customerName" name="order.customer.customerName" value="${order.customer.customerName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 联系方式:</label>
						<div class="col-md-3">
							<input id="mobile" name="order.customer.mobile" value="${order.customer.mobile}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>明细</h2>
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
							<button class="btn btn-default" type="button" onclick="loadContent('drp_storeorder','${nvix}/nvixnt/vixntOrderAction!goList.action');">
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
	"name" : "outerGoodsId",
	"data" : function(data) {
		return data.outerGoodsId;
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
	"name" : "num",
	"data" : function(data) {
		return data.num;
	}
	} ];
	var saleOrderItemTable = initDataTable("saleOrderItemTableId", "${nvix}/nvixnt/vixntOrderAction!goOrderDetailList.action", stockRecordLinesColumns, function(page, pageSize, orderField, orderBy) {
		var orderId = $("#orderId").val();
		var itemName = $("#itemName").val();
		itemName = Url.encode(itemName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"itemName" : itemName,
		"id" : orderId
		};
	}, 10, '1');
</script>