<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 配送管理 <span>&gt; 订单管理</span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>订单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<table id="salesOrderTableId" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th>编号</th>
							<th>单据编码</th>
							<th>主题</th>
							<th>要货人</th>
							<th>配送车辆</th>
							<th>要货时间</th>
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
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"name" : "code",
	"width" : "10%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"width" : "10%",
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"width" : "10%",
	"data" : function(data) {
		return data.vehicleNO;
	}
	}, {
	"width" : "10%",
	"name" : "deliveryTime",
	"data" : function(data) {
		return data.deliveryTimeTimeStr;
	}
	} ];
	var salesOrderTable = initDataTable("salesOrderTableId", "${nvix}/nvixnt/vixntLogisticsAction!goSingleList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize
		};
	});
</script>