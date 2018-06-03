<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i> 采购管理 <span onclick="">&gt; 采购退货</span>
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
				<button class="btn btn-default" type="button" onclick="loadContent('purchase_purchaseReturn','${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>退货订单</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="purchaseReturnForm">
				<fieldset>
					<input type="hidden" id="purchaseReturnId" name="purchaseReturn.id" value="${purchaseReturn.id}" /> 
					<input type="hidden" id="sourceClassName" name="purchaseReturn.sourceClassName" value="${purchaseReturn.sourceClassName}" /> 
					<input type="hidden" id="sourceBillCode" name="purchaseReturn.sourceBillCode" value="${purchaseReturn.sourceBillCode}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 单号:</label>
						<div class="col-md-3">
							<input id="code" name="purchaseReturn.code" value="${purchaseReturn.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" name="purchaseReturn.name" value="${purchaseReturn.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 供应商:</label>
						<div class="col-md-3">
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
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 部门:</label>
						<div class="col-md-3">
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
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">币种:</label>
						<div class="col-md-3">
							<select id="currency" name="purchaseReturn.currency" class="form-control validate[required]">
								<c:forEach items="${currencyTypeList}" var="entity">
									<option  value="${entity.name}"<c:if test='${purchaseReturn.currency eq entity.name}'>selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>过账日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="postingDate" name="purchaseReturn.postingDate" value="${purchaseReturn.postingDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'postingDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>交货日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="deliveryDate" name="purchaseReturn.deliveryDate" value="${purchaseReturn.deliveryDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'deliveryDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 采购人:</label>
						<div class="col-md-3">
							<input id="purchasePerson" name="purchaseReturn.purchasePerson" value="${purchaseReturn.purchasePerson}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 联系人:</label>
						<div class="col-md-3">
							<input id="contactPerson" name="purchaseReturn.contactPerson" value="${purchaseReturn.contactPerson}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">退货原因:</label>
						<div class="col-md-3">
							<input id="reason" name="purchaseReturn.reason" value="${purchaseReturn.reason}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="purchaseReturn.memo" class="form-control" rows="4">${purchaseReturn.memo}</textarea>
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>退货明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="名称" class="form-control" id="searchName">
										</div>
										<button onclick="purchaseReturnItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchName').val('');purchaseReturnItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="goSaveOrUpdatePurchaseReturnItem('','新增');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="新增明细" />
											</button>
										</div>
									</div>
								</div>
								<table id="purchaseReturnItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
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
							<button class="btn btn-default" type="button" onclick="loadContent('purchase_purchaseReturn','${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goList.action');">
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
/** 初始化所属部门下拉列表树 */
var showOrgUnitMenu = initDropListTree("orgUnitTreeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","internalUnitId","internalUnitName","customerAccountOrgUnitTree","OrgUnitContent");
function goChooseSupplier() {
	chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
};
$("#purchaseReturnForm").validationEngine();
function saveOrUpdate() {
	if ($("#purchaseReturnForm").validationEngine('validate')) {
		$("#purchaseReturnForm").ajaxSubmit({
		type : "post",
		url : "${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			loadContent('', '${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goList.action');
		}
		});
	} else {
		return false;
	}
};

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
	}, {
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
	}, {
	"title" : "BAR码",
	"name" : "skuCode",
	"data" : function(data) {
		return data.skuCode;
	}
	}, {
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
	"title" : "订货数量",
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
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePurchaseReturnItem('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletePurchaseReturnItemById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];
	var purchaseReturnItemTable = initDataTable("purchaseReturnItemTableId", "${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goSinglePurchaseReturnItemsList.action", purchaseOrderLineItemColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#purchaseReturnId").val();
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
	function deletePurchaseReturnItemById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!deletePurchaseReturnItemById.action?id=' + id, '是否删除退货明细?', purchaseReturnItemTable);
	}
	function goSaveOrUpdatePurchaseReturnItem(id,title){
		var purchaseReturnId = $("#purchaseReturnId").val();
		if(purchaseReturnId){
			openWindowForShow('${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goSaveOrUpdatePurchaseReturnItem.action?id='+id, title, 850, 625);
		}else{
			if ($("#purchaseReturnForm").validationEngine('validate')) {
				$("#purchaseReturnForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "0"){
						showMessage(r[1]);
						return;
					}else{
						showMessage(r[1]);
						$("#purchaseReturnId").val(r[2]);
						openWindowForShow('${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goSaveOrUpdatePurchaseReturnItem.action?id='+id, title, 850, 625);
					}
				}
				});
			}else{
				return false;
			}
		}
	}
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
	function audit() {
		if ($("#purchaseReturnForm").validationEngine('validate')) {
			$("#purchaseReturnForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!audit.action",
			dataType : "text",
			success : function(result) {
				showMessage(result, 'success');
				loadContent('', '${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goList.action');
			}
			});
		} else {
			return false;
		}
	}
</script>