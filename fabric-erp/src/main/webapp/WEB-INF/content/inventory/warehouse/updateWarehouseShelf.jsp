<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#shelfform").validationEngine();
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="shelfform">
		<s:hidden id="invShelfid" name="invShelf.id" value="%{invShelf.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">货区:&nbsp;</td>
					<td><input type="hidden" id="parentId" name="parentId" value="${invShelf.invWarehousezone.id}" onchange="fieldChanged(this);" /> <input type="text" id="parentName" name="parentName" value="${invShelf.invWarehousezone.name}" readonly="readonly" /></td>
				</tr>
				<tr height="40">
					<td align="right">货架编码:&nbsp;</td>
					<td><input type="text" id="invShelfCode" name="invShelfCode" value="${invShelf.code}" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
					<td align="right">货架名称:&nbsp;</td>
					<td><input type="text" id="invShelfName" name="invShelfName" value="${invShelf.name}" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">优先级:&nbsp;</td>
					<td><s:textfield id="priority" name="invShelf.priority" value="%{invShelf.priority}" theme="simple" onchange="fieldChanged(this);" /></td>
					<td align="right">最大存货数量:&nbsp;</td>
					<td><s:textfield id="maximum" name="invShelf.maximum" value="%{invShelf.maximum}" theme="simple" onchange="fieldChanged(this);" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>