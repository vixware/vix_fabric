<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<li><a href="#" <c:if test="${menu.menuUrl!=null}">onclick="loadContent('','${nvix}/ws${menu.menuUrl}');"</c:if>><c:if test="${not empty menu.children }">
			<span class="menu-item-parent">${menu.name}</span>
		</c:if>
		<c:if test="${ empty menu.children }">${menu.name}</c:if></a> <c:if test="${not empty menu.children }">
		<ul>
			<c:forEach var="menu" items="${menu.children}">
				<c:set var="menu" value="${menu}" scope="request"></c:set>
				<jsp:include page="headMenu.jsp"></jsp:include>
			</c:forEach>
		</ul>
	</c:if></li>