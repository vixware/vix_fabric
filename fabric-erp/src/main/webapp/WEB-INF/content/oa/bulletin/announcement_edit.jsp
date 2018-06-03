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
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box cardTable">
		<form id="brandForm" method="post" enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="${noticeUploader.id}" /> <input type="hidden" id="announcementNotificationId" name="noticeUploader.announcementNotification.id" value="${noticeUploader.announcementNotification.id}" />
			<table width="100%">
				<tr>
					<th class="tr" width="150">文档名称：</th>
					<td><input id="title" name="noticeUploader.title" value="${noticeUploader.title}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr" width="150">文档类型：</th>
					<td><input id="fileType" name="noticeUploader.fileType" value="${noticeUploader.fileType}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th>上传文件：</th>
					<td colspan="3"><input type="file" id="fileToUpload" name="fileToUpload" style="width: 90%;" /></td>
				</tr>
				<tr>
					<th>文件备注：</th>
					<td colspan="3" style="padding-top: 5px;"><textarea id="note" name="noticeUploader.note" rows="1" cols="1" style="width: 90%; height: 50px;"><s:property value="noticeUploader.note" escape="false" /></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>