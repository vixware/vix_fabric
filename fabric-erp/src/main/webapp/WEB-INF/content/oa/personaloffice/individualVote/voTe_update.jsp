<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<form id="brandForm">
			<input type="hidden" id="id" name="id" value="${votingManagement.id}" />
			<table width="100%">
				<tr>
					<th class="tr" width="150">标题：</th>
					<td><input disabled="disabled" id="title" name="votingManagement.title" value="${votingManagement.title}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr" width="150">发布范围（部门/人员）：</th>
					<td><input type="hidden" id="pubIds" name="pubIds" value="${pubIds}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" /> <input disabled="disabled" style="width: 500px; height: 22px;" type="text" id="pubNames" name="pubNames" readonly="readonly" value="${votingManagement.pubNames}" /></td>
				</tr>
				<tr>
					<th class="tr">投票描述：</th>
					<td><div style="width: 300px; height: 97px; float: clear;">${votingManagement.voteDescribe}</div></td>
				</tr>
				<tr>
					<th class="tr">有效期：</th>
					<td>生效日期：<input disabled="disabled" id="startTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="startTime" value="<fmt:formatDate value='${votingManagement.startTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="26" /> <img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
						width="16" height="22" align="absmiddle"><br> 终止日期：<input disabled="disabled" id="endTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="endTime" value="<fmt:formatDate value='${votingManagement.endTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="26" /> <img onclick="showTime('endTime','yyyy-MM-dd')"
						src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><br>
					</td>
				</tr>
				<tr>
					<th class="tr">备注：</th>
					<td><div style="width: 300px; height: 97px; float: clear;">${votingManagement.remarks}</div></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>