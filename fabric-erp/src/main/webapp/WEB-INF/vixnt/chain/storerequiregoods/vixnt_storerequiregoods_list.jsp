<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-institution"></i> 门店管理</span><span>&gt; 门店订单管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a href="#" class="btn btn-primary" onclick="goSaveOrUpdate('');"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</a>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>门店订单列表</h2>
			<ul class="nav nav-tabs pull-right in">
				<li class="active"><a data-toggle="tab" href="#hr1"><span class="hidden-mobile hidden-tablet">待审核</span></a></li>
				<li><a data-toggle="tab" href="#hr2"><span class="hidden-mobile hidden-tablet">待发货</span></a></li>
				<li><a data-toggle="tab" href="#hr3"><span class="hidden-mobile hidden-tablet">待收货</span></a></li>
				<li><a data-toggle="tab" href="#hr4"><span class="hidden-mobile hidden-tablet">待入库</span></a></li>
				<li><a data-toggle="tab" href="#hr5"><span class="hidden-mobile hidden-tablet">已完成</span></a></li>
			</ul>
		</header>
		<div class="tab-content">
			<div class="tab-pane active" id="hr1">
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								主题: <input type="text" class="form-control" id="selectname">
							</div>
							<button onclick="salesOrderTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectname').val('');salesOrderTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="salesOrderTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
			<div class="tab-pane" id="hr2">
				<div class="widget-body no-padding">
					<div id="">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								主题: <input type="text" value="" class="form-control" id="overselectname">
							</div>
							<button onclick="allSalesOrderTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#overselectname').val('');allSalesOrderTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="allSalesOrderTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
			<div class="tab-pane" id="hr3">
				<div class="widget-body no-padding">
					<div id="">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								主题: <input type="text" value="" class="form-control" id="overselectname">
							</div>
							<button onclick="toRevTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#overselectname').val('');toRevTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="toRevTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
			<div class="tab-pane" id="hr4">
				<div class="widget-body no-padding">
					<div id="">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								主题: <input type="text" value="" class="form-control" id="overselectname">
							</div>
							<button onclick="toInvTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#overselectname').val('');toInvTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="toInvTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
			<div class="tab-pane" id="hr5">
				<div class="widget-body no-padding">
					<div id="">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								主题: <input type="text" value="" class="form-control" id="overselectname">
							</div>
							<button onclick="toFinTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#overselectname').val('');toFinTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="toFinTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var salesOrderColumns = [ {
	"title" : "编号",
	"width" : "10%",
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
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "要货人",
	"width" : "15%",
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"title" : "要货时间",
	"width" : "15%",
	"name" : "deliveryTime",
	"data" : function(data) {
		return data.deliveryTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"name" : "status",
	"data" : function(data) {
		if (data.status == 0) {
			return "<span class='label label-danger'>待审核</span>";
		} else if (data.status == 1) {
			return "<span class='label label-warning'>待发货</span>";
		} else if (data.status == 2) {
			return "<span class='label label-info'>待收货</span>";
		} else if (data.status == 3) {
			return "<span class='label label-primary'>待入库</span>";
		} else if (data.status == 4) {
			return "<span class='label label-success'>已完成</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var showPurchaseOrder = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePurchaseOrder('" + data.id + "');\" title='查看子订单'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var confirmReceipt = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goConfirmReceipt('" + data.id + "');\" title='确认收货'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-check'></i></span></a>";
		var inv = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateInbond('" + data.id + "');\" title='入库'><span class='txt-color-blue pull-right'><i class='fa fa-cubes'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + showPurchaseOrder + "&nbsp;&nbsp;" + del;
	}
	} ];
	var salesOrderTable = initDataTable("salesOrderTableId", "${nvix}/nvixnt/vixntStoreRequireGoodsAction!goSingleList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var selectname = $("#selectname").val();
		selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectname" : selectname
		};
	});
	var allSalesOrderColumns = [ {
	"title" : "编号",
	"width" : "10%",
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
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "要货人",
	"width" : "10%",
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"title" : "要货时间",
	"width" : "15%",
	"name" : "deliveryTime",
	"data" : function(data) {
		return data.deliveryTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"name" : "status",
	"data" : function(data) {
		if (data.status == 0) {
			return "<span class='label label-danger'>待审核</span>";
		} else if (data.status == 1) {
			return "<span class='label label-warning'>待发货</span>";
		} else if (data.status == 2) {
			return "<span class='label label-info'>配送中</span>";
		} else if (data.status == 3) {
			return "<span class='label label-primary'>已收货</span>";
		} else if (data.status == 4) {
			return "<span class='label label-success'>已入库</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "20%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var showPurchaseOrder = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePurchaseOrder('" + data.id + "');\" title='查看子订单'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var confirmReceipt = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goConfirmReceipt('" + data.id + "');\" title='确认收货'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-check'></i></span></a>";
		var inv = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateInbond('" + data.id + "');\" title='入库'><span class='txt-color-blue pull-right'><i class='fa fa-cubes'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return showPurchaseOrder;
	}
	} ];
	var allSalesOrderTable = initDataTable("allSalesOrderTableId", "${nvix}/nvixnt/vixntStoreRequireGoodsAction!goAllSingleList.action", allSalesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var selectname = $("#selectname").val();
		selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectname" : selectname
		};
	});
	var toRevColumns = [ {
	"title" : "编号",
	"width" : "10%",
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
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "要货人",
	"width" : "10%",
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"title" : "要货时间",
	"width" : "15%",
	"name" : "deliveryTime",
	"data" : function(data) {
		return data.deliveryTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"name" : "status",
	"data" : function(data) {
		if (data.status == 0) {
			return "<span class='label label-danger'>待审核</span>";
		} else if (data.status == 1) {
			return "<span class='label label-warning'>待发货</span>";
		} else if (data.status == 2) {
			return "<span class='label label-info'>配送中</span>";
		} else if (data.status == 3) {
			return "<span class='label label-primary'>已收货</span>";
		} else if (data.status == 4) {
			return "<span class='label label-success'>已入库</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "20%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var showPurchaseOrder = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePurchaseOrder('" + data.id + "');\" title='查看子订单'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var confirmReceipt = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goConfirmReceipt('" + data.id + "');\" title='确认收货'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-check'></i></span></a>";
		var inv = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateInbond('" + data.id + "');\" title='入库'><span class='txt-color-blue pull-right'><i class='fa fa-cubes'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return showPurchaseOrder + "&nbsp;&nbsp;" + confirmReceipt;
	}
	} ];
	var toRevTable = initDataTable("toRevTableId", "${nvix}/nvixnt/vixntStoreRequireGoodsAction!goToRevList.action", toRevColumns, function(page, pageSize, orderField, orderBy) {
		var selectname = $("#selectname").val();
		selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectname" : selectname
		};
	});
	var toInvColumns = [ {
	"title" : "编号",
	"width" : "10%",
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
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "要货人",
	"width" : "10%",
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"title" : "要货时间",
	"width" : "15%",
	"name" : "deliveryTime",
	"data" : function(data) {
		return data.deliveryTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"name" : "status",
	"data" : function(data) {
		if (data.status == 0) {
			return "<span class='label label-danger'>待审核</span>";
		} else if (data.status == 1) {
			return "<span class='label label-warning'>待发货</span>";
		} else if (data.status == 2) {
			return "<span class='label label-info'>配送中</span>";
		} else if (data.status == 3) {
			return "<span class='label label-primary'>已收货</span>";
		} else if (data.status == 4) {
			return "<span class='label label-success'>已入库</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "20%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var showPurchaseOrder = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePurchaseOrder('" + data.id + "');\" title='查看子订单'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var confirmReceipt = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goConfirmReceipt('" + data.id + "');\" title='确认收货'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-check'></i></span></a>";
		var inv = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateInbond('" + data.id + "');\" title='入库'><span class='txt-color-blue pull-right'><i class='fa fa-cubes'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return showPurchaseOrder + "&nbsp;&nbsp;" + inv;
	}
	} ];
	var toInvTable = initDataTable("toInvTableId", "${nvix}/nvixnt/vixntStoreRequireGoodsAction!goToInvList.action", toInvColumns, function(page, pageSize, orderField, orderBy) {
		var selectname = $("#selectname").val();
		selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectname" : selectname
		};
	});
	var toFinColumns = [ {
	"title" : "编号",
	"width" : "10%",
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
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "要货人",
	"width" : "10%",
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"title" : "要货时间",
	"width" : "15%",
	"name" : "deliveryTime",
	"data" : function(data) {
		return data.deliveryTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"name" : "status",
	"data" : function(data) {
		if (data.status == 0) {
			return "<span class='label label-danger'>待审核</span>";
		} else if (data.status == 1) {
			return "<span class='label label-warning'>待发货</span>";
		} else if (data.status == 2) {
			return "<span class='label label-info'>配送中</span>";
		} else if (data.status == 3) {
			return "<span class='label label-primary'>已收货</span>";
		} else if (data.status == 4) {
			return "<span class='label label-success'>已入库</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "20%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var showPurchaseOrder = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePurchaseOrder('" + data.id + "');\" title='查看子订单'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		var confirmReceipt = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goConfirmReceipt('" + data.id + "');\" title='确认收货'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-check'></i></span></a>";
		var inv = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateInbond('" + data.id + "');\" title='入库'><span class='txt-color-blue pull-right'><i class='fa fa-cubes'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return showPurchaseOrder;
	}
	} ];
	var toFinTable = initDataTable("toFinTableId", "${nvix}/nvixnt/vixntStoreRequireGoodsAction!goToFinList.action", toFinColumns, function(page, pageSize, orderField, orderBy) {
		var selectname = $("#selectname").val();
		selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectname" : selectname
		};
	});
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntStoreRequireGoodsAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function show(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntStoreRequireGoodsAction!show.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goSaveOrUpdatePurchaseOrder(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntStoreRequireGoodsAction!goSaveOrUpdatePurchaseOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goSaveOrUpdateInbond(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntStoreRequireGoodsAction!converterPurchaseOrderToStockrecords.action?requireGoodsOrderId=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goConfirmReceipt(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntStoreRequireGoodsAction!goConfirmReceipt.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntStoreRequireGoodsAction!deleteById.action?id=' + id, '是否删除要货单?', salesOrderTable);
	};
</script>