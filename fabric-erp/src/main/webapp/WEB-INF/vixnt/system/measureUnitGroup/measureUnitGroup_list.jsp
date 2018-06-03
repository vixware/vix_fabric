<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 基础设置 <span>> 计量单位 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>计量单位列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input id="name" type="text" value="" class="form-control">
						</div>
						<button onclick="measureUnitGroupTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#name').val('');measureUnitGroupTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="measureUnitGroupTable" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var measureUnitGroupColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "编码",
	"width" : "20%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "名称",
	"width" : "20%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "类别",
	"width" : "20%",
	"data" : function(data) {
		if (null == data.type) {
			return '';
		}
		if (data.type == '1') {
			return '无换算率';
		}
		if (data.type == '2') {
			return '固定换算率';
		}
		if (data.type == '3') {
			return '浮动换算率';
		}
	}
	}, {
	"title" : "状态",
	"width" : "20%",
	"data" : function(data) {
		return null == data.status ? '' : data.status == '1' ? '启用' : '禁用';
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		if (null == data.id) {
			return '';
		}
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var measureUnitGroupTable = initDataTable("measureUnitGroupTable", "${nvix}/nvixnt/nvixntMeasureUnitGroupAction!getMeasureUnitJson.action", measureUnitGroupColumns, function(page, pageSize, orderField, orderBy) {
		var id = $('#id').val();
		var name = $("#name").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name,
		"id" : id
		};
	});

	$("#measureUnitGroupForm").validationEngine();
	//新增编辑常量
	function saveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntMeasureUnitGroupAction!goSaveOrUpdate.action?id=' + id, "measureUnitGroupForm", "计量单位组", 850, 600, measureUnitGroupTable);
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntMeasureUnitGroupAction!deleteById.action?id=' + id, '是否删除该计量单位?', measureUnitGroupTable);
	};
</SCRIPT>