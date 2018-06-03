<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	/** 载入数据列排序图标 */
	loadOrderByImage("${vix}", "saleOrderItem");
</script>
<input type="hidden" id="saleOrderItemTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="saleOrderItemPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="saleOrderItemTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="saleOrderItemOrderField" value="${pager.orderField}" />
<input type="hidden" id="saleOrderItemOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="saleOrderItemInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="itemTable" class="list">
	<tr class="alt">
		<th style="cursor: pointer;" onclick="orderBy('code');" width="30%">订单明细编码&nbsp;<img id="saleOrderItemImg_code" src="${vix}/common/img/arrow.gif"> </span></th>
		<th style="cursor: pointer;" onclick="orderBy('name');" width="40%">订单明细主题&nbsp;<img id="saleOrderItemImg_name" src="${vix}/common/img/arrow.gif"> </span></th>
	</tr>
	<%
		int count = 0;
	%>
	<s:iterator value="pager.resultList" var="entity">
		<%
			count++;
		%>
		<tr>
			<td style="cursor: pointer" onclick="chooseItemForPrice('${entity.id}','${entity.itemCode}','${entity.title}','${entity.price}','${entity.skuCode}','${entity.amount}')">${entity.itemCode}</td>
			<td style="cursor: pointer" onclick="chooseItemForPrice('${entity.id}','${entity.itemCode}','${entity.title}','${entity.price}','${entity.skuCode}','${entity.amount}')">${entity.title}</td>
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