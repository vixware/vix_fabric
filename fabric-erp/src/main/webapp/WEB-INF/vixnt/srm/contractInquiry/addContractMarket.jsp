<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="supplierBankInfoForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntSupplierAction!saveOrUpdateSupplierBankInfo.action">
	<input type="hidden" id="supplierBankInfoSupplierId" name="supplierBankInfo.supplier.id" value="${supplierBankInfo.supplier.id}" />
	<input type="hidden" id="id" name="supplierBankInfo.id" value="${supplierBankInfo.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span style="color: red;">*</span> 开户银行:</label>
			<div class="col-md-8">
				<input id="bankName" name="supplierBankInfo.bankName" value="${supplierBankInfo.bankName}" type="text" class="form-control validate[required]" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">开户银行代码:</label>
			<div class="col-md-8">
				<input id="bankCode" name="supplierBankInfo.bankCode" value="${supplierBankInfo.bankCode}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span style="color: red;">*</span> 银行账号:</label>
			<div class="col-md-8">
				<input id="bankAccount" name="supplierBankInfo.bankAccount" value="${supplierBankInfo.bankAccount}" type="text" class="form-control validate[required]" />
			</div>
		</div>
	</fieldset>			
</form>
