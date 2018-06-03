<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cubes"></i> 库存管理<span>> 库存冻结 </span>
			</h1>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>仓库</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /><input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/vixntWarehouseAction!findInvWarehouseTreeToJson.action",
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
							<div id="">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										商品名称: <input type="text" value="" class="form-control" id="itemname"> <input type="hidden" class="form-control" id="invShelfId"> <input type="hidden" class="form-control" id="invShelfName">
									</div>
									<button onclick="inventoryCurrentStockTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#itemname').val('');$('#invShelfName').val('');$('#invShelfId').val('');inventoryCurrentStockTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="inventoryCurrentStockTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var inventoryCurrentStockColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"class" : 'details-control',
	"orderable" : false,
	"data" : null,
	"defaultContent" : ''
	}, {
	"title" : "商品编码",
	"data" : function(data) {
		return data.itemcode;
	}
	}, {
	"title" : "商品名称",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
	"title" : "SKU编码",
	"data" : function(data) {
		return data.skuCode;
	}
	}, {
	"title" : "规格",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"title" : "库存数量",
	"data" : function(data) {
		return "<span class='badge bg-color-blueLight'>" + data.quantity + " " + data.measureUnitName + "</span>";
	}
	}, {
	"title" : "可用数量",
	"data" : function(data) {
		return "<span class='badge bg-color-greenLight'>" + data.avaquantity + " " + data.measureUnitName + "</span>";
	}
	}, {
	"title" : "冻结数量",
	"data" : function(data) {
		return "<span class='badge bg-color-greenLight'>" + data.freezequantity + " " + data.measureUnitName + "</span>";
	}
	}, {
	"title" : "价格",
	"data" : function(data) {
		return data.price + " 元";
	}
	}, {
	"title" : "库存成本",
	"data" : function(data) {
		return data.price * data.avaquantity + " 元";
	}
	}, {
	"title" : "所在仓库",
	"data" : function(data) {
		return data.warehouse;
	}
	}, {
	"title" : "货位",
	"data" : function(data) {
		return data.invShelfName;
	}
	} ];

	var inventoryCurrentStockTable = initDataTable("inventoryCurrentStockTableId", "${nvix}/nvixnt/vixntInventoryFreezeAction!goSingleList.action", inventoryCurrentStockColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var itemname = $("#itemname").val();
		itemname = Url.encode(itemname);
		var invShelfName = $("#invShelfName").val();
		invShelfName = Url.encode(invShelfName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"parentId" : parentId,
		"itemname" : itemname,
		"invShelfName" : invShelfName,
		"treeType" : treeType
		};
	}, 10, '0', '0');

	function format(d) {
		return '<table cellpadding="5" cellspacing="0" border="0" class="table table-hover table-condensed">' + '<tr>' + '<td style="width:100px">批次号:</td>' + '<td>' + d.batchcode + '</td>' + '</tr>' + '<tr>' + '<td style="width:100px">供应商:</td>' + '<td>' + d.supplierName + '</td>' + '</tr>' + '<tr>' + '<td style="width:100px">到期日:</td>' + '<td>' + d.massunitEndTimeStr + '</td>' + '</tr>' + '<tr>' + '<td>单位:</td>' + '<td>' + d.measureUnitName + '</td>' + '</tr>' + '<tr>' + '<td>操作:</td>' + '<td><button class="btn btn-xs btn-danger pull-right" style="margin-left:5px" onclick="deleteItemById(\'' + d.id + '\');">删除</button> </td>' + '</tr>' + '</table>';
	};
	$('#inventoryCurrentStockTableId tbody').on('click', 'td.details-control', function() {
		var tr = $(this).closest('tr');
		var row = inventoryCurrentStockTable.row(tr);
		if (row.child.isShown()) {
			row.child.hide();
			tr.removeClass('shown');
		} else {
			row.child(format(row.data())).show();
			tr.addClass('shown');
		}
	});
</script>