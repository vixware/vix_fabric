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
	<form id="ttForm">
		<s:hidden id="daId" name="trainingTeacher.id" value="%{trainingTeacher.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">教师编码:&nbsp;</th>
					<td><input id="teacherCode" name="trainingTeacher.teacherCode" value="${trainingTeacher.teacherCode}" type="text" /></td>
					<th align="right">教师类别:&nbsp;</th>
					<td><input id="teacherType" name="trainingTeacher.teacherType" value="${trainingTeacher.teacherType}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">教师姓名:&nbsp;</th>
					<td><input id="teacherName" name="trainingTeacher.teacherName" value="${trainingTeacher.teacherName}" type="text" /></td>
					<th align="right">学历:&nbsp;</th>
					<td><input id="education" name="trainingTeacher.education" value="${trainingTeacher.education}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">专业:&nbsp;</th>
					<td><input id="professional" name="trainingTeacher.professional" value="${trainingTeacher.professional}" type="text" /></td>
					<th align="right">联系电话:&nbsp;</th>
					<td><input id="telephone" name="trainingTeacher.telephone" value="${trainingTeacher.telephone}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">教师简介:&nbsp;</th>
					<td><input id="teacherIntroduction" name="trainingTeacher.teacherIntroduction" value="${trainingTeacher.teacherIntroduction}" type="text" /></td>
					<th align="right">备注:&nbsp;</th>
					<td><input id="remark" name="trainingTeacher.remark" value="${trainingTeacher.remark}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#ttForm").validationEngine();
</script>