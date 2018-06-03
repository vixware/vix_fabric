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
				<h2>采购退货列表</h2>
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
		"title" : "退货单号",
		"orderable" : false,
		"data" : function(data) {
			return data.code;
		}
		}, {
		"title" : "主题",
		"orderable" : false,
		"data" : function(data) {
			return data.name;
		}
		}, {
		"title" : "金额",
		"orderable" : false,
		"data" : function(data) {
			return data.totalAmount == null  ? "￥"+0 : "￥"+data.totalAmount;
		}
		}, {
		"title" : "部门",
		"orderable" : false,
		"data" : function(data) {
			return data.requireDepartment;
		}
		}, {
		"title" : "供应商",
		"orderable" : false,
		"data" : function(data) {
			return data.supplierName;
		}
		}, {
		"title" : "采购人",
		"orderable" : false,
		"data" : function(data) {
			return data.purchasePerson;
		}
		}, {
		"title" : "退货日期",
		"orderable" : false,
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
		}, {
		"title" : "审批状态",
		"orderable" : false,
		"data" : function(data) {
			if(data.approvalStatus == '0'){
				return "<span class='label label-warning'>未提交</span>";
			}else if(data.approvalStatus == '1'){
				return "<span class='label label-primary'>审批中</span>";
			}else if(data.approvalStatus == '2'){
				return "<span class='label label-success'>审批完成</span>";
			}
			return "";
		}
		} ];
	
	var showQueryDataTable = initDataTable("showQueryDataTableId", "${nvix}/nvixnt/purchase/vixntPurchaseStatisticsClickAction!clickLookPurchaseReturnQuery.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		var queryTime = $("#queryTime").val();
		queryTime = Url.encode(queryTime);   
		return {
			"page" : page,
			"pageSize" : pageSize,
			"orderField" : orderField,
			"orderBy" : orderBy,
			"queryTime" : queryTime,
			"name" : searchName
		};
	}, 10, '0');
</script>