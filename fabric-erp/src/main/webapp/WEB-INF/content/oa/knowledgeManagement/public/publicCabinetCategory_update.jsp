<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#publicCabinetCategoryForm").validationEngine();
	function chooseParentKnowledgeBaseCategory() {
		$.ajax({
		url : '${vix}/oa/publicCabinetAction!goPrivateCabinetCategory.action',
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
						$("#publicCabinetCategoryssId").val(result[0]);
						$("#publicCabinetCategoryssName").val(result[1]); 
					} else {
					 	$("#publicCabinetCategoryssId").val("");
						$("#publicCabinetCategoryssName").val(""); 
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
	<form id="publicCabinetCategoryForm" method="post">
		<s:hidden id="publicCabinetCategoryId" name="publicCabinetCategory.id" value="%{publicCabinetCategory.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">上级分类:&nbsp;</td>
					<td><s:hidden id="publicCabinetCategoryssId" name="publicCabinetCategory.publicCabinetCategoryss.id" value="%{publicCabinetCategory.publicCabinetCategoryss.id}" theme="simple" /> <input type="text" id="publicCabinetCategoryssName" name="publicCabinetCategory.publicCabinetCategoryss.name"
						value="${publicCabinetCategory.publicCabinetCategoryss.name}" readonly="readonly" /> <input class="btn" type="button" value="选择" onclick="chooseParentKnowledgeBaseCategory();" /></td>
				</tr>
				<tr height="40">
					<td align="right">排序号:&nbsp;</td>
					<td><input type="text" id="code" name="publicCabinetCategory.code" class="validate[required] text-input" value="${publicCabinetCategory.code}" /> <span style="color: red;">*</span></td>
					<td align="right">文档名称:&nbsp;</td>
					<td><input type="text" id="name" name="publicCabinetCategory.name" class="validate[required] text-input" value="${publicCabinetCategory.name}" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="publicCabinetCategory.memo" size="45" value="${publicCabinetCategory.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>