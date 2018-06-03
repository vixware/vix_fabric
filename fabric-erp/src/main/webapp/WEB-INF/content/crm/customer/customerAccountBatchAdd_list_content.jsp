<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
</script>
<input type="hidden" id="customerAccountTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="customerAccountPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="customerAccountTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="customerAccountOrderField" value="${pager.orderField}" />
<input type="hidden" id="customerAccountOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="customerAccountInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
	<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
		com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
		count = pager.getPageSize() - count;
		request.setAttribute("count",count);
	%>
	<c:forEach begin="1" end="${count}" varStatus="status">
		<tr id="${status.count}">
			<td>&nbsp;</td>
			<td><input id="code" type="text" class="ipt" value="" /></td>
			<td><input id="name" type="text" class="ipt" value="" /></td>
			<td><input id="shorterName" type="text" class="ipt" value="" /></td>
			<td><s:select id="hotLevelId" headerKey="" headerValue="--请选择--" list="%{hotLevelList}" listValue="name" listKey="id" multiple="false" theme="simple"></s:select></td>
			<td><s:select id="customerTypeId" headerKey="" headerValue="--请选择--" list="%{customerTypeList}" listValue="name" listKey="id" multiple="false" theme="simple"></s:select></td>
			<td><s:select id="relationshipClassId" headerKey="" headerValue="--请选择--" list="%{relationshipClassList}" listValue="name" listKey="id" multiple="false" theme="simple"></s:select></td>
			<td><s:select id="customerSourceId" headerKey="" headerValue="--请选择--" list="%{customerSourceList}" listValue="name" listKey="id" multiple="false" theme="simple"></s:select></td>
			<td><s:select id="customerStageId" headerKey="" headerValue="--请选择--" list="%{customerStageList}" listValue="name" listKey="id" multiple="false" theme="simple"></s:select></td>
			<td><select id="customerType"><option value="enterPrise">企业客户</option>
					<option value="personal">个人客户</option></select></td>
		</tr>
	</c:forEach>
	<tr>
		<td>&nbsp;</td>
		<td><input name="" type="button" class="btn" value="批量新增" onclick="batchAddCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量新增" onclick="batchAddCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量新增" onclick="batchAddCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量新增" onclick="batchAddCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量新增" onclick="batchAddCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量新增" onclick="batchAddCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量新增" onclick="batchAddCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量新增" onclick="batchAddCustomerAccount();" /></td>
		<td><input name="" type="button" class="btn" value="批量新增" onclick="batchAddCustomerAccount();" /></td>
	</tr>
</table>