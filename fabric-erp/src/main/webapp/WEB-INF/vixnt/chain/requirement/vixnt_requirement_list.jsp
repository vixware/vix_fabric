<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 系统管理 <span>&gt; 新品需求管理</span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>需求列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							主题: <input type="text" value="" class="form-control" id="mytitle" style="width: 250px;">
						</div>
						<button onclick="salesOrderTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#mytitle').val('');salesOrderTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="salesOrderTableId" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th>编号</th>
							<th>门店</th>
							<th>需求内容</th>
							<th>创建时间</th>
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
	"width" : "10%",
	"data" : function(data) {
		return data.channelDistributorName;
	}
	}, {
	"name" : "content",
	"data" : function(data) {
		return data.content;
	}
	}, {
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeStr;
	}
	} ];
	var salesOrderTable = initDataTable("salesOrderTableId", "${nvix}/nvixnt/vixntRequirementAction!goSingleList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#mytitle").val();
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"title" : title
		};
	});
</script>