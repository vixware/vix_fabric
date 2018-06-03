<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-chain"></i> 连锁经营管理 <span>&gt; 门店出入库流水账</span>
			</h1>
		</div>
	</div>
	<section>
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>门店</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
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
							salesOrderTable.ajax.reload();
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
						<h2>出入库流水账</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										商品名称: <input type="text" value="" class="form-control" id="searchName">
									</div>
									<button onclick="salesOrderTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchName').val('');salesOrderTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="salesOrderTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th>编号</th>
										<th>商品编码</th>
										<th>商品名称</th>
										<th>规格型号</th>
										<th>数量</th>
										<th>单价</th>
										<th>金额</th>
										<th>类型</th>
										<th>时间</th>
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
	var salesOrderColumns = [ {
	"orderable" : false,
	"width" : "6%",
	"data" : function(data) {
		return "";
	}
	}, {
	"name" : "itemcode",
	"width" : "10%",
	"data" : function(data) {
		return data.itemcode;
	}
	}, {
	"width" : "19%",
	"name" : "itemname",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
	"width" : "10%",
	"name" : "specification",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"width" : "7%",
	"name" : "quantity",
	"data" : function(data) {
		return data.quantity;
	}
	}, {
	"width" : "7%",
	"name" : "unitcost",
	"data" : function(data) {
		return data.unitcost;
	}
	}, {
	"width" : "7%",
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"width" : "10%",
	"data" : function(data) {
		if (data.flag == 1) {
			return "<span class='label label-info'>入库</span>";
		} else if (data.flag == 2) {
			return "<span class='label label-success'>出库</span>";
		}
		return "";
	}
	}, {
	"width" : "14%",
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	} ];
	var salesOrderTable = initDataTable("salesOrderTableId", "${nvix}/nvixnt/vixntStockRecordLinesStatisticsAction!goAllStockRecordLinesList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var shopId = $('#selectId').val();
		var searchName = $('#searchName').val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchName" : searchName,
		"shopId" : shopId
		};
	});
</script>