<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-truck"></i> <a>供应商管理</a> <span>>供应商分类管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp; 新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>供应商分类</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/vixntSupplierCategoryAction!findTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							supplierCategoryTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>分类列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									编码:
									<div class="form-group">
										<input type="text" value="" class="form-control" id="searchCode" />
									</div>
									名称:
									<div class="form-group">
										<input type="text" value="" class="form-control" id="searchSupplierCategoryName">
									</div>
									<button onclick="supplierCategoryTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchCode').val('');$('#searchSupplierCategoryName').val('');supplierCategoryTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
								<table id="supplierCategoryTable" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var supplierCategoryColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "父分类",
	"width" : "20%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "编码",
	"width" : "15%",
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
	"orderable" : false,
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var supplierCategoryTable = initDataTable("supplierCategoryTable", "${nvix}/nvixnt/vixntSupplierCategoryAction!getSupplierCategoryJson.action", supplierCategoryColumns, function(page, pageSize, orderField, orderBy) {
		var searchCode = $("#searchCode").val();
		var searchName = $("#searchSupplierCategoryName").val();
		var parentId = $("#selectId").val();
		parentId = Url.encode(parentId);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchName,
		"searchCode" : searchCode,
		"parentId" : parentId
		};
	});

	function saveOrUpdate(id) {
		var parentId = $("#selectId").val();
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntSupplierCategoryAction!goSaveOrUpdate.action?id=' + id + '&parentId=' + parentId, 'supplierCategoryForm', id == '' ? '新增分类' : '修改分类', 850, 300, supplierCategoryColumns, null, function() {
			supplierCategoryTable.ajax.reload();
			var treeNode = zTree.getNodeByTId($("#selectId").val());
			if (null != treeNode) {
				treeNode.isParent = true;
			}
			zTree.reAsyncChildNodes(treeNode, "refresh");
		});
	}
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierCategoryAction!deleteById.action?id=' + id, '是否删除该分类?', supplierCategoryTable, '', function() {
			var treeNode = zTree.getNodeByTId($("#selectId").val());
			if (null != treeNode) {
				treeNode.isParent = true;
			}
			zTree.reAsyncChildNodes(treeNode, "refresh");
		});
	};
</script>