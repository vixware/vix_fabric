<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#bookCategoryForm").validationEngine();
	function bookBaseCategory() {
		$.ajax({
		url : '${vix}/oa/bookEntryAction!goBookCategory.action',
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
						$("#parentBookCategoryId").val(result[0]);
						$("#parentBookCategoryName").val(result[1]); 
					} else {
					 	$("#parentBookCategoryId").val("");
						$("#parentBookCategoryName").val(""); 
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
		<s:hidden id="bookCategoryId" name="bookCategory.id" value="%{bookCategory.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">上级分类:&nbsp;</td>
					<td><input type="hidden" id="parentBookCategoryId" name="bookCategory.parentBookCategory.id"> <input type="text" id="parentBookCategoryName" name="bookCategory.parentBookCategory.name" value="${bookCategory.parentBookCategory.name}" readonly="readonly" /> <input class="btn" type="button" value="选择" onclick="bookBaseCategory();" />
					</td>
				</tr>
				<tr height="40">
					<td align="right">排序号:&nbsp;</td>
					<td><input type="text" id="code" name="bookCategory.code" class="validate[required] text-input" value="${bookCategory.code}" /><span style="color: red;">*</span></td>
					<td align="right">分类名称:&nbsp;</td>
					<td><input type="text" id="name" name="bookCategory.name" class="validate[required] text-input" value="${bookCategory.name}" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="bookCategory.memo" size="45" value="${bookCategory.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>