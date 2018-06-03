<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 5px;">
	<form id="actionHistoryForm">
		<div class="order">
			<dl>
				<dt>
					<strong> <b> <s:if test="actionHistory.id > 0">
								${actionHistory.subject}
							</s:if> <s:else>
								新增行动历史
							</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<s:hidden id="actionHistoryId" name="actionHistory.id" value="%{actionHistory.id}" theme="simple" />
						<table>
							<tr height="30">
								<td align="right">主题:&nbsp;</td>
								<td><input type="text" id="subject" name="actionHistory.subject" value="${actionHistory.subject}" /></td>
								<td align="right">客户:&nbsp;</td>
								<td><span id="customerAccountName">${actionHistory.customerAccount.name}</span> <span class="abtn"><a href="###" onclick="chooseCustomer();"><span>选择</span></a></span></td>
							</tr>
							<tr height="30">
								<td align="right">行动日期:&nbsp;</td>
								<td><input type="text" id="actionDate" name="actionHistory.startTime" value="${actionHistory.actionDate}" /> <img onclick="showTime('actionDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								<td align="right">描述:&nbsp;</td>
								<td><input type="text" id="description" name="actionHistory.description" value="${actionHistory.description}" /></td>
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
	$("#actionHistoryForm").validationEngine();
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