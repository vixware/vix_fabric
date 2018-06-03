<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
$("#orderItemForm").validationEngine();

</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="supplierCategoryForm">
		<s:hidden id="newId" name="hrTitleSys.id" value="%{hrTitleSys.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">职称编码:&nbsp;</th>
					<td><input id="titlesysCode" name="hrTitleSys.titlesysCode" value="${hrTitleSys.titlesysCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">职称名称:&nbsp;</th>
					<td><input id="titlesysName" name="hrTitleSys.titlesysName" value="${hrTitleSys.titlesysName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">职称类型:&nbsp;</th>
					<td><s:select id="titleTypeId" headerKey="-1" headerValue="--请选择--" list="%{titleTypeList}" listValue="name" listKey="id" value="%{hrTitleSys.titleType.id}" multiple="false" theme="simple">
						</s:select> <span style="color: red;">*</span></td>
					<th align="right">职称等级:&nbsp;</th>
					<td><s:select id="titleGradeId" headerKey="-1" headerValue="--请选择--" list="%{titleGradeList}" listValue="name" listKey="id" value="%{hrTitleSys.titleGrade.id}" multiple="false" theme="simple">
						</s:select> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">

					<th align="right">技术水平:&nbsp;</th>
					<td><s:select id="technicalLevelId" headerKey="-1" headerValue="--请选择--" list="%{technicalLevelList}" listValue="name" listKey="id" value="%{hrTitleSys.technicalLevel.id}" multiple="false" theme="simple">
						</s:select> <span style="color: red;">*</span></td>
					<th align="right">熟练程度:&nbsp;</th>
					<td><s:select id="proficiencyId" headerKey="-1" headerValue="--请选择--" list="%{proficiencyList}" listValue="name" listKey="id" value="%{hrTitleSys.proficiency.id}" multiple="false" theme="simple">
						</s:select> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">技术名称:&nbsp;</th>
					<td><input id="technicalName" name="hrTitleSys.technicalName" value="${hrTitleSys.technicalName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">有效开始时间:&nbsp;</th>
					<td><input id="startTime" name="startTime" value="<fmt:formatDate value='${hrTitleSys.startTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('newstartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span>
					</td>
					<th align="right">有效结束时间:&nbsp;</th>
					<td><input id="endTime" name="endTime" value="<fmt:formatDate value='${hrTitleSys.endTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('newendTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">工作经验:&nbsp;</th>
					<td><textarea id="workExperience" name="workExperience" class="example" rows="6" style="width: 250px; height: 103px;">${hrTitleSys.workExperience}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>