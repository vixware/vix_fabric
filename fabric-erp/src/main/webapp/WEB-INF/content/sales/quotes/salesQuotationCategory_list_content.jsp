<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function deleteEntity(id){
	asyncbox.confirm('确定要删除该分类吗?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","salesQuotationCategory");
</script>
<input type="hidden" id="salesQuotationCategoryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="salesQuotationCategoryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="salesQuotationCategoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="salesQuotationCategoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="salesQuotationCategoryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="salesQuotationCategoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="table" class="list">
	<tr>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('id');"> 编号&nbsp;<img id="salesQuotationCategory_id" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('parentSalesQuotationCategory.name');"> 上级分类&nbsp;<img id="salesQuotationCategory_parentSalesQuotationCategory" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('name');"> 名称&nbsp;<img id="salesQuotationCategory_name" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="10%">操作</th>
	</tr>
	<% int count = 0; %>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<% count++; %>
		<tr>
			<td>${entity.id}</td>
			<td>${entity.parentSalesQuotationCategory.name}</td>
			<td>${entity.name}</td>
			<td align="center"><a href="#" onclick="saveOrUpdate('${entity.id}');"> <img src="${vix}/common/img/icon_edit.png" alt="修改" />
			</a> <a href="#" onclick="deleteEntity('${entity.id}');"> <img src="${vix}/common/img/icon_12.png" alt="删除" />
			</a></td>
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