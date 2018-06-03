<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-file"></i> 文件<span>> 文件分类管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
					<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/nvixFileAction!goList.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" data-widget-editbutton="false" data-widget-deletebutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>分类列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										名称: <input type="text" value="" class="form-control" id="searchName">
									</div>
									<button onclick="uploaderTypeTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchName').val('');uploaderTypeTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="uploaderTypeTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var uploaderTypeColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "分类名称",
	"width" : "80%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteUploaderTypeById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var uploaderTypeTable = initDataTable("uploaderTypeTableId", "${nvix}/nvixnt/nvixFileAction!goUploaderTypePager.action", uploaderTypeColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchName
		};
	});
	$("#uploaderTypeForm").validationEngine();
	//添加附件分类
	function goSaveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixFileAction!goSaveOrUpdateUploaderType.action?id=' + id, "uploaderTypeForm", title, 650, 250, uploaderTypeTable);
	};
	function deleteUploaderTypeById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixFileAction!deleteUploaderTypeById.action?id=' + id, '是否删除分类?', uploaderTypeTable);
	};
</SCRIPT>