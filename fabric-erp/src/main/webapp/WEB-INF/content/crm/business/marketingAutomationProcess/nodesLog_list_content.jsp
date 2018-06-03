<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<input type="hidden" id="nodesLogTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="nodesLogPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="nodesLogTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="nodesLogOrderField" value="${pager.orderField}" />
<input type="hidden" id="nodesLogOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="nodesLogInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<s:iterator value="pager.resultList" var="entity" status="s">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${entity.logContent}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
		</br>
	</s:iterator>
</table>