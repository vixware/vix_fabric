<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cog"></i> 系统管理 <span>> 部门管理 </span>
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
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>部门管理</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectIdTreeId").val(treeNode.tId);
							$("#selectTreeType").val(treeNode.treeType);
							organizationUnitTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>部门列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										部门名称: <input type="text" value="" class="form-control" id="searchCode">
									</div>
									<button onclick="organizationUnitTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchCode').val('');organizationUnitTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="organizationUnitTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var organizationUnitColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "名称",
	"width" : "30%",
	"data" : function(data) {
		return data.fs;
	}
	}, {
	"title" : "全称",
	"width" : "50%",
	"data" : function(data) {
		return data.fullName;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteEntityById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var organizationUnitTable = initDataTable("organizationUnitTableId", "${nvix}/nvixnt/wxpOrganizationUnitAction!goSingleList.action", organizationUnitColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var searchCode = $("#searchCode").val();
		searchCode = Url.encode(searchCode);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"parentId" : parentId,
		"treeType" : treeType,
		"searchCode" : searchCode
		};
	});

	function goSaveOrUpdate(id) {
		if (id != '' && id != null) {
			openSaveOrUpdateWindow('${nvix}/nvixnt/wxpOrganizationUnitAction!goSaveOrUpdate.action?id=' + id, "organizationUnitForm", "修改部门", 750, 250, organizationUnitTable);
		} else {
			if ($("#selectTreeType").val() != '' && $("#selectTreeType").val() != null) {
				openSaveOrUpdateWindow('${nvix}/nvixnt/wxpOrganizationUnitAction!goSaveOrUpdate.action?parentId=' + $('#selectId').val() + "&treeType=" + $('#selectTreeType').val() + "&id=" + id, "organizationUnitForm", "新增部门", 750, 250, organizationUnitTable, null, function() {
					var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
					if (null != treeNode) {
						treeNode.isParent = true;
					}
					zTree.reAsyncChildNodes(treeNode, "refresh");
					$("#selectId").val('');
					$("#selectTreeType").val('');
				});
			} else {
				layer.alert("请选择公司或部门!");
			}
		}
	};
	function deleteEntityById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/wxpOrganizationUnitAction!deleteEntityById.action?id=' + id, "是否删除该部门!", organizationUnitTable, "删除部门");
	};
</script>