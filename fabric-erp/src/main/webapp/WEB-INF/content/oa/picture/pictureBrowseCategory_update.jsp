<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#pictureBrowseCategoryForm").validationEngine();
	function chooseParentKnowledgeBaseCategory() {
		$.ajax({
		url : '${vix}/oa/pictureBrowseAction!goPrivateCabinetCategory.action',
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
						$("#pictureBrowseCategoryssId").val(result[0]);
						$("#pictureBrowseCategoryssName").val(result[1]); 
					} else {
					 	$("#pictureBrowseCategoryssId").val("");
						$("#pictureBrowseCategoryssName").val(""); 
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
	<form id="pictureBrowseCategoryForm" method="post">
		<s:hidden id="pictureBrowseCategoryId" name="pictureBrowseCategory.id" value="%{pictureBrowseCategory.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">上级分类:&nbsp;</td>
					<td><s:hidden id="pictureBrowseCategoryssId" name="pictureBrowseCategory.pictureBrowseCategoryss.id" value="%{pictureBrowseCategory.pictureBrowseCategoryss.id}" theme="simple" /> <input type="text" id="pictureBrowseCategoryssName" name="pictureBrowseCategory.pictureBrowseCategoryss.name"
						value="${pictureBrowseCategory.pictureBrowseCategoryss.name}" readonly="readonly" /> <input class="btn" type="button" value="选择" onclick="chooseParentKnowledgeBaseCategory();" /></td>
				</tr>
				<tr height="40">
					<td align="right">排序号:&nbsp;</td>
					<td><input type="text" id="code" name="pictureBrowseCategory.code" class="validate[required] text-input" value="${pictureBrowseCategory.code}" /> <span style="color: red;">*</span></td>
					<td align="right">文档名称:&nbsp;</td>
					<td><input type="text" id="name" name="pictureBrowseCategory.name" class="validate[required] text-input" value="${pictureBrowseCategory.name}" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="pictureBrowseCategory.memo" size="45" value="${pictureBrowseCategory.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>