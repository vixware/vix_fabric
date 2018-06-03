<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<s:if test="returnPage=='SalePlate'">
					<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售智能分析 <span>&gt; 销售仪表盘</span>
				 </s:if>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
			 	<s:if test="returnPage=='SalePlate'">
					<button class="btn btn-default" type="button" onclick="loadContent('purchase_purchaseStatisticsSale','${nvix}/nvixnt/nvixntSalesStatisticsAction!goStatisticsSaleView.action');"> 
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				 </s:if>
			</div>
		</div>
	</div>
		<div class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i></span>
				<h2>退货单列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
							          编码: <input type="text" value="" class="form-control" id="code" style="width: 130px;">
							</div>
							<div class="form-group">
							         客户: <input type="text" value="" class="form-control" id="customerAccountName" style="width: 130px;">
							</div>
							<div class="form-group">
							退货日期:
							<input type="text" class="form-control" placeholder="退货日期" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
							</div>
							<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="clearSearchCondition('customerAccountName,searchName,code,salePerson,createTime',customerAccountTable);" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
		</div> 
		<input type="hidden" value="${queryTime }" id="queryTime">
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	var customerAccountColumns = [ {
		"title" : "编号",
		"width" : "10%",
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"title" : "单据编码",
		"width" : "15%",
		"name" : "code",
		"data" : function(data) {
			return data.code;
		}
		}, {
		"title" : "主题",
		"width" : "25%",
		"name" : "name",
		"data" : function(data) {
			return data.name;
		}
		}, {
		"title" : "客户",
		"width" : "15%",
		"name" : "customerAccount",
		"data" : function(data) {
			return data.customerAccountName;
		}
		}, {
		"title" : "退货日期",
		"width" : "15%",
		"name" : "returnTime",
		"data" : function(data) {
			return data.returnTimeStr;
		}
		}, {
		"title" : "状态",
		"width" : "15%",
		"name" : "status",
		"data" : function(data) {
			if(data.status == "1"){
				return "<span class='label label-primary'>待收货</span>";
			}else if(data.status == "2"){
				return "<span class='label label-success'>已完成</span>";
			}
			return "";
		}
		} ];
	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/nvixntSalesStatisticsClickAction!clickSeeReturnGoodsQuery.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var code = $("#code").val();
		var createTime = $("#createTime").val();
		var queryTime = $("#queryTime").val();
		queryTime = Url.encode(queryTime);   
		var customerAccountName = $("#customerAccountName").val(); 
		customerAccountName = Url.encode(customerAccountName);
		return {
			"page" : page,
			"pageSize" : pageSize,
			"orderField" : orderField,
			"orderBy" : orderBy,
			"code" : code,
			"createTime" : createTime,
			"orderBy" : orderBy,
			"queryTime" : queryTime,
			"customerAccountName" : customerAccountName,
			"code" : code
		};
	}, 10, '0');
	function advanceSearch(){
		customerAccountTable.ajax.reload();
		layer.closeAll('page');
	}
</script>