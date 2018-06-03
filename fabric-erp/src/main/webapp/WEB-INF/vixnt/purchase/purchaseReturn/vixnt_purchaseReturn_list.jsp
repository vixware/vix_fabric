<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i> 采购管理 <span>&gt; 采购退货</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<!-- <button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button> -->
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增&nbsp;<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdate('');">新增采购退货</a></li>
						<li><a href="javascript:void(0);" onclick="choosePurchaseOrder();">源自采购订单</a></li>
						<li><a href="javascript:void(0);" onclick="choosePurchaseArrival();">源自采购到货单</a></li>
						<li><a href="javascript:void(0);" onclick="goChoosePurchaseInBound();">源自采购入库单</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="purchaseOrderId" name="" value="">
	<input type="hidden" id="purchaseArrivalId" name="" value="">
	<input type="hidden" id="stockRecordsId" name="" value="">
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
							主题: <input type="text" value="" class="form-control" id="mytitle">
						</div>
						<button onclick="purchaseReturnTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="var index = layer.open({title:'高级检索',type: 1, area: ['700px', '300px'], content: $('#advanceSearch')});" type="button" class="btn btn-primary">
							<i class="glyphicon glyphicon-search"></i> 高级检索
						</button>
						<button onclick="clearSearchCondition('name,supplierName,internalUnitName,code,contactPerson,createTime',purchaseReturnTable);" type="button" class="btn btn-default">
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
													<input id="supplierName" name="purchaseReturn.supplierName" value="${purchaseReturn.supplierName}" class="form-control validate[required]" type="text" readonly="readonly" />
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
									<label class="col-md-2 control-label"><span class="text-danger">*</span> 部门:</label>
									<div class="col-md-4">
										<div class="row">
											<div class="col-sm-12">
												<div id="orgUnitTreeMenu" class="input-group">
													<input id="internalUnitName" name="purchaseReturn.requireDepartment" type="text" onfocus="showOrgUnitMenu(); return false;" value="${purchaseReturn.requireDepartment}" type="text" class="form-control validate[required]" />
													<div class="input-group-btn">
														<button type="button" class="btn btn-default" onclick="$('#internalUnitId').val('');$('#internalUnitName').val('');$('#internalUnitType').val('');">清空</button>
													</div>
													<div id="OrgUnitContent" class="OrgUnitContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
														<ul id="customerAccountOrgUnitTree" class="ztree"></ul>
													</div>
												</div>
											</div>
										</div>
									</div>
									<lable class="col-md-2 control-label">业务员:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="contactPerson" placeholder="业务员" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<lable class="col-md-2 control-label">采购人:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="purchasePerson" placeholder="采购人" class="form-control"/>
									</div>
									<lable class="col-md-2 control-label">退货日期:</lable>
									<div class="col-md-4"> 
										<input type="text" class="form-control" placeholder="退货日期" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
									</div>
								</div>
								<div class="form-group" style="text-align:center;">
									<button onclick="advanceSearch();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="clearSearchCondition('name,supplierName,internalUnitName,code,contactPerson,createTime',purchaseReturnTable);"
										type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
				<table id="purchaseReturnTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var purchaseReturnColumns = [ {
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
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goShowPurchaseReturn('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		//var inv = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateInbond('" + data.id + "');\" title='到货入库'><span class='txt-color-blue pull-right'><i class='fa fa-cubes'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update+"&nbsp;&nbsp;" + show +"&nbsp;&nbsp;" + del;
	}
	} ];
	var purchaseReturnTable = initDataTable("purchaseReturnTableId", "${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goSingleList.action", purchaseReturnColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#mytitle").val();
		var supplierName = $("#supplierName").val();
		var requireDepartment = $("#internalUnitName").val();
		var code = $("#code").val();
		var contactPerson = $("#contactPerson").val();
		var purchasePerson = $("#purchasePerson").val();
		var createTime = $("#createTime").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"createTime" : createTime,
		"contactPerson" : contactPerson,
		"requireDepartment" : requireDepartment,
		"supplierName" : supplierName,
		"purchasePerson" : purchasePerson,
		"code" : code,
		"name" : name
		};
	});

	//新增
	function goSaveOrUpdate(id) {
		$.ajax({
			url : '${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goSaveOrUpdate.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	};
	//新增
	function goShowPurchaseReturn(id) {
		$.ajax({
			url : '${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goShowPurchaseReturn.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	};
	function choosePurchaseOrder(){
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goChoosePurchaseOrder.action', 'single', '选择采购订单', 'purchaseOrder',function(){
			$.ajax({
				url : '${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!createPurchaseReturnByPurchaseOrder.action?id=' + $("#purchaseOrderId").val(),
				cache : false,
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "0"){
						showMessage(r[1]);
						loadContent('purchase_purchaseReturn','${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goList.action');
					}else{
						$.ajax({
							url : '${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goSaveOrUpdate.action?id=' + r[1],
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
	function choosePurchaseArrival(){
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goChoosePurchaseArrival.action', 'single', '选择采购到货单', 'purchaseArrival',function(){
			$.ajax({
				url : '${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!createPurchaseReturnByPurchaseArrival.action?id=' + $("#purchaseArrivalId").val(),
				cache : false,
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "0"){
						showMessage(r[1]);
						loadContent('purchase_purchasePlan','${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goList.action');
					}else{
						$.ajax({
							url : '${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goSaveOrUpdate.action?id=' + r[1],
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
	function goChoosePurchaseInBound(){
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goChoosePurchaseInBound.action', 'single', '选择采购入库单', 'stockRecords',function(){
			$.ajax({
				url : '${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!createPurchaseReturnByPurchaseInBound.action?id=' + $("#stockRecordsId").val(),
				cache : false,
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "0"){
						showMessage(r[1]);
						loadContent('purchase_purchasePlan','${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goList.action');
					}else{
						$.ajax({
							url : '${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goSaveOrUpdate.action?id=' + r[1],
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
	function goSaveOrUpdateByStoreOrder(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goSaveOrUpdateByStoreOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!deleteById.action?id=' + id, '是否删除退货订单?', purchaseReturnTable);
	};
	var showOrgUnitMenu = initDropListTree("orgUnitTreeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","internalUnitId","internalUnitName","customerAccountOrgUnitTree","OrgUnitContent");
	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};
	function advanceSearch(){
		purchaseReturnTable.ajax.reload();
		layer.closeAll('page');
	}
</script>