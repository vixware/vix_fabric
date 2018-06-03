<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="warehouseform">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">订单编码:&nbsp;</td>
					<td><input type="text" id="telephone" name="telephone" value="${invWarehouse.telephone}" /></td>
					<td align="right">客户姓名:&nbsp;</td>
					<td><input type="text" id="postion" name="postion" value="${invWarehouse.postion}" /></td>
				</tr>
				<tr height="40">
					<td align="right">联系方式:&nbsp;</td>
					<td><input type="text" id="telephone" name="telephone" value="${invWarehouse.telephone}" /></td>
					<td align="right">物流方式:&nbsp;</td>
					<td><input type="text" id="postion" name="postion" value="${invWarehouse.postion}" /></td>
				</tr>
				<tr height="40">
					<td align="right">收货地址:&nbsp;</td>
					<td colspan="3"><input type="text" id="telephone" name="telephone" value="${invWarehouse.telephone}" size="45" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>