<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/main.css" rel="stylesheet" type="text/css">

<title>公告列表</title>
</head>
<body>
	<section class="dynList">
		<div class="search">
			<p>
				<img src="${vix}/wechatcommon/images/search_icon.png" /><input type="text" placeholder="搜索">
			</p>
		</div>
		<div class="dynList_list1">
			<s:if test="announcementNotificationList.size > 0">
				<s:iterator value="announcementNotificationList" var="entity" status="s">
					<dl>
						<dt>
							<img src="${vix}/wechatcommon/images/dynList_pic1.jpg">
						</dt>
						<dd>
							<span><b>${entity.title}</b><i><fmt:formatDate value="${entity.updateTime }" pattern="yyyy-MM-dd" /></i></span> <strong>${entity.content}</strong>
						</dd>
					</dl>
				</s:iterator>
			</s:if>
		</div>
	</section>
	<p class="ftech ftech2">中盛科技提供技术支持</p>
</body>
</html>

