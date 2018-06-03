<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-truck"></i> 供应商管理<span>> 车辆管理 </span>
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
			<h2>车辆列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						车牌号: <input type="text" value="" class="form-control" id="searchvehicleno" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#searchvehicleno').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="vehicleTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var vehicleColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "车辆编号",
	"width" : "20%",
	"data" : function(data) {
		return data.vehicleCode;
	}
	}, {
	"title" : "车牌号",
	"width" : "20%",
	"data" : function(data) {
		return data.vehicleNO;
	}
	}, {
	"title" : "司机",
	"width" : "20%",
	"data" : function(data) {
		return data.driver;
	}
	}, {
	"title" : "联系方式",
	"width" : "15%",
	"data" : function(data) {
		return data.driverTelephone;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"data" : function(data) {
		if (data.status == '1') {
			return "<span class='label label-info'>空闲</span>";
		} else if (data.status == '2') {
			return "<span class='label label-primary'>占用</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var vehicleTable = initDataTable("vehicleTableId", "${nvix}/nvixnt/vixntVehicleAction!goSingleList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var searchvehicleno = $("#searchvehicleno").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchvehicleno" : searchvehicleno
		};
	}, 10);

	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntVehicleAction!deleteById.action?id=' + id, '是否删除车辆?', vehicleTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntVehicleAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>