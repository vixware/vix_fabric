<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 销售管理 <span>> 销售订单 </span>
			</h1>
		</div>
		<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增&nbsp;<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);" onclick="saveOrUpdate('');">新增销售单</a></li>
						<li><a href="javascript:void(0);" onclick="goChooseSalesQuotation()">源自报价单</a></li>
					</ul>
				</div>
				<div class="btn-group">
				<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
					<i class="fa fa-gear"></i>&nbsp;其他&nbsp;<span class="caret"></span>
				</button>
				<ul class="dropdown-menu pull-right">
					<li><a href="javascript:void(0);" onclick="exportExcel()">导出销售单</a></li>
				</ul>
			</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="salesQuotationId" />
	<section id="" class="">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" id="salesOrderHead" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>销售订单列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="salesOrderSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										主题:<input type="text" value="" placeholder="销售单主题" class="form-control" id="searchTitle"> 客户:<input type="text" value="" placeholder="客户名称" class="form-control" id="searchCustomerName">
									</div>
									<button onclick="salesOrderTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="var index = layer.open({title:'高级检索',type: 1, area: ['700px', '300px'], content: $('#advanceSearch')});" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-search"></i> 高级检索
									</button>
									<button onclick="clearSearchCondition('searchCustomerName,searchTitle,code,salePerson,bizTypeId,orderTypeId,createTime',salesOrderTable);" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
								<div id="advanceSearch" style="padding:20px 15px;display:none;">
									<form id="advanceSearchForm" class="form-horizontal">
										<fieldset>
											<div class="form-group">
												<lable class="col-md-2 control-label">单据编码:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="code" placeholder="单据编码" class="form-control"/>
												</div>
												<lable class="col-md-2 control-label">负责人:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="salePerson" placeholder="负责人" class="form-control"/>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">业务类型</lable>
												<div class="col-md-4"> 
													<select id="bizTypeId" class="form-control">
														<option value="">------请选择------</option>
														<c:forEach items="${bizTypeList}" var="entity">
															<option value="${entity.id}">${entity.name}</option>
														</c:forEach>
													</select>
												</div>
												<lable class="col-md-2 control-label">单据类型:</lable>
												<div class="col-md-4"> 
													<select id="orderTypeId" class="form-control">
														<option value="">------请选择------</option>
														<c:forEach items="${orderTypeList}" var="entity">
															<option value="${entity.id}">${entity.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">单据日期:</lable>
												<div class="col-md-4"> 
													<input type="text" class="form-control" placeholder="单据日期" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
												</div>
											</div>
											<div class="form-group" style="text-align:center;">
												<button onclick="advanceSearch();" type="button" class="btn btn-primary">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="clearSearchCondition('searchCustomerName,searchTitle,code,salePerson,bizTypeId,orderTypeId,createTime',salesOrderTable);"
													type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
											</div>
										</fieldset>
									</form>
								</div>
							</div>
							<table id="salesOrder" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="5%">编号</th>
										<th width="10%">编码</th>
										<th width="15%">主题</th>
										<th width="15%">客户</th>
										<th width="10%">金额</th>
										<th width="10%">状态</th>
										<th width="7%">负责人</th>
										<th width="10%">单据日期</th>
										<th width="15%">操作</th>
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
	var salesOrderColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "title",
	"data" : function(data) {
		return data.title;
	}
	}, {
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccount == null ? '' : data.customerAccount.name;
	}
	}, {
	"name" : "amountTotal",
	"data" : function(data) {
		return data.amountTotal == null ? '￥0' : '￥' + data.amountTotal;
	}
	}, {
	"name" : "status",
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
	"name" : "salePerson",
	"data" : function(data) {
		return data.salePerson == null ? '' : data.salePerson.name;
	}
	}, {
	"name" : "billDate",
	"data" : function(data) {
		return data.billDateStr;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.id != null) {
			var print = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"printSalesOrder('" + data.id + "');\" title='打印订单'><span class='txt-color-blue pull-right'><i class='fa fa-print'></i></span></a>";
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var showOrder = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"showOrder('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;"+showOrder+"&nbsp;&nbsp;"+print+"&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];
	
	var salesOrderTable = initDataTable("salesOrder", "${nvix}/nvixnt/nvixSalesOrderAction!goListContentJson.action", salesOrderColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchCustomerName").val();
		var title = $("#searchTitle").val();
		var code = $("#code").val();
		var salePerson = $("#salePerson").val();
		var bizTypeId = $("#bizTypeId").val();
		var orderTypeId = $("#orderTypeId").val();
		var createTime = $("#createTime").val();
		name = Url.encode(name);
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title,
		"code" : code,
		"salePerson" : salePerson,
		"bizTypeId" : bizTypeId,
		"orderTypeId" : orderTypeId,
		"createTime" : createTime,
		"name" : name
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixSalesOrderAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixSalesOrderAction!deleteById.action?id=' + id, '是否删除该销售单?', salesOrderTable);
	}
	function exportExcel() {
		form = document.getElementById("exportMD");
		form.action = "${nvix}/nvixnt/nvixSalesOrderAction!exportSalesOrderExcel.action";
		form.submit();
	};
	var LODOP;
	function printSalesOrder(id){
		$.ajax({
		url : '${nvix}/nvixnt/nvixSalesOrderAction!goPrintOrder.action?id='+id,
		cache : false,
		success : function(html) {
			LODOP = getLodop();
			LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
			LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
			LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);// 打印后自动关闭预览窗口
			LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
			LODOP.SET_PRINT_PAGESIZE(3, "240mm", "45mm", "");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
			LODOP.SET_PREVIEW_WINDOW(1, 0, 0, 1024, 550, ""); // 2上下打印工具条都显示
			/* LODOP.PRINT(); */
			LODOP.PREVIEW();
		}
		});
	}
	function goChooseSalesQuotation(){
		chooseListData('${nvix}/nvixnt/nvixSalesOrderAction!goChooseSalesQuotation.action', 'single', '选择报价单', 'salesQuotation',function(){
			$.ajax({
				url : '${nvix}/nvixnt/nvixSalesOrderAction!convertSalesQuotationToSalesOrder.action?id=' + $("#salesQuotationId").val(),
				cache : false,
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "1"){
						showMessage(r[1]);
						loadContent('purchase_purchasePlan','${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goList.action');
					}else{
						showMessage(r[1]);
						$.ajax({
							url : '${nvix}/nvixnt/nvixSalesOrderAction!goSaveOrUpdate.action?id=' + r[2],
							cache : false,
							success : function(html) {
								$("#mainContent").html(html);
							}
							});
					}
				}
				}); 
		});
	}
	function showOrder(id){
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixSalesOrderAction!goShowSalesOrder.action?id=' + id);
	}
	function advanceSearch(){
		salesOrderTable.ajax.reload();
		layer.closeAll('page');
	}
	pageSetUp();
</script>