<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$(function() {
    //加载更新时间(新增)
    //因为每次都需要加载新的时间，所以屏蔽
     /* if (document.getElementById("updateTime").value == "")  */{
	    var myDate = new Date();
	    $("#update_updateTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours()+ ":" + myDate.getMinutes()+ ":" + myDate.getSeconds());
    }
});
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
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box cardTable">
		<form id="brandForm">
			<input type="hidden" id="update_id" name="update_id" value="${workLog.id}" />
			<table width="100%">
				<tr>
					<th class="tr" width="150">日志标题：</th>
					<td><input id="update_title" name="workLog.title" value="${workLog.title}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th align="right">日志类型：</th>
					<td><s:radio list="#{'0':'工作日志','1':'个人日志'}" name="update_logType" value="%{workLog.logType}" theme="simple"></s:radio></td>
				</tr>
				<tr>
					<th class="tr">时间：</th>
					<td><input id="update_logDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="update_logDate" value="<fmt:formatDate value='${workLog.logDate}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" type="text" style="width: 190px; height: 25px;" /> <img onclick="showTime('update_logDate','yyyy-MM-dd HH:mm:ss ')"
						src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr>
					<th class="tr">修改时间：</th>
					<td><input id="update_updateTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="update_updateTime" value="<fmt:formatDate value='${workLog.updateTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" type="text" style="width: 190px; height: 25px;" /></td>
				</tr>
				<tr>
					<th class="tr">日志内容：</th>
					<td colspan="3"><textarea id="updateLogContent" name="updateLogContent" class="example" rows="1" style="width: 508px; height: 120px;">${workLog.logContent}</textarea> <script type="text/javascript">
				 var updateLogContent = KindEditor.create('textarea[name="updateLogContent"]',
				{basePath:'${vix}/plugin/KindEditor/',
					width:580,
					height:200,
					cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
					uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
					fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
					allowFileManager : true 
			}
		 );
		 </script></td>
				</tr>
				<tr>
					<td class="tr">事务提醒：</td>
					<td><label> <input name="" type="checkbox" value="" class="checkbox" />发送事务提醒消息
					</label></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>