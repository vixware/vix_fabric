<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/news.css" rel="stylesheet" type="text/css" />
<div class="main">
	<h1>${announcementNotification.title}</h1>
	<div class="fonts">
		【字体：<a class="navy" onclick="document.getElementById('content').style.fontSize='24px';" href="javascript:;">大</a> <a class="navy" onclick="document.getElementById('content').style.fontSize='16px';" href="javascript:;">中</a> <a class="navy" onclick="document.getElementById('content').style.fontSize='12px';" href="javascript:;">小</a>】
	</div>
	<div id="content">
		<font id="Zoom"> ${announcementNotification.content}</font>
	</div>
	<div class="related"></div>
</div>
