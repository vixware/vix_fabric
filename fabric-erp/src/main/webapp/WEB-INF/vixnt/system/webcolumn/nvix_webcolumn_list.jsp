<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cog"></i> 系统管理 <span>> 门户配置 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>栏目列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="homeColumnTable.ajax.reload();" type="button" class="btn btn-primary">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');homeColumnTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="homeColumnColumnTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var homeColumnColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "类型",
	"width" : "30%",
	"data" : function(data) {
		if (data.typeCode == "LBT_COLUMN") {
			return "<span class='label label-info'>首页轮播图</span>";
		}
		return "";
	}
	}, {
	"title" : "名称",
	"width" : "30%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "是否启用",
	"width" : "20%",
	"data" : function(data) {
		if (data.status == 'Y') {
			return "<span class='label label-info'>是</span>";
		} else if (data.status == 'N') {
			return "<span class='label label-primary'>否</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"orderable" : false,
	"width" : "10%",
	"data" : function(data) {
		var set = "";
		if (data.status == 'Y') {
			set = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"disabledHomeColumnById('" + data.id + "');\" title='禁用'><span class='txt-color-blue pull-right'><i class='fa fa-times-circle-o'></i></span></a>";
		} else {
			set = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"enableHomeColumnById('" + data.id + "');\" title='启用'><span class='txt-color-blue pull-right'><i class='fa fa-times-circle-o'></i></span></a>";
		}
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + set + "&nbsp;&nbsp;" + del;
	}
	} ];

	var homeColumnTable = initDataTable("homeColumnColumnTableId", "${nvix}/nvixnt/system/nvixntWebColumnAction!goSingleList.action", homeColumnColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchName" : searchName
		};
	});

	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/system/nvixntWebColumnAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//禁用
	function disabledHomeColumnById(id) {
		updateEntityByConfirm('${nvix}/nvixnt/system/nvixntWebColumnAction!disabledHomeColumnById.action?id=' + id, '确定禁用吗?', homeColumnTable);
	};
	//启用
	function enableHomeColumnById(id) {
		updateEntityByConfirm('${nvix}/nvixnt/system/nvixntWebColumnAction!enableHomeColumnById.action?id=' + id, '确定启用吗?', homeColumnTable);
	};
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/system/nvixntWebColumnAction!deleteById.action?id=' + id, '是否删除该栏目?', homeColumnTable);
	};
</SCRIPT>