<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i> 采购管理 <span onclick="">&gt; 采购订单</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<s:if test="isAllowAudit == 1">
					<button type="button" class="btn bg-color-blueLight txt-color-white" onclick="approvalSalesOrder('');">
						<i class="fa fa-save"></i> &nbsp;提交审批
					</button>
				</s:if>
				<button class="btn btn-default" type="button" onclick="loadContent('purchase_purchaseorder','${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>订单</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="purchaseOrderForm">
				<fieldset>
					<input type="hidden" id="purchaseOrderId" name="purchaseOrder.id" value="${purchaseOrder.id}" /> <input type="hidden" id="supplierId" name="purchaseOrder.supplier.id" value="${purchaseOrder.supplier.id}" />
					<input type="hidden" id="sourceClassName" name="purchaseOrder.sourceClassName" value="${purchaseOrder.sourceClassName}" /> 
					<input type="hidden" id="sourceBillCode" name="purchaseOrder.sourceBillCode" value="${purchaseOrder.sourceBillCode}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 单号:</label>
						<div class="col-md-3">
							<input id="code" name="purchaseOrder.code" value="${purchaseOrder.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" name="purchaseOrder.name" value="${purchaseOrder.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 业务类型:</label>
						<div class="col-md-3">
							<select id="businessTypeCode" name="purchaseOrder.businessTypeCode" class="form-control">
								<c:forEach items="${bizTypeList}" var="entity">
									<option  value="${entity.code}"<c:if test='${purchaseOrder.businessTypeCode eq entity.code}'>selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"> 单据类型:</label>
						<div class="col-md-3">
							<select id="orderTypeCode" name="purchaseOrder.orderTypeCode" class="form-control">
								<c:forEach items="${orderTypeList}" var="entity">
									<option  value="${entity.code}"<c:if test='${purchaseOrder.orderTypeCode eq entity.code}'>selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">采购人:</label>
						<div class="col-md-3">
							<input id="purchasePersonId" name="purchaseOrder.purchasePersonId" value="${purchaseOrder.purchasePersonId}" type="hidden"> <input id="purchasePerson" name="purchaseOrder.purchasePerson" value="${purchaseOrder.purchasePerson}" type="text" class="form-control">
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>采购日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="createTime" name="purchaseOrder.createTime" value="${purchaseOrder.createTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'createTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 供应商:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="supplierName" value="${purchaseOrder.supplier.name }" class="form-control validate[required]" type="text" readonly="readonly" />
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
						<label class="col-md-2 control-label"> 采购合同:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="contractTime" name="purchaseOrder.contractTime" value="${purchaseOrder.contractTime}" class="form-control" type="hidden" readonly="readonly" />
										<input id="contractId" name="purchaseOrder.contractId" value="${purchaseOrder.contractId}" class="form-control" type="hidden" readonly="readonly" />
										<input id="contractCode" name="purchaseOrder.contractCode" value="${purchaseOrder.contractCode}" class="form-control" type="hidden" readonly="readonly" />
										<input id="contractName" name="purchaseOrder.contractName" value="${purchaseOrder.contractName}" class="form-control" type="text" readonly="readonly" />
										<div class="input-group-btn">
											<button onclick="goChooseContract();" type="button" class="btn btn-info">
												<i class="glyphicon glyphicon-search"></i>
											</button>
											<button onclick="$('#contractId').val('');$('#contractName').val('');$('#contractTime').val('');$('#contractCode').val('');" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="purchaseOrder.memo" class="form-control" rows="4">${purchaseOrder.memo }</textarea>
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>订单明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="名称" class="form-control" id="searchName">
										</div>
										<button onclick="purchaseOrderLineItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchName').val('');purchaseOrderLineItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="goSaveOrUpdatePurchaseOrderLineItem('','新增');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="新增明细" />
											</button>
										</div>
									</div>
								</div>
								<table id="purchaseOrderLineItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">合计:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control" id="totalAmount" name="purchaseOrder.totalAmount" value="${purchaseOrder.totalAmount }" type="text" readonly="readonly"> <span class="input-group-addon">(¥)</span>
									</div>
								</div>
							</div>
						</div>
						<label class="control-label col-md-2">创建人:</label>
						<div class="col-md-3">
							<input class="form-control" id="creator" name="purchaseOrder.creator" value="${purchaseOrder.creator }" type="text">
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<s:if test="isAllowAudit == 1">
								<button type="button" class="btn bg-color-blueLight txt-color-white" onclick="approvalSalesOrder('');">
									<i class="fa fa-save"></i> &nbsp;提交审批
								</button>
							</s:if>
							<button class="btn btn-default" type="button" onclick="loadContent('purchase_purchaseorder','${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	var purchaseOrderLineItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "商品编码",
	"name" : "itemCode",
	"data" : function(data) {
		return data.itemCode;
	}
	}, {
	"title" : "商品名称",
	"name" : "itemName",
	"data" : function(data) {
		return data.itemName;
	}
	}, /* {
	"title" : "规格型号",
	"name" : "specification",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"title" : "SKU编码",
	"name" : "skuCode",
	"data" : function(data) {
		return data.skuCode;
	}
	}, */{
	"title" : "单价",
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	},{
	"title" : "税率",
	"name" : "price",
	"data" : function(data) {
		return data.taxRate;
	}
	}, {
	"title" : "数量",
	"name" : "amount",
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"title" : "单位",
	"name" : "unit",
	"data" : function(data) {
		return data.unit;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePurchaseOrderLineItem('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletePurchaseOrderItem('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];
	var purchaseOrderLineItemTable = initDataTable("purchaseOrderLineItemTableId", "${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goPurchaseOrderLineItemList.action", purchaseOrderLineItemColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#purchaseOrderId").val();
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchName,
		"id" : id
		};
	});

	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};
	function goChooseContract() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseContract.action', 'single', '选择采购合同', 'contract');
	};
	
	function goSaveOrUpdatePurchaseOrderLineItem(id, title) {
		if (id != null && id != '' && id != 'undefined') {
			var purchaseOrderId = $('#purchaseOrderId').val();
			openWindowForShow('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goSaveOrUpdatePurchaseOrderLineItem.action?id=' + id + "&purchaseOrderId=" + purchaseOrderId, title, 850, 665);
		} else {
			if ($("#purchaseOrderForm").validationEngine('validate')) {
				$("#purchaseOrderForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "0"){
						layer.alert(r[1]);
						return;
					}else{
						$('#purchaseOrderId').val(r[2]);
						openWindowForShow('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goSaveOrUpdatePurchaseOrderLineItem.action?id=' + id + "&purchaseOrderId=" + r[2], title, 850, 665);
					}
				}
				});
			} else {
				return false;
			}
		}
	};
	function deletePurchaseOrderItem(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!deletePurchaseOrderItem.action?id=' + id, '是否删除采购订单明细?', purchaseOrderLineItemTable, "删除采购订单明细", function() {
			$.ajax({
			url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!getOrderItemTotal.action?id=' + $("#purchaseOrderId").val(),
			cache : false,
			success : function(json) {
				$("#totalAmount").val(json);
			}
			});
		});
	};
	function approvalSalesOrder(){
		layer.confirm('订单提交审批后,将不可修改,确定要审批订单吗?',{
			btn:['确定','取消']
		},function(action){
			layer.close(action);
			audit();
		},function(action){
			layer.close(action);
		});
	}
	$("#purchaseOrderForm").validationEngine();
	function saveOrUpdate() {
		if ($("#purchaseOrderForm").validationEngine('validate')) {
			$("#purchaseOrderForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!saveOrUpdate.action",
			dataType : "text",
			success : function(data) {
				var d = data.split(":");
				showMessage(d[1]);
				loadContent('purchase_purchaseorder', '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	$("#purchaseOrderForm").validationEngine();
	function audit() {
		if ($("#purchaseOrderForm").validationEngine('validate')) {
			$("#purchaseOrderForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!audit.action",
			dataType : "text",
			success : function(result) {
				showMessage(result,'success');
				loadContent('purchase_purchaseorder', '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>