<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<input type="hidden" id="id" name="id" value="${messageManagement.id}" />
	<h1 align="center" style="color: #0000C6;">${messageManagement.uploadPersonName}</h1>
	<div class="source">
		<span>收信人：${messageManagement.senderPeople}</span> <span>抄送人：${messageManagement.ccPeople}&nbsp;&nbsp;${messageManagement.ccPeople1}</span> <span>发布时间: <fmt:formatDate value="${messageManagement.sendDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>
	</div>
	<div class="fonts">
		【字体：<a class="navy" onclick="document.getElementById('content').style.fontSize='24px';" href="javascript:;">大</a> <a class="navy" onclick="document.getElementById('content').style.fontSize='16px'; " href="javascript:;">中</a> <a class="navy" onclick="document.getElementById('content').style.fontSize='12px';" href="javascript:;">小</a>】 <input
			type="button" class="btn" onclick="javascript:window.print()" value="打印本页" name="print2">
	</div>
	<div id="content">
		<font size="1" face="楷体_GB2312" style="FONT-SIZE: 12pt">&nbsp;&nbsp;&nbsp;${messageManagement.newscontent}</font>
	</div>
	<br />
</div>


<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="source">
		<!-- workLog.logComment.size获取评论数量 -->
		<span>最新 ${messageManagement.newsComment.size()} 条评论</span>
	</div>
	<br />
	<s:iterator value="messageManagement.newsComment" var="entity" status="s">
		<div class="left">
			<span style="color: #0000C6;">评论人：${entity.uploadPersonName}</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">评论时间：<s:date name="#entity.updateTime" format="yyyy-MM-dd HH:mm:ss" /></span>
		</div>
		<div id="content">
			<font size="1" face="楷体_GB2312" style="FONT-SIZE: 12pt">&nbsp;&nbsp;&nbsp; <s:property value="#entity.commentscontent" /><br>&nbsp;&nbsp;&nbsp;
			</font>
		</div>
	</s:iterator>
</div>
