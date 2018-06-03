<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="tool-js-msg">
	<c:choose>
		<c:when test="${orderCount != null && orderCount > 0}">
			数量：<span style="font-size: 25px;">${orderCount}</span>
		</c:when>
		<c:otherwise>
			数量：<span style="font-size: 25px;">0</span>
		</c:otherwise>
	</c:choose>
</div>
<div class="tool-js-msg">
	<c:choose>
		<c:when test="${orderTotalFee != null && orderTotalFee > 0}">
			金额：<span style="font-size: 25px;">￥${orderTotalFee}</span>
		</c:when>
		<c:otherwise>
			金额：<span style="font-size: 25px;">￥0.00</span>
		</c:otherwise>
	</c:choose>
</div>