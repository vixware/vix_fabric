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
 
$("#customerServiceForm").validationEngine();
if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}
 
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
								$("#customerCode").val(result[2]);
							}else{
								$("#customerAccountId").val("");
								$("#customerAccountName").val("");
								$("#customerCode").val("");
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
								$("#employeeId").val(result[0]);
								$("#employeeName").val(result[1]);
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
//-->
</script>
<input type="hidden" id="customerServiceId" name="customerServiceId" value="${customerService.id}" />
<input type="hidden" id="cpId" name="cpId" value="${customerService.crmProject.id}" />
<input type="hidden" id="employeeId" value="${customerService.employee.id}" />
<input type="hidden" id="customerAccountId" value="${customerService.customerAccount.id}" />
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="customerServiceForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">主题:</td>
					<td><input id="subject" name="customerService.subject" value="${customerService.subject}" type="text" /></td>
					<td align="right">客户:</td>
					<td><input id="customerAccountName" name="customerService.customerAccount.name" value="${customerService.customerAccount.name}" type="text" /> <span><a class="abtn" href="###" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
				</tr>
				<tr height="30">
					<td align="right">服务类型:</td>
					<td><s:select id="serviceTypeId" headerKey="-1" headerValue="--请选择--" list="%{serviceTypeList}" listValue="name" listKey="id" value="%{customerService.serviceType.id}" multiple="false" theme="simple"></s:select></td>
					<td align="right">服务方式:</td>
					<td><s:select id="serviceModeId" headerKey="-1" headerValue="--请选择--" list="%{serviceModeList}" listValue="name" listKey="id" value="%{customerService.serviceMode.id}" multiple="false" theme="simple"></s:select></td>
				</tr>
				<tr height="30">
					<td align="right">开始日期:</td>
					<td><input id="startDate" name="customerService.startDate" value="${customerService.startDate}" type="text" /><img onclick="showTime('startDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">开始时间:</td>
					<td><select id="startTimeStr">
							<option value=""></option>
							<s:iterator value="startTimeList" var="st">
								<s:if test="#st == customerService.startTimeStr">
									<option value="${st}" selected="selected">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
								</s:if>
								<s:else>
									<option value="${st}">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
								</s:else>
							</s:iterator>
					</select></td>
				</tr>
				<tr height="30">
					<td align="right">花费时间:</td>
					<td><s:select id="consumeTimeId" headerKey="-1" headerValue="--请选择--" list="%{consumeTimeList}" listValue="name" listKey="id" value="%{customerService.consumeTime.id}" multiple="false" theme="simple"></s:select></td>
					<td align="right">联系人:</td>
					<td><input id="contactPerson" name="customerService.contactPerson" value="${customerService.contactPerson}" type="text" /></td>
				</tr>
				<tr height="30">
					<td align="right">状态:&nbsp;</td>
					<td><s:select id="customerServiceStatusId" headerKey="-1" headerValue="--请选择--" list="%{customerServiceStatusList}" listValue="name" listKey="id" value="%{customerService.customerServiceStatus.id}" multiple="false" theme="simple"></s:select></td>
					<td align="right">执行人:&nbsp;</td>
					<td><input id="employeeName" name="customerService.employee.name" value="${customerService.employee.name}" type="text" /> <span class="abtn"><a href="#" onclick="chooseEmployee();"><span>选择</span></a></span></td>
				</tr>
				<tr height="30">
					<td align="right">服务内容:&nbsp;</td>
					<td><textarea id="serviceContent" rows="4" cols="40" style="font-size: 12px;">${customerService.serviceContent}</textarea></td>
					<td align="right">客户反馈:&nbsp;</td>
					<td><textarea id="customerFeedback" rows="4" cols="40" style="font-size: 12px;">${customerService.customerFeedback}</textarea></td>
				</tr>
				<tr height="30">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><textarea id="memo" rows="3" cols="60" style="font-size: 12px;">${customerService.memo}</textarea></td>
				</tr>
			</table>
		</div>
		<!--oder-->
	</form>
</div>
<!-- content -->
