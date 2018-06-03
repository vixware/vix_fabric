<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="content" style="text-align: left; padding: 25px 25px;">
	<s:iterator value="purchaseOrderList" var="entity" status="s" id="invoiceId">
		<div style="page-break-after: always; marks: none">
			<table width="100%" cellspacing="0" cellpadding="4" border="0">
				<tr>
					<td align="center" style="font-size: 15px">${invoiceId.name}</td>
				</tr>
			</table>
			<table width="100%" cellspacing="0" cellpadding="4" border="0">
				<tr>
					<th width="15%" align="right" style="font-size: 13px">收货门店:</th>
					<td width="35%" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceId.channelDistributor.name}</td>
					<th width="15%" valign="top" align="right" style="font-size: 13px">订单编码:</th>
					<td width="35%" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceId.code}</td>
				</tr>
				<tr>
					<th width="15%" align="right" style="font-size: 13px">联系电话:</th>
					<td width="35%" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceId.channelDistributor.telephone}</td>
					<th width="15%" align="right" style="font-size: 13px">打印时间:</th>
					<td width="35%" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceId.createTimeTimeStr}</td>
				</tr>
				<tr>
					<th width="15%" align="right" style="font-size: 13px">配送地址:</th>
					<td width="35%" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceId.channelDistributor.address}</td>
					<th width="15%" valign="top" align="right" style="font-size: 13px">备注:</th>
					<td width="35%" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceId.memo}</td>
				</tr>
			</table>
			<table width="100%" cellspacing="0" cellpadding="0" border="1">
				<tr>
					<th width="20%" height="30" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品编码</th>
					<th width="60%" height="30" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品名称</th>
					<th width="10%" height="30" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价格</th>
					<th width="10%" height="30" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</th>
				</tr>
				<s:iterator value="#invoiceId.purchaseOrderLineItems" var="invoiceListDetail" status="s">
					<tr>
						<td height="25" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceListDetail.itemCode}</td>
						<td height="25" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceListDetail.itemName}</td>
						<td height="25" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceListDetail.price}</td>
						<td height="25" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceListDetail.amount}</td>
					</tr>
				</s:iterator>
			</table>
			<table width="100%" cellspacing="0" cellpadding="4" border="0">
				<tr>
					<th width="15%" valign="top" align="right" style="font-size: 13px"></th>
					<td width="35%" style="font-size: 13px"></td>
					<th width="15%" align="right" style="font-size: 13px">总计金额:</th>
					<td width="35%" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${invoiceId.totalAmount}</td>
				</tr>
			</table>
		</div>
	</s:iterator>
</div>