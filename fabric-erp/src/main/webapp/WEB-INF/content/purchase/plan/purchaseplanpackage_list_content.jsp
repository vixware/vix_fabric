<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="supplierTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="supplierPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="supplierTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="supplierOrderField" value="${pager.orderField}" />
<input type="hidden" id="supplierOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="supplierInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<div class="table">
	<table class="list">
		<tr class="alt">
			<th width="50">选择</th>
			<th onclick="orderBy('id');">编号</th>
			<th onclick="orderBy('name');">名称</th>
		</tr>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td><input id="chkBrandId" name="chkBrandId" value="${entity.id}" type="radio" onclick="chkChange(this,'${entity.id}','${entity.name}')" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.name}</td>
			</tr>
		</s:iterator>
	</table>
</div>
<div class="pagelist drop">
	<div>
		<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="supplierInfo"></b>到 <b class="supplierTotalCount"></b>)
		</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
	</div>
</div>
