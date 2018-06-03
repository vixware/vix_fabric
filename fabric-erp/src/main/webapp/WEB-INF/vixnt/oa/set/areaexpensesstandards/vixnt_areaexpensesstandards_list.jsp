<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-file-text-o"></i> 办公 <span>> 报销管理 </span><span>> 交通报销标准 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('','新增');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>交通报销标准列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="transportTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');transportTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="transportTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var transportColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "名称",
	"name" : "standardName",
	"data" : function(data) {
		return data.standardName;
	}
	}, {
	"title" : "职位",
	"data" : function(data) {
		return data.orgPositionName;
	}
	}, {
	"title" : "交通工具",
	"data" : function(data) {
		return data.transportName;
	}
	}, {
	"title" : "区域",
	"data" : function(data) {
		return data.areaLevelName;
	}
	}, {
	"title" : "报销标准",
	"name" : "reimbursementAmount",
	"data" : function(data) {
		return data.reimbursementAmount;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var transportTable = initDataTable("transportTableId", "${nvix}/nvixnt/nvixAreaExpensesStandardsAction!goSingleList.action", transportColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchName" : searchName
		};
	});
	function saveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixAreaExpensesStandardsAction!goSaveOrUpdate.action?id=' + id, "transportForm", title, 750, 450, transportTable);
	};
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixAreaExpensesStandardsAction!deleteById.action?id=' + id, '是否删除交通报销标准?', transportTable);
	};
</script>