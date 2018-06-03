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
 
$("#backSectionRecordForm").validationEngine();
 
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
//-->
</script>
<div class="content">
	<form id="backSectionRecordForm">
		<input type="hidden" id="bsrId" name="bsrId" value="${backSectionRecord.id}" />
		<div class="order">
			<dl>
				<dt>
					<strong> <b> <s:if test="backSectionRecord.id > 0">
								${backSectionRecord.amount}
							</s:if> <s:else>
								新增回款记录
							</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">回款日期:</td>
								<td width="35%"><input id="backSectionDate" name="backSectionRecord.backSectionDate" value="${backSectionRecord.backSectionDate}" type="text" /> <img onclick="showTime('backSectionDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								<td width="10%" align="right">金额:</td>
								<td width="40%"><input id="amount" name="backSectionRecord.amount" value="${backSectionRecord.amount}" type="text" /></td>
							</tr>
							<tr>
								<td align="right">期次:</td>
								<td><input id="stageNumber" name="backSectionRecord.stageNumber" value="${backSectionRecord.stageNumber}" type="text" /></td>
								<td align="right">外币备注:</td>
								<td><input id="foreignCurrencyMemo" name="backSectionRecord.foreignCurrencyMemo" value="${backSectionRecord.foreignCurrencyMemo}" type="text" /></td>
							</tr>
							<tr>
								<td align="right">开票状态:</td>
								<td><s:if test="backSectionRecord.isBilling == 0">
										<input type="radio" id="isBilling1" name="isBilling" value="0" checked="checked" />未开
										<input type="radio" id="isBilling1" name="isBilling" value="1" />已开
										<input type="radio" id="isBilling2" name="isBilling" value="2" />不开
									</s:if> <s:elseif test="backSectionRecord.isBilling == 1">
										<input type="radio" id="isBilling1" name="isBilling" value="0" />未开
										<input type="radio" id="isBilling1" name="isBilling" value="1" checked="checked" />已开
										<input type="radio" id="isBilling2" name="isBilling" value="2" />不开
									</s:elseif> <s:elseif test="backSectionRecord.isBilling == 2">
										<input type="radio" id="isBilling1" name="isBilling" value="0" />未开
										<input type="radio" id="isBilling1" name="isBilling" value="1" />已开
										<input type="radio" id="isBilling2" name="isBilling" value="2" checked="checked" />不开
									</s:elseif> <s:else>
										<input type="radio" id="isBilling1" name="isBilling" value="0" />未开
										<input type="radio" id="isBilling1" name="isBilling" value="1" />未开
										<input type="radio" id="isBilling2" name="isBilling" value="2" />不开
									</s:else></td>
								<td align="right">所有者:</td>
								<td><input id="ownerName" name="backSectionPlan.owner.name" value="${backSectionPlan.owner.name}" type="text" /> <input type="hidden" id="ownerId" name="ownerId" value="${backSectionPlan.owner.id}" /> <a class="abtn" href="###" onclick="chooseOwner();"><span>选择</span></a></td>
							</tr>
							<tr>
								<td align="right">付款方式:</td>
								<td><s:select id="paymentTypeId" headerKey="-1" headerValue="--请选择--" list="%{paymentTypeList}" listValue="name" listKey="id" value="%{backSectionPlan.paymentType.id}" multiple="false" theme="simple"></s:select></td>
								<td align="right">付款分类:</td>
								<td><s:select id="paymentCategoryId" headerKey="-1" headerValue="--请选择--" list="%{paymentCategoryList}" listValue="name" listKey="id" value="%{backSectionPlan.paymentCategory.id}" multiple="false" theme="simple"></s:select></td>
							</tr>
							<tr>
								<td align="right">备注:</td>
								<td colspan="3"><input id="memo" name="backSectionRecord.memo" value="${backSectionRecord.memo}" type="text" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
