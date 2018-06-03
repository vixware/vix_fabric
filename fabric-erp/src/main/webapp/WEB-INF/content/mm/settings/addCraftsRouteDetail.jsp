<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>

<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="detail.id" value="%{detail.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">工序编号:&nbsp;</th>
					<td><input id="processCode" name="detail.processCode" value="${detail.processCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">标准工序:&nbsp;</th>
					<td><input id="standardProcedure" name="detail.standardProcedure" value="${detail.standardProcedure}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">工序说明:&nbsp;</th>
					<td><input id="processExplain" name="detail.processExplain" value="${detail.processExplain}" type="text" /></td>
					<th align="right">报告点:&nbsp;</th>
					<td><input id="repPoint" name="detail.repPoint" value="${detail.repPoint}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">倒冲工序:&nbsp;</th>
					<td><input id="reverseBlankingProcess" name="detail.reverseBlankingProcess" value="${detail.reverseBlankingProcess}" type="text" /></td>
					<th align="right">工作中心:&nbsp;</th>
					<td><input id="org" name="detail.org" value="${detail.org}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">委外工序:&nbsp;</th>
					<td><input id="qutsourcingProcess" name="detail.qutsourcingProcess" value="${detail.qutsourcingProcess}" type="text" /></td>
					<th align="right">厂商名称:&nbsp;</th>
					<td><input id="tradeName" name="detail.tradeName" value="${detail.tradeName}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#daForm" ).validationEngine ( );
</script>