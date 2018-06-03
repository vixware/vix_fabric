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
	<form id="brandForm">
		<input type="hidden" id="id" name="id" value="${scheduleQueries.id}" />
		<div class="box cardTable">
			<table width="100%">
				<tr>
					<th class="tr" width="150">文档名称：</th>
					<td><input id="rname" name="scheduleQueries.rname" value="${scheduleQueries.rname}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<td align="right">选择文件:</td>
					<td><input name="attach" type="FILE" id="attach" size="30"></td>
				</tr>
				<tr>
					<th class="tr">文件描述：</th>
					<td colspan="2"><textarea name="" cols="" rows="" style="width: 400px;"></textarea></td>
				</tr>
				<tr>
					<th class="tr" width="150">文件标签：</th>
					<td><input id="rname" name="scheduleQueries.rname" value="${scheduleQueries.rname}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr">选择文件夹：</th>
					<td colspan="2"><select name="">
							<option>选择文件夹</option>
							<option>任务文档</option>
							<option>计划文档</option>
					</select></td>
				</tr>
				<tr>
					<th class="tr" width="150">发送文件通知：</th>
					<td><input id="rname" name="scheduleQueries.rname" value="${scheduleQueries.rname}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
			</table>
		</div>
	</form>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>