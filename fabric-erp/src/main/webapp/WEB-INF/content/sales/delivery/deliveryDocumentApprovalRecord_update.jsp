<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="background: #DCE7F1">
	<form id="deliveryDocumentApprovalRecordForm">
		<div class="box order_table" style="line-height: normal; margin: 10px;">
			<table class="table-padding020">
				<tr height="30">
					<td>审批意见:&nbsp;</td>
				</tr>
				<tr>
					<td><textarea id="ddContent" name="ddContent" rows="4" cols="50">${deliveryDocumentApprovalRecord.content}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>