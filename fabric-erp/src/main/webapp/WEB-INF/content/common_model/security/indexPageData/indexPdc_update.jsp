<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#configUrlForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="indexPdcForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="name" name="entityForm.name" class="validate[required] text-input" value="${entity.name}" /><span style="color: red;">*</span></td>
					<td align="right">div标识:&nbsp;</td>
					<td><input type="text" id="divId" name="entityForm.divId" class="validate[required] text-input" value="${entity.divId}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">URL:&nbsp;</td>
					<td><input type="text" id="divUrl" name="entityForm.divUrl" class="validate[required] text-input" value="${entity.divUrl}" /><span style="color: red;">*</span></td>
					<td align="right">备注:&nbsp;</td>
					<td><input type="text" id="memo" name="entityForm.memo" class="validate[required] text-input" value="${entity.memo}" /><span style="color: red;">*</span></td>
				</tr>
				<%-- <tr height="40">
					<td align="right">类型:&nbsp;</td>
					<td>
						<s:select id="moduleType" name="entityForm.moduleType" list="#request.moduleTypeMap" listKey="key" listValue="value" value="%{entity.moduleType}" theme="simple"></s:select>
					</td>
				</tr> --%>
			</table>
		</div>
	</form>
</div>