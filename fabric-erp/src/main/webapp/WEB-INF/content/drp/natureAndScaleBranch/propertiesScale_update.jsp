<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#propertiesScaleForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="propertiesScaleForm">
		<s:hidden id="propertiesScaleId" name="propertiesScaleId" value="%{propertiesScale.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">所有性质:&nbsp;</th>
					<td><input id="allProperties" name="allProperties" value="${propertiesScale.allProperties}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">业务管理信息化状况:&nbsp;</th>
					<td><input id="bmic" name="bmic" value="${propertiesScale.bmic}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">人员规模:&nbsp;</th>
					<td><input id="workerScale" name="workerScale" value="${propertiesScale.workerScale}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">销售人员规模:&nbsp;</th>
					<td><input id="salesmanScale" name="salesmanScale" value="${propertiesScale.salesmanScale}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>