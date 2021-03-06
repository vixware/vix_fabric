<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#configUrlForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="configUrlForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="name" name="entityForm.name" class="validate[required] text-input" value="${entity.name}" /><span style="color: red;">*</span></td>
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="code" name="entityForm.code" class="validate[required] text-input" value="${entity.code}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">是否重定向:&nbsp;</td>
					<td><s:select list="#{\"N\":\"否\",\"Y\":\"是\"}" name="entityForm.isRedirect" value="%{entity.isRedirect}" theme="simple"></s:select></td>
					<td align="right">URL:&nbsp;</td>
					<td><input type="text" id="url" name="entityForm.url" class="validate[required] text-input" value="${entity.url}" /><span style="color: red;">*</span></td>
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