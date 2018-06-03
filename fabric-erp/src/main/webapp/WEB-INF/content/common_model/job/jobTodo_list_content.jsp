<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="jobTodoTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="jobTodoPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="jobTodoTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="jobTodoOrderField" value="${pager.orderField}" />
<input type="hidden" id="jobTodoOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="jobTodoInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr>
			<th>单据类型</th>
			<th>单据内容</th>
			<th>审批节点</th>
			<th>时间</th>
			<th>操作</th>
		</tr>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td>${entity.sourceClass}</td>
				<td>${entity.name}</td>
				<td>${entity.jobName}</td>
				<td><s:property value="formatDateToString(#entity.createTime)" /></td>
				<td><a href="#" onclick="goBillAudit('${entity.id}');">审批</a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>