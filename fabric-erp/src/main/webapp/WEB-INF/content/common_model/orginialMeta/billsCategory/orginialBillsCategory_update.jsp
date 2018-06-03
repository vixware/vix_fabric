<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#orginialBillsCategoryForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="orginialBillsCategoryForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="categoryCode" style="width: 80%;" name="entityForm.categoryCode" class="validate[required] text-input" value="${entity.categoryCode}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="categoryName" style="width: 80%;" name="entityForm.categoryName" class="validate[required] text-input" value="${entity.categoryName}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">描述:&nbsp;</td>
					<td><input type="text" id="categoryDescription" style="width: 80%;" name="entityForm.categoryDescription" value="${entity.categoryDescription}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>