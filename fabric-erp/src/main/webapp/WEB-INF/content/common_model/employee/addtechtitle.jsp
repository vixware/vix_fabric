<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="dechtitleForm">
		<s:hidden id="daId" name="techtitle.id" value="%{techtitle.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="techtitle.employeeCode" value="${techtitle.employeeCode}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">专业技术职务系列:&nbsp;</th>
					<td><input id="professionalAndTechnicalQualifications" name="techtitle.professionalAndTechnicalQualifications" value="${techtitle.professionalAndTechnicalQualifications}" type="text" /></td>
					<th align="right">专业技术职务名称:&nbsp;</th>
					<td><input id="professionalAndTechnicalQualificationName" name="techtitle.professionalAndTechnicalQualificationName" value="${techtitle.professionalAndTechnicalQualificationName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">专业级别:&nbsp;</th>
					<td><input id="professionallevel" name="techtitle.professionallevel" value="${techtitle.professionallevel}" type="text" /></td>
					<th align="right">评定机构:&nbsp;</th>
					<td><input id="assessmentOrganization" name="techtitle.assessmentOrganization" value="${techtitle.assessmentOrganization}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">认定机构:&nbsp;</th>
					<td><input id="accreditationInstitution" name="techtitle.accreditationInstitution" value="${techtitle.accreditationInstitution}" type="text" /></td>
					<th align="right">是否国家专业技术资格考试:&nbsp;</th>
					<td><input id="whetherTheNationalProfessionalQualificationExamination" name="techtitle.whetherTheNationalProfessionalQualificationExamination" value="${techtitle.whetherTheNationalProfessionalQualificationExamination}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">是否通过认定:&nbsp;</th>
					<td><input id="whetherThroughTheIdentificationOf" name="techtitle.whetherThroughTheIdentificationOf" value="${techtitle.whetherThroughTheIdentificationOf}" type="text" /></td>
					<th align="right">是否最高资格:&nbsp;</th>
					<td><input id="ifTheHighestQualification" name="techtitle.ifTheHighestQualification" value="${techtitle.ifTheHighestQualification}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">资格证号:&nbsp;</th>
					<td><input id="certificateNo" name="techtitle.certificateNo" value="${techtitle.certificateNo}" type="text" /></td>
					<th align="right">文件号:&nbsp;</th>
					<td><input id="fileNumber" name="techtitle.fileNumber" value="${techtitle.fileNumber}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="techtitle.remark" value="${techtitle.remarks}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#dechtitleForm").validationEngine();
</script>