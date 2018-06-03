<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-book"></i> <a style="cursor: pointer;">合同管理</a><span>> 劳动合同管理 </span>
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
			<h2>劳动合同列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						名称: <input type="text" value="" class="form-control" id="supplierName" style="width: 250px;">
					</div>
					<button onclick="supplierTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#supplierName').val('');supplierTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="supplierTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	var supplierColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "编码",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "合同类型",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "金额",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "经办人",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "批准人",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "签订日期",
	"data" : function(data) {
		return data.code;
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

	var supplierTable = initDataTable("supplierTableId", "${nvix}/nvixnt/contract/vixntLaborContractAction!goSingleList.action", supplierColumns, function(page, pageSize, orderField, orderBy) {
		var supplierName = $("#supplierName").val();
		supplierName = Url.encode(supplierName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"supplierName" : supplierName
		};
	}, 10, '0');

	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/contract/vixntLaborContractAction!deleteById.action?id=' + id, '是否删除协议?', supplierTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/contract/vixntLaborContractAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>