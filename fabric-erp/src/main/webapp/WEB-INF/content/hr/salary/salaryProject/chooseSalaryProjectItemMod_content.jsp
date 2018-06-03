<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
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
<input type="hidden" id="metaDataChkTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="metaDataChkPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="metaDataChkTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="metaDataChkOrderField" value="${pager.orderField}" />
<input type="hidden" id="metaDataChkOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="metaDataChkInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">选择</th>
			<th onclick="orderBy('id');">编号</th>
			<th onclick="orderBy('name');">工资项名称</th>
			<th onclick="orderBy('salaryType');">类型</th>
			<th onclick="orderBy('isActive');">是否启用</th>
			<th onclick="orderBy('defaultValue');">初始值</th>
			<th onclick="orderBy('decutTaxFlag');">是否扣税</th>
		</tr>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td><input id="chkBrandId" name="chkBrandId" value="${entity.id}" type="${chkStyle}" onclick="chkChange(this,'${entity.id}','${entity.name}')" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.name}</td>
				<td><s:property value="%{@com.vix.core.constant.BizConstant@HR_SAL_ITEMTYPE.get(#entity.salaryType)}" /></td>
				<td><s:if test="%{#entity.isActive=='Y'}">是</s:if> <s:elseif test="%{#entity.isActive=='N'}">否</s:elseif> <s:else>否</s:else></td>
				<td>${entity.defaultValue}</td>
				<td><s:if test="%{#entity.isActive=='Y'}">是</s:if> <s:elseif test="%{#entity.isActive=='N'}">否</s:elseif> <s:else>否</s:else></td>
			</tr>
		</s:iterator>
	</tbody>
</table>