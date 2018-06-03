<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#orderItemForm").validationEngine();
//选择分类
function chooseSupplierCategory(){
	$.ajax({
		  url:'${vix}/hr/attendanceCategoryAction!goChooseSupplierCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择所属分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
									$("#newScId").val(result[0]);
									$("#newScName").val(result[1]);
							}else{
								asyncbox.success("请选择考勤类别!","提示信息");
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
	<form id="supplierCategoryForm">
		<s:hidden id="newId" name="attendanceCategory.id" value="%{attendanceCategory.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">上级分类:&nbsp;</th>
					<td colspan="3"><input id="newScName" name="attendanceCategory.attendanceCategory.name" value="${attendanceCategory.attendanceCategory.name}" type="text" readonly="readonly" style="background-color: #C0C0C0;" /> <input type="hidden" id="newScId" name="attendanceCategory.attendanceCategory.id"
						value="${attendanceCategory.attendanceCategory.id}" /> <input class="btn" type="button" value="选择" onclick="chooseSupplierCategory();" /></td>
				</tr>
				<tr height="40">
					<th align="right">类别编码:&nbsp;</th>
					<td><input id="newCode" name="newCode" value="${attendanceCategory.categoryCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">类别名称:&nbsp;</th>
					<td><input id="newName" name="newName" value="${attendanceCategory.categoryName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>