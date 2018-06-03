<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<fieldset>
	<div class="col-sm-7" style="text-align: left; padding: 5px 5px;">
		<div id="stockInDetailDiv" class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-cubes"></i>
				</span>
				<h2>商品列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin: 5px;">
						<div class="form-group" style="margin: 0 0px;">
							<div class="col-md-5">
								<div id="treeMenu" class="input-group col-md-12">
									<input type="hidden" id="chooseProductCategoryId" name="productCategoryId" value="${productCategoryId}" /> <input id="chooseProductCategoryName" type="text" onfocus="showMenu(); return false;" value="${productCategoryName}" class="form-control" />
									<div class="input-group-btn">
										<button type="button" class="btn btn-default" onclick="$('#chooseProductCategoryId').val('');$('#chooseProductCategoryName').val('');chooseEcProductTable.ajax.reload();">清空</button>
									</div>
									<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
										<ul id="chooseProductCategoryZtree" class="ztree"></ul>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<input type="text" value="" placeholder="商品名称" class="form-control" id="searchProductName">
							</div>
							<button onclick="chooseEcProductTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
						</div>
					</div>
					<table id="chooseEcProduct" class="table table-striped table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th width="15%">编码</th>
								<th width="60%">名称</th>
								<th width="25%">采购价格</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-5" style="text-align: left; padding: 0px 0px;">
		<form id="stockRecordLinesForm" class="form-horizontal" style="padding: 5px 5px;">
			<div class="jarviswidget">
				<header>
					<h2>入库单明细</h2>
				</header>
				<div>
					<div class="widget-body">
						<fieldset>
							<input type="hidden" id="stockRecordLinesId" name="stockRecordLines.id" value="${stockRecordLines.id }" /> 
							<input type="hidden" id="stockRecordLinesStockRecordsId" name="stockRecordsId" value="${stockRecordsId }" /> 
							<input type="hidden" id="itemcode" name="stockRecordLines.itemcode" value="${stockRecordLines.itemcode }" />
							<div class="form-group">
								<label class="col-md-3 control-label"><span class="text-danger">*</span>名称:</label>
								<div class="col-md-9">
									<input id="itemname" name="stockRecordLines.itemname" value="${stockRecordLines.itemname}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label"> SKU: </label>
								<div class="col-md-9">
									<input id="skuCode" name="stockRecordLines.skuCode" value="${stockRecordLines.skuCode}" data-prompt-position="topLeft" class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label"><span class="text-danger">*</span>价格:</label>
								<div class="col-md-9">
									<div class="input-group">
										<span class="input-group-addon">￥</span> <input id="unitcost" name="stockRecordLines.unitcost" value="${stockRecordLines.unitcost}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" readonly="readonly" type="text" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label"><span class="text-danger">*</span>数量:</label>
								<div class="col-md-3">
									<s:if test="null == stockRecordLines">
										<input id="quantity" name="stockRecordLines.quantity" value="1" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:if>
									<s:else>
										<input id="quantity" name="stockRecordLines.quantity" value="${stockRecordLines.quantity}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:else>
								</div>
								<label class="col-md-3 control-label"><span class="text-danger">*</span>单位:</label>
								<div class="col-md-3">
									<input id="measureUnitId" name="stockRecordLines.measureUnit.id" value="${stockRecordLines.measureUnit.id}" type="hidden" /> <input id="measureUnitName" name="stockRecordLines.measureUnit.name" value="${stockRecordLines.measureUnit.name}" data-prompt-position="topLeft" class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label"><span class="text-danger">*</span>货位:</label>
								<div class="col-md-9">
									<input id="invShelfId" name="stockRecordLines.invShelf.id" value="${stockRecordLines.invShelf.id}" type="hidden" /> <input id="invShelfName" name="stockRecordLines.invShelf.name" value="${stockRecordLines.invShelf.name}" onfocus="chooseLocation();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label"><span class="text-danger">*</span>供应商:</label>
								<div class="col-md-9">
									<input id="supplierId" name="stockRecordLines.supplier.id" value="${stockRecordLines.supplier.id}" type="hidden" /> <input id="supplierName" name="stockRecordLines.supplier.name" value="${stockRecordLines.supplier.name}" onfocus="goChooseSupplier();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">批次号:</label>
								<div class="col-md-9">
									<input id="batchcode" name="stockRecordLines.batchcode" value="${stockRecordLines.batchcode}" class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">有效期:</label>
								<div class="col-md-9">
									<div class="input-group">
										<input id="massunitEndTime" name="stockRecordLines.massunitEndTime" value="${stockRecordLines.massunitEndTimeStr}" data-prompt-position="topLeft" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'massunitEndTime'});"><i
											class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
						</fieldset>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-12">
									<button type="button" class="btn btn-success" onclick="updateStockRecordLines();">
										<i class="fa fa-save"></i> &nbsp; 保存
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
		return data.price == '' ? '' : '￥' + data.price;
	}
	} ];

	var chooseEcProductTable = initDataTable("chooseEcProduct", "${nvix}/nvixnt/vixntInitInventoryAction!goChooseListContent.action", chooseEcProductColumns, function(page, pageSize, orderField, orderBy) {
		var productCategoryId = $("#chooseProductCategoryId").val();
		var searchItemName = $("#searchProductName").val();
		searchItemName = Url.encode(searchItemName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"selectName" : searchItemName,
		"categoryId" : productCategoryId
		};
	}, 10, '1', '0');

	$('#chooseEcProduct tbody').on('click', 'tr', function() {
		var data = chooseEcProductTable.row(".selected").data();
		if (data != undefined) {
			$("#itemcode").val(data.code);
			$("#itemname").val(data.name);
			$("#skuCode").val(data.skuCode);
			$("#unitcost").val(data.price);
			$("#measureUnitId").val(data.measureUnitId);
			$("#measureUnitName").val(data.measureUnitName);
			$("#quantity").val("1");
			$("#batchcode").val(data.barCode);
			$("#massunitEndTime").val("");
			$("#invShelfId").val("");
			$("#invShelfName").val("");
			$("#supplierId").val(data.supplierId);
			$("#supplierName").val(data.supplierName);
		} else {
			clearStockInDetailForm();
		}
	});

	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/vixntInitInventoryAction!findTreeToJson.action", "chooseProductCategoryId", "chooseProductCategoryName", "chooseProductCategoryZtree", "menuContent", null, function() {
		chooseEcProductTable.ajax.reload();

	});

	/* 选择库位*/
	function chooseLocation() {
		var wareHouseId = $("#warehouseId").val();
		if (wareHouseId == '') {
			layer.alert("请选择仓库!", function(index) {
				layer.close(index);
			});
		} else {
			chooseListData('${nvix}/nvixnt/vixntTakeStockAction!goChooseShelf.action?wareHouseId=' + wareHouseId, 'single', '选择货位', 'invShelf');
		}
	};
	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};
	/* 保存入库明细*/
	function updateStockRecordLines() {
		if ($('#stockRecordLinesForm').validationEngine('validate')) {
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			//入库单主体ID
			$("#stockRecordLinesForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntInitInventoryAction!updateStockRecordLines.action",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				stockRecordLinesTable.ajax.reload();
				showMessage(result, 'success');
				clearStockInDetailForm();
				$.ajax({
				url : '${nvix}/nvixnt/vixntInitInventoryAction!getStockRecordTotal.action?id=' + $("#stockRecordsId").val(),
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
		$("#itemcode").val("");
		$("#itemname").val("");
		$("#skuCode").val("");
		$("#unitcost").val("");
		$("#measureUnitId").val("");
		$("#measureUnitName").val("");
		$("#quantity").val("");
		$("#batchcode").val("");
		$("#massunitEndTime").val("");
		$("#invShelfId").val("");
		$("#invShelfName").val("");
	};
</script>