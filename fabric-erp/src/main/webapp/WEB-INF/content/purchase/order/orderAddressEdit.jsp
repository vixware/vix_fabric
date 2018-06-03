<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var isSaving = false;
	function submitAddressForm() {
		if (isSaving)
			return;
		isSaving = true;

		//if($('#addressForm #addressStreet').val()==''){
		//	showMessage('请输入地址信息');
		//	return false;
		//}

		$("#addressForm").ajaxSubmit({
		type : "post",
		url : "${vix}/purchase/purchaseOrderAction!orderAddressSave.action",
		dataType : "text",
		success : function(result) {
			if (result != null) {
				showMessage('信息保存完毕');
				reloadDataGrid2();

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
	<form id="addressForm" method="post">
		<s:hidden id="adderssId" name="address.id" value="%{address.id}" theme="simple" />
		<input type="hidden" name="purchaseId" value="${purchaseId}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">地址代码:&nbsp;</td>
					<td><input id="addressCode" name="address.code" value="${address.code}" type="text" class=" text-input" /></td>
					<td align="right">国家:&nbsp;</td>
					<td><input id="addressCountry" name="address.country" value="${address.country}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">省:&nbsp;</td>
					<td><input id="addressProvince" name="address.provience" value="${address.provience}" type="text" class=" text-input" /> <span style="color: red;">*</span></td>
					<td align="right">市:&nbsp;</td>
					<td><input id="addressCity" name="address.city" value="${address.city}" type="text" class=" text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">街道:&nbsp;</td>
					<td><input id="addressStreet" name="address.street" value="${address.street}" type="text" class=" text-input" /> <span style="color: red;">*</span></td>
					<td align="right">邮政编码:&nbsp;</td>
					<td><input id="addressPostCode" name="address.postCode" value="${address.postCode}" type="text" class=" text-input" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="c_foot">
	<span class="left_bg"></span><span class="right_bg"></span>
</div>