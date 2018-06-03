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
 
$("#backSectionPlanForm").validationEngine();
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
function saveOrUpdateBackSectionPlan(){
	if($('#backSectionPlanForm').validationEngine('validate')){
		$.post('${vix}/crm/salechance/backSectionPlanAction!saveOrUpdate.action',
			{'backSectionPlan.id':$("#id").val(),
			  'backSectionPlan.backSectionPlanDate':$("#backSectionPlanDate").val(),
			  'backSectionPlan.stageNumber':$("#stageNumber").val(),
			  'backSectionPlan.customerAccount.id':$("#customerAccountId").val(),
			  'backSectionPlan.amount':$("#amount").val(),
			  'backSectionPlan.foreignCurrencyMemo':$("#foreignCurrencyMemo").val(),
			  'backSectionPlan.backSectionPlanStatus':$(":radio[name=backSectionPlanStatus][checked]").val(),
			  'backSectionPlan.owner.id':$("#ownerId").val(),
			  'backSectionPlan.charger.id':$("#chargerId").val(),
			  'backSectionPlan.crmProject.id':$("#crmProjectId").val(),
			  'backSectionPlan.saleOrderId':$("#saleOrderId").val(),
			  'backSectionPlan.saleOrderTitle':$("#saleOrderName").val(),
			  'backSectionPlan.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				loadContent('${vix}/crm/salechance/backSectionPlanAction!goList.action');
			}
		);
	}
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
function chooseSaleOrder(){
	$.ajax({
		  url:'${vix}/sales/order/salesOrderAction!goChooseSalesOrder.action?chooseType=single',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择订单",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#saleOrderId").val(result[0]);
								$("#saleOrderName").val(result[1]);
							}else{
								$("#saleOrderId").val("");
								$("#saleOrderName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
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
								$("#customerName").val(result[1]);
								$("#customerCode").val(result[2]);
							}else{
								$("#customerAccountId").val("");
								$("#customerName").val("");
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
function chooseOwner(){
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
								$("#ownerId").val(result[0].replace(",",""));
								$("#ownerName").val(result[1].replace(",",""));
							}else{
								$("#ownerId").val("");
								$("#ownerName").val("");
								asyncbox.success("<s:text name='pleaseChooseEmployee'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseCharger(){
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
								$("#chargerId").val(result[0].replace(",",""));
								$("#chargerName").val(result[1].replace(",",""));
							}else{
								$("#chargerId").val("");
								$("#chargerName").val("");
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
				<li><a href="#"><img src="${vix}/common/img/crm/backSectionPlan.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/salechance/saleChanceAction!goList.action');">销售机会</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/salechance/backSectionPlanAction!goList.action');">回款计划</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${backSectionPlan.id}" />
<div class="content">
	<form id="backSectionPlanForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateBackSectionPlan();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/salechance/backSectionPlanAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="backSectionPlan.id > 0">
							${backSectionPlan.customerAccount.name}
						</s:if> <s:else>
							新增回款计划
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
											<td width="15%" align="right">计划回款日期:</td>
											<td width="35%"><input id="backSectionPlanDate" name="backSectionPlan.backSectionPlanDate" value="<s:property value='formatDateToString(backSectionPlan.backSectionPlanDate)'/>" type="text" /> <img onclick="showTime('backSectionPlanDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td width="10%" align="right">客户名称:</td>
											<td width="40%"><input id="customerName" name="backSectionPlan.customerAccount.name" value="${backSectionPlan.customerAccount.name}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId"
												value="${backSectionPlan.customerAccount.id}" /> <a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">项目:</td>
											<td><input id="crmProjectName" name="backSectionPlan.crmProject.subject" value="${backSectionPlan.crmProject.subject}" type="text" /> <input type="hidden" id="crmProjectId" name="crmProjectId" value="${backSectionPlan.crmProject.id}" /> <a class="abtn" href="#" onclick="chooseCrmProject();"><span>选择</span></a></td>
											<td align="right">销售订单:</td>
											<td><input id="saleOrderName" name="backSectionPlan.saleOrderTitle" value="${backSectionPlan.saleOrderTitle}" type="text" /> <input type="hidden" id="saleOrderId" name="saleOrderId" value="${backSectionPlan.saleOrderId}" /> <a class="abtn" href="#" onclick="chooseSaleOrder();"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">期次:</td>
											<td><input id="stageNumber" name="backSectionPlan.stageNumber" value="${backSectionPlan.stageNumber}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">金额:</td>
											<td><input id="amount" name="backSectionPlan.amount" value="${backSectionPlan.amount}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">外汇备注:</td>
											<td><input id="foreignCurrencyMemo" name="backSectionPlan.foreignCurrencyMemo" value="${backSectionPlan.foreignCurrencyMemo}" type="text" /></td>
											<td align="right">回款状态:</td>
											<td><s:if test="backSectionPlan.backSectionPlanStatus == 0">
													<input type="radio" id="backSectionPlanStatus1" name="backSectionPlanStatus" value="1" />是
												<input type="radio" id="backSectionPlanStatus2" name="backSectionPlanStatus" value="0" checked="checked" />否
											</s:if> <s:elseif test="backSectionPlan.backSectionPlanStatus == 1">
													<input type="radio" id="backSectionPlanStatus1" name="backSectionPlanStatus" value="1" checked="checked" />是
												<input type="radio" id="backSectionPlanStatus2" name="backSectionPlanStatus" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="backSectionPlanStatus1" name="backSectionPlanStatus" value="1" />是
												<input type="radio" id="backSectionPlanStatus2" name="backSectionPlanStatus" value="0" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right">所有者:</td>
											<td><input id="ownerName" name="backSectionPlan.owner.name" value="${backSectionPlan.owner.name}" type="text" /> <input type="hidden" id="ownerId" name="ownerId" value="${backSectionPlan.owner.id}" /> <a class="abtn" href="#" onclick="chooseOwner();"><span>选择</span></a></td>
											<td align="right">负责人:</td>
											<td><input id="chargerName" name="backSectionPlan.charger.name" value="${backSectionPlan.charger.name}" type="text" /> <input type="hidden" id="chargerId" name="chargerId" value="${backSectionPlan.charger.id}" /> <a class="abtn" href="#" onclick="chooseCharger();"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">备注:</td>
											<td colspan="3"><input id="memo" name="backSectionPlan.memo" value="${backSectionPlan.memo}" type="text" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
