<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="deForm">
		<s:hidden id="daId" name="familyRelationship.id" value="%{familyRelationship.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">员工编码:&nbsp;</th>
					<td><input id="employeeCode" name="familyRelationship.employeeCode" value="${familyRelationship.employeeCode}" type="text" /></td>
					<th align="right">成员类别:&nbsp;</th>
					<td><input id="categoriesOfMembership" name="familyRelationship.categoriesOfMembership" value="${familyRelationship.categoriesOfMembership}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">性别:&nbsp;</th>
					<td><input id="sex" name="familyRelationship.sex" value="${familyRelationship.sex}" type="text" /></td>
					<th align="right">出生日期:&nbsp;</th>
					<td><input id="dateOfBirth" name="dateOfBirth" value="<fmt:formatDate value='${familyRelationship.dateOfBirth}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('dateOfBirth','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">民族:&nbsp;</th>
					<td><input id="nation" name="familyRelationship.nation" value="${familyRelationship.nation}" type="text" /></td>
					<th align="right">身份证号:&nbsp;</th>
					<td><input id="idNumber" name="familyRelationship.idNumber" value="${familyRelationship.idNumber}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">是否港澳台人员:&nbsp;</th>
					<td><input id="whetherHongKongMacaoAndTaiwanStaff" name="familyRelationship.whetherHongKongMacaoAndTaiwanStaff" value="${familyRelationship.whetherHongKongMacaoAndTaiwanStaff}" type="text" /></td>
					<th align="right">户口所在地:&nbsp;</th>
					<td><input id="accountTheLocationOf" name="familyRelationship.accountTheLocationOf" value="${familyRelationship.accountTheLocationOf}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">政治面貌:&nbsp;</th>
					<td><input id="politicalLandscape" name="familyRelationship.politicalLandscape" value="${familyRelationship.politicalLandscape}" type="text" /></td>
					<th align="right">最高学历:&nbsp;</th>
					<td><input id="highestDegree" name="familyRelationship.highestDegree" value="${familyRelationship.highestDegree}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">与本人关系:&nbsp;</th>
					<td><input id="relationship" name="familyRelationship.relationship" value="${familyRelationship.relationship}" type="text" /></td>
					<th align="right">地址:&nbsp;</th>
					<td><input id="address" name="familyRelationship.address" value="${familyRelationship.address}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">联系电话:&nbsp;</th>
					<td><input id="tel" name="familyRelationship.tel" value="${familyRelationship.tel}" type="text" /></td>
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="familyRelationship.remarks" value="${familyRelationship.remarks}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#deForm").validationEngine();
</script>