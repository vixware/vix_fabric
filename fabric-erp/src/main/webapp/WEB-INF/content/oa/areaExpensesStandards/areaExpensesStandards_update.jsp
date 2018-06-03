<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#areaExpensesStandardsForm").validationEngine();
	function chooseEmployees() {
		$.ajax({
		url : '${vix}/common/select/commonSelectEmpAction!goList.action',
		data : {
			chkStyle : "checkbox"
		},
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 800,
			height : 600,
			title : "选择人员",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var selectIds = "";
						var selectNames = "";
						var result = returnValue.split(",");
						for (var i = 0; i < result.length; i++) {
							if (result[i].length > 1) {
								var v = result[i].split(":");
								selectIds += "," + v[0];
								selectNames = v[1];
								$("#employeeId").val(v[0]);
								$("#employeeName").val(v[1]);
							}
						}
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
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
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="areaExpensesStandardsForm">
		<s:hidden id="areaExpensesStandardsId" name="areaExpensesStandardsId" value="%{areaExpensesStandards.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="code" name="code" class="validate[required] text-input" value="${areaExpensesStandards.code}" style="width: 175px;" /><span style="color: red;">*</span></td>
					<td align="right">主题:&nbsp;</td>
					<td><input type="text" id="standardName" name="standardName" class="validate[required] text-input" value="${areaExpensesStandards.standardName}" style="width: 175px;" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">岗位:&nbsp;</td>
					<td><input type="hidden" id="parentPosId" name="parentPosId" value="${areaExpensesStandards.orgPosition.id}" /> <input type="text" id="parentPosName" name="parentPosName" readonly="readonly" value="${areaExpensesStandards.orgPosition.posName}" style="width: 175px;" /> <input class="btn" type="button" value="选择"
						onclick="chooseParentOrgPosition();" /></td>
					<td align="right">出差区域:&nbsp;</td>
					<td><s:select id="areaLevelId" headerKey="-1" headerValue="--请选择--" list="%{areaLevelList}" listValue="name" listKey="id" value="%{areaExpensesStandards.areaLevel.id}" multiple="false" theme="simple">
						</s:select></td>
				</tr>
				<tr height="40">
					<td align="right">交通工具:&nbsp;</td>
					<td><s:select id="transportId" headerKey="-1" headerValue="--请选择--" list="%{transportList}" listValue="name" listKey="id" value="%{areaExpensesStandards.transport.id}" multiple="false" theme="simple">
						</s:select></td>
					<td align="right">报销标准:&nbsp;</td>
					<td><input type="text" id="reimbursementAmount" name="reimbursementAmount" class="validate[required] text-input" value="${areaExpensesStandards.reimbursementAmount}" style="width: 175px;" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>