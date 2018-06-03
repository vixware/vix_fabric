<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<%-- <script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script> --%>




<script type="text/javascript">
$(function(){
	//设置选中
	$("#skilledInChengdu").val('${languageAbility.skilledInChengdu}');
	$("#masterLanguageLevel").val('${languageAbility.masterLanguageLevel}');
	$("#typeOfCertificate").val('${languageAbility.typeOfCertificate}');
	$("#otherTypeOfCertificate").val('${languageAbility.otherTypeOfCertificate}');
});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="languageAbility.id" value="%{languageAbility.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="languageAbility.employeeCode" value="${languageAbility.employeeCode}" type="text" /></td>
					<th align="right">语种:&nbsp;</th>
					<td><input id="cassificationOfLanguage" name="languageAbility.cassificationOfLanguage" value="${languageAbility.cassificationOfLanguage}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">熟练程度:&nbsp;</th>
					<td><select id="skilledInChengdu" name="skilledInChengdu" style="width: 50">
							<option value="">请选择</option>
							<option value="1">了解</option>
							<option value="2">熟练</option>
							<option value="3">精通</option>
					</select></td>
					<th align="right">掌握语种水平级别:&nbsp;</th>
					<td><select id="masterLanguageLevel" name="masterLanguageLevel" style="width: 50">
							<option value="">请选择</option>
							<option value="1">A级</option>
							<option value="2">B级</option>
							<option value="3">C级</option>
					</select></td>
				</tr>
				<tr height="40">
					<th align="right">证书类型:&nbsp;</th>
					<td><select id="typeOfCertificate" name="typeOfCertificate" style="width: 50">
							<option value="">请选择</option>
							<option value="1">一般认证证书</option>
							<option value="2">荣誉证书</option>
							<option value="3">国家级认证证书</option>
							<option value="4">其他机构认证证书</option>
					</select></td>
					<th align="right">其他证书类型:&nbsp;</th>
					<td><select id="otherTypeOfCertificate" name="otherTypeOfCertificate" style="width: 50">
							<option value="">请选择</option>
							<option value="1">一般认证证书</option>
							<option value="2">荣誉证书</option>
							<option value="3">国家级认证证书</option>
							<option value="4">其他机构认证证书</option>
					</select></td>
				</tr>
				<tr height="40">
					<th align="right">证书编号:&nbsp;</th>
					<td><input id="certificateNumber" name="languageAbility.certificateNumber" value="${languageAbility.certificateNumber}" type="text" /></td>
					<th align="right">成绩:&nbsp;</th>
					<td><input id="score" name="languageAbility.score" value="${languageAbility.score}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">证书取得时间:&nbsp;</th>
					<td><input id="certificateInTime" name="certificateInTime" value="<fmt:formatDate value='${languageAbility.certificateInTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('certificateInTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
					</td>
					<th align="right">认证机构:&nbsp;</th>
					<td><input id="certificationBody" name="languageAbility.certificationBody" value="${languageAbility.certificationBody}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="languageAbility.remarks" value="${languageAbility.remarks}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#daForm").validationEngine();
</script>