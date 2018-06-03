<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 门店管理 <span>&gt; 门店数据统计</span><span>&gt; 门店要货耗损统计</span>
			</h1>
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
							<th>要货单编码</th>
							<th>主题</th>
							<th>要货时间</th>
							<th>耗损金额</th>
							<th>操作</th>
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
	"name" : "code",
	"width" : "15%",
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
	"width" : "15%",
	"name" : "deliveryTime",
	"data" : function(data) {
		return data.deliveryTimeStr;
	}
	}, {
	"width" : "10%",
	"data" : function(data) {
		return data.wastageTotal;
	}
	}, {
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		return update;
	}
	} ];
	var salesOrderTable = initDataTable("salesOrderTableId", "${nvix}/nvixnt/vixntRequireGoodsWastageAction!goSingleList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var selectname = $("#selectname").val();
		selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectname" : selectname
		};
	});
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntRequireGoodsWastageAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>