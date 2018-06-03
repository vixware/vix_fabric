<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-pencil-square-o fa-fw "></i> 销售管理 <span>> 返利单</span>
			</h1>
		</div>
		<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button class="btn btn-primary" type="button" onclick="var index = layer.open({title:'选择条件',type: 1, area: ['700px', '300px'], content: $('#advanceSearch')});">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
					
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" id="salesOrderHead" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>返利单列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="salesOrderSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										商品:<input type="text" value=""  class="form-control" id="searchTitle"> 客户:<input type="text" value="" class="form-control" id="searchCustomerName">
									</div>
									<button onclick="salesOrderTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchTitle').val('');$('#searchCustomerName').val('');salesOrderTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
								<div id="advanceSearch" style="padding:20px 15px;display:none;">
									<form id="advanceSearchForm" class="form-horizontal">
										<fieldset>
											<div class="form-group">
												<label class="col-md-2 control-label"><span class="text-danger">*</span>客户:</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="hidden" id="customerId" name="customerId" value=""/>
														<input id="customerName" value="" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
														<span class="input-group-addon" style="cursor:pointer;" onclick="">选择</span>
													</div>
												</div>			
												<label class="col-md-2 control-label">物料:</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="hidden" id="itemId" name="itemId" value=""/>
														<input id="itemName" value="" onfocus="chooseItem();" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
														<span class="input-group-addon" style="cursor:pointer;" onclick="chooseItem();">选择</span>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
												<div class="col-md-4">
													<div class="input-group">
														<input id="startTime" name="startTime" value="" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" type="text" /> <span class="input-group-addon"
															onclick="WdatePicker({dateFmt:'yyyy/MM/dd',el:'startTime'});"><i class="fa fa-calendar"></i></span>
													</div>
												</div>
												<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
												<div class="col-md-4">
													<div class="input-group">
														<input id="endTime" name="endTime" value="" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" type="text" /> <span class="input-group-addon"
															onclick="WdatePicker({dateFmt:'yyyy/MM/dd',el:'endTime'});"><i class="fa fa-calendar"></i></span>
													</div>
												</div>
											</div>
											<div class="form-group" style="text-align:center;">
												<button onclick="advanceSearch();" type="button" class="btn btn-primary">
													<i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;确定
												</button>
												<button onclick="clearSearchCondition('searchCustomerName,searchTitle,code,salePerson,bizTypeId,orderTypeId,createTime',salesOrderTable);"
													type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-repeat"></i> &nbsp;&nbsp;清空
												</button>
											</div>
										</fieldset>
									</form>
								</div>
							</div>
							<table id="salesOrder" class="table table-striped table-bordered table-hover" width="100%">
								
							</table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var salesOrderColumns = [ {
	"title":"编号",	
	"width":"5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title":"客户",
	"data" : function(data) {
		return data.customerAccountName;
	}
	}, {
	"title":"金额",
	"data" : function(data) {
		return data.returnAmount;
	}
	}, {
	"title":"创建日期",
	"data" : function(data) {
		return data.rbDateTimeStr;
	}
	}, {
	"title":"状态",
	"data" : function(data) {
		if(data.status == "1"){
			return "<span class='label label-warning'>待返款</span>";
		}else if(data.status == "2"){
			return "<span class='label label-success'>已完成</span>";
		}
		return "";
	}
	}, {
	"title":"操作",
	"width":"10%",
	"orderable" : false,
	"data" : function(data) {
		if (data.id != null) {
			var print = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"printSalesOrder('" + data.id + "');\" title='打印订单'><span class='txt-color-blue pull-right'><i class='fa fa-print'></i></span></a>";
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var showOrder = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"showOrder('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return showOrder +"&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var salesOrderTable = initDataTable("salesOrder", "${nvix}/nvixnt/nvixntSaleReturnBillAction!goSingleList.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchCustomerName").val();
		var title = $("#searchTitle").val();
		name = Url.encode(name);
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title,
		"name" : name
		};
	});

	//更新
	function goChooseCondition() {
		openWindowForShow('${nvix}/nvixnt/nvixntSaleReturnBillAction!goChooseCondition.action', "选择条件", 850, 350,function(){
			chooseListData("${nvix}/nvixnt/nvixntSaleReturnBillAction!goChooseSaleOrderItem.action",'multi',"选择订单",null,function(){
				
			},860,700);
		});
	}
	function showOrder(id){
		$.ajax({
			url : '${nvix}/nvixnt/nvixntSaleReturnBillAction!showSaleReturnBill.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	}
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntSaleReturnBillAction!deleteById.action?id=' + id, '是否删除返利规则?', salesOrderTable);
	}
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer');
	}
	function chooseItem(){
		chooseListData('${nvix}/nvixnt/vixntProductAssemblyAction!goChooseItem.action', 'single', '选择商品','item');
	}
	function advanceSearch(){
		$("#advanceSearchForm").validationEngine();
		chooseListData("${nvix}/nvixnt/nvixntSaleReturnBillAction!goChooseSaleOrderItem.action?customerAccountId="+$("#customerId").val()+"&itemId="+$("#itemId").val()+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val(),'multi',"选择订单",null,function(){
			var saleOrderItemIds = chooseMap.valueIdSet();
			if(saleOrderItemIds){
				$.ajax({
					url : '${nvix}/nvixnt/nvixntSaleReturnBillAction!createSaleReturnBill.action?ids=' + saleOrderItemIds+"&customerAccountId="+$("#customerId").val()+"&itemId="+$("#itemId").val(),
					cache : false,
					success : function(data) {
						var d  = data.split(":");
						if(d[0] == "1"){
							showMessage(d[1],'success');
							$.ajax({
								url : '${nvix}/nvixnt/nvixntSaleReturnBillAction!goSaveOrUpdate.action?id=' + d[2],
								cache : false,
								success : function(html) {
									$("#mainContent").html(html);
								}
								});
						}else{
							showMessage(d[1]);
						}
					}
					});
			}
		},860,700);
		layer.closeAll('page');
	}
	pageSetUp();
</script>