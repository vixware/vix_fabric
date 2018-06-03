<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 供应商管理 <span>&gt; 门店销售记录</span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>门店销售记录列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<input type="hidden" id="supplierId" name="" value="" />
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
				<table id="salesOrderTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var salesOrderColumns = [ {
	"title" : "编号",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "门店",
	"name" : "code",
	"data" : function(data) {
		return data.channelDistributorName;
	}
	}, {
	"title" : "订单编码",
	"name" : "outerId",
	"data" : function(data) {
		return data.outerId;
	}
	}, {
	"title" : "购买数量",
	"name" : "num",
	"data" : function(data) {
		return data.num;
	}
	}, {
	"title" : "支付类型",
	"name" : "payTypeCn",
	"data" : function(data) {
		return data.payTypeCn;
	}
	}, {
	"title" : "交易时间",
	"name" : "createTimeTimeStr",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-eye-open'></i></span></a>";
		return update;
	}
	} ];
	var salesOrderTable = initDataTable("salesOrderTableId", "${nvix}/nvixnt/vixntOrderAction!goSingleList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var selectname = $("#selectname").val();
		selectname = Url.encode(selectname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"selectname" : selectname
		};
	});

	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntOrderAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>