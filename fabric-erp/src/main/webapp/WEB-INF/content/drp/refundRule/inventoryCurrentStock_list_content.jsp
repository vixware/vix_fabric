<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="inventoryCurrentStockTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="inventoryCurrentStockPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="inventoryCurrentStockTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="inventoryCurrentStockOrderField" value="${pager.orderField}" />
<input type="hidden" id="inventoryCurrentStockOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="inventoryCurrentStockInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<div class="right_content">
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="inventoryCurrentStockInfo"></b>到 <b class="inventoryCurrentStockTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
		</div>
	</div>
	<div class="table">
		<table class="list">
			<tr class="alt">
				<th width="50">选择</th>
				<th>商品编码</th>
				<th>商品名称</th>
			</tr>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td><input id="chkBrandId" name="chkBrandId" value="${entity.id}" type="radio" onclick="chkChange(this,'${entity.itemcode}','${entity.itemname}','${entity.id}')" /></td>
					<td>${entity.itemcode}</td>
					<td>${entity.itemname}</td>
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
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="inventoryCurrentStockInfo"></b>到 <b class="inventoryCurrentStockTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
		</div>
	</div>
</div>
