<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function initdata() {
		$("#channelDistributorId").val('${coupon.channelDistributor.id}');
	}
	initdata();
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="couponForm">
		<s:hidden id="couponId" name="couponId" value="%{coupon.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">主题:&nbsp;</td>
					<td><input type="text" id="name" name="name" class="validate[required] text-input" value="${coupon.name}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
					<td align="right">门店:&nbsp;</td>
					<td><s:select id="channelDistributorId" headerKey="" headerValue="总部" list="%{channelDistributorList}" listValue="name" listKey="id" value="" onchange="fieldChanged(this);" multiple="false" theme="simple">
						</s:select></td>
				</tr>
				<tr height="40">
					<td align="right">类别:&nbsp;</td>
					<td><input type="text" id="type" name="type" class="validate[required] text-input" value="${coupon.type}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
					<td align="right">面额:&nbsp;</td>
					<td><input type="text" id="amount" name="amount" class="validate[required] text-input" value="${coupon.amount}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">消费额:&nbsp;</td>
					<td><input type="text" id="expenditure" name="expenditure" class="validate[required] text-input" value="${coupon.expenditure}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
					<td align="right">数量:&nbsp;</td>
					<td><input type="text" id="quantity" name="quantity" class="validate[required] text-input" value="${coupon.quantity}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">生效时间:&nbsp;</td>
					<td><input id="effectiveDate" name="effectiveDate" value="${coupon.effectiveDate}" type="text" onchange="fieldChanged(this);" /><img onclick="showTime('effectiveDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">截止时间:&nbsp;</td>
					<td><input id="cutOffDate" name="cutOffDate" value="${coupon.cutOffDate}" type="text" onchange="fieldChanged(this);" /><img onclick="showTime('cutOffDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td align="right">使用限制:&nbsp;</td>
					<td colspan="3"><input type="text" id="userule" name="userule" class="validate[required] text-input" value="${coupon.userule}" size="50" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><textarea rows="1" cols="3" id="memo" name="memo" style="width: 425px; height: 60px" onchange="fieldChanged(this);">${coupon.memo}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
