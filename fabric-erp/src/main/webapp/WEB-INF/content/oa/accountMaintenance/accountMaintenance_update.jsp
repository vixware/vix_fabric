<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#expenseAccountform").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="expenseAccountform">
		<s:hidden id="expenseAccountId" name="expenseAccountId" value="%{expenseAccount.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">科目编码:&nbsp;</td>
					<td><input type="text" id="expenseAccountCode" name="expenseAccountCode" class="validate[required] text-input" value="${expenseAccount.code}" style="width: 175px;" /><span style="color: red;">*</span></td>
					<td align="right">科目名称:&nbsp;</td>
					<td><input type="text" id="expenseAccountName" name="expenseAccountName" class="validate[required] text-input" value="${expenseAccount.name}" style="width: 175px;" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">对应财务科目:&nbsp;</td>
					<td><input type="text" id="financeAccount" name="financeAccount" value="${expenseAccount.financeAccount}" style="width: 175px;" /></td>
					<td align="right">财务对方科目:&nbsp;</td>
					<td><input type="text" id="financialSubjects " name="financialSubjects" value="${expenseAccount.financialSubjects}" style="width: 175px;" /></td>
				</tr>
				<tr height="40">
					<td align="right">描述:&nbsp;</td>
					<td colspan="3"><textarea rows="" cols="" id="expenseAccountMemo" name="expenseAccountMemo" style="width: 525px; height: 150px;">${expenseAccount.memo}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>