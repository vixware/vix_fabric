<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 门店管理 <span>&gt; 门店数据统计</span><span>&gt; 门店商品毛利统计</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="dealAttendance();">
						<i class="glyphicon glyphicon-list"></i>&nbsp;统计
					</button>
				</a>
			</div>
		</div>
	</div>
	<!-- row -->
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input type="text" class="form-control" id="selectname">
						</div>
						<button onclick="salesOrderTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#selectname').val('');salesOrderTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="salesOrderTableId" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th>编号</th>
							<th>日期</th>
							<th>商品编码</th>
							<th>商品名称</th>
							<th>销售收入</th>
							<th>销售成本</th>
							<th>毛利率</th>
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
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"name" : "salesDate",
	"data" : function(data) {
		return data.salesDate;
	}
	}, {
	"name" : "itemcode",
	"data" : function(data) {
		return data.itemcode;
	}
	}, {
	"name" : "itemname",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"width" : "10%",
	"data" : function(data) {
		return data.costPrice;
	}
	}, {
	"width" : "10%",
	"data" : function(data) {
		return data.margin;
	}
	} ];
	var salesOrderTable = initDataTable("salesOrderTableId", "${nvix}/nvixnt/vixntStoreSalesMarginAction!goSingleList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var selectname = $("#selectname").val();
		selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectname" : selectname
		};
	});

	function dealAttendance() {
		$.ajax({
		url : '${nvix}/nvixnt/vixntStoreSalesMarginAction!updateOrderDetail.action',
		cache : false,
		success : function() {
			taskStatisticsTable.ajax.reload();
		}
		});
	};
</script>