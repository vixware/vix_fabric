<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="customerForm" class="form-horizontal" style="padding: 35px;">
<input id="clipId" name="rechargeRecord.customerAccountClip.id" value="${rechargeRecord.customerAccountClip.id}" class="form-control validate[required]" type="hidden" readonly="readonly" />
<input id="rechargeRecordId" name="rechargeRecord.id" value="${rechargeRecord.id}" class="form-control validate[required]" type="hidden" readonly="readonly" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">支付金额:</label>
			<div class="col-md-3">
				<input id="payMoney" name="rechargeRecord.customerAccountClip.payMoney" value="${rechargeRecord.customerAccountClip.payMoney}" class="form-control validate[required]" type="text" readonly="readonly" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">支付类型:</label>
			<div class="col-md-8">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default active"> 
						<input type="radio" value="1" id="payType" name="payType" checked="checked" >现金
					</label> 
					<label class="btn btn-default"> 
						<input type="radio" value="3" id="payType" name="payType">银行卡
					</label>
					<label class="btn btn-default"> 
						<input type="radio" value="4" id="payType" name="payType">微信
					</label>
					<label class="btn btn-default"> 
						<input type="radio" value="5" id="payType" name="payType">支付宝
					</label>
				</div>
			</div>
		</div>
	</fieldset>
</form>