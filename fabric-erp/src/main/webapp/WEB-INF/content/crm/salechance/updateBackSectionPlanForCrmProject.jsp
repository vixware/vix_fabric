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
								$("#ownerId").val(result[0]);
								$("#ownerName").val(result[1]);
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
								$("#chargerId").val(result[0]);
								$("#chargerName").val(result[1]);
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
//-->
</script>
<div class="content">
	<form id="backSectionPlanForm">
		<input type="hidden" id="bspId" name="bspId" value="${backSectionPlan.id}" />
		<div class="order">
			<dl>
				<dt>
					<strong> <b> <s:if test="backSectionPlan.id > 0">
								${backSectionPlan.amount}
							</s:if> <s:else>
								新增回款计划
							</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">计划回款日期:</td>
								<td width="35%"><input id="backSectionPlanDate" name="backSectionPlan.backSectionPlanDate" value="${backSectionPlan.backSectionPlanDate}" type="text" /> <img onclick="showTime('backSectionPlanDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								<td width="10%" align="right">金额:</td>
								<td width="40%"><input id="amount" name="backSectionPlan.amount" value="${backSectionPlan.amount}" type="text" /></td>
							</tr>
							<tr>
								<td align="right">期次:</td>
								<td><input id="stageNumber" name="backSectionPlan.stageNumber" value="${backSectionPlan.stageNumber}" type="text" /></td>
								<td align="right">外汇备注:</td>
								<td><input id="foreignCurrencyMemo" name="backSectionPlan.foreignCurrencyMemo" value="${backSectionPlan.foreignCurrencyMemo}" type="text" /></td>
							</tr>
							<tr>
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
								<td align="right">所有者:</td>
								<td><input id="ownerName" name="backSectionPlan.owner.name" value="${backSectionPlan.owner.name}" type="text" /> <input type="hidden" id="ownerId" name="ownerId" value="${backSectionPlan.owner.id}" /> <a class="abtn" href="###" onclick="chooseOwner();"><span>选择</span></a></td>
							</tr>
							<tr>
								<td align="right">负责人:</td>
								<td><input id="chargerName" name="backSectionPlan.charger.name" value="${backSectionPlan.charger.name}" type="text" /> <input type="hidden" id="chargerId" name="chargerId" value="${backSectionPlan.charger.id}" /> <a class="abtn" href="###" onclick="chooseCharger();"><span>选择</span></a></td>
								<td align="right">备注:</td>
								<td><input id="memo" name="backSectionPlan.memo" value="${backSectionPlan.memo}" type="text" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
