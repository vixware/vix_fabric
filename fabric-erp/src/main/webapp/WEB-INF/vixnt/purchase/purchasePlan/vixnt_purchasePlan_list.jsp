<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i>采购管理 <span>&gt; 采购计划</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>采购计划列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							主题: <input type="text" value="" class="form-control" id="mytitle">
						</div>
						<button onclick="purchaseOrderTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#mytitle').val('');purchaseOrderTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="purchaseOrderTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var purchaseOrderColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "编码",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "主题",
	"name" : "purchasePlanName",
	"data" : function(data) {
		return data.purchasePlanName;
	}
	}, {
	"title" : "类型",
	"name" : "style",
	"data" : function(data) {
		if (data.style == '1') {
			return "<span class='label label-primary'>年计划</span>";
		} else if (data.style == '2') {
			return "<span class='label label-success'>季计划</span>";
		} else if (data.style == '3') {
			return "<span class='label label-warning'>月计划</span>";
		}
		return "";
	}
	}, {
	"title" : "编制人",
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"title" : "采购数量",
	"name" : "amount",
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"title" : "采购金额",
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"title" : "编写日期",
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "审批状态",
	"name" : "approvalStatus",
	"data" : function(data) {
		if(data.approvalStatus == '0'){
			return "<span class='label label-warning'>待提交</span>";
		}else if(data.approvalStatus == '1'){
			return "<span class='label label-primary'>审批中</span>";
		}else if(data.approvalStatus == '2'){
			return "<span class='label label-success'>审批完成</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var create = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"createPurchaseOrder('" + data.id + "');\" title='生成订单'><span class='txt-color-blue pull-right'><i class='fa fa-exchange'></i></span></a>";
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		if (data.status == "2") {
			return show + "&nbsp;&nbsp;" + del
		}
		return update + "&nbsp;&nbsp;" + create + "&nbsp;&nbsp;" + del;
	}
	} ];
	var purchaseOrderTable = initDataTable("purchaseOrderTableId", "${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goSingleList.action", purchaseOrderColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#mytitle").val();
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title
		};
	});

	//新增
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//新增
	function show(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchasePlanAction!show.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goSaveOrUpdateByStoreOrder(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goSaveOrUpdateByStoreOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goSaveOrUpdateInbond(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntInboundWarehouseAction!converterPurchaseOrderToStockrecords.action?purchaseOrderid=' + id,
		cache : false,
		success : function(stockRecordsId) {
			$.ajax({
			url : '${nvix}/nvixnt/vixntInboundWarehouseAction!goSaveOrUpdate.action?id=' + stockRecordsId,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchasePlanAction!deleteById.action?id=' + id, '是否删除采购计划?', purchaseOrderTable);
	};
	//通过采购计划生成采购订单
	function createPurchaseOrder(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchasePlanAction!createPurchaseOrder.action?id=' + id,
		cache : false,
		success : function(result) {
			var r = result.split(":");
			if (r[0] == "0") {
				showMessage(r[1]);
				loadContent('purchase_purchasePlan', '${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goList.action');
			} else {
				$.ajax({
				url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goSaveOrUpdate.action?id=' + r[2],
				cache : false,
				success : function(html) {
					$("#mainContent").html(html);
				}
				});
			}
		}
		});
	}
</script>