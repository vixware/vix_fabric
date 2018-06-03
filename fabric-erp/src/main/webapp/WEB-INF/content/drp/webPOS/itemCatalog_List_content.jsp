<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<input type="hidden" id="itemCatalogListTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="itemCatalogListPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="itemCatalogListTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="itemCatalogListOrderField" value="${pager.orderField}" />
<input type="hidden" id="itemCatalogListOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="itemCatalogListInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<s:iterator value="pager.resultList" var="entity" status="s">
	<a href="#" class="pos_btn"><span>${entity.name }</span> </a>
</s:iterator>
