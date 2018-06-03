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
	<form id="pmContractForm">
		<s:hidden id="daId" name="pmContract.id" value="%{pmContract.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">合同当事人:&nbsp;</th>
					<td><input id="contractingParties" name="pmContract.contractingParties" value="${pmContract.contractingParties}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">开始日期:&nbsp;</th>
					<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${pmContract.createTime }' 
						type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">发包人:&nbsp;</th>
					<td><input id="employer" name="pmContract.employer" value="${pmContract.employer}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">承包人:&nbsp;</th>
					<td><input id="contractor" name="pmContract.contractor" value="${pmContract.contractor}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">监理人:&nbsp;</th>
					<td><input id="supervisor" name="pmContract.supervisor" value="${pmContract.supervisor}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">工程和设备:&nbsp;</th>
					<td><input id="engineeringEquipment" name="pmContract.engineeringEquipment" value="${pmContract.engineeringEquipment}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">单位工程:&nbsp;</th>
					<td><input id="unitProject" name="pmContract.unitProject" value="${pmContract.unitProject}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">临时占地:&nbsp;</th>
					<td><input id="temporaryCovering" name="pmContract.temporaryCovering" value="${pmContract.temporaryCovering}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">交公日期:&nbsp;</th>
					<td><input id="jiaoGongTime" name="jiaoGongTime" value="<fmt:formatDate value='${pmContract.jiaoGongTime }' 
						type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('jiaoGongTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">缺陷责任期:&nbsp;</th>
					<td><input id="defectsLiabilityPeriod" name="pmContract.defectsLiabilityPeriod" value="${pmContract.defectsLiabilityPeriod}" type="text" class="validate[required] text-input" /> <span style="color: red;">天*</span></td>
				</tr>
				<tr height="40">
					<th align="right">保修期:&nbsp;</th>
					<td><input id="warrantyPeriod" name="pmContract.warrantyPeriod" value="${pmContract.warrantyPeriod}" type="text" class="validate[required] text-input" /> <span style="color: red;">天*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#daForm" ).validationEngine ( );
</script>