<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","chooseCompetitor");
function chooseSingleCompetitor(id,name){
	$.returnValue = id+":"+name;
}
</script>
<input type="hidden" id="chooseCompetitorTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="chooseCompetitorPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="chooseCompetitorTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="chooseCompetitorOrderField" value="${pager.orderField}" />
<input type="hidden" id="chooseCompetitorOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="chooseCompetitorInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">选择</th>
			<th onclick="orderBy('id');" width="10%" style="cursor: pointer;"><s:text name="cmn_id" />&nbsp;<img id="chooseCompetitorImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('companyName');" width="20%" style="cursor: pointer;">公司名称&nbsp;<img id="chooseCompetitorImg_companyName" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('competeProduct');" width="20%" style="cursor: pointer;">竞争产品&nbsp;<img id="chooseCompetitorImg_competeProduct" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('memo');" width="35%" style="cursor: pointer;">备注&nbsp;<img id="chooseCompetitorImg_memo" src="${vix}/common/img/arrow.gif">
			</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="radio" onchange="chooseSingleCompetitor('${entity.id}','${entity.companyName}');" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.companyName}</td>
				<td>${entity.competeProduct}</td>
				<td>${entity.memo}</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>