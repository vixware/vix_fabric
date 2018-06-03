<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售中管理 </span><span>> 开票记录 </span>
			</h1>
		</div>
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
				<div class="jarviswidget jarviswidget-color-darken" id="chanceAndTrackingHead" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>开票记录列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="chanceAndTrackingSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										发票号:<input type="text" value="" placeholder="发票号" class="form-control" id="searchCode"> 
										客户:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName">
									</div>
									<button onclick="salesInvoiceTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchCode').val('');$('#searchCustomerName').val('');salesInvoiceTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="salesInvoiceTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="8%">编号</th>
										<th>开票内容</th>
										<th>票据类型</th>
										<th>发票金额</th>
										<th>发票号码</th>
										<th>客户名称</th>
										<th>订单名称</th>
										<th>经手人</th>
										<th>是否回款</th>
										<th>开票日期</th>
										<th width="8%">操作</th>
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
	var salesInvoiceColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"name" : "saleInvoiceType",
	"data" : function(data) {
		if(data.saleInvoiceType != null){
			return "<span class='label label-info'>"+data.saleInvoiceType.name+"</span>";
		}
	}
	},{
	"name" : "amount",
	"data" : function(data) {
		if(data.amount != null){
			return data.amount;
		}else{
			return 0;
		}
	}
	},{
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccount == null ? '' : data.customerAccount.name;
	}
	}, {
	"name" : "salesOrder",
	"data" : function(data) {
		return data.salesOrder == null ? '' : data.salesOrder.title;
	}
	},{
	"name" : "employee",
	"data" : function(data) {
		return data.employee == null ? '' : data.employee.name;
	}
	}, {
	"name" : "isBackSection",
	"data" : function(data) {
		if(data.isBackSection == 0){
			return "<span class='label label-info'>未回款</span>";
		}else if(data.isBackSection == 1){
			return "<span class='label label-success'>已回款</span>";
		}else{
			return "";
		}
	}
	}, {
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (null != data.id) {
			var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var salesInvoiceTable = initDataTable("salesInvoiceTableId", "${nvix}/nvixnt/nvixSalesInvoiceAction!goListContent.action", salesInvoiceColumns, function(page, pageSize, orderField, orderBy) {
		var code = $("#searchCode").val();
		var cName = $("#searchCustomerName").val();
		code = Url.encode(code);
		cName = Url.encode(cName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"code" : code,
		"customerName" : cName
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixSalesInvoiceAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixSalesInvoiceAction!deleteById.action?id=' + id, '是否删除该开票记录?', salesInvoiceTable);
	}

	pageSetUp();
</SCRIPT>