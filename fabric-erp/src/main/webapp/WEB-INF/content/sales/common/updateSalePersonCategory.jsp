<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#salePersonCategoryForm").validationEngine();
function chooseEmployee(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择核对人员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#employeeId").val(result[0]);
								$("#employeeName").val(result[1]);
							}else{
								$("#employeeId").val("");
								$("#employeeName").val("");
								asyncbox.success("<s:text name='pleaseChooseEmployee'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<input type="hidden" id="id" name="id" value="${salePersonCategory.id}" />
<div style="padding-top: 15px;" class="content">
	<form id="salePersonCategoryForm">
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<th align="right">编码:&nbsp;</th>
					<td><input id="code" name="salePersonCategory.code" value="${salePersonCategory.code}" type="text" /></td>
					<th align="right">名称:&nbsp;</th>
					<td><input id="name" name="salePersonCategory.name" value="${salePersonCategory.name}" type="text" /></td>
				</tr>
				<tr height="30">
					<th align="right">销售组织:&nbsp;</th>
					<td><input id="saleOrg" name="salePersonCategory.saleOrg" value="${salePersonCategory.saleOrg}" type="text" /></td>
					<th align="right">核对人员:&nbsp;</th>
					<td><input type="hidden" id="employeeId" value="${salePersonCategory.checker.id}" /> <input id="employeeName" name="salePersonCategory.checker.name" value="${salePersonCategory.checker.name}" type="text" /> <a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></td>
				</tr>
				<tr height="30">
					<th align="right">销售编码:&nbsp;</th>
					<td><input id="saleCatalogCode" name="salePersonCategory.saleCatalogCode" value="${salePersonCategory.saleCatalogCode}" type="text" /></td>
					<th align="right">销售名称:&nbsp;</th>
					<td><input id="saleCatalogName" name="salePersonCategory.saleCatalogName" value="${salePersonCategory.saleCatalogName}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
