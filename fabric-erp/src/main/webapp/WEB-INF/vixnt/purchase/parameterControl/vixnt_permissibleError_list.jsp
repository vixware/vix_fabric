<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-shopping-cart"></i> 采购管理<span>> 误差规则 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('','新增');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>规则列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="purchasePermissibleErrorTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');purchasePermissibleErrorTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="purchasePermissibleErrorTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var purchasePermissibleErrorColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "编码",
	"name" : "name",
	"width" : "10%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "名称",
	"name" : "name",
	"width" : "10%",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'>"+data.name+"</a>";
	}
	}, {
	"title" : "业务类型",
	"width" : "10%",
	"data" : function(data) {
		if(data.entityType=="PUR_ORDER"){
			return "采购订单";
		}else if(data.entityType == "PUR_PLAN"){
			return "采购计划";
		}else if(data.entityType == "PUR_INQUERY"){
			return "采购询价";
		}else if(data.entityType == "PUR_INBOUND"){
			return "采购入库";
		}else if(data.entityType == "PUR_APPLY"){
			return "采购申请";
		}else if(data.entityType == "PUR_ARRIVAL"){
			return "采购到货";
		}else if(data.entityType == "PUR_INVOICE"){
			return "采购发票";
		}
		return "";
	}
	}, {
	"title" : "单据性质",
	"width" : "10%",
	"data" : function(data) {
		return data.bizTypeName;
	}
	}, {
	"title" : "允许数量偏差百分比",
	"width" : "12%",
	"data" : function(data) {
		return data.number+"%";
	}
	}, {
	"title" : "允许金额偏差百分比",
	"width" : "12%",
	"data" : function(data) {
		return data.money+"%";
	}
	}, {
	"title" : "允许迟到天数",
	"width" : "10%",
	"data" : function(data) {
		return data.beLate+"天";
	}
	}, {
	"title" : "允许早到天数",
	"width" : "10%",
	"data" : function(data) {
		return data.earlyArrival+"天";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {

		var start = "<div class=\"btn-group\"><button class=\"btn btn-success btn-xs dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-gear\"></i> 操作  <span class=\"caret\"></span></button><ul class=\"dropdown-menu pull-right\">";
		var update = "<li><a href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'> <span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span>修改</a></li>";
		var del = "<li><a href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span>删除</a></li>";
		var end = "</ul></div>";
		return start + update + del + end;

		/* var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del; */
	}
	} ];

	var purchasePermissibleErrorTable = initDataTable("purchasePermissibleErrorTableId", "${nvix}/nvixnt/purchase/vixntPurchasePermissibleErrorAction!goSingleList.action", purchasePermissibleErrorColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"name" : searchName,
		"status":'PUR'
		};
	});

	function saveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/purchase/vixntPurchasePermissibleErrorAction!goSaveOrUpdate.action?id=' + id, "purchasePermissibleErrorForm", title, 850, 480, purchasePermissibleErrorTable);
	};

	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchasePermissibleErrorAction!deleteById.action?id=' + id, '是否删除单据类型?', purchasePermissibleErrorTable);
	};
</script>