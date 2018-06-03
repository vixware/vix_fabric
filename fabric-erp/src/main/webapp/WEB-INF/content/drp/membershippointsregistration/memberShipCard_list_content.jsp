<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="memberShipCardTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="memberShipCardPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="memberShipCardTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="memberShipCardOrderField" value="${pager.orderField}" />
<input type="hidden" id="memberShipCardOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="memberShipCardInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<div class="right_content">
	<div class="table">
		<table class="list">
			<tr class="alt">
				<th width="10%">选择</th>
				<th>卡号</th>
				<th>会员姓名</th>
			</tr>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td><input id="chkBrandId" name="chkBrandId" value="${entity.id}" type="radio" onclick="chkChange(this,'${entity.id}','${entity.customerAccount.name}','${entity.vipcardid}')" /></td>
					<td>${entity.vipcardid}</td>
					<td>${entity.customerAccount.name}</td>
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
		</table>
	</div>
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="memberShipCardInfo"></b>到 <b class="memberShipCardTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
		</div>
	</div>
</div>
