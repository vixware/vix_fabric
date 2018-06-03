<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$ ("#warehouseform").validationEngine ();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="warehouseform">
		<s:hidden id="invWarehouseid" name="invWarehouse.id" value="%{invWarehouse.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">商品编码:&nbsp;</td>
					<td><input type="text" id="code" name="code" class="validate[required] text-input" value="${invWarehouse.code}" /><span style="color: red;">*</span></td>
					<td align="right">商品名称:&nbsp;</td>
					<td><input type="text" id="name" name="name" class="validate[required] text-input" value="${invWarehouse.name}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">规格型号:&nbsp;</td>
					<td><input type="text" id="warehousePerson" name="warehousePerson" value="${invWarehouse.warehousePerson}" /></td>
					<td align="right">计量单位:&nbsp;</td>
					<td><input type="text" id="telephone" name="telephone" value="${invWarehouse.telephone}" /></td>
				</tr>
				<tr height="40">
					<td align="right">单价:&nbsp;</td>
					<td><input type="text" id="warehousePerson" name="warehousePerson" value="${invWarehouse.warehousePerson}" /></td>
					<td align="right">所需积分:&nbsp;</td>
					<td><input type="text" id="telephone" name="telephone" value="${invWarehouse.telephone}" /></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="postion" name="postion" size="45" class="validate[required] text-input" value="${invWarehouse.postion}" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>