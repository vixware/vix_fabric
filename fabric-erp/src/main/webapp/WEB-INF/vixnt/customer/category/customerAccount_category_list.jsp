<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i>客户关系管理 <span>>客户管理 </span><span>>客户分类 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp; 新增
					</button>
					<!-- <button class="btn btn-primary" type="button" onclick="$('#fileToUpload').click()">
						<i class="glyphicon glyphicon-import"></i>&nbsp;导入
					</button>
					<button class="btn btn-primary" type="button" onclick="downloadTemplate()">
						<i class="glyphicon glyphicon-save"></i>&nbsp;模板下载
					</button> -->
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>客户分类</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> 
					<input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/nvixCustomerCategoryAction!findTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							customerAccountCategoryTable.ajax.reload();
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
										<input type="text" value="" class="form-control" id="searchCode">
									</div>
									名称:
									<div class="form-group">
										<input type="text" value="" class="form-control" id="searchName">
									</div>
									<button onclick="customerAccountCategoryTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchCode').val('');$('#searchName').val('');customerAccountCategoryTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
								<table id="customerAccountCategoryTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
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
	var customerAccountCategoryColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "编码",
	"name" : "code",
	"width" : "15%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "名称",
	"name" : "name",
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

	var customerAccountCategoryTable = initDataTable("customerAccountCategoryTableId", "${nvix}/nvixnt/nvixCustomerCategoryAction!getCustomerAccountCategoryJson.action", customerAccountCategoryColumns, function(page, pageSize, orderField, orderBy) {
		var catalogCode = $("#searchCode").val();
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		var parentId = $("#selectId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : searchName,
		"code" : catalogCode,
		"parentId" : parentId
		};
	});

	function saveOrUpdate(id) {
		var parentId = $("#selectId").val();
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerCategoryAction!goSaveOrUpdate.action?id=' + id + '&parentId=' + parentId, 'customerAccountCategoryForm', id == '' ? '新增客户分类' : '修改客户分类', 650, 280, customerAccountCategoryTable, null, function() {
			customerAccountCategoryTable.ajax.reload();
			var treeNode = zTree.getNodeByTId($("#selectId").val());
			if (null != treeNode) {
				treeNode.isParent = true;
			}
			zTree.reAsyncChildNodes(treeNode, "refresh");
		});
	}
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerCategoryAction!deleteById.action?id=' + id, '是否删除该客户分类?', customerAccountCategoryTable, '', function() {
			var treeNode = zTree.getNodeByTId($("#selectId").val());
			if (null != treeNode) {
				treeNode.isParent = true;
			}
			zTree.reAsyncChildNodes(treeNode, "refresh");
		});
	};
</script>