<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cubes"></i> 供应商管理<span>> 供应商管理库存 </span>
			</h1>
		</div>
	</div>
	<section>
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>门店</h2>
					<ul id="tree" class="ztree" style="height: 450px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /><input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/vixntDistributionSystemAction!findOrgAndUnitTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							inventoryCurrentStockTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>商品列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<table id="inventoryCurrentStockTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th class="hasinput"><input type="text" class="form-control" placeholder="编号" /></th>
										<th class="hasinput"><input type="text" class="form-control" placeholder="商品编码" id="itemcode"></th>
										<th class="hasinput"><input type="text" class="form-control" placeholder="SKU编码" id="skuCode" /></th>
										<th class="hasinput"><input type="text" class="form-control" placeholder="商品名称" id="itemname" /></th>
										<th class="hasinput"><input type="text" class="form-control" placeholder="库存数量" /></th>
										<th class="hasinput"><input type="text" class="form-control" placeholder="仓库" /></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var inventoryCurrentStockColumns = [ {
	"width" : "8%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
		"data" : function(data) {
			return data.itemcode;
		}
	}, {
		"data" : function(data) {
			return data.skuCode;
		}
	}, {
		"data" : function(data) {
			return data.itemname;
		}
	}, {
		"data" : function(data) {
			return "<span class='badge bg-color-greenLight'>" + data.avaquantity + "</span>";
		}
	}, {
		"data" : function(data) {
			return data.invWarehouseName;
		}
	} ];

	var inventoryCurrentStockTable = initDataTable("inventoryCurrentStockTableId", "${nvix}/nvixnt/vixntSupplierStandingBookAction!goSingleList.action", inventoryCurrentStockColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var itemname = $("#itemname").val();
		var itemcode = $("#itemcode").val();
		var skuCode = $("#skuCode").val();
		itemname = Url.encode(itemname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"itemname" : itemname,
		"itemcode" : itemcode,
		"skuCode" : skuCode,
		"treeType" : treeType
		};
	}, 10, '0');
</script>