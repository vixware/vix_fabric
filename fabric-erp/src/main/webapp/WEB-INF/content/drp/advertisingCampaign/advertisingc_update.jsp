<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#advertisingCampaignForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="advertisingCampaignForm">
		<s:hidden id="advertisingCampaignId" name="advertisingCampaignId" value="%{advertisingCampaign.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">媒体名称:&nbsp;</th>
					<td><input id="mediaName" name="mediaName" value="${advertisingCampaign.mediaName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">栏目名称:&nbsp;</th>
					<td><input id="columnName" name="columnName" value="${advertisingCampaign.columnName}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">媒体类别:&nbsp;</th>
					<td><input id="type" name="type" value="${advertisingCampaign.type}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">媒体数量:&nbsp;</th>
					<td><input id="amount" name="amount" value="${advertisingCampaign.amount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">每周投放次数:&nbsp;</th>
					<td><input id="deliveryTimes" name="deliveryTimes" value="${advertisingCampaign.deliveryTimes}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">版面时间 :&nbsp;</th>
					<td><input id="spaceTime" name="spaceTime" value="<s:date format="yyyy-MM-dd" name="%{#advertisingCampaign.spaceTime}" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('spaceTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
						style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>