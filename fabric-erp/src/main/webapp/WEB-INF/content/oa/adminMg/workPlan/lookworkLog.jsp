<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<h1 align="center" style="color: #0000C6;">${workLog.title}</h1>
	<div class="source">
		<span>发布人：${workLog.uploadPersonName}</span> <span>时间:<s:date name="workLog.logDate" format="yyyy-MM-dd" /></span>
	</div>
	<div class="fonts">
		【字体：<a class="navy" onclick="document.getElementById('content').style.fontSize='24px';" href="javascript:;">大</a> <a class="navy" onclick="document.getElementById('content').style.fontSize='16px'; " href="javascript:;">中</a> <a class="navy" onclick="document.getElementById('content').style.fontSize='12px';" href="javascript:;">小</a>】 <input
			type="button" class="btn" onclick="javascript:window.print()" value="打印本页" name="print2">
	</div>
	<div class="source">
		<span>日志内容</span>
	</div>
	<div id="content">
		<font size="1" face="楷体_GB2312" style="FONT-SIZE: 12pt">&nbsp;&nbsp;&nbsp;${workLog.logContent}</font>
	</div>

</div>
