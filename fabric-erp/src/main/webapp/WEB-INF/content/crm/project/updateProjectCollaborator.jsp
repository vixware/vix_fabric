<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 5px;">
	<form id="projectCollaboratorForm">
		<div class="order">
			<dl>
				<dt>
					<strong> <b> <s:if test="projectCollaborator.id > 0">
								${projectCollaborator.customerAccount.name}
							</s:if> <s:else>
								新增项目协作方
							</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<s:hidden id="projectCollaboratorId" name="projectCollaborator.id" value="%{projectCollaborator.id}" theme="simple" />
						<s:hidden id="customerAccountId" name="projectCollaborator.customerAccount.id" value="%{projectCollaborator.customerAccount.id}" theme="simple" />
						<table>
							<tr height="30">
								<td align="right">客户:&nbsp;</td>
								<td><span id="customerAccountName">${projectCollaborator.customerAccount.name}</span> <span class="abtn"><a href="###" onclick="chooseCustomer();"><span>选择</span></a></span></td>
							</tr>
							<tr height="30">
								<td align="right">分类:&nbsp;</td>
								<td><s:select id="collaboratorTypeId" headerKey="-1" headerValue="--请选择--" list="%{collaboratorTypeList}" listValue="name" listKey="id" value="%{projectCollaborator.collaboratorType.id}" multiple="false" theme="simple"></s:select></td>
							</tr>
							<tr height="30">
								<td align="right">备注:&nbsp;</td>
								<td colspan="3"><textarea id="memo" rows="3" cols="40" style="font-size: 12px;">${projectCollaborator.memo}</textarea></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
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
	$("#projectCollaboratorForm").validationEngine();
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
									$("#customerAccountName").html(result[1]);
									$("#customerCode").val(result[2]);
								}else{
									$("#customerAccountId").val("");
									$("#customerAccountName").html("");
									$("#customerCode").val("");
									asyncbox.success("<s:text name='pleaseChooseCustomerAccount'/>","<s:text name='vix_message'/>");
								}
							}else{
								$("#customerAccountId").val("");
								$("#customerAccountName").html("");
								asyncbox.success("请选择客户!","<s:text name='vix_message'/>");
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
</script>