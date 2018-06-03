<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<li <c:if test="${not empty menu.children }">class="fly"</c:if>><a href="#" <c:if test="${menu.menuUrl!=null}">onclick="loadContent('${vix}${menu.menuUrl}');"</c:if>>${menu.name}</a> <c:if test="${not empty menu.children }">
		<ul>
			<c:forEach var="menu" items="${menu.children}">
				<c:set var="menu" value="${menu}" scope="request"></c:set>
				<jsp:include page="headMenu.jsp"></jsp:include>
			</c:forEach>
		</ul>
	</c:if></li>