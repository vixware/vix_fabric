<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#orginialVarForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="orginialVarForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">变量:&nbsp;</td>
					<td><input type="text" id="varCode" style="width: 80%;" name="entityForm.varCode" <s:if test="%{entity.id!=null && entity.id>0}">readonly="readonly"</s:if> class="validate[required] text-input" value="${entity.varCode}" /><span style="color: red;">*</span></td>
					<%-- <td align="right">名称:&nbsp;</td>
					<td>
						<input type="text" id="name" name="entityForm.name" class="validate[required] text-input" value="${entity.name}" /><span style="color: red;">*</span>
					</td> --%>
				</tr>
				<tr height="40">
					<td align="right">默认值:&nbsp;</td>
					<td><input type="text" id="defaultValue" style="width: 80%;" name="entityForm.defaultValue" class="validate[required] text-input" value="${entity.defaultValue}" /></td>
				</tr>
				<tr height="40">
					<td align="right">描述:&nbsp;</td>
					<td><input type="text" id="description" style="width: 80%;" name="entityForm.description" class="validate[required] text-input" value="${entity.description}" /></td>
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