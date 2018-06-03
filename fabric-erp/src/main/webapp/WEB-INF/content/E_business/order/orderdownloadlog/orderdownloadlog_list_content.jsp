<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<input type="hidden" id="latestOperateEntityTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="latestOperateEntityPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="latestOperateEntityTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="latestOperateEntityOrderField" value="${pager.orderField}" />
<input type="hidden" id="latestOperateEntityOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="latestOperateEntityInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<%
		int count = 0;
	%>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<%
			count++;
		%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${entity.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作员:${entity.operatePersonnel}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${entity.operate}
		</br>
	</s:iterator>
	<%
		com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request.getAttribute("pager");
		count = pager.getPageSize() - count;
		request.setAttribute("count", count);
	%>
	<c:forEach begin="1" end="${count}">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
	</c:forEach>
</table>