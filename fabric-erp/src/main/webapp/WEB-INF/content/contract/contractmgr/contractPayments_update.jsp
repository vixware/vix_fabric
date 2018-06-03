<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
/* 付款人 */
function choosePaymentPeople(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"radio"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 600,
					title:"选择付款人",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										
										selectIds += "," + v[0];
										selectNames = v[1];
									}
								}
								$("#paymentPeople").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

/* 收款人 */
function chooseAprPerson(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 600,
					title:"选择收款人",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										
										selectIds += "," + v[0];
										selectNames = v[1];
									}
								}
								$("#aprPerson").val(selectNames);
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
	<form id="brandForm">
		<input type="hidden" id="id" name="id" value="${contractPaymentPlan.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">合同编码:&nbsp;</th>
					<td><input id="contractCode" name="contractPaymentPlan.contractCode" value="${contractPaymentPlan.contractCode}" data-text-tooltip="Tip tip tip tip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" class="validate[required] text-input" /></td>
					<th align="right">合同名称:&nbsp;</th>
					<td><input id="contractName" name="contractPaymentPlan.contractName" value="${contractPaymentPlan.contractName}" data-text-tooltip="Tip tip tip tip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">收款单位名称:&nbsp;</th>
					<td><input id="beneficiaryName" name="contractPaymentPlan.beneficiaryName" value="${contractPaymentPlan.beneficiaryName}" data-text-tooltip="Tip tip tip tip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" class="validate[required] text-input" /></td>
					<th align="right">收款单位账号:&nbsp;</th>
					<td><input id="beneficiaryAccount" name="contractPaymentPlan.beneficiaryAccount" value="${contractPaymentPlan.beneficiaryAccount}" data-text-tooltip="Tip tip tip tip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" class="validate[required] text-input" /></td>
				</tr>
				<tr>
					<th align="right">付款金额:&nbsp;</th>
					<td><s:textfield id="paymentAmount" name="contractPaymentPlan.paymentAmount" value="%{contractPaymentPlan.paymentAmount}" theme="simple" /></td>
					<th align="right">收款金额:&nbsp;</th>
					<td><s:textfield id="amount" name="contractPaymentPlan.amount" value="%{contractPaymentPlan.amount}" theme="simple" /></td>
				</tr>
				<tr>
					<th align="right">付款人:&nbsp;</th>
					<td><input id="paymentPeople" name="parentId" value="${contractPaymentPlan.paymentPeople}" type="text" size="20" class="validate[required] text-input" /> <input class="btn" type="button" value="选择" onclick="choosePaymentPeople();" /> <span style="color: red;">*</span></td>
					<th align="right">收款人:&nbsp;</th>
					<td><input id="aprPerson" name="parentId" value="${contractPaymentPlan.aprPerson}" type="text" size="20" class="validate[required] text-input" /> <input class="btn" type="button" value="选择" onclick="chooseAprPerson();" /> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<th>付款时间:&nbsp;</th>
					<td><input id="paymentTime" name="paymentTime" value="<fmt:formatDate value='${contractPaymentPlan.paymentTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" readonly="readonly" type="text" /> <img onclick="showTime('paymentTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
					</td>
					<th>收款时间:&nbsp;</th>
					<td><input id="aprTime" name="aprTime" value="<fmt:formatDate value='${contractPaymentPlan.aprTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" readonly="readonly" type="text" /> <img onclick="showTime('aprTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr>
					<th>状态:&nbsp;</th>
					<td><select id=status name="status" value="${contractPaymentPlan.status}" class="select-forms" style="height: 24px; line-height: 24px;">
							<option>支付完</option>
							<option>未完成</option>
					</select></td>
				</tr>
			</table>
		</div>
	</form>
</div>