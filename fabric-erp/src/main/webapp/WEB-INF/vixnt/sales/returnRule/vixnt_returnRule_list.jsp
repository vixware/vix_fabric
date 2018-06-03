<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-pencil-square-o fa-fw "></i> 销售管理 <span>> 返利规则 </span>
			</h1>
		</div>
		<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" id="salesOrderHead" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>返利规则列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="salesOrderSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										商品:<input type="text" value=""  class="form-control" id="searchTitle"> 客户:<input type="text" value="" class="form-control" id="searchCustomerName">
									</div>
									<button onclick="salesOrderTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchTitle').val('');$('#searchCustomerName').val('');salesOrderTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="salesOrder" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="5%">编号</th>
										<th width="10%">规则类型</th>
										<th width="15%">客户</th>
										<th width="15%">商品</th>
										<th width="10%">最低销售数量</th>
										<th width="10%">返款比率</th>
										<th width="15%">明细计算类型</th>
										<th width="10%">操作</th>
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
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "code",
	"data" : function(data) {
		if(data.rrType == 'customerAccount'){
			return "客户";
		}else if(data.rrType == 'item'){
			return "物料";
		}
		return '';
	}
	}, {
	"data" : function(data) {
		return data.customerAccountName;
	}
	}, {
	"data" : function(data) {
		return data.itemName;
	}
	}, {
	"data" : function(data) {
		if(data.detailType == "count"){
			 return data.lowerSaleCount == null ? '0' : data.lowerSaleCount;
		}else if(data.detailType == "money"){
			 return data.lowerSaleCount == null ? '￥0' : '￥' + data.lowerSaleCount;
		}
		return "";
	}
	}, {
	"data" : function(data) {
		return data.ratio;
	}
	}, {
	"data" : function(data) {
		if(data.detailType == 'count'){
			return '数量';
		}else if(data.detailType == 'money'){
			return '金额';
		}
		return '';
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.id != null) {
			var print = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"printSalesOrder('" + data.id + "');\" title='打印订单'><span class='txt-color-blue pull-right'><i class='fa fa-print'></i></span></a>";
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var showOrder = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"showOrder('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update +"&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var salesOrderTable = initDataTable("salesOrder", "${nvix}/nvixnt/nvixntReturnRuleAction!goSingleList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchCustomerName").val();
		var title = $("#searchTitle").val();
		name = Url.encode(name);
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title,
		"name" : name
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixntReturnRuleAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntReturnRuleAction!deleteById.action?id=' + id, '是否删除返利规则?', salesOrderTable);
	}
	pageSetUp();
</script>