<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="customerForm" class="form-horizontal" style="padding: 35px;">
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">金额:</label>
			<div class="col-md-3">
				<input id="amount" name="amount" value="${requireGoodsOrder.amount - payAmount}" class="form-control validate[required]" type="text" readonly="readonly" />
			</div>
			<label class="col-md-2 control-label">实付金额:</label>
			<div class="col-md-3">
				<input id="payAmount" name="payAmount" value="<fmt:formatNumber type='number' value='${requireGoodsOrder.amount*discount - payAmount - reduceAmount}' pattern='0.00' maxFractionDigits='2' />" class="form-control validate[required]" type="text" readonly="readonly" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">满减金额:</label>
			<div class="col-md-3">
				<input id="reduceAmount" name="reduceAmount" value="${reduceAmount}" class="form-control validate[required]" type="text" readonly="readonly" />
			</div>
			<label class="col-md-2 control-label">优惠金额:</label>
			<div class="col-md-3">
				<input id="discount" name="discount" value="0.0" class="form-control validate[required]" type="text" readonly="readonly" />
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
						<input type="radio" value="2" id="payType" name="payType">余额
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
		<c:if test="${couponList != null && fn:length(couponList) > 0}">
			<div class="form-group">
				<label class="col-md-2 control-label">优惠券:</label>
				<div class="col-md-8">
					<div data-toggle="buttons" class="btn-group">
						<c:forEach items="${couponList}" var="entity" varStatus="s">
							<label class="btn btn-default" onclick="selectCoupon('${entity.amount}');"> 
								<input type="radio" value="${entity.id}" id="couponId" name="couponId">${entity.amount}元
							</label> 
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
	</fieldset>
</form>
<script type="text/javascript">
	function selectCoupon(amount){
		var payAmount = Number($("#payAmount").val());
		amount = Number(amount);
		$("#discount").val(amount);
		$("#payAmount").val(payAmount - amount);
	}
</script>