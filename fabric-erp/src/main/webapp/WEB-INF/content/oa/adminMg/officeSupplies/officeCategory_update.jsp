<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#bookCategoryForm").validationEngine();
	function bookBaseCategory() {
		$.ajax({
		url : '${vix}/oa/officeSuppliesAction!goBookCategory.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择父分类",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#parentOfficeCategoryId").val(result[0]);
						$("#parentOfficeCategoryName").val(result[1]); 
					} else {
					 	$("#parentOfficeCategoryId").val("");
						$("#parentOfficeCategoryName").val(""); 
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
	<form id="bookCategoryForm" method="post">
		<s:hidden id="officeCategoryId" name="officeCategory.id" value="%{officeCategory.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">上级分类:&nbsp;</td>
					<td><input type="hidden" id="parentOfficeCategoryId" name="officeCategory.parentOfficeCategory.id"> <input type="text" id="parentOfficeCategoryName" name="officeCategory.parentOfficeCategory.name" value="${officeCategory.parentOfficeCategory.name}" readonly="readonly" /> <input class="btn" type="button" value="选择"
						onclick="bookBaseCategory();" /></td>
				</tr>
				<tr height="40">
					<td align="right">排序号:&nbsp;</td>
					<td><input type="text" id="code" name="officeCategory.code" class="validate[required] text-input" value="${officeCategory.code}" /><span style="color: red;">*</span></td>
					<td align="right">分类名称:&nbsp;</td>
					<td><input type="text" id="name" name="officeCategory.name" class="validate[required] text-input" value="${officeCategory.name}" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="officeCategory.memo" size="45" value="${officeCategory.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>