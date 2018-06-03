<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("#loadingGroupForm").validationEngine();
</script>
<form id="loadingGroupForm">
	<div class="content" style="margin-top: 15px; overflow: hidden">
		<div class="box order_table" style="line-height: normal;">
			<s:hidden id="id" name="loadingGroup.id" value="%{loadingGroup.id}" theme="simple" />
			<table>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input id="code" name="loadingGroup.code" type="text" value="${loadingGroup.code}" /></td>
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="loadingGroup.name" type="text" value="${loadingGroup.name}" /></td>
				</tr>
				<tr height="40">
					<td align="right">是否启用:&nbsp;</td>
					<td><s:if test="loadingGroup.enable == 1">
							<input type="radio" id="enable1" name="enable" value="1" checked="checked" />启用
							<input type="radio" id="enable1" name="enable" value="0" />禁用
						</s:if> <s:elseif test="loadingGroup.enable == 0">
							<input type="radio" id="enable1" name="enable" value="1" />启用
							<input type="radio" id="enable1" name="enable" value="0" checked="checked" />禁用
						</s:elseif> <s:else>
							<input type="radio" id="enable1" name="enable" value="1" />启用
							<input type="radio" id="enable1" name="enable" value="0" />禁用
						</s:else></td>
					<td align="right">备注:&nbsp;</td>
					<td><input id="memo" name="loadingGroup.memo" type="text" value="${loadingGroup.memo}" /></td>
				</tr>
			</table>
		</div>
	</div>
</form>