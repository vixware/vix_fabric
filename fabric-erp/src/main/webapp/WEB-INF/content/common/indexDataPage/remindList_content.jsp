<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="remindsTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="remindsPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="remindsTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="remindsOrderField" value="${pager.orderField}" />
<input type="hidden" id="remindsOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="remindsInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tr>
		<th width="2%">&nbsp;</th>
		<th width="15%">主题</th>
		<th width="63%">内容</th>
		<th width="10%">优先级</th>
		<th width="10%">时间</th>
	</tr>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<tr>
			<td><img src="img/ico-right.gif" alt="" title="提示：123" class="hand" onclick="saveOrUpdate('${entity.id}');" /></td>
			<td><a href="javascript:;" onclick="saveOrUpdate('${entity.id}');">${entity.subject}</a></td>
			<td>${entity.content}</td>
			<td>${entity.priority}</td>
			<td><s:date format="yyyy-MM-dd" name="%{#entity.remindTime}" /></td>
		</tr>
	</s:iterator>
</table>
