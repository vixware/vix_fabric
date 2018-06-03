<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#caTable table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#caTable table tr:even").addClass("alt");
function radioCheck(id,name,code){
	$.returnValue = id+":"+name+":"+code;
}
</script>
<input type="hidden" id="chooseCustomerAccountTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="chooseCustomerAccountPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="chooseCustomerAccountTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="chooseCustomerAccountOrderField" value="${pager.orderField}" />
<input type="hidden" id="chooseCustomerAccountOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="chooseCustomerAccountInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="caTable" class="list">
	<tbody>
		<tr class="alt">
			<th width="8%">选择</th>
			<th width="10%">编码</th>
			<th width="20%">名称</th>
			<th width="10%">简称</th>
			<th width="10%">热点程度</th>
			<th width="10%">客户种类</th>
			<th width="10%">关系等级</th>
			<th width="10%">客户来源</th>
			<th width="10%">阶段</th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkAccountId" name="chkAccountId" value="${entity.id}" type="radio" onclick="radioCheck('${entity.id}','${entity.name}','${entity.code}');" /></td>
				<td><span style="color: gray;">${entity.code}&nbsp;</span></td>
				<td><span style="color: gray;">${entity.name}</span></td>
				<td><span style="color: gray;">${entity.shorterName}</span></td>
				<td><span style="color: gray;">${entity.hotLevel.name }</span></td>
				<td><span style="color: gray;">${entity.customerType.name }</span></td>
				<td><span style="color: gray;">${entity.relationshipClass.name }</span></td>
				<td><span style="color: gray;">${entity.customerSource.name }</span></td>
				<td><span style="color: gray;">${entity.customerStage.name }</span></td>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>