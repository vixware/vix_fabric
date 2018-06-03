<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function deleteEntity(id){
	asyncbox.confirm('确定要删除该字典分类吗?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","enumerationCategory");
</script>
<input type="hidden" id="enumerationCategoryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="enumerationCategoryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="enumerationCategoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="enumerationCategoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="enumerationCategoryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="enumerationCategoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="table" class="list">
	<tr>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('id');"><s:text name="cmn_id" />&nbsp;<img id="enumerationCategoryImg_id" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="120px"><span style="cursor: pointer;" onclick="orderBy('parentEnumerationCategory.name');">父分类&nbsp;<img id="enumerationCategoryImg_parentEnumerationCategory" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img id="enumerationCategoryImg_name" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('memo');">备注&nbsp;<img id="enumerationCategoryImg_memo" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="10%">操作</th>
	</tr>
	<% int count = 0; %>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<% count++; %>
		<tr>
			<td><s:if test="#entity.id != null ">
					${s.count + (pager.pageSize * (pager.pageNo -1))}
				</s:if> <s:else>&nbsp;</s:else></td>
			<td>${entity.parentEnumerationCategory.name}</td>
			<td><span style="cursor: pointer; font-weight: bold;" onclick="saveOrUpdate('${entity.id}');">${entity.name}</span></td>
			<td>${entity.memo}</td>
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
		</tr>
	</c:forEach>
</table>