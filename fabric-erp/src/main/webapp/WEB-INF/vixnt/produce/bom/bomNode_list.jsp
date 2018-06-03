<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 生产管理 <span>> BOM管理</span><span>> BOM节点</span>
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
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
					<h2>BOM节点</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> 
					<input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/produce/nvixBomNodeAction!findBomTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							bomNodeTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>BOM节点列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									主题:
									<div class="form-group">
										<input type="text" value="" class="form-control" placeholder="主题" id="searchSubject">
									</div>
									<!-- BOM结构名称:
									<div class="form-group">
										<input type="text" value="" class="form-control" placeholder="BOM结构名称" id="searchBomStructName">
									</div> -->
									<button onclick="bomNodeTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchSubject').val('');$('#searchBomStructName').val('');bomNodeTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
								<table id="bomNodeTable" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
										<th width="8%">编号</th>
										<th>Bom结构</th>
										<th>父Bom节点</th>
										<th>主题</th>
										<!-- th>节点类型</th> -->
										<th>数量</th>
										<th width="8%">操作</th>
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
	pageSetUp();
	var bomNodeColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.bomStructName == '' ? "无" : data.bomStructName;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.parentBomNodeName == '' ? "无" : data.parentBomNodeName;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.subject;
	}
	}, /* {
	"orderable" : false,
	"data" : function(data) {
		return data.nodeType;
	}
	}, */{
	"orderable" : false,
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (null != data.id) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var bomNodeTable = initDataTable("bomNodeTable", "${nvix}/nvixnt/produce/nvixBomNodeAction!goListContent.action", bomNodeColumns, function(page, pageSize, orderField, orderBy) {
		var subject = $("#searchSubject").val();
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		subject = Url.encode(subject);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"subject" : subject,
		"treeType" : treeType,
		"parentId" : parentId
		};
	});

	function saveOrUpdate(id) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		if(!id && !treeType){
			layer.alert("请选择Bom结构或者Bom节点");
			return ;
		}
		openSaveOrUpdateWindow('${nvix}/nvixnt/produce/nvixBomNodeAction!goSaveOrUpdate.action?id=' + id + '&parentId=' + parentId + '&treeType=' + treeType, 'bomNodeForm', id == '' ? '新增BOM节点' : '修改BOM节点', 850, 450, bomNodeTable, null, function() {
			bomNodeTable.ajax.reload();
			var treeNode = zTree.getNodeByTId($("#selectId").val());
			if (null != treeNode) {
				treeNode.isParent = true;
			}
			zTree.reAsyncChildNodes(treeNode, "refresh");
		});
	}
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/produce/nvixBomNodeAction!deleteById.action?id=' + id, '是否删除BOM节点?', bomNodeTable, '', function() {
			var treeNode = zTree.getNodeByTId($("#selectId").val());
			if (null != treeNode) {
				treeNode.isParent = true;
			}
			zTree.reAsyncChildNodes(treeNode, "refresh");
		});
	};
</script>