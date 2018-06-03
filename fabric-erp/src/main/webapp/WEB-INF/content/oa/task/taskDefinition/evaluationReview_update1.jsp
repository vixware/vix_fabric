<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="source">
		<span>任务反馈内容</span>
	</div>
	<br />
	<div class="left">
		<span style="color: #0000C6;">作者：${executionFeedback.uploadPersonName}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">反馈时间：<s:date name="executionFeedback.feedbackTime" format="yyyy-MM-dd" /></span><br>
	</div>
	<div id="content">
		<font size="1" face="楷体_GB2312" style="FONT-SIZE: 12pt">&nbsp;&nbsp;&nbsp; ${executionFeedback.executionFeedback}<br>&nbsp;&nbsp;&nbsp;
		</font>
	</div>
	<br />
	<div class="source">
		<!-- executionFeedback.evaluationReview.size获取评论数量 -->
		<span>最新 ${executionFeedback.evaluationReview.size()} 条评论</span>
	</div>
	<br />
	<s:iterator value="executionFeedback.evaluationReview" var="entity" status="s">
		<div class="left">
			<span style="color: #0000C6;">评论人：${entity.uploadPersonName}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #0000C6;">评论时间：<s:date name="#entity.evaluationTime" format="yyyy-MM-dd" /></span><br>
		</div>
		<div id="content">
			<font size="1" face="楷体_GB2312" style="FONT-SIZE: 12pt">&nbsp;&nbsp;&nbsp; <s:property value="#entity.evaluationContent" /><br>&nbsp;&nbsp;&nbsp;
			</font>
		</div>
	</s:iterator>
</div>
