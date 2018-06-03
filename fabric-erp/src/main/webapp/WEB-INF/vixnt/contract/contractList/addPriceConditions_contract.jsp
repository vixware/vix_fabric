<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="priceConditionsForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdatePriceConditions.action">
	<input type="hidden" id="contractId" name="priceConditions.contract.id" value="${priceConditions.contract.id}" />
	<input type="hidden" id="id" name="priceConditions.id" value="${priceConditions.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"> <span style="color: red;">*</span> 开始数量:</label>
			<div class="col-md-3">
				<input id="startQuantity" name="priceConditions.startQuantity" value="${priceConditions.startQuantity}" type="text" class="form-control validate[required]" />
			</div>
			<label class="col-md-2 control-label">结束数量:</label>
			<div class="col-md-3">
				<input id="cutoffQuantity" name="priceConditions.cutoffQuantity" value="${priceConditions.cutoffQuantity}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">折扣:</label>
			<div class="col-md-3">
				<input id="discount" name="priceConditions.discount" value="${priceConditions.discount}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">数量(从):</label>
			<div class="col-md-3">
				<input id="numberFrom" name="priceConditions.numberFrom" value="${priceConditions.numberFrom}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">数量(到):</label>
			<div class="col-md-3">
				<input id="numberto" name="priceConditions.numberto" value="${priceConditions.numberto}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">采购价格条件:</label>
			<div class="col-md-3">
				<input id="purchasepriceCondition" name="priceConditions.purchasepriceCondition" value="${priceConditions.purchasepriceCondition}" type="text" class="form-control" />
			</div>
		</div>
	</fieldset>			
</form>
