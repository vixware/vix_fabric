<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-cubes"></i> 库存管理 <span>&gt; 出入库流水账</span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<table id="salesOrderTableId" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th></th>
							<th class="hasinput"><input type="text" class="form-control" placeholder="商品编码" id="itemCode"></th>
							<th class="hasinput"><input type="text" class="form-control" placeholder="商品名称" id="itemName" /></th>
							<th class="hasinput"></th>
							<th class="hasinput"></th>
							<th class="hasinput"></th>
							<th class="hasinput"></th>
							<th class="hasinput"></th>
							<th class="hasinput"></th>
						</tr>
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
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	} ];
	var salesOrderTable = initDataTable("salesOrderTableId", "${nvix}/nvixnt/vixntStockRecordLinesStatisticsAction!goSingleList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var itemCode = $('#itemCode').val();
		var itemName = $('#itemName').val();
		itemName = Url.encode(itemName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"itemCode" : itemCode,
		"itemName" : itemName
		};
	});
</script>