<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#channelDistributorId").val('${coupon.channelDistributor.id}');
	});

	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/channelAction!goChooseOrganization.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择代理",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						if (result[0] == $("#id").val()) {
							asyncbox.success("不允许引用本公司为父公司!", "提示信息");
						} else {
							$("#channelDistributorId").val(result[0]);
							document.getElementById('channelDistributorName').innerHTML = result[1];
						}
					} else {
						$("#channelDistributorId").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	$(function() {
		document.getElementById('channelDistributorName').innerHTML = '${coupon.channelDistributor.name }';
	});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="couponForm">
		<s:hidden id="couponId" name="couponId" value="%{coupon.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">代理：</td>
					<td><label id="channelDistributorName" style="width: 180px;"></label>&nbsp;<input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${coupon.channelDistributor.id}" /><input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /></td>
				</tr>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="code" name="code" class="validate[required] text-input" value="${coupon.code}" style="width: 180px;" /><span style="color: red;">*</span></td>
					<td align="right">主题:&nbsp;</td>
					<td><input type="text" id="name" name="name" class="validate[required] text-input" value="${coupon.name}" style="width: 180px;" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">类别:&nbsp;</td>
					<td><input type="text" id="type" name="type" class="validate[required] text-input" value="${coupon.type}" style="width: 180px;" /><span style="color: red;">*</span></td>
					<td align="right">面额:&nbsp;</td>
					<td><input type="text" id="amount" name="amount" class="validate[required] text-input" value="${coupon.amount}" style="width: 180px;" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">消费额:&nbsp;</td>
					<td><input type="text" id="expenditure" name="expenditure" class="validate[required] text-input" value="${coupon.expenditure}" style="width: 180px;" /><span style="color: red;">*</span></td>
					<td align="right">数量:&nbsp;</td>
					<td><input type="text" id="quantity" name="quantity" class="validate[required] text-input" value="${coupon.quantity}" style="width: 180px;" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">生效时间:&nbsp;</td>
					<td><input id="effectiveDate" name="effectiveDate" value="${coupon.effectiveDate}" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="validate[required] text-input" style="width: 180px;" readonly="readonly" /><span style="color: red;">*</span></td>
					<td align="right">截止时间:&nbsp;</td>
					<td><input id="cutOffDate" name="cutOffDate" value="${coupon.cutOffDate}" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="validate[required] text-input" style="width: 180px;" readonly="readonly" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">使用限制:&nbsp;</td>
					<td colspan="3"><input type="text" id="userule" name="userule" value="${coupon.userule}" style="width: 470px;" /></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><textarea rows="1" cols="3" id="memo" name="memo" style="width: 470px; height: 85px; margin-top: 5px; padding-top: 3px; padding-left: 3px;">${coupon.memo}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
