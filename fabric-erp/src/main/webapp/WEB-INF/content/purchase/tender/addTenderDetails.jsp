<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
$(function(){
	//设置单据类型选中(修改)
	$("#typeDetails").val('${purchaseTenderDetails.type}');
});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="tenderDetailsForm">
		<s:hidden id="idDetails" name="purchaseTenderDetails.id" value="%{purchaseTenderDetails.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="tenderCodeDetails" value="${purchaseTenderDetails.tenderCode}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<th align="right">主题:&nbsp;</th>
					<td><input id="titleDetails" value="${purchaseTenderDetails.title}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">类型:&nbsp;</th>
					<td><select id="typeDetails" style="width: 50" class="validate[required] text-input">
							<option value="">请选择</option>
							<option value="1">类型1</option>
							<option value="2">类型2</option>
					</select><span style="color: red;">*</span></td>
					<th align="right">竞标地点:&nbsp;</th>
					<td><input id="tenderAgentDetails" value="${purchaseTenderDetails.tenderAgent}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">联系人:&nbsp;</th>
					<td><input id="contactDetails" value="${purchaseTenderDetails.contact}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<th align="right">联系电话:&nbsp;</th>
					<td><input id="telephoneDetails" value="${purchaseTenderDetails.telephone}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">开始时间:&nbsp;</th>
					<td><input id="startTimeDetails" value="<fmt:formatDate value='${purchaseTenderDetails.startTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('startTimeDetails','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
						width="16" height="22" align="absmiddle"></td>
					<th align="right">结束时间:&nbsp;</th>
					<td><input id="endTimeDetails" value="<fmt:formatDate value='${purchaseTenderDetails.endTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('endTimeDetails','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
						width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">简介:&nbsp;</th>
					<td colspan="3"><textarea id="introductionDetails" class="example" rows="2" style="width: 500px">${purchaseTenderDetails.introduction }</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#tenderDetailsForm").validationEngine();
</script>