<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="tool-pro-lists">
	<div class="tool-content-main cur">
		<c:if test="${itemList != null && fn:length(itemList) > 0}">
			<c:forEach items="${itemList}" var="item">
				<div class="tool-content-main-box" style="cursor: pointer;" onclick="addItem('${item.id}')">
					<div class="box-text">${item.code}${item.name}</div>
					<div class="box-price">￥${item.price}</div>
				</div>
			</c:forEach>	
		</c:if>
	</div>
</div>
<c:if test="${pager != null && pager.totalPage > 1}">
	<input id="pageNo" type="hidden" value="${pager.pageNo}">
	<input id="pageCount" type="hidden" value="${pager.pageCount}">
	<input id="totalPage" type="hidden" value="${pager.totalPage}">
	<div class="left-btn" style="cursor: pointer;" onclick="loadItemByPage('pre');">
		<i></i>
	</div>
	<div class="right-btn" style="cursor: pointer;" onclick="loadItemByPage('next');">
		<i></i>
	</div>
</c:if>