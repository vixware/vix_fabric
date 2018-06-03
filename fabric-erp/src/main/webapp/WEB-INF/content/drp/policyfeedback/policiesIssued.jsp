<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#policyInformationForm").validationEngine();
    function chooseParentOrganization() {
	    $.ajax({
	    url : '${vix}/drp/distributionSystemRelationShipAction!goChooseOrganization.action',
	    cache : false,
	    success : function(html) {
		    asyncbox.open({
		    modal : true,
		    width : 560,
		    height : 340,
		    title : "选择公司",
		    html : html,
		    callback : function(action,returnValue) {
			    if (action == 'ok') {
				    if (returnValue != undefined) {
					    var result = returnValue.split(",");
					    if (result[0] == $("#id").val()) {
						    asyncbox.success("不允许引用本公司为父公司!", "提示信息");
					    } else {
						    $("#channelDistributorId").val(result[0]);
						    $("#channelDistributorName").val(result[1]);
					    }
				    } else {
					    $("#channelDistributorId").val("");
					    $("#channelDistributorName").val("");
					    asyncbox.success("请选择公司信息!", "提示信息");
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
	<form id="policyInformationForm">
		<s:hidden id="policyInformationId" name="policyInformationId" value="%{policyInformation.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr>
					<td align="right">门店：</td>
					<td><input id="channelDistributorName" name="channelDistributorName" type="text" class="validate[required] text-input" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" /> <input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>