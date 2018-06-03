<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="projectSolutionForm">
		<div class="box order_table" style="line-height: normal;">
			<s:hidden id="projectSolutionId" name="projectSolution.id" value="%{projectSolution.id}" theme="simple" />
			<s:hidden id="customerAccountId" name="projectSolution.customerAccount.id" value="%{projectSolution.customerAccount.id}" theme="simple" />
			<s:hidden id="crmProjectId" name="projectSolution.crmProject.id" value="%{projectSolution.crmProject.id}" theme="simple" />
			<table class="table-padding020">
				<tr height="30">
					<td align="right">客户:&nbsp;</td>
					<td><input type="text" id="customerAccountName" name="projectSolution.customerAccount.name" value="${projectSolution.customerAccount.name}" readonly="readonly" class="validate[required] text-input" /> <span style="color: red;">*</span> <span class="abtn"><a href="###" onclick="chooseCustomer();"><span>选择</span></a></span></td>
					<td align="right">项目:&nbsp;</td>
					<td><input type="text" id="crmProjectName" name="projectSolution.crmProject.subject" value="${projectSolution.crmProject.subject}" readonly="readonly" /> <span class="abtn"><a href="###" onclick="chooseCrmProject();"><span>选择</span></a></span></td>
				</tr>
				<tr height="30">
					<td align="right">主题:&nbsp;</td>
					<td><input type="text" id="subject" name="projectSolution.subject" value="${projectSolution.subject}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">记录日期:&nbsp;</td>
					<td><input type="text" id="submitDate" name="projectSolution.submitDate" value="<s:property value='formatDateToString(projectSolution.submitDate)'/>" /> <img onclick="showTime('submitDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td></td>
				</tr>
				<tr height="30">
					<td align="right">方案内容:&nbsp;</td>
					<td align="left" colspan="3"><textarea id="solutionContent" name="solutionContent">${projectSolution.solutionContent}</textarea></td>
				</tr>
				<tr height="30">
					<td></td>
				</tr>
				<tr height="30">
					<td align="right">客户反馈:&nbsp;</td>
					<td align="left" colspan="3"><textarea id="customerFeedback" name="customerFeedback">${projectSolution.customerFeedback}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
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
	$("#projectSolutionForm").validationEngine();
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
	
var editorSolutionContent = KindEditor.create('textarea[name="solutionContent"]',
	{basePath:'${vix}/plugin/KindEditor/',
		width:700,
		height:250,
		cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
		uploadJson : '${vix}/common/kindEditorAction!uploadFile.action',
		fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
		allowFileManager : true 
	}
 );
		
var editorCustomerFeedback = KindEditor.create('textarea[name="customerFeedback"]',
	{basePath:'${vix}/plugin/KindEditor/',
		width:700,
		height:250,
		cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
		uploadJson : '${vix}/common/kindEditorAction!uploadFile.action',
		fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
		allowFileManager : true 
	}
 );
</script>