<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<c:choose>
	<c:when test="${requireGoodsOrder != null}">
		<input id="orderId" type="hidden" value="${requireGoodsOrder.id}">
	</c:when>
	<c:otherwise>
		<input id="orderId" type="hidden" value="">
	</c:otherwise>
</c:choose>
<c:if test="${requireGoodsOrderItemList != null && fn:length(requireGoodsOrderItemList) > 0}">
	<c:forEach items="${requireGoodsOrderItemList}" var="entity" varStatus="s">
		<dl id="${entity.id}" role="${entity.itemCode}" style="cursor: pointer;">
			<dt>${s.index+1}</dt>
			<dd class="second-dd">
				<div class="pro-con-msg">${entity.itemCode}</div>
				<div class="pro-con-msg">${entity.item.name}</div>
				<div class="pro-con-msg">${entity.num}</div>
				<div class="pro-con-msg">￥${entity.price}</div>
				<div class="pro-con-msg">￥${entity.netTotal}</div>
			</dd>
		</dl>
	</c:forEach>
</c:if>