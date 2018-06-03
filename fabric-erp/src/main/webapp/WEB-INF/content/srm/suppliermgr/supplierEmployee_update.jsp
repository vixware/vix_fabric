<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#supplierEmployeeForm").validationEngine();
/** 选择单选供应商 */
function chooseRadioSupplier(){
	$.ajax({
		  url:'${vix}/purchase/purchaseOrderAction!goChooseRadioSupplier.action',
		  cache: false,
		  success: function(html){
			  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
			  $(".ab_c .content").css("margin-bottom","0");
			  $('.ab_c .content').parent().css('position','relative');
			  asyncbox.open({
				 	modal:true,
					width : 1000,
					height : 500,
					title:"选择供应商",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var rv = returnValue.split(","); 
								$("#newSId").val(rv[0]);
								$("#newSName").val(rv[1]);
							}else{
								asyncbox.success("请选择分类信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="supplierEmployeeForm">
		<s:hidden id="newId" value="%{supplierEmployee.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">供应商:&nbsp;</th>
					<td colspan="3"><input type="text" id="newSName" value="${supplierEmployee.supplier.name}" class="validate[required] text-input" readonly="readonly" style="background-color: #C0C0C0;" size="50" /> <input type="hidden" id="newSId" value="${supplierEmployee.supplier.id}" /> <span style="color: red;">*</span> <input type="button"
						class="btn" value="选择" onclick="chooseRadioSupplier();" /></td>
				</tr>
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="newCode" value="${supplierEmployee.code}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">姓名:&nbsp;</th>
					<td><input id="newName" value="${supplierEmployee.name}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>