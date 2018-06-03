<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-institution"></i> 门店管理<span>> 提成规则管理 </span>
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
			<h2>提成规则列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							规则名称: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="employeeTypeTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');employeeTypeTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="employeeTypeTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	var employeeTypeColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "名称",
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "提成类型",
	"width" : "20%",
	"data" : function(data) {
		if (data.percentageType == 1) {
			return "<span class='label label-warning'>固定金额</span>";
		} else if (data.percentageType == 2) {
			return "<span class='label label-info'>销售比例</span>";
		}
		return "";
	}
	}, {
	"title" : "结算类型",
	"width" : "20%",
	"data" : function(data) {
		if (data.settlementsType == 1) {
			return "<span class='label label-warning'>订单</span>";
		} else if (data.settlementsType == 2) {
			return "<span class='label label-info'>金额</span>";
		}
		return "";
	}
	}, {
	"title" : "是否启用",
	"width" : "20%",
	"data" : function(data) {
		if (data.enabled == 0) {
			return "<span class='label label-warning'>是</span>";
		} else if (data.enabled == 1) {
			return "<span class='label label-info'>否</span>";
		}
		return "";
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
	}
	} ];

	var employeeTypeTable = initDataTable("employeeTypeTableId", "${nvix}/nvixnt/vixntPercentageAction!goSingleList.action", employeeTypeColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchName" : searchName
		};
	});
	function saveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntPercentageAction!goSaveOrUpdate.action?id=' + id, "employeeTypeForm", title, 850, 550, employeeTypeTable);
	};
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntPercentageAction!deleteById.action?id=' + id, '是否删除提成规则?', employeeTypeTable);
	};
</script>