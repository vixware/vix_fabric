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
	<form id="caForm">
		<s:hidden id="daId" name="computerLevel.id" value="%{computerLevel.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="computerLevel.employeeCode" value="${computerLevel.employeeCode}" type="text" /></td>
					<th align="right">计算机水平级别:&nbsp;</th>
					<td><input id="computerProficiencyLevel" name="computerLevel.computerProficiencyLevel" value="${computerLevel.computerProficiencyLevel}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">证书名称:&nbsp;</th>
					<td><input id="obtainCertificateName" name="computerLevel.obtainCertificateName" value="${computerLevel.obtainCertificateName}" type="text" /></td>
					<th align="right">其他证书名称:&nbsp;</th>
					<td><input id="otherCertificateName" name="computerLevel.otherCertificateName" value="${computerLevel.otherCertificateName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">证书取得时间:&nbsp;</th>
					<td><input id="certificateToObtainTime" name="certificateToObtainTime" value="<fmt:formatDate value='${computerLevel.certificateToObtainTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('certificateToObtainTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
						height="22" align="absmiddle"></td>
					<th align="right">认证机构:&nbsp;</th>
					<td><input id="certificationBody" name="computerLevel.certificationBody" value="${computerLevel.certificationBody}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="computerLevel.remarks" value="${computerLevel.remarks}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#caForm").validationEngine();
</script>