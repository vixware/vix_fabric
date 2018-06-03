<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="subjectForm">
		<s:hidden id="daId" name="subject.id" value="%{subject.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">标题编码:&nbsp;</th>
					<td><input id="subjectCode" name="subject.subjectCode" value="${subject.subjectCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">标的名称:&nbsp;</th>
					<td><input id="subjectName" name="subject.subjectName" value="${subject.subjectName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">来源:&nbsp;</th>
					<td><input id="source" name="subject.source" value="${subject.source}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">存货分类编码:&nbsp;</th>
					<td><input id="stockClassificationCode" name="subject.stockClassificationCode" value="${subject.stockClassificationCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">对应存货编码:&nbsp;</th>
					<td><input id="correspondingInventoryCode" name="subject.correspondingInventoryCode" value="${subject.correspondingInventoryCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">项目大类:&nbsp;</th>
					<td><input id="projectCatalog" name="subject.projectCatalog" value="${subject.projectCatalog}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">存货规格型号:&nbsp;</th>
					<td><input id="inventoriesSpecification" name="subject.inventoriesSpecification" value="${subject.inventoriesSpecification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">无税原币单价:&nbsp;</th>
					<td><input id="nnTOCurrencyPrice" name="subject.nnTOCurrencyPrice" value="${subject.nnTOCurrencyPrice}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">含税原币单价:&nbsp;</th>
					<td><input id="ttIOriginalCurrencyPrice" name="subject.ttIOriginalCurrencyPrice" value="${subject.ttIOriginalCurrencyPrice}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">无税原币金额:&nbsp;</th>
					<td><input id="nnTaxAmountOriginalCurrency" name="subject.nnTaxAmountOriginalCurrency" value="${subject.nnTaxAmountOriginalCurrency}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">含税原币金额:&nbsp;</th>
					<td><input id="ttATOriginalCurrency" name="subject.ttATOriginalCurrency" value="${subject.ttATOriginalCurrency}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">执行数量:&nbsp;</th>
					<td><input id="executionQuantity" name="subject.executionQuantity" value="${subject.executionQuantity}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">执行无税金额原币:&nbsp;</th>
					<td><input id="eeTAOriginalCurrency" name="subject.eeTAOriginalCurrency" value="${subject.eeTAOriginalCurrency}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">执行含税金额原币:&nbsp;</th>
					<td><input id="eeTAIncLriginalCurrency" name="subject.eeTAIncLriginalCurrency" value="${subject.eeTAIncLriginalCurrency}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">开始时间:&nbsp;</th>
					<td><input id="startTime" name="startTime" value="<fmt:formatDate value='${subject.startTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">结束时间:&nbsp;</th>
					<td><input id="endTime" name="endTime" value="<fmt:formatDate value='${subject.endTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#subjectForm" ).validationEngine ( );
</script>