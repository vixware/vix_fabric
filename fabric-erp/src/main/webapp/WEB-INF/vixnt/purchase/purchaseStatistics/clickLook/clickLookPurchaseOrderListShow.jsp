<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<s:if test="returnPage=='PurchasePlate'">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 采购智能分析 <span>&gt; 采购仪表盘</span>
				</s:if>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
			 <s:if test="returnPage=='PurchasePlate'">
				<button class="btn btn-default" type="button" onclick="loadContent('purchase_purchaseStatistics','${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!goStatisticsView.action');"> 
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			 </s:if>
			</div>
		</div>
	</div>
		<div class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i></span>
				<s:if test="queryTime=='ThisMonthOT'">
				<h2>本月订单列表</h2>
				</s:if>
				<s:else>
				<h2>列表</h2>
				</s:else>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
							          主题: <input type="text" value="" class="form-control" id="searchName" style="width: 130px;">
							</div>
							<button onclick="showQueryDataTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchName').val('');showQueryDataTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="showQueryDataTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
		</div> 
		<input type="hidden" value="${queryTime }" id="queryTime">
		<input type="hidden" value="${backupsStr }" id="backupsStr">
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	var customerAccountColumns = [ {
		"title" : "编号",
		"width" : "5%",
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"title" : "单据编码",
		"name":"code",
		"orderable" : true,
		"data" : function(data) {
			return data.code;
		}
		}, {
		"title" : "主题",
		"name":"name",
		"orderable" : true,
		"data" : function(data) {
			return data.name;
		}
		}, {
		"title" : "采购人",
		"name":"purchasePerson",
		"orderable" : true,
		"data" : function(data) {
			return data.purchasePerson;
		}
		}, {
		"title" : "采购金额",
		"name":"totalAmount",
		"orderable" : true,
		"data" : function(data) {
			return data.totalAmount;
		}
		}, {
		"title" : "采购时间",
		"name":"createTime",
		"orderable" : true,
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
		}, {
		"title" : "类型",
		"orderable" : true,
		"name":"type",
		"data" : function(data) {
			if(data.type == '1'){
				return "<span class='label label-primary'>总部采购订单</span>";
			}else if(data.type == '0'){
				return "<span class='label label-success'>门店采购订单</span>";
			}
		}
		}, {
		"title" : "审批状态",
		"orderable" : true,
		"name":"approvalType",
		"data" : function(data) {
			if(data.approvalType == '0'){
				return "<span class='label label-warning'>未提交</span>";
			}else if(data.approvalType == '1'){
				return "<span class='label label-primary'>审批中</span>";
			}else if(data.approvalType == '2'){
				return "<span class='label label-success'>审批完成</span>";
			}
			return "";
		}
		}, {
		"title" : "状态",
		"orderable" : true,
		"name":"status",
		"data" : function(data) {
			if(data.status == '0'){
				return "待配货";
			}else if(data.status == '1'){
				return "代发货";
			}else if(data.status == '2'){
				return "配送中";
			}else if(data.status == '3'){
				return "已完成";
			}else if(data.status == '4'){
				return "待分拣";
			}
			return "";
		}
		} ];
	
	var showQueryDataTable = initDataTable("showQueryDataTableId", "${nvix}/nvixnt/purchase/vixntPurchaseStatisticsClickAction!clickLookPurchaseOrderListShowQuery.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		var queryTime = $("#queryTime").val();
		queryTime = Url.encode(queryTime); 
		var backupsStr = $("#backupsStr").val();
		backupsStr = Url.encode(backupsStr);       
		return {
			"page" : page,
			"pageSize" : pageSize,
			"orderField" : orderField,
			"orderBy" : orderBy,
			"queryTime" : queryTime,
			"backupsStr" : backupsStr,
			"name" : searchName
		};
	}, 10, '0');
</script>