<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#policiesCategoryForm").validationEngine();
	function chooseCategory() {
		$.ajax({
		url : '${vix}/hr/policiesAction!goPoliciesCategory.action',
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
						$("#polSysManageCategoryssId").val(result[0]);
						$("#polSysManageCategoryssName").val(result[1]); 
					} else {
					 	$("#polSysManageCategoryssId").val("");
						$("#polSysManageCategoryssName").val(""); 
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
	<form id="policiesCategoryForm" method="post">
		<s:hidden id="polSysManageCategoryId" name="polSysManageCategory.id" value="%{polSysManageCategory.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">上级分类:&nbsp;</td>
					<td><s:hidden id="polSysManageCategoryssId" name="polSysManageCategory.polSysManageCategoryss.id" value="%{polSysManageCategory.polSysManageCategoryss.id}" theme="simple" /> <input type="text" id="polSysManageCategoryssName" name="polSysManageCategory.polSysManageCategoryss.name"
						value="${polSysManageCategory.polSysManageCategoryss.name}" readonly="readonly" /> <input class="btn" type="button" value="选择" onclick="chooseCategory();" /></td>
				</tr>
				<tr height="40">
					<td align="right">排序号:&nbsp;</td>
					<td><input type="text" id="code" name="polSysManageCategory.code" class="validate[required] text-input" value="${polSysManageCategory.code}" /><span style="color: red;">*</span></td>
					<td align="right">文档名称:&nbsp;</td>
					<td><input type="text" id="name" name="polSysManageCategory.name" class="validate[required] text-input" value="${polSysManageCategory.name}" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="polSysManageCategory.memo" size="45" value="${polSysManageCategory.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>