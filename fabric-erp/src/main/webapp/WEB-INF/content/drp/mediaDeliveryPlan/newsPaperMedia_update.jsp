<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#newsPaperMediaForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="newsPaperMediaForm">
		<s:hidden id="newsPaperMediaId" name="newsPaperMediaId" value="%{newsPaperMedia.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">栏目名称&nbsp;</th>
					<td><input id="columnName" name="columnName" value="${newsPaperMedia.columnName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">栏目覆盖率:&nbsp;</th>
					<td><input id="columnCoverageRate" name="columnCoverageRate" value="${newsPaperMedia.columnCoverageRate}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">广告版面:&nbsp;</th>
					<td><input id="advertisementPage" name="advertisementPage" value="${newsPaperMedia.advertisementPage}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">广告价格:&nbsp;</th>
					<td><input id="advertisementPrice" name="advertisementPrice" value="${newsPaperMedia.advertisementPrice}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">投放时间:&nbsp;</th>
					<td><input id="cameTime" name="cameTime" value="<s:date format="yyyy-MM-dd" name="%{newsPaperMedia.cameTime}" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('cameTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
						style="color: red;">*</span></td>
					<th align="right">受众人数:&nbsp;</th>
					<td><input id="audience" name="audience" value="${newsPaperMedia.audience}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">广告制作文件名称:&nbsp;</th>
					<td><input id="advertisementFileName" name="advertisementFileName" value="${newsPaperMedia.advertisementFileName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>