<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$ ("table tr").mouseover (function (){
		$ (this).addClass ("over");
	}).mouseout (function (){
		$ (this).removeClass ("over");
	});
	$ ("table tr:even").addClass ("alt");
	$ (".list_check").bind ('mouseover' , function (){
		$ (".list_check").addClass ('posr');
		$ (".list_check ul").css ('display' , 'block');
	}).bind ('mouseleave' , function (){
		$ (".list_check").removeClass ('posr');
		$ (".list_check ul").css ('display' , 'none');
	});
</script>

<input type="hidden" id="oajobtodoListTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="oajobtodoListPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="oajobtodoListTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="oajobtodoListOrderField" value="${pager.orderField}" />
<input type="hidden" id="oajobtodoListOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="oajobtodoListInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr>
			<th width="2%"></th>
			<th>主题</th>
			<th>内容</th>
			<th>优先级</th>
			<th>时间</th>
		</tr>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr>
				<td><img src="img/ico-right.gif" alt="" title="提示：123" class="hand" onclick="saveOrUpdate('${entity.id}');" /></td>
				<td><b><a href="javascript:;" onclick="saveOrUpdate('${entity.id}');">${entity.flowName}</a></b></td>
				<td>${entity.jobName}</td>
				<td>${entity.priority}</td>
				<td><s:date format="yyyy-MM-dd" name="%{#entity.createTime}" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
