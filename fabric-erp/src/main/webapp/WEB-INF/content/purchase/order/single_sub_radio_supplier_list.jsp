<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("table tr").mouseover(function() {
		$(this).addClass("over");
	}).mouseout(function() {
		$(this).removeClass("over");
	});
	$("table tr:even").addClass("alt");
	//list_check
	$(".list_check").bind('mouseover', function() {
		$(".list_check").addClass('posr');
		$(".list_check ul").css('display', 'block');
	}).bind('mouseleave', function() {
		$(".list_check").removeClass('posr');
		$(".list_check ul").css('display', 'none');
	});
</script>
<input type="hidden" id="radioTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="radioPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="radioTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="radioOrderField" value="${pager.orderField}" />
<input type="hidden" id="radioOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="radioInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<div class="right_content">
	<div class="table">
		<table class="list">
			<tr class="alt">
				<th width="50">选择</th>
				<th width="50">编号</th>
				<th>名称</th>
			</tr>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<tr>
					<td><input id="chkBrandId" name="chkBrandId" value="${entity.id}" type="radio" onclick="chkChange(this,'${entity.id}','${entity.name}','${entity.code }')" /></td>
					<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
					<td>${entity.name}</td>
				</tr>
			</s:iterator>
			<s:iterator begin="pager.currentPageSize" end="7" step="1">
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
			</s:iterator>
		</table>
	</div>
</div>
