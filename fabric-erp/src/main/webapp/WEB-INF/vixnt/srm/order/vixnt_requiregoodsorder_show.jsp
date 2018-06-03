<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 供应商管理 <span>&gt; 供应商订单管理</span>
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
			<h2>采购订单</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal">
				<fieldset>
					<input type="hidden" id="purchaseOrderId" name="nvixOrderBatch.id" value="${nvixOrderBatch.id}" /> <input type="hidden" id="supplierId" name="nvixOrderBatch.supplier.id" value="${nvixOrderBatch.supplier.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 批次号:</label>
						<div class="col-md-3">
							<input id="code" name="nvixOrderBatch.code" value="${nvixOrderBatch.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" name="nvixOrderBatch.name" value="${nvixOrderBatch.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">处理人:</label>
						<div class="col-md-3">
							<input id="creator" name="nvixOrderBatch.creator" value="${nvixOrderBatch.creator}" type="text" class="form-control">
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>处理日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="createTime" name="nvixOrderBatch.createTime" value="${nvixOrderBatch.createTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'createTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="nvixOrderBatch.memo" class="form-control" rows="4">${nvixOrderBatch.memo }</textarea>
						</div>
					</div>
					<div class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>订单明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" placeholder="主题" class="form-control" id="itemName">
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
	var saleOrderItemColumns = [ {
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
	"title" : "要货方",
	"width" : "15%",
	"data" : function(data) {
		return data.channelDistributorName;
	}
	}, {
	"title" : "采购时间",
	"width" : "15%",
	"name" : "createTime",
	"data" : function(data) {
		return data.deliveryTimeStr;
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
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		return update;
	}
	} ];
	var saleOrderItemTable = initDataTable("saleOrderItemTableId", "${nvix}/nvixnt/vixntSalesOrderAction!goRequireGoodsOrderList.action?id=${nvixOrderBatch.id}", saleOrderItemColumns, function(page, pageSize, orderField, orderBy) {
		var itemName = $("#itemName").val();
		itemName = Url.encode(itemName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"itemName" : itemName
		};
	}, '10', '0');

	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntStoreRequireGoodsAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>