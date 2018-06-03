<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售中管理 </span><span>> 退款记录 </span>
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
						<h2>退款记录列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="chanceAndTrackingSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										主题:<input type="text" value="" placeholder="主题" class="form-control" id="searchName">
										客户:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName">
									</div>
									<button onclick="saleReturnBillTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchName').val('');$('#searchCustomerName').val('');saleReturnBillTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="saleReturnBillTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="8%">编号</th>
										<th>主题</th>
										<th>客户名称</th>
										<th>币种</th>
										<th>退款金额</th>
										<th>退款日期</th>
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
	var saleReturnBillColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	},{
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccount.name;
	}
	}, {
	"name" : "currencyType",
	"data" : function(data) {
		return data.currencyType.name
	}
	}, {
	"name" : "returnAmount",
	"data" : function(data) {
		if(data.returnAmount != null){
			return data.returnAmount;
		}else{
			return 0;
		}
	}
	}, {
	"name" : "rbDate",
	"data" : function(data) {
		return data.rbDateStr;
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

	var saleReturnBillTable = initDataTable("saleReturnBillTableId", "${nvix}/nvixnt/nvixSaleReturnBillAction!goListContent.action", saleReturnBillColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchName").val();
		var cName = $("#searchCustomerName").val();
		name = Url.encode(name);
		cName = Url.encode(cName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name,
		"customerName" : cName
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixSaleReturnBillAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixSaleReturnBillAction!deleteById.action?id=' + id, '是否删除该退款记录?', saleReturnBillTable);
	}

	pageSetUp();
</SCRIPT>