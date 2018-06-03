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
 
	$("#customerCreditInfoForm").validationEngine();
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
<input type="hidden" id="id" name="id" value="${customerCreditInfo.id}" />
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="customerCreditInfoForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td width="15%" align="right">客户名称:</td>
					<td width="35%"><input id="customerName" name="customerCreditInfo.customerAccount.name" value="${customerCreditInfo.customerAccount.name}" type="text" readonly="readonly" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId"
						value="${customerCreditInfo.customerAccount.id}" /> <span><a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
					<td width="15%" align="right">信用经理:</td>
					<td width="35%"><input id="creditManagerName" name="customerCreditInfo.creditManagerName" value="${customerCreditInfo.creditManagerName}" type="text" readonly="readonly" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="creditManagerCode" name="creditManagerCode"
						value="${customerCreditInfo.creditManagerCode}" /> <span><a class="abtn" href="#" onclick="chooseEmployee('选择信用经理','creditManagerCode','creditManagerName');"><span>选择</span></a></span></td>
				</tr>
				<tr height="30">
					<td align="right" width="15%">已使用信息额度:</td>
					<td><input id="usedCreditAmount" name="customerCreditInfo.usedCreditAmount" value="${customerCreditInfo.usedCreditAmount}" type="text" /></td>
					<td align="right">是否信用控制:</td>
					<td><input id="isCreditControl" name="customerCreditInfo.isCreditControl" value="${customerCreditInfo.isCreditControl}" type="text" /></td>
				</tr>
				<tr height="30">
					<td align="right" width="15%">风险类别:</td>
					<td><input id="riskType" name="customerCreditInfo.riskType" value="${customerCreditInfo.riskType}" type="text" /></td>
					<td align="right">信用冻结:</td>
					<td><input id="isFreezeCredit" name="customerCreditInfo.isFreezeCredit" value="${customerCreditInfo.isFreezeCredit}" type="text" /></td>
				</tr>
				<tr height="30">
					<td align="right" width="15%">单据最大值:</td>
					<td><input id="maxOfBill" name="customerCreditInfo.maxOfBill" value="${customerCreditInfo.maxOfBill}" type="text" /></td>
					<td align="right">允许最长逾期日期:</td>
					<td><input id="maxTimeOfArrears" name="customerCreditInfo.maxTimeOfArrears" value="${customerCreditInfo.maxTimeOfArrears}" type="text" /></td>
				</tr>
				<tr height="30">
					<td align="right" width="15%">拖欠等级:</td>
					<td><input id="arrearsLevel" name="customerCreditInfo.arrearsLevel" value="${customerCreditInfo.arrearsLevel}" type="text" /></td>
					<td align="right">允许最高拖欠登记:</td>
					<td><input id="arrearsMaxLevel" name="customerCreditInfo.arrearsMaxLevel" value="${customerCreditInfo.arrearsMaxLevel}" type="text" /></td>
				</tr>
				<tr height="30">
					<td align="right" width="15%">DSO:</td>
					<td><input id="dso" name="customerCreditInfo.dso" value="${customerCreditInfo.dso}" type="text" /></td>
					<td align="right">信用状态 :</td>
					<td><input id="status" name="customerCreditInfo.status" value="${customerCreditInfo.status}" type="text" /></td>
				</tr>
				<tr height="30">
					<td align="right" width="15%">最近信用检查人:</td>
					<td><input id="creditCheckerName" name="customerCreditInfo.creditChecker.Name" value="${customerCreditInfo.creditChecker.name}" type="text" readonly="readonly" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="creditCheckerId" name="creditCheckerId"
						value="${customerCreditInfo.creditChecker.id}" /> <span><a class="abtn" href="#" onclick="chooseEmployee('选择信用检查人','creditCheckerId','creditCheckerName');"><span>选择</span></a></span></td>
					<td align="right">最近信用检查时间 :</td>
					<td><input id="creditCheckTime" name="customerCreditInfo.creditCheckTime" value="${customerCreditInfo.creditCheckTime}" readonly="readonly" onfocus="showTime('creditCheckTime','yyyy-MM-dd HH:mm')" type="text" /> <img onclick="showTime('creditCheckTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td align="right" width="15%">信用等级:</td>
					<td><input id="creditLevel" name="customerCreditInfo.creditLevel" value="${customerCreditInfo.creditLevel}" type="text" /></td>
					<td align="right">信用截止日期 :</td>
					<td><input id="expirationDate" name="customerCreditInfo.expirationDate" value="${customerCreditInfo.expirationDate}" readonly="readonly" onfocus="showTime('expirationDate','yyyy-MM-dd HH:mm')" type="text" /> <img onclick="showTime('expirationDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td align="right">上年度开发票金额:</td>
					<td><input id="beforeYearBillAmount" name="customerCreditInfo.beforeYearBillAmount" value="${customerCreditInfo.beforeYearBillAmount}" type="text" /></td>
					<td align="right">本年度开发票金额:</td>
					<td><input id="currentYearBillAmount" name="customerCreditInfo.currentYearBillAmount" value="${customerCreditInfo.currentYearBillAmount}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
