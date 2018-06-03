<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#itemChoose tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#itemChoose tr:even").addClass("alt");
 
$(".close").click(
	function () {
		$(this).parent().parent().css({ "display": "none"});
	}
);
</script>
<input type="hidden" id="itemForChooseTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="itemForChoosePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="itemForChooseTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="itemForChooseOrderField" value="${pager.orderField}" />
<input type="hidden" id="itemForChooseOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="itemForChooseInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="itemChoose" class="list">
	<tbody>
		<tr class="alt">
			<th width="50" style="height: 20px;">选择</th>
			<th onclick="orderBy('scode');"><span style="cursor: pointer;" onclick="orderBy('scode');"> 编码&nbsp; <s:if test="%{pager.orderField == 'scode' && pager.orderBy == 'asc' }">
						<img src="${vix}/common/img/arrow_down.gif">
					</s:if> <s:elseif test="%{pager.orderField == 'scode' && pager.orderBy == 'desc' }">
						<img src="${vix}/common/img/arrow_up.gif">
					</s:elseif> <s:else>
						<img src="${vix}/common/img/arrow.gif">
					</s:else>
			</span></th>
			<th onclick="orderBy('xcode');"><span style="cursor: pointer;" onclick="orderBy('xcode');"> 名称&nbsp; <s:if test="%{pager.orderField == 'xcode' && pager.orderBy == 'asc' }">
						<img src="${vix}/common/img/arrow_down.gif">
					</s:if> <s:elseif test="%{pager.orderField == 'xcode' && pager.orderBy == 'desc' }">
						<img src="${vix}/common/img/arrow_up.gif">
					</s:elseif> <s:else>
						<img src="${vix}/common/img/arrow.gif">
					</s:else>
			</span></th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td style="height: 20px;"><input id="chkId" name="chkId" value="${entity.id}" type="radio" onchange="chkChange(this,${entity.id});" /></td>
				<td>${entity.code}</td>
				<td>${entity.name}</td>
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
				<td style="height: 20px;">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>