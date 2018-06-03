<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#currentExpenseDetailForm").validationEngine();
	function chooseCustomerAccount() {
		$.ajax({
		url : '${vix}/oa/currentExpenseDetailAction!goChooseExpenseAccount.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 650,
			height : 450,
			title : "选择科目",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#expenseAccountId").val(rv[0]);
						$("#expenseAccountName").val(rv[1]);
					} else {
						asyncbox.success("请选择科目信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	function getReimbursementAmount() {
		$.ajax({
		url : '${vix}/oa/currentExpenseDetailAction!getReimbursementAmount.action?areaLevelId=' + $("#areaLevelId").val(),
		cache : false,
		success : function(json) {
			$("#expensesStandard").val(json);
		}
		});
	}/**
	 * 选择公司的岗位
	 */
	function chooseParentOrgPosition() {
		$.ajax({
		url : '${vix}/oa/areaExpensesStandardsAction!goChoosePosition.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择岗位",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						if (result[0] == $("#entityId").val()) {
							showErrorMessage("不允许引用岗位为父岗位!");
							setTimeout("", 1000);
							return false;
						} else {
							$("#parentPosId").val(result[0]);
							$("#parentPosName").val(result[1]);
						}
					} else {
						$("#parentPosId").val("");
						$("#parentPosName").val("");
						showErrorMessage("请选择岗位信息!");
						setTimeout("", 1000);
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
</script>
<div class="content" style="padding-top: 10px; margin: 0;">
	<form id="currentExpenseDetailForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="currentExpenseDetailId" name="currentExpenseDetailId" value="%{currentExpenseDetail.id}" theme="simple" />
				<s:hidden id="travelExpenseId" name="travelExpenseId" value="%{currentExpenseDetail.travelExpense.id}" theme="simple" />
				<tr height="30">
					<td align="right">岗位:&nbsp;</td>
					<td><input type="hidden" id="parentPosId" name="parentPosId" value="${currentExpenseDetail.orgPosition.id}" /> <input type="text" id="parentPosName" name="parentPosName" readonly="readonly" value="${currentExpenseDetail.orgPosition.posName}" /> <input class="btn" type="button" value="选择" onclick="chooseParentOrgPosition();" /></td>
					<td align="right">所在区域:&nbsp;</td>
					<td><s:select id="areaLevelId" headerKey="-1" headerValue="--请选择--" list="%{areaLevelList}" listValue="name" listKey="id" value="%{currentExpenseDetail.areaLevel.id}" multiple="false" theme="simple" onchange="getReimbursementAmount();">
						</s:select></td>
				</tr>
				<tr height="30">
					<td align="right">报销标准:&nbsp;</td>
					<td><input id="expensesStandard" name="expensesStandard" value="${currentExpenseDetail.expensesStandard}" readonly="readonly" /></td>
					<td align="right">开支日期 :&nbsp;</td>
					<td><input id="expensesDate" name="expensesDate" value="${currentExpenseDetail.expensesDate}" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">月份:&nbsp;</td>
					<td><input id="expensesMonth" name="expensesMonth" value="${currentExpenseDetail.expensesMonth}" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM'})" /></td>
					<td align="right">报销金额:&nbsp;</td>
					<td><input id="expensesAmountDetail" name="expensesAmountDetail" value="${currentExpenseDetail.expensesAmountDetail}" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">发票张数 :&nbsp;</td>
					<td><input id="invoiceNumberDetail" name="invoiceNumberDetail" value="${currentExpenseDetail.invoiceNumberDetail}" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">备注:&nbsp;</td>
					<td><input id="currentExpenseDetailMemo" name="currentExpenseDetailMemo" value="${currentExpenseDetail.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
