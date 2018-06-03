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
 
	$("#riskLevelForm").validationEngine();
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
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	function chooseEmployee(text,id,name){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action?tag=choose',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 960,
						height : 500,
						title:text,
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var result = returnValue.split(":");
									$("#"+id).val(result[0]);
									$("#"+name).val(result[1]);
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
//-->
</script>
<input type="hidden" id="id" name="id" value="${riskLevel.id}" />
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="riskLevelForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<%-- <tr height="30">
					<td width="15%" align="right" >客户名称:</td>
					<td width="35%">
						<input id="customerName" name="riskLevel.customerAccount.name" value="${riskLevel.customerAccount.name}" type="text" readonly="readonly" class="validate[required] text-input"/><span style="color: red;">*</span>
						<input type="hidden" id="customerAccountId" name="customerAccountId" value="${riskLevel.customerAccount.id}"/>
						<span><a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span>	
					</td>
					<td width="15%" align="right" >信用经理:</td>
					<td width="35%">
						<input id="creditManagerName" name="riskLevel.creditManagerName" value="${riskLevel.creditManagerName}" type="text" readonly="readonly" class="validate[required] text-input"/><span style="color: red;">*</span>
						<input type="hidden" id="creditManagerCode" name="creditManagerCode" value="${riskLevel.creditManagerCode}"/>
						<span><a class="abtn" href="#" onclick="chooseEmployee('选择信用经理','creditManagerCode','creditManagerName');"><span>选择</span></a></span>	
					</td>
				</tr> --%>
				<tr height="30">
					<td align="right" width="15%">风险类型:</td>
					<td><input id="levelType" name="riskLevel.levelType" value="${riskLevel.levelType}" type="text" /></td>
					<td align="right">风险等级:</td>
					<td><input id="level" name="riskLevel.level" value="${riskLevel.level}" type="text" /></td>
				</tr>
				<tr height="30">
					<td align="right">是否控制:</td>
					<td><input id="isControl" name="riskLevel.isControl" value="${riskLevel.isControl}" type="text" /></td>
					<td align="right">控制方法:</td>
					<td><input id="controlMethod" name="riskLevel.controlMethod" value="${riskLevel.controlMethod}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
