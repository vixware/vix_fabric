<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseCustomerAccount(){
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goChooseCustomerAccount.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#customerAccountId").val(result[0]);
								$("#customerName").val(result[1]);
							}else{
								$("#customerAccountId").val("");
								$("#customerName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
$("#ccfcForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<form id="ccfcForm">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">客户:&nbsp;</td>
					<td><input id="customerName" name="salesOrder.customerAccount.name" value="" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId" value="" /> <span><a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
				</tr>
				<tr height="30">
					<td align="right">开始时间:&nbsp;</td>
					<td><input type="text" id="startTime" name="startTime" value="" onfocus="showTime('startTime','yyyy-MM-dd HH:mm')" class="validate[required] text-input" /> <span style="color: red;">*</span><img onclick="showTime('startTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">结束时间:&nbsp;</td>
					<td><input type="text" id="endTime" name="endTime" value="" onfocus="showTime('endTime','yyyy-MM-dd HH:mm')" class="validate[required] text-input" /> <span style="color: red;">*</span><img onclick="showTime('endTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</form>
	</div>
</div>