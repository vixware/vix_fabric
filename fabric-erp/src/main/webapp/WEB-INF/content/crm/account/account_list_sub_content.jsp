<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function radioCheck(id,name){
	$.returnValue = id+":"+name;
}
</script>
<input type="hidden" id="accountTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="accountPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="accountTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="accountOrderField" value="${pager.orderField}" />
<input type="hidden" id="accountOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="accountInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th>选择</th>
			<th width="15%"><s:text name="cmn_id" /></th>
			<th>编码</th>
			<th>名称</th>
			<th><s:text name="crm_stage" /></th>
			<th><s:text name="crm_kind" /></th>
			<th><s:text name="crm_dominus" /></th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkAccountId" name="chkAccountId" value="${entity.id}" type="radio" onclick="radioCheck('${entity.id}','${entity.name}');" /></td>
				<td width="15%"><span style="color: gray;">${entity.code}</span></td>
				<td><span style="color: gray;">${entity.name}</span></td>
				<td width="15%"><span style="color: gray;">家乐福连锁经营超市</span></td>
				<td><span style="color: gray;">待成交</span></td>
				<td><span style="color: gray;">食品类</span></td>
				<td><span style="color: gray;">李鸿忠</span></td>
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