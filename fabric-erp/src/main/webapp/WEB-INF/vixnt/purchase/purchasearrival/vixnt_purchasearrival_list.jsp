<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i> 采购管理 <span>&gt; 采购到货</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu js-status-update pull-right">
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdate('');">新增退货单</a></li>
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdateByPurchaseOrder('');">来源采购订单</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- row -->
	<input id="purchaseOrderId" type="hidden">
	<div class="jarviswidget" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>到货单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							主题: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="purchaseArrivalTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="var index = layer.open({title:'高级检索',type: 1, area: ['700px', '230px'], content: $('#advanceSearch')});" type="button" class="btn btn-primary">
							<i class="glyphicon glyphicon-search"></i> 高级检索
						</button>
						<button onclick="clearSearchCondition('name,supplierId,supplierName,code,purchasePerson,createTime',purchaseArrivalTable);" type="button" class="btn btn-default">
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
									<label class="col-md-2 control-label"><span class="text-danger">*</span> 供应商:</label>
									<div class="col-md-4">
										<div class="row">
											<div class="col-sm-12">
												<div class="input-group">
													<input type="hidden" id="supplierId" name="purchaseArrival.supplierId" value="${purchaseArrival.supplierId}" />
													<input type="hidden" id="supplierCode" name="purchaseArrival.supplierCode" value="${purchaseArrival.supplierCode}" />
													<input id="supplierName" name="purchaseArrival.supplierName" value="${purchaseArrival.supplierName}" class="form-control validate[required]" type="text" readonly="readonly" />
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
									<lable class="col-md-2 control-label">采购人:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="purchasePerson" placeholder="采购人" class="form-control"/>
									</div>
									<lable class="col-md-2 control-label">到货日期:</lable>
									<div class="col-md-4"> 
										<input type="text" class="form-control" placeholder="到货日期" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
									</div>
								</div>
								<div class="form-group" style="text-align:center;">
									<button onclick="advanceSearch();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="clearSearchCondition('name,supplierId,supplierName,code,purchasePerson,createTime',purchaseArrivalTable);"
										type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
				<table id="purchaseArrivalTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	//我的报销单
	var purchaseArrivalColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "编码",
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
	"title" : "交货时间",
	"orderable" : false,
	"data" : function(data) {
		return data.deliveryDateStr;
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var inv = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateInbond('" + data.id + "');\" title='到货入库'><span class='txt-color-blue pull-right'><i class='fa fa-cubes'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + inv + "&nbsp;&nbsp;" + del;

	}
	} ];
	var purchaseArrivalTable = initDataTable("purchaseArrivalTableId", "${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goSingleList.action", purchaseArrivalColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		var supplierId = $("#supplierId").val();
		var code = $("#code").val();
		var purchasePerson = $("#purchasePerson").val();
		var createTime = $("#createTime").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"supplierId" : supplierId,
		"code" : code,
		"purchasePerson" : purchasePerson,
		"createTime" : createTime,
		"name" : searchName
		};
	});

	//新增
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function show(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!show.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!deleteById.action?id=' + id, '是否删除到货单?', purchaseArrivalTable);
	};
	function goSaveOrUpdateInbond(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntInboundWarehouseAction!converterPurchaseArrivalToStockrecords.action?purchaseArrivalId=' + id,
		cache : false,
		success : function(stockRecordsId) {
			$.ajax({
			url : '${nvix}/nvixnt/vixntInboundWarehouseAction!goSaveOrUpdate.action?id=' + stockRecordsId,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
		}
		});
	};
	function goSaveOrUpdateByPurchaseOrder() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goChoosePurchaseOrder.action', 'single', '选择采购订单', 'purchaseOrder', function() {
			$.ajax({
			url : '${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!createPurchaseArrivalByPurchaseOrder.action?id=' + $("#purchaseOrderId").val(),
			cache : false,
			success : function(result) {
				var r = result.split(":");
				if (r[0] == "0") {
					showMessage(r[1]);
					loadContent('purchase_purchaseOrder', '${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goList.action');
				} else {
					showMessage(r[1]);
					$.ajax({
					url : '${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goSaveOrUpdate.action?id=' + r[2],
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
	function advanceSearch(){
		purchaseArrivalTable.ajax.reload();
		layer.closeAll('page');
	}
	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};
</script>