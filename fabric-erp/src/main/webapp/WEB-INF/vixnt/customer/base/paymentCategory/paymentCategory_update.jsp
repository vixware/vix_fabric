<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="paymentCategoryForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/nvixPaymentCategoryAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="paymentCategory.id" value="${paymentCategory.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-8">
				<input id="paymentCode" name="paymentCategory.paymentCode" value="${paymentCategory.paymentCode}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-8">
				<input id="payment" name="paymentCategory.payment" value="${paymentCategory.payment}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<input id="memo" name="paymentCategory.memo" value="${paymentCategory.memo}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>