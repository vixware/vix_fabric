<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	
</script>

<input type="hidden" id="bulletinListTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="bulletinListPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="bulletinListTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="bulletinListOrderField" value="${pager.orderField}" />
<input type="hidden" id="bulletinListOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="bulletinListInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<table class="list">
	<tr>
		<th width="2%">&nbsp;</th>
		<th width="88%">主题</th>
		<th width="10%">时间</th>
	</tr>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<tr>
			<td><img src="img/ico-right.gif" alt="" title="提示：123" class="hand" onclick="goAnnouncementNotification('${entity.id}');" /></td>
			<td><a href="javascript:;" onclick="goAnnouncementNotification('${entity.id}');">${entity.title}</a></td>
			<td><s:date format="yyyy-MM-dd" name="%{#entity.activeStartDate}" /></td>
		</tr>
	</s:iterator>
</table>
