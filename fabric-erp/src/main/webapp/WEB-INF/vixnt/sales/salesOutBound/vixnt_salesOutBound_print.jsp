<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div id="div_print">
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7">
			<tbody>
				<tr>
					<td align="center" style="font-size: 15px">销售出库单</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7" class="pickinglisttable">
			<tr>
				<td align="right" height="25">单据编码:</td>
				<td height="25">${stockRecords.code}</td>
				<td align="right" height="25">单据主题:</td>
				<td height="25">${stockRecords.name}</td>
			</tr>
			<tr>
				<td align="right" height="25">出货仓库:</td>
				<td height="25">${stockRecords.invWarehouse.name}</td>
				<td align="right" height="25">出库日期:</td>
				<td height="25">${stockRecords.createTimeStr}</td>
			</tr>
				<td align="right" height="25">创建人:</td>
				<td height="25">${stockRecords.checkperson}</td>
				<td align="right" height="25"></td>
				<td height="25"></td>
			<tr>
				<td align="right" height="25">备注:</td>
				<td colspan="3">${stockRecords.memo}</td>
			</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" border="1" class="pickinglisttable">
			<tbody>
				<tr>
					<th height="25" align="center">编号</th>
					<th height="25" align="center">编码</th>
					<th height="25" align="center">名称</th>
					<th height="25" align="center">规格型号</th>
					<th height="25" align="center">SKU编码</th>
					<th height="25" align="center">条形码</th>
					<th height="25" align="center">批次号</th>
					<th height="25" align="center">单价</th>
					<th height="25" align="center">数量</th>
					<th height="25" align="center">货位</th>
				</tr>
				<s:iterator value="stockRecords.subStockRecordLines" var="stockRecordLines" status="s">
					<tr bgcolor="#FFFFFF">
						<td height="25" align="center">${stockRecordLines.id}</td>
						<td height="25" align="center">${stockRecordLines.itemcode}</td>
						<td height="25" align="center">${stockRecordLines.itemname}</td>
						<td height="25" align="center">${stockRecordLines.specification}</td>
						<td height="25" align="center">${stockRecordLines.skuCode}</td>
						<td height="25" align="center">${stockRecordLines.barCode}</td>
						<td height="25" align="center">${stockRecordLines.batchcode}</td>
						<td height="25" align="center">${stockRecordLines.unitcost}</td>
						<td height="25" align="center">${stockRecordLines.unit}</td>
						<td height="25" align="center">${stockRecordLines.quantity}</td>
						<td height="25" align="center">${stockRecordLines.invShelfName}</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</div>
<style>
.pickinglisttable tr {
	font-size: 10px;
}
</style>