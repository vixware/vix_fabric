<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-shopping-cart"></i> 采购管理 <span>&gt; 采购订单</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu js-status-update pull-right">
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdate('');">普通采购单</a></li>
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdateByStoreOrder('');">来源门店订单</a></li>
						<li><a href="javascript:void(0);" onclick="choosePurchasePlan('');">来源采购计划</a></li>
						<li><a href="javascript:void(0);" onclick="choosePurchaseInquire('');">来源采购询价</a></li>
						<li><a href="javascript:void(0);" onclick="chooseSalesOrder('');">来源销售订单</a></li>
					</ul>
				</div>
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-gear"></i>&nbsp;其他&nbsp;<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);" onclick="exportExcel()">导出采购单</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="purchasePlanId" name="" value="">
	<input type="hidden" id="salesOrderId" name="" value="">
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>采购订单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							主题: <input type="text" value="" class="form-control" id="mytitle">
						</div>
						<button onclick="purchaseOrderTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="var index = layer.open({title:'高级检索',type: 1, area: ['700px', '300px'], content: $('#advanceSearch')});" type="button" class="btn btn-primary">
							<i class="glyphicon glyphicon-search"></i> 高级检索
						</button>
						<button onclick="clearSearchCondition('name,title,code,purchasePerson,createTime,supplierName,supplierId,businessTypeCode,orderTypeCode',purchaseOrderTable);" type="button" class="btn btn-default">
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
									<label class="col-md-2 control-label">供应商:</label>
									<div class="col-md-4">
										<div class="row">
											<div class="col-sm-12">
												<div class="input-group">
													<input id="supplierId" value="" class="form-control validate[required]" type="hidden" readonly="readonly" />
													<input id="supplierName" value="" class="form-control validate[required]" type="text" readonly="readonly" />
													<div class="input-group-btn">
														<button onclick="goChooseSupplier();" type="button" class="btn btn-info">
															<i class="glyphicon glyphicon-search"></i>
														</button>
														<button onclick="$('#supplierId').val('');$('#supplierName').val('');" type="button" class="btn btn-default">
															<i class="glyphicon glyphicon-repeat"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<lable class="col-md-2 control-label">业务类型</lable>
									<div class="col-md-4"> 
										<select id="businessTypeCode" class="form-control">
											<option value="">------请选择------</option>
											<c:forEach items="${bizTypeList}" var="entity">
												<option value="${entity.code}">${entity.name}</option>
											</c:forEach>
										</select>
									</div>
									<lable class="col-md-2 control-label">单据类型:</lable>
									<div class="col-md-4"> 
										<select id="orderTypeCode" class="form-control">
											<option value="">------请选择------</option>
											<c:forEach items="${orderTypeList}" var="entity">
												<option value="${entity.code}">${entity.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<lable class="col-md-2 control-label">采购人:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="purchasePerson" placeholder="采购人" class="form-control"/>
									</div>
									<lable class="col-md-2 control-label">采购日期:</lable>
									<div class="col-md-4"> 
										<input type="text" class="form-control" placeholder="采购日期" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
									</div>
								</div>
								<div class="form-group" style="text-align:center;">
									<button onclick="advanceSearch();" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="clearSearchCondition('name,title,code,purchasePerson,createTime,supplierName,supplierId,businessTypeCode,orderTypeCode',purchaseOrderTable);"
										type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
				<table id="purchaseOrderTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var purchaseOrderColumns = [ {
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
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var print = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"printPurchaseOrder('" + data.id + "');\" title='打印订单'><span class='txt-color-blue pull-right'><i class='fa fa-print'></i></span></a>";
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goShowPurchaseOrder('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var inv = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateInbond('" + data.id + "');\" title='到货入库'><span class='txt-color-blue pull-right'><i class='fa fa-cubes'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		if(data.isInventory == "1"){
			return update + "&nbsp;&nbsp;"+ show +"&nbsp;&nbsp;"  + print + "&nbsp;&nbsp;" + del;
		}
		if(data.type == '1'){
			return update + "&nbsp;&nbsp;" + show +"&nbsp;&nbsp;"  + print + "&nbsp;&nbsp;" +inv + "&nbsp;&nbsp;" + del;
		}else if(data.type == '0'){
			return inv + "&nbsp;&nbsp;" + show +"&nbsp;&nbsp;"  + print + "&nbsp;&nbsp;"  + del;
		}
	}
	} ];
	var purchaseOrderTable = initDataTable("purchaseOrderTableId", "${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goSingleList.action", purchaseOrderColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#mytitle").val();
		var code = $("#code").val();
		var purchasePerson = $("#purchasePerson").val();
		var createTime = $("#createTime").val();
		var supplierId = $("#supplierId").val();
		var businessTypeCode = $("#businessTypeCode").val();
		var orderTypeCode = $("#orderTypeCode").val();
		createTime = Url.encode(createTime);
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"code" : code,
		"purchasePerson" : purchasePerson,
		"createTime" : createTime,
		"supplierId" : supplierId,
		"businessTypeCode" : businessTypeCode,
		"orderTypeCode" : orderTypeCode,
		"title" : title
		};
	});
	function advanceSearch(){
		purchaseOrderTable.ajax.reload();
		layer.closeAll('page');
	}
	//新增
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goShowPurchaseOrder(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goShowPurchaseOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goSaveOrUpdateByStoreOrder(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goSaveOrUpdateByStoreOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goSaveOrUpdateInbond(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntInboundWarehouseAction!converterPurchaseOrderToStockrecords.action?purchaseOrderid=' + id,
		cache : false,
		success : function(result) {
			var r = result.split(":");
			if(r.length > 0){
				$.ajax({
					url : '${nvix}/nvixnt/vixntInboundWarehouseAction!goSaveOrUpdate.action?id=' + r[0],
					cache : false,
					success : function(html) {
						$("#mainContent").html(html);
					}
					});
			}
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!deleteById.action?id=' + id, '是否删除采购订单?', purchaseOrderTable);
	};
	function choosePurchasePlan(){
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!choosePurchasePlan.action', 'single', '选择采购计划', 'purchasePlan',function(){
			$.ajax({
				url : '${nvix}/nvixnt/purchase/vixntPurchasePlanAction!createPurchaseOrder.action?id=' + $("#purchasePlanId").val(),
				cache : false,
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "0"){
						showMessage(r[1]);
						loadContent('purchase_purchasePlan','${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goList.action');
					}else{
						$.ajax({
							url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goSaveOrUpdate.action?id=' + r[2],
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
	function chooseSalesOrder(){
		chooseListData('${nvix}/nvixnt/nvixntSalesOutBoundAction!goChooseSalesOrder.action', 'single', '选择销售订单', 'salesOrder',function(){
			$.ajax({
				url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!createPurchaseOrderBySalesOrder.action?id=' + $("#salesOrderId").val(),
				cache : false,
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "0"){
						showMessage(r[1]);
						loadContent('purchase_purchaseOrder','${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goList.action');
					}else{
						showMessage(r[1]);
						$.ajax({
							url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goSaveOrUpdate.action?id=' + r[2],
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
	function choosePurchaseInquire(){
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!goChoosePurchaseInquire.action', 'single', '选择采购询价单', 'salesOrder',function(){
			$.ajax({
				url : '${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!createPurchaseOrder.action?id=' + $("#salesOrderId").val(),
				cache : false,
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "0"){
						showMessage(r[1]);
						loadContent('purchase_purchaseOrder','${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goList.action');
					}else{
						showMessage(r[1]);
						$.ajax({
							url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goSaveOrUpdate.action?id=' + r[2],
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
	
	//var LODOP;
	function printPurchaseOrder(id){
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!printPurchaseOrder.action?id='+id,
		cache : false,
		success : function(html) {
			LODOP = getLodop();
			LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
			LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
			LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);// 打印后自动关闭预览窗口
			LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
			LODOP.SET_PRINT_PAGESIZE(3, "240mm", "45mm", "");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
			LODOP.SET_PREVIEW_WINDOW(1, 0, 0, 1024, 550, ""); // 2上下打印工具条都显示
			LODOP.PRINT(); 
			LODOP.PREVIEW();
		}
		});
	}
	function exportExcel() {
		form = document.getElementById("exportMD");
		form.action = "${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!exportPurchaseOrderExcel.action";
		form.submit();
	};
	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};
</script>