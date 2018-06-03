<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">
$(function(){
	//设置选中
	$("#educationalBackground").val('${degree.educationalBackground}');
	$("#academicCertificates").val('${degree.academicCertificates}');
	$("#formsOfLearning").val('${degree.formsOfLearning}');
	$("#whether").val('${degree.whether}');
	$("#whetherTheHighestDegree").val('${degree.whetherTheHighestDegree}');
	$("#whetherTheOriginalQualifications").val('${degree.whetherTheOriginalQualifications}');
	$("#degreeGrantedWhetherTheHighestDegree").val('${degree.degreeGrantedWhetherTheHighestDegree}');
	$("#whetherMinorInTheSecondDegree").val('${degree.whetherMinorInTheSecondDegree}');
});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="deForm">
		<s:hidden id="daId" name="degree.id" value="%{degree.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="degree.employeeCode" value="${degree.employeeCode}" type="text" /></td>
					<th align="right">学历:&nbsp;</th>
					<td><select id="educationalBackground" name="educationalBackground" style="width: 50">
							<option value="">请选择</option>
							<option value="1">专科</option>
							<option value="2">本科</option>
							<option value="3">硕士</option>
							<option value="4">博士</option>
					</select></td>
				</tr>
				<tr height="40">
					<th align="right">学历证书:&nbsp;</th>
					<td><select id="academicCertificates" name="academicCertificates" style="width: 50">
							<option value="">请选择</option>
							<option value="1">一般认证证书</option>
							<option value="2">荣誉证书</option>
							<option value="3">国家级认证证书</option>
							<option value="4">其他机构认证证书</option>
					</select></td>
					<th align="right">学历证书编号:&nbsp;</th>
					<td><input id="educationCertificateNumber" name="degree.educationCertificateNumber" value="${degree.educationCertificateNumber}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">专业门类:&nbsp;</th>
					<td><input id="professionalCategory" name="degree.professionalCategory" value="${degree.professionalCategory}" type="text" /></td>
					<th align="right">专业学科:&nbsp;</th>
					<td><input id="professionalDisciplinary" name="degree.professionalDisciplinary" value="${degree.professionalDisciplinary}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">学习形式:&nbsp;</th>
					<td><select id="formsOfLearning" name="formsOfLearning" style="width: 50">
							<option value="">请选择</option>
							<option value="1">全日制</option>
							<option value="2">自考</option>
							<option value="3">成考</option>
					</select></td>
					<th align="right">学制:&nbsp;</th>
					<td><input id="schoolSystem" name="degree.schoolSystem" value="${degree.schoolSystem}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">学校名称:&nbsp;</th>
					<td><input id="schoolName" name="degree.schoolName" value="${degree.schoolName}" type="text" /></td>
					<th align="right">入学时间:&nbsp;</th>
					<td><input id="admissionTime" name="admissionTime" value="<fmt:formatDate value='${degree.admissionTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('admissionTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">是否211或985:&nbsp;</th>
					<td><select id="whether" name="whether" style="width: 50">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
					</select></td>
					<th align="right">是否最高学历:&nbsp;</th>
					<td><select id="whetherTheHighestDegree" name="whetherTheHighestDegree" style="width: 50">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
					</select></td>
				</tr>
				<tr height="40">
					<th align="right">是否原始学历:&nbsp;</th>
					<td><select id="whetherTheOriginalQualifications" name="whetherTheOriginalQualifications" style="width: 50">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
					</select></td>
					<th align="right">是否最高学位:&nbsp;</th>
					<td><select id="degreeGrantedWhetherTheHighestDegree" name="degreeGrantedWhetherTheHighestDegree" style="width: 50">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
					</select></td>
				</tr>
				<tr height="40">
					<th align="right">学位:&nbsp;</th>
					<td><input id="degree" name="degree.degree" value="${degree.degree}" type="text" /></td>
					<th align="right">学位证书编号:&nbsp;</th>
					<td><input id="diplomaNumber" name="degree.diplomaNumber" value="${degree.diplomaNumber}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">学位授予国家:&nbsp;</th>
					<td><input id="degreeGrantingCountries" name="degree.degreeGrantingCountries" value="${degree.degreeGrantingCountries}" type="text" /></td>
					<th align="right">学位授予单位:&nbsp;</th>
					<td><input id="degreeAwarding" name="degree.degreeAwarding" value="${degree.degreeAwarding}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">是否辅修二学位:&nbsp;</th>
					<td><select id="whetherMinorInTheSecondDegree" name="whetherMinorInTheSecondDegree" style="width: 50">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
					</select></td>
					<th align="right">辅修二学位名称:&nbsp;</th>
					<td><input id="minorSecondDegreeName" name="degree.minorSecondDegreeName" value="${degree.minorSecondDegreeName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remark" name="degree.remark" value="${degree.remark}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#deForm").validationEngine();
</script>