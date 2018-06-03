<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<option value="">------请选择------</option>
<c:forEach items="${crmProjectList}" var="entity">
	<option value="${entity.id}">${entity.subject}</option>
</c:forEach>
