<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<%-- <script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script> --%>




<script type="text/javascript">
$(function(){
	//设置单据类型选中
	$("#publicationType").val('${details.publicationType}');
	$("#publicationStruts").val('${details.publicationStruts}');
});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="details.id" value="%{details.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">招聘组织部门:&nbsp;</th>
					<td><input id="orgDepartment" name="orgDepartment" value="${details.orgDepartment}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(3);" /> <span
						style="color: red;">*</span></td>
					<th align="right">招聘职位:&nbsp;</th>
					<td><input id="job" name="job" value="${details.job}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(2);" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">发布类型:&nbsp;</th>
					<td><select id="publicationType" name="publicationType" style="width: 50">
							<option value="">请选择</option>
							<option value="1">内部</option>
							<option value="2">外部</option>
							<option value="3">内部和外部</option>
					</select></td>
					<th align="right">发布状态:&nbsp;</th>
					<td><select id="publicationStruts" name="publicationStruts" style="width: 50">
							<option value="">请选择</option>
							<option value="1">已发布</option>
							<option value="2">未发布</option>
					</select></td>
				</tr>
				<tr height="40">
					<th align="right">有效期限:&nbsp;</th>
					<td><input id="lifeSpan" name="lifeSpan" value="<fmt:formatDate value='${details.lifeSpan }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('lifeSpan','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">工作地点:&nbsp;</th>
					<td><input id="workingPlace" name="details.workingPlace" value="${details.workingPlace}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">招聘在编人数:&nbsp;</th>
					<td><input id="recruitmentInTheSeriesNumber" name="details.recruitmentInTheSeriesNumber" value="${details.recruitmentInTheSeriesNumber}" type="text" /></td>
					<th align="right">招聘非在编人数:&nbsp;</th>
					<td><input id="recruitmentOfTheUnofficialNumber" name="details.recruitmentOfTheUnofficialNumber" value="${details.recruitmentOfTheUnofficialNumber}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">招聘要求:&nbsp;</th>
					<td><input id="recruitmentRequirements" name="details.recruitmentRequirements" value="${details.recruitmentRequirements}" type="text" /></td>
					<th align="right">工作职责:&nbsp;</th>
					<td><input id="operatingDuty" name="details.operatingDuty" value="${details.operatingDuty}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#daForm").validationEngine();
</script>