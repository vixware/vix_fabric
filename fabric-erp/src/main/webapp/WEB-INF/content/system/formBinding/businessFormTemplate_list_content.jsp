<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="radioTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="radioPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="radioTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="radioOrderField" value="${pager.orderField}" />
<input type="hidden" id="radioOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="radioInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<div class="right_content">
	<div class="table">
		<table class="list">
			<tr class="alt">
				<th width="50">选择</th>
				<th onclick="orderBy('code');">编码</th>
				<th onclick="orderBy('name');">业务表名称</th>
			</tr>
			<s:iterator value="businessFormList" var="entity" status="s">
				<tr class="order_tr" orderId="${entity.id}">
					<td><input id="chkBrandId" name="chkBrandId" value="${entity.id}" type="checkbox" onclick="chkChange(this,'${entity.id}')" /></td>
					<td>${entity.businessFormCode}</td>
					<td>${entity.businessFormName}</td>
				</tr>
			</s:iterator>
		</table>
	</div>
</div>
