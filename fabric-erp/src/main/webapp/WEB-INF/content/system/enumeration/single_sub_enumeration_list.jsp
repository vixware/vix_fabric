<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#tableSub tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#tableSub tr:even").addClass("alt");
function radioCheck(id,name){
	$.returnValue = id+":"+name;
}
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","subEnumeration");
</script>
<input type="hidden" id="subEnumerationTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="subEnumerationPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="subEnumerationTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="subEnumerationOrderField" value="${pager.orderField}" />
<input type="hidden" id="subEnumerationOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="subEnumerationInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="table" class="list">
	<tr>
		<th width="10%">选择</th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('enumerationCategory.name');">分类&nbsp;<img id="enumerationImg_enumerationCategory" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('displayName');">显示名称&nbsp;<img id="enumerationImg_code" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img id="enumerationImg_name" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="10%">操作</th>
	</tr>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<tr>
			<td><input id="chkId" name="chkId" value="${entity.id}" type="radio" onclick="radioCheck('${entity.code}','${entity.displayName}');" /></td>
			<td>${entity.enumerationCategory.name}</td>
			<td>${entity.displayName}</td>
			<td>${entity.name}</td>
			<td align="center"><s:if test="#entity.id != null ">
					<a href="#" onclick="saveOrUpdate('${entity.id}');"> <img src="${vix}/common/img/icon_edit.png" alt="修改" />
					</a>
					<a href="#" onclick="deleteEntity('${entity.id}');"> <img src="${vix}/common/img/icon_12.png" alt="删除" />
					</a>
				</s:if></td>
		</tr>
	</s:iterator>
</table>