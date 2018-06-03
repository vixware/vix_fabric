<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="stockRecordLinesForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/vixntInboundWarehouseAction!updateStockRecordLines.action">
	<fieldset>
		<input type="hidden" id="stockRecordLinesId" name="stockRecordLines.id" value="${stockRecordLines.id }" /> <input type="hidden" id="stockRecordLinesStockRecordsId" name="id" value="${stockRecordLines.stockRecords.id }" /> <input type="hidden" id="itemcode" name="stockRecordLines.itemcode" value="${stockRecordLines.itemcode }" />
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
			<label class="col-md-3 control-label">供应商:</label>
			<div class="col-md-9">
				<input id="supplierId" name="stockRecordLines.supplier.id" value="${stockRecordLines.supplier.id}" type="hidden" /> <input id="supplierName" name="stockRecordLines.supplier.name" value="${stockRecordLines.supplier.name}" onfocus="goChooseSupplier();" data-prompt-position="topLeft" class="form-control" type="text" />
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
</form>
<script type="text/javascript">
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
</script>