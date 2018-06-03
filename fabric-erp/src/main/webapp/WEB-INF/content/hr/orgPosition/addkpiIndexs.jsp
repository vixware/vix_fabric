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
	<form id="caForm">
		<s:hidden id="daId" name="kpiIndexs.id" value="%{kpiIndexs.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="kpiCode" name="kpiIndexs.kpiCode" value="${kpiIndexs.kpiCode}" type="text" /></td>
					<th align="right">名称:&nbsp;</th>
					<td><input id="kpiName" name="kpiIndexs.kpiName" value="${kpiIndexs.kpiName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">名称描述:&nbsp;</th>
					<td><input id="kpiNameDepict" name="kpiIndexs.kpiNameDepict" value="${kpiIndexs.kpiNameDepict}" type="text" /></td>
					<th align="right">公式:&nbsp;</th>
					<td colspan="1"><textarea id="formula" name="formula" class="example" rows="1" style="width: 200px">${kpiIndexs.formula }</textarea></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="kpiIndexs.remarks" value="${kpiIndexs.remarks}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#caForm").validationEngine();
</script>