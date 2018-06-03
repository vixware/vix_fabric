<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="detail.id" value="%{detail.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">培训方式:&nbsp;</th>
					<td><input id="trainingWay" name="detail.trainingWay" value="${detail.trainingWay}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">培训目标:&nbsp;</th>
					<td><input id="goalOfTraining" name="detail.goalOfTraining" value="${detail.goalOfTraining}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">师资来源:&nbsp;</th>
					<td><input id="sourcesOfTeachers" name="detail.sourcesOfTeachers" value="${detail.sourcesOfTeachers}" type="text" /></td>
					<th align="right">培训课程:&nbsp;</th>
					<td><input id="trainingCourse" name="detail.trainingCourse" value="${detail.trainingCourse}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">培训地点:&nbsp;</th>
					<td><input id="trainingSite" name="detail.trainingSite" value="${detail.trainingSite}" type="text" /></td>
					<th align="right">培训机构:&nbsp;</th>
					<td><input id="trainingInstitutions" name="detail.trainingInstitutions" value="${detail.trainingInstitutions}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">计划开始时间:&nbsp;</th>
					<td><input id="planStartDate" name="planStartDate" value="<fmt:formatDate value='${detail.planStartDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('planStartDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">计划结束时间:&nbsp;</th>
					<td><input id="planEndDate" name="planEndDate" value="<fmt:formatDate value='${detail.planEndDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('planEndDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">课程学时:&nbsp;</th>
					<td><input id="curriculumClassHours" name="detail.curriculumClassHours" value="${detail.curriculumClassHours}" type="text" /></td>
					<th align="right">培训内容:&nbsp;</th>
					<td><input id="trainingContent" name="detail.trainingContent" value="${detail.trainingContent}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#daForm" ).validationEngine ( );
</script>