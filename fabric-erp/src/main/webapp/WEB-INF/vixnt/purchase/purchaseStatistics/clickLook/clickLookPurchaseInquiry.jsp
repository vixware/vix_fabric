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
				<h2>询价单列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
							          主题: <input type="text" value="" class="form-control" id="searchName" style="width: 130px;">
							</div>
							<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="var index = layer.open({title:'高级检索',type: 1, area: ['700px', '230px'], content: $('#advanceSearch')});" type="button" class="btn btn-primary">
							<i class="glyphicon glyphicon-search"></i> 高级检索
							</button>
							<button onclick="$('#searchName').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<div id="advanceSearch" style="padding:20px 15px;display:none;">
						<form id="advanceSearchForm" class="form-horizontal">
							<fieldset>
								<div class="form-group">
									<lable class="col-md-2 control-label">单据编码:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="code" placeholder="单据编码" class="form-control"/>
									</div>
									<lable class="col-md-2 control-label">主题:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="name" placeholder="主题" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<lable class="col-md-2 control-label">询价人:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="purchasePerson" placeholder="询价人" class="form-control"/>
									</div>
									<lable class="col-md-2 control-label">询价日期:</lable>
									<div class="col-md-4"> 
										<input type="text" class="form-control" placeholder="询价日期" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
									</div>
								</div>
								<div class="form-group" style="text-align:center;">
									<button onclick="advanceSearch();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="clearSearchCondition('name,searchName,code,purchasePerson,createTime',customerAccountTable);"
										type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</div>
							</fieldset>
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
		"width" : "5%",
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"title" : "编码",
		"width" : "15%",
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
		},{
		"title" : "供应商",
		"orderable" : false,
		"data" : function(data) {
			return data.supplierName;
		}
		}, {
		"title" : "询价人",
		"orderable" : false,
		"data" : function(data) {
			return data.purchasePerson;
		}
		}, {
		"title" : "询价时间",
		"orderable" : false,
		"data" : function(data) {
			return data.appDateStr;
		}
		} ];
	
	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/purchase/vixntPurchaseStatisticsClickAction!clickLookPurchaseInquiryQuery.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		var code = $("#code").val();
		var purchasePerson = $("#purchasePerson").val();
		var createTime = $("#createTime").val();
		searchName = Url.encode(searchName);
		var queryTime = $("#queryTime").val();
		queryTime = Url.encode(queryTime);   
		return {
			"page" : page,
			"pageSize" : pageSize,
			"orderField" : orderField,
			"orderBy" : orderBy,
			"code" : code,
			"purchasePerson" : purchasePerson,
			"createTime" : createTime,
			"orderBy" : orderBy,
			"queryTime" : queryTime,
			"name" : searchName
		};
	}, 10, '0');
	function advanceSearch(){
		customerAccountTable.ajax.reload();
		layer.closeAll('page');
	}
</script>