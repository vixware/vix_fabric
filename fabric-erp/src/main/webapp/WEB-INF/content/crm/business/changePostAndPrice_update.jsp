<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#salesOrderForm").validationEngine();
</script>
<input type="hidden" id="salesOrderId" name="salesOrderId" value="${salesOrder.id}" />
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="salesOrderForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">邮费：</td>
					<td><input type="text" id="postFee" name="postFee" value="${salesOrder.postFee}" class="validate[required] text-input" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>