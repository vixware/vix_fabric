<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="childItem.id" value="%{childItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">类型:&nbsp;</th>
					<td><input id="contractType" name="childItem.contractType" value="${childItem.contractType}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">状态:&nbsp;</th>
					<td><input id="contractStatus" name="childItem.contractStatus" value="${childItem.contractStatus}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">内容:&nbsp;</th>
					<td><input id="theContract" name="childItem.theContract" value="${childItem.theContract}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#daForm" ).validationEngine ( );
</script>