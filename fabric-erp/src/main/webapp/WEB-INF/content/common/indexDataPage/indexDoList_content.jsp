<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<h4 class="blue">待审批数据</h4>
<table class="list">
	<tbody>
		<tr>
			<th>单据类型</th>
			<th>单据内容</th>
			<th>审批节点</th>
			<th>时间</th>
			<th>操作</th>
		</tr>
		<s:if test="pager.resultList.size > 0">
			<s:iterator value="pager.resultList" var="entity" status="s">
				<tr>
					<td>${entity.sourceClass}</td>
					<td>${entity.name}</td>
					<td>${entity.jobName}</td>
					<td><s:property value="formatDateToString(#entity.createTime)" /></td>
					<td><a href="#" onclick="saveOrUpdate('${entity.id}');">审批</a></td>
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<c:forEach begin="1" end="5">
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
		</s:else>
	</tbody>
</table>
<h4 class="blue">任务数据</h4>
<table class="list">
	<tbody>
		<s:if test="taskList.size > 0">
			<s:iterator value="taskList" var="entity" status="s">
				<tr>
					<td><a href="javascript:;">${entity.name}</a> <s:date format="yyyy-MM-dd HH:mm:ss" name="%{#entity.createTime}" /></td>
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<c:forEach begin="1" end="5">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
		</s:else>
	</tbody>
</table>
<h4 class="blue">提醒数据</h4>
<table class="list">
	<tbody>
		<s:if test="alertNoticeList.size > 0">
			<s:iterator value="alertNoticeList" var="entity" status="s">
				<tr>
					<td><a href="javascript:;">${entity.name}</a> <s:date format="yyyy-MM-dd HH:mm:ss" name="%{#entity.createTime}" /></td>
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<c:forEach begin="1" end="5">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
		</s:else>
	</tbody>
</table>

