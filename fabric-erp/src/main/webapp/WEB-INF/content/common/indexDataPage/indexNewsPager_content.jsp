<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<input type="hidden" id="news1ListTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="news1ListPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="news1ListTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="news1ListOrderField" value="${pager.orderField}" />
<input type="hidden" id="news1ListOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="news1ListInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr>
			<th width="2%"></th>
			<th>标题 <a href="#"><img src="img/arrow.gif" alt="" /></a></th>
			<th width="10%">日期 <a href="#"><img src="img/arrow.gif" alt="" /></a></th>
			<th width="5%">操作</th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td><img src="img/ico-right.gif" alt="" title="提示：123" class="hand" onclick="popNews();" /></td>
				<td><a href="javascript:;" onclick="popNews('${entity.id}');">${entity.title}</a></td>
				<td><s:date format="yyyy-MM-dd" name="%{#entity.createTime}" /></td>
				<td><img src="img/icon_19.gif" alt="" title="提示：123" class="hand" /></td>
			</tr>
		</s:iterator>
		<%
			/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count", count);
		%>
		<c:forEach begin="1" end="${count}">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
