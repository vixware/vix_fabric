<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<input type="hidden" id="schedulListTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="schedulListPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="schedulListTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="schedulListOrderField" value="${pager.orderField}" />
<input type="hidden" id="schedulListOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="schedulListInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr>
			<th width="2%">&nbsp;</th>
			<th width="83%">主题</th>
			<th width="15%">时间</th>
		</tr>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td><img src="img/ico-right.gif" alt="" title="${entity.scheduleName}" class="hand" onclick="saveOrUpdateSchedul('${entity.id}');" /></td>
				<td><a href="javascript:;" onclick="saveOrUpdateSchedul('${entity.id}');">${entity.scheduleName}</a></td>
				<td><s:date format="yyyy-MM-dd" name="%{#entity.startTime}" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>