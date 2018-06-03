<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>


<style>
.message_box {
	padding: 15px;
}

.message_title {
	margin-bottom: 15px;
}

.message_title input {
	vertical-align: middle;
}

.message_text {
	width: 90%;
	display: block;
	margin: 0 auto;
	height: 100px;
}

.message_box p {
	margin: 15px 0;
	color: #666;
}

.message_test {
	overflow: hidden;
	overflow: hidden;
}

.message_test textarea {
	width: 98%;
	height: 50px;
}
</style>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="messageLogForm">
		<s:hidden id="shippingId" name="shippingId" value="%{shippingId}" theme="simple" />
		<div class="message_box">
			<span class="pull-left">延长订单：</span>
			<textarea class="message_text" id="tradeNo" name="tradeNo">${ shipping.tradeNo}</textarea>
			<p></p>
			<div class="message_test">
				延长本交易的"确认收货"期限为： <select id="prolongDays">
					<option value="3">3天</option>
					<option value="5">5天</option>
					<option value="7">7天</option>
					<option value="10">10天</option>
				</select>
			</div>
		</div>
	</form>
</div>