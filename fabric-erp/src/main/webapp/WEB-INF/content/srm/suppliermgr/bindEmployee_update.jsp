<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#bindEmployeeForm").validationEngine();
//选择分类
function chooseEmployee(){
	$.ajax({
		  url:'${vix}/srm/managementBusinessPartnerAction!goChooseRadioEmployee.action',
		  cache: false,
		  success: function(html){
			  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
			  $(".ab_c .content").css("margin-bottom","0");
			  $('.ab_c .content').parent().css('position','relative');
			  asyncbox.open({
				 	modal:true,
					width : 1000,
					height : 500,
					title:"选择职员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue); 
								var rv = returnValue.split(","); 
								$("#eId").val(rv[0]);
								$("#eName").val(rv[1]);
							}else{
								asyncbox.success("请选择职员!","提示信息");
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
	<form id="bindEmployeeForm">
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">供应商:&nbsp;</th>
					<td><input id="sName" value="${supplier.name}" type="text" readonly="readonly" size="50" /> <input id="sId" name="id" value="${supplier.id }" type="hidden" /></td>
				</tr>
				<tr height="40">
					<th align="right">职员:&nbsp;</th>
					<td><input id="eName" type="text" class="validate[required] text-input" size="50" readonly="readonly" /> <span style="color: red;">*</span> <input id="eId" name="eId" type="hidden" /> <input type="button" value="选择" onclick="chooseEmployee();" class="btn" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>