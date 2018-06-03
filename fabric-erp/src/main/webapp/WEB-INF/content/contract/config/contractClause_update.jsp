<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

</script>

<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="brandForm">
		<input type="hidden" id="id" name="id" value="${contractClause.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">合同编号:&nbsp;</th>
					<td><input id="contractCode" name="contractClause.contractCode" value="${contractClause.contractCode}" data-text-tooltip="Tip tip tip tip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" class="validate[required] text-input" /></td>
					<th>合同类型:&nbsp;</th>
					<td><select id=type name="type" value="${contractClause.type}" class="select-form" style="height: 24px; line-height: 24px;">
							<option>采购</option>
							<option>销售</option>
					</select></td>
				</tr>
				<tr>
					<th>状态:&nbsp;</th>
					<td><select id=mode name="mode" value="${contractClause.mode}" class="select-form" style="height: 24px; line-height: 24px;">
							<option>一般</option>
							<option>特殊</option>
					</select></td>
				</tr>
				<tr>
					<th align="right">条款内容:</th>
					<td colspan="3"><textarea id="clauseContent" name="clauseContent" class="example" rows="2" style="width: 550px; height: 130px;">${contractClause.clauseContent }</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>