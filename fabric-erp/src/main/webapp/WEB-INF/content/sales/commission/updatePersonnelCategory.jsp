<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseParentPersonnelCategory(){
	$.ajax({
		  url:'${vix}/sales/commission/personnelCategoryAction!goChoosePersonnelCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 340,
					title:"选择父分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(typeof(returnValue) == undefined){
								$("#parentPersonnelCategoryId").val("");
								$("#parentPersonnelCategoryName").val("");
								asyncbox.success("<s:text name='pleaseChoosePersonnelCategory'/>","<s:text name='vix_message'/>");
							}else{
								var result = returnValue.split(",");
								$("#parentPersonnelCategoryId").val(result[0]);
								$("#parentPersonnelCategoryName").val(result[1]);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
$("#personnelCategoryForm").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="personnelCategoryForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="id" name="personnelCategory.id" value="%{personnelCategory.id}" theme="simple" />
				<s:hidden id="parentPersonnelCategoryId" name="parentPersonnelCategoryId" value="%{personnelCategory.parentPersonnelCategory.id}" theme="simple" />
				<tr height="30">
					<td align="right">父分类:&nbsp;</td>
					<td><input id="parentPersonnelCategoryName" name="personnelCategory.parentPersonnelCategory.name" value="${personnelCategory.parentPersonnelCategory.name}" /> <a class="abtn"><span onclick="chooseParentPersonnelCategory();">选择</span></a></td>
				</tr>
				<tr height="30">
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="personnelCategory.name" value="${personnelCategory.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">备注:&nbsp;</td>
					<td><input id="memo" name="personnelCategory.memo" value="${personnelCategory.memo}" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>