<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#marketActivityForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="marketActivityForm">
		<s:hidden id="marketActivityId" name="marketActivityId" value="%{marketActivity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">活动主题:&nbsp;</th>
					<td><input id="marketActivityName" name="marketActivityName" value="${marketActivity.name}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">活动类型:&nbsp;</th>
					<td><input id="type" name="type" value="${marketActivity.type}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">促销产品名称:&nbsp;</th>
					<td><input id="promotionalProductName" name="promotionalProductName" value="${marketActivity.promotionalProductName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">新产品名称:&nbsp;</th>
					<td><input id="newProductName" name="newProductName" value="${marketActivity.newProductName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">开始时间 :&nbsp;</th>
					<td><input id="startTime" name="startTime" value="<s:date format="yyyy-MM-dd" name="%{#marketActivity.startTime}" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
						style="color: red;">*</span></td>
					<th align="right">结束时间 :&nbsp;</th>
					<td><input id="endTime" name="endTime" value="<s:date format="yyyy-MM-dd" name="%{#marketActivity.endTime}" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
						style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>