<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-cubes"></i>库存管理 <span>>调拨管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
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
			<h2>调拨单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							<input type="text" value="" placeholder="主题" class="form-control" id="searchName" style="width: 250px;">
						</div>
						<button onclick="allocateTransferTable.ajax.reload();" type="button" class="btn btn-primary">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');allocateTransferTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="allocateTransferTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var allocateTransferColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "调拨单号",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "主题",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "转出仓库",
	"orderable" : false,
	"data" : function(data) {
		return data.outInvWarehouseName;
	}
	}, {
	"title" : "转入仓库",
	"orderable" : false,
	"data" : function(data) {
		return data.inInvWarehouseName;
	}
	}, {
	"title" : "调拨时间",
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"title" : "调拨人",
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var allocateTransferTable = initDataTable("allocateTransferTableId", "${nvix}/nvixnt/vixntAllocateTransferAction!goSingleList.action", allocateTransferColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchName" : searchName
		};
	}, 10, '0');
	//新增
	function goSaveOrUpdate(id) {
		goSaveOrUpdateEntity("${nvix}/nvixnt/vixntAllocateTransferAction!goSaveOrUpdate.action?id=" + id);
	};
	//删除调拨单
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntAllocateTransferAction!deleteById.action?id=' + id, '是否删除调拨单?', allocateTransferTable);
	};
</script>