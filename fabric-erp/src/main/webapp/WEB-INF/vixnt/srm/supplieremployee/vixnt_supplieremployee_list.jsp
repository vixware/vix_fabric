<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-user"></i> 供应商管理<span> > 供应商员工管理 </span>
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
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>员工列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						姓名: <input type="text" value="" class="form-control" id="employeeName">
					</div>
					<button onclick="supplierEmployeeTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#employeeName').val('');supplierEmployeeTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="supplierEmployeeTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var supplierEmployeeColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}/* , {
	"title" : "员工职号",
	"data" : function(data) {
		return data.staffJobNumber;
	}
	} */, {
	"title" : "姓名",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "联系电话",
	"data" : function(data) {
		return data.telephone;
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update  + "&nbsp;&nbsp;" + del;
	}
	} ];

	var supplierEmployeeTable = initDataTable("supplierEmployeeTableId", "${nvix}/nvixnt/vixntSupplierEmployeeAction!goSingleList.action", supplierEmployeeColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var employeeName = $("#employeeName").val();
		employeeName = Url.encode(employeeName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"treeType" : treeType,
		"employeeName" : employeeName
		};
	}, 10, '0');
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierEmployeeAction!deleteById.action?id=' + id, '是否删除人员?', supplierEmployeeTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntSupplierEmployeeAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>