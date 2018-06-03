<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
</script>
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
	width: 100%;
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

.pull-left {
	float: left;
}

.pull-right {
	float: right;
}
</style>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="box">
		<form id="messageTemplateForm">
			<s:hidden id="messageTemplateId" name="messageTemplateId" value="%{messageTemplate.id}" theme="simple" />
			<div class="message_box">
				<div class="clearfix message_title">
					<span class="pull-left">短信主题：</span> <label><input id="messageTemplateName" name="messageTemplateName" value="${messageTemplate.name }" type="text" size="50" class="ipt" onchange="fieldChanged(this);"> </label>
				</div>
				<div class="clearfix message_title">
					<span class="pull-left">模板类型：</span> <label><s:select id="messageTemplateTypeId" headerKey="" headerValue="--请选择--" list="%{messageTemplateTypeList}" listValue="name" listKey="id" value="%{messageTemplate.messageTemplateType.id}" multiple="false" theme="simple" onchange="fieldChanged(this);">
						</s:select> </label>
				</div>
				<div class="clearfix message_title">
					<span class="pull-left">插入变量：</span> <input type="button" value="订单编码" onclick="check('messageContent','{$isOrderCode$}')" class="btn" onchange="fieldChanged(this);" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="买家昵称" onclick="check('messageContent','{$isBuyerNick$}')" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"
						value="商品名称" onclick="check('messageContent','{$isGoodsName$}')" class="btn" />
				</div>
				<div class="clearfix message_title">
					<span class="pull-left">短信内容：</span>
				</div>
				<textarea id="messageContent" name="messageContent" class="message_text" onchange="fieldChanged(this);">${messageTemplate.messageContent}</textarea>
				<p></p>
				<div class="pull-right">
					<label><input type="checkbox" name="isTd" /> 短信末尾添加“退订回复TD”</label>&nbsp;&nbsp;&nbsp;&nbsp;<label><input name="isPb" type="checkbox" /> 屏蔽黑名单</label>
				</div>
			</div>
		</form>
		<!-- right -->
	</div>
</div>
<script type="text/javascript">
	(function($) {
		$.fn.extend({
			insertAtCaret : function(myValue) {
				var $t = $(this)[0];
				if (document.selection) {
					this.focus();
					sel = document.selection.createRange();
					sel.text = myValue;
					this.focus();
				} else if ($t.selectionStart || $t.selectionStart == '0') {
					var startPos = $t.selectionStart;
					var endPos = $t.selectionEnd;
					var scrollTop = $t.scrollTop;
					$t.value = $t.value.substring(0, startPos) + myValue + $t.value.substring(endPos, $t.value.length);
					this.focus();
					$t.selectionStart = startPos + myValue.length;
					$t.selectionEnd = startPos + myValue.length;
					$t.scrollTop = scrollTop;
				} else {
					this.value += myValue;
					this.focus();
				}
			}
		})
	})(jQuery);
	function check(a, b) {
		$("#" + a).insertAtCaret(b);
	}
</script>
