<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<html>
<head>
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/main.css" rel="stylesheet" type="text/css">

<title>添加公告</title>
</head>

<script type="text/javascript">
	function editTrends() {
		var title = $("#title").val();
		var content = $("#content").val();

		// 验证
		if (!title || $.trim(title).length <= 0) {
			alert("请输入公告标题");
			return;
		}
		if (!content || $.trim(content).length <= 0) {
			alert("请输入公告详情");
			return;
		}

		$.post("${vix}/wechat/weChatDynListAction!saveOrUpdateAnnouncementNotification.action", {
		'announcementNotification.id' : $("#announcementNotificationId").val(),
		'announcementNotification.title' : $("#title").val(),
		'announcementNotification.content' : $("#content").val()
		}, function(data) {
			cleardata();
			window.location.href = "${vix}/wechat/weChatDynListAction!getDynList.action";
		});
	}
	function cleardata() {
		$("#announcementNotificationId").val('');
		$("#title").val('');
		$("#content").val('');
	}
</script>
<body>
	<section>
		<form id="editTrendsForm">
			<input type="hidden" id="announcementNotificationId" name="announcementNotificationId" value="${announcementNotification.id }" />
			<div class="fillInfor">
				<ul>
					<li><span>公告标题</span><strong><input type="text" id="title" name="title" value="${announcementNotification.title }" placeholder="请输入公告标题"></strong></li>
				</ul>
				<h2>
					<textarea id="content" name="content" cols="" rows="" placeholder="请输入公告详情">${announcementNotification.content }</textarea>
				</h2>
			</div>
		</form>
	</section>
	<p class="ftech ftech2">中盛科技提供技术支持</p>
	<div class="fcontainer">
		<input name="" type="button" class="fmessageBt" value="取消" onClick="history.go(-1);" style="width: 45%; float: left; background: #b4b5b5;" /> <input name="" type="button" class="fmessageBt" value="保存" onclick="editTrends();" style="width: 45%; float: right;" />
	</div>
</body>
</html>

