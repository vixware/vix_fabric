<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<fieldset>
	<div class="col-sm-8" style="text-align: left; padding: 5px 5px;">
		<div id="stockInDetailDiv" class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i>
				</span>
				<h2>商品列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								<div id="treeMenu" class="input-group col-md-12">
									<input type="hidden" id="chooseProductCategoryId" name="productCategoryId" value="${productCategoryId}" /> 
									<input id="chooseProductCategoryName" type="text" onfocus="showMenu(); return false;" value="${productCategoryName}" placeholder="商品分类" class="form-control" />
									<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
										<ul id="chooseProductCategoryZtree" class="ztree"></ul>
									</div>
								</div>
							</div>
							<div class="form-group">
								<input type="text" value="" placeholder="商品名称" class="form-control" id="searchProductName">
							</div>
							<button onclick="chooseEcProductTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button type="button" class="btn btn-default" onclick="$('#searchProductName').val('');$('#chooseProductCategoryId').val('');$('#chooseProductCategoryName').val('');chooseEcProductTable.ajax.reload();">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="chooseEcProduct" class="table table-striped table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th width="15%">编码</th>
								<th width="70%">名称</th>
								<th width="15%">采购价格</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-4" style="text-align: left; padding: 0px 0px;">
		<form id="purchaseInquiryDetailForm" class="form-horizontal" style="padding: 5px 5px;" action="">
			<div class="jarviswidget">
				<header>
					<h2>询价明细</h2>
				</header>
				<div>
					<div class="widget-body">
						<fieldset>
							<input type="hidden" id="supplierId" value="${purchaseInquire.supplierId}" /> 
							<input type="hidden" id="itemId" name="purchaseInquiryDetail.itemId" value="${purchaseInquiryDetail.itemId}" /> 
							<input type="hidden" id="purchaseInquiryDetailId" name="purchaseInquiryDetail.id" value="${purchaseInquiryDetail.id}" /> 
							<input type="hidden" id="purchaseInquiryDetailpurchaseInquirelId" name="purchaseInquiryDetail.purchaseInquire.id" value="${purchaseInquiryDetail.purchaseInquire.id}" />
							<div class="form-group">
								<label class="col-md-4 control-label">商品编码:
								</label>
								<div class="col-md-8">
									<input id="itemCode" name="purchaseInquiryDetail.itemCode" value="${purchaseInquiryDetail.itemCode}" data-prompt-position="topLeft" class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>商品名称:</label>
								<div class="col-md-8">
									<input id="itemName" name="purchaseInquiryDetail.itemName" value="${purchaseInquiryDetail.itemName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<%-- <div class="form-group">
								<label class="col-md-4 control-label"> <span class="text-danger">*</span>SKU编码:
								</label>
								<div id="skuTd" class="col-md-8">
									<input id="skuCode" name="purchaseOrderLineItem.skuCode" value="${purchaseOrderLineItem.skuCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div> --%>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>价格:</label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon">￥</span> <input id="price" name="purchaseInquiryDetail.price" value="${purchaseInquiryDetail.price}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">单位:</label>
								<div class="col-md-8">
									<input id="unit" name="purchaseInquiryDetail.unit" value="${purchaseInquiryDetail.unit}" data-prompt-position="topLeft" class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>数量:</label>
								<div class="col-md-8">
									<s:if test="null == purchaseOrderLineItem">
										<input id="amount" name="purchaseInquiryDetail.amount" value="1" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:if>
									<s:else>
										<input id="amount" name="purchaseInquiryDetail.amount" value="${purchaseInquiryDetail.amount}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:else>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>需求时间:</label>
								<div class="col-md-8">
									<div class="input-group">
										<input id="purchaseInquiryDetailCreateTime" name="purchaseInquiryDetail.createTime" value="${purchaseInquiryDetail.createTimeStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'purchaseInquiryDetailCreateTime'});"><i class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>收货仓库:</label>
								<div class="col-md-8">
									<input id="invWarehouseId" value="" type="hidden" /> 
									<div class="input-group">
										<input id="invWarehouseName" name="purchaseInquiryDetail.warehouse" value="${purchaseInquiryDetail.warehouse}" onfocus="goChooseWarehouse();" data-prompt-position="topLeft"
											class="form-control validate[required]" type="text" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="goChooseWarehouse();">选择</button>
										</div>
									</div>
								</div>
							</div>
						</fieldset>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-12">
									<button type="button" class="btn btn-success" onclick="mergeStockInDetail();">
										<s:if test="purchaseOrderLineItem.id != ''">
											<i class="fa fa-edit"></i> &nbsp; 修改
										</s:if>
										<s:else>
											<i class="fa fa-save"></i> &nbsp; 保存
										</s:else>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</fieldset>
<script type="text/javascript">
	var chooseEcProductColumns = [ {
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"name" : "price",
	"data" : function(data) {
		return data.price == ''|| data.price == null? '' : '￥' + data.price;
	}
	} ];

	var chooseEcProductTable = initDataTable("chooseEcProduct", "${nvix}/nvixnt/vixntInboundWarehouseAction!goChooseListContent.action", chooseEcProductColumns, function(page, pageSize, orderField, orderBy) {
		var productCategoryId = $("#chooseProductCategoryId").val();
		var supplierId = $("#supplierId").val();
		var name = $("#searchProductName").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"selectName" : name,
		"categoryId" : productCategoryId,
		"supplierId" : supplierId
		};
	}, 10, '1', '0');

	$('#chooseEcProduct tbody').on('click', 'tr', function() {
		var data = chooseEcProductTable.row(".selected").data();
		if (data != undefined) {
			$("#itemId").val(data.id);
			$("#itemCode").val(data.code);
			$("#itemName").val(data.name);
			/* $("#skuCode").val(data.skuCode); */
			$("#price").val(data.price);
			$("#unit").val(data.measureUnit.name);
			//$("#measureUnitId").val(data.measureUnit.id);
			$("#amount").val("1");
			
			
		} else {
			clearStockInDetailForm();
		}
	});

	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/vixntInboundWarehouseAction!findTreeToJson.action", "chooseProductCategoryId", "chooseProductCategoryName", "chooseProductCategoryZtree", "menuContent");

	function mergeStockInDetail() {
		if ($('#purchaseInquiryDetailForm').validationEngine('validate')) {
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			//入库单主体ID
			$("#purchaseInquiryDetailpurchaseInquirelId").val($("#purchaseInquireId").val());
			$("#purchaseInquiryDetailForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!saveOrUpdatePurchaseInquireDetail.action",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				purchaseInquireDetailTable.ajax.reload();
				showMessage(result, 'success');
				clearStockInDetailForm();
				$.ajax({
					url : '${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!getOrderItemTotal.action?id=' + $("#purchaseInquireId").val(),
					cache : false,
					success : function(json) {
						$("#totalAmount").val(json);
					}
				});
			}
			});
		}
	};

	/** 清空输入项 */
	function clearStockInDetailForm() {
		$("#itemId").val("");
		$("#itemCode").val("");
		$("#itemName").val("");
		/* $("#skuCode").val(""); */
		$("#price").val("");
		$("#amount").val("");
		$("#unit").val("");
		/* $("#purchaseOrderLineItemCreateTime").val("");
		$("#invWarehouseId").val("");
		$("#invWarehouseName").val(""); */
		$("#purchaseInquiryDetailId").val("");
	};
	function goChooseWarehouse() {
		chooseListData('${nvix}/nvixnt/vixntTakeStockAction!goChooseWarehouse.action', 'single', '选择仓库', 'invWarehouse');
	};
</script>