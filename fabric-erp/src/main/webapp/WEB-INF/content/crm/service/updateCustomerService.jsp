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
function saveOrUpdateCustomerService(){
	if($('#customerServiceForm').validationEngine('validate')){
		$.post('${vix}/crm/service/customerServiceAction!saveOrUpdate.action',
			{'customerService.id':$("#customerServiceId").val(),
			  'customerService.subject':$("#subject").val(),
			  'customerService.startDate':$("#startDate").val(),
			  'customerService.startTimeStr':$("#startTimeStr").val(),
			  'customerService.consumeTime.id':$("#consumeTimeId").val(),
			  'customerService.serviceContent':$("#serviceContent").val(),
			  'customerService.customerFeedback':$("#customerFeedback").val(),
			  'customerService.memo':$("#memo").val(),
			  'customerService.isDeleted':$("#isDeleted").val(),
			  'customerService.serviceMode.id':$("#serviceModeId").val(),
			  'customerService.serviceType.id':$("#serviceTypeId").val(),
			  'customerService.customerAccount.id':$("#customerAccountId").val(),
			  'customerService.employee.id':$("#employeeId").val(),
			  'customerService.crmProject.id':$("#cpId").val(),
			  'customerService.customerServiceStatus.id':$("#customerServiceStatusId").val(),
			  'customerService.customerFeedback':$("#customerFeedback").val(),
			  'customerService.serviceContent':$("#serviceContent").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/crm/service/customerServiceAction!goList.action');
			}
		);
	}
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
								loadContactPerson();
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
								$("#employeeId").val(result[0].replace(",",""));
								$("#employeeName").val(result[1].replace(",",""));
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
function loadContactPerson(){
	$.ajax({
		  url:"${vix}/sales/order/salesOrderAction!loadCustomerContactPerson.action?id="+$("#customerServiceId").val()+"&customerAccountId="+$("#customerAccountId").val(),
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
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">客服管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/service/customerServiceAction!goList.action');">客服记录</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="customerServiceId" name="customerServiceId" value="${customerService.id}" />
<input type="hidden" id="cpId" name="cpId" value="${customerService.crmProject.id}" />
<input type="hidden" id="isDeleted" name="isDeleted" value="${customerService.isDeleted}" />
<input type="hidden" id="employeeId" value="${customerService.employee.id}" />
<input type="hidden" id="customerAccountId" value="${customerService.customerAccount.id}" />
<div class="content">
	<form id="customerServiceForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateCustomerService();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/service/customerServiceAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="customerService.id > 0">
							${customerService.subject}
						</s:if> <s:else>
							新增客服记录
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table class="addtable_c">
										<tr>
											<td align="right" width="15%">主题:</td>
											<td width="35%"><input id="subject" name="customerService.subject" value="${customerService.subject}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right" width="10%">客户:</td>
											<td width="40%"><input id="customerAccountName" name="customerService.customerAccount.name" value="${customerService.customerAccount.name}" type="text" readonly="readonly" size="30" /> <span><a class="abtn" href="###" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
										</tr>
										<tr>
											<td align="right">服务类型:</td>
											<td><s:select id="serviceTypeId" headerKey="" headerValue="--请选择--" list="%{serviceTypeList}" listValue="name" listKey="id" value="%{customerService.serviceType.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">服务方式:</td>
											<td><s:select id="serviceModeId" headerKey="" headerValue="--请选择--" list="%{serviceModeList}" listValue="name" listKey="id" value="%{customerService.serviceMode.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">开始日期:</td>
											<td><input id="startDate" name="customerService.startDate" value="<s:property value='formatDateToString(customerService.startDate)'/>" type="text" size="30" /><img onclick="showTime('startDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
										<tr>
											<td align="right">花费时间:</td>
											<td><s:select id="consumeTimeId" headerKey="" headerValue="--请选择--" list="%{consumeTimeList}" listValue="name" listKey="id" value="%{customerService.consumeTime.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">联系人:</td>
											<td><select id="contactPersonId"></select></td>
										</tr>
										<tr>
											<td align="right">状态:&nbsp;</td>
											<td><s:select id="customerServiceStatusId" headerKey="" headerValue="--请选择--" list="%{customerServiceStatusList}" listValue="name" listKey="id" value="%{customerService.customerServiceStatus.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">执行人:&nbsp;</td>
											<td><input id="employeeName" name="customerService.employee.name" value="${customerService.employee.name}" type="text" size="30" /> <span><a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></span></td>
										</tr>
										<tr>
											<td align="right">服务内容:&nbsp;</td>
											<td><textarea id="serviceContent" rows="4" cols="60" style="font-size: 12px;">${customerService.serviceContent}</textarea></td>
											<td align="right">客户反馈:&nbsp;</td>
											<td><textarea id="customerFeedback" rows="4" cols="60" style="font-size: 12px;">${customerService.customerFeedback}</textarea></td>
										</tr>
										<tr>
											<td align="right">备注:&nbsp;</td>
											<td colspan="3"><textarea id="memo" rows="3" cols="60" style="font-size: 12px;">${customerService.memo}</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--oder-->
	</form>
</div>
<!-- content -->
