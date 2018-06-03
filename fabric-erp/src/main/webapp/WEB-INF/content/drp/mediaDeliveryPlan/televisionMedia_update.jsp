<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#televisionMediaForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="televisionMediaForm">
		<s:hidden id="televisionMediaId" name="televisionMediaId" value="%{televisionMedia.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">频道名称:&nbsp;</th>
					<td><input id="channelName" name="channelName" value="${televisionMedia.channelName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">媒体栏目:&nbsp;</th>
					<td><input id="mediaColumn" name="mediaColumn" value="${televisionMedia.mediaColumn}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">栏目覆盖率:&nbsp;</th>
					<td><input id="columnCoverageRate" name="columnCoverageRate" value="${televisionMedia.columnCoverageRate}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">栏目开播时间 :&nbsp;</th>
					<td><input id="columnStartTime" name="columnStartTime" value="<s:date format="yyyy-MM-dd" name="%{televisionMedia.columnStartTime}" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('columnStartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">播放时长:&nbsp;</th>
					<td><input id="playTimes" name="playTimes" value="${televisionMedia.playTimes}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">广告时长:&nbsp;</th>
					<td><input id="advertisementTimes" name="advertisementTimes" value="${televisionMedia.advertisementTimes}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">广告价格:&nbsp;</th>
					<td><input id="advertisementPrice" name="advertisementPrice" value="${televisionMedia.advertisementPrice}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">投放时间:&nbsp;</th>
					<td><input id="cameTime" name="cameTime" value="<s:date format="yyyy-MM-dd" name="%{televisionMedia.cameTime}" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('cameTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
						style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">受众人数:&nbsp;</th>
					<td><input id="audience" name="audience" value="${televisionMedia.audience}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">广告制作文件名称:&nbsp;</th>
					<td><input id="advertisementFileName" name="advertisementFileName" value="${televisionMedia.advertisementFileName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>