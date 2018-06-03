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
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
    //加载时间(新增)
    if (document.getElementById("createTime").value == "") {
	    var myDate = new Date();
	    $("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
    }
    
    //默认选择
    if($('input:radio[name=enable]:checked').length==0){
    	$('input:radio[name=enable]:first').trigger('click');
    }
   
});
</script>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box cardTable">
		<form id="brandForm">
			<input type="hidden" id="id" name="id" value="${inspectionStatus.id}" />
			<table width="100%">
				<tr>
					<th class="tr" width="150">编码：</th>
					<td><input id="code" name="inspectionStatus.code" value="${inspectionStatus.code}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr" width="150">内容：</th>
					<td colspan="3"><textarea id="name" class="example" rows="1" style="width: 200px; height: 54px;">${inspectionStatus.name}</textarea></td>
				</tr>
				<tr>
					<th class="tr" width="150">备注：</th>
					<td colspan="3"><textarea id="memo" class="memo" rows="1" style="width: 200px; height: 54px;">${inspectionStatus.memo}</textarea></td>
				</tr>
				<tr>
					<td align="right">是否启用:&nbsp;</td>
					<td><s:radio list="#{0:\"否\",1:\"是\"}" name="enable" value="%{inspectionStatus.enable}" theme="simple"></s:radio></td>
				</tr>
				<tr>
					<th class="tr" width="150">时间：</th>
					<td><input id="createTime" name="inspectionStatus.createTime" value="${inspectionStatus.createTime}" onchange="salesOrderFieldChanged(this);" type="text" class="validate[required] text-input" /> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>