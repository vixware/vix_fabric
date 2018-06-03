<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> <a data-toggle="modal" href="#">供应商管理 </a><span>> 订单管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;待发货订单 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu js-status-update pull-right">
						<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierSalesOrderAction!goAllToDoDataList.action');">待配货订单列表</a></li>
						<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierSalesOrderAction!goList.action');">待汇总订单列表</a></li>
						<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierSalesOrderAction!goAllDataList.action');">所有订单列表</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>待发货订单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<input type="hidden" id="vehicleId" name="" value="" />
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						主题: <input type="text" value="" class="form-control" id="selectname">
					</div>
					<button onclick="allSalesOrderTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#selectname').val('');allSalesOrderTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="allSalesOrderTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var allSalesOrderColumns = [ {
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
	"width" : "25%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "要货方",
	"width" : "15%",
	"name" : "purchasePerson",
	"data" : function(data) {
		return data.purchasePerson;
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
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePurchaseOrder('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var distribution = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goChooseVehicle('" + data.id + "');\" title='派车配送'><span class='txt-color-blue pull-right'><i class='fa fa-truck'></i></span></a>";
		return update + "&nbsp;&nbsp;" + distribution;
	}
	} ];
	var allSalesOrderTable = initDataTable("allSalesOrderTableId", "${nvix}/nvixnt/vixntSupplierSalesOrderAction!goTadayAllToSellDataList.action", allSalesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var selectname = $("#selectname").val();
		selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectname" : selectname
		};
	});

	//新增
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntSupplierSalesOrderAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goSaveOrUpdatePurchaseOrder(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntSupplierSalesOrderAction!goSaveOrUpdatePurchaseOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function collectRequireGoodsOrder() {
		$.ajax({
		url : '${nvix}/nvixnt/vixntSupplierSalesOrderAction!collectRequireGoodsOrder.action',
		cache : false,
		success : function() {
			salesOrderTable.ajax.reload();
			overSalesOrderTable.ajax.reload();
			supplierSalesOrderTable.ajax.reload();
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntSalesOrderAction!deleteById.action?id=' + id, '是否删除要货单?', salesOrderTable);
	};
	function prepareItemOver(id) {
		updateEntityByConfirm('${nvix}/nvixnt/vixntSupplierSalesOrderAction!prepareItemOver.action?purchaseOrderId=' + id, "确认配货完成!", supplierSalesOrderTable);
	};

	function goChooseVehicle(id) {
		chooseListData('${nvix}/nvixnt/vixntSupplierSalesOrderAction!goChooseVehicle.action', 'single', '选择车辆', 'vehicle', function() {
			$.ajax({
			url : '${nvix}/nvixnt/vixntSupplierSalesOrderAction!sendToVehicle.action?purchaseOrderId=' + id + "&vehicleId=" + $('#vehicleId').val(),
			cache : false,
			success : function() {
				allSalesOrderTable.ajax.reload();
			}
			});
		});
	};

	function exportExcel(id) {
		form = document.getElementById("exportMD");
		form.action = "${nvix}/nvixnt/vixntSupplierSalesOrderAction!exportPurchaseOrderExcel.action?id=" + id;
		form.submit();
	}

	pageSetUp();
</script>