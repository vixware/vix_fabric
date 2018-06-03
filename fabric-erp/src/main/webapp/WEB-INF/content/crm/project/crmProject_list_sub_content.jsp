<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#caTable table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#caTable table tr:even").addClass("alt");
$.returnValue = "";
function radioCheck(id,name){
	$.returnValue = id+":"+name;
}
</script>
<input type="hidden" id="crmProjectTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="crmProjectPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="crmProjectTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="crmProjectOrderField" value="${pager.orderField}" />
<input type="hidden" id="crmProjectOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="crmProjectInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="caTable" class="list">
	<tbody>
		<tr class="alt">
			<th>选择</th>
			<th width="15%">编号</th>
			<th width="20%">客户</th>
			<th width="25%">主题</th>
			<th width="10%">立项时间</th>
			<th width="10%">预计签单日期</th>
			<th width="10%">预计金额</th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkAccountId" name="chkAccountId" value="${entity.id}" type="radio" onclick="radioCheck('${entity.id}','${entity.subject}');" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.subject}</td>
				<td><s:property value="formatDateToString(#entity.projectEstablishDate)" /></td>
				<td><s:property value="formatDateToString(#entity.forecastSignDate)" /></td>
				<td>${entity.forecastMoney}</td>
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