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
					<input type="hidden" id="requireGoodsOrderStatus" name="requireGoodsOrder.status" value="${requireGoodsOrder.status}" /> 
					<input type="hidden" id="requireGoodsOrderId" name="requireGoodsOrder.id" value="${requireGoodsOrder.id}" /> 
					<input type="hidden" id="salesOrderBizType" name="requireGoodsOrder.bizType" value="${requireGoodsOrder.bizType}" />
					 <input type="hidden" id="channelDistributorId" name="requireGoodsOrder.channelDistributor.id"
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
							<h2>门店订单明细</h2>
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
										<div class="listMenu-float-right" id="addItems">
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
					<div class="form-group">
						<label class="control-label col-md-2">经办人:</label>
						<div class="col-md-3">
							<input class="form-control" id="creator" name="requireGoodsOrder.creator" value="${requireGoodsOrder.creator }" type="text">
						</div>
						<label class="control-label col-md-2">合计:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control validate[custom[number]]" id="requireGoodsOrderAmount" name="requireGoodsOrder.amount" value="${requireGoodsOrder.amount }" type="text"> <span class="input-group-addon">(¥)</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12e">
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
	"title" : "单位",
	"name" : "unit",
	"data" : function(data) {
		return data.unit;
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
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var saleOrderItemTable = initDataTable("saleOrderItemTableId", "${nvix}/nvixnt/vixntStoreRequireGoodsAction!goSaleOrderItemList.action", stockRecordLinesColumns, function(page, pageSize, orderField, orderBy) {
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
	function goChooseChannelDistributor() {
		chooseListData('${nvix}/nvixnt/vixntStoreRequireGoodsAction!goChooseChannelDistributor.action', 'single', '选择门店', 'channelDistributor');
	};
	$("#salesOrderForm").validationEngine();
	function saveOrUpdate() {
		if ($("#salesOrderForm").validationEngine('validate')) {
			$("#salesOrderForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntStoreRequireGoodsAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('drp_storerequiregoods', '${nvix}/nvixnt/vixntStoreRequireGoodsAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	function goSaveOrUpdateSaleOrderItem(id, title) {
		if (id != null && id != "") {
			openWindowForShow('${nvix}/nvixnt/vixntStoreRequireGoodsAction!goSaveOrUpdateSaleOrderItem.action?id=' + id, title, 850, 675);
		} else {
			if ($("#salesOrderForm").validationEngine('validate')) {
				$("#salesOrderForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntStoreRequireGoodsAction!saveOrUpdate.action",
				dataType : "text",
				success : function(requireGoodsOrderId) {
					$('#requireGoodsOrderId').val(requireGoodsOrderId);
					openWindowForShow('${nvix}/nvixnt/vixntStoreRequireGoodsAction!goSaveOrUpdateSaleOrderItem.action?id=' + id, title, 850, 675);
				}
				});
			} else {
				return false;
			}
		}
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntStoreRequireGoodsAction!deleteSaleOrderItemById.action?id=' + id, '是否删除订单明细?', saleOrderItemTable);
	};

	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};
	
	$(function(){
		var status = $("#requireGoodsOrderStatus").val();
		if(status){
			if(status == '0'){
				$("#addItems").attr("style","");
			}else{
				$("#addItems").attr("style","display: none;");
			}
		}else{
			$("#addItems").attr("style","");
		}
	});
</script>