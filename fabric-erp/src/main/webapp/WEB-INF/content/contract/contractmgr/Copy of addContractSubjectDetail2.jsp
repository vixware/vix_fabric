<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>

<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="contractSubject.id" value="%{contractSubject.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">标的编码:&nbsp;</th>
					<td><input id="subjectCode" name="contractSubject.subjectCode" value="${contractSubject.subjectCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">标的名称:&nbsp;</th>
					<td><input id="subjectName" name="contractSubject.subjectName" value="${contractSubject.subjectName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">来源:&nbsp;</th>
					<td><input id="source" name="contractSubject.source" value="${contractSubject.source}" type="text" /></td>
					<th align="right">存货分类编码:&nbsp;</th>
					<td><input id="stockClassificationCode" name="contractSubject.stockClassificationCode" value="${contractSubject.stockClassificationCode}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">存货分类编码:&nbsp;</th>
					<td><input id="stockClassificationCode" name="contractSubject.stockClassificationCode" value="${contractSubject.stockClassificationCode}" type="text" /></td>
					<th align="right">对应存货编码:&nbsp;</th>
					<td><input id="correspondingInventoryCode" name="contractSubject.correspondingInventoryCode" value="${contractSubject.correspondingInventoryCode}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">项目大类:&nbsp;</th>
					<td><input id="projectCatalog" name="contractSubject.projectCatalog" value="${contractSubject.projectCatalog}" type="text" /></td>
					<th align="right">存货规格型号:&nbsp;</th>
					<td><input id="inventoriesSpecification" name="contractSubject.inventoriesSpecification" value="${contractSubject.inventoriesSpecification}" type="text" /></td>
				</tr>
				<tr height="40">
					<%-- <th align="right">无税原币单价：</th>					
					<td><input class="easyui-numberbox" id="nnTOCurrencyPrice"
						name="nnTOCurrencyPrice" value="${contractSubject.nnTOCurrencyPrice}"
						type="text"
						data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input>
					</td>	 --%>
					<th align="right">无税原币单价：</th>
					<td><input id="nnTOCurrencyPrice" name="nnTOCurrencyPrice" value="${contractSubject.nnTOCurrencyPrice}" type="text" /></td>
					<th align="right">含税原币单价：</th>
					<td><input id="ttIOriginalCurrencyPrice" name="ttIOriginalCurrencyPrice" value="${contractSubject.ttIOriginalCurrencyPrice}" type="text" /></td>
					<%-- <td><input class="easyui-numberbox" id="ttIOriginalCurrencyPrice"
						name="ttIOriginalCurrencyPrice" value="${contractSubject.ttIOriginalCurrencyPrice}"
						type="text"
						data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input>
					</td>	 --%>
				</tr>
				<tr height="40">
					<th align="right">无税原币金额:&nbsp;</th>
					<td><input id="nnTaxAmountOriginalCurrency" name="nnTaxAmountOriginalCurrency" value="${contractSubject.nnTaxAmountOriginalCurrency}" type="text" /></td>
					<%-- <td><input class="easyui-numberbox" id="nnTaxAmountOriginalCurrency"
						name="nnTaxAmountOriginalCurrency" value="${contractSubject.nnTaxAmountOriginalCurrency}"
						type="text"
						data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input>
					</td> --%>
					<th align="right">含税原币金额:&nbsp;</th>
					<td><input id="ttATOriginalCurrency" name="ttATOriginalCurrency" value="${contractSubject.ttATOriginalCurrency}" type="text" /></td>
					<%-- <td><input class="easyui-numberbox" id="ttATOriginalCurrency"
						name="ttATOriginalCurrency" value="${contractSubject.ttATOriginalCurrency}"
						type="text"
						data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input>
					</td> --%>
				</tr>
				<tr height="40">
					<th align="right">执行数量:&nbsp;</th>
					<td><input id="executionQuantity" name="contractSubject.executionQuantity" value="${contractSubject.executionQuantity}" type="text" /></td>
					<th align="right">执行无税金额原币:&nbsp;</th>
					<td><input id="eeTAOriginalCurrency" name="contractSubject.eeTAOriginalCurrency" value="${contractSubject.eeTAOriginalCurrency}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">执行含税金额原币:&nbsp;</th>
					<td><input id="eeTAIncLriginalCurrency" name="contractSubject.eeTAIncLriginalCurrency" value="${contractSubject.eeTAIncLriginalCurrency}" type="text" /></td>
					<th align="right">开始时间:&nbsp;</th>
					<td><input id="startTime" name="startTime" value="<fmt:formatDate value='${contractSubject.startTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">结束时间:&nbsp;</th>
					<td><input id="endTime" name="endTime" value="<fmt:formatDate value='${contractSubject.endTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#daForm" ).validationEngine ( );
</script>