<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#transportCostsForm").validationEngine();
	/**
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
	function getReimbursementAmount() {
		$.ajax({
		url : '${vix}/oa/transportCostsAction!getReimbursementAmount.action?areaLevelId=' + $("#areaLevelId").val() + "&transportId=" + $("#transportId").val(),
		cache : false,
		success : function(json) {
			$("#reimbursementAmount").val(json);
		}
		});
	}
</script>
<div class="content" style="padding-top: 10px; margin: 0;">
	<form id="transportCostsForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="transportCostsId" name="transportCostsId" value="%{transportCosts.id}" theme="simple" />
				<s:hidden id="priceType" name="priceType" value="%{priceType}" theme="simple" />
				<s:hidden id="travelExpenseId" name="travelExpenseId" value="%{transportCosts.travelExpense.id}" theme="simple" />
				<tr height="30">
					<td align="right">所在岗位:&nbsp;</td>
					<td><input type="hidden" id="parentPosId" name="parentPosId" value="${transportCosts.orgPosition.id}" /> <input type="text" id="parentPosName" name="parentPosName" readonly="readonly" value="${transportCosts.orgPosition.posName}" /> <input class="btn" type="button" value="选择" onclick="chooseParentOrgPosition();" /></td>
					<td align="right">出差区域:&nbsp;</td>
					<td><s:select id="areaLevelId" headerKey="-1" headerValue="--请选择--" list="%{areaLevelList}" listValue="name" listKey="id" value="%{transportCosts.areaLevel.id}" multiple="false" theme="simple">
						</s:select></td>
				</tr>
				<tr height="30">
					<td align="right">交通工具:&nbsp;</td>
					<td><s:select id="transportId" headerKey="-1" headerValue="--请选择--" list="%{transportList}" listValue="name" listKey="id" value="%{transportCosts.transport.id}" multiple="false" theme="simple" onchange="getReimbursementAmount();">
						</s:select></td>
					<td align="right">报销标准:&nbsp;</td>
					<td><input id="reimbursementAmount" name="reimbursementAmount" value="${transportCosts.reimbursementAmount}" readonly="readonly" /></td>
				</tr>
				<tr height="30">
					<td align="right">出发地:&nbsp;</td>
					<td><input id="fromPlace" name="fromPlace" value="${transportCosts.fromPlace}" /></td>
					<td align="right">目的地:&nbsp;</td>
					<td><input id="toPlace" name="toPlace" value="${transportCosts.toPlace}" /></td>
				</tr>
				<tr height="30">
					<td align="right">乘坐时间:&nbsp;</td>
					<td><input id="usingTime" name="usingTime" value="<s:date name="%{transportCosts.usingTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" class="validate[required] text-input" /><img onclick="showTime('usingTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
						style="color: red;">*</span></td>
					<td align="right">费用:&nbsp;</td>
					<td><input id="transportCost" name="transportCost" value="${transportCosts.cost}" /></td>
				</tr>
				<tr>
					<th align="right">备注：</th>
					<td colspan="3"><textarea id="memo" name="memo" style="width: 525px; height: 100px; margin-top: 5px;">${transportCosts.memo }</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
