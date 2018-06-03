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
function saveOrUpdateCustomerComplaint(){
	if($('#customerComplaintForm').validationEngine('validate')){
		$.post('${vix}/crm/service/customerComplaintAction!saveOrUpdate.action',
			{'customerComplaint.id':$("#customerComplaintId").val(),
			  'customerComplaint.subject':$("#subject").val(),
			  'customerComplaint.description':$("#description").val(),
			  'customerComplaint.complaintDate':$("#complaintDate").val(),
			  'customerComplaint.complaintTime':$("#complaintTime").val(),
			  'customerComplaint.customerAccount.id':$("#customerAccountId").val(),
			  'customerComplaint.contactPerson.id':$("#contactPersonId").val(),
			  'customerComplaint.employee.id':$("#employeeId").val(),
			  'customerComplaint.employee.name':$("#employeeName").val(),
			  'customerComplaint.crmProject.id':$("#cpId").val(),
			  'customerComplaint.complaintType.id':$("#complaintTypeId").val(),
			  'customerComplaint.dealResult.id':$("#dealResultId").val(),
			  'customerComplaint.consumeTime.id':$("#consumeTimeId").val(),
			  'customerComplaint.emergencyLevelType.id':$("#emergencyLevelTypeId").val(),
			  'customerComplaint.dealProcess':$("#dealProcess").val(),
			  'customerComplaint.customerFeedback':$("#customerFeedback").val(),
			  'customerComplaint.visitConfirmed':$("#visitConfirmed").val(),
			  'customerComplaint.isDeleted':$("#isDeleted").val(),
			  'customerComplaint.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/crm/service/customerComplaintAction!goList.action');
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
		  url:"${vix}/crm/service/customerComplaintAction!loadCustomerContactPerson.action?id="+$("#customerComplaintId").val()+"&customerAccountId="+$("#customerAccountId").val(),
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
				<li><a href="#"><img src="${vix}/common/img/crm/customerComplaint.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">客户管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/service/customerComplaintAction!goList.action');">投诉管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="customerComplaintId" name="customerComplaintId" value="${customerComplaint.id}" />
<input type="hidden" id="cpId" value="${customerComplaint.crmProject.id}" />
<input type="hidden" id="isDeleted" value="${customerComplaint.isDeleted}" />
<input type="hidden" id="employeeId" value="${customerComplaint.employee.id}" />
<input type="hidden" id="customerAccountId" value="${customerComplaint.customerAccount.id}" />
<div class="content">
	<form id="customerComplaintForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateCustomerComplaint();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/service/customerComplaintAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="customerComplaint.id > 0">
							${customerComplaint.subject}
						</s:if> <s:else>
							新增投诉
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
											<td align="right" width="15%">客户:</td>
											<td width="35%"><input id="customerAccountName" name="customerComplaint.customerAccount.name" readonly="readonly" value="${customerComplaint.customerAccount.name}" type="text" size="30" /> <span><a class="abtn" href="###" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
											<td align="right">联系人:</td>
											<td><select id="contactPersonId"></select></td>
										</tr>
										<tr>
											<td align="right">主题:</td>
											<td><input id="subject" name="customerComplaint.subject" value="${customerComplaint.subject}" type="text" size="30" /></td>
											<td align="right">描述:</td>
											<td><input id="description" name="customerComplaint.description" value="${customerComplaint.description}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">接待人:</td>
											<td><input id="employeeName" name="customerComplaint.employee.name" readonly="readonly" value="${customerComplaint.employee.name}" type="text" size="30" /> <span><a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></span></td>
											<td align="right">投诉分类:</td>
											<td><s:select id="complaintTypeId" headerKey="" headerValue="--请选择--" list="%{complaintTypeList}" listValue="name" listKey="id" value="%{customerComplaint.complaintType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">处理结果:</td>
											<td><s:select id="dealResultId" headerKey="" headerValue="--请选择--" list="%{dealResultList}" listValue="name" listKey="id" value="%{customerComplaint.dealResult.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">花费时间:</td>
											<td><s:select id="consumeTimeId" headerKey="" headerValue="--请选择--" list="%{consumeTimeList}" listValue="name" listKey="id" value="%{customerComplaint.consumeTime.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">投诉日期:</td>
											<td><input id="complaintDate" name="customerComplaint.complaintDate" value="<s:property value='formatDateToString(customerComplaint.complaintDate)'/>" type="text" size="30" /><img onclick="showTime('complaintDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">投诉时间:</td>
											<td><select id="complaintTime">
													<option value=""></option>
													<s:iterator value="complaintTimeList" var="st">
														<s:if test="#st == customerComplaint.complaintTime">
															<option value="${st}" selected="selected">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
														</s:if>
														<s:else>
															<option value="${st}">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
														</s:else>
													</s:iterator>
											</select></td>
										</tr>
										<tr>
											<td align="right">回访确认:</td>
											<td><input id="visitConfirmed" name="customerComplaint.visitConfirmed" value="${customerComplaint.visitConfirmed}" type="text" size="30" /></td>
											<td align="right">紧急程度:</td>
											<td><s:select id="emergencyLevelTypeId" headerKey="-1" headerValue="--请选择--" list="%{emergencyLevelTypeList}" listValue="name" listKey="id" value="%{customerComplaint.emergencyLevelType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">处理过程:</td>
											<td><textarea id="dealProcess" rows="3" cols="60" style="font-size: 12px;">${customerComplaint.dealProcess}</textarea></td>
											<td align="right">客户反馈:</td>
											<td><textarea id="customerFeedback" rows="3" cols="60" style="font-size: 12px;">${customerComplaint.customerFeedback}</textarea></td>
										</tr>

										<tr>
											<td align="right">备注:</td>
											<td colspan="3"><textarea id="memo" rows="3" cols="60" style="font-size: 12px;">${customerComplaint.memo}</textarea></td>
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
