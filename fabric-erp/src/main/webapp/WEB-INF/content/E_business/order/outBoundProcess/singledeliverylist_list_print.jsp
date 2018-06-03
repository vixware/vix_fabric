<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="content">
	<div style="page-break-after: always; marks: none">
		<table width="100%" cellspacing="0" cellpadding="4" border="0">
			<tbody>
				<tr>
					<td align="center" style="font-size: 12px">发货单</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0">
			<tbody>
				<tr>
					<th width="18%" nowrap="" align="right" style="font-size: 10px">收货人:</th>
					<td width="27%" nowrap="" style="font-size: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceList.order.receiverName}</td>
					<th width="18%" valign="top" nowrap="" align="right" style="font-size: 10px">订单号:</th>
					<td width="27%" nowrap="" style="font-size: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceList.order.outerId}</td>
				</tr>
				<tr>
					<th width="16%" nowrap="" align="right" style="font-size: 10px">电话:</th>
					<td width="39%" nowrap="" style="font-size: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceList.order.receiverMobile}</td>
					<th width="18%" nowrap="" align="right" style="font-size: 10px">打印时间:</th>
					<td width="27%" nowrap="" style="font-size: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${invoiceList.invoiceTime}" pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<th width="18%" nowrap="" align="right" style="font-size: 10px">配送地址:</th>
					<td width="27%" nowrap="" style="font-size: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceList.order.receiverAddress}</td>
					<th width="18%" valign="top" nowrap="" align="right" style="font-size: 10px">备注:</th>
					<td width="27%" nowrap="" style="font-size: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceList.order.buyerMemo}</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" border="1" id="pickinglisttable">
			<tbody>
				<tr>
					<th width="20%" height="26" align="center" style="font-size: 10px">货号</th>
					<th width="60%" height="26" align="center" style="font-size: 10px">商品名称</th>
					<th width="10%" height="26" align="center" style="font-size: 10px">价格</th>
					<th width="10%" height="26" align="center" style="font-size: 10px">数量</th>
				</tr>
				<s:iterator value="#invoiceList.subInvoiceListDetails" var="invoiceListDetail" status="s">
					<tr>
						<td height="20" align="center" style="font-size: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceListDetail.orderDetail.bn}</td>
						<td height="20" align="center" style="font-size: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceListDetail.goodsName}</td>
						<td height="20" align="center" style="font-size: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceListDetail.price}</td>
						<td height="20" align="center" style="font-size: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceListDetail.orderDetail.num}</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0">
			<tr>
				<td width="25%" style="font-size: 10px">总计:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceList.total}</td>
				<td width="25%" style="font-size: 10px">运费:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceList.postageFee}</td>
				<td width="25%" style="font-size: 10px">已收款:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceList.payment}</td>
				<td width="25%" style="font-size: 10px">应收:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceList.payment}</td>
			</tr>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0">
			<tr>
				<td style="font-size: 12px" align="left">备注:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td style="font-size: 12px" align="right">贺语:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
		</table>
	</div>
</div>
<style>
#pickinglisttable {
	font-size: 10px;
}
</style>
