<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	$("#billsTypeForm").validationEngine();
	$(function() {
		if ($("#typeCode").val() == null) {
			$("#typeCode").val('${encodingRulesTableInTheMiddle.typeCode}');
		}
	});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="billsTypeForm">
		<s:hidden id="billsTypeId" name="billsTypeId" value="%{billsType.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">分类:&nbsp;</td>
					<td><input type="hidden" id="billsPropertyId" name="billsPropertyId" value="${billsType.billsProperty.id}" /> <input type="text" id="categoryName" name="categoryName" value="${billsType.billsProperty.propertyName}" readonly="readonly" /></td>
				</tr>
				<tr height="40">
					<td align="right">类型编码:&nbsp;</td>
					<td><input type="text" id="typeCode" name="typeCode" class="validate[required] text-input" value="${billsType.typeCode}" /> <span style="color: red;">*</span></td>
					<td align="right">类型名称:&nbsp;</td>
					<td><input type="text" id="typeName" name="typeName" class="validate[required] text-input" value="${billsType.typeName}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">类型描述:&nbsp;</td>
					<td colspan="3"><input type="text" id="typeDescription" name="typeDescription" size="45" value="${billsType.typeDescription}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>