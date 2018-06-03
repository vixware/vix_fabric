<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
</script>
<input type="hidden" id="subCommissionPlanItemTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="subCommissionPlanItemPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="subCommissionPlanItemTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="subCommissionPlanItemOrderField" value="${pager.orderField}" />
<input type="hidden" id="subCommissionPlanItemOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="subCommissionPlanItemInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50px">选择</th>
			<th width="15%">编码</th>
			<th width="15%">名称</th>
			<th width="15%">业务类型</th>
			<th width="15%">${vv:varView('vix_mdm_item')}类型</th>
			<th width="10%">佣金比率</th>
			<th width="10%">固定佣金</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId${entity.id}" name="chkId" type="radio" onchange="chkChange(this,'${entity.id}','${entity.name}')" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td><span style="color: gray;">${entity.name }</span></td>
				<td><span style="color: gray;">${entity.bizType }</span></td>
				<td><span style="color: gray;">${entity.itemType }</span></td>
				<td><span style="color: gray;">${entity.fixedCommissionTerm }</span></td>
				<td><span style="color: gray;">${entity.fixedCommission }</span></td>
			</tr>
		</s:iterator>
		<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count",count);
		%>
		<c:forEach begin="1" end="${count}">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>