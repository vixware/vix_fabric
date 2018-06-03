<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<input type="hidden" id="storeTypeTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="storeTypePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="storeTypeTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="storeTypeOrderField" value="${pager.orderField}" />
<input type="hidden" id="storeTypeOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="storeTypeInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr>
			<th width="5%">编号</th>
			<th width="40%">平台编码</th>
			<th width="50%">平台名称</th>
			<th width="5%"><s:text name="cmn_operate" /></th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td style="cursor: pointer" onclick="chooseStoreType('${entity.id}','${entity.code}','${entity.name}','${entity.type}')">${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td style="cursor: pointer" onclick="chooseStoreType('${entity.id}','${entity.code}','${entity.name}','${entity.type}')">${entity.code}</td>
				<td style="cursor: pointer" onclick="chooseStoreType('${entity.id}','${entity.code}','${entity.name}','${entity.type}')">${entity.name}</td>
				<td><a href="#" onclick="deleteStoreTypeById('${entity.id}');" title="删除"><img src="${vix}/common/img/icon_delete.png" /></a></td>
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
	</tbody>
</table>