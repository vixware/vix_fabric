<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="rechargeRecordForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntDistributionSystemAction!saveOrUpdateRechargeRecord.action">
	<input type="hidden" id="id" name="rechargeRecord.id" value="${rechargeRecord.id}" /> <input type="hidden" id="channelDistributorId" name="rechargeRecord.channelDistributor.id" value="${rechargeRecord.channelDistributor.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 储值金额:</label>
			<div class="col-md-3">
				<input id="payMoney" name="rechargeRecord.payMoney" value="${rechargeRecord.payMoney}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 储值方式:</label>
			<div class="col-md-3">
				<select id="type" name="rechargeRecord.payType" class="form-control">
					<option value="1" <c:if test='${rechargeRecord.payType eq 1}'>selected="selected"</c:if>>现金</option>
					<option value="2" <c:if test='${rechargeRecord.payType eq 2}'>selected="selected"</c:if>>余额</option>
					<option value="3" <c:if test='${rechargeRecord.payType eq 3}'>selected="selected"</c:if>>银行卡</option>
					<option value="4" <c:if test='${rechargeRecord.payType eq 4}'>selected="selected"</c:if>>微信</option>
					<option value="5" <c:if test='${rechargeRecord.payType eq 5}'>selected="selected"</c:if>>支付宝</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="rechargeRecord.memo" class="form-control">${rechargeRecord.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#rechargeRecordForm").validationEngine();
</script>