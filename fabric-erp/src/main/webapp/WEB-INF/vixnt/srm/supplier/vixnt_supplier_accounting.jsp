<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="supplierAccountingInfoForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntSupplierAction!saveOrUpdateAccounting.action">
	<input type="hidden" id="supplierId" name="supplierAccountingInfo.supplier.id" value="${supplierAccountingInfo.supplier.id}" />
	<input type="hidden" id="id" name="supplierAccountingInfo.id" value="${supplierAccountingInfo.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"> <span style="color: red;">*</span> 总帐分类:</label>
			<div class="col-md-3">
				<input id="generalAccountCatalog" name="supplierAccountingInfo.generalAccountCatalog" value="${supplierAccountingInfo.generalAccountCatalog}" type="text" class="form-control validate[required]" />
			</div>
			<label class="col-md-2 control-label">付款条件:</label>
			<div class="col-md-3">
				<input id="payCondition" name="supplierAccountingInfo.payCondition" value="${supplierAccountingInfo.payCondition}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">付款类型:</label>
			<div class="col-md-3">
				<input id="payType" name="supplierAccountingInfo.payType" value="${supplierAccountingInfo.payType}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">付款方式:</label>
			<div class="col-md-3">
				<input id="payStyle" name="supplierAccountingInfo.payStyle" value="${supplierAccountingInfo.payStyle}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">付款对象:</label>
			<div class="col-md-3">
				<input id="payTarget" name="supplierAccountingInfo.payTarget" value="${supplierAccountingInfo.payTarget}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">付款期限:</label>
			<div class="col-md-3">
				<input id="limited" name="supplierAccountingInfo.limited" value="${supplierAccountingInfo.limited}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">发票抬头:</label>
			<div class="col-md-3">
				<input id="invoiceHeader" name="supplierAccountingInfo.invoiceHeader" value="${supplierAccountingInfo.invoiceHeader}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">增值税开户银行:</label>
			<div class="col-md-3">
				<input id="vaBank" name="supplierAccountingInfo.vaBank" value="${supplierAccountingInfo.vaBank}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">审核人:</label>
			<div class="col-md-3">
				<input id="checked" name="supplierAccountingInfo.checked" value="${supplierAccountingInfo.checked}" type="text" class="form-control" />
			</div>
		</div>
	</fieldset>			
</form>
