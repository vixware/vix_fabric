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
	<form id="daForm">
		<s:hidden id="daId" name="trainingCourse.id" value="%{trainingCourse.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">课程编码:&nbsp;</th>
					<td><input id="courseCode" name="trainingCourse.courseCode" value="${trainingCourse.courseCode}" type="text" /></td>
					<th align="right">课程类别:&nbsp;</th>
					<td><input id="courseType" name="trainingCourse.courseType" value="${trainingCourse.courseType}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">课程名称:&nbsp;</th>
					<td><input id="courseName" name="trainingCourse.courseName" value="${trainingCourse.courseName}" type="text" /></td>
					<th align="right">课程简介:&nbsp;</th>
					<td><input id="courseeducation" name="trainingCourse.courseeducation" value="${trainingCourse.courseeducation}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarkss" name="trainingCourse.remarkss" value="${trainingCourse.remarkss}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#daForm").validationEngine();
</script>