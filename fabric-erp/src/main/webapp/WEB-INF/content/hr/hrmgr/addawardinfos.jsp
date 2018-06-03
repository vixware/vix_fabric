<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<%-- <script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script> --%>




<script type="text/javascript">
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="deForm">
		<s:hidden id="daId" name="awardInfo.id" value="%{awardInfo.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="awardInfo.employeeCode" value="${awardInfo.employeeCode}" type="text" /></td>
					<th align="right">荣誉奖励级别:&nbsp;</th>
					<td><input id="awardsLevel" name="awardInfo.awardsLevel" value="${awardInfo.awardsLevel}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">荣誉奖励名称:&nbsp;</th>
					<td><input id="awardsName" name="awardInfo.awardsName" value="${awardInfo.awardsName}" type="text" /></td>
					<th align="right">荣誉奖励原因:&nbsp;</th>
					<td><input id="awardsReasons" name="awardInfo.awardsReasons" value="${awardInfo.awardsReasons}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">荣誉奖励批准日期:&nbsp;</th>
					<td><input id="awardsTheDateOfApproval" name="awardsTheDateOfApproval" value="<fmt:formatDate value='${awardInfo.awardsTheDateOfApproval}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('awardsTheDateOfApproval','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
					<th align="right">荣誉奖励批准文件号:&nbsp;</th>
					<td><input id="awardsApprovedFileNumber" name="awardInfo.awardsApprovedFileNumber" value="${awardInfo.awardsApprovedFileNumber}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">荣誉奖励批准机关名称:&nbsp;</th>
					<td><input id="awardsApprovedFileNameOfTheAuthority" name="awardInfo.awardsApprovedFileNameOfTheAuthority" value="${awardInfo.awardsApprovedFileNameOfTheAuthority}" type="text" /></td>
					<th align="right">荣誉奖励批准文件名:&nbsp;</th>
					<td><input id="awardsApprovedFileName" name="awardInfo.awardsApprovedFileName" value="${awardInfo.awardsApprovedFileName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="awardInfo.remarks" value="${awardInfo.remarks}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#deForm").validationEngine();
</script>