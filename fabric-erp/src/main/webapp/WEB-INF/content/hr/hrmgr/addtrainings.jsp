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
		<s:hidden id="daId" name="training.id" value="%{training.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="training.employeeCode" value="${training.employeeCode}" type="text" /></td>
					<th align="right">培训机构:&nbsp;</th>
					<td><input id="trainingAgency" name="training.trainingAgency" value="${training.trainingAgency}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">培训类别:&nbsp;</th>
					<td><input id="trainingClass" name="training.trainingClass" value="${training.trainingClass}" type="text" /></td>
					<th align="right">主办层次:&nbsp;</th>
					<td><input id="hostLevel" name="training.hostLevel" value="${training.hostLevel}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">培训项目名称:&nbsp;</th>
					<td><input id="projectTraining" name="training.projectTraining" value="${training.projectTraining}" type="text" /></td>
					<th align="right">培训对象:&nbsp;</th>
					<td><input id="trainingObjects" name="training.trainingObjects" value="${training.trainingObjects}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">培训内容:&nbsp;</th>
					<td><input id="trainingContent" name="training.trainingContent" value="${training.trainingContent}" type="text" /></td>
					<th align="right">培训形式:&nbsp;</th>
					<td><input id="trainingForm" name="training.trainingForm" value="${training.trainingForm}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">培训教材:&nbsp;</th>
					<td><input id="trainingMaterials" name="training.trainingMaterials" value="${training.trainingMaterials}" type="text" /></td>
					<th align="right">培训讲师:&nbsp;</th>
					<td><input id="trainingInstructor" name="training.trainingInstructor" value="${training.trainingInstructor}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">证书取得时间:&nbsp;</th>
					<td><input id="planTime" name="planTime" value="<fmt:formatDate value='${training.planTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('planTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">学时:&nbsp;</th>
					<td><input id="cassHour" name="training.cassHour" value="${training.cassHour}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">人数:&nbsp;</th>
					<td><input id="peopleNumber" name="training.peopleNumber" value="${training.peopleNumber}" type="text" /></td>
					<th align="right">期数:&nbsp;</th>
					<td><input id="periods" name="training.periods" value="${training.periods}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">联系人:&nbsp;</th>
					<td><input id="contacts" name="training.contacts" value="${training.contacts}" type="text" /></td>
					<th align="right">联系电话:&nbsp;</th>
					<td><input id="contactNumber" name="training.contactNumber" value="${training.contactNumber}" type="text" /></td>
				</tr>
				<tr height="40">

					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="training.remarks" value="${training.remarks}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#dtForm").validationEngine();
</script>