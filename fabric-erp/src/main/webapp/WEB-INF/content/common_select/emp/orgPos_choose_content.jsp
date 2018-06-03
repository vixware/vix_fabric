<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/* $("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
}); */
function chooseAll(chk){
	if(null == chk){
		$("input[name='chkBrandId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkBrandId']").attr("checked", true);
		}else{
			$("input[name='chkBrandId']").attr("checked", false);
		}
	}
	selectCount();
}
 
</script>
<input type="hidden" id="commonPosSelectTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="commonPosSelectPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="commonPosSelectTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="commonPosSelectOrderField" value="${pager.orderField}" />
<input type="hidden" id="commonPosSelectOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="commonPosSelectInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">选择</th>
			<!-- <th onclick="orderBy('id');">编号</th> -->
			<th onclick="orderBy('name');">名称</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkBrandId" name="chkBrandId" value="${entity.id}" type="${chkStyle}" <s:if test="%{checkedObjIds.contains(#entity.id+\",\")}">checked="checked"</s:if> onclick="chkChange(this,'${entity.id}','${entity.posName}')" /></td>
				<%-- <td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td> --%>
				<td>${entity.posName}<%-- <s:if test="%{checkedObjIds.contains(#entity.id+\",\")}">vvv</s:if> --%>
				</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>