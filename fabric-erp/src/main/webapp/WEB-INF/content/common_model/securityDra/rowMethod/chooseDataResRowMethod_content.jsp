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
<input type="hidden" id="dataResRowMethodTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="dataResRowMethodPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="dataResRowMethodTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="dataResRowMethodOrderField" value="${pager.orderField}" />
<input type="hidden" id="dataResRowMethodOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="dataResRowMethodInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">选择</th>
			<th onclick="orderBy('id');">编号</th>
			<th>名称</th>
		</tr>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td><input id="chkBrandId" name="chkBrandId" value="${entity.id}" type="${chkStyle}" onclick="roleChange(this,'${entity.id}','${entity.hqlProvider}')" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.hqlProvider}</td>
			</tr>
		</s:iterator>
	</tbody>
</table>