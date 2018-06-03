<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<c:choose>
	<c:when test="${shoppingCartCount != null && shoppingCartCount > 0}">${fn:split(shoppingCartCount,'.')[0]}</c:when>
	<c:otherwise>0</c:otherwise>
</c:choose>