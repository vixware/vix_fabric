<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#productInformationForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="productInformationForm">
		<s:hidden id="productInformationId" name="productInformationId" value="%{productInformation.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">名称:&nbsp;</th>
					<td><input id="productInformationName" name="productInformationName" value="${productInformation.name}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="specification" value="${productInformation.specification}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">地区:&nbsp;</th>
					<td><input id="productInformationRegion" name="productInformationRegion" value="${productInformation.region}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">价格:&nbsp;</th>
					<td><input id="price" name="price" value="${productInformation.price}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">生产日期 :&nbsp;</th>
					<td><input id="produceDate" name="produceDate" value="<s:date format="yyyy-MM-dd" name="%{#productInformation.produceDate}" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('produceDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
						style="color: red;">*</span></td>
					<th align="right">保质期:&nbsp;</th>
					<td><input id="qualityPeriod" name="qualityPeriod" value="${productInformation.qualityPeriod}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">投放日期:&nbsp;</th>
					<td><input id="putOnDate" name="putOnDate" value="<s:date format="yyyy-MM-dd" name="%{#productInformation.putOnDate}" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('putOnDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
						style="color: red;">*</span></td>
					<th align="right">主计量单位:&nbsp;</th>
					<td><input id="masterUnit" name="masterUnit" value="${productInformation.masterUnit}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">生产企业:&nbsp;</th>
					<td><input id="productEnterprise" name="productEnterprise" value="${productInformation.productEnterprise}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">品牌:&nbsp;</th>
					<td><input id="brand" name="brand" value="${productInformation.brand}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">产地:&nbsp;</th>
					<td><input id="origin" name="origin" value="${productInformation.origin}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>