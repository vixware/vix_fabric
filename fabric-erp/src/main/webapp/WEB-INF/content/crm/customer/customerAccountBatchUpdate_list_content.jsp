<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
</script>
<input type="hidden" id="batchUpdateCustomerAccountTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="batchUpdateCustomerAccountPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="batchUpdateCustomerAccountTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="batchUpdateCustomerAccountOrderField" value="${pager.orderField}" />
<input type="hidden" id="batchUpdateCustomerAccountOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="batchUpdateCustomerAccountInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tr>
		<th width="10"></th>
		<th width="50">编码</th>
		<th>名称</th>
		<th>简称</th>
		<th>热度</th>
		<th>种类</th>
		<th>关系</th>
		<th>来源</th>
		<th>阶段</th>
		<th>类型</th>
	</tr>
	<% int count =0; %>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<% count++; %>
		<tr id="${s.count}">
			<td><input id="id" type="hidden" value="${entity.id}" />&nbsp;</td>
			<td><input id="code" type="text" class="ipt" value="${entity.code}" /></td>
			<td><input id="name" type="text" class="ipt" value="${entity.name }" /></td>
			<td><input id="shorterName" type="text" class="ipt" value="${entity.shorterName}" /></td>
			<td><s:select id="hotLevelId" headerKey="-1" headerValue="--请选择--" list="%{hotLevelList}" value="%{#entity.hotLevel.id}" listValue="name" listKey="id" multiple="false" theme="simple"></s:select></td>
			<td><s:select id="customerTypeId" headerKey="-1" headerValue="--请选择--" list="%{customerTypeList}" value="%{#entity.customerType.id}" listValue="name" listKey="id" multiple="false" theme="simple"></s:select></td>
			<td><s:select id="relationshipClassId" headerKey="-1" headerValue="--请选择--" list="%{relationshipClassList}" value="%{#entity.relationshipClass.id}" listValue="name" listKey="id" multiple="false" theme="simple"></s:select></td>
			<td><s:select id="customerSourceId" headerKey="-1" headerValue="--请选择--" list="%{customerSourceList}" value="%{#entity.customerSource.id}" listValue="name" listKey="id" multiple="false" theme="simple"></s:select></td>
			<td><s:select id="customerStageId" headerKey="-1" headerValue="--请选择--" list="%{customerStageList}" value="%{#entity.customerStage.id}" listValue="name" listKey="id" multiple="false" theme="simple"></s:select></td>
			<td><select id="customerType"><option value="enterPrise">企业客户</option>
					<option value="personal">个人客户</option></select></td>
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input name="" type="button" class="btn" value="批量修改" onclick="batchUpdateCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量修改" onclick="batchUpdateCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量修改" onclick="batchUpdateCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量修改" onclick="batchUpdateCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量修改" onclick="batchUpdateCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量修改" onclick="batchUpdateCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量修改" onclick="batchUpdateCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量修改" onclick="batchUpdateCustomerAccount();" /></td>
	</tr>
</table>