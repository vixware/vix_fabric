<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	/** 载入数据列排序图标 */
	loadOrderByImage("${vix}", "salesOrder");
</script>
<input type="hidden" id="salesOrderTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="salesOrderPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="salesOrderTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="salesOrderOrderField" value="${pager.orderField}" />
<input type="hidden" id="salesOrderOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="salesOrderInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="itemTable" class="list">
	<tr class="alt">
		<th style="cursor: pointer;" onclick="orderBy('code');" width="30%">订单编码&nbsp;<img id="salesOrderImg_code" src="${vix}/common/img/arrow.gif"> </span></th>
		<th style="cursor: pointer;" onclick="orderBy('name');" width="40%">订单名称&nbsp;<img id="salesOrderImg_name" src="${vix}/common/img/arrow.gif"> </span></th>
	</tr>
	<%
		int count = 0;
	%>
	<s:iterator value="pager.resultList" var="entity">
		<%
			count++;
		%>
		<tr>
			<td style="cursor: pointer" onclick="salesOrderFieldChanged('${entity.id}')">${entity.code}</td>
			<td style="cursor: pointer" onclick="salesOrderFieldChanged('${entity.id}')">${entity.name}</td>
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
		</tr>
	</c:forEach>
</table>