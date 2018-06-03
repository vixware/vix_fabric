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
 
	$("#saleReturnBillForm").validationEngine();
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
	function chooseItem(){
		$.ajax({
			  url:'${vix}/mdm/item/itemAction!goChooseItem.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 960,
						height : 580,
						title:"选择${vv:varView('vix_mdm_item')}",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != ''){
									var r = returnValue.split(",");
									$("#itemId").val(r[0]);
									$("#itemCode").val(r[1]);
									$("#itemName").val(r[2]);
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
<input type="hidden" id="id" name="id" value="${saleReturnBill.id}" />
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="saleReturnBillForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right" width="15%">编码:</td>
					<td width="35%"><input id="rbCode" name="saleReturnBill.rbCode" value="${saleReturnBill.rbCode}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right" width="10%">主题:</td>
					<td width="40%"><input id="rbTitle" name="saleReturnBill.rbTitle" value="${saleReturnBill.rbTitle}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">客户名称:</td>
					<td><input id="customerName" name="saleReturnBill.customerAccount.name" value="${saleReturnBill.customerAccount.name}" type="text" readonly="readonly" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId" value="${saleReturnBill.customerAccount.id}" />
						<span><a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
					<td align="right">${vv:varView('vix_mdm_item')}:&nbsp;</td>
					<td><input id="itemName" value="${saleReturnBill.item.name}" type="text" readonly="readonly" /> <input id="itemId" type="hidden" value="${saleReturnBill.item.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem();">选择</span></span></td>
				</tr>
				<tr height="30">
					<td align="right">金额:</td>
					<td><input id="returnAmount" name="saleReturnBill.returnAmount" value="${saleReturnBill.returnAmount}" type="text" /></td>
					<td align="right">币种</td>
					<td><s:select id="currencyTypeId" headerKey="-1" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{saleReturnBill.currencyType.id}" multiple="false" theme="simple"></s:select></td>
				</tr>
				<tr height="30">
					<td align="right">返款日期:</td>
					<td><input id="rbDate" name="saleReturnBill.rbDate" value="${saleReturnBill.rbDate}" readonly="readonly" onfocus="showTime('rbDate','yyyy-MM-dd HH:mm')" type="text" /> <img onclick="showTime('rbDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
