<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#newEnable").val('${supplierAccount.enable}');
});
$("#supplierAccount").validationEngine();
/** 选择单员工 */
function chooseEmployee(){
	$.ajax({
		  url:'${vix}/srm/managementBusinessPartnerAction!goChooseRadioEmployee.action',
		  cache: false,
		  success: function(html){
			  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
			  $(".ab_c .content").css("margin-bottom","0");
			  $('.ab_c .content').parent().css('position','relative');
			  asyncbox.open({
				 	modal:true,
					width : 1000,
					height : 500,
					title:"选择职员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var rv = returnValue.split(","); 
								$("#newEId").val(rv[0]);
								$("#newEName").val(rv[1]);
							}else{
								asyncbox.success("请选择职员!","提示信息");
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
	<form id="supplierAccount">
		<s:hidden id="newId" value="%{supplierAccount.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工:&nbsp;</th>
					<td colspan="3"><input type="text" id="newEName" value="${supplierAccount.employee.name}" class="validate[required] text-input" readonly="readonly" style="background-color: #C0C0C0;" size="50" /> <input type="hidden" id="newEId" value="${supplierAccount.employee.id}" /> <span style="color: red;">*</span> <input type="button" class="btn"
						value="选择" onclick="chooseEmployee();" /></td>
				</tr>
				<tr height="40">
					<th align="right">帐号:&nbsp;</th>
					<td><input id="newAccount" value="${supplierAccount.account}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">密码:&nbsp;</th>
					<td><input id="newPassword" value="${supplierAccount.password}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">启用日期:&nbsp;</th>
					<td><input id="newStartTime" value="<fmt:formatDate value='${supplierAccount.startTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('newStartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">停用日期:&nbsp;</th>
					<td><input id="newEndTime" value="<fmt:formatDate value='${supplierAccount.endTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('newEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">激活:&nbsp;</th>
					<td><select id="newEnable" class="validate[required] text-input">
							<option>请选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>