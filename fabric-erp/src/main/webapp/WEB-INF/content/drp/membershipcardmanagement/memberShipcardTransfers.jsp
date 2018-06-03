<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#memberShipCardForm").validationEngine();
    function chooseMemberShipCard() {
	    $.ajax({
	    url : '${vix}/drp/membershipPointsregistrationAction!goChooseMemberShipCard.action',
	    cache : false,
	    success : function(html) {
		    asyncbox.open({
		    modal : true,
		    width : 1000,
		    height : 500,
		    title : "选择会员卡",
		    html : html,
		    callback : function(action,returnValue) {
			    if (action == 'ok') {
				    if (returnValue != undefined) {
					    var rv = returnValue.split(",");
					    $("#otherMemberShipCardId").val(rv[0]);
					    $("#otherMemberShipCardCode").val(rv[2]);
				    } else {
					    asyncbox.success("请选择分类信息!", "提示信息");
				    }
			    }
		    },
		    btnsbar : $.btn.OKCANCEL
		    });
	    }
	    });
    }
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="memberShipCardForm">
		<s:hidden id="memberShipCardId" name="memberShipCardId" value="%{memberShipCard.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr>
					<td align="right">转出卡号:&nbsp;</td>
					<td><input type="text" id="vipcardid" name="vipcardid" value="${memberShipCard.vipcardid}" /></td>
					<td align="right">转入卡号：</td>
					<td><input id="otherMemberShipCardCode" name="otherMemberShipCardCode" type="text" class="validate[required] text-input" /><input type="hidden" id="otherMemberShipCardId" name="otherMemberShipCardId" /> <input class="btn" type="button" value="选择" onclick="chooseMemberShipCard();" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">余额:&nbsp;</td>
					<td><input type="text" id="balance_amount" name="balance_amount" value="${memberShipCard.balance_amount}" /></td>
					<td align="right">转出金额:&nbsp;</td>
					<td><input type="text" id="transfersOutAmount" name="transfersOutAmount" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>