<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<input type="hidden" id="objectModifyRecordTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="objectModifyRecordPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="objectModifyRecordTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="objectModifyRecordOrderField" value="${pager.orderField}" />
<input type="hidden" id="objectModifyRecordOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="objectModifyRecordInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<div class="right_content">
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="radioInfo"></b>到 <b class="radioTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
		</div>
	</div>
	<div class="table">
		<table class="list">
			<tr class="alt">
				<th>选择</th>
				<th>字段编码</th>
				<th>原始值</th>
				<th>修改后的值</th>
				<th>修改时间</th>
			</tr>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td><input id="chkBrandId" name="chkBrandId" value="${entity.id}" type="radio" onclick="chkChange(this,'${entity.id}','${entity.name}')" /></td>
					<td>${entity.key}</td>
					<td>${entity.oldValue}</td>
					<td>${entity.newValue}</td>
					<td><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd HH:MM:SS" /></td>
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
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="radioInfo"></b>到 <b class="radioTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
		</div>
	</div>
</div>
