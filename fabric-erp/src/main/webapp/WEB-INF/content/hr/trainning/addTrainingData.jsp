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
	<form id="tdForm">
		<s:hidden id="daId" name="trainingData.id" value="%{trainingData.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">资料编码:&nbsp;</th>
					<td><input id="datumCode" name="trainingData.datumCode" value="${trainingData.datumCode}" type="text" /></td>
					<th align="right">资料类别:&nbsp;</th>
					<td><input id="datumType" name="trainingData.datumType" value="${trainingData.datumType}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">资料名称:&nbsp;</th>
					<td><input id="datumName" name="trainingData.datumName" value="${trainingData.datumName}" type="text" /></td>
					<th align="right">资料简介:&nbsp;</th>
					<td><input id="profile" name="trainingData.profile" value="${trainingData.profile}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">资料作者:&nbsp;</th>
					<td><input id="datumauthor" name="trainingData.datumauthor" value="${trainingData.datumauthor}" type="text" /></td>
					<th align="right">资料费用:&nbsp;</th>
					<td><input id="datumCost" name="trainingData.datumCost" value="${trainingData.datumCost}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">出版单位:&nbsp;</th>
					<td><input id="publishingUnit" name="trainingData.publishingUnit" value="${trainingData.publishingUnit}" type="text" /></td>
					<th align="right">存放位置:&nbsp;</th>
					<td><input id="storageLocation" name="trainingData.storageLocation" value="${trainingData.storageLocation}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarksss" name="trainingData.remarksss" value="${trainingData.remarksss}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#tdForm").validationEngine();
</script>