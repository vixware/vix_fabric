<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div style="padding: 5px;">
	<table>
		<s:hidden id="enumValueId" name="enumValue.id" value="%{enumValue.id}" theme="simple" theme="simple" />
		<tr height="30">
			<td align="right">值:&nbsp;</td>
			<td><input type="text" id="value" name="enumValue.value" value="${enumValue.value}" /></td>
			<td align="right">显示名称:&nbsp;</td>
			<td><input type="text" id="enumValueDisplayName" name="enumValue.displayName" value="${enumValue.displayName}" /></td>
		</tr>
	</table>
</div>
