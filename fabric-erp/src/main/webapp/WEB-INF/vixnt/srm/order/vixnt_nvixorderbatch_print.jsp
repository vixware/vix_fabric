<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="text-align: left; padding: 25px 25px;">
	<div id="div_print">
		<div>
			<table width="100%">
				<tr>
					<td align="center" style="font-size: 15px">拣货单</td>
				</tr>
			</table>
			<table width="100%" cellspacing="0" cellpadding="4" border="0">
				<tr>
					<td width="50%" height="30" align="left" style="font-size: 13px">批次号: ${nvixOrderBatch.code}</td>
					<td width="50%" height="30" align="right" style="font-size: 13px">打印时间: ${nvixOrderBatch.createTimeTimeStr}</td>
				</tr>
			</table>
			<table width="100%" cellspacing="0" cellpadding="0" border="1">
				<tr>
					<th width="25%" height="30" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品编码</th>
					<th width="45%" height="30" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品名称</th>
					<th width="15%" height="30" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价格</th>
					<th width="15%" height="30" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</th>
				</tr>
				<s:iterator value="nvixPickingListDetailList" var="orderDetail" status="s">
					<tr style="border-width: 1px; border-style: solid;">
						<td height="26" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${orderDetail.itemCode}</td>
						<td height="26" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${orderDetail.itemName}</td>
						<td height="26" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${orderDetail.price}</td>
						<td height="26" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${orderDetail.amount}</td>
					</tr>
				</s:iterator>
			</table>
			<table width="100%" cellspacing="0" cellpadding="4" border="0">
				<tr>
					<td width="30%" height="30"></td>
					<td width="20%" height="30"></td>
					<td width="30%" height="30" align="right" style="font-size: 13px">处理人:</td>
					<td width="20%" height="30" style="font-size: 13px">${nvixOrderBatch.creator}</td>
				</tr>
			</table>
		</div>
	</div>
</div>