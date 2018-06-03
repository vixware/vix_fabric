<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(function() {
    //加载更新时间(新增)
    if (document.getElementById("evaluationTime").value == "") {
	    var myDate = new Date();
	    $("#evaluationTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
    }
});
</script>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="brandForm">
		<input type="hidden" id="id" name="id" value="${evaluationReview.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr>
					<td align="right">评论时间：</td>
					<td><input id="evaluationTime" name="evaluationReview.evaluationTime" value="${evaluationReview.evaluationTime}" onchange="salesOrderFieldChanged(this);" type="text" class="validate[required] text-input" /> <img onclick="showTime('evaluationReview','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
				</tr>
				<tr>
					<td align="right">评论内容：</td>
					<td><textarea id="evaluationContent" name="evaluationContent">${evaluationReview.evaluationContent}</textarea> <script type="text/javascript">
							 var evaluationContent = KindEditor.create('textarea[name="evaluationContent"]',
							{basePath:'${vix}/plugin/KindEditor/',
								width:600,
								height:245,
								cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
								uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
								fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
								allowFileManager : true 
						}
					 );
					 </script></td>
				</tr>
			</table>
		</div>
	</form>
</div>