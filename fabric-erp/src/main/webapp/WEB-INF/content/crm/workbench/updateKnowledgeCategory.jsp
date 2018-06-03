<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseParentKnowledgeCategory(){
	$.ajax({
		  url:'${vix}/crm/workbench/knowledgeCategoryAction!goChooseKnowledgeCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 340,
					title:"选择父${vv:varView('vix_mdm_item')}分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(typeof(returnValue) == undefined){
								$("#parentKnowledgeCategoryId").val("");
								$("#parentKnowledgeCategoryName").val("");
								asyncbox.success("<s:text name='pleaseChooseItemCategory'/>","<s:text name='vix_message'/>");
							}else{
								var result = returnValue.split(",");
								$("#parentKnowledgeCategoryId").val(result[0]);
								$("#parentKnowledgeCategoryName").val(result[1]);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
$("#knowledgeCategoryForm").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="knowledgeCategoryForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="id" name="knowledgeCategory.id" value="%{knowledgeCategory.id}" theme="simple" />
				<s:hidden id="parentKnowledgeCategoryId" name="parentKnowledgeCategoryId" value="%{knowledgeCategory.parentKnowledgeCategory.id}" theme="simple" />
				<tr height="30">
					<td align="right">父分类:&nbsp;</td>
					<td colspan="3"><input id="parentKnowledgeCategoryName" name="knowledgeCategory.parentKnowledgeCategory.name" value="${knowledgeCategory.parentKnowledgeCategory.name}" /> <span class="abtn"><span onclick="chooseParentKnowledgeCategory();">选择</span></span></td>
				</tr>
				<tr height="30">
					<td align="right">编码:&nbsp;</td>
					<td><input id="code" name="knowledgeCategory.code" value="${knowledgeCategory.code}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="knowledgeCategory.name" value="${knowledgeCategory.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>