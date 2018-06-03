<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#noticeForm").validationEngine();
	function chooseBizEmp() {
		$.ajax({
		url : '${vix}/common/select/commonSelectEmpAction!goList.action',
		data : {
			chkStyle : "checkbox"
		},
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择人员",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var selectIds = "";
						var selectNames = "";
						var pubIdTmp = $("#employeeId").val();
						pubIdTmp = pubIdTmp + ",";
						var result = returnValue.split(",");
						for (var i = 0; i < result.length; i++) {
							if (result[i].length > 1) {
								var v = result[i].split(":");
								if (!pubIdTmp.contains(v[0] + ",")) {
									selectIds += "," + v[0];
									selectNames += "," + v[1];
								}
							}
						}
						selectIds = $("#employeeId").val() + selectIds;
						selectNames = $("#creator").val() + selectNames;
						$("#employeeId").val(selectIds);
						selectNames = selectNames.substring(1, selectNames.length);
						$("#creator").val(selectNames);
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
	<form id="noticeForm">
		<input type="hidden" id="noticeId" name="noticeId" value="${notice.id}" /> <input type="hidden" id="warningTypeId" name="warningTypeId" value="${notice.warningType.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">任务源：</td>
					<td><input id="timedTaskName" name="timedTaskName" type="text" size="20" value="${notice.warningType.typeName}" /></td>
					<%-- <td align="right">提醒人：</td>
					<td><input id="creator" name="creator" type="text" size="20" value="${timedTask.creator}" /> <input type="hidden" id="employeeId" name="employeeId" /><input class="btn" type="button"
						value="选择" onclick="chooseBizEmp();" /></td> --%>
				</tr>
				<tr height="40">
					<td align="right">提醒内容：</td>
					<td><input id="noticecontent" name="noticecontent" type="text" size="20" value="${notice.content}" /></td>
					<td align="right">提醒时间：</td>
					<td><input id="remindTime" name="remindTime" value="<s:date name="%{notice.remindTime}" format="yyyy-MM-dd"/>" type="text" /> <img onclick="showTime('remindTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="middle"></td>
				</tr>
				<tr height="40">
					<td align="right">预警类型：</td>
					<td><select id="type" name="type">
							<option value="">请选择</option>
							<option value="1" selected="selected">预警通知</option>
							<option value="2">普通通知</option>
					</select></td>
					<td align="right">优先级：</td>
					<td><input id="priority" name="priority" type="text" size="20" value="${notice.priority}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>