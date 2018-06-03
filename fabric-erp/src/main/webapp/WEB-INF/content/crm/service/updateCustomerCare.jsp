<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
 
$("#customerCareForm").validationEngine();

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
								$("#customerAccountName").val(result[1]);
								$("#customerAccountCode").val(result[2]);
								loadContactPerson();
							}else{
								$("#customerAccountId").val("");
								$("#customerAccountName").val("");
								$("#customerAccountCode").val("");
								asyncbox.success("<s:text name='pleaseChooseCustomerAccount'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseEmployee(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择职员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#employeeId").val(result[0].replace(',',''));
								$("#employeeName").val(result[1].replace(',',''));
							}else{
								$("#employeeId").val("");
								$("#employeeName").val("");
								asyncbox.success("<s:text name='pleaseChooseEmployee'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseCrmProject(){
	$.ajax({
		  url:'${vix}/crm/project/crmProjectAction!goChooseCrmProject.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 960,
					height : 500,
					title:"选择项目",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#crmProjectId").val(result[0]);
								$("#crmProjectName").val(result[1]);
							}else{
								$("#crmProjectId").val("");
								$("#crmProjectName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function loadContactPerson(){
	$.ajax({
		  url:"${vix}/crm/service/customerCareAction!loadCustomerContactPerson.action?id="+$("#id").val()+"&customerAccountId="+$("#customerAccountId").val(),
		  cache: false,
		  success: function(html){
			  $("#contactPersonId").html(html);
		  }
	});
}
loadContactPerson();
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
//-->
</script>
<input type="hidden" id="id" name="id" value="${customerCare.id}" />
<input type="hidden" id="isDeleted" name="isDeleted" value="${customerCare.isDeleted}" />
<input type="hidden" id="employeeId" value="${customerCare.employee.id}" />
<input type="hidden" id="customerAccountId" value="${customerCare.customerAccount.id}" />
<input type="hidden" id="crmProjectId" value="${customerCare.crmProject.id}" />
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="customerCareForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">主题:</td>
					<td colspan="3"><input id="subject" name="customerCare.subject" value="${customerCare.subject}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right" width="15%">项目:&nbsp;</td>
					<td width="35%"><input type="text" id="crmProjectName" name="projectSolution.crmProject.subject" value="${customerCare.crmProject.subject}" readonly="readonly" /> <span class="abtn"><a href="###" onclick="chooseCrmProject();"><span>选择</span></a></span></td>
					<td align="right" width="10%">执行人:&nbsp;</td>
					<td width="40%"><input id="employeeName" name="customerCare.employee.name" value="${customerCare.employee.name}" type="text" /> <span><a class="abtn" href="###" onclick="chooseEmployee();"><span>选择</span></a></span></td>
				</tr>
				<tr height="30">
					<td align="right">客户:</td>
					<td><input id="customerAccountName" name="customerCare.customerAccount.name" value="${customerCare.customerAccount.name}" type="text" /> <span><a class="abtn" href="###" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
					<td align="right">联系人:</td>
					<td><select id="contactPersonId"></select></td>
				</tr>
				<tr>
					<td align="right">关怀内容:</td>
					<td><textarea id="careContent" rows="3" cols="40" style="font-size: 12px;">${customerCare.careContent}</textarea></td>
					<td align="right">客户反馈:</td>
					<td><textarea id="customerFeedback" rows="3" cols="40" style="font-size: 12px;">${customerCare.customerFeedback}</textarea></td>
				</tr>
				<tr>
					<td align="right">备注:</td>
					<td colspan="3"><textarea id="memo" rows="3" cols="60" style="font-size: 12px;">${customerCare.memo}</textarea></td>
				</tr>
			</table>
		</div>
		<!--oder-->
	</form>
</div>
<!-- content -->
