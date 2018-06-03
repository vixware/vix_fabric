<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#privateCabinetCategoryForm").validationEngine();
	function chooseParentKnowledgeBaseCategory() {
		$.ajax({
		url : '${vix}/oa/privateCabinetAction!goPrivateCabinetCategory.action',
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
						$("#privateCabinetCategoryssId").val(result[0]);
						$("#privateCabinetCategoryssName").val(result[1]); 
					} else {
					 	$("#privateCabinetCategoryssId").val("");
						$("#privateCabinetCategoryssName").val(""); 
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
	<form id="privateCabinetCategoryForm" method="post">
		<s:hidden id="privateCabinetCategoryId" name="privateCabinetCategory.id" value="%{privateCabinetCategory.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">上级分类:&nbsp;</td>
					<td><s:hidden id="privateCabinetCategoryssId" name="privateCabinetCategory.privateCabinetCategoryss.id" value="%{privateCabinetCategory.privateCabinetCategoryss.id}" theme="simple" /> <input type="text" id="privateCabinetCategoryssName" name="privateCabinetCategory.privateCabinetCategoryss.name"
						value="${privateCabinetCategory.privateCabinetCategoryss.name}" readonly="readonly" /> <input class="btn" type="button" value="选择" onclick="chooseParentKnowledgeBaseCategory();" /></td>
				</tr>
				<tr height="40">
					<td align="right">排序号:&nbsp;</td>
					<td><input type="text" id="code" name="privateCabinetCategory.code" class="validate[required] text-input" value="${privateCabinetCategory.code}" /><span style="color: red;">*</span></td>
					<td align="right">文档名称:&nbsp;</td>
					<td><input type="text" id="name" name="privateCabinetCategory.name" class="validate[required] text-input" value="${privateCabinetCategory.name}" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="privateCabinetCategory.memo" size="45" value="${privateCabinetCategory.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>