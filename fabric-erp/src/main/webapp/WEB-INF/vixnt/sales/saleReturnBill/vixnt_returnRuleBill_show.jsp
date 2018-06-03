<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-pencil-square-o"></i> 销售管理 <span onclick="">&gt; 返利单</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntSaleReturnBillAction!goList.action');">
					<i class="glyphicon glyphicon-repeat"></i>&nbsp;返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>返利单</h2>
		</header>
		<div class="widget-body">
			<form id="saleReturnBillForm" class="form-horizontal">
				<input type="hidden" id="saleReturnBillId" name="saleReturnBill.id" value="${saleReturnBill.id}"/>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label">客户:</label>
						<div class="col-md-3">
							<input type="text" id="customerAccount" name="saleReturnBill.customerAccount.name" value="${saleReturnBill.customerAccount.name}" class="form-control" readonly="readonly"/>
						</div>
						<label class="col-md-2 control-label">返款金额:</label>
						<div class="col-md-3">
							<input type="text" id="returnAmount" name="saleReturnBill.returnAmount" value="${saleReturnBill.returnAmount}" class="form-control" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">币种:</label>
						<div class="col-md-3">
							<input type="text" id="currencyType" name="saleReturnBill.currencyType.name" value="${saleReturnBill.currencyType.name}" class="form-control" readonly="readonly"/>
						</div>
						<label class="col-md-2 control-label">返款日期:</label>
						<div class="col-md-3">
							<input id="rbDate" type="text" name="saleReturnBill.rbDate" value="${saleReturnBill.rbDateTimeStr}"  class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea rows="3" id="memo" name="saleReturnBill.memo" value="${saleReturnBill.memo}" class="form-control">${saleReturnBill.memo}</textarea> 
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>返款单明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="商品名称" class="form-control" id="searchItemName">
										</div>
										<button onclick="saleReturnBillItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchItemName').val('');saleReturnBillItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</div>
								</div>
								<table id="saleReturnBillItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top:15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntSaleReturnBillAction!goList.action');">
								<i class="glyphicon glyphicon-repeat"></i>&nbsp;返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
var saleReturnBillItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "${vv:varView('vix_mdm_item')}名称",
	"orderable" : false,
	"data" : function(data) {
		return data.itemName;
	}
	}, {
	"title" : "${vv:varView('vix_mdm_item')}数量",
	"orderable" : false,
	"data" : function(data) {
		return data.amount == null ?"0" : data.amount;
	}
	}, {
	"title" : "单位",
	"orderable" : false,
	"data" : function(data) {
		if(data.saleOrderItem != null){
			return data.saleOrderItem.unit;
		}
		return "";
	}
	}, {
	"title" : "${vv:varView('vix_mdm_item')}金额",
	"orderable" : false,
	"data" : function(data) {
		return data.acount == null ?"￥0" : "￥"+data.acount;
	}
	}, {
	"title" : "返款金额",
	"orderable" : false,
	"data" : function(data) {
		return data.totalAmount == null ?"￥0" : "￥"+data.totalAmount;
	}
	} ];
	var saleReturnBillItemTable = initDataTable("saleReturnBillItemTableId", "${nvix}/nvixnt/nvixntSaleReturnBillAction!goSingleSaleReturnBillList.action", saleReturnBillItemColumns, function(page, pageSize, orderField, orderBy) {
		var searchItemName = $("#searchItemName").val();
		searchItemName = Url.encode(searchItemName);
		var id = $("#saleReturnBillId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : searchItemName,
		"id":id
		};
	});
</script>