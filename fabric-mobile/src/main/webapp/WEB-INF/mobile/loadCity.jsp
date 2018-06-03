<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<option value="">请选择城市</option>
<c:forEach items="${cityList}" var="city">
	<option value="${city.id}">${city.name}</option>
</c:forEach>