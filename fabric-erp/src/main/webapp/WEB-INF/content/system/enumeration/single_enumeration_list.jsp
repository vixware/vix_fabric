<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function deleteEntity(id){
	asyncbox.confirm('确定要删除该字典数据么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","enumeration");
</script>
<input type="hidden" id="enumerationTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="enumerationPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="enumerationTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="enumerationOrderField" value="${pager.orderField}" />
<input type="hidden" id="enumerationOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="enumerationInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="table" class="list">
	<tr>
		<th onclick="orderBy('id');" width="10%">编号&nbsp;<img id="enumerationImg_id" src="${vix}/common/img/arrow.gif"></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('enumerationCategory.name');">分类&nbsp;<img id="enumerationImg_enumerationCategory" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img id="enumerationImg_name" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('displayName');">显示名称&nbsp;<img id="enumerationImg_displayName" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="10%">操作</th>
	</tr>
	<% int count = 0; %>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<% count++; %>
		<tr>
			<td><s:if test="#entity.id != null ">
					${s.count + (pager.pageSize * (pager.pageNo -1))}
				</s:if> <s:else>&nbsp;</s:else></td>
			<td>${entity.enumerationCategory.name}</td>
			<td><span style="cursor: pointer; font-weight: bold;" onclick="saveOrUpdate('${entity.id}');">${entity.name}</span></td>
			<td>${entity.displayName}</td>
			<td align="center"><s:if test="#entity.id != null ">
					<a href="#" onclick="saveOrUpdate('${entity.id}');"> <img src="${vix}/common/img/icon_edit.png" alt="修改" />
					</a>
					<a href="#" onclick="deleteEntity('${entity.id}');"> <img src="${vix}/common/img/icon_12.png" alt="删除" />
					</a>
				</s:if></td>
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
		</tr>
	</c:forEach>
</table>