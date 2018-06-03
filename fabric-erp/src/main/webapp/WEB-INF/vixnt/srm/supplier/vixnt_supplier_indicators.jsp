<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="supplierIndicatorsForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntSupplierAction!saveOrUpdateIndicators.action">
	<input type="hidden" id="supplierId" name="supplierIndicators.supplier.id" value="${supplierIndicators.supplier.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span style="color: red;">*</span> 指标名称:</label>
			<div class="col-md-8">
				<input id="itemName" name="supplierIndicators.itemName" value="${supplierIndicators.itemName}" type="text" class="form-control validate[required]" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">权重:</label>
			<div class="col-md-8">
				<input id="weights" name="supplierIndicators.weights" value="${supplierIndicators.weights}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">配额数量:</label>
			<div class="col-md-8">
				<input id="quota" name="supplierIndicators.quota" value="${supplierIndicators.quota}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">金额限度:</label>
			<div class="col-md-8">
				<input id="priceLimit" name="supplierIndicators.priceLimit" value="${supplierIndicators.priceLimit}" type="text" class="form-control" />
			</div>
		</div>
	</fieldset>			
</form>
