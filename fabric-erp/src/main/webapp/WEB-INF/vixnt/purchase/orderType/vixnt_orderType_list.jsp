<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-shopping-cart"></i> 采购管理<span>> 单据类型 </span>
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
			<h2>单据类型列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="orderTypeTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');orderTypeTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="orderTypeTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var orderTypeColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "编码",
	"name" : "name",
	"width" : "20%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "名称",
	"name" : "name",
	"width" : "20%",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'>"+data.name+"</a>";
	}
	}, {
	"title" : "描述",
	"width" : "20%",
	"data" : function(data) {
		return data.memo;
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

	var orderTypeTable = initDataTable("orderTypeTableId", "${nvix}/nvixnt/purchase/vixntPurchaseOrderTypeAction!goSingleList.action", orderTypeColumns, function(page, pageSize, orderField, orderBy) {
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
		openSaveOrUpdateWindow('${nvix}/nvixnt/purchase/vixntPurchaseOrderTypeAction!goSaveOrUpdate.action?id=' + id, "orderTypeForm", title, 850, 350, orderTypeTable);
	};

	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchaseOrderTypeAction!deleteById.action?id=' + id, '是否删除单据类型?', orderTypeTable);
	};
</script>