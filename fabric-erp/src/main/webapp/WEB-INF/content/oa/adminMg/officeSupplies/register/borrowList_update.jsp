<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#officeSuppliesBorrowForm").validationEngine();

$(function() {
    if($('input:radio[name=disabled]:checked').length==0){
    	$('input:radio[name=disabled]:first').trigger('click');
    }
});
</script>
<form id="officeSuppliesBorrowForm">
	<div style="padding: 5px;">
		<input type="hidden" id="officeSuppliesRegisterId" name="officeSuppliesRegisterId" value="${officeSuppliesBorrow.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">办公用品编码:&nbsp;</td>
					<td><input disabled="disabled" id="model" name="officeSuppliesBorrow.model" value="${officeSuppliesBorrow.model}" class="validate[required] text-input" /></span></td>
					<td align="right">办公用品名称:&nbsp;</td>
					<td><input disabled="disabled" id="officeName" name="officeSuppliesBorrow.officeName" value="${officeSuppliesBorrow.officeName}" class="validate[required] text-input" /></span></td>
				</tr>
				<tr height="30">
					<td align="right">工号:&nbsp;</td>
					<td><input disabled="disabled" id="encoding" name="officeSuppliesBorrow.officeSuppliesRegister.encoding" value="${officeSuppliesBorrow.officeSuppliesRegister.encoding}" class="validate[required] text-input" /></td>
					<td align="right">借用人:&nbsp;</td>
					<td><input disabled="disabled" id="recipientsWho" name="officeSuppliesBorrow.officeSuppliesRegister.recipientsWho" value="${officeSuppliesBorrow.officeSuppliesRegister.recipientsWho}" class="validate[required] text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">借用量:&nbsp;</td>
					<td><input disabled="disabled" id="numberOfRecipients" name="officeSuppliesBorrow.numberOfRecipients" value="${officeSuppliesBorrow.numberOfRecipients}" class="validate[required] text-input" /></span></td>
					<td align="right">借用时间:&nbsp;</td>
					<td><input disabled="disabled" id="startTime" name="officeSuppliesBorrow.startTime" value="${officeSuppliesBorrow.startTime}" class="validate[required] text-input" /></span></td>
				</tr>
				<tr height="30">
					<td align="right">未还量:&nbsp;</td>
					<td><input disabled="disabled" id="borrowNumber" name="officeSuppliesBorrow.borrowNumber" value="${officeSuppliesBorrow.borrowNumber}" class="validate[required] text-input" /></span></td>
					<td align="right">归还量:&nbsp;</td>
					<td><input id="returnNumber" name="officeSuppliesBorrow.returnNumber" value="${officeSuppliesBorrow.returnNumber}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</div>
</form>