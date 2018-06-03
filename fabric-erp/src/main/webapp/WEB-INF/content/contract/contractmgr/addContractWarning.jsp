<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="warningForm">
		<s:hidden id="daId" name="warning.id" value="%{warning.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">预警信息:&nbsp;</th>
					<td><input id="warnningContent" name="warning.warnningContent" value="${warning.warnningContent}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">类型:&nbsp;</th>
					<td><input id="warnningType" name="warning.warnningType" value="${warning.warnningType}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">预警时间:&nbsp;</th>
					<td><input id="warnningTime" name="warnningTime" value="<fmt:formatDate value='${warning.warnningTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('warnningTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#warningForm" ).validationEngine ( );
</script>