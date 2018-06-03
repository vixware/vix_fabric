<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">
$(function(){
	//设置选中
	$("#whetherTheNationalProfessionalQualificationExamination").val('${workQualify.whetherTheNationalProfessionalQualificationExamination}');
	$("#whetherThroughTheIdentificationOf").val('${workQualify.whetherThroughTheIdentificationOf}');
	$("#ifTheHighestQualification").val('${workQualify.ifTheHighestQualification}');
});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="waForm">
		<s:hidden id="daId" name="workQualify.id" value="%{workQualify.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="workQualify.employeeCode" value="${workQualify.employeeCode}" type="text" /></td>
					<th align="right">级别:&nbsp;</th>
					<td><input id="level" name="workQualify.level" value="${workQualify.level}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">专业技术资格系列:&nbsp;</th>
					<td><input id="professionalAndTechnicalQualifications" name="workQualify.professionalAndTechnicalQualifications" value="${workQualify.professionalAndTechnicalQualifications}" type="text" /></td>

					<th align="right">专业技术资格名称:&nbsp;</th>
					<td><input id="professionalAndTechnicalQualificationName" name="workQualify.professionalAndTechnicalQualificationName" value="${workQualify.professionalAndTechnicalQualificationName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">评定机构:&nbsp;</th>
					<td><input id="assessmentOrganization" name="workQualify.assessmentOrganization" value="${workQualify.assessmentOrganization}" type="text" /></td>

					<th align="right">认定机构:&nbsp;</th>
					<td><input id="accreditationInstitution" name="workQualify.accreditationInstitution" value="${workQualify.accreditationInstitution}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">是否国家专业技术资格考试:&nbsp;</th>
					<td><select id="whetherTheNationalProfessionalQualificationExamination" name="whetherTheNationalProfessionalQualificationExamination" style="width: 50">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
					</select></td>
					<th align="right">是否通过认定:&nbsp;</th>
					<td><select id="whetherThroughTheIdentificationOf" name="whetherThroughTheIdentificationOf" style="width: 50">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
					</select></td>
				</tr>
				<tr height="40">
					<th align="right">资格证号:&nbsp;</th>
					<td><input id="certificateNo" name="workQualify.certificateNo" value="${workQualify.certificateNo}" type="text" /></td>
					<th align="right">文件号:&nbsp;</th>
					<td><input id="fileNumber" name="workQualify.fileNumber" value="${workQualify.fileNumber}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="workQualify.remarks" value="${workQualify.remarks}" type="text" /></td>
					<th align="right">是否最高资格:&nbsp;</th>
					<td><select id="ifTheHighestQualification" name="ifTheHighestQualification" style="width: 50">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
					</select></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#waForm").validationEngine();
</script>