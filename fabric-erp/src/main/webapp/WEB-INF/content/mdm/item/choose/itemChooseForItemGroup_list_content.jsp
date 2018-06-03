<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#itemChoose tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#itemChoose tr:even").addClass("alt");
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","chooseForItemGroup");
</script>
<input type="hidden" id="chooseForItemGroupTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="chooseForItemGroupPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="chooseForItemGroupTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="chooseForItemGroupOrderField" value="${pager.orderField}" />
<input type="hidden" id="chooseForItemGroupOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="chooseForItemGroupInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="itemChoose" class="list">
	<tbody>
		<tr class="alt">
			<th width="50" style="height: 20px;">选择</th>
			<th style="cursor: pointer;" onclick="orderBy('scode');">编码&nbsp; <img id="chooseForItemGroupImg_serialCode" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img id="chooseForItemGroupImg_serialCode" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('serialCode');">序列号&nbsp;<img id="chooseForItemGroupImg_serialCode" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('price');">价格&nbsp;<img id="chooseForItemGroupImg_price" src="${vix}/common/img/arrow.gif">
			</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<s:if test="chooseType == 'multi'">
					<td><input id="chkId" name="chkId" value="${entity.id}" onchange="chkChange(this,'${entity.id}','${entity.code}','${entity.name}','${entity.price}')" type="checkbox" /></td>
				</s:if>
				<s:else>
					<td><input id="chkId" name="chkId" value="${entity.id}" onchange="chkChange(this,'${entity.id}','${entity.code}','${entity.name}','${entity.price}');" type="radio" /></td>
				</s:else>
				<td>${entity.scode}&nbsp;</td>
				<td>${entity.name}</td>
				<td>${entity.serialCode}</td>
				<td>${entity.price}</td>
			</tr>
		</s:iterator>
		<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count",count);
		%>
		<c:forEach begin="1" end="${count}">
			<tr>
				<td style="height: 20px;">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>