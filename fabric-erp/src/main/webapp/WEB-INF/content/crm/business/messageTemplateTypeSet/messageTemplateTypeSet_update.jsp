<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#messageTemplateTypeForm").validationEngine();
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="messageTemplateTypeForm">
		<s:hidden id="messageTemplateTypeId" name="messageTemplateTypeId" value="%{messageTemplateType.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="code" name="code" class="validate[required] text-input" value="${messageTemplateType.code}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="name" name="name" class="validate[required] text-input" value="${messageTemplateType.name}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>