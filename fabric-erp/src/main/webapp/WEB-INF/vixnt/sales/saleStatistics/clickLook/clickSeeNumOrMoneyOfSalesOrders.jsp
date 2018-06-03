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
				<h2>销售订单列表</h2>
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
							<button onclick="clearSearchCondition('customerAccountName,searchName,code,salePerson,createTime',customerAccountTable);" type="button" class="btn btn-default">
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
									<lable class="col-md-2 control-label">客户:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="customerAccountName" placeholder="客户" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<lable class="col-md-2 control-label">负责人:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="salePerson" placeholder="负责人" class="form-control"/>
									</div>
									<lable class="col-md-2 control-label">单据日期:</lable>
									<div class="col-md-4"> 
										<input type="text" class="form-control" placeholder="单据日期" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
									</div>
								</div>
								<div class="form-group" style="text-align:center;">
									<button onclick="advanceSearch();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="clearSearchCondition('customerAccountName,searchName,code,salePerson,createTime',customerAccountTable);"
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
		"width" : "10%",
		"orderable" : false,
		"data" : function(data) {
			return data.code;
		}
		}, {
		"title" : "主题",
		"orderable" : false,
		"data" : function(data) {
			return data.title;
		}
		}, {
		"title" : "客户",
		"orderable" : false,
		"data" : function(data) {
			return data.customerAccount == null ? '' : data.customerAccount.name;
		}
		}, {
		"title" : "金额",
		"orderable" : false,
		"data" : function(data) {
			return data.amountTotal == null ? '￥0' : '￥' + data.amountTotal;
		}
		}, {
		"title" : "状态",
		"orderable" : false,
		"data" : function(data) {
			if (data.status == '1') {
				return '<span style="color: blue;">激活</span>';
			}
			if (data.status == '0') {
				return '<span style="red: blue;">禁用</span>';
			}
			return '';
		}
		}, {
		"title" : "负责人",
		"orderable" : false,
		"data" : function(data) {
			return data.salePerson == null ? '' : data.salePerson.name;
		}
		}, {
		"title" : "单据日期",
		"orderable" : false,
		"data" : function(data) {
			return data.billDateStr;
		}
		} ];
	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/nvixntSalesStatisticsClickAction!clickSeeNumOrMoneyOfSalesOrdersQuery.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		var code = $("#code").val();
		var salePerson = $("#salePerson").val();
		var createTime = $("#createTime").val();
		searchName = Url.encode(searchName);
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
			"salePerson" : salePerson,
			"createTime" : createTime,
			"orderBy" : orderBy,
			"queryTime" : queryTime,
			"customerAccountName" : customerAccountName,
			"title" : searchName
		};
	}, 10, '0');
	function advanceSearch(){
		customerAccountTable.ajax.reload();
		layer.closeAll('page');
	}
</script>