<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","itemPriceManage");
</script>
<input type="hidden" id="itemPriceManageTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="itemPriceManagePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="itemPriceManageTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="itemPriceManageOrderField" value="${pager.orderField}" />
<input type="hidden" id="itemPriceManageOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="itemPriceManageInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="itemTable" class="list">
	<tbody>
		<tr class="alt">
			<th style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="itemPriceManageImg_code" src="${vix}/common/img/arrow.gif"> </span></th>
			<th style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img id="itemPriceManageImg_name" src="${vix}/common/img/arrow.gif"> </span></th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity">
			<%
				count++;
			%>
			<tr>
				<td style="cursor: pointer" onclick="chooseItemForPrice('${entity.id}','${entity.itemcode}','${entity.itemname}','${entity.price}','${entity.masterUnit}','${entity.skuCode}','${entity.specification}',${entity.quantity})">${entity.itemcode}</td>
				<td style="cursor: pointer" onclick="chooseItemForPrice('${entity.id}','${entity.itemcode}','${entity.itemname}','${entity.price}','${entity.masterUnit}','${entity.skuCode}','${entity.specification}',${entity.quantity})">${entity.itemname}</td>
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
	</tbody>
</table>