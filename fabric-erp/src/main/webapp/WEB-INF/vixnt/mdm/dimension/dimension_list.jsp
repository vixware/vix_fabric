<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 商品管理<span>> 维度管理 </span>
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-12 text-align-right">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>分类</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							itemsTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>维度列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										<input type="text" value="" placeholder="名称" class="form-control" id="searchName">
									</div>
									<button onclick="dimensionTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="zTree.cancelSelectedNode();$('#selectId').val('');$('#searchName').val('');dimensionTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
								<table id="dimension" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th width="10%">编号</th>
											<th width="25%">分类</th>
											<th width="35%">名称</th>
											<th width="10%">顺序</th>
											<th width="10%">操作</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var dimensionColumns = [ {
	"name" : "id",
	"data" : function(data) {
		return "";
	}
	}, {
	"name" : "itemCatalog.name",
	"data" : function(data) {
		return data.itemCatalogName;
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'>"+data.name+"</a>";
	}
	}, {
	"name" : "orderBy",
	"data" : function(data) {
		return data.orderBy;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var dimensionTable = initDataTable("dimension", "${nvix}/nvixnt/mdm/nvixDimensionAction!goSingleList.action", dimensionColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchName").val();
		name = Url.encode(name);
		var productCategoryId = $("#selectId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name,
		"id" : productCategoryId
		};
	});

	function saveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixDimensionAction!goSaveOrUpdate.action?id=' + id + '&itemCatalogId=' + $("#selectId").val(), "dimensionForm", id == '' ? "新增维度" : "修改维度", 720, 500, dimensionTable);
	};

	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixDimensionAction!deleteById.action?id=' + id, '是否删除维度管理?', dimensionTable);
	}

	/** 页面加载完调用 */
	pageSetUp();
</script>