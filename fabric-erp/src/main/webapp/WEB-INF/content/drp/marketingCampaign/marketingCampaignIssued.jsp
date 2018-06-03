<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#marketingCampaignForm").validationEngine();
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/marketingCampaignAction!goChooseChannelDistributor.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择执行机构",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var selectIds = "";
						var selectNames = "";
						var resObj = $.parseJSON(returnValue);
						for (var i = 0; i < resObj.length; i++) {
							selectIds += "," + resObj[i].treeid;
							selectNames += "," + resObj[i].treename;
						}
						$("#channelDistributorId").val(selectIds);
						$("#channelDistributorName").val(selectNames);
					} else {
						$("#channelDistributorId").val("");
						$("#channelDistributorName").val("");
						asyncbox.success("请选择机构信息!", "提示信息");
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
	<form id="marketingCampaignForm">
		<s:hidden id="marketingCampaignId" name="marketingCampaignId" value="%{marketingCampaign.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr>
					<td align="right">执行机构：</td>
					<td><input id="channelDistributorName" name="channelDistributorName" type="text" class="validate[required] text-input" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" /> <input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>