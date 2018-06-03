<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#orderTypeForm").validationEngine();
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="orderTypeForm">
		<s:hidden id="newId" value="%{orderType.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="newCode" value="${orderType.code}" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span></td>
					<th align="right">名称:&nbsp;</th>
					<td><input id="newName" value="${orderType.name}" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">描述:&nbsp;</th>
					<td colspan="3"><textarea rows="3" id="newDescription" style="width: 450px;" onchange="fieldChanged(this);">${orderType.description}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>