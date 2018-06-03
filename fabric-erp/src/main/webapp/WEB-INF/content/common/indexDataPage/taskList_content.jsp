<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<input type="hidden" id="erpjobtodoListTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="erpjobtodoListPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="erpjobtodoListTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="erpjobtodoListOrderField" value="${pager.orderField}" />
<input type="hidden" id="erpjobtodoListOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="erpjobtodoListInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr>
			<th width="2%"></th>
			<th width="15%">主题</th>
			<th width="40%">内容</th>
			<th width="10%">优先级</th>
			<th width="30%">时间</th>
		</tr>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td><img src="img/ico-right.gif" alt="" title="提示：123" class="hand" onclick="saveOrUpdate('${entity.id}');" /></td>
				<td><b><a href="javascript:;" onclick="saveOrUpdate('${entity.id}');">${entity.name}</a></b></td>
				<td>${entity.code}</td>
				<td>${entity.name}</td>
				<td><s:date format="yyyy-MM-dd" name="%{#entity.createTime}" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
