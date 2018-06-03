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
 
$("#crmProjectForm").validationEngine();
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
function saveOrUpdateCrmProject(){
	if($('#crmProjectForm').validationEngine('validate')){
		$.post('${vix}/crm/project/crmProjectAction!saveOrUpdate.action',
			{'crmProject.id':$("#id").val(),
			  'crmProject.subject':$("#subject").val(),
			  'crmProject.projectStageMemo':$("#projectStageMemo").val(),
			  'crmProject.customerAccount.id':$("#customerAccountId").val(),
			  'crmProject.projectStatus.id':$("#projectStatusId").val(),
			  'crmProject.projectStage.id':$("#projectStageId").val(),
			  'crmProject.personInCharge.id':$("#employeeId").val(),
			  'crmProject.projectSalePreviousStage.id':$("#projectSalePreviousStageId").val(),
			  'crmProject.projectEstablishDate':$("#projectEstablishDate").val(),
			  'crmProject.projectSummary':$("#projectSummary").val(),
			  'crmProject.forecastSignDate':$("#forecastSignDate").val(),
			  'crmProject.forecastMoney':$("#forecastMoney").val(),
			  'crmProject.possibility':$("#possibility").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/crm/project/crmProjectAction!goList.action');
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
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
$(window).scroll(function(){
	if($('#orderTitleFixd').parent('dl').offset().top - 43 < $(window).scrollTop()){
		if(!$('#orderTitleFixd').hasClass('fixed')){
			$('#orderTitleFixd').addClass('fixed');
			$('#orderTitleFixd').parent('dl').css('padding-top',30);
		}
	}else{
		$('#orderTitleFixd').removeClass('fixed');
		$('#orderTitleFixd').parent('dl').css('padding-top',0);
	}
});
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
				<li><a href="#"><img src="${vix}/common/img/crm/project.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">项目管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/project/crmProjectAction!goList.action');">项目管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${crmProject.id}" />
<input type="hidden" id="employeeId" value="${crmProject.personInCharge.id}" />
<input type="hidden" id="customerAccountId" value="${crmProject.customerAccount.id}" />
<div class="content">
	<form id="crmProjectForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCrmProject();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/project/crmProjectAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="crmProject.id > 0">
							${crmProject.subject}
						</s:if> <s:else>
							新增项目
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
											<td align="right" width="15%">项目主题:</td>
											<td width="35%"><input id="subject" name="crmProject.subject" value="${crmProject.subject}" type="text" size="30" /></td>
											<td align="right" width="10%">客户:</td>
											<td width="40%"><input id="customerAccountName" name="crmProject.customerAccount.name" value="${crmProject.customerAccount.name}" type="text" readonly="readonly" size="30" /> <span><a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
										</tr>
										<tr>
											<td align="right">项目状态:</td>
											<td><s:select id="projectStatusId" headerKey="" headerValue="--请选择--" list="%{projectStatusList}" listValue="name" listKey="id" value="%{crmProject.projectStatus.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">项目阶段:</td>
											<td><s:select id="projectStageId" headerKey="" headerValue="--请选择--" list="%{projectStageList}" listValue="name" listKey="id" value="%{crmProject.projectStage.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">项目阶段备注:</td>
											<td><input id="projectStageMemo" name="crmProject.projectStageMemo" value="${crmProject.projectStageMemo}" type="text" size="30" /></td>
											<td align="right">售前阶段:</td>
											<td><s:select id="projectSalePreviousStageId" headerKey="" headerValue="--请选择--" list="%{projectSalePreviousStageList}" listValue="name" listKey="id" value="%{crmProject.projectSalePreviousStage.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">负责人:</td>
											<td><input id="employeeName" name="crmProject.personInCharge.name" value="${crmProject.personInCharge.name}" type="text" size="30" /> <span><a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></span></td>
											<td align="right">立项时间:</td>
											<td><input id="projectEstablishDate" name="crmProject.projectEstablishDate" value="${crmProject.projectEstablishDate}" type="text" size="30" /><img onclick="showTime('projectEstablishDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">预计金额:</td>
											<td><input id="forecastMoney" name="crmProject.forecastMoney" value="${crmProject.forecastMoney}" type="text" size="30" /></td>
											<td align="right">预计签单日期:</td>
											<td><input id="forecastSignDate" name="crmProject.forecastSignDate" value="${crmProject.forecastSignDate}" type="text" size="30" /><img onclick="showTime('forecastSignDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">可能性:</td>
											<td colspan="3"><select id="possibility">
													<option value=""></option>
													<s:iterator value="possibilityList" var="st">
														<s:if test="#st == crmProject.possibility">
															<option value="${st}" selected="selected">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
														</s:if>
														<s:else>
															<option value="${st}">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
														</s:else>
													</s:iterator>
											</select></td>
										</tr>
										<tr>
											<td align="right">项目概要:</td>
											<td colspan="3"><textarea id="projectSummary" rows="5" cols="80">${crmProject.projectSummary}</textarea></td>
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
