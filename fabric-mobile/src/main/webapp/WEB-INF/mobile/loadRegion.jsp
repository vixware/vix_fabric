<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<option value="">请选择区县</option>
<c:forEach items="${districtList}" var="district">
	<option value="${district.id}">${district.name}</option>
</c:forEach>