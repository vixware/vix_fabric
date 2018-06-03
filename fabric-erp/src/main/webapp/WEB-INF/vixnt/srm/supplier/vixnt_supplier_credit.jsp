<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="supplierCreditForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntSupplierAction!saveOrUpdateCredit.action">
	<input type="hidden" id="supplierId" name="supplierCreditInfo.supplier.id" value="${supplierCreditInfo.supplier.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"> <span style="color: red;">*</span> 信用等级:</label>
			<div class="col-md-3">
				<input id="creditLevel" name="supplierCreditInfo.creditLevel" value="${supplierCreditInfo.creditLevel}" type="text" class="form-control validate[required]" />
			</div>
			<label class="col-md-2 control-label">信用额度:</label>
			<div class="col-md-3">
				<input id="creditAmount" name="supplierCreditInfo.creditAmount" value="${supplierCreditInfo.creditAmount}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">折扣率:</label>
			<div class="col-md-3">
				<input id="discount" name="supplierCreditInfo.discount" value="${supplierCreditInfo.discount}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">付款条件:</label>
			<div class="col-md-3">
				<input id="payCondition" name="supplierCreditInfo.payCondition" value="${supplierCreditInfo.payCondition}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">最后交易金额:</label>
			<div class="col-md-3">
				<input id="lastDealAmount" name="supplierCreditInfo.lastDealAmount" value="${supplierCreditInfo.lastDealAmount}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">最后交易日期:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastDealTime" name="supplierCreditInfo.lastDealTime" value="${supplierCreditInfo.lastDealTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'lastDealTime'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
	</fieldset>			
</form>
