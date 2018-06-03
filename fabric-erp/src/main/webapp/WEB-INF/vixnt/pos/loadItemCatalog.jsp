<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<c:forEach items="${secondItemCatalogList}" var="entity" varStatus="s">
	<div <c:if test='${s.index == 0}'>class="cur"</c:if>>
		<a href="javascript:void(0);" onclick="loadItem('${entity.id}');">${entity.name}</a>
	</div>
</c:forEach>