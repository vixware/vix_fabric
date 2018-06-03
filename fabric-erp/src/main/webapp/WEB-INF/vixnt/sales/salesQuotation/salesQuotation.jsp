<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 销售管理 <span>> 报价单 </span>
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
				<div class="jarviswidget" id="salesQuotationHead" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>报价单列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="salesQuotationSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										主题:<input type="text" value="" placeholder="报价单主题" class="form-control" id="searchTheme"> 
										客户:<input type="text" value="" placeholder="客户名称" class="form-control" id="searchCustomerName">
									</div>
									<button onclick="salesQuotationTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchTheme').val('');$('#searchCustomerName').val('');salesQuotationTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="salesQuotation" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="5%">编号</th>
										<th width="20%">报价单主题</th>
										<th width="10%">客户名称</th>
										<th width="10%">销售机会</th>
										<!-- <th width="10%">业务类型</th> -->
										<th width="10%">报价(总)</th>
										<th width="10%">接收人</th>
										<th width="10%">负责人</th>
										<th width="15%">日期</th>
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
	var salesQuotationColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.quoteSubject;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.customerAccount == null ? '' : data.customerAccount.name;
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		return data.saleChance == null ? '' : data.saleChance.subject;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if(data.amount != null && data.amount > 0){
			return "￥"+data.amount;
		}
		return "￥0";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.contactPerson == null ? '' : data.contactPerson.name;
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		return data.employee == null ? '' : data.employee.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.billDateStr;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (null != data.id) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"show('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;"+ show + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var salesQuotationTable = initDataTable("salesQuotation", "${nvix}/nvixnt/nvixSalesQuotationAction!goListContentJson.action", salesQuotationColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchCustomerName").val();
		var theme = $("#searchTheme").val();
		name = Url.encode(name);
		theme = Url.encode(theme);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"theme" : theme,
		"name" : name
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixSalesQuotationAction!goSaveOrUpdate.action?id=' + id);
	}
	function show(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixSalesQuotationAction!show.action?id=' + id);
	}
	
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixSalesQuotationAction!deleteById.action?id=' + id, '是否删除该报价单?', salesQuotationTable);
	}

	pageSetUp();
</SCRIPT>