<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="content" style="text-align: left; padding: 25px 25px;">
	<c:if test="${requireGoodsOrder != null}">
		<div style="page-break-after: always; marks: none">
			<table width="100%" cellspacing="0" cellpadding="4" border="0">
				<tr>
					<td align="center" style="font-size: 15px">${requireGoodsOrder.channelDistributor.name}</td>
				</tr>
			</table>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<th width="30%" align="right" style="font-size: 13px">订单编码:</th>
					<td width="70%" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${requireGoodsOrder.code}</td>
				</tr>
				<tr>
					<th width="30%" align="right" style="font-size: 13px">下单时间:</th>
					<td width="70%" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${requireGoodsOrder.createTimeTimeStr}</td>
				</tr>
				<%-- <tr>
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
				</tr> --%>
			</table>
			<table width="100%" cellspacing="0" cellpadding="0" border="1">
				<tr>
					<th width="60%" height="30" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品名称</th>
					<th width="20%" height="30" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价格</th>
					<th width="20%" height="30" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</th>
				</tr>
				<c:forEach items="${requireGoodsOrder.subRequireGoodsOrderItems}" var="item">
					<tr>
						<td height="25" align="left" style="font-size: 13px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.item.name}
							<c:if test="${item.item.isServiceItem == 'Y'}">(服务项目)</c:if>
							<c:if test="${item.item.isServiceItem != 'Y' && item.price == 0}">(赠品)</c:if>
						</td>
						<td height="25" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.price}</td>
						<td height="25" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.num}</td>
					</tr>
				</c:forEach>
			</table>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<th width="15%" valign="top" align="right" style="font-size: 13px"></th>
					<th width="30%" align="right" style="font-size: 13px">总计金额:</th>
					<td width="35%" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;￥${requireGoodsOrder.amount}</td>
				</tr>
				<tr>
					<th width="15%" valign="top" align="right" style="font-size: 13px"></th>
					<th width="30%" align="right" style="font-size: 13px">应付金额:</th>
					<td width="35%" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;￥${requireGoodsOrder.payAmount}</td>
				</tr>
				<tr>
					<th width="15%" valign="top" align="right" style="font-size: 13px"></th>
					<th width="30%" align="right" style="font-size: 13px">满减金额:</th>
					<td width="35%" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;￥${requireGoodsOrder.reduceAmount}</td>
				</tr>
				<tr>
					<th width="15%" valign="top" align="right" style="font-size: 13px"></th>
					<th width="30%" align="right" style="font-size: 13px">优惠金额:</th>
					<td width="35%" style="font-size: 13px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<c:choose>
                  			<c:when test="${order.discountFee != null && order.discountFee != ''}">
                  				￥${order.discountFee}
                  			</c:when>
                  			<c:otherwise>
                  				￥0.0
                  			</c:otherwise>
                  		</c:choose>
                  	</td>
				</tr>
			</table>
		</div>
	</c:if>
</div>