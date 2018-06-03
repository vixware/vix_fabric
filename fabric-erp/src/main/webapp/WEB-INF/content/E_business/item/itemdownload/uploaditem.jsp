<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#warehouseform").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="warehouseform">
		<s:hidden id="invWarehouseid" name="invWarehouseid" value="%{invWarehouse.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">网店:&nbsp;</td>
					<td><input type="text" id="code" name="code" class="validate[required] text-input" value="${invWarehouse.code}" /><span style="color: red;">*</span></td>
					<td align="right">商品类目:&nbsp;</td>
					<td><input type="text" id="code" name="code" class="validate[required] text-input" value="${invWarehouse.code}" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>