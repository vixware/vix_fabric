<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
<script type="text/javascript">
function load(){
	window.open ('${trends.sourceFromUrl}') 
}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<h1 align="center" style="color: #0000C6;">${trends.title}</h1>
	<div class="source">
		<span>${trends.uploadPersonName}</span><span>发布时间:<s:date name="trends.createTime" format="yyyy-MM-dd" /></span><span>点击: ${trends.readTimes}次</span>
	</div>
	<div class="fonts">
		【字体：<a class="navy" onclick="document.getElementById('content').style.fontSize='24px';" href="javascript:;">大</a> <a class="navy" onclick="document.getElementById('content').style.fontSize='16px'; " href="javascript:;">中</a> <a class="navy" onclick="document.getElementById('content').style.fontSize='12px';" href="javascript:;">小</a>】 <input
			type="button" class="btn" onclick="javascript:window.print()" value="打印本页" name="print2">
	</div>
	<div id="content">
		<font size="1" face="楷体_GB2312" style="FONT-SIZE: 12pt">&nbsp;&nbsp;&nbsp;${trends.content}</font>
	</div>
	<br />
	<div class="left">
		<span style="color: #0000C6;"><a href="javascript:void(0);" onclick="window.open('${trends.sourceFromUrl}')">全文连接地址： ${trends.sourceFromUrl}</a></span>
	</div>
	<br />
	<div class="left">
		<span style="color: #0000C6;">本文关键词： ${trends.keywords}</a></span>
	</div>
	<br />
	<div class="source">
		<!-- trends.comments.size获取评论数量 -->
		<span>最新 ${trends.comments.size()} 条评论</span>
	</div>
	<br />
	<s:iterator value="trends.comments" var="entity" status="s">
		<div class="left">
			<span style="color: #0000C6;">评论人：${entity.uploadPersonName}</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #0000C6;">评论时间：<s:date name="#entity.createTime" format="yyyy-MM-dd HH:mm:ss" /></span>
		</div>
		<div id="content">
			<font size="1" face="楷体_GB2312" style="FONT-SIZE: 12pt">&nbsp;&nbsp;&nbsp; <s:property value="#entity.commentscontent" /><br>&nbsp;&nbsp;&nbsp;
			</font>
		</div>
	</s:iterator>
	<!-- JiaThis Button BEGIN -->
	<div class="jiathis_style">
		<a class="jiathis_button_qzone"></a> <a class="jiathis_button_tsina"></a> <a class="jiathis_button_tqq"></a> <a class="jiathis_button_weixin"></a> <a class="jiathis_button_renren"></a> <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a> <a class="jiathis_counter_style"></a>
	</div>
	<br>
</div>
