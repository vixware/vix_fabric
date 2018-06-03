<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","inventoryCurrentStock");
</script>
<input type="hidden" id="inventoryCurrentStockTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="inventoryCurrentStockPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="inventoryCurrentStockTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="inventoryCurrentStockOrderField" value="${pager.orderField}" />
<input type="hidden" id="inventoryCurrentStockOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="inventoryCurrentStockInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="itemTable" class="list">
	<tbody>
		<tr>
			<th width="20%" style="cursor: pointer;" onclick="orderBy('code');">商品编码&nbsp;<img id="itemPriceManageImg_code" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="60%" style="cursor: pointer;" onclick="orderBy('name');">商品名称&nbsp;<img id="itemPriceManageImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="20%" style="cursor: pointer;">价格</th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity">
			<%
				count++;
			%>
			<tr>
				<td style="cursor: pointer;" onclick="chooseItemForPrice('${entity.id}','${entity.code}','${entity.name}','${entity.measureUnit.name}','${entity.skuCode}')">${entity.code}</td>
				<td style="cursor: pointer;" onclick="chooseItemForPrice('${entity.id}','${entity.code}','${entity.name}','${entity.measureUnit.name}','${entity.skuCode}')">${entity.name}</td>
				<td style="cursor: pointer;" onclick="chooseItemForPrice('${entity.id}','${entity.code}','${entity.name}','${entity.measureUnit.name}','${entity.skuCode}')">${entity.price}</td>
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
	</tbody>
</table>