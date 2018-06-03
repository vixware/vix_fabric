<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-puzzle-piece"></i> 连锁管理<span> > 供应商商品管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntAllocationItemAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>供应商</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> 
					<input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/vixntAllocationItemAction!findSupplierTree.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							storeItemTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>商品列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									编码:
									<div class="form-group">
										<input type="text" value="" class="form-control" id="iCode">
									</div>
									名称:
									<div class="form-group">
										<input type="text" value="" class="form-control" id="iName">
									</div>
									<button onclick="storeItemTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#iCode').val('');$('#iName').val('');storeItemTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="storeItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var storeItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "编码",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "名称",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "规格",
	"name" : "specification",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"title" : "sku编码",
	"name" : "skuCode",
	"data" : function(data) {
		return data.skuCode;
	}
	}, {
	"title" : "销售价格",
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"title" : "会员价格",
	"name" : "vipPrice",
	"data" : function(data) {
		return data.vipPrice;
	}
	}, {
	"title" : "采购价格",
	"name" : "purchasePrice",
	"data" : function(data) {
		return data.purchasePrice;
	}
	}, {
	"title" : "供应商",
	"data" : function(data) {
		return data.supplierName;
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"isUsed('" + data.id + "');\" title='启用'><span class='txt-color-blue pull-right'><i class='fa fa-dot-circle-o'></i></span></a>";
		return update;
	}
	} ];

	var storeItemTable = initDataTable("storeItemTableId", "${nvix}/nvixnt/vixntAllocationItemAction!goSupplierItemSingleList.action", storeItemColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#iName").val();
		searchName = Url.encode(searchName);
		var treeId = $("#selectId").val();
		treeId = Url.encode(treeId);
		var treeType = $("#selectTreeType").val();
		treeType = Url.encode(treeType);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"treeId" : treeId,
		"treeType" : treeType,
		"searchName" : searchName
		};
	}, 10, '0');

	function isUsed(id) {
		updateEntityByConfirm('${nvix}/nvixnt/vixntSupplierItemAction!isUsed.action?id=' + id, "启用!", storeItemTable);
	};
</script>