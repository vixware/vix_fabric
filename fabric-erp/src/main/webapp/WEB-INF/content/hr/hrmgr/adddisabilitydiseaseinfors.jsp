<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>


<script type="text/javascript">

</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="dtForm">
		<s:hidden id="daId" name="disabilityDiseaseInfor.id" value="%{disabilityDiseaseInfor.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="disabilityDiseaseInfor.employeeCode" value="${disabilityDiseaseInfor.employeeCode}" type="text" /></td>
					<th align="right">伤残病类型:&nbsp;</th>
					<td><input id="disabilityDiseaseType" name="disabilityDiseaseInfor.disabilityDiseaseType" value="${disabilityDiseaseInfor.disabilityDiseaseType}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">伤残病名称:&nbsp;</th>
					<td><input id="disabilityDiseaseName" name="disabilityDiseaseInfor.disabilityDiseaseName" value="${disabilityDiseaseInfor.disabilityDiseaseName}" type="text" /></td>
					<th align="right">伤残病原因:&nbsp;</th>
					<td><input id="disabilityDiseaseReasons" name="disabilityDiseaseInfor.disabilityDiseaseReasons" value="${disabilityDiseaseInfor.disabilityDiseaseReasons}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">伤残病程度:&nbsp;</th>
					<td><input id="disabilityDiseaseExtent" name="disabilityDiseaseInfor.disabilityDiseaseExtent" value="${disabilityDiseaseInfor.disabilityDiseaseExtent}" type="text" /></td>
					<th align="right">受伤日期:&nbsp;</th>
					<td><input id="dateOfInjury" name="dateOfInjury" value="<fmt:formatDate value='${disabilityDiseaseInfor.dateOfInjury}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('dateOfInjury','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">确诊日期:&nbsp;</th>
					<td><input id="dateOfConfirmation" name="dateOfConfirmation" value="<fmt:formatDate value='${disabilityDiseaseInfor.dateOfConfirmation}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('dateOfConfirmation','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
					<th align="right">康复日期:&nbsp;</th>
					<td><input id="rehabilitationDate" name="rehabilitationDate" value="<fmt:formatDate value='${disabilityDiseaseInfor.rehabilitationDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('rehabilitationDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">诊断机构:&nbsp;</th>
					<td><input id="diagnosisAgencies" name="disabilityDiseaseInfor.diagnosisAgencies" value="${disabilityDiseaseInfor.diagnosisAgencies}" type="text" /></td>
					<th align="right">工伤证号:&nbsp;</th>
					<td><input id="workInjuryCardNumber" name="disabilityDiseaseInfor.workInjuryCardNumber" value="${disabilityDiseaseInfor.workInjuryCardNumber}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">职业病名称:&nbsp;</th>
					<td><input id="occupationalDiseaseName" name="disabilityDiseaseInfor.occupationalDiseaseName" value="${disabilityDiseaseInfor.occupationalDiseaseName}" type="text" /></td>
					<th align="right">残疾证号:&nbsp;</th>
					<td><input id="disabilityCardNumber" name="disabilityDiseaseInfor.disabilityCardNumber" value="${disabilityDiseaseInfor.disabilityCardNumber}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">鉴定类别:&nbsp;</th>
					<td><input id="ldentificationCategory" name="disabilityDiseaseInfor.ldentificationCategory" value="${disabilityDiseaseInfor.ldentificationCategory}" type="text" /></td>
					<th align="right">鉴定日期:&nbsp;</th>
					<td><input id="ldentificationDate" name="ldentificationDate" value="<fmt:formatDate value='${disabilityDiseaseInfor.ldentificationDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('ldentificationDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
				</tr>
				</tr>
				<tr height="40">
					<th align="right">伤残病鉴定等级:&nbsp;</th>
					<td><input id="disabilityDiseaseIdentificationLevel" name="disabilityDiseaseInfor.disabilityDiseaseIdentificationLevel" value="${disabilityDiseaseInfor.disabilityDiseaseIdentificationLevel}" type="text" /></td>
					<th align="right">护理等级:&nbsp;</th>
					<td><input id="careLevel" name="disabilityDiseaseInfor.careLevel" value="${disabilityDiseaseInfor.careLevel}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="disabilityDiseaseInfor.remarks" value="${disabilityDiseaseInfor.remarks}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#dtForm").validationEngine();
</script>