<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript">	
/**管理人*/
function chooseEmployees(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 600,
					title:"选择管理人",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										
										selectIds += "," + v[0];
										selectNames = v[1];
									}
								}
								$("#creator").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box cardTable">
		<form id="brandForm">
			<input type="hidden" id="id" name="id" value="${meetingRequest.id}" />
			<table style="border: none;">
				<tr>
					<th class="tr" width="150">会议室编码：</th>
					<td colspan="2"><input id="code" name="code" value="${meetingRequest.code }" type="text" style="width: 200px;" class="ipt" /> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<th class="tr" width="150">会议室名称：</th>
					<td colspan="2"><input id="meetingName" name="meetingName" value="${meetingRequest.meetingName }" type="text" style="width: 200px;" class="ipt" /> <span style="color: red;">*</span></td>
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
					<th class="tr">可申请开始时间：</th>
					<td><input id="allowedStartTime" style="width: 200px; height: 22px;" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="allowedStartTime" onclick="showTime('allowedStartTime','yyyy-MM-dd HH:mm:ss')" value="<fmt:formatDate value='${meetingRequest.allowedStartTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" type="text" size="29" /> <img
						onclick="showTime('allowedStartTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<th class="tr">可申请结束时间：</th>
					<td><input id="allowedEndTime" style="width: 200px; height: 22px;" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="allowedEndTime" onclick="showTime('allowedEndTime','yyyy-MM-dd HH:mm:ss')" value="<fmt:formatDate value='${meetingRequest.allowedEndTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" type="text" size="29" /> <img
						onclick="showTime('allowedEndTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<th class="tr">会议室管理员：</th>
					<td colspan="2"><input id="creator" name="creator" value="${meetingRequest.creator}" type="text" style="width: 200px;" class="ipt" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /></td>
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