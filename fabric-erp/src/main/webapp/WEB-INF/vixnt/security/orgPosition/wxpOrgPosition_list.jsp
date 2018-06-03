<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> <a>系统管理</a> <span>> <a>岗位管理 </a></span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="createDefaultBvRel();">
					<i class="fa fa-cog"></i>&nbsp;创建默认上下级关系
				</button>
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增岗位');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>岗位管理</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/nvixntOrgPositionAction!findOrgAndUnitTreeToJson.action",
						autoParam : [ "treeId", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.treeId);
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
						<h2>岗位列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										岗位名称: <input type="text" value="" class="form-control" id="searchCode">
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
	var organizationUnitColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "岗位名称",
	"width" : "80%",
	"data" : function(data) {
		return data.posName;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改岗位');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteEntityById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var organizationUnitTable = initDataTable("organizationUnitTableId", "${nvix}/nvixnt/nvixntOrgPositionAction!goSingleList.action", organizationUnitColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var searchCode = $("#searchCode").val();
		var treeType = $("#selectTreeType").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"treeId" : parentId,
		"treeType" : treeType,
		"searchCode" : searchCode
		};
	});
	function goSaveOrUpdate(id, title) {
		if (id != '') {
			openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntOrgPositionAction!goSaveOrUpdate.action?id=' + id, "organizationUnitForm", title, 750, 450, organizationUnitTable, null, function() {
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if (null != treeNode) {
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
			});
		} else {
			if ($('#selectId').val().substring($('#selectId').val().length - 1, $('#selectId').val().length) == 'O') {
				openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntOrgPositionAction!goSaveOrUpdate.action?id=' + id + '&treeId=' + $('#selectId').val(), "organizationUnitForm", title, 750, 450, organizationUnitTable, null, function() {
					var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
					if (null != treeNode) {
						treeNode.isParent = true;
					}
					zTree.reAsyncChildNodes(treeNode, "refresh");
				});
			} else {
				showMessage("非部门组织不能创建岗位！");
				setTimeout("", 1000);
				return;
			}
		}
	};
	function deleteEntityById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntOrgPositionAction!deleteEntityById.action?id=' + id, "是否删除该岗位!", organizationUnitTable, "删除岗位");
	};

	function createDefaultBvRel() {
		var msgTmp = "";
		var confirmMsg = "确认要创建企业职员上下级关系吗?";
		$.ajax({
		url : "${nvix}/nvixnt/nvixntOrgPositionAction!checkBizViewHasDefaultRelation.action",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			isSuccess = data.isSuccess;
			msgTmp = data.msg;
		}
		});
		if (isSuccess) {
			confirmMsg = "确认要重建企业职员上下级关系吗?"
		}
		if (confirm(confirmMsg)) {
			$.post('${nvix}/nvixnt/nvixntOrgPositionAction!createBizViewHasDefaultRelation.action', "", function(result) {
				showMessage(result);
				setTimeout("", 1000);
			});
		}
	}
</script>