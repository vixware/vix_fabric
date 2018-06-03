<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	$("#collectBillForm").validationEngine();
	/** 选择销售订单 */
	function chooseSalesOrder() {
		$.ajax({
		url : '${vix}/drp/collectManagementAction!goSalesOrder.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 750,
			height : 450,
			title : "选择订单",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#salesOrderId").val(result[0]);
						$("#salesOrderCode").val(result[1]);
						$("#receivable").val(result[2]);
					} else {
						$("#salesOrderId").val("");
						$("#salesOrderCode").val("");
						$("#receivable").val("");
						asyncbox.success("请选择订单信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function chooseCustomerAccount() {
		$.ajax({
		url : '${vix}/drp/membershipCardmanagementAction!goChooseCustomerAccount.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择会员",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#customerAccountId").val(rv[0]);
						$("#customerAccountName").val(rv[1]);
					} else {
						asyncbox.success("请选择会员信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	function initdata() {
		if (document.getElementById("paymentTime").value == "") {
			var myDate = new Date();
			$("#paymentTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	}
	initdata();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="collectBillForm">
		<s:hidden id="collectBillId" name="collectBillId" value="%{collectBill.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">会员名称:&nbsp;</td>
					<td><input id="customerAccountName" name="customerAccountName" value="${collectBill.customerAccount.name }" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><input type="hidden" id="customerAccountId" name="customerAccountId" value="${collectBill.customerAccount.id}" /> <input class="btn" type="button"
						value="选择" onclick="chooseCustomerAccount();" /> <span style="color: red;">*</span></td>
					<td align="right">应收金额:&nbsp;</td>
					<td><input type="text" id="receivable" name="receivable" class="validate[required] text-input" value="${collectBill.receivable}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>

				</tr>
				<tr height="40">
					<td align="right">订单编码:&nbsp;</td>
					<td><input type="text" id="salesOrderCode" name="salesOrderCode" value="${collectBill.salesOrder.outerId}" readonly="readonly" onchange="fieldChanged(this);" /> <s:hidden id="salesOrderId" name="salesOrderId" value="%{collectBill.salesOrder.id}" theme="simple" /><input class="btn" type="button" value="选择" onclick="chooseSalesOrder();" /></td>
					<td align="right">回款金额:&nbsp;</td>
					<td><input type="text" id="payment" name="payment" class="validate[required] text-input" value="${collectBill.payment}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">日期：</td>
					<td><input id="paymentTime" name="paymentTime" onchange="fieldChanged(this);" value="<s:date format="yyyy-MM-dd" name="%{#collectBill.paymentTime}" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('paymentTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>