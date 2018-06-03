<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseParentSalesQuotationCategory(){
	$.ajax({
		  url:'${vix}/sales/quotes/salesQuotationCategoryAction!goChooseSalesQuotationCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 580,
					height : 340,
					title:"选择上级分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#parentSalesQuotationCategoryId").val(result[0]);
								$("#parentSalesQuotationCategoryName").val(result[1]);
							}else{
								$("#parentSalesQuotationCategoryId").val("");
								$("#parentSalesQuotationCategoryName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
$("#salesQuotationCategoryForm").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="salesQuotationCategoryForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="id" name="salesQuotationCategory.id" value="%{salesQuotationCategory.id}" theme="simple" />
				<s:hidden id="parentSalesQuotationCategoryId" name="parentSalesQuotationCategoryId" value="%{salesQuotationCategory.parentSalesQuotationCategory.id}" theme="simple" />
				<tr height="30">
					<td align="right">上级分类:&nbsp;</td>
					<td><input id="parentSalesQuotationCategoryName" name="salesQuotationCategory.parentSalesQuotationCategory.name" value="${salesQuotationCategory.parentSalesQuotationCategory.name}" /> <span class="abtn"><a href="###" onclick="chooseParentSalesQuotationCategory();"><span>选择</span></a></span></td>
					<td align="right">分类名称:&nbsp;</td>
					<td><input id="name" name="salesQuotationCategory.name" value="${salesQuotationCategory.name}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>