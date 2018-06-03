<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="wareHouseId" name="wareHouseId" value="${wareHouseId}" />
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cubes"></i> 库存管理<span> > 货位管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdateShelf('','新增货位');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntWarehouseAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>货位列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							货位名称: <input type="text" value="" class="form-control" id="shelfName">
						</div>
						<button onclick="invShelfTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#shelfName').val('');invShelfTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="invShelfTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var invShelfColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "仓库名称",
	"data" : function(data) {
		return data.warehouseName;
	}
	}, {
	"title" : "货位编码",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "货位名称",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "是否默认货位",
	"data" : function(data) {
		if (data.isDefault == 0) {
			return "<span class='label label-primary'>是</span>";
		} else if (data.isDefault == 1) {
			return "<span class='label label-default'>否</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateShelf('" + data.id + "','修改');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteShelf('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var invShelfTable = initDataTable("invShelfTableId", "${nvix}/nvixnt/vixntWarehouseAction!goInvShelfSingleList.action", invShelfColumns, function(page, pageSize, orderField, orderBy) {
		var wareHouseId = $("#wareHouseId").val();
		var shelfName = $("#shelfName").val();
		shelfName = Url.encode(shelfName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"shelfName" : shelfName,
		"wareHouseId" : wareHouseId
		};
	}, 10, '0');
	//删除
	function deleteShelf(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntWarehouseAction!doListInvShelfById.action?id=' + id, '是否删除货位?', invShelfTable);
	};
	//新增货位
	$("#invShelfForm").validationEngine();
	function goSaveOrUpdateShelf(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntWarehouseAction!goSaveOrUpdateShelf.action?parentId=' + $('#wareHouseId').val() + "&id=" + id, "invShelfForm", title, 900, 425, invShelfTable);
	};

</script>