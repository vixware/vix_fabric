<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<h1 align="center" style="color: #0000C6;">${projectManagement.proposalTitle}</h1>
	<div class="source">
		<span>发布人：${projectManagement.uploadPersonName}</span> <span>发布时间:<s:date name="projectManagement.initiateTime" format="yyyy-MM-dd" /></span> <span>截止时间:<s:date name="projectManagement.overTime" format="yyyy-MM-dd" /></span> <span>发布范围：${projectManagement.bizOrgNames}</span>
	</div>
	<div class="fonts">
		【字体：<a class="navy" onclick="document.getElementById('content').style.fontSize='24px';" href="javascript:;">大</a> <a class="navy" onclick="document.getElementById('content').style.fontSize='16px'; " href="javascript:;">中</a> <a class="navy" onclick="document.getElementById('content').style.fontSize='12px';" href="javascript:;">小</a>】 <input
			type="button" class="btn" onclick="javascript:window.print()" value="打印本页" name="print2">
	</div>
	<div class="source">
		<span>计划内容</span>
	</div>
	<div id="content">
		<font size="1" face="楷体_GB2312" style="FONT-SIZE: 12pt">&nbsp;&nbsp;&nbsp;${projectManagement.planContent}</font>
	</div>
	<br />
	<div class="source">
		<span>备注</span>
	</div>
	<div id="content">
		<font size="1" face="楷体_GB2312" style="FONT-SIZE: 12pt">&nbsp;&nbsp;&nbsp;${projectManagement.remark}</font>
	</div>
	<div class="source">
		<!-- projectManagement.postil.size获取评论数量 -->
		<span>最新 ${projectManagement.postil.size()} 条批注</span>
	</div>
	<br />
	<s:iterator value="projectManagement.postil" var="entity" status="s">
		<div class="left">
			<span style="color: #0000C6;">评论人：${entity.uploadPersonName}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #0000C6;">评论时间：<s:date name="#entity.createTime" format="yyyy-MM-dd" /></span>
		</div>
		<div id="content">
			<font size="1" face="楷体_GB2312" style="FONT-SIZE: 12pt">&nbsp;&nbsp;&nbsp; <s:property value="#entity.planContent" /><br>&nbsp;&nbsp;&nbsp;
			</font>
		</div>
	</s:iterator>
</div>
