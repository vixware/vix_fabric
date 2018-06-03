<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js" />
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
 
$("#activityForm").validationEngine();
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

var editor = KindEditor.create('textarea[name="activity.content"]', {
	basePath : '${vix}/plugin/KindEditor/',
	width : 850,
	height : 300,
	cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
	fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
	allowFileManager : true,
	items : ['fontname', 'fontsize', '|', 'forecolor', 
	         'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 
			'justifyright', 'insertorderedlist','insertunorderedlist'],
	afterCreate : function() {
		var self = this;
		KindEditor.ctrl(document, 13, function() {
			self.sync();
			document.forms['competitorForm'].submit();
		});
		KindEditor.ctrl(self.edit.doc, 13, function() {
			self.sync();
			document.forms['competitorForm'].submit();
		});
	}
});

function saveOrUpdateActivity(){
	if($('#activityForm').validationEngine('validate')){
		$.post('${vix}/crm/activity/activityAction!saveOrUpdate.action',
			{'activity.id':$("#id").val(),
			  'activity.title':$("#title").val(),
			  'activity.activity':$("#activity").val(),
			  'activity.type':$("#type").val(),
			  'activity.date':$("#date").val(),
			  'activity.content':editor.html(),
			  'activity.startDate':$("#startDate").val(),
			  'activity.startTimeStr':$("#startTimeStr").val(),
			  'activity.endDate':$("#endDate").val(),
			  'activity.endTimeStr':$("#endTimeStr").val(),
			  'activity.address':$("#address").val(),
			  'activity.isDeleted':$("#isDeleted").val(),
			  'activity.estimatedCost':$("#estimatedCost").val(),
			  'activity.costsIncurred':$("#costsIncurred").val(),
			  'activity.approvalOfFees':$("#approvalOfFees").val(),
			  'activity.projectedSales':$("#projectedSales").val(),
			  'activity.currencyType.id':$("#currencyTypeId").val(),
			  'activity.saleActivity.id':$("#saleActivityId").val(),
			  'activity.customerAccount.id':$("#customerAccountId").val(),
			  'activity.personInCharge.id':$("#employeeId").val()
			},
			function(result){
				showMessage(result);
				loadContent('${vix}/crm/activity/activityAction!goList.action');
			}
		);
	}
}

//选择职员
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

//选择客户
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
				<li><a href="#"><img src="${vix}/common/img/crm/saleActivity.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/activity/activityAction!goList.action');">销售活动</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${activity.id}" />
<input type="hidden" id="employeeId" value="${crmProject.personInCharge.id}" />
<input type="hidden" id="customerAccountId" value="${crmProject.customerAccount.id}" />
<input type="hidden" id="isDeleted" name="isDeleted" value="${activity.isDeleted}" />
<div class="content">
	<form id="activityForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateActivity();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/activity/activityAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="activity.id > 0">
							${activity.title}
						</s:if> <s:else>
							新增活动
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
											<td width="15%" align="right">活动主题:</td>
											<td width="35%"><input id="title" name="activity.title" value="${activity.title}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td width="10%" align="right">活动:</td>
											<td width="40%"><input id="activity" name="activity.activity" value="${activity.activity}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">类型:</td>
											<td><s:select id="saleActivityId" headerKey="" headerValue="--请选择--" list="%{saleActivityList}" listValue="name" listKey="id" value="%{activity.saleActivity.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">日期:</td>
											<td><input id="date" name="activity.date" value="<s:property value='formatDateToString(activity.date)'/>" type="text" size="30" class="validate[required] text-input" onfocus="showTime('date','yyyy-MM-dd HH:mm')" /><span style="color: red;">*</span> <img onclick="showTime('date','yyyy-MM-dd HH:mm')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right" width="10%">客户:</td>
											<td width="40%"><input id="customerAccountName" name="activity.customerAccount.name" value="${activity.customerAccount.name}" type="text" readonly="readonly" size="30" /> <span><a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
											<td align="right">负责人:</td>
											<td><input id="employeeName" name="activity.personInCharge.name" value="${activity.personInCharge.name}" type="text" readonly="readonly" size="30" /> <span><a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></span></td>
										</tr>
										<tr>
											<td align="right">地址:</td>
											<td><input id="address" name="activity.address" value="${activity.address}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">预计费用:</td>
											<td><input id="estimatedCost" name="activity.estimatedCost" value="${activity.estimatedCost}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">发生费用:</td>
											<td><input id="costsIncurred" name="activity.costsIncurred" value="${activity.costsIncurred}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">批准费用:</td>
											<td><input id="approvalOfFees" name="activity.approvalOfFees" value="${activity.approvalOfFees}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">预计销售额:</td>
											<td><input id="projectedSales" name="activity.projectedSales" value="${activity.projectedSales}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">货币类型:</td>
											<td><s:select id="currencyTypeId" headerKey="" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{activity.currencyType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">开始日期:</td>
											<td><input id="startDate" name="activity.startDate" value="${activity.startDateStr}" type="text" size="30" class="validate[required] text-input" onfocus="showTime('startDate','yyyy-MM-dd')" /><span style="color: red;">*</span> <img onclick="showTime('startDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
											<td align="right">开始时间:</td>
											<td><select id="startTimeStr">
													<option value=""></option>
													<s:iterator value="startTimeList" var="st">
														<s:if test="#st == activity.startTimeStr">
															<option value="${st}" selected="selected">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
														</s:if>
														<s:else>
															<option value="${st}">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
														</s:else>
													</s:iterator>
											</select></td>
										</tr>
										<tr>
											<td align="right">结束日期:</td>
											<td><input id="endDate" name="activity.endDate" value="${activity.endDateStr}" type="text" size="30" class="validate[required] text-input" onfocus="showTime('endDate','yyyy-MM-dd')" /><span style="color: red;">*</span> <img onclick="showTime('endDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td align="right">结束时间:</td>
											<td><select id="endTimeStr">
													<option value=""></option>
													<s:iterator value="endTimeList" var="st">
														<s:if test="#st == activity.endTimeStr">
															<option value="${st}" selected="selected">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
														</s:if>
														<s:else>
															<option value="${st}">&nbsp;&nbsp;${st}&nbsp;&nbsp;&nbsp;</option>
														</s:else>
													</s:iterator>
											</select></td>
										</tr>
										<tr>
											<td align="right"><h4>
													<span>内&nbsp;&nbsp;&nbsp;&nbsp;容:&nbsp;</span>
												</h4></td>
											<td align="center"><textarea id="content" name="activity.content">${activity.content}</textarea></td>
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
