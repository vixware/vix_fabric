<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<footer>
    <ul>
        <li <c:if test="${key == 'home'}">class="footer_cur"</c:if>><a href="${beiken}/beiken/beikenIndexAction!goIndex.action?key=home"><b></b><span>首页</span></a> </li>
        <li <c:if test="${key == 'category'}">class="footer_cur"</c:if>><a href="${beiken}/beiken/beikenCategoryAction!goIndex.action?key=category"><b class="icon2"></b><span>分类</span></a> </li>
        <li <c:if test="${key == 'shoppingcart'}">class="footer_cur"</c:if>>
        	<a href="${beiken}/beiken/beikenShoppingCartAction!goIndex.action?key=shoppingcart">
        		<b class="icon3"></b><span>购物车</span>
        	</a> 
        	<div id="add" class="add">
        		<c:choose>
        			<c:when test="${shoppingCartCount != null && shoppingCartCount > 0}">${fn:split(shoppingCartCount,'.')[0]}</c:when>
        			<c:otherwise>0</c:otherwise>
        		</c:choose>
        	</div>
        </li>
        <li <c:if test="${key == 'user'}">class="footer_cur"</c:if>><a href="${beiken}/beiken/beikenUserCenterAction!goIndex.action?key=user"><b class="icon5"></b><span>我的</span></a> </li>
    </ul>
</footer>