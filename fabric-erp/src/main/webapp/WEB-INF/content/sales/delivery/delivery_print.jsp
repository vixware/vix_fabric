<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div id="div_print">
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7">
			<tbody>
				<tr>
					<td align="center" style="font-size: 15px">销售发货单</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7" class="pickinglisttable">
			<tr>
				<td align="right" height="25">发货单编码：</td>
				<td height="25">${deliveryDocument.ddCode }</td>
				<td align="right" height="25">客户名称：</td> <
				<td height="25">${deliveryDocument.customerAccount.name}<input type="hidden" id="customerAccountId" name="customerAccountId" value="${deliveryDocument.customerAccount.id}" />
				</td>
			</tr>
			<tr>
				<td align="right" height="25">发运地：</td>
				<td height="25">${deliveryDocument.source }</td>
				<td align="right" height="25">目的地：</td>
				<td height="25">${deliveryDocument.target }</td>
			</tr>
			<tr>
				<td align="right" height="25">单据类型：</td>
				<td height="25">${deliveryDocument.salesBillType.name}</td>
				<td align="right" height="25">业务类型：</td>
				<td height="25">${deliveryDocument.salesBusinessType.name}</td>
			</tr>
			<tr>
				<td align="right" height="25">金额：</td>
				<td height="25">${deliveryDocument.amount }</td>
				<td align="right" height="25">业务员：</td>
				<td height="25">${deliveryDocument.salePerson.name}<input type="hidden" id="salePersonId" value="${deliveryDocument.salePerson.id}" />
				</td>
			</tr>
			<tr>
				<td align="right" height="25">交货日期：</td>
				<td height="25"><s:property value='formatDateToString(deliveryDocument.deliveryTime )' /></td>
				<td align="right" height="25">发货日期：</td>
				<td height="25"><s:property value='formatDateToString(deliveryDocument.shippedDate )' /></td>
				</td>
			</tr>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" border="1" class="pickinglisttable">
			<tbody>
				<tr>
					<th height="25" align="center">编号</th>
					<th height="25" align="center">${vv:varView('vix_mdm_item')}编码</th>
					<th height="25" align="center">${vv:varView('vix_mdm_item')}名称</th>
					<th height="25" align="center">规格型号</th>
					<th height="25" align="center">${vv:varView('vix_mdm_item')}类型</th>
					<th height="25" align="center">订货数量</th>
					<th height="25" align="center">金额</th>
					<th height="25" align="center">单价</th>
					<th height="25" align="center">收货地址</th>
				</tr>
				<s:iterator value="deliveryDocument.deliveryDocumentItems" var="deliveryDocumentItems" status="s">
					<tr bgcolor="#FFFFFF">
						<td height="25" align="center">${deliveryDocumentItems.id}</td>
						<td height="25" align="center">${deliveryDocumentItems.itemCode}</td>
						<td height="25" align="center">${deliveryDocumentItems.itemName}</td>
						<td height="25" align="center">${deliveryDocumentItems.specification}</td>
						<td height="25" align="center">${deliveryDocumentItems.itemType}</td>
						<td height="25" align="center">${deliveryDocumentItems.count}</td>
						<td height="25" align="center">${deliveryDocumentItems.netTotal}</td>
						<td height="25" align="center">${deliveryDocumentItems.netPrice}</td>
						<td height="25" align="center">${deliveryDocumentItems.recieveAddress}</td>
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