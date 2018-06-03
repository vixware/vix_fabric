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
</script>

<input type="hidden" id="msgListTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="msgListPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="msgListTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="msgListOrderField" value="${pager.orderField}" />
<input type="hidden" id="msgListOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="msgListInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<table class="list">
	<tr>
		<th>标题</th>
		<th>内容</th>
		<th>时间</th>
		<th>是否阅读</th>
		<th>&nbsp;</th>
	</tr>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<tr>
			<td>${entity.title}</td>
			<td>${entity.content}</td>
			<td><s:date format="yyyy-MM-dd" name="%{#entity.createTime}" /></td>
			<td>$${entity.isRead==1? '是': '否' }</td>
			<td><img src="img/icon_edit.png" alt="" title="查看" class="hand" /></td>
		</tr>
	</s:iterator>
</table>
