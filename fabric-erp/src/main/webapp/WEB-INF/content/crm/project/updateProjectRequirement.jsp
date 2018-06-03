<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="projectRequirementForm">
		<div class="box order_table" style="line-height: normal;">
			<s:hidden id="projectRequirementId" name="projectRequirement.id" value="%{projectRequirement.id}" theme="simple" />
			<s:hidden id="customerAccountId" name="projectRequirement.customerAccount.id" value="%{projectRequirement.customerAccount.id}" theme="simple" />
			<s:hidden id="providerId" name="projectRequirement.provider.id" value="%{projectRequirement.provider.id}" theme="simple" />
			<s:hidden id="crmProjectId" name="projectRequirement.crmProject.id" value="%{projectRequirement.crmProject.id}" theme="simple" />
			<table class="table-padding020">
				<tr height="40">
					<td align="right">主题:&nbsp;</td>
					<td><input type="text" id="subject" name="projectRequirement.subject" value="${projectRequirement.subject}" /></td>
					<td>记录日期</td>
					<td><input type="text" id="recordDate" name="projectRequirement.recordDate" value="<s:property value='formatDateToString(projectRequirement.recordDate)'/>" /> <img onclick="showTime('recordDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td align="right">客户:&nbsp;</td>
					<td><input type="text" id="customerAccountName" name="projectRequirement.customerAccount.name" value="${projectRequirement.customerAccount.name}" readonly="readonly" /> <span class="abtn"><a href="###" onclick="chooseCustomer();"><span>选择</span></a></span></td>
					<td align="right">项目:&nbsp;</td>
					<td><input type="text" id="crmProjectName" name="projectRequirement.crmProject.subject" value="${projectRequirement.crmProject.subject}" readonly="readonly" /> <span class="abtn"><a href="###" onclick="chooseCrmProject();"><span>选择</span></a></span></td>
				</tr>
				<tr height="40">
					<td align="right">提供人:&nbsp;</td>
					<td><input type="text" id="providerName" name="projectRequirement.provider.name" value="${projectRequirement.provider.name}" readonly="readonly" /> <span class="abtn"><a href="###" onclick="chooseEmployee();"><span>选择</span></a></span></td>
				</tr>
				<tr height="40">
					<td align="right">需求描述:&nbsp;</td>
					<td colspan="3"><textarea id="description" rows="5" cols="70" style="font-size: 12px;">${projectRequirement.description}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
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
	$("#projectRequirementForm").validationEngine();
	function chooseCustomer(){
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
									$("#customerCode").val(result[2]);
								}else{
									$("#customerAccountId").val("");
									$("#customerAccountName").val("");
									$("#customerCode").val("");
									asyncbox.success("<s:text name='pleaseChooseCustomerAccount'/>","<s:text name='vix_message'/>");
								}
							}else{
								$("#customerAccountId").val("");
								$("#customerAccountName").val("");
								asyncbox.success("请选择客户!","<s:text name='vix_message'/>");
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
									$("#providerId").val(result[0].replace(",",""));
									$("#providerName").val(result[1].replace(",",""));
								}else{
									$("#providerId").val("");
									$("#providerName").val("");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
</script>