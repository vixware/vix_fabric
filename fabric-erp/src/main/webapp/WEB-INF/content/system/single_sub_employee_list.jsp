<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#tableSub tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#tableSub tr:even").addClass("alt");
</script>
<input type="hidden" id="subTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="subPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="subTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="subOrderField" value="${pager.orderField}" />
<input type="hidden" id="subOrderBy" value="${pager.orderBy}" />
<table id="tableSub">
	<tr>
		<th width="10%">选择</th>
		<th onclick="orderBy('name');" width="15%">名称</th>
		<th onclick="orderBy('gender');" width="10%">性别</th>
		<th onclick="orderBy('isMarriage');" width="10%">婚否</th>
		<th onclick="orderBy('currentResidence');" width="15%">住址</th>
		<th onclick="orderBy('national');" width="10%">民族</th>
		<th onclick="orderBy('isDemission');" width="10%">是否离职</th>
	</tr>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<tr>
			<td><input id="employeeId" name="employeeId" value="${entity.id}" type="radio" onchange="chkChange(this,'${entity.id}','${entity.name}');" /></td>
			<td>${entity.name}</td>
			<td><s:if test="#entity.gender == 0">
					女
				</s:if> <s:elseif test="#entity.gender == 1">
					男
				</s:elseif></td>
			<td><s:if test="#entity.isMarriage == 0">
					未婚
				</s:if> <s:elseif test="#entity.isMarriage == 1">
					已婚
				</s:elseif></td>
			<td>${entity.currentResidence}</td>
			<td>${entity.national}</td>
			<td><s:if test="#entity.isDemission == 0">
					在职
				</s:if> <s:elseif test="#entity.isDemission == 1">
					离职
				</s:elseif></td>
		</tr>
	</s:iterator>
</table>