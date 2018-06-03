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
 
$("#customerCareForm").validationEngine();
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
//-->
</script>
<input type="hidden" id="customerCareId" name="customerCareId" value="${customerCare.id}" />
<input type="hidden" id="employeeId" value="${customerCare.employee.id}" />
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="customerCareForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right" width="15%">主题:</td>
					<td width="35%"><input id="subject" name="customerCare.subject" value="${customerCare.subject}" type="text" /></td>
					<td align="right" width="10%">执行人:&nbsp;</td>
					<td width="40%"><input id="employeeName" name="customerCare.employee.name" value="${customerCare.employee.name}" type="text" /> <span><a class="abtn" href="###" onclick="chooseEmployee();"><span>选择</span></a></span></td>
				</tr>
				<tr height="30">
					<td align="right">关怀内容:</td>
					<td><textarea id="careContent" rows="3" cols="40" style="font-size: 12px;">${customerCare.careContent}</textarea></td>
					<td align="right">客户反馈:</td>
					<td><textarea id="customerFeedback" rows="3" cols="40" style="font-size: 12px;">${customerCare.customerFeedback}</textarea></td>
				</tr>
				<tr height="30">
					<td align="right">备注:</td>
					<td colspan="3"><textarea id="memo" rows="3" cols="60" style="font-size: 12px;">${customerCare.memo}</textarea></td>
				</tr>
			</table>
		</div>
		<!--oder-->
	</form>
</div>
<!-- content -->
