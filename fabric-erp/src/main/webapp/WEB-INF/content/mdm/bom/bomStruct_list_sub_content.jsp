<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#caTable table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#caTable table tr:even").addClass("alt");
function radioCheck(id,name){
	$.returnValue = id+":"+name;
}
</script>
<input type="hidden" id="bomStructTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="bomStructPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="bomStructTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="bomStructOrderField" value="${pager.orderField}" />
<input type="hidden" id="bomStructOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="bomStructInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="caTable" class="list">
	<tbody>
		<tr class="alt">
			<th>选择</th>
			<th onclick="orderBy('configItemBomName');" width="15%">Bom名称&nbsp; <s:if test="%{pager.orderField == 'configItemBomName' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'configItemBomName' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('bomType');" width="15%">Bom类型&nbsp; <s:if test="%{pager.orderField == 'bomType' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'bomType' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('bomClass');" width="15%">Bom类别&nbsp; <s:if test="%{pager.orderField == 'bomClass' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'bomClass' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('version');" width="10%">版本&nbsp; <s:if test="%{pager.orderField == 'version' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'version' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('createTime');" width="15%">创建日期&nbsp; <s:if test="%{pager.orderField == 'createTime' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'createTime' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="radio" onclick="radioCheck(${entity.id},'${entity.configItemBomName}');" /></td>
				<td>${entity.configItemBomName}</td>
				<td>${entity.bomType}</td>
				<td>${entity.bomClass}</td>
				<td>${entity.version}</td>
				<td>${entity.createTime}</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>