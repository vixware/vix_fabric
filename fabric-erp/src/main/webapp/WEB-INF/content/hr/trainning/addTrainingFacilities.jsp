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
	<form id="tfForm">
		<s:hidden id="daId" name="trainingFacilities.id" value="%{trainingFacilities.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">设施编码:&nbsp;</th>
					<td><input id="facilitiesCode" name="trainingFacilities.facilitiesCode" value="${trainingFacilities.facilitiesCode}" type="text" /></td>
					<th align="right">设施类别:&nbsp;</th>
					<td><input id="facilitiesType" name="trainingFacilities.facilitiesType" value="${trainingFacilities.facilitiesType}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">设施名称:&nbsp;</th>
					<td><input id="facilitiesName" name="trainingFacilities.facilitiesName" value="${trainingFacilities.facilitiesName}" type="text" /></td>
					<th align="right">培训教室:&nbsp;</th>
					<td><input id="classroomTraining" name="trainingFacilities.classroomTraining" value="${trainingFacilities.classroomTraining}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">数量:&nbsp;</th>
					<td><input id="facilitiesNumber" name="trainingFacilities.facilitiesNumber" value="${trainingFacilities.facilitiesNumber}" type="text" /></td>
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarsks" name="trainingFacilities.remarsks" value="${trainingFacilities.remarsks}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#tfForm").validationEngine();
</script>