<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="paymentTypeForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/nvixPaymentTypeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="paymentType.id" value="${paymentType.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-8">
				<input id="paymentCode" name="paymentType.paymentCode" value="${paymentType.paymentCode}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-8">
				<input id="payment" name="paymentType.payment" value="${paymentType.payment}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<input id="memo" name="paymentType.memo" value="${paymentType.memo}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>