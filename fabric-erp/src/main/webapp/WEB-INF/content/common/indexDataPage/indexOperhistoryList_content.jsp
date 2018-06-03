<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<input type="hidden" id="operHisListTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="operHisListPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="operHisListTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="operHisListOrderField" value="${pager.orderField}" />
<input type="hidden" id="operHisListOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="operHisListInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<table class="list">
	<tr>
		<th width="2%"></th>
		<th width="88%">操作内容</th>
		<th width="10%">操作时间</th>
	</tr>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<tr>
			<td><img src="img/ico-right.gif" alt="" title="提示：123" class="hand" /></td>
			<td><a href="javascript:;">${entity.operate}</a></td>
			<td><s:date format="yyyy-MM-dd" name="%{#entity.operateTime}" /></td>
		</tr>
	</s:iterator>
</table>
