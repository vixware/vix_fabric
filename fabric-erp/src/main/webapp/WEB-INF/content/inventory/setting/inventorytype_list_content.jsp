<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="customerSourceTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="customerSourcePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="customerSourceTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="customerSourceOrderField" value="${pager.orderField}" />
<input type="hidden" id="customerSourceOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="customerSourceInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%"><s:text name="id" />&nbsp;</th>
			<th width="10%">缺省&nbsp;</th>
			<th width="10%">启用&nbsp;</th>
			<th width="50%">内容</th>
			<th width="25%">备注</th>
		</tr>
		<s:iterator value="inventoryTypeList" var="entity" status="s">
			<tr>
				<td><input id="customerSourceId_${s.count}" value="${entity.id}" type="hidden" />${s.count}</td>
				<td><s:if test="#entity.isDefault == 1">
						<input id="customerSourceRadio_${s.count}" type="radio" value="1" name="customerSourceRadio" checked="checked" />
					</s:if> <s:else>
						<input id="customerSourceRadio_${s.count}" type="radio" value="0" name="customerSourceRadio" />
					</s:else></td>
				<td><s:if test="#entity.enable == 1">
						<input id="customerSourceCheckbox_${s.count}" type="checkbox" value="1" checked="checked" />
					</s:if> <s:else>
						<input id="customerSourceCheckbox_${s.count}" type="checkbox" value="0" />
					</s:else></td>
				<td><input id="customerSourceName_${s.count}" type="text" style="height: 24px; width: 100%;" value="${entity.name }" onblur="directoryBlur('customerSource',${s.count});" /></td>
				<td><input id="customerSourceMemo_${s.count}" type="text" style="height: 24px; width: 100%;" value="${entity.memo }" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>