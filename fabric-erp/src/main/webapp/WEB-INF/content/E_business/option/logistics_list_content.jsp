<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<input type="hidden" id="logisticsTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="logisticsPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="logisticsTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="logisticsOrderField" value="${pager.orderField}" />
<input type="hidden" id="logisticsOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="logisticsInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr>
			<th width="5%">编号</th>
			<th width="30%">公司编码</th>
			<th width="30%">公司名称</th>
			<th width="30%">是否默认</th>
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
				<td style="cursor: pointer" onclick="chooseLogistics('${entity.id}','${entity.code}','${entity.name}','${entity.isDefault}')">${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td style="cursor: pointer" onclick="chooseLogistics('${entity.id}','${entity.code}','${entity.name}','${entity.isDefault}')">${entity.code}</td>
				<td style="cursor: pointer" onclick="chooseLogistics('${entity.id}','${entity.code}','${entity.name}','${entity.isDefault}')">${entity.name}</td>
				<td style="cursor: pointer" onclick="chooseLogistics('${entity.id}','${entity.code}','${entity.name}','${entity.isDefault}')"><s:if test="%{#entity.isDefault==0}">否</s:if> <s:elseif test="%{#entity.isDefault==1}">是</s:elseif></td>
				<td><a href="#" onclick="deleteLogisticsById('${entity.id}');" title="删除"><img src="${vix}/common/img/icon_delete.png" /></a></td>
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
	</tbody>
</table>