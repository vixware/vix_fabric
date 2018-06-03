<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
</script>
<input type="hidden" id="couponDetailTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="couponDetailPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="couponDetailTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="couponDetailOrderField" value="${pager.orderField}" />
<input type="hidden" id="couponDetailOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="couponDetailInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="itemTable" class="list">
	<tr class="alt">
		<th width="10%">
			<div class="list_check">
				<div class="drop">
					<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"> </label>
					<ul>
						<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /> </a></li>
						<li><a href="#"><s:text name="cmn_other" /> </a></li>
						<li><a href="#"><s:text name="cmn_close" /> </a></li>
					</ul>
				</div>
			</div>
		</th>
		<th style="cursor: pointer;" onclick="orderBy('name');" width="30%">面额&nbsp;<img src="${vix}/common/img/arrow.gif"> </span></th>
		<th style="cursor: pointer;" onclick="orderBy('name');" width="30%">消费额&nbsp;<img src="${vix}/common/img/arrow.gif"> </span></th>
		<th style="cursor: pointer;" onclick="orderBy('name');" width="30%">备注&nbsp;<img src="${vix}/common/img/arrow.gif"> </span></th>
	</tr>
	<%
		int count = 0;
	%>
	<s:iterator value="pager.resultList" var="entity">
		<%
			count++;
		%>
		<tr>
			<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onclick="chkChange(this,'${entity.id}')" /></td>
			<td style="cursor: pointer">${entity.amount}</td>
			<td style="cursor: pointer">${entity.expenditure}</td>
			<td style="cursor: pointer">${entity.memo}</td>
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
			<td>&nbsp;</td>
		</tr>
	</c:forEach>
</table>