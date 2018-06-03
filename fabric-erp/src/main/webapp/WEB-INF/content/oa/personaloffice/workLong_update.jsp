<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript">
$(function() {
    //加载更新时间(新增)
    //因为每次都需要加载新的时间，所以屏蔽
     if (document.getElementById("updateTime").value == "")  {
	    var myDate = new Date();
	    $("#updateTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours()+ ":" + myDate.getMinutes()+ ":" + myDate.getSeconds());
    }
});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="brandForm">
		<input type="hidden" id="logCommentId" name="id" value="${logComment.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr>
					<td><textarea id="commentscontent" name="commentscontent">${logComment.commentscontent}</textarea> <script type="text/javascript">
							 var commentscontents = KindEditor.create('textarea[name="commentscontent"]',
							{basePath:'${vix}/plugin/KindEditor/',
								width:670,
								height:245,
								cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
								uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
								fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
								allowFileManager : true 
							}
						 );
					 </script></td>
				</tr>
				<tr>
					<td><input id="updateTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="updateTime" value="<fmt:formatDate value='${logComment.updateTime}' type='both' pattern='yyyy-MM-dd' />" type="hidden" style="width: 190px; height: 25px;" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>