<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> 供应商管理 <span>> 订单管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="collectPurchaseOrder();">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;汇总
					</button>
				</a>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>订单列表</h2>
			<ul class="nav nav-tabs pull-right in" id="">
				<li class="active"><a data-toggle="tab" href="#s1"><span class="hidden-mobile hidden-tablet">待汇总</span></a></li>
				<li><a data-toggle="tab" href="#s2"><span class="hidden-mobile hidden-tablet">待配货</span></a></li>
				<li><a data-toggle="tab" href="#s3"><span class="hidden-mobile hidden-tablet">待发货</span></a></li>
				<li><a data-toggle="tab" href="#s4"><span class="hidden-mobile hidden-tablet">配送中</span></a></li>
				<li><a data-toggle="tab" href="#s5"><span class="hidden-mobile hidden-tablet">已完成</span></a></li>
			</ul>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="" class="tab-content">
					<div class="tab-pane fade active in" id="s1">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								主题: <input type="text" value="" class="form-control" id="selectname">
							</div>
							<button onclick="supplierSalesOrderTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectname').val('');supplierSalesOrderTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="supplierSalesOrderTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<div class="tab-pane fade in" id="s2">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								主题: <input type="text" value="" class="form-control" id="selectname">
							</div>
							<button onclick="tadayAllToDoDataListTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectitemname').val('');tadayAllToDoDataListTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="tadayAllToDoDataListTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<div class="tab-pane fade in" id="s3">
						<input type="hidden" id="vehicleId" name="" value="" />
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								主题: <input type="text" value="" class="form-control" id="selectname">
							</div>
							<button onclick="tadayAllToSellDataTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectname').val('');tadayAllToSellDataTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="tadayAllToSellDataTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<div class="tab-pane fade in" id="s4">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								主题: <input type="text" value="" class="form-control" id="selectname">
							</div>
							<button onclick="tadayAllSellDataListTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectname').val('');tadayAllSellDataListTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="tadayAllSellDataListTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<div class="tab-pane fade in" id="s5">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								主题: <input type="text" value="" class="form-control" id="selectname">
							</div>
							<button onclick="allFinishDataListTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectname').val('');allFinishDataListTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="allFinishDataListTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var supplierSalesOrderColumns = [ {
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
	"width" : "35%",
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
		} else if (data.status == 4) {
			return "<span class='label label-success'>待分拣</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePurchaseOrder('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		return update;
	}
	} ];
	var supplierSalesOrderTable = initDataTable("supplierSalesOrderTableId", "${nvix}/nvixnt/vixntSupplierSalesOrderAction!goTadayDataList.action", supplierSalesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var selectname = $("#selectname").val();
		selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectname" : selectname
		};
	});
	var tadayAllToDoDataListColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "批次编码",
	"width" : "15%",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "主题",
	"width" : "40%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "创建时间",
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
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goShowNvixOrderBatch('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var showPurchaseOrder = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goShowPurchaseOrder('" + data.id + "');\" title='查看门店订货情况'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var orderBatch = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goPrintOrderBatch('" + data.id + "');\" title='打印拣货单'><span class='txt-color-blue pull-right'><i class='fa fa-print'></i></span></a>";
		var exportExcel = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goPrintOrder('" + data.id + "');\" title='打印发货单'><span class='txt-color-blue pull-right'><i class='fa fa-print'></i></span></a>";
		var prepareitemover = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"prepareItemOver('" + data.id + "');\" title='配货完成'><span class='txt-color-blue pull-right'><i class='fa fa-cubes'></i></span></a>";
		//return update + "&nbsp;&nbsp;" + exportExcel + "&nbsp;&nbsp;" + prepareitemover + "&nbsp;&nbsp;" + distribution;
		return update + "&nbsp;&nbsp;" + showPurchaseOrder + "&nbsp;&nbsp;" + orderBatch + "&nbsp;&nbsp;" + exportExcel + "&nbsp;&nbsp;" + prepareitemover;
		//return orderBatch + "&nbsp;&nbsp;" + exportExcel + "&nbsp;&nbsp;" + prepareitemover;
	}
	} ];
	var tadayAllToDoDataListTable = initDataTable("tadayAllToDoDataListTableId", "${nvix}/nvixnt/vixntSupplierSalesOrderAction!goAfterPickingList.action", tadayAllToDoDataListColumns, function(page, pageSize, orderField, orderBy) {
		//var selectname = $("#selectname").val();
		//selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize
		//"selectname" : selectname
		};
	});

	var tadayAllToSellDataColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "批次编码",
	"width" : "15%",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "主题",
	"width" : "35%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "创建时间",
	"width" : "15%",
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "13%",
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
	"width" : "7%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goShowPurchaseOrder('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var distribution = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goChooseVehicle('" + data.id + "');\" title='派车配送'><span class='txt-color-blue pull-right'><i class='fa fa-truck'></i></span></a>";
		return update + "&nbsp;&nbsp;" + distribution;
	}
	} ];
	var tadayAllToSellDataTable = initDataTable("tadayAllToSellDataTableId", "${nvix}/nvixnt/vixntSupplierSalesOrderAction!goToBeShippedList.action", tadayAllToSellDataColumns, function(page, pageSize, orderField, orderBy) {
		//var selectname = $("#selectname").val();
		//selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize
		//"selectname" : selectname
		};
	});
	var allFinishDataListColumns = [ {
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
	"width" : "35%",
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
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePurchaseOrder('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		return update;
	}
	} ];
	var allFinishDataListTable = initDataTable("tadayAllSellDataListTableId", "${nvix}/nvixnt/vixntSupplierSalesOrderAction!goDispatchingList.action", allFinishDataListColumns, function(page, pageSize, orderField, orderBy) {
		//var selectname = $("#selectname").val();
		//selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize
		//"selectname" : selectname
		};
	});
	var tadayAllSellDataListColumns = [ {
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
	"width" : "20%",
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
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePurchaseOrder('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		return update;
	}
	} ];
	var tadayAllSellDataListTable = initDataTable("allFinishDataListTableId", "${nvix}/nvixnt/vixntSupplierSalesOrderAction!goTadayAllSellDataList.action", tadayAllSellDataListColumns, function(page, pageSize, orderField, orderBy) {
		//var selectname = $("#selectname").val();
		//selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize
		//"selectname" : selectname
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
	function goShowNvixOrderBatch(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntSupplierSalesOrderAction!goShowNvixOrderBatch.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goShowPurchaseOrder(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntSupplierSalesOrderAction!goShowPurchaseOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//声明为全局变量
	//var LODOP;
	function collectPurchaseOrder() {
		//LODOP.FORMAT("VOICE:0;50", "您好！您正在汇总订单.");
		updateEntityByConfirm('${nvix}/nvixnt/vixntSupplierSalesOrderAction!pickOrder.action', "确认汇总今日订单吗!", supplierSalesOrderTable, null, function() {
			tadayAllToDoDataListTable.ajax.reload();
		});
	};
	
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntSalesOrderAction!deleteById.action?id=' + id, '是否删除要货单?', salesOrderTable);
	};
	
	function prepareItemOver(id) {
		updateEntityByConfirm('${nvix}/nvixnt/vixntSupplierSalesOrderAction!prepareItemOver.action?id=' + id, "确认配货完成!", tadayAllToDoDataListTable, null, function() {
			tadayAllToSellDataTable.ajax.reload();
		});
	};

	function goChooseVehicle(id) {
		chooseListData('${nvix}/nvixnt/vixntSupplierSalesOrderAction!goChooseVehicle.action', 'single', '选择车辆', 'vehicle', function() {
			$.ajax({
			url : '${nvix}/nvixnt/vixntSupplierSalesOrderAction!sendToVehicle.action?id=' + id + "&vehicleId=" + $('#vehicleId').val(),
			cache : false,
			success : function() {
				supplierSalesOrderTable.ajax.reload();
				tadayAllSellDataListTable.ajax.reload();
			}
			});
		});
	};

	function exportExcel(id) {
		form = document.getElementById("exportMD");
		form.action = "${nvix}/nvixnt/vixntSupplierSalesOrderAction!exportPurchaseOrderExcel.action?id=" + id;
		form.submit();
	}
	function goPrintOrder(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntSupplierSalesOrderAction!goPrintOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			LODOP = getLodop();
			LODOP.SET_PRINT_PAGESIZE(3, "240mm", "45mm", "");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
			LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
			LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
			LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);//打印后自动关闭预览窗口
			LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
			LODOP.SET_PREVIEW_WINDOW(1, 2, 0, 1024, 550, "");
			/* LODOP.PRINT(); */
			LODOP.PREVIEW();
		}
		});
	};
	function goPrintOrderBatch(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntSupplierSalesOrderAction!goPrintOrderBatch.action?id=' + id,
		cache : false,
		success : function(html) {
			LODOP = getLodop();
			LODOP.SET_PRINT_PAGESIZE(3, "240mm", "45mm", "");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
			LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
			LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
			LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);//打印后自动关闭预览窗口
			LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
			LODOP.SET_PREVIEW_WINDOW(1, 2, 0, 1024, 750, "");
			/* LODOP.PRINT(); */
			LODOP.PREVIEW();
		}
		});
	};
</script>