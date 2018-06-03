<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<input type="hidden" id="storeTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="storePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="storeTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="storeOrderField" value="${pager.orderField}" />
<input type="hidden" id="storeOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="storeInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr>
			<th width="5%">编号</th>
			<th width="10%">平台类型</th>
			<th width="10%">店铺名称</th>
			<th width="20%">appKey</th>
			<th width="20%">appSecret</th>
			<th width="30%">session</th>
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
				<td style="cursor: pointer" onclick="chooseStore('${entity.id}','${entity.storeType.id}','${entity.name}','${entity.appKey}','${entity.appSecret}','${entity.session}','${entity.nick}','${entity.mallType}')">${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td style="cursor: pointer" onclick="chooseStore('${entity.id}','${entity.storeType.id}','${entity.name}','${entity.appKey}','${entity.appSecret}','${entity.session}','${entity.nick}','${entity.mallType}')">${entity.storeType.name}</td>
				<td style="cursor: pointer" onclick="chooseStore('${entity.id}','${entity.storeType.id}','${entity.name}','${entity.appKey}','${entity.appSecret}','${entity.session}','${entity.nick}','${entity.mallType}')">${entity.name}</td>
				<td style="cursor: pointer" onclick="chooseStore('${entity.id}','${entity.storeType.id}','${entity.name}','${entity.appKey}','${entity.appSecret}','${entity.session}','${entity.nick}','${entity.mallType}')">${entity.appKey}</td>
				<td style="cursor: pointer" onclick="chooseStore('${entity.id}','${entity.storeType.id}','${entity.name}','${entity.appKey}','${entity.appSecret}','${entity.session}','${entity.nick}','${entity.mallType}')">${entity.appSecret}</td>
				<td style="cursor: pointer" onclick="chooseStore('${entity.id}','${entity.storeType.id}','${entity.name}','${entity.appKey}','${entity.appSecret}','${entity.session}','${entity.nick}','${entity.mallType}')">${entity.session}</td>
				<td>
					<%-- <a href="#" onclick="goAuthorize('${entity.id}')" title="授权"><img src="${vix}/common/img/icon_show.gif" /></a> --%> <a href="#" onclick="deleteStoreById('${entity.id}');" title="删除"><img src="${vix}/common/img/icon_delete.png" /></a>
				</td>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>