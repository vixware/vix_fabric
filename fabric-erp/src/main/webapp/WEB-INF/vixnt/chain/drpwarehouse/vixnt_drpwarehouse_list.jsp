<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cubes"></i> 门店管理<span>> 业务管理 </span><span>> 仓库管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>仓库列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							仓库名称: <input type="text" value="" class="form-control" id="selectName">
						</div>
						<button onclick="invWarehouseTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#selectName').val('');invWarehouseTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="invWarehouseTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var invWarehouseColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "编码",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "名称",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "地址",
	"data" : function(data) {
		return data.postion;
	}
	}, {
	"title" : "默认",
	"data" : function(data) {
		if (data.isDefault == 0) {
			return "<span class='label label-primary'>是</span>";
		} else if (data.isDefault == 1) {
			return "<span class='label label-default'>否</span>";
		}
		return "";
	}
	}, {
	"title" : "创建时间",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var addshelf = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goInvShelfList('" + data.id + "');\" title='货位管理'><span class='txt-color-blue pull-right'><i class='fa fa-cubes'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + addshelf + "&nbsp;&nbsp;" + del;
	}
	} ];

	var invWarehouseTable = initDataTable("invWarehouseTableId", "${nvix}/nvixnt/vixntDrpWarehouseAction!goSingleList.action", invWarehouseColumns, function(page, pageSize, orderField, orderBy) {
		var selectName = $("#selectName").val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectName" : selectName
		};
	}, 10, '0');
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntDrpWarehouseAction!deleteById.action?id=' + id, '是否删除仓库?', invWarehouseTable);
	};
	//新增仓库
	$("#invWarehouseForm").validationEngine();
	function goSaveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntDrpWarehouseAction!goSaveOrUpdate.action?id=' + id, "invWarehouseForm", title, 900, 515, invWarehouseTable);
	};
	function goInvShelfList(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntDrpWarehouseAction!goInvShelfList.action?wareHouseId=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>