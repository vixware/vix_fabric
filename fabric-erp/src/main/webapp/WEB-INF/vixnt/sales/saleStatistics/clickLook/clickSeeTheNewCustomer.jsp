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
				<h2>本月新客户列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
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
		"width" : "25%",
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"title" : "客户",
		"width" : "25%",
		"data" : function(data) {
			return (data.tname != null ? data.tname : "" );
		}
		}, {
		"title" : "本月消费总金额(元)",
		"width" : "25%",
		"data" : function(data) {
			return (data.tnum != null ? Number(data.tnum).toFixed(2) : "" );  
		}
		}, {
		"title" : "本月消费总金额(万元)",
		"width" : "25%",
		"data" : function(data) {
			return (data.tnum != null ? Number( ((data.tnum)/10000) ).toFixed(2) +"万元" : "" );  
		}
		} ];
	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/nvixntSalesStatisticsClickAction!clickSeeTheNewCustomerQuery.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var queryTime = $("#queryTime").val();
		queryTime = Url.encode(queryTime);   
		return {
			"page" : page,
			"pageSize" : pageSize,
			"orderField" : orderField,
			"orderBy" : orderBy,
			"queryTime" : queryTime
		};
	}, 10, '0');
	function advanceSearch(){
		customerAccountTable.ajax.reload();
		layer.closeAll('page');
	}
</script>