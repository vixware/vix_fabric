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
		<s:hidden id="messageLogId" name="messageLogId" value="%{messageLog.id}" theme="simple" />
		<div class="message_box">
			<span class="pull-left">内容：</span>
			<textarea class="message_text" id="messageContent" name="messageContent">${ messageLog.messageContent}</textarea>
			<p></p>
			<span class="pull-right" style="padding-top: 40px;">（多号码以逗号","分隔）</span><span class="pull-left">手机号码：</span>
			<div class="message_test">
				<textarea id="mobilePhone" name="mobilePhone">${ messageLog.mobilePhone}</textarea>
			</div>
		</div>
	</form>
</div>