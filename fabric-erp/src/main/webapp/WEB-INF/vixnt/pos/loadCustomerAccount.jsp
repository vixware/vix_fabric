<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<c:if test="${customerAccount != null}">
	<c:choose>
		<c:when test="${customerAccount.headImage != null && customerAccount.headImage != ''}">
			<img src="${customerAccount.headImage}" style="width: 30px;height: 30px" >
		</c:when>
		<c:otherwise>
			<img src="${nvix}/vixntcommon/base/img/icon-pos/photo.jpg">
		</c:otherwise>
	</c:choose>
	<input id="customerAccountId" type="hidden" value="${customerAccount.id}">
	<input id="money" type="hidden" value="${customerAccount.money}">
	<input id="point" type="hidden" value="${customerAccount.point}">
	<div class="tool-header-msg">
		<div>会员：${customerAccount.name}</div>
		<div>手机号：${customerAccount.mobilePhone}</div>
	</div>
	<div class="tool-header-msg">
		<c:choose>
			<c:when test="${customerAccount.point != null && customerAccount.point > 0}">
				<div>可用积分：${customerAccount.point}</div>
			</c:when>
			<c:otherwise>
				<div>可用积分：0</div>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${customerAccount.money != null && customerAccount.money > 0}">
				<div>可用余额：${customerAccount.money}</div>
			</c:when>
			<c:otherwise>
				<div>可用余额：0</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="tool-header-msg">
		<div>会员等级：${customerAccount.memberLevelName}</div>
		<div>会员折扣：${customerAccount.discount}%</div>
	</div>
</c:if>