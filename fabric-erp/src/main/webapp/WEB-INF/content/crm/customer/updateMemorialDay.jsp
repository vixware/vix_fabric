<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="memorialDayForm">
		<div class="box order_table" style="line-height: normal;">
			<s:hidden id="memorialDayId" name="memorialDay.id" value="%{memorialDay.id}" theme="simple" />
			<s:hidden id="customerAccountId" name="memorialDay.customerAccount.id" value="%{memorialDay.customerAccount.id}" theme="simple" />
			<table class="table-padding020">
				<tr height="40">
					<td align="right">客户:&nbsp;</td>
					<td><input type="text" id="customerAccountName" name="memorialDay.customerAccount.name" value="${memorialDay.customerAccount.name}" readonly="readonly" /> <span class="abtn"><a href="###" onclick="chooseCustomer();"><span>选择</span></a></span></td>
					<td align="right">联系人:</td>
					<td><select id="contactPersonId"></select></td>
				</tr>
				<tr height="40">
					<td align="right">纪念日类型:&nbsp;</td>
					<td><s:select id="memorialDayTypeId" headerKey="-1" headerValue="--请选择--" list="%{memorialDayTypeList}" listValue="name" listKey="id" value="%{memorialDay.memorialDayType.id}" multiple="false" theme="simple"></s:select></td>
					<td>日期类型</td>
					<td><s:if test="memorialDay.dateType == 0">
							<input type="radio" id="dateType1" name="dateType" value="1" />农历
							<input type="radio" id="dateType2" name="dateType" value="0" checked="checked" />公历
						</s:if> <s:elseif test="memorialDay.dateType == 1">
							<input type="radio" id="dateType1" name="dateType" value="1" checked="checked" />农历
							<input type="radio" id="dateType2" name="dateType" value="0" />公历
						</s:elseif> <s:else>
							<input type="radio" id="dateType1" name="dateType" value="1" />农历
							<input type="radio" id="dateType2" name="dateType" value="0" />公历
						</s:else></td>
				</tr>
				<tr height="30">
					<th align="right">日期:&nbsp;</th>
					<td><input id="mdDate" name="memorialDay.date" value="${memorialDay.dateStr}" type="text" /> <img onclick="showTime('mdDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">备注:&nbsp;</th>
					<td><input id="memo" name="memorialDay.memo" value="${memorialDay.memo}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	$("#memorialDayForm").validationEngine();
	function chooseCustomer(){
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
									alert(result);
									$("#customerAccountId").val(result[0]);
									$("#customerAccountName").val(result[1]);
									$("#customerCode").val(result[2]);
									loadContactPerson();
								}
							}else{
								$("#customerAccountId").val("");
								$("#customerAccountName").val("");
								asyncbox.success("请选择客户!","<s:text name='vix_message'/>");
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	function loadContactPerson(){
		$.ajax({
			  url:"${vix}/crm/customer/memorialDayAction!loadCustomerContactPerson.action?id=" + $("#memorialDayId").val() + "&customerAccountId=" + $("#customerAccountId").val(),
			  cache: false,
			  success: function(html){
				  $("#contactPersonId").html(html);
			  }
		});
	}
	loadContactPerson();
</script>