<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

</script>
<style>
.content {
	margin-bottom: 0;
}

.cardTable {
	padding: 0 10px;
}

.cardTable table th, .cardTable table td {
	padding: 5px;
	vertical-align: top;
	border: #CCC solid 1px;
}

.cardTable table th {
	background: #DCE7F1;
}

.cardTable table .tr {
	text-align: right;
}

.cardTable .popupArea {
	height: 300px;
}

.cardTable .checkbox {
	vertical-align: middle;
}

.cardTable label {
	margin-right: 10px;
}
</style>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<script type="text/javascript">
if('${null != meetingRequest.department }'){
	var chkTime = '${meetingRequest.allowedTime }'.split(",");
	for (var i=0;i<chkTime.length-1;i++){
		if ($("#allowedTime"+chkTime[i]).val() == chkTime[i]) {  
            $("#allowedTime"+chkTime[i]).attr("checked", "checked");  
        }
	}
}
</script>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box cardTable">
		<form id="brandForm">
			<input type="hidden" id="id" name="id" value="${meetingRequest.id}" />
			<table style="border: none;">
				<tr>
					<th class="tr" width="150">会议室名称：</th>
					<td colspan="2"><input id="name" name="name" value="${meetingRequest.name }" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr">会议室描述：</th>
					<td colspan="2"><textarea id="description" name="description" class="example" rows="2" style="width: 400px">${meetingRequest.description }</textarea></td>
				</tr>
				<tr>
					<th class="tr">可容纳人数：</th>
					<td><input id="capacity" name="capacity" value="${meetingRequest.capacity }" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr">会议室管理员：</th>
					<td colspan="2"><input id="creator" name="creator" value="${meetingRequest.creator }" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr">申请权限（部门）：</th>
					<td colspan="2"><input id="department" name="department" value="${meetingRequest.department }" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr">申请权限（人员）：</th>
					<td colspan="2"><input id="interestedPartyPerson" name="interestedPartyPerson" value="${meetingRequest.interestedPartyPerson }" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr">可申请时间：</th>
					<td colspan="2"><input class="checkbox" type="checkbox" id="allowedTime7" name="allowedTime" value="7"><label> 星期日</label> <input class="checkbox" type="checkbox" id="allowedTime1" name="allowedTime" value="1"><label> 星期一</label> <input class="checkbox" type="checkbox" id="allowedTime2" name="allowedTime" value="2"><label>
							星期二</label> <input class="checkbox" type="checkbox" id="allowedTime3" name="allowedTime" value="3"><label> 星期三</label> <input class="checkbox" type="checkbox" id="allowedTime4" name="allowedTime" value="4"><label> 星期四</label> <input class="checkbox" type="checkbox" id="allowedTime5" name="allowedTime" value="5"><label>
							星期五</label> <input class="checkbox" type="checkbox" id="allowedTime6" name="allowedTime" value="6"><label> 星期六</label></td>
				</tr>
				<tr>
					<th class="tr">设备情况：</th>
					<td colspan="2"><textarea id="equipment" name="equipment" class="example" rows="2" style="width: 400px">${meetingRequest.equipment }</textarea></td>
				</tr>
				<tr>
					<th class="tr">备注：</th>
					<td colspan="2"><textarea id="memo" name="memo" class="example" rows="2" style="width: 400px">${meetingRequest.memo }</textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>