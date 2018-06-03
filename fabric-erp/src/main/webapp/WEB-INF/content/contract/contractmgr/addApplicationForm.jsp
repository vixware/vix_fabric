<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="applicationFormss">
		<s:hidden id="daId" name="applications.id" value="%{applications.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">审批人:&nbsp;</th>
					<td><input id="underTakePerson" name="applications.underTakePerson" value="${applications.underTakePerson}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">审批时间:&nbsp;</th>
					<td><input id="underTakeDate" name="underTakeDate" value="<fmt:formatDate value='${applications.underTakeDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('underTakeDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#applicationFormss" ).validationEngine ( );
</script>