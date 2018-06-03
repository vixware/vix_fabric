<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		_pad_addInputCheckNumEvent('#invoiceForm #totalAmount');
		_pad_addInputCheckNumEvent('#invoiceForm #taxRate');
	});

	var isSaving = false;
	function submitInvoiceForm() {
		if (isSaving)
			return;
		isSaving = true;

		if ($('#invoiceForm #invoiceName').val() == '') {
			showMessage('请输入发票名称');
			isSaving = false;
			return false;
		}

		$("#invoiceForm").ajaxSubmit({
		type : "post",
		url : "${vix}/purchase/purchaseOrderAction!orderInvoiceSave.action",
		dataType : "text",
		success : function(result) {
			if (result != null) {
				showMessage('信息保存完毕');
				reloadDataGrid4();

				isSaving = false;
				$.close('popModalWin');
			} else {
				showErrorMessage('信息保存失败');
				isSaving = false;
			}
		}
		});
		return false;
	}
</script>

<div class="c_head">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>
<div class="box">
	<form id="invoiceForm" method="post">
		<s:hidden id="adderssId" name="invoice.id" value="%{invoice.id}" theme="simple" />
		<input type="hidden" name="purchaseId" value="${purchaseId}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">名称:&nbsp;</td>
					<td><input id="invoiceName" name="invoice.name" value="${invoice.name}" type="text" class=" text-input" /> <span style="color: red;">*</span></td>
					<td align="right">发票类型:&nbsp;</td>
					<td><input id="orderType" name="invoice.orderType" value="${invoice.orderType}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">币种:&nbsp;</td>
					<td><input id="currency" name="invoice.currency" value="${invoice.currency}" type="text" class=" text-input" /></td>
					<td align="right">金额(未税):&nbsp;</td>
					<td><input id="totalAmount" name="invoice.totalAmount" value="${invoice.totalAmount}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">税率(%):&nbsp;</td>
					<td><input id="taxRate" name="invoice.taxRate" value="${invoice.taxRate}" type="text" class=" text-input" /></td>
					<td align="right">供应商:&nbsp;</td>
					<td><input id="supplierName" name="invoice.supplierName" value="${invoice.supplierName}" type="text" class=" text-input" /> <input type="hidden" name="invoice.supplierCode" value="${invoice.supplierCode}" /></td>
				</tr>
				<tr height="30">
					<td align="right">采购人:&nbsp;</td>
					<td><input id="purchasePerson" name="invoice.purchasePerson" value="${invoice.purchasePerson}" type="text" class=" text-input" /></td>
					<td align="right">交货时间:&nbsp;</td>
					<td><input id="invoiceDeliveryDate" name="invoice.deliveryDate" value="<s:date name="invoice.deliveryDate" format="yyyy-MM-dd"/>" readonly="readonly" type="text" onclick="showTime('invoiceDeliveryDate','yyyy-MM-dd')" /> <img onclick="showTime('invoiceDeliveryDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
						height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="c_foot">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>