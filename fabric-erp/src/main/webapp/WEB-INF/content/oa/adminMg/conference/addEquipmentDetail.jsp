<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>
<script type="text/javascript">
$("#daForm").validationEngine();
/** 选择单选供应商 */
	function chooseEquipment() {
		$.ajax({
		url : '${vix}/oa/applicationMeetingAction!goChooseEquipment.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择设备",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#equipmentName").val(rv[1]);
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
	<form id="orderItemForm">
		<s:hidden id="daId" name="equipmentDetails.id" value="%{equipmentDetails.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">设备名称：&nbsp;</th>
					<td><input id="equipmentName" name="equipmentName" value="${equipmentDetails.equipmentName }" type="text" class="validate[required] text-input" /> <input class="btn" type="button" value="选择" onclick="chooseEquipment();" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>