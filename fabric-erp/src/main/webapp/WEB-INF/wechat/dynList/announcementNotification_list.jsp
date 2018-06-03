<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
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
							<img src="${vix}${entity.firstPictureUrl}">
						</dt>
						<dd>
							<span><b><a href="${vix}/wechat/weChatDynListAction!goShowAnnouncementNotification.action?id=${entity.id}">${entity.title}</a></b><i>${fn:substring(entity.createTime, 0, 10)}</i></span> <strong>
								<div>
									<h1>
										<!-- <b>置顶</b> -->${fn:substring(entity.content, 0, 20)}...
									</h1>
									<h2>
										<span><b></b> <c:choose>
												<c:when test="${not empty entity.pointPraiseNums}">
													${entity.pointPraiseNums }
												</c:when>
												<c:otherwise>
													0
												</c:otherwise>
											</c:choose></span><span><b class="icon2"></b> <c:choose>
												<c:when test="${not empty entity.readTimes}">
													${entity.readTimes }
												</c:when>
												<c:otherwise>
													0
												</c:otherwise>
											</c:choose> </span><span><b class="icon3"></b> <c:choose>
												<c:when test="${not empty entity.replyNums}">
													${entity.replyNums }
												</c:when>
												<c:otherwise>
													0
												</c:otherwise>
											</c:choose></span>
									</h2>
								</div>
							</strong>
						</dd>
					</dl>
				</s:iterator>
			</s:if>
		</div>
	</section>
</body>
</html>