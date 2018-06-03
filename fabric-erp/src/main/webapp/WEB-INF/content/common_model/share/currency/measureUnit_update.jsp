<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#measureUnitForm").validationEngine();
	$(function() {
		//设置原单类型选中
		$("#measureUnitTypeId").val('${measureUnit.measureUnitType.name}');
		$("#organizationId").val('${measureUnit.organization.id}');
	});
</script>
<form id="measureUnitForm">
	<div style="padding: 5px;">
		<s:hidden id="id" name="id" value="%{measureUnit.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">公司:&nbsp;</td>
					<td><s:select id="organizationId" headerKey="-1" headerValue="--请选择--" list="%{organizationList}" listValue="orgName" listKey="id" value="%{measureUnit.organization.id}" multiple="false" theme="simple">
						</s:select></td>
				</tr>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input id="code" name="code" value="${measureUnit.code}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="name" value="${measureUnit.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">类型:&nbsp;</td>
					<td><s:select id="measureUnitTypeId" headerKey="-1" headerValue="--请选择--" list="%{measureUnitTypeList}" listValue="name" listKey="id" value="%{measureUnit.measureUnitType.id}" multiple="false" theme="simple">
						</s:select> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</div>
</form>