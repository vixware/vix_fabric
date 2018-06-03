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
 
	$("#creditExcuteResultForm").validationEngine();
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
<input type="hidden" id="id" name="id" value="${creditExcuteResult.id}" />
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="creditExcuteResultForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">客户名称:</td>
					<td><input id="customerName" name="creditExcuteResult.customerAccount.name" value="${creditExcuteResult.customerAccount.name}" type="text" readonly="readonly" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId"
						value="${creditExcuteResult.customerAccount.id}" /> <span><a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
					<td align="right">执行人:&nbsp;</td>
					<td><input id="excuterName" value="${creditExcuteResult.excuter.name}" type="text" readonly="readonly" /> <input id="excuterId" type="hidden" value="${creditExcuteResult.excuter.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseEmployee('执行人','excuterId','excuterName');">选择</span></span></td>
				</tr>
				<tr height="30">
					<td align="right">信用执行结果:</td>
					<td><input id="result" name="creditExcuteResult.result" value="${creditExcuteResult.result}" type="text" /></td>
					<td align="right">执行时间</td>
					<td><input id="excuteDate" name="creditExcuteResult.excuteDate" value="${creditExcuteResult.excuteDate}" readonly="readonly" onfocus="showTime('excuteDate','yyyy-MM-dd HH:mm')" type="text" /> <img onclick="showTime('excuteDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
