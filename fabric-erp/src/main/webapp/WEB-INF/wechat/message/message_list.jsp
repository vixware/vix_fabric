<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${vix}/common/core/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>消息列表</title>
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/main.css" rel="stylesheet" type="text/css">

</head>
<script type="text/javascript">
	function popNews(id) {
		asyncbox.open({
		modal : true,
		width : 780,
		height : 460,
		title : "消息列表",
		url : "${vix}/wechat/wechatMessageAction!goShowNews.action?id=" + id,
		btnsbar : $.btn.OKCANCEL
		});
	}
	$(document).ready(function() {
		$('.contact1_list2 ul li').hover(function() {
			$(this).addClass('over');
		}, function() {
			$(this).removeClass('over');
		});
	});
</script>
<style>
li:over {
	background: #EBEBEC;
}
</style>
<body>
	<div class="myMessage_list">
		<div class="search">
			<p>
				<img src="${vix}/wechatcommon/images/search_icon.png" /><input type="text" placeholder="搜索">
			</p>
		</div>
		<s:if test="messageManagementList.size > 0">
			<s:iterator value="messageManagementList" var="entity" status="s">
				<a href="${vix}/wechat/wechatMessageAction!goShowNews.action?id=${entity.id}"><dl>
						<dt>
							<img src="${vix}/wechatcommon/images/tou.jpg">
						</dt>
						<dd>
							<span>${entity.senderPeople}<b>${entity.newscontent}</b></span><strong><b><font style="color: red;">3</font>/15</b><em><fmt:formatDate value="${entity.sendDate }" pattern="yyyy-MM-dd" /></em></strong>
						</dd>
					</dl></a>
			</s:iterator>
		</s:if>
	</div>
	<p class="ftech ftech2">中盛科技提供技术支持</p>
</body>
</html>