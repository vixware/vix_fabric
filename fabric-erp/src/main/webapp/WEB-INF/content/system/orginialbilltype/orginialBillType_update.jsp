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
		<s:hidden id="billsTypeId" name="billsTypeId" value="%{orginialBillsType.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">分类:&nbsp;</td>
					<td><input type="hidden" id="billsPropertyId" name="billsPropertyId" value="${orginialBillsType.orginialBillsProperty.id}" /> <input type="text" id="categoryName" name="categoryName" value="${orginialBillsType.orginialBillsProperty.propertyName}" readonly="readonly" /></td>
				</tr>
				<tr height="40">
					<td align="right">类型编码:&nbsp;</td>
					<td><input type="text" id="typeCode" name="typeCode" class="validate[required] text-input" value="${orginialBillsType.typeCode}" /> <span style="color: red;">*</span></td>
					<td align="right">类型名称:&nbsp;</td>
					<td><input type="text" id="typeName" name="typeName" class="validate[required] text-input" value="${orginialBillsType.typeName}" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>