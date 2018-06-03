<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<ul>
   	<c:if test="${employee.type == '2'}">
   		<li><a href="${beiken}/beiken/beikenCategoryAction!goProductList.action?id=${storeItemCatalog.id}">全部</a> </li>
   	</c:if>
   	<c:if test="${employee.type == '1'}">
        <li><a href="${beiken}/beiken/beikenCategoryAction!goProductList.action?id=${itemCatalog.id}">全部</a> </li>
        <c:if test="${secondItemCatalogList != null && fn:length(secondItemCatalogList) > 0}">
    		<c:forEach items="${secondItemCatalogList}" var="catalog" varStatus="s">
        		<li><a href="${beiken}/beiken/beikenCategoryAction!goProductList.action?id=${catalog.id}">${catalog.name}</a> </li>
        	</c:forEach>
        </c:if>
	</c:if>
</ul>