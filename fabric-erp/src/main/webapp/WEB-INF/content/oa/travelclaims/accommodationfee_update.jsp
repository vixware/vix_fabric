<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#accommodationFeeForm").validationEngine();
	function getReimbursementAmount() {
		$.ajax({
		url : '${vix}/oa/accommodationFeeAction!getReimbursementAmount.action?areaLevelId=' + $("#areaLevelId").val(),
		cache : false,
		success : function(json) {
			$("#lodgingFee").val(json);
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
	<form id="accommodationFeeForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="accommodationFeeId" name="accommodationFeeId" value="%{accommodationFee.id}" theme="simple" />
				<s:hidden id="travelExpenseId" name="travelExpenseId" value="%{transportCosts.travelExpense.id}" theme="simple" />
				<s:hidden id="priceType" name="priceType" value="%{priceType}" theme="simple" />
				<tr height="30">
					<td align="right">岗位:&nbsp;</td>
					<td><input type="hidden" id="parentPosId" name="parentPosId" value="${accommodationFee.orgPosition.id}" /> <input type="text" id="parentPosName" name="parentPosName" readonly="readonly" value="${accommodationFee.orgPosition.posName}" /> <input class="btn" type="button" value="选择" onclick="chooseParentOrgPosition();" /></td>
					<td align="right">出差区域:&nbsp;</td>
					<td><s:select id="areaLevelId" headerKey="-1" headerValue="--请选择--" list="%{areaLevelList}" listValue="name" listKey="id" value="%{currentExpenseDetail.areaLevel.id}" multiple="false" theme="simple" onchange="getReimbursementAmount();">
						</s:select></td>
				</tr>
				<tr height="30">
					<td align="right">住宿标准:&nbsp;</td>
					<td><input id="lodgingFee" name="lodgingFee" value="${accommodationFee.lodgingFee}" readonly="readonly" /></td>
					<td align="right">住宿地点:&nbsp;</td>
					<td><input id="accomodationsPlaces" name="accomodationsPlaces" value="${accommodationFee.accomodationsPlaces}" /></td>
				</tr>
				<tr height="30">
					<td align="right">房价/天:&nbsp;</td>
					<td><input id="housePrices" name="housePrices" value="${accommodationFee.housePrices}" /></td>
					<td align="right">天数:&nbsp;</td>
					<td><input id="days" name="days" value="${accommodationFee.days}" /></td>
				</tr>
				<tr height="30">
					<td align="right">开始时间:&nbsp;</td>
					<td><input id="startAccomodationsTime" name="startAccomodationsTime" value="<s:date name="%{accommodationFee.startAccomodationsTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" class="validate[required] text-input" /><img onclick="showTime('startAccomodationsTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16"
						height="22" align="absmiddle"><span style="color: red;">*</span></td>
					<td align="right">结束时间:&nbsp;</td>
					<td><input id="endAccomodationsTime" name="endAccomodationsTime" value="<s:date name="%{accommodationFee.endAccomodationsTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" class="validate[required] text-input" /><img onclick="showTime('endAccomodationsTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16"
						height="22" align="absmiddle"><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">消费额:&nbsp;</td>
					<td><input id="accommodationCost" name="accommodationCost" value="${accommodationFee.cost}" /></td>
				</tr>
				<tr>
					<th align="right">备注：</th>
					<td colspan="3"><textarea id="memo" name="memo" style="width: 525px; height: 100px; margin-top: 5px;">${accommodationFee.memo }</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
