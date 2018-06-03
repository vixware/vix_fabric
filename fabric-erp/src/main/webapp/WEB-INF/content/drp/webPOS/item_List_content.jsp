<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="itemListTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="itemListPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="itemListTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="itemListOrderField" value="${pager.orderField}" />
<input type="hidden" id="itemListOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="itemListInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="pos_order_btn">
	<s:iterator var="entity" value="pager.resultList" status="s">
		<s:if test="#s.count%6 == 1 ">
			<tr>
		</s:if>
		<td align="center"><a href="#" class="pos_btn" onclick="appendDlLineItem('${entity.id }');" style="width: 250px;"><span>${entity.itemname }</span> </a></td>
		<s:if test="#s.count%3 == 0 ">
			</tr>
		</s:if>
	</s:iterator>
</table>
