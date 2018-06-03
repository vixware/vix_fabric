<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 门店管理 <span>&gt; 门店数据统计</span><span>&gt; 门店销售记录</span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>订单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="input-group">
							<input placeholder="开始时间" id="taskStartTime" name="vixTask.taskStartTime" value="${vixTask.taskStartTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'taskStartTime'});"><i class="fa fa-calendar"></i></span>
						</div>
						<div class="input-group">
							<input placeholder="结束时间" id="taskStartTime" name="vixTask.taskStartTime" value="${vixTask.taskStartTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'taskStartTime'});"><i class="fa fa-calendar"></i></span>
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
	"name" : "createTime",
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
		return {
		"page" : page,
		"pageSize" : pageSize
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