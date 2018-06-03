<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<option value="">------请选择------</option>
<c:forEach items="${contactPersonList}" var="entity">
	<option value="${entity.id}">${entity.name}</option>
</c:forEach>
