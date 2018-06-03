<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#knowledgeBaseCategoryForm").validationEngine();
	function chooseParentKnowledgeBaseCategory() {
		$.ajax({
		url : '${vix}/drp/knowledgeBasemanagementAction!goKnowledgeBaseCategory.action',
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
						$("#parentKnowledgeBaseCategoryId").val(result[0]);
						$("#parentKnowledgeBaseCategoryName").val(result[1]);
					} else {
						$("#parentKnowledgeBaseCategoryId").val("");
						$("#parentKnowledgeBaseCategoryName").val("");
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
	<form id="knowledgeBaseCategoryForm" method="post">
		<s:hidden id="knowledgeBaseCategoryId" name="knowledgeBaseCategory.id" value="%{knowledgeBaseCategory.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">上级分类:&nbsp;</td>
					<td><s:hidden id="parentKnowledgeBaseCategoryId" name="knowledgeBaseCategory.parentKnowledgeBaseCategory.id" value="%{knowledgeBaseCategory.parentKnowledgeBaseCategory.id}" theme="simple" /><input type="text" id="parentKnowledgeBaseCategoryName" name="knowledgeBaseCategory.parentKnowledgeBaseCategory.name"
						value="${knowledgeBaseCategory.parentKnowledgeBaseCategory.name}" readonly="readonly" /><input class="btn" type="button" value="选择" onclick="chooseParentKnowledgeBaseCategory();" /></td>
				</tr>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="code" name="knowledgeBaseCategory.code" class="validate[required] text-input" value="${knowledgeBaseCategory.code}" /><span style="color: red;">*</span></td>
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="name" name="knowledgeBaseCategory.name" class="validate[required] text-input" value="${knowledgeBaseCategory.name}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="knowledgeBaseCategory.memo" size="45" value="${knowledgeBaseCategory.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>