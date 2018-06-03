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
 
$("#customerComplaintForm").validationEngine();
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
<input type="hidden" id="customerComplaintId" name="customerComplaintId" value="${customerComplaint.id}" />
<input type="hidden" id="cpId" value="${customerComplaint.crmProject.id}" />
<input type="hidden" id="employeeId" value="${customerComplaint.employee.id}" />
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="customerComplaintForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">主题:</td>
					<td><input id="subject" name="customerComplaint.subject" value="${customerComplaint.subject}" type="text" /></td>
					<td align="right">联系人:</td>
					<td><input id="contactPersonId" name="customerComplaint.contactPerson.id" value="${customerComplaint.contactPerson.id}" type="text" /></td>
				</tr>
				<tr height="30">
					<td align="right">接待人:</td>
					<td><input id="employeeName" name="customerComplaint.employee.name" value="${customerComplaint.employee.name}" type="text" /> <span><a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></span></td>
					<td align="right">投诉分类:</td>
					<td><s:select id="complaintTypeId" headerKey="-1" headerValue="--请选择--" list="%{complaintTypeList}" listValue="name" listKey="id" value="%{customerComplaint.complaintType.id}" multiple="false" theme="simple"></s:select></td>
				</tr>
				<tr height="30">
					<td align="right">处理结果:</td>
					<td><s:select id="dealResultId" headerKey="-1" headerValue="--请选择--" list="%{dealResultList}" listValue="name" listKey="id" value="%{customerComplaint.dealResult.id}" multiple="false" theme="simple"></s:select></td>
					<td align="right">花费时间:</td>
					<td><s:select id="consumeTimeId" headerKey="-1" headerValue="--请选择--" list="%{consumeTimeList}" listValue="name" listKey="id" value="%{customerComplaint.consumeTime.id}" multiple="false" theme="simple"></s:select></td>
				</tr>
				<tr height="30">
					<td align="right">投诉日期:</td>
					<td><input id="complaintDate" name="customerComplaint.complaintDate" value="${customerComplaint.complaintDate}" type="text" /><img onclick="showTime('complaintDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">投诉时间:</td>
					<td><select id="complaintTime">
							<option value=""></option>
							<s:iterator value="complaintTimeList" var="st">
								<s:if test="#st == customerService.complaintTime">
									<option value="${st}" selected="selected">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
								</s:if>
								<s:else>
									<option value="${st}">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
								</s:else>
							</s:iterator>
					</select></td>
				</tr>
				<tr height="30">
					<td align="right">回访确认:</td>
					<td><input id="visitConfirmed" name="customerComplaint.visitConfirmed" value="${customerComplaint.visitConfirmed}" type="text" /></td>
					<td align="right">紧急程度:</td>
					<td><s:select id="emergencyLevelTypeId" headerKey="-1" headerValue="--请选择--" list="%{emergencyLevelTypeList}" listValue="name" listKey="id" value="%{customerComplaint.emergencyLevelType.id}" multiple="false" theme="simple"></s:select></td>
				</tr>
				<tr height="30">
					<td align="right">处理过程:</td>
					<td><textarea id="dealProcess" rows="3" cols="40" style="font-size: 12px;">${customerService.dealProcess}</textarea></td>
					<td align="right">客户反馈:</td>
					<td><textarea id="customerFeedback" rows="3" cols="40" style="font-size: 12px;">${customerService.customerFeedback}</textarea></td>
				</tr>
				<tr height="30">
					<td align="right">描述:</td>
					<td><input id="description" name="customerComplaint.description" value="${customerComplaint.description}" type="text" /></td>
					<td align="right">备注:</td>
					<td><textarea id="memo" rows="3" cols="60" style="font-size: 12px;">${customerService.memo}</textarea></td>
				</tr>
			</table>
		</div>
		<!--oder-->
	</form>
</div>
<!-- content -->
