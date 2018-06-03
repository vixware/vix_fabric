<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 系统管理 <span>> 订单回收站</span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>销售订单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							主题:<input type="text" value="" placeholder="销售单主题" class="form-control" id="searchTitle">
						</div>
						<button onclick="salesOrderTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="salesOrder" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th width="5%">编号</th>
							<th width="10%">编码</th>
							<th width="15%">主题</th>
							<th width="10%">金额</th>
							<th width="10%">状态</th>
							<th width="10%">单据日期</th>
							<th width="15%">操作</th>
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
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "title",
	"data" : function(data) {
		return data.title;
	}
	}, {
	"name" : "amountTotal",
	"data" : function(data) {
		return data.amountTotal == null ? '￥0' : '￥' + data.amountTotal;
	}
	}, {
	"name" : "status",
	"data" : function(data) {
		if (data.status == '1') {
			return '<span style="color: blue;">激活</span>';
		}
		if (data.status == '0') {
			return '<span style="red: blue;">禁用</span>';
		}
		return '';
	}
	}, {
	"name" : "billDate",
	"data" : function(data) {
		return data.billDateStr;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"recovery('" + data.id + "');\" title='恢复'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var showOrder = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"showOrder('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + showOrder + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var salesOrderTable = initDataTable("salesOrder", "${nvix}/nvixnt/system/vixntBillRecycleBinAction!goSingleList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#searchTitle").val();
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title
		};
	});

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/system/vixntBillRecycleBinAction!deleteById.action?id=' + id, '是否删除该销售单?', salesOrderTable);
	}
	//恢复订单
	function recovery(id) {
		updateEntityByConfirm('${nvix}/nvixnt/system/vixntBillRecycleBinAction!recovery.action?id=' + id, '确定恢复该订单吗?', salesOrderTable);
	};
	//显示订单
	function showOrder(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixSalesOrderAction!goShowSalesOrder.action?id=' + id);
	}
</script>