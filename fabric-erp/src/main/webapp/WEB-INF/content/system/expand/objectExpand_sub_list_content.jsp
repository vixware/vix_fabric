<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
$.returnValue = "";
function radioCheck(id,name){
	$.returnValue = id+","+name;
}
</script>
<input type="hidden" id="objectExpandSubTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="objectExpandSubPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="objectExpandSubTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="objectExpandSubOrderField" value="${pager.orderField}" />
<input type="hidden" id="objectExpandSubOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="objectExpandSubInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<table class="list">
	<tbody>
		<tr class="alt">
			<th width="10%">选择</th>
			<th width="80px"><span style="cursor: pointer;" onclick="orderBy('id');">编号&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
			<th width="200px"><span style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
			<th width="100px"><span style="cursor: pointer;" onclick="orderBy('isReference');">是否已用&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
			<th width="160px"><span style="cursor: pointer;" onclick="orderBy('expandTableName');">扩展表&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
			<th width="80px"><span style="cursor: pointer;" onclick="orderBy('status');">状态&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
		</tr>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td><input id="radId" name="radId" value="${entity.id}" type="radio" onclick="radioCheck(${entity.id},'${entity.name}');" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.name}</td>
				<td><s:if test="#entity.isReference == 0">
						未用
					</s:if> <s:elseif test="#entity.isReference == 1">
						已用
					</s:elseif> <s:else>
						未用
					</s:else></td>
				<td style="text-align: left;">${entity.expandTableName}</td>
				<td><s:if test="#entity.status == 0">
						禁用
					</s:if> <s:elseif test="#entity.status == 1">
						激活
					</s:elseif></td>
			</tr>
		</s:iterator>
	</tbody>
</table>