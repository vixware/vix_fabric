<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseParentCustomerAccountCategory(){
	$.ajax({
		  url:'${vix}/crm/customer/crmCustomerAccountCategoryAction!goChooseCustomerAccountCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 340,
					title:"选择父客户分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(typeof(returnValue) != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#id").val()){
									asyncbox.success("不允许引用自身为父分类!","<s:text name='snow_message'/>");
								}else{
									$("#parentCustomerAccountCategoryId").val(result[0]);
									$("#parentCustomerAccountCategoryName").val(result[1]);
								}
							}else{
								$("#parentCustomerAccountCategoryId").val("");
								$("#parentCustomerAccountCategoryName").val("");
								asyncbox.success("<s:text name='pleaseChooseItemCategory'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
$("#customerAccountCategoryForm").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="customerAccountCategoryForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="id" name="customerAccountCategory.id" value="%{customerAccountCategory.id}" theme="simple" />
				<s:hidden id="parentCustomerAccountCategoryId" name="parentCustomerAccountCategoryId" value="%{customerAccountCategory.parentCustomerAccountCategory.id}" theme="simple" />
				<tr height="30">
					<td align="right">父分类:&nbsp;</td>
					<td><input id="parentCustomerAccountCategoryName" name="customerAccountCategory.parentCustomerAccountCategory.name" value="${customerAccountCategory.parentCustomerAccountCategory.name}" /> <span class="abtn"><span onclick="chooseParentCustomerAccountCategory();">选择</span></span></td>
					<td align="right">编码:&nbsp;</td>
					<td><input id="code" name="customerAccountCategory.code" value="${customerAccountCategory.code}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="customerAccountCategory.name" value="${customerAccountCategory.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">备注:&nbsp;</td>
					<td><input id="memo" name="customerAccountCategory.memo" value="${customerAccountCategory.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>