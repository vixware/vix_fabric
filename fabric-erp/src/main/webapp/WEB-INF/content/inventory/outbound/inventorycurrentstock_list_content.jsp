<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<input type="hidden" id="masterInventoryCurrentStockTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="masterInventoryCurrentStockPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="masterInventoryCurrentStockTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="masterInventoryCurrentStockOrderField" value="${pager.orderField}" />
<input type="hidden" id="masterInventoryCurrentStockOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="masterInventoryCurrentStockInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<div class="table">
	<table class="list">
		<tr class="alt">
			<th width="50">选择</th>
			<th onclick="orderBy('batchcode');">批次</th>
			<th onclick="orderBy('massunitEndTime');">到期日</th>
			<th onclick="orderBy('avaquantity');">可用数量</th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td><input id="chkBrandId" name="chkBrandId" value="${entity.id}" type="checkbox" onclick="chkChange(this,'${entity.id}','${entity.batchcode}')" /></td>
				<td>${entity.batchcode}</td>
				<td><fmt:formatDate value="${entity.massunitEndTime}" pattern="yyyy-MM-dd" /></td>
				<td>${entity.avaquantity}</td>
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
	</table>
</div>
<div class="pagelist drop">
	<div>
		<span><a class="start" onclick="currentOrderPager('start');"></a> </span> <span><a class="previous" onclick="currentOrderPager('previous');"></a> </span> <em>(<b class="inventoryCurrentStockInfo"></b>到 <b class="inventoryCurrentStockTotalCount"></b>)
		</em> <span><a class="next" onclick="currentOrderPager('next');"></a> </span> <span><a class="end" onclick="currentOrderPager('end');"></a> </span>
	</div>
</div>
