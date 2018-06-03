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
	<form id="dtForm">
		<s:hidden id="daId" name="punInfo.id" value="%{punInfo.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="punInfo.employeeCode" value="${punInfo.employeeCode}" type="text" /></td>
					<th align="right">惩罚级别:&nbsp;</th>
					<td><input id="disciplinaryCategory" name="punInfo.disciplinaryCategory" value="${punInfo.disciplinaryCategory}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">惩罚名称:&nbsp;</th>
					<td><input id="disciplinaryName" name="punInfo.disciplinaryName" value="${punInfo.disciplinaryName}" type="text" /></td>
					<th align="right">惩罚原因:&nbsp;</th>
					<td><input id="disciplinaryReasons" name="punInfo.disciplinaryReasons" value="${punInfo.disciplinaryReasons}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">惩罚批准时间:&nbsp;</th>
					<td><input id="disciplinaryDateOfApproval" name="disciplinaryDateOfApproval" value="<fmt:formatDate value='${punInfo.disciplinaryDateOfApproval}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('disciplinaryDateOfApproval','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
						height="22" align="absmiddle"></td>
					<th align="right">惩罚批准文件名:&nbsp;</th>
					<td><input id="disciplinaryApprovalOfTheFileName" name="punInfo.disciplinaryApprovalOfTheFileName" value="${punInfo.disciplinaryApprovalOfTheFileName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">惩罚批准文件号:&nbsp;</th>
					<td><input id="disciplinaryApprovalDocument" name="punInfo.disciplinaryApprovalDocument" value="${punInfo.disciplinaryApprovalDocument}" type="text" /></td>
					<th align="right">惩罚批准机关名称:&nbsp;</th>
					<td><input id="disciplinaryApprovalAuthorityName" name="punInfo.disciplinaryApprovalAuthorityName" value="${punInfo.disciplinaryApprovalAuthorityName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">撤销惩戒日期:&nbsp;</th>
					<td><input id="revocationOfDisciplinaryDate" name="revocationOfDisciplinaryDate" value="<fmt:formatDate value='${punInfo.revocationOfDisciplinaryDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('revocationOfDisciplinaryDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
						width="16" height="22" align="absmiddle"></td>
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="punInfo.remarks" value="${punInfo.remarks}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#dtForm").validationEngine();
</script>